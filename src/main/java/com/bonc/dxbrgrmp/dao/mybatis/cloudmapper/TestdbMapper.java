package com.bonc.dxbrgrmp.dao.mybatis.cloudmapper;

import com.bonc.dxbrgrmp.pojo.po.Testdb;

import java.util.List;

/**
 * @Auther: lgf
 * @Date: 2019/12/23
 * @Description: com.bonc.dxbrgrmp.dao.mybatis.mapper1
 * @version: 1.0
 */
public interface TestdbMapper {

    List<Testdb> selectDbAll();
}
