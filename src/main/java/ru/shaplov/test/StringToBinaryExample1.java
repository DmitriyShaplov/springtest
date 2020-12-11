package ru.shaplov.test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

/**
 * @author dshaplov
 */
public class StringToBinaryExample1 {

    public static void main(String[] args) {

        HashCode qq123456 = Hashing.sha256().hashString("Qq123456", StandardCharsets.UTF_8);
        Hashing.sha256().bits();
        System.out.println(qq123456);
//        String input = "шиза";
//        String result = convertStringToBinary(input);
//
//        System.out.println(result);
//
//        // pretty print the binary format
//        System.out.println(prettyBinary(result, 8, " "));

    }

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        byte[] bytes = input.getBytes();
        for (byte aByte : bytes) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aByte & 0xFF))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }

    public static String prettyBinary(String binary, int blockSize, String separator) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
}