package com.fairy.api.util;

import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;

/**
 * Created by yihaibo on 2019-04-02.
 */
public class MdcUtil {
    public static final String TRACE_ID = "Trace-Id";
    public static void start() {
        String traceId = uuid();
        MDC.put(TRACE_ID,traceId);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void clear() {
        MDC.clear();
    }

    public static String getTraceId(){
        return MDC.get(TRACE_ID);
    }

    public static void setContextMap(Map<String,String> contextMap){
        MDC.setContextMap(contextMap);
    }

    public static Map<String,String> getCurrentContextMap(){
        return MDC.getCopyOfContextMap();
    }
}
