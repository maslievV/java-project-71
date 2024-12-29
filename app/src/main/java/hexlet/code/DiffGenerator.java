package hexlet.code;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

import static hexlet.code.utils.DiffStatuses.ADDED;
import static hexlet.code.utils.DiffStatuses.UPDATED;
import static hexlet.code.utils.DiffStatuses.UNCHANGED;
import static hexlet.code.utils.DiffStatuses.REMOVED;

import hexlet.code.utils.DiffKeys;
import static hexlet.code.utils.DiffKeys.KEY;
import static hexlet.code.utils.DiffKeys.STATUS;
import static hexlet.code.utils.DiffKeys.OLD_VALUE;
import static hexlet.code.utils.DiffKeys.NEW_VALUE;

public class DiffGenerator {
    public static List<Map<DiffKeys, Object>> findDiff(
            Map<String, Object> jsonMap1,
            Map<String, Object> jsonMap2
    ) {
        List<Map<DiffKeys, Object>> result = new ArrayList<>();
        var allDataKeys = new TreeSet<String>(jsonMap1.keySet());
        allDataKeys.addAll(jsonMap2.keySet());

        for (var key: allDataKeys) {
            Object value1 = jsonMap1.get(key);
            Object value2 = jsonMap2.get(key);
            var diffMap = new LinkedHashMap<DiffKeys, Object>();

            diffMap.put(KEY, key);

            if (!jsonMap1.containsKey(key)) {
                diffMap.put(STATUS, ADDED);
                diffMap.put(NEW_VALUE, value2);
            } else if (!jsonMap2.containsKey(key)) {
                diffMap.put(STATUS, REMOVED);
                diffMap.put(OLD_VALUE, value1);
            } else if (Objects.equals(value1, value2)) {
                diffMap.put(STATUS, UNCHANGED);
                diffMap.put(OLD_VALUE, value1);
            } else {
                diffMap.put(STATUS, UPDATED);
                diffMap.put(OLD_VALUE, value1);
                diffMap.put(NEW_VALUE, value2);
            }
            result.add(diffMap);
        }
        return result;
    }
}
