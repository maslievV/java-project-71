package hexlet.code;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

import static hexlet.code.DiffStatuses.ADDED;
import static hexlet.code.DiffStatuses.UPDATED;
import static hexlet.code.DiffStatuses.UNCHANGED;
import static hexlet.code.DiffStatuses.REMOVED;

public class DiffGenerator {
    public static List<Map<String, Object>> findDiff(
            Map<String, Object> jsonMap1,
            Map<String, Object> jsonMap2
    ) {
        List<Map<String, Object>> result = new ArrayList<>();
        var allDataKeys = new TreeSet<String>(jsonMap1.keySet());
        allDataKeys.addAll(jsonMap2.keySet());

        for (var key: allDataKeys) {
            Object value1 = jsonMap1.get(key);
            Object value2 = jsonMap2.get(key);
            var diffMap = new LinkedHashMap<String, Object>();

            if (!jsonMap1.containsKey(key)) {
                diffMap.put("key", key);
                diffMap.put("status", ADDED);
                diffMap.put("newValue", value2);
            } else if (!jsonMap2.containsKey(key)) {
                diffMap.put("key", key);
                diffMap.put("status", REMOVED);
                diffMap.put("oldValue", value1);
            } else if (Objects.equals(value1, value2)) {
                diffMap.put("key", key);
                diffMap.put("status", UNCHANGED);
                diffMap.put("oldValue", value1);
            } else {
                diffMap.put("key", key);
                diffMap.put("status", UPDATED);
                diffMap.put("oldValue", value1);
                diffMap.put("newValue", value2);
            }
            result.add(diffMap);
        }
        return result;
    }
}
