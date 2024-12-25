package hexlet.code;


import static hexlet.code.DiffStatuses.REMOVED;
import static hexlet.code.DiffStatuses.UPDATED;
import static hexlet.code.DiffStatuses.ADDED;
import static hexlet.code.DiffStatuses.UNCHANGED;


import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var parsedFile1 = Parser.parse(filepath1);
        var parsedFile2 = Parser.parse(filepath2);

        var result = findDiff(parsedFile1, parsedFile2);
        return Formatter.format(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static Map<String, List<Object>> findDiff(
            Map<String, Object> jsonMap1,
            Map<String, Object> jsonMap2
    ) {
        Map<String, List<Object>> diffMap = new LinkedHashMap<>();
        var allDataKeys = new TreeSet<String>(jsonMap1.keySet());
        allDataKeys.addAll(jsonMap2.keySet());

        for (var key: allDataKeys) {
            Object value1 = jsonMap1.get(key);
            Object value2 = jsonMap2.get(key);

            if (!jsonMap1.containsKey(key)) {
                diffMap.putIfAbsent(key, new ArrayList<>(Arrays.asList(ADDED, value2)));
            } else if (!jsonMap2.containsKey(key)) {
                diffMap.putIfAbsent(key, new ArrayList<>(Arrays.asList(REMOVED, value1)));
            } else if (Objects.equals(value1, value2)) {
                diffMap.putIfAbsent(key, new ArrayList<>(Arrays.asList(UNCHANGED, value1)));
            } else {
                diffMap.putIfAbsent(key, new ArrayList<>(Arrays.asList(UPDATED, value1, value2)));
            }
        }
        return diffMap;
    }

}
