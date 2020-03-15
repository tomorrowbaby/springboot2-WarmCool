package com.wy.service.model;

import java.io.Serializable;

public class DictModel implements Serializable {

    private Integer id;

    private String dict;

    private Integer type;


    private String returnResult;

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict == null ? null : dict.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
