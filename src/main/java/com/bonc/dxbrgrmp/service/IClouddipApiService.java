package com.bonc.dxbrgrmp.service;

import java.util.Map;

public interface IClouddipApiService {
    public String getToken(String userId,String teantId,String pwd) throws Exception;
    public Object queryAssetTree(String token, Map map) throws Exception;
}
