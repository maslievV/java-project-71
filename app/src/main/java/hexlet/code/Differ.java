package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var parsedFile1 = readAndParseFile(filepath1);
        var parsedFile2 = readAndParseFile(filepath2);

        var result = DiffGenerator.findDiff(parsedFile1, parsedFile2);
        return Formatter.format(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
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

    public static Map<String, Object> readAndParseFile(String filepath) throws Exception {
        var normalizedPath = getNormalizedPath(filepath);
        String content = Files.readString(normalizedPath);
        var format = getFormat(filepath);
        return Parser.parse(content, format);
    }
}
