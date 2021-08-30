import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int length = scan.nextInt();
        int result = length / n;
        if (length % n != 0) {
            result++;
        }
        System.out.print(result);
    }
}