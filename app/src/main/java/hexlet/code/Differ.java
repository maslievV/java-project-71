package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        var parsedFile1 = Parser.parse(filepath1);
        var parsedFile2 = Parser.parse(filepath2);

        return findDiff(parsedFile1, parsedFile2);
    }

    public static String findDiff(
            Map<String, Object> jsonMap1,
            Map<String, Object> jsonMap2
    ) {
        var allDataKeys = new TreeSet<String>(jsonMap1.keySet());
        allDataKeys.addAll(jsonMap2.keySet());
        StringBuilder builder = new StringBuilder("{\n");
        for (var key: allDataKeys) {
            Object value1 = jsonMap1.get(key);
            Object value2 = jsonMap2.get(key);

            if (!jsonMap1.containsKey(key)) {
                builder.append(String.format("+ %s: %s\n", key, value2));
            } else if (!jsonMap2.containsKey(key)) {
                builder.append(String.format("- %s: %s\n", key, value1));
            } else if (Objects.equals(value1, value2)) {
                builder.append(String.format("%s: %s\n", key, value1));
            } else {
                builder.append(String.format("- %s: %s\n+ %s: %s\n", key, value1, key, value2));
            }
        }
        builder.append("}");
        return builder.toString();
    }

}
