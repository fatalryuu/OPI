package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 20;
    private static final int MIN_VALUE = -1000;
    private static final int MAX_VALUE = 1000;

    public static String getFileExtension(File file) {
        String fileName = file.getName();

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else
            return "";
    }

    public static void outputOfTaskInfo() {
        System.out.print("Эта программа находит норму заданной матрицы.\n");
    }

    public static String inputPathToFile() {
        boolean isInCorrect;
        String path;

        System.out.print("Введите ссылку на файл: ");

        do {
            isInCorrect = false;
            path = scan.nextLine();
            File file = new File(path);

            if (!file.exists()) {
                System.out.print("Файл не найден! Введите правильную ссылку: ");
                isInCorrect = true;
            }
            if (!isInCorrect && !getFileExtension(file).equals("txt")) {
                System.out.print("Ошибка! Попробуйте указать другую ссылку: ");
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
                System.out.print("Ошбика! Введите целое число: ");
                isInCorrect = true;
            }
            if (!isInCorrect && (choice != 1 && choice != 2)) {
                System.out.print("Ошибка! Попробуйте ввести значение еще раз: ");
                isInCorrect = true;
            }
        } while (isInCorrect);

        return choice;
    }

    public static int readSizeFromConsole() {
        boolean isInCorrect;
        int size = 0;
        System.out.print("Введите размерность матрицы (от " + MIN_SIZE + " до " + MAX_SIZE + ") : ");

        do {
            isInCorrect = false;
            try {
                size = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Попробуйте ввести значение еще раз: ");
                isInCorrect = true;
            }
            if (!isInCorrect && (size > MAX_SIZE || size < MIN_SIZE)) {
                System.out.print("Ошибка! Попробуйте ввести значение еще раз: ");
                isInCorrect = true;
            }

        } while (isInCorrect);
        return size;
    }

    public static int readSizeFromFile(final String path) {
        boolean isInCorrect = true;
        int size;

        System.out.print("\nЧтение размеров матрицы...\n");

        try {
            Scanner fileReader = new Scanner(new File(path));
            size = fileReader.nextInt();
        } catch (IOException e) {
            isInCorrect = false;
            System.out.print("Ошбика! Введите в консоль целое число от " + MIN_VALUE + " до " + MAX_VALUE + ": ");
            size = readSizeFromConsole();
        }
        if (isInCorrect && (size > MAX_VALUE || size < MIN_VALUE)) {
            size = readSizeFromConsole();
        }

        return size;
    }

    public static void outputOfMatrixInConsole(final int size, final int[][] matrix){
        System.out.print("Исходная матрица: \n");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + "\t");

            } System.out.print("\n");
        } System.out.print("\n");
    }

    public static int[][] getMatrixFromConsole(final int size) {
        int[][] matrix = new int[size][size];
        boolean isInCorrect;

        System.out.print("Вводите по очереди элементы от " + MIN_VALUE + " до " + MAX_VALUE + ". \n");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("Введите значение " + (i + 1) + "-ой строки и " + (j + 1) + "-ого столбца матрицы: ");
                do {
                    isInCorrect = false;
                    try {
                        matrix[i][j] = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.print("Ошибка! Попробуйте ввести значение еще раз: ");
                        isInCorrect = true;
                    }
                    if (!isInCorrect && (matrix[i][j] < MIN_VALUE || matrix[i][j] > MAX_VALUE)) {
                        System.out.print("Ошибка! Введите элемент из указанного диапазона: ");
                        isInCorrect = true;
                    }
                } while (isInCorrect);
            }
        } outputOfMatrixInConsole(size, matrix);
        return matrix;
    }

    public static int inputMatrixElemFromConsole() {
        int elem = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            try {
                elem = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.err.print("Введите целое число: ");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return elem;
    }


    public static int[][] getMatrixFromFile(final String path, final int size) throws FileNotFoundException {
        int[][] matrix = new int[size][size];

        Scanner fileReader = new Scanner(new File(path));
        //BufferedReader fileReader = new BufferedReader(new FileReader(path));
        fileReader.nextLine();

        System.out.print("\nЧтение матрицы...\n");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    matrix[i][j] = fileReader.nextInt();
                }
                catch (Exception e) {
                    System.out.print("Ошибка при чтении матрицы. Введите матрицу с консоли: ");
                    matrix = getMatrixFromConsole(size);
                    i = size - 1;
                    j = size - 1;
                    //matrix[i][j] = inputMatrixElemFromConsole();

                }
                if (matrix[i][j] < MIN_VALUE || matrix[i][j] > MAX_VALUE) {
                    System.out.print("Ошибка при чтении матрицы! Введите матрицу с консоли: ");
                    matrix = getMatrixFromConsole(size);
                    //matrix[i][j] = inputMatrixElemFromConsole();
                    i = size - 1;
                    j = size - 1;
                }
            }
        }
        return matrix;
    }

    public static int getNorm(final int[][] matrix, final int size) {
        int max = 0;
        int sum;


        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = 0; j < size; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            if (sum > max)
                max = sum;
        }
        return max;
    }
    public static void outputOfNorm(final int choice, String path, final int norm) {
        boolean isInCorrect;

        if (choice == 1)
            System.out.print("Норма вашей матрицы равна: " + norm);

        if (choice == 2) {
            do {
                isInCorrect = false;
                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(String.valueOf(norm));
                    writer.close();
                } catch (IOException e) {
                    isInCorrect = true;
                    System.out.print("Ошибка! Отказано в доступе. Измените параметры файла или укажите новую ссылку.\n");
                    path = inputPathToFile();
                }
            } while (isInCorrect);

            System.out.print("Данные успешно записаны в файл!");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        int size = 0;
        int choiceForSize;
        int choiceForInput;
        int choiceForOutput;
        int norm = 0;
        String inputFile = "";
        String outputFile = "";

        outputOfTaskInfo();

        System.out.print("Вы хотите получить размер с консоли(1) или с файла(2)? ");

        choiceForSize = getVerificationOfChoice();

        if (choiceForSize == 1)
            size = readSizeFromConsole();

        if (choiceForSize == 2) {
            inputFile = inputPathToFile();
            size = readSizeFromFile(inputFile);
        }

        int[][] matrix = new int[size][size];


        System.out.print("\nВы хотите заполнить матрицу самостоятельно(1) или считать с файла(2)? ");
        choiceForInput = getVerificationOfChoice();

        if (choiceForInput == 1)
            matrix = getMatrixFromConsole(size);

        if (choiceForInput == 2) {
            inputFile = inputPathToFile();
            matrix = getMatrixFromFile(inputFile, size);
        }

        System.out.print("\nВы хотите получить ответ в консоли(1) или в файле(2)? ");
        choiceForOutput = getVerificationOfChoice();

        if (choiceForOutput == 2)
            outputFile = inputPathToFile();

        norm = getNorm(matrix, size);
        outputOfNorm(choiceForOutput, outputFile, norm);
        scan.close();
    }
}
