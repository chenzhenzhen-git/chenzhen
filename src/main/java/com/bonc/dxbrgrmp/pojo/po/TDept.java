package com.bonc.dxbrgrmp.pojo.po;


import org.springframework.data.annotation.Id;

import java.util.List;

public class TDept {

    /**
    * 单元ID
    */

    private Integer deptId;

    /**
    * 单元名称
    */
    private String deptName;

    /**
    * 单元简称
    */
    private String deptShortName;

    /**
    * 上级单元ID
    */
    private Integer parentDeptId;

    /**
    * 附属ID
    */
    private String oDeptId;

    /**
    * 单元类型
单元类型
1：分公司
2：热源厂
3：换热站下属分类分类
31:单系统
32:双系统
33:三系统

    */
    private Byte deptType;

    /**
    * 单元编码
    */
    private String deptNo;

    /**
    * 区域ID
    */
    private Integer regionId;

    /**
    * 单元树
    */
    private String deptTree;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public Integer getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getoDeptId() {
        return oDeptId;
    }

    public void setoDeptId(String oDeptId) {
        this.oDeptId = oDeptId;
    }

    public Byte getDeptType() {
        return deptType;
    }

    public void setDeptType(Byte deptType) {
        this.deptType = deptType;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getDeptTree() {
        return deptTree;
    }

    public void setDeptTree(String deptTree) {
        this.deptTree = deptTree;
    }
}