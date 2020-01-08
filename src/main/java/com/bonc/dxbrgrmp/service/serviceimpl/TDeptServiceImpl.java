package com.bonc.dxbrgrmp.service;

import com.bonc.dxbrgrmp.pojo.po.TWeatherCityCode;
import com.bonc.dxbrgrmp.response.ResultModel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.bonc.dxbrgrmp.pojo.po.TDept;
import com.bonc.dxbrgrmp.dao.mybatis.mapper.TDeptMapper;
import com.bonc.dxbrgrmp.service.serviceimpl.TDeptService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class TDeptServiceImpl implements TDeptService{

    @Resource
    private TDeptMapper tDeptMapper;

    @Override
    public String selectTempTree(){
         List<TDept> tDeptList=tDeptMapper.selectTempTree();
         Map<Integer, String> treeMap = new HashMap<Integer, String>();
         for(TDept tDept : tDeptList){
               Integer tempId=tDept.getDeptId();
               String TreeInfo=tDept.getDeptTree();
               treeMap.put(tempId,TreeInfo);
         }
         for(Integer key:treeMap.keySet()){
            String value=treeMap.get(key);
            Map<String, Object> tree= new HashMap<String,Object>();
            tree.put("deptId",key);
            tree.put("deptTree",value);
            tDeptMapper.updateTreeInfo(tree);
         }
         return "SUCCESS";
    }




}
