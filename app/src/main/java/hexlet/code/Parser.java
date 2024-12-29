package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws IOException {
        ObjectMapper mapper = switch (format) {
            case ".json" -> new ObjectMapper();
            case ".yml", ".yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new RuntimeException("Unexpected extension: ");
        };
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
