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
    public void testDifferGenerate() throws Exception {
        var actual1 = Differ.generate(testJson1, testJson2);
        var expected1 = readFile("output_files/output.txt");
        assertEquals(expected1, actual1);

        var actual2 = Differ.generate(testYaml1, testYaml2);
        var expected2 = readFile("output_files/output.txt");
        assertEquals(expected2, actual2);
    }
}
