package hexlet.code.formatters;

import hexlet.code.DiffStatuses;

import static hexlet.code.DiffStatuses.UPDATED;
import static hexlet.code.DiffStatuses.ADDED;
import static hexlet.code.DiffStatuses.REMOVED;
import static hexlet.code.DiffStatuses.UNCHANGED;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<String, Object>> diffData) {
        StringBuilder builder = new StringBuilder();

        for (var map: diffData) {
            var key = map.get("key");
            var status = (DiffStatuses) map.get("status");
            var oldValue = map.get("oldValue");
            var newValue = map.get("newValue");

            var content = switch (status) {
                case UPDATED -> String.format("Property '%s' was updated. From %s to %s\n",
                        key, formatValue(oldValue), formatValue(newValue));
                case REMOVED -> String.format("Property '%s' was removed\n", key);
                case ADDED -> String.format("Property '%s' was added with value: %s\n",
                        key, formatValue(newValue));
                case UNCHANGED -> "";
            };

            builder.append(content);
        }
        return builder.toString().trim();
    }

    public static String formatValue(Object value) {

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
