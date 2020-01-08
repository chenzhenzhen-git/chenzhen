package com.bonc.dxbrgrmp.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * 实时数据表
 */
@Data
public class RealtimeValue {

    private Long dataId;//主键ID自增
    private Double dataValue;//值
    private Date dataTime;//时间
    private Double energyCost;//费用
    private Double minusValue;//差值
    private Integer rtcFlowId;//流式计算ID
    private Integer pointId;//测点ID
    private Integer deviceId;//设备ID

    private String dealTS;
    private Double accumValue;

}





