package com.fairy.api.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yihaibo on 2019-04-28.
 */
@Slf4j
public final class ShellUtil {
    public static int execute(String cmd) throws Exception {
        String[] command = {"/bin/sh", "-c", cmd};
        log.info("execute:{}", cmd);
        Process process = Runtime.getRuntime().exec(command);
        new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(log::info);
        new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().forEach(log::info);
        return process.waitFor();
    }

    private ShellUtil() {
    }
}
