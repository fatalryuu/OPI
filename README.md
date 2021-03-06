# OPI
репозиторий для опи

package com.company;

import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static String getFileExtension(File file) {
        String fileName = file.getName();

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
       else
            return "";
    }

    public static void outputOfTaskInfo() {
        System.out.println("This program determines the number of vowels and consonants in sentence.");
    }

    public static String inputPathToFile() {
        boolean isInCorrect;
        String path;

        System.out.print("Enter the link to a file: ");

        do {
            isInCorrect = false;
            path = scan.next(); //Line
            File file = new File(path);

            if (!file.exists()) {
                System.out.print("The file is not found! Enter the right path: ");
                isInCorrect = true;
            }
            if (!isInCorrect && !getFileExtension(file).equals("txt")) {
                System.out.print("ERROR! Try to enter another link: ");
                isInCorrect = true;
            }
        } while (isInCorrect);
        return path;
    }

   public static int getVerificationOfChoice() {
        int choice = 0;
        boolean isInCorrect;

        do {
            isInCorrect = false;
            try {
                choice = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("ERROR! Try to enter the value again: ");
                isInCorrect = true;
            }
            if (!isInCorrect && (choice != 1 && choice != 2)) {
                System.out.print("ERROR! Try to enter the value again: ");
                isInCorrect = true;
            }
        } while (isInCorrect);

        return choice;
    }

    public static String readSentenceFromConsole() {
        String sentence;

        System.out.print("Enter your sentence: ");
        sentence = scan.next();

        return sentence.toLowerCase();
    }

    public static String readSentenceFromFile(final String path) {
        String sentence = "";

        System.out.println("Reading...");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(path));
            sentence = fileReader.readLine();
        } catch(IOException e) {
            System.out.print("ERROR");
        }

        return sentence.toLowerCase();

    }

    public static LinkedHashSet<Character> transformSentenceToSet(String sentence) {
        LinkedHashSet<Character> sentenceSet = new LinkedHashSet<>();
        char[] sentenceArr = sentence.toCharArray();

        for (char c : sentenceArr)
            sentenceSet.add(c);

        return sentenceSet;
    }

    public static boolean findOtherSymbols(final LinkedHashSet<Character> sentence) {
        LinkedHashSet<Character> nums = new LinkedHashSet<>();
        LinkedHashSet<Character> russ = new LinkedHashSet<>();
        LinkedHashSet<Character> other = new LinkedHashSet<>();

        return false;

        //for (int i = 0; i < 10; i++)
        //nums.insert("i");
    }

    public static int[] findResult(final LinkedHashSet<String> sogl, final LinkedHashSet<String> glas, final LinkedHashSet<Character> sentenceSet) {
        LinkedHashSet<String> interSogl = new LinkedHashSet<>(sogl);
        LinkedHashSet<String> interGlas = new LinkedHashSet<>(glas);
        int[] answerArray = { 0, 0 };

        sogl.retainAll(sentenceSet);
        glas.retainAll(sentenceSet);

        interSogl.retainAll(sentenceSet);
        interGlas.retainAll(sentenceSet);

        answerArray[0] = sogl.size();
        answerArray[1] = glas.size();

        /*answerArray[0] = interSogl.size();
        answerArray[1] = interGlas.size();*/

        return answerArray;
    }

    public static void outputToConsole(int[] answerArray) {
        int numberOfSogl = answerArray[0];
        int numberOfGlas = answerArray[1];

        System.out.print("Consonants: " + numberOfSogl + "\n");
        System.out.print("Vowels: " + numberOfGlas);
    }

    public static void outputToFile(int[] answerArray, String path) {
        int numberOfSogl = answerArray[0];
        int numberOfGlas = answerArray[1];
        boolean isInCorrect;

        do {
            isInCorrect = false;
            try {
                FileWriter writer = new FileWriter(path);
                writer.write("Consonants: " + numberOfSogl + "\n");
                writer.write("Vowels: " + numberOfGlas);
                writer.close();
            }
            catch (IOException e) {
                System.out.print("ERROR! Change file parameters or provide a link to a new one. \n");
                isInCorrect = true;
                path = inputPathToFile();
            }
        } while (isInCorrect);

        System.out.print("The data has been successfully written to a file. ");
    }

    public static void main(String[] args) {
        int choiceForInput;
        int choiceForOutput;
        String inputFile;
        String outputFile;
        String sentence;
        boolean isInCorrect;
        String[] glasList = new String[] { "a", "e", "i", "o", "u" };
        LinkedHashSet<String> glas = new LinkedHashSet<>(Arrays.asList(glasList));
        String[] soglList = new String[] { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z" };
        LinkedHashSet<String> sogl = new LinkedHashSet<>(Arrays.asList(soglList));
        LinkedHashSet<Character> sSet = new LinkedHashSet<>();
        int[] answerArray = { 0, 0 };

        outputOfTaskInfo();

        System.out.print("Do you want to enter a sentence from console(1) or read from a file(2) ? ");
        choiceForInput = getVerificationOfChoice();

        if (choiceForInput == 1) {
            do {
                sentence = readSentenceFromConsole();
                sSet = transformSentenceToSet(sentence);
                isInCorrect = findOtherSymbols(sSet);
            } while(isInCorrect);
        }

        if (choiceForInput == 2) {
            inputFile = inputPathToFile();
            sentence = readSentenceFromFile(inputFile);
            sSet = transformSentenceToSet(sentence);
            isInCorrect = findOtherSymbols(sSet);
            if (!isInCorrect) {
                do {
                    sentence = readSentenceFromConsole();
                    sSet = transformSentenceToSet(sentence);
                    isInCorrect = findOtherSymbols(sSet);
                } while (isInCorrect);
            }
        }

        answerArray = findResult(sogl, glas, sSet);

        System.out.print("Do you want to get an answer in console(1) or in file(2) ? ");
        choiceForOutput = getVerificationOfChoice();

        if (choiceForOutput == 1)
            outputToConsole(answerArray);

        if (choiceForOutput == 2) {
            outputFile = inputPathToFile();
            outputToFile(answerArray, outputFile);
        }
        scan.close();
    }
}
