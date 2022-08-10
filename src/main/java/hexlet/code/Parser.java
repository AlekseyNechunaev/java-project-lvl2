package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String content) {
        return parse(content);
    }

    private static Map<String, Object> parse(String content) {
        Map<String, Object> result = null;
        if (content.startsWith("{")) {
            result = jsonParse(content);
        }
        return result;
    }

    private static Map<String, Object> jsonParse(String content) {
        Map<String, Object> result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
