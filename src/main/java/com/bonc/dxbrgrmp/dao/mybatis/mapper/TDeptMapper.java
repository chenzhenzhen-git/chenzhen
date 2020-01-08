package com.bonc.dxbrgrmp.dao.mybatis.mapper;

import com.bonc.dxbrgrmp.pojo.po.TDept;

import java.util.List;
import java.util.Map;

public interface TDeptMapper {

    //查询TempTree这一列应该填入的数据和dept_id
    List<TDept> selectTempTree();

   //更新dept_tree这一列的数据-->
    int updateTreeInfo(Map<String, Object> tree);

}