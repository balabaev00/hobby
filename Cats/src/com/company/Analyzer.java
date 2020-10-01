package com.company;

import java.io.*;
import java.util.ArrayList;

public class Analyzer {
    private ArrayList<Cat> cats = new ArrayList<Cat>();
    // Для хранения средних значений
    private double[][] middleValue = new double[2][4];
    // Коэффицент корреляции Пирсона
    private double pearsonCorrelationCoefficient;

    public ArrayList<Cat> getCats() {
        return cats;
    }

    public double getPearsonCorrelationCoefficient() {
        double numerator = 0; // числитель
        double a = 0;
        double b = 0;
        double denominator = 0; // знаменатель
        for (int i = 0; i < cats.size(); i++) 
            numerator += (cats.get(i).getAge() - middleValue[0][0]) * (cats.get(i).getMass() - middleValue[1][1]);
        for (int i = 0; i < cats.size(); i++) {
            a += Math.pow(cats.get(i).getAge()-middleValue[0][0],2);
        }
        for (int i = 0; i < cats.size(); i++) {
            b += Math.pow(cats.get(i).getMass()-middleValue[0][1],2);
        }
        denominator = Math.sqrt(a*b);
        return numerator/denominator;
    }

    // Вывод средних значений
    public void printMiddleValue() {
        for (int i = 0; i < middleValue.length; i++) {
            for (int j = 0; j < middleValue[i].length; j++) {
                    System.out.print(middleValue[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Получаем средние значения
    public void analyzeCats() {
        int countFirstBrand = 0;
        int countSecondBrand = 0;
        for (Cat cat: cats) {
            if(cat.getBrand()==0) {
                countFirstBrand++;
                middleValue[0][0] += cat.getAge();
                middleValue[0][1] += cat.getMass();
                middleValue[0][2] += cat.getxSound();
                middleValue[0][3] += cat.getSleep();
            }
            else {
                countSecondBrand++;
                middleValue[1][0] += cat.getAge();
                middleValue[1][1] += cat.getMass();
                middleValue[1][2] += cat.getxSound();
                middleValue[1][3] += cat.getSleep();
            }
        }
        for (int i = 0; i < middleValue.length; i++) {
            for (int j = 0; j < middleValue[i].length; j++) {
                if (i==0)
                    middleValue[i][j] = middleValue[i][j]/countFirstBrand;
                else
                    middleValue[i][j] = middleValue[i][j]/countSecondBrand;
            }
        }
    }

    // Преобразуем строку из файла в массив котов
    public void lineToArray(String line) {
        String[] temp  = line.split(",");
        Cat cat = new Cat();
        cat.setAll(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]),
        Double.parseDouble(temp[2]),Double.parseDouble(temp[3]),Double.parseDouble(temp[4]));
        cats.add(cat);
    }

    public void getInfo(String analyzeFileName) throws IOException {
        File analyzeFile = new File(analyzeFileName);
        FileReader analyzeFileReader = new FileReader(analyzeFile);
        BufferedReader analyzeBufferedReader = new BufferedReader(analyzeFileReader);
        // Дважды считываем строки, чтобы пропустить описание
        String lineRead = analyzeBufferedReader.readLine();
        lineRead = analyzeBufferedReader.readLine();
        while (lineRead != null) {
            lineToArray(lineRead);
            lineRead = analyzeBufferedReader.readLine();
        }
    }
}
