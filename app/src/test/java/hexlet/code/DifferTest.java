package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

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
        expectedStylish = readFile("output_files/outputStylish.txt");
        expectedJson = readFile("output_files/outputJson.json");
        expectedPlain = readFile("output_files/outputPlain.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testDifferDefault(String extension) throws Exception {
        var fileTest1 = getAbsolutePath("input_files/fileTest1." + extension).toString();
        var fileTest2 = getAbsolutePath("input_files/fileTest2." + extension).toString();
        var actual = Differ.generate(fileTest1, fileTest2);
        assertEquals(expectedStylish, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testDifferStylish(String extension) throws Exception {
        var fileTest1 = getAbsolutePath("input_files/fileTest1." + extension).toString();
        var fileTest2 = getAbsolutePath("input_files/fileTest2." + extension).toString();
        var actual = Differ.generate(fileTest1, fileTest2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testDifferPlain(String extension) throws Exception {
        var fileTest1 = getAbsolutePath("input_files/fileTest1." + extension).toString();
        var fileTest2 = getAbsolutePath("input_files/fileTest2." + extension).toString();
        var actual = Differ.generate(fileTest1, fileTest2, "plain");
        assertEquals(expectedPlain, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testDifferJson(String extension) throws Exception {
        var fileTest1 = getAbsolutePath("input_files/fileTest1." + extension).toString();
        var fileTest2 = getAbsolutePath("input_files/fileTest2." + extension).toString();
        var actual = Differ.generate(fileTest1, fileTest2, "json");
        assertEquals(expectedJson, actual);
    }
}
