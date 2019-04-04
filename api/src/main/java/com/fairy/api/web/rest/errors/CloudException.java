package com.fairy.api.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yihaibo on 2019-04-03.
 */
public class CloudException extends AbstractThrowableProblem {
    private static final String PARAM = "param";

    public CloudException(String message, Object... params) {
        this(message, toParamMap(params));
    }

    public CloudException(String message, Map<String, Object> paramMap) {
        super(ErrorConstants.DEFAULT_TYPE, Status.BAD_REQUEST.getReasonPhrase(), Status.BAD_REQUEST, message, null, null, paramMap);
    }

    public static Map<String, Object> toParamMap(Object... params) {
        Map<String, Object> paramMap = new HashMap<>();
        if(Objects.nonNull(params) && params.length > 0) {
            for(int i=0; i<params.length; i++) {
                paramMap.put(PARAM + i, params[i]);
            }
        }
        return paramMap;
    }


}
