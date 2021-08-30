package ru.shaplov.vtb;

import java.util.Locale;

class Parent {
    String str = "parent";
}

public final class Child extends Parent {
    String[][] str = new String[2][];

     public Child(String[][] str) {
        this.str = str;
    }

    public static void main(String[] args) {
        String str = "ВесКот";
        str.concat("иДжава");
        str = str.toUpperCase(Locale.ROOT);
        str.replace("КОТ", "Груви");
        System.out.println(str);
    }
}
