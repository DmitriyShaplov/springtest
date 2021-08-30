import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<Integer> boxes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boxes.add(scan.nextInt());
        }
        Collections.sort(boxes);
        int result = 0;
        for (int i = 1; i < n; i += 2) {
            result += boxes.get(i) - boxes.get(i - 1);
        }
        System.out.print(result);
    }
}