package com.example.skuniv.fleamarket2.model.AdminSellerModel;

import java.util.List;

public class ApplyListModel {
    List<ApplyModel> applyList;
    int applyCount = 200;

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    int page = 1;

    public List<ApplyModel> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<ApplyModel> applyList) {
        this.applyList = applyList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}