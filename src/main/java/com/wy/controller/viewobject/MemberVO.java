package com.wy.controller.viewobject;

import com.wy.service.model.MemberModel;

import java.util.List;

public class MemberVO {

    private Long memberCount;

    private Long memberRemoveCount;


    public Long getMemberRemoveCount() {
        return memberRemoveCount;
    }

    public void setMemberRemoveCount(Long memberRemoveCount) {
        this.memberRemoveCount = memberRemoveCount;
    }

    public Long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Long memberCount) {
        this.memberCount = memberCount;
    }

}
