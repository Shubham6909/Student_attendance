//package com.attendance.attendance.model;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//public class ResumeAnalysis {
//
//    public static void main(String[] args) {
//        String fileName = "C:\\Users\\Shubham Gupta\\Downloads\\Resume.txt";
//
//        try {
//            String content = readFile(fileName);
//            processContent(content);
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + e.getMessage());
//        }
//    }
//
//    public static String readFile(String fileName) throws IOException {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//        }
//        return content.toString();
//    }
//
//    public static void processContent(String content) {
//        Map<Character, Integer> alphabetCount = new HashMap<>();
//        Map<String, Integer> wordCount = new HashMap<>();
//        Map<Character, Integer> digitCount = new HashMap<>();
//
//        String[] words = content.split("\\s+");
//
//        for (String word : words) {
//            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
//
//            for (char ch : word.toCharArray()) {
//                if (Character.isLetter(ch)) {
//                    ch = Character.toLowerCase(ch);
//                    alphabetCount.put(ch, alphabetCount.getOrDefault(ch, 0) + 1);
//                } else if (Character.isDigit(ch)) {
//                    digitCount.put(ch, digitCount.getOrDefault(ch, 0) + 1);
//                }
//            }
//        }
//
//        char maxUsedAlphabet = getMaxKey(alphabetCount);
//        char minUsedAlphabet = getMinKey(alphabetCount);
//
//        char maxUsedNumber = getMaxKey(digitCount);
//        char minUsedNumber = getMinKey(digitCount);
//
//        String maxUsedWord = getMaxKey(wordCount);
//        Optional<String> secondMaxUsedWordOpt = getSecondMaxKey(wordCount);
//        String minUsedWord = getMinKey(wordCount);
//        Optional<String> secondMinUsedWordOpt = getSecondMinKey(wordCount);
//
//        System.out.println("Max Used Alphabet: " + maxUsedAlphabet);
//        System.out.println("Max Used Number: " + maxUsedNumber);
//        System.out.println("Min Used Alphabet: " + minUsedAlphabet);
//        System.out.println("Min Used Number: " + minUsedNumber);
//        System.out.println("Max Used Word: " + maxUsedWord);
//        System.out.println("2nd Max Used Word: " + secondMaxUsedWordOpt.orElse("N/A"));
//        System.out.println("Min Used Word: " + minUsedWord);
//        System.out.println("2nd Min Used Word: " + secondMinUsedWordOpt.orElse("N/A"));
//    }
//
//    private static <K> K getMaxKey(Map<K, Integer> map) {
//        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
//    }
//
//    private static <K> K getMinKey(Map<K, Integer> map) {
//        return Collections.min(map.entrySet(), Map.Entry.comparingByValue()).getKey();
//    }
//
//    private static <K> Optional<K> getSecondMaxKey(Map<K, Integer> map) {
//        return map.entrySet()
//                .stream()
//                .sorted(Map.Entry.<K, Integer>comparingByValue().reversed())
//                .skip(1)
//                .map(Map.Entry::getKey)
//                .findFirst();
//    }
//
//    private static <K> Optional<K> getSecondMinKey(Map<K, Integer> map) {
//        return map.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue())
//                .skip(1)
//                .map(Map.Entry::getKey)
//                .findFirst();
//    }
//}
