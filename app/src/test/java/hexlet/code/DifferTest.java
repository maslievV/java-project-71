package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private static String testJson1;
    private static String testJson2;
    private static String testYaml1;
    private static String testYaml2;

    public static Path getAbsolutePath(String fileName) {
        return Paths.get("src/test/resources/", fileName)
                .toAbsolutePath()
                .normalize();
    }

    public static String readFile(String fileName) throws Exception {
        var fullPath = getAbsolutePath(fileName);
        return Files.readString(fullPath).trim();
    }

    @BeforeAll
    public static void setup() throws Exception {
        testJson1 = getAbsolutePath("input_files/jsonTest1.json").toString();
        testJson2 = getAbsolutePath("input_files/jsonTest2.json").toString();
        testYaml1 = getAbsolutePath("input_files/yamlTest1.yaml").toString();
        testYaml2 = getAbsolutePath("input_files/yamlTest2.yaml").toString();
    }

    @Test
    public void testDifferStylish() throws Exception {
        var actual = Differ.generate(testJson1, testJson2);
        var expected = readFile("output_files/outputStylish.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferPlain() throws Exception {
        var actual = Differ.generate(testJson1, testJson2, "plain");
        var expected = readFile("output_files/outputPlain.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferJson() throws Exception {
        var actual = Differ.generate(testJson1, testJson2, "json");
        var expected = readFile("output_files/outputJson.json");
        assertEquals(expected, actual);
    }
}
