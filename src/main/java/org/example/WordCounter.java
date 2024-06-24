package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {

    public static void main(String[] args) {
        String fileName = "counter.txt";
        Map<String, Integer> wordFreq = countWordFrequency(fileName);
        printWordFrequency(wordFreq);
    }

    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordFreq = new HashMap<>();

        try (InputStream inputStream = WordCounter.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                for (String word : words) {
                    wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> sortedWordFreq = new TreeMap<>((w1, w2) -> {
            int freqCompare = wordFreq.get(w2).compareTo(wordFreq.get(w1));
            if (freqCompare == 0) {
                return w1.compareTo(w2);
            }
            return freqCompare;
        });
        sortedWordFreq.putAll(wordFreq);

        return sortedWordFreq;
    }

    public static void printWordFrequency(Map<String, Integer> wordFreq) {
        for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
