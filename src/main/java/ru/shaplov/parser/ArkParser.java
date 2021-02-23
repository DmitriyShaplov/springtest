package ru.shaplov.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ArkParser {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Excite\\Desktop\\IZRL.txt";
        List<String> strings = Files.readAllLines(Paths.get(filePath));
        List<String> collect = strings.stream().map(v -> {
                    int index = v.length();
                    for (int i = 0; i < 4; i++) {
                        index = v.lastIndexOf(" ", index - 1);
                    }
                    String substring1 = v.substring(v.indexOf(" ") + 1, index);
                    String substring2 = v.substring(v.lastIndexOf(" ") + 1);
                    return substring1 + ";" + substring2;
                }
        ).collect(Collectors.toList());
        try (BufferedWriter bufferedWriter =
                Files.newBufferedWriter(Paths.get(filePath.replaceAll("\\.", "1.")))) {
            collect.forEach(v -> {
                try {
                    bufferedWriter.write(v);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
