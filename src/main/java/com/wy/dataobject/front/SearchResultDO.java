package com.wy.dataobject.front;

import java.io.Serializable;
import java.util.List;

/**
 * @author Exrickx
 */
public class SearchResultDO implements Serializable{

    private Long recordCount;

    private int totalPages;

    private List<SearchItemDO> itemList;

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItemDO> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItemDO> itemList) {
        this.itemList = itemList;
    }
}
