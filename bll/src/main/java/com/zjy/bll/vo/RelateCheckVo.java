package com.zjy.bll.vo;

import java.util.ArrayList;
import java.util.List;

public class RelateCheckVo {
    private String id;
    private String name;
    private String relativeId;
    private boolean isCheck;
    private boolean showDetail;
    List<RelateCheckVo> subList;

    public RelateCheckVo() {
        subList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RelateCheckVo> getSubList() {
        return subList;
    }

    public void setSubList(List<RelateCheckVo> subList) {
        this.subList = subList;
    }

    public boolean getShowDetail() {
        return showDetail;
    }

    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }
}
