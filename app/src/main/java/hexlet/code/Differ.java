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
        StringBuilder builder = new StringBuilder();
        for (var key: allDataKeys) {
            Object value1 = jsonMap1.get(key);
            Object value2 = jsonMap2.get(key);

            if (!jsonMap1.containsKey(key)) {
                builder.append("+ ")
                        .append(key)
                        .append(": ")
                        .append(value2)
                        .append("\n");
                //diffData.put("+ " + key, value2);
            } else if (!jsonMap2.containsKey(key)) {
                builder.append("- ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                //diffData.put("- " + key, value1);
            } else if (Objects.equals(value1, value2)) {
                //diffData.put(" " + key, value1);
                builder.append(" ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
            } else {
//                diffData.put("- " + key, value1);
//                diffData.put("+ " + key, value2);
                builder.append("- ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n")
                        .append("+ ")
                        .append(key)
                        .append(": ")
                        .append(value2)
                        .append("\n");
            }
        }
        return builder.toString();
    }

}
