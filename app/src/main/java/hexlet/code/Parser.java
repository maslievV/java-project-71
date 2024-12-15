package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filepath) throws IOException {
        var mapper = new ObjectMapper();
        Path normalizedPath = Paths.get(filepath)
                .normalize()
                .toAbsolutePath();
        byte[] arr = Files.readAllBytes(normalizedPath);
        return mapper.readValue(arr, new TypeReference<>() {
        });
    }
}
