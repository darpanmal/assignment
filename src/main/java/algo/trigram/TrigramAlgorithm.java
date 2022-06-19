package algo.trigram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * This class is used to Generate trigram keySet words and write to output file.
 */
@Component
public class TrigramAlgorithm {
    @Value(("${input.file.path}"))
    private String inputFilePath;
    @Value(("${output.file.path}"))
    private String outputFilePath;

    private static final int ADJACENT_WORDS_COUNT = 2;


    /**
     * This method is used to create trigram algorithms.
     *
     * @throws IOException
     */
    public void trigramAlgorithm() throws IOException {
        String inputFileLocation = System.getProperty("user.dir") + inputFilePath;
        String outputFileLocation = System.getProperty("user.dir") + outputFilePath;
        String fileInputData = fileReader(inputFileLocation);
        Map<String, List<String>> result = createWords(fileInputData, ADJACENT_WORDS_COUNT);
        List<String> records = new ArrayList<>();
        result.entrySet().forEach(entry -> {
            records.add(entry.getKey() + " -> " + entry.getValue());
        });
        fileWriter(records, outputFileLocation);
    }

    /**
     * This method is used generate trigram keySet words.
     *
     * @param fileText
     * @param adjacentWords
     * @return
     */
    private Map<String, List<String>> createWords(String fileText, int adjacentWords) {
        Map<String, List<String>> trigramMap = new HashMap<>();
        String[] words = formatText(fileText).split(" ");
        IntStream.range(0, words.length - adjacentWords)
                .forEach(i -> {
                            StringBuffer stringBuffer = new StringBuffer(words[i].trim());
                            IntStream.range(i + 1, i + adjacentWords)
                                    .forEach(j -> {
                                        stringBuffer.append(' ').append(words[j].trim());
                                    });
                            String key = stringBuffer.toString();
                            List<String> value = trigramMap.get(key);
                            if (value == null) {
                                value = new ArrayList<>();
                                trigramMap.put(key, value);
                            }
                            value.add(words[i + adjacentWords]);
                        }
                );
        return trigramMap;
    }

    /**
     * This method is used to read data from file.
     *
     * @param
     * @return
     */
    private String fileReader(String inputFilePath)
            throws IOException {
        StringBuffer resultStringBuffer = new StringBuffer();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(new File(inputFilePath.strip())))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuffer.append(line.strip()).append(" ");
            }
        }
        return resultStringBuffer.toString().strip();
    }

    /**
     * This method is used to write generated keySet trigram words.
     *
     * @param keySetWords
     * @param filePath
     * @throws IOException
     */
    private void fileWriter(final List<String> keySetWords, final String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, keySetWords, StandardCharsets.UTF_8);
    }

    /**
     * This method is used to format file.
     *
     * @param text
     * @return
     */
    private String formatText(final String text) {
        return text.replaceAll("\\n", " ")
                .replaceAll("[^\\w\\s\']", " ")
                .replaceAll("([ ]+)", " ");
    }

}
