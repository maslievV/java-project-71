package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private static String testDiffer1;
    private static String testDiffer2;

    public static Path getAbsolutePath(String fileName) {
        return Paths.get("src/test/resources/", fileName)
                .toAbsolutePath()
                .normalize();
    }

    public static String readFile(String fileName) throws Exception{
        var fullPath = getAbsolutePath(fileName);
        return Files.readString(fullPath).trim();
    }

    @BeforeAll
    public static void setup() throws Exception{
        testDiffer1 = getAbsolutePath("input_files/jsonTest1.json").toString();
        testDiffer2 = getAbsolutePath("input_files/jsonTest2.json").toString();
    }

    @Test
    public void testDifferGenerate() throws Exception{
        var actual = Differ.generate(testDiffer1, testDiffer2);
        var expected = readFile("output_files/outputJson.text");
        assertEquals(expected, actual);
    }
}
