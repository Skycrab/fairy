package com.fairy.api.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Created by yihaibo on 2019-04-03.
 */
public final class ResponseUtil {
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> response) {
        return wrapOrNotFound(response, null);
    }

    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> response, HttpHeaders headers) {
        return response.map(res -> ResponseEntity.ok().headers(headers).body(res))
                .orElse(ResponseEntity.notFound().build());
    }

    private ResponseUtil() {
    }
}
