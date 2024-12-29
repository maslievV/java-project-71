package hexlet.code.formatters;

import hexlet.code.utils.DiffStatuses;
import hexlet.code.utils.DiffKeys;
import static hexlet.code.utils.DiffKeys.KEY;
import static hexlet.code.utils.DiffKeys.STATUS;
import static hexlet.code.utils.DiffKeys.OLD_VALUE;
import static hexlet.code.utils.DiffKeys.NEW_VALUE;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(List<Map<DiffKeys, Object>> diffData) {
        StringBuilder builder = new StringBuilder("{\n");

        for (var map: diffData) {
            var key = map.get(KEY);
            var oldValue = map.get(OLD_VALUE);
            var newValue = map.get(NEW_VALUE);
            var status = (DiffStatuses) map.get(STATUS);

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
