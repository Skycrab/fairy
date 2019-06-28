package com.fairy.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by yihaibo on 2019-04-03.
 */
public final class JsonUtil {

    private static final ObjectMapper mapper = createObjectMapper();

    public static final MediaType JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static JsonNode toJsonNode(String jsonStr) throws IOException{
        return mapper.readTree(jsonStr);
    }

    public static <T> T decode(String jsonStr, Class<T> valueType) throws IOException{
        return mapper.readValue(jsonStr, valueType);
    }

    public static <T> T decode(String jsonStr, TypeReference valueTypeRef) throws IOException{
        return mapper.readValue(jsonStr, valueTypeRef);
    }

    public static <T> T decode(JsonNode jsonNode, TypeReference valueTypeRef) throws IOException{
        ObjectReader reader = mapper.readerFor(valueTypeRef);
        return reader.readValue(jsonNode);
    }

    public static String encode(Object o) throws IOException {
        return mapper.writeValueAsString(o);
    }

    private JsonUtil() {

    }
}
