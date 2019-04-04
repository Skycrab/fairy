package com.fairy.api.web.rest.admin.vm;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yihaibo on 2019-04-03.
 * View Model for logger
 */
@ApiModel(description = "日志")
@Data
public class LoggerVm {
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "级别")
    private String level;

    public LoggerVm(Logger logger) {
        this.name = logger.getName();
        this.level = logger.getEffectiveLevel().toString();
    }

    public LoggerVm() {
    }
}
