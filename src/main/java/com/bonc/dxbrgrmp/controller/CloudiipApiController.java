package com.bonc.dxbrgrmp.controller;

import com.bonc.dxbrgrmp.response.ResultModel;
import com.bonc.dxbrgrmp.service.IClouddipApiService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CloudiipApiController {

    @Resource
    IClouddipApiService clouddipApiService;

    @PostMapping("/queryAssetTree")
    public Object queryAssetTree(@RequestBody Map parametersMap){
        if(parametersMap.isEmpty() || parametersMap.get("parentId") == null){
            parametersMap = new HashMap();
            parametersMap.put("parentId",null);
        }
        String u = "beiranadmin";
        String p = "123456";
        String t="brrl";
        try{
            String token = clouddipApiService.getToken(u,t,p);
            if(token == null)
                return ResultModel.failModel("获取授权失败");
            return ResultModel.successModel(clouddipApiService.queryAssetTree(token,parametersMap));
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResultModel.failModel("获取资产树失败");
        }
    }
}
