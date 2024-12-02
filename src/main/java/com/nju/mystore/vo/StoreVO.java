package com.nju.mystore.vo;


import com.nju.mystore.po.Store;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StoreVO {

    private int storeId;

    private String storeName;

    private String storeDescription;

    private String storeImageUrl;

    private String storeAddress;

    private Double storeScore;

    private Integer storeCommentNum;

    public Store toPO() {
        Store store = new Store();
        store.setStoreId(this.storeId);
        store.setStoreName(this.storeName);
        store.setStoreDescription(this.storeDescription);
        store.setStoreImageUrl(this.storeImageUrl);
        store.setStoreAddress(this.storeAddress);
        store.setStoreScore(this.storeScore);
        store.setStoreCommentNum(this.storeCommentNum);
        return store;
    }
}
