package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, List<Object>> diffMap, String format) throws JsonProcessingException {
        return switch (format) {
            case "plain" -> PlainFormatter.format(diffMap);
            case "json" -> JsonFormatter.format(diffMap);
            case "stylish" -> StylishFormatter.format(diffMap);
            default -> throw new IllegalStateException("Unknown type of format: " + format);
        };
    }

}
