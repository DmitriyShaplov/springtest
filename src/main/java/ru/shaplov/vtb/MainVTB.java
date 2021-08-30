package ru.shaplov.vtb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainVTB {

    private MainVTB mainVTB;
//    T = [73, 74, 75, 71, 69, 72, 76, 73]
//    daysNumber( T ) --> [1, 1, 4, 2, 1, 1, 0, 0]

    public static void main(String[] args) {

        List<Integer> array = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3));
        for (Integer integer : array) {
            array.add(integer + 3);
            System.out.println(integer);
        }
//        System.out.println(v1() ^ v2());
//        System.out.println(dailyTemperatures(Arrays.asList(73, 74, 75, 71, 69, 72, 76, 73)));
    }

   static boolean v1() {
        return false;
    }

   static boolean v2() {
        return true;
    }

    public static List<Integer> dailyTemperatures(List<Integer> t) {
        if (t.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < t.size(); i++) {
            Integer curNum = t.get(i);
            int offset = 0;
            do {
                offset++;
                if (i + offset == t.size()) {
                    offset = 0;
                    break;
                }
            }
            while (i + offset < t.size() && t.get(i + offset) <= curNum);
            result.add(offset);
        }
        return result;
    }
}
