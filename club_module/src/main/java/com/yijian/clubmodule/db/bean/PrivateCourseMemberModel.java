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


}
