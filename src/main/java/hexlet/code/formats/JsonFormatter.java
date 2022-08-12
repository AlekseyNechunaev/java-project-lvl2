package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ValueInfo;

import java.util.Map;

public class JsonFormatter {

    public static String diffToJson(Map<String, ValueInfo<Object>> diff) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
