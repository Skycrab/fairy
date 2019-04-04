package com.fairy.api.web.rest;

import com.fairy.api.config.ProfileConfig;
import com.fairy.api.service.dto.ApplicationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yihaibo on 2019-04-01.
 */
@Api(description="健康检查")
@RestController
@Slf4j
public class HealthCheckController {
    private final ProfileConfig profileConfig;

    public HealthCheckController(ProfileConfig profileConfig) {
        this.profileConfig = profileConfig;

    }

    @ApiOperation(value = "获取健康检查状态")
    @GetMapping("/healthcheck")
    public ResponseEntity<ApplicationDto> getHealthCheck() {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setEnv(profileConfig.getProfileActive());

        return ResponseEntity.ok().body(applicationDto);
    }
}
