package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws IOException {
        ObjectMapper mapper = switch (format) {
            case ".json" -> new ObjectMapper();
            case ".yml", ".yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IOException("Unexpected extension: ");
        };
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    public static Path getNormalizedPath(String filepath) {
        return Paths.get(filepath)
                .toAbsolutePath()
                .normalize();
    }

    public static String getFormat(String filepath) {
        int lastIndex = filepath.lastIndexOf(".");
        return filepath.substring(lastIndex);
    }

    public static Map<String, Object> readFile(String filepath) throws Exception {
        var normalizedPath = getNormalizedPath(filepath);
        String content = Files.readString(normalizedPath);
        var format = getFormat(filepath);
        return parse(content, format);
    }
}
