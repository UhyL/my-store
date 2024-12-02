package com.nju.mystore.serviceImpl;

import com.nju.mystore.configure.AlipayTools;
import com.nju.mystore.enums.CouponEnum;
import com.nju.mystore.enums.CouponStatusEnum;
import com.nju.mystore.enums.InvoiceStatusEnum;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.po.Coupon;
import com.nju.mystore.po.Invoice;
import com.nju.mystore.po.Product;
import com.nju.mystore.po.Store;
import com.nju.mystore.repository.CouponRepository;
import com.nju.mystore.repository.InvoiceRepository;
import com.nju.mystore.repository.ProductRepository;
import com.nju.mystore.repository.StoreRepository;
import com.nju.mystore.service.InvoiceService;
import com.nju.mystore.serviceImpl.calculateCouponStrategy.CalculateStrategy;
import com.nju.mystore.serviceImpl.calculateCouponStrategy.FullReductionStrategy;
import com.nju.mystore.serviceImpl.calculateCouponStrategy.SpecialStrategy;
import com.nju.mystore.util.OssUtil;
import com.nju.mystore.vo.InvoiceVO;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    OssUtil ossUtil;

    @Autowired
    AlipayTools alipayTools;

    @Override
    public Integer createInvoice(InvoiceVO invoiceVO) {
        Invoice newInvoice = invoiceVO.toPO();
        Product product = productRepository.findByProductId(invoiceVO.getInvoiceProductId());
        int num = product.getProductSales() - invoiceVO.getInvoiceProductNum();
        if (num < 0) {
            throw BlueWhaleException.productNotEnough();
        }
        newInvoice.setInvoiceStatus(InvoiceStatusEnum.UNPAID);
        productRepository.updateProductBySales(product.getProductId(), num);
        newInvoice.setInvoiceTime(new Date());
        invoiceRepository.save(newInvoice);
        return newInvoice.getInvoiceId();
    }

    @Override
    public InvoiceVO getInvoiceById(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId);
        if (invoice == null) {
            throw BlueWhaleException.invoiceNotFound();
        }
        return invoice.toVO();
    }

    @Override
    public List<InvoiceVO> getAllByInvoiceUserId(Integer invoiceUserId) {
        List<Invoice> list = invoiceRepository.findAllByInvoiceUserId(invoiceUserId);
        return list.stream().map(Invoice::toVO).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceVO> getAllByInvoiceStoreId(Integer invoiceStoreId) {
        List<Invoice> list = invoiceRepository.findAllByInvoiceStoreId(invoiceStoreId);
        return list.stream().map(Invoice::toVO).collect(Collectors.toList());
    }

    @Override
    public Double getRealPrice(Integer invoiceId, Integer couponId) {
        Coupon coupon = couponRepository.findById(couponId).get();
        if (coupon.getCouponCategory() == CouponEnum.SpecialCoupon) {
            CalculateStrategy strategy = new SpecialStrategy();
            return strategy.calculate(invoiceRepository.findById(invoiceId).get().getInvoicePrice(), coupon);
        } else if (coupon.getCouponCategory() == CouponEnum.FullReduction) {
            CalculateStrategy strategy = new FullReductionStrategy();
            return strategy.calculate(invoiceRepository.findById(invoiceId).get().getInvoicePrice(), coupon);
        }
        return 0.0;
    }

    @Override
    public String payInvoice(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId);
        List<Integer> coupons = invoice.getCoupons();
        Double invoicePrice;
        if (coupons.isEmpty()) {
            invoicePrice = invoice.getInvoicePrice();

        } else {
            Integer couponId = invoice.getCoupons().get(0);
            invoicePrice = getRealPrice(invoiceId, couponId);
            couponRepository.updateCouponStatus(couponId, CouponStatusEnum.USED);
        }
        invoiceRepository.updateInvoiceRealPrice(invoiceId, invoicePrice);
        invoiceRepository.updateInvoicePayTime(invoiceId, new Date());
        invoiceRepository.updateInvoiceRealPrice(invoiceId, invoicePrice);
        invoiceRepository.updateInvoiceStatus(invoiceId, InvoiceStatusEnum.UNSEND);
        return alipayTools.pay(String.valueOf(invoiceId), invoiceRepository.findByInvoiceId(invoiceId).getInvoiceName(), invoicePrice);
    }

    @Override
    public Boolean sendInvoice(Integer invoiceId) {
        invoiceRepository.updateInvoiceStatus(invoiceId, InvoiceStatusEnum.UNGET);
        return true;
    }

    @Override
    public Boolean getInvoice(Integer invoiceId) {
        invoiceRepository.updateInvoiceStatus(invoiceId, InvoiceStatusEnum.UNCOMMENT);
        return true;
    }

    @Override
    public Boolean addComment(Integer invoiceId, Integer score, String comment) {
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId);
        invoiceRepository.updateInvoiceRemarkAndScore(invoiceId, comment, score, new Date());
        invoiceRepository.updateInvoiceStatus(invoiceId, InvoiceStatusEnum.DONE);

        int productId = invoice.getInvoiceProductId();
        Product product = productRepository.findByProductId(productId);

        double productScore = 0.0;
        if (product.getProductScore() != null) {
            productScore = product.getProductScore();
        }

        int commentNumProduct = 0;
        if (product.getCommentNum() != null) {
            commentNumProduct = product.getCommentNum();
        }

        productScore = (productScore * commentNumProduct + score) / (commentNumProduct + 1);
        productRepository.updateProductByComment(productId, productScore, commentNumProduct + 1);

        Store store = storeRepository.findByStoreId(invoice.getInvoiceStoreId());

        double storeScore = 0.0;
        if (store.getStoreScore() != null) {
            storeScore = store.getStoreScore();
        }
        int commentNum = 0;
        if (store.getStoreCommentNum() != null) commentNum = store.getStoreCommentNum();
        storeScore = (storeScore * commentNum + score) / (commentNum + 1);
        storeRepository.updateStoreByComment(store.getStoreId(), storeScore, commentNum + 1);
        return true;
    }

    @Override
    public List<InvoiceVO> getAllInvoices() {
        List<Invoice> list = invoiceRepository.findAll();
        return list.stream().map(Invoice::toVO).collect(Collectors.toList());
    }

    @Override
    public Boolean cancelInvoice(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId);
        int productNum = invoice.getInvoiceProductNum();
        productRepository.updateProductBySales(invoice.getInvoiceProductId(), productNum + productRepository.findByProductId(invoice.getInvoiceProductId()).getProductSales());
        invoiceRepository.deleteInvoice(invoiceId);
        return true;
    }

    @Override
    public String exportInvoices(Integer storeId) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("本店订单报表");

        XSSFRow row0 = sheet.createRow(0);

        row0.createCell(0).setCellValue("订单号");
        row0.createCell(1).setCellValue("商品名称");
        row0.createCell(2).setCellValue("商品数量");
        row0.createCell(3).setCellValue("商品价格");
        row0.createCell(4).setCellValue("实付金额");
        row0.createCell(5).setCellValue("下单时间");
        row0.createCell(6).setCellValue("支付时间");
        row0.createCell(7).setCellValue("订单状态");
        for (Invoice invoice : invoiceRepository.findAllByInvoiceStoreId(storeId)) {
            XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(invoice.getInvoiceId());
            row.createCell(1).setCellValue(productRepository.findByProductId(invoice.getInvoiceProductId()).getProductName());
            row.createCell(2).setCellValue(invoice.getInvoiceProductNum());
            row.createCell(3).setCellValue(invoice.getInvoicePrice());
            Double realPrice = invoice.getInvoiceRealPrice();
            if (realPrice != null) {
                row.createCell(4).setCellValue(realPrice);
            } else {
                row.createCell(4).setCellValue(0.0);
            }

            Date invoiceTime = invoice.getInvoiceTime();
            String invoiceTimeStr = (invoiceTime != null) ? invoiceTime.toString() : "";
            row.createCell(5).setCellValue(invoiceTimeStr);
            Date payTime = invoice.getInvoicePayTime();
            String payTimeStr = (payTime != null) ? payTime.toString() : "";
            row.createCell(6).setCellValue(payTimeStr);
            row.createCell(7).setCellValue(invoice.getInvoiceStatus().toString());
        }

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
            return ossUtil.upload("invoice.xlsx", inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String exportAllInvoices() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("总订单报表");

        XSSFRow row0 = sheet.createRow(0);

        row0.createCell(0).setCellValue("订单号");
        row0.createCell(1).setCellValue("商品名称");
        row0.createCell(2).setCellValue("商品数量");
        row0.createCell(3).setCellValue("商品价格");
        row0.createCell(4).setCellValue("实付金额");
        row0.createCell(5).setCellValue("下单时间");
        row0.createCell(6).setCellValue("支付时间");
        row0.createCell(7).setCellValue("订单状态");
        for (Invoice invoice : invoiceRepository.findAll()) {
            XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(invoice.getInvoiceId());
            row.createCell(1).setCellValue(productRepository.findByProductId(invoice.getInvoiceProductId()).getProductName());
            row.createCell(2).setCellValue(invoice.getInvoiceProductNum());
            row.createCell(3).setCellValue(invoice.getInvoicePrice());
            Double realPrice = invoice.getInvoiceRealPrice();
            if (realPrice != null) {
                row.createCell(4).setCellValue(realPrice);
            } else {
                row.createCell(4).setCellValue(0.0);
            }
            Date invoiceTime = invoice.getInvoiceTime();
            String invoiceTimeStr = (invoiceTime != null) ? invoiceTime.toString() : "";
            row.createCell(5).setCellValue(invoiceTimeStr);
            Date payTime = invoice.getInvoicePayTime();
            String payTimeStr = (payTime != null) ? payTime.toString() : "";
            row.createCell(6).setCellValue(payTimeStr);
            row.createCell(7).setCellValue(invoice.getInvoiceStatus().toString());
        }


        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
            return ossUtil.upload("invoice.xlsx", inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 当前设置15分钟自动删除订单
    // 14天： 14×24×60×60×1000 ms 的时间间隔

    @Override
    @Scheduled(cron = "0 0 0 * * * ")
    public void updateInvoiceDate() {
        Date time = new Date();
        List<Invoice> list = invoiceRepository.findAll();
        for (Invoice invoice : list) {
            if (invoice.getInvoiceStatus() == InvoiceStatusEnum.UNPAID) {
                if (time.getTime() - invoice.getInvoiceTime().getTime() > 14 * 24 * 60 * 60 * 1000) {
                    int productNum = invoice.getInvoiceProductNum();
                    productRepository.updateProductBySales(invoice.getInvoiceProductId(), productNum + productRepository.findByProductId(invoice.getInvoiceProductId()).getProductSales());
                    invoiceRepository.deleteInvoice(invoice.getInvoiceId());
                }
            }
        }
    }

}
