//package com.attendance.attendance.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CandidateProcessor {
//
//    static class Candidate {
//        String type;
//        String name;
//
//        Candidate(String type, String name) {
//            this.type = type;
//            this.name = name;
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Candidate> candidates = createDummyCandidates();
//
//        // Print Candidates
//        for (Candidate candidate : candidates) {
//            System.out.println(candidate.type + " Candidate: " + candidate.name);
//        }
//
//        // Generate comma-separated names for Academic and Sports candidates
//        String academicNames = generateCommaSeparatedNames(candidates, "Academic");
//        String sportsNames = generateCommaSeparatedNames(candidates, "Sports");
//
//        // Process and sort names
//        System.out.println("Processed Academic Names: ");
//        process(academicNames);
//
//        System.out.println("Processed Sports Names: ");
//        process(sportsNames);
//    }
//
//    private static List<Candidate> createDummyCandidates() {
//        List<Candidate> candidates = new ArrayList<>();
//
//        // Add 23 Sports Candidates with Indian names
//        String[] sportsNames = {
//                "Virat", "Sachin", "Dhoni", "Rohit", "Sourav",
//                "Kapil", "Rahul", "Anil", "Yuvraj", "Harbhajan",
//                "Zaheer", "Laxman", "Irfan", "Suresh", "Jadeja",
//                "Shikhar", "Bumrah", "Pant", "Hardik", "Shreyas",
//                "Ajinkya", "KL Rahul", "Bhuvneshwar"
//        };
//
//        for (String name : sportsNames) {
//            candidates.add(new Candidate("Sports", name));
//        }
//
//        // Add 27 Academic Candidates with Indian names
//        String[] academicNames = {
//                "Vishal", "Naman", "Shivam", "Rohan", "Harsh",
//                "Mohit", "Vikram", "Rohit", "Ram", "Mohan",
//                "Krishna", "Sandeep", "Aditya", "Raja", "Sham",
//                "Nitesh", "Raj", "Jayant", "Ritesh",
//                "Sonu", "Monu", "Taresh", "Ajit",
//                "Vishnu", "Abhishek", "Hitesh", "Somnath"
//        };
//
//        for (String name : academicNames) {
//            candidates.add(new Candidate("Academic", name));
//        }
//
//        return candidates;
//    }
//
//    private static String generateCommaSeparatedNames(List<Candidate> candidates, String type) {
//        StringBuilder names = new StringBuilder();
//        int count = 0;
//
//        for (Candidate candidate : candidates) {
//            if (candidate.type.equals(type)) {
//                if (count > 0) {
//                    names.append(",");
//                }
//                names.append(candidate.name);
//                count++;
//            }
//            if (count == 10) {
//                break;
//            }
//        }
//
//        return names.toString();
//    }
//
//    private static void process(String names) {
//        String[] nameArray = names.split(",");
//        sort(nameArray);
//
//        for (String name : nameArray) {
//            System.out.println(name);
//        }
//    }
//
//    private static void sort(String[] array) {
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = i + 1; j < array.length; j++) {
//                if (array[i].compareTo(array[j]) > 0) {
//                    String temp = array[i];
//                    array[i] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }
//    }
//}
