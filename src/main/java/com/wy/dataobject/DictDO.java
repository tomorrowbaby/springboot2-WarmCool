package com.wy.dataobject;

public class DictDO {
    private Integer id;

    private String dict;

    private Integer type;

    public DictDO(Integer id, String dict, Integer type) {
        this.id = id;
        this.dict = dict;
        this.type = type;
    }

    public DictDO() {
        super();
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