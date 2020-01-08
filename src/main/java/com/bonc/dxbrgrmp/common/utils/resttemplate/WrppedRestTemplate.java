package com.bonc.dxbrgrmp.common.utils.resttemplate;

import com.bonc.dxbrgrmp.interceptor.WrppedHttpRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

public class WrppedRestTemplate implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(new WrppedHttpRequestInterceptor());
    }
}
