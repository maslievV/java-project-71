package hexlet.code.formatters;

import hexlet.code.utils.DiffStatuses;
import hexlet.code.utils.DiffKeys;
import static hexlet.code.utils.DiffKeys.KEY;
import static hexlet.code.utils.DiffKeys.STATUS;
import static hexlet.code.utils.DiffKeys.OLD_VALUE;
import static hexlet.code.utils.DiffKeys.NEW_VALUE;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<DiffKeys, Object>> diffData) {
        StringBuilder builder = new StringBuilder();

        for (var map: diffData) {
            var key = map.get(KEY);
            var status = (DiffStatuses) map.get(STATUS);
            var oldValue = map.get(OLD_VALUE);
            var newValue = map.get(NEW_VALUE);

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
