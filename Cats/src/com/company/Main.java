package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new Analyzer();
        analyzer.getInfo("train.csv");
        analyzer.analyzeCats();
        analyzer.printMiddleValue();
        System.out.println("Коэффицент корреляции Пирсона = " + analyzer.getPearsonCorrelationCoefficient());
    }
}
