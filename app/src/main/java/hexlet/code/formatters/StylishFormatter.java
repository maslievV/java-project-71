package hexlet.code.formatters;

import hexlet.code.DiffStatuses;

import static hexlet.code.DiffStatuses.UPDATED;
import static hexlet.code.DiffStatuses.ADDED;
import static hexlet.code.DiffStatuses.UNCHANGED;
import static hexlet.code.DiffStatuses.REMOVED;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> diffData) {
        StringBuilder builder = new StringBuilder("{\n");

        for (var map: diffData) {
            var key = map.get("key");
            var oldValue = map.get("oldValue");
            var newValue = map.get("newValue");
            var status = (DiffStatuses) map.get("status");

            var content = switch (status) {
                case ADDED -> String.format("  + %s: %s\n", key, newValue);
                case REMOVED -> String.format("  - %s: %s\n", key, oldValue);
                case UNCHANGED -> String.format("    %s: %s\n", key, oldValue);
                case UPDATED -> String.format("  - %s: %s\n  + %s: %s\n",
                        key, oldValue, key, newValue);
            };
            builder.append(content);
        }
        return builder.append("}").toString();
    }
}
