package hexlet.code.formatters;

import static hexlet.code.DiffStatuses.UPDATED;
import static hexlet.code.DiffStatuses.ADDED;
import static hexlet.code.DiffStatuses.UNCHANGED;
import static hexlet.code.DiffStatuses.REMOVED;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder builder = new StringBuilder("{\n");

        for (var entry: diffMap.entrySet()) {
            String key = entry.getKey();
            var value1 = diffMap.get(key).get(1);
            var status = diffMap.get(key).getFirst();

            var content = switch (status) {
                case ADDED -> String.format("  + %s: %s\n", key, value1);
                case REMOVED -> String.format("  - %s: %s\n", key, value1);
                case UNCHANGED -> String.format("    %s: %s\n", key, value1);
                case UPDATED -> String.format("  - %s: %s\n  + %s: %s\n",
                        key, value1, key, diffMap.get(key).get(2));
                default -> throw new IllegalStateException("Unexpected format: " + status);
            };
            builder.append(content);

        }
        return builder.append("}").toString();
    }
}
