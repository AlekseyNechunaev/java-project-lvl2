package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.serivces.ValueInfo;

import java.util.Map;

public class JsonFormatter {

    public static String diffToJson(Map<String, ValueInfo> diff) {
        try {
            return new ObjectMapper().writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
