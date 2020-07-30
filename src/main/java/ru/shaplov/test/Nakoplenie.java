package ru.shaplov.test;

/**
 * @author Dmitriy Shaplov
 */
public class Nakoplenie {

    public static void main(String[] args) {
        double yearAmount = 1;
        double percent = 1.15;
        int years = 10;
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
