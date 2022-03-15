package com.xchange.demobackend.infastructure.dto;

public class ProductsWthQty {
    private long productId;

    private Integer qty;

    ProductsWthQty(long productId, Integer qty){
        this.productId = productId;
        this.qty = qty;
    }

    public long getProductId() {
        return productId;
    }

    public Integer getQty() {
        return qty;
    }
}

