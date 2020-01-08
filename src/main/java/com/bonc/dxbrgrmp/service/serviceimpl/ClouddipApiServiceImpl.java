package com.bonc.dxbrgrmp.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.bonc.dxbrgrmp.controller.CloudiipApiController;
import com.bonc.dxbrgrmp.service.IClouddipApiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Service
public class ClouddipApiServiceImpl implements IClouddipApiService {
    private Log loger = LogFactory.getLog(CloudiipApiController.class);

    private RestTemplate restTemplate;

    @Autowired
    public ClouddipApiServiceImpl(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    @Value("${cloudiip.apiurl.token}")
    private String cloudiip_token_apiurl;

    @Value("${cloudiip.apiurl.asset_tree}")
    private String asset_tree_apiurl;

    /**
     * 获取token
     * @param userId
     * @param teantId
     * @param pwd
     * @return
     */
    public String getToken(String userId,String teantId,String pwd) throws Exception {
        String u = userId;
        String p = pwd;
        String t=teantId;
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("u", u);
        postParameters.add("p", p);
        postParameters.add("t", t);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(postParameters, headers);
        String token = null;
            ResponseEntity<JSONObject> responseEntity =  restTemplate.exchange(
                    cloudiip_token_apiurl, HttpMethod.POST, entity,
                    JSONObject.class);
            if(responseEntity != null && responseEntity.getBody() != null){
                token = responseEntity.getBody().getString("message");
            }
        return token;
    }

    /**
     * 获取资产树
     * @param token
     * @param parametersMap
     * @return
     * @throws Exception
     */
    public Object queryAssetTree(String token, Map parametersMap) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        String url = asset_tree_apiurl+"?"+analyParameter(parametersMap);
        ResponseEntity<JSONObject> responseEntity =  restTemplate.exchange(
                url, HttpMethod.GET, entity,
                JSONObject.class);

        return responseEntity.getBody();
    }

    private String analyParameter(Map map){
        StringBuilder pa = new StringBuilder();
        map.forEach((key1,value)->{
            if(value == null){
                pa.append(key1).append("=&");
            }else{
                pa.append(key1).append("=").append(value).append("&");
            }
        });
        return pa.toString();
    }
}
