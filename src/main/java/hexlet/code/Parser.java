package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    private static final String JSON = "json";
    private static final String YAML = "yaml";
    private static final String YML = "yml";

    public static Map<String, Object> parse(String content, String fileExtension) {
        if (content.isEmpty()) {
            throw new RuntimeException("There is no content in the file");
        }
        Map<String, Object> result;
        ObjectMapper objectMapper = createMapper(fileExtension);
        try {
            result = objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static ObjectMapper createMapper(String fileExtension) {
        switch (fileExtension) {
            case JSON -> {
                return new ObjectMapper();
            }
            case YAML, YML -> {
                return new ObjectMapper(new YAMLFactory());
            }
            default -> throw new RuntimeException("incorrect format");
        }
    }
}
