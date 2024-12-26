package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> diffData, String format) throws JsonProcessingException {
        return switch (format) {
            case "plain" -> PlainFormatter.format(diffData);
            case "json" -> JsonFormatter.format(diffData);
            case "stylish" -> StylishFormatter.format(diffData);
            default -> throw new IllegalStateException("Unknown type of format: " + format);
        };
    }

}
