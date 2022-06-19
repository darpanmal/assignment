package algo.trigram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
class TrigramAlgorithmTest {

    private TrigramAlgorithm trigramAlgorithm;
    private static final String INPUT_FILE_PATH = "\\src\\test\\resources\\input.txt";
    private static final String OUTPUT_FILE_PATH = "\\src\\test\\resources\\output.txt";

    @BeforeEach
    void setUp() {
        trigramAlgorithm = new TrigramAlgorithm();
        ReflectionTestUtils.setField(trigramAlgorithm, "inputFilePath", INPUT_FILE_PATH);
        ReflectionTestUtils.setField(trigramAlgorithm, "outputFilePath", OUTPUT_FILE_PATH);
    }

    @Test
    void trigramAlgorithm() throws IOException {
        trigramAlgorithm.trigramAlgorithm();
        String fileDir = System.getProperty("user.dir");
        Path path = Paths.get(fileDir.concat(OUTPUT_FILE_PATH));
        String expectedRecord = Files.readString(path);
        Assertions.assertNotNull(expectedRecord);
    }
}