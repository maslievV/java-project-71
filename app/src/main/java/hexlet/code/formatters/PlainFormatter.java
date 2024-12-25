package hexlet.code.formatters;

import static hexlet.code.DiffStatuses.UPDATED;
import static hexlet.code.DiffStatuses.ADDED;
import static hexlet.code.DiffStatuses.REMOVED;
import static hexlet.code.DiffStatuses.UNCHANGED;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder builder = new StringBuilder();

        for (var entry: diffMap.entrySet()) {
            String key = entry.getKey();
            var status = entry.getValue().getFirst();
            var value1 = entry.getValue().get(1);
            var value2 = entry.getValue().size() > 2 ? entry.getValue().get(2) : null;

            var content = switch (status) {
                case UPDATED -> String.format("Property '%s' was updated. From %s to %s\n",
                        key, isComplexValue(value1), isComplexValue(value2));
                case REMOVED -> String.format("Property '%s' was removed\n", key);
                case ADDED -> String.format("Property '%s' was added with value: %s\n",
                        key, isComplexValue(value1));
                case UNCHANGED -> "";
                default -> throw new IllegalStateException("Unexpected status: " + status);
            };

            builder.append(content);
        }
        return builder.toString().trim();
    }

    public static String isComplexValue(Object value) {

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else if (value instanceof String) {
            return String.format("'%s'", value);
        } else if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }
}
