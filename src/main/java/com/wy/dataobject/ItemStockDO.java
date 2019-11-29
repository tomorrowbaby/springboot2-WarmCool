package com.wy.dataobject;

public class ItemStockDO {
    private Long id;

    private Long stock;

    private Long itemId;

    public ItemStockDO(Long id, Long stock, Long itemId) {
        this.id = id;
        this.stock = stock;
        this.itemId = itemId;
    }

    public ItemStockDO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}