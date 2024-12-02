package com.nju.mystore.po;


import com.nju.mystore.vo.StoreVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Store {
    /**
     * 商店的id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private int storeId;

    /**
     * 商店的名字
     */
    @Basic
    @Column(name = "store_name")
    private String storeName;


    /**
     * 商店的描述
     */
    @Basic
    @Column(name = "store_description")
    private String storeDescription;

    /**
     * 图片url
     */
    @Basic
    @Column(name = "image_url")
    private String storeImageUrl;


    /**
     * 商店地址
     */
    @Basic
    @Column(name = "store_address")
    private String storeAddress;


    /**
     * 商店商品的评分
     */
    @Basic
    @Column(name = "store_score")
    private Double storeScore;

    /**
     * 商店评论数
     */
    @Basic
    @Column(name = "store_comment_num")
    private Integer storeCommentNum;

    public StoreVO toVO() {
        StoreVO storeVO = new StoreVO();
        storeVO.setStoreId(this.storeId);
        storeVO.setStoreName(this.storeName);
        storeVO.setStoreDescription(this.storeDescription);
        storeVO.setStoreImageUrl(this.storeImageUrl);
        storeVO.setStoreAddress(this.storeAddress);
        storeVO.setStoreScore(this.storeScore);
        storeVO.setStoreCommentNum(this.storeCommentNum);
        return storeVO;
    }
}
