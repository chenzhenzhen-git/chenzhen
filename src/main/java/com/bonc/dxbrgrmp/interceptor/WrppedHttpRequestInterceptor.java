package com.bonc.dxbrgrmp.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class WrppedHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    Log logger = LogFactory.getLog(WrppedHttpRequestInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logRequestDetails(httpRequest);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    private void logRequestDetails(HttpRequest request) {
        logger.info("Headers: "+request.getHeaders());
        logger.info("Request Method: "+ request.getMethod());
        logger.info("Request URI: "+ request.getURI());
    }
}
