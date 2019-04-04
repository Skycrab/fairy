package com.fairy.api.web.rest.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Created by yihaibo on 2019-04-02.
 *
 * https://github.com/zalando/problem-spring-web/tree/master/problem-spring-web
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807)
 */
@ControllerAdvice
public class ExceptionHandling implements ProblemHandling {

}
