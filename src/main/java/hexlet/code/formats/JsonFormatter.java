package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.serivces.ValueInfo;

import java.util.Map;

public class JsonFormatter implements Formatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public String toFormat(Map<String, ValueInfo> diff) {
        try {
            return MAPPER.writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
