package com.yijian.workspace.static_assessment;


public enum PointEnum {

    /**
     * 正面
     */
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
    /**
     * 侧面
     */
    POINT_EAR(14,"耳朵"),
    POINT_GONGGU(15,"肱骨头"),
    POINT_JIAN(16,"肩胛骨"),
    POINT_HIPBONE(17,"胯骨"),
    POINT_KNEE(18,"膝盖"),

    /**
     * 盆骨
     */
    POINT_SCIATICBONE_LEFT(19,"左坐骨(左点)"),
    POINT_SCIATICBONE_RIGHT(20,"右坐骨(右点)"),
    POINT_PUBICBONE(21,"耻骨联合(前点)"),
    POINT_CAUDABONE(22,"尾骨(后点)");

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
