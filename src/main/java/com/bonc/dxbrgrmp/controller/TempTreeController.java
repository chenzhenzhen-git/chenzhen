package com.bonc.dxbrgrmp.controller;

import com.bonc.dxbrgrmp.response.ResultModel;
import com.bonc.dxbrgrmp.service.IWeatherService;
import com.bonc.dxbrgrmp.service.serviceimpl.TDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TempTreeController {
    @Resource
    TDeptService tDeptServiceImpl;

    //更新数据库中Temp_Tree字段的值
    @RequestMapping("/TreeInfo")
    @ResponseBody
    public ResultModel getTreeInfo(){
        return ResultModel.successModel( tDeptServiceImpl.selectTempTree());
    }

}
