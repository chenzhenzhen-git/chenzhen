package com.bonc.dxbrgrmp.common.utils;

public interface ConstantEnum {
    enum DEVICE_TYPE {
        POWER("1", "电表"),
        WATER("2", "测试环境"),
        GAS("3", "气表"),
        STEAM("4", "蒸汽流量计"),
        COMPRESSED_AIR("5", "压缩空气流量计"),
        INDICATE("6", "指标"),
        COLD_GAUGE("7", "冷量表")
        ;

        DEVICE_TYPE(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public final String id;
        public final String name;

        public final static String enumId = "DEVICE_TYPE";
        public final static String enumName = "表类型";
    }
}
