package com.fairy.api.web.rest.admin;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.fairy.api.web.rest.admin.vm.LoggerVm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yihaibo on 2019-04-03.
 */
@Api(description="日志管理")
@RestController
@RequestMapping("/admin")
public class LogController {
    @ApiOperation(value = "日志列表")
    @GetMapping("/logs")
    public List<LoggerVm> getLoggers() {
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        return context.getLoggerList().stream().map(LoggerVm::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "修改日志级别")
    @PutMapping("/logs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeLevel(@RequestBody LoggerVm loggerVm) {
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        context.getLogger(loggerVm.getName()).setLevel(Level.valueOf(loggerVm.getLevel()));
    }
}
