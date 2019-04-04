package com.fairy.api.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by yihaibo on 2019-04-02.
 */
@Data
@ApiModel(description = "应用程序信息")
public class ApplicationDto {

    @ApiModelProperty(value = "当前profile")
    private String env;
}
