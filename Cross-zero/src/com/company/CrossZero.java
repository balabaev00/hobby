package com.company;

import java.io.*;
import java.util.Scanner;

public class CrossZero {
    private String[][] match;
    private int wonMatches = 0; // Количество побед крестиков
    private int notWonMatches = 0; // Проигрышь, либо ничья крестиков
    private final int size = 3;

    public CrossZero() {
        match = new String[size][size];
    }

    public int getWonMatches() {
        return wonMatches;
    }

    public int getNotWonMatches() {
        return notWonMatches;
    }

    /**
     *  Ввод данных в ручную
     *  Ввод осуществляется на английской раскладке через пробел
     *  "x" - крестик
     *  "o" - нолик
     *  "b" - пустая клетка
     **/
    public void readMatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные для игры <Крестики Нолики>");
        System.out.println("Ввод осуществляется на английской раскладке\n" +
                " \"x\" - крестик\n" +
                " \"o\" - нолик\n" +
                " \"b\" - пустая клетка");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                match[i][j] = scanner.next();
            }
        }
    }

    // Форматированный вывод игры на экран
    public void printMatch() {
        for (int i = 0; i < match.length; i++) {
            for (int j = 0; j < match[i].length; j++) {
                System.out.print(" | " + match[i][j]);
            }
            System.out.println(" | ");
        }
    }

    // Проверка строк
    private boolean checkLines() {
        for (int i = 0; i < match.length; i++) {
            if (match[i][0].equals("x") &&
                match[i][1].equals("x") &&
                match[i][2].equals("x"))
                return true;
            /*if (match[i][0].equals("o") &&
                match[i][1].equals("o") &&
                match[i][2].equals("o"))
                return true;
             */
        }
        return false;
    }

    // Проверка столбцов
    private boolean checkColumn() {
        for (int i = 0; i < match[0].length; i++) {
            if (match[0][i].equals("x") &&
                match[1][i].equals("x") &&
                match[2][i].equals("x"))
                return true;
            /*if (match[0][i].equals("o") &&
                match[1][i].equals("o") &&
                match[2][i].equals("o"))
                return true;
             */
        }
        return false;
    }

    // Проверка диагоналей
    private boolean checkDiagonal() {
        if (match[0][0].equals("x") &&
            match[1][1].equals("x") &&
            match[2][2].equals("x"))
            return true;
        /*if (match[0][0].equals("o") &&
            match[1][1].equals("o") &&
            match[2][2].equals("o"))
            return true;
         */
        if (match[0][2].equals("x") &&
            match[1][1].equals("x") &&
            match[2][0].equals("x"))
            return true;
        /*if (match[0][2].equals("o") &&
            match[1][1].equals("o") &&
            match[2][0].equals("o"))
            return true;
         */
        return false;
    }

    /**
     * Преобразование строки из файла в массив
     * @param line - строка из файла
     */
    public void lineToArray(String line) {
        char[] temp = line.toCharArray();
        int k=0;
        int j=0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i]!=',' && temp[i]!=' ') {
                match[k][j] = String.valueOf(temp[i]);
                if (j==2) {
                    j = 0;
                    k++;
                }
                else j++;
            }
        }
    }

    // Проверка победели ли крестики
    public boolean checkWinner() {
        if (checkLines() || checkColumn() || checkDiagonal()) {
            wonMatches++;
            return true;
        }
        else {
            notWonMatches++;
            return false;
        }
    }

    // Основной метод
    public void checkMatch(String inputFileName, String outputFileName) throws IOException {
        // Данные для считывания из data.txt
        File dataFile = new File(inputFileName);
        FileReader frData = new FileReader(dataFile);
        BufferedReader readerData = new BufferedReader(frData);
        // Данные для записи в новый файл
        File outputDataFile = new File(outputFileName);
        FileWriter fwData = new FileWriter(outputFileName);
        String readLine = readerData.readLine();
        // Считывания файла
        while (readLine != null) {
            // Перевод строки в массив
            lineToArray(readLine);
            if (checkWinner())
                fwData.write(readLine + " (1)" + "\n");
            else
                fwData.write(readLine + " (0)" + "\n");
            readLine = readerData.readLine();
        }
        frData.close();
        fwData.write("Побед крестиков : " + getWonMatches() + "\n");
        fwData.write("Проигрышь, либо ничья крестиков :" + getNotWonMatches() + "\n");
        fwData.close();
    }

}
