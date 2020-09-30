package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CrossZero test = new CrossZero();
        test.checkMatch("inputData.txt","outputData.txt");
        System.out.println("Побед крестиков : " + test.getWonMatches());
        System.out.println("Проигрышь, либо ничья крестиков :" + test.getNotWonMatches());
    }
}
