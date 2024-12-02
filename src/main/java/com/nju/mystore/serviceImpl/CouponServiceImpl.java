package com.nju.mystore.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.mystore.enums.CouponEnum;
import com.nju.mystore.enums.CouponStatusEnum;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.Coupon;
import com.nju.mystore.po.CouponGroup;
import com.nju.mystore.po.Invoice;
import com.nju.mystore.po.User;
import com.nju.mystore.repository.CouponGroupRepository;
import com.nju.mystore.repository.CouponRepository;
import com.nju.mystore.repository.InvoiceRepository;
import com.nju.mystore.repository.UserRepository;
import com.nju.mystore.service.CouponService;
import com.nju.mystore.util.SecurityUtil;
import com.nju.mystore.vo.CouponGroupVO;
import com.nju.mystore.vo.CouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponGroupRepository couponGroupRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    SecurityUtil securityUtil;


    @Override
    public Integer createCouponGroup(Integer num, CouponVO couponVO) {
        CouponGroup newCouponGroup = new CouponGroup();
        User user = securityUtil.getCurrentUser();
        CouponEnum couponGroupSpace = couponVO.getCouponSpace();
        if (couponGroupSpace == CouponEnum.GlobalCoupon && user.getRole() != RoleEnum.CEO) { // 只有经理可以创建全局优惠券
            throw BlueWhaleException.couponGroupGlobal();
        } else if (couponGroupSpace == CouponEnum.StoreOnly && !Objects.equals(user.getStoreId(), couponVO.getCouponStoreId())) {
            // 只有商店员工可以创建本店
            throw BlueWhaleException.couponGroupStore();
        } else {
            newCouponGroup.setCouponGroupCreateTime(new Date());
            newCouponGroup.setCouponGroupRemains(num);
            newCouponGroup.setCouponGroupStoreId(couponVO.getCouponStoreId());
            newCouponGroup.setCouponGroupSpace(couponGroupSpace);
            newCouponGroup.setCouponGroupReceiveNum(0);
            newCouponGroup.setCouponGroupCategory(couponVO.getCouponCategory());
            if (couponVO.getCouponCategory() == CouponEnum.FullReduction) {
                newCouponGroup.setFull(couponVO.getFull());
                newCouponGroup.setReduction(couponVO.getReduction());
            }
            newCouponGroup = couponGroupRepository.save(newCouponGroup);
            int count = newCouponGroup.getCouponGroupRemains();
            for (int i = 0; i < count; i++) {
                createCoupon(couponVO, newCouponGroup.getCouponGroupId());
            }
        }
        return newCouponGroup.getCouponGroupId();
    }

    @Override
    public Integer createCoupon(CouponVO couponVO, Integer couponGroupId) {
        Coupon newCoupon = couponVO.toPO();
        newCoupon.setCouponGroupId(couponGroupId);
        newCoupon.setCouponCreateTime(new Date());
        couponRepository.save(newCoupon);
        couponRepository.updateCouponStatus(newCoupon.getCouponId(), CouponStatusEnum.FREE);
        return newCoupon.getCouponId();
    }

    @Override
    public List<CouponGroupVO> getAllCouponGroups() {
        User user = securityUtil.getCurrentUser();
        if (user.getRole() == RoleEnum.CEO) {
            List<CouponGroup> list = couponGroupRepository.findAll();
            return list.stream().map(CouponGroup::toVO).collect(Collectors.toList());
        } else if (user.getRole() == RoleEnum.MANAGER) {
            throw BlueWhaleException.couponNotExist();
        } else if (user.getRole() == RoleEnum.STAFF) {
            return getAllCouponGroupsByStoreId(user.getStoreId());
        } else {
            List<Integer> couponIds = user.getCoupons();
            List<CouponGroup> list = couponGroupRepository.findAll();
            if (list.isEmpty()) {
                throw BlueWhaleException.couponNotExist();
            }
            List<CouponGroup> list1 = new ArrayList<>(Collections.emptyList());
            for (CouponGroup couponGroup : list) {
                list1.add(couponGroup);
                for (Integer couponId : couponIds) {
                    if (Objects.equals(couponGroup.getCouponGroupId(), couponRepository.findById(couponId).get().getCouponGroupId())) {
                        list1.remove(couponGroup);
                        break;
                    }
                }
            }
            return list1.stream().map(CouponGroup::toVO).collect(Collectors.toList());
        }
    }

    @Override
    public List<CouponGroupVO> getAllCouponGroupsByStoreId(Integer storeId) {
        List<CouponGroup> list = couponGroupRepository.findByCouponGroupStoreId(storeId);
        return list.stream().map(CouponGroup::toVO).collect(Collectors.toList());
    }

    @Override
    public List<CouponVO> getAllCouponsByUserId() {
        User user = securityUtil.getCurrentUser();
        List<CouponVO> coupons = new ArrayList<>(Collections.emptyList());
        List<Integer> couponIds = user.getCoupons();
        if (couponIds == null) {
            throw BlueWhaleException.couponNotExist();
        }
        for (Integer couponId : couponIds) {
            coupons.add(couponRepository.findById(couponId).get().toVO());
        }
        return coupons;
    }

    @Override
    public List<CouponVO> getAllCouponsByInvoice(Integer invoiceId) {
        List<CouponVO> coupons = new ArrayList<>(Collections.emptyList());
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        int userId = invoice.getInvoiceUserId();
        int invoiceStoreId = invoice.getInvoiceStoreId();
        double price = invoice.getInvoicePrice();
        List<Integer> couponIds = userRepository.findById(userId).get().getCoupons();
        if (couponIds == null) {
            throw BlueWhaleException.couponNotExist();
        }

        for (Integer couponId : couponIds) {
            Coupon coupon = couponRepository.findById(couponId).get();
            if (coupon.getCouponStatus() == CouponStatusEnum.UNUSED) {
                if (coupon.getCouponCategory() == CouponEnum.FullReduction) {
                    if (coupon.getFull() <= price) {
                        if (coupon.getCouponSpace() == CouponEnum.GlobalCoupon) {
                            coupons.add(coupon.toVO());
                        } else if (coupon.getCouponSpace() == CouponEnum.StoreOnly) {
                            if (coupon.getCouponStoreId() == invoiceStoreId) {
                                coupons.add(coupon.toVO());
                            }
                        }
                    }
                } else if (coupon.getCouponCategory() == CouponEnum.SpecialCoupon) {
                    if (coupon.getCouponSpace() == CouponEnum.GlobalCoupon) {
                        coupons.add(coupon.toVO());
                    } else if (coupon.getCouponSpace() == CouponEnum.StoreOnly) {
                        if (coupon.getCouponStoreId() == invoiceStoreId) {
                            coupons.add(coupon.toVO());
                        }
                    }
                }
            }
        }
        if (coupons.isEmpty()) {
            throw BlueWhaleException.couponNotExist();
        }
        return coupons;
    }

    @Override
    @Transactional
    public Integer addCoupon(Integer invoiceId, Integer couponId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        List<Integer> newCoupons;
        if (invoice.getCoupons() == null) {
            newCoupons = new ArrayList<>(Collections.emptyList());
        } else {
            newCoupons = invoice.getCoupons();
        }
        newCoupons.add(couponId);
        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceId(invoiceId);
        newInvoice.setInvoiceUserId(invoice.getInvoiceUserId());
        newInvoice.setInvoiceStoreId(invoice.getInvoiceStoreId());
        newInvoice.setInvoicePrice(invoice.getInvoicePrice());
        newInvoice.setInvoiceProductId(invoice.getInvoiceProductId());
        newInvoice.setInvoiceProductNum(invoice.getInvoiceProductNum());
        newInvoice.setInvoiceStatus(invoice.getInvoiceStatus());
        newInvoice.setInvoiceName(invoice.getInvoiceName());
        newInvoice.setInvoiceTime(invoice.getInvoiceTime());
        newInvoice.setInvoiceAddress(invoice.getInvoiceAddress());
        newInvoice.setInvoicePhone(invoice.getInvoicePhone());
        newInvoice.setCoupons(newCoupons);
        entityManager.merge(newInvoice);
        return couponId;
    }


    @Override
    @Transactional
    public Integer receiveCoupon(Integer couponId) {
        User user = securityUtil.getCurrentUser();
        List<Integer> newCoupons = Collections.emptyList();
        if (user.getAddresses() == null) {
            newCoupons = new ArrayList<>(Collections.emptyList());
        } else {
            newCoupons = user.getCoupons();
        }

        newCoupons.add(couponId);
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setStoreId(user.getStoreId());
        newUser.setCreateTime(user.getCreateTime());
        newUser.setCoupons(newCoupons);
        newUser.setAddresses(user.getAddresses());
        newUser.setPhones(user.getPhones());
        entityManager.merge(newUser);
        couponRepository.updateCouponStatus(couponId, CouponStatusEnum.UNUSED);

        return couponId;
    }
}
