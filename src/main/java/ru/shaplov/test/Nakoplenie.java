package ru.shaplov.test;

/**
 * @author Dmitriy Shaplov
 */
public class Nakoplenie {

    public static void main(String[] args) {
        double yearAmount = 0.5;
        double percent = 1.1;
        int years = 50;
        double sum = 0;
        for (int i = 0; i < years; i++) {
            if (i > 0) {
                sum *= percent;
            }
            sum += yearAmount;
        }
        System.out.println(sum);
    }
}
