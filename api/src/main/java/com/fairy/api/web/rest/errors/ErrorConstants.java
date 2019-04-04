package com.fairy.api.web.rest.errors;

import java.net.URI;

/**
 * Created by yihaibo on 2019-04-03.
 */
public final class ErrorConstants {
    public static final String PROBLEM_URL = "https://github.com/zalando/problem-spring-web/tree/master/problem-spring-web";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_URL);

    private ErrorConstants() {
    }
}
