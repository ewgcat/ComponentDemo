package com.yijian.clubmodule.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/30 11:10:46
 */
@Entity
public class PrivateCourseMemberModel {
    /**
     * headPath : string
     * memberId : string
     * memberName : string
     */
    @Id(autoincrement = true)
    private Long idx;
    private String id;
    private String headPath;
    private String memberId;
    private String memberName;
    private Integer memberSex;

    @Generated(hash = 77753146)
    public PrivateCourseMemberModel(Long idx, String id, String headPath,
            String memberId, String memberName, Integer memberSex) {
        this.idx = idx;
        this.id = id;
        this.headPath = headPath;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberSex = memberSex;
    }

    @Generated(hash = 877873440)
    public PrivateCourseMemberModel() {
    }
 
    @Override
    public String toString() {
        return "PrivateCourseMemberModel{" +
                "id='" + id + '\'' +
                ", headPath='" + headPath + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberSex=" + memberSex +
                '}';
    }

    public Long getIdx() {
        return this.idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPath() {
        return this.headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getMemberSex() {
        return this.memberSex;
    }

    public void setMemberSex(Integer memberSex) {
        this.memberSex = memberSex;
    }
}
