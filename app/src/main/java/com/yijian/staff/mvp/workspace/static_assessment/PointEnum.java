package com.yijian.staff.mvp.workspace.static_assessment;


public enum PointEnum {

    POINT_EAR_LEFT(0,"左耳朵"),
    POINT_EAR_RIGHT(1,"右耳朵"),
    POINT_JIAN_LEFT(2,"左肩"),
    POINT_JIAN_RIGHT(3,"右肩"),
    POINT_NAVEL(4,"肚脐眼"),
    POINT_HIPBONE_LEFT(5,"左胯骨"),
    POINT_HIPBONE_RIGHT(6,"右胯骨"),
    POINT_KNEE_LEFT(8,"左膝盖"),
    POINT_KNEE_RIGHT(9,"右膝盖"),
    POINT_ANKLE_LEFT(10,"左脚踝"),
    POINT_ANKLE_RIGHT(11,"右脚踝"),
    POINT_TIPTOE_LEFT(12,"左脚尖"),
    POINT_TIPTOE_RIGHT(13,"右脚尖"),
    POINT_EAR(14,"耳朵"),
    POINT_GONGGU(15,"肱骨头"),
    POINT_JIAN(16,"肩胛骨"),
    POINT_HIPBONE(17,"胯骨"),
    POINT_KNEE(18,"膝盖");

    private final int value;
    private final String name;

    PointEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }



}
