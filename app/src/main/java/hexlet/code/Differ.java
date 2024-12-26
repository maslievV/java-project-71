package hexlet.code;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var parsedFile1 = Parser.readFile(filepath1);
        var parsedFile2 = Parser.readFile(filepath2);

        var result = DiffGenerator.findDiff(parsedFile1, parsedFile2);
        return Formatter.format(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
