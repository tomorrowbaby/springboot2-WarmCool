package com.wy.dataobject;

public class ItemSalesDO {
    private Long id;

    private Long limitNum;

    private Long sales;

    private Long itemId;

    public ItemSalesDO(Long id, Long limitNum, Long sales, Long itemId) {
        this.id = id;
        this.limitNum = limitNum;
        this.sales = sales;
        this.itemId = itemId;
    }

    public ItemSalesDO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Long limitNum) {
        this.limitNum = limitNum;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}