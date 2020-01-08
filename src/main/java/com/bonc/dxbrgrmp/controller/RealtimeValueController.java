package com.bonc.dxbrgrmp.controller;

import com.bonc.dxbrgrmp.service.IRealtimeValueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RealtimeValueController {
    @Resource
    IRealtimeValueService realtimeValueService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping("/getfromdb")
    public Object getRealtime(){
        return realtimeValueService.getNoneDateTimes();
    }
}
