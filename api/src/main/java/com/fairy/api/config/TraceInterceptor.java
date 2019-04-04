package com.fairy.api.config;

import com.fairy.api.util.MdcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yihaibo on 2019-04-02.
 */
@Slf4j
public class TraceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MdcUtil.start();
        response.setHeader(MdcUtil.TRACE_ID, MdcUtil.getTraceId());
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MdcUtil.clear();
    }
}
