package com.bonc.dxbrgrmp.filter;

//import com.bonc.config.AppConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxiaoyang on 16/12/30.
 */
@Component
public class CORSFilter implements Filter {

   /* @Resource
    private AppConfig appConfig;*/

    private static List<String> defaultOrigins = new ArrayList<>();

    static {
        defaultOrigins = new ArrayList<>();
        defaultOrigins.add("http://localhost:8080");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        List<String> origins = null;
        /*if (!CollectionUtils.isEmpty(appConfig.getAllowOrigins())) {
            origins = appConfig.getAllowOrigins();
        } else {
            origins = defaultOrigins;
        }*/
        origins = defaultOrigins;
        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) res;

        String refer = request.getHeader("Referer");
        String originHeader = request.getHeader("Origin");
        if (StringUtils.isEmpty(refer) && StringUtils.isEmpty(originHeader)) {
            filterChain.doFilter(req, res);
            return;
        }
        URL url = new URL(refer == null ? originHeader : refer);
        StringBuffer origin = new StringBuffer();
        origin.append(url.getProtocol()).append("://").append(url.getHost());
        if (url.getPort() != -1) {
            origin.append(":").append(url.getPort());
        }
        // 遍历允许的origin,判断是否可以放行
        boolean allow = true;
        /*for (String tmpOrigin : origins) {
            if (StringUtils.equals(tmpOrigin, origin.toString())) {
                allow = true;
            }
        }*/
        if (allow) {
            response.setHeader("Access-Control-Allow-Origin", origin.toString());
        } else {
            response.setHeader("Access-Control-Allow-Origin", "http://bonc.com.cn");
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Content-disposition");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
