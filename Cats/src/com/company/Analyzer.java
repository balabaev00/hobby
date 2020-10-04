package com.company;

import java.io.*;
import java.util.ArrayList;

public class Analyzer {
    private ArrayList<Cat> cats = new ArrayList<Cat>();
    private int countFirstBrand = 0;
    private int countSecondBrand = 0;
    // Для хранения средних значений
    private double[][] middleValue = new double[2][4];
    // Коэффицент корреляции Пирсона
    private double pearsonCorrelationCoefficient;

    public void clearCats() {
        cats.clear();
    }

    public ArrayList<Cat> getCats() {
        return cats;
    }

    /*public double test() {
        double numerator = 0; // числитель
        double a = 0;
        double b = 0;
        double denominator = 0; // знаменатель
        double sr = ((double)countFirstBrand+countSecondBrand)/cats.size();
        double srAge = 0;
        for (int i = 0; i < cats.size(); i++) {
            srAge+=cats.get(i).getSleep();
        }
        srAge = srAge/ cats.size();
        for (int i = 0; i < cats.size(); i++)
            numerator += (cats.get(i).getSleep() - srAge) * (cats.get(i).getBrand() - sr);
        for (int i = 0; i < cats.size(); i++) {
            a += Math.pow(cats.get(i).getSleep()-srAge,2);
        }
        for (int i = 0; i < cats.size(); i++) {
            b += Math.pow(cats.get(i).getBrand()-sr,2);
        }
        denominator = Math.sqrt(a*b);
        return numerator/denominator;
    }
     */


/*
    public double getPearsonCorrelationCoefficient() {
        double numerator = 0; // числитель
        double a = 0;
        double b = 0;
        double denominator = 0; // знаменатель
        for (int i = 0; i < cats.size(); i++) 
            numerator += (cats.get(i).getAge() - middleValue[0][0]) * (cats.get(i).getxSound() - middleValue[0][2]);
        for (int i = 0; i < cats.size(); i++) {
            a += Math.pow(cats.get(i).getAge()-middleValue[0][0],2);
        }
        for (int i = 0; i < cats.size(); i++) {
            b += Math.pow(cats.get(i).getMass()-middleValue[0][2],2);
        }
        denominator = Math.sqrt(a*b);
        return numerator/denominator;
    }
    *
     */

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
    public void lineToArray(String line, int index) {
        String[] temp  = line.split(",");
        Cat cat = new Cat();
        if (index == 0)
            cat.setAll(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]),
            Double.parseDouble(temp[2]),Double.parseDouble(temp[3]),Double.parseDouble(temp[4]));
        else {
            cat.setAge(Double.parseDouble(temp[0]));
            cat.setMass(Double.parseDouble(temp[1]));
            cat.setxSound(Double.parseDouble(temp[2]));
            cat.setSleep(Double.parseDouble(temp[3]));
        }
        cats.add(cat);
    }

    public void getInfo(String analyzeFileName, int index) throws IOException {
        File analyzeFile = new File(analyzeFileName);
        FileReader analyzeFileReader = new FileReader(analyzeFile);
        BufferedReader analyzeBufferedReader = new BufferedReader(analyzeFileReader);
        clearCats();
        // Дважды считываем строки, чтобы пропустить описание
        String lineRead = analyzeBufferedReader.readLine();
        lineRead = analyzeBufferedReader.readLine();
        while (lineRead != null) {
            lineToArray(lineRead,index);
            lineRead = analyzeBufferedReader.readLine();
        }
        analyzeBufferedReader.close();
        analyzeFileReader.close();
    }

    public void prediction(String inputFileName, String outputFileName) throws IOException {
        double stepAge = (middleValue[0][0]-middleValue[1][0])/2;
        double stepMass = (middleValue[0][1]-middleValue[1][1])/2;
        double stepXSound = (middleValue[0][2]-middleValue[1][2])/2;
        double stepSleep = (middleValue[0][3]-middleValue[1][3])/2;
        getInfo(inputFileName,1);
        File file = new File(outputFileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("# id,brand\n");
        double sum1=0;
        double sum2=middleValue[1][0]+middleValue[1][1]+middleValue[1][2]+middleValue[1][3];
        for (int i=0;i<cats.size();i++) {
            fileWriter.write(i +",");
            sum1+=cats.get(i).getAge()+cats.get(i).getMass()+cats.get(i).getSleep()+cats.get(i).getxSound();
            if(sum2<=sum1)
                fileWriter.write(1 + "\n");
            else fileWriter.write(0+"\n");
            sum1 = 0;
        }
        fileWriter.close();
    }

    public void start(String inputFileName,String outputFileName) throws IOException {
        getInfo("train.csv",0);
        analyzeCats();
        printMiddleValue();
        prediction(inputFileName,outputFileName);
    }
}
