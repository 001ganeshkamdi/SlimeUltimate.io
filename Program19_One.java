import java.util.Scanner;

public class Program19_One {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int count = 1;
            int remainder = 1 % n;

            while (remainder != 0) {
                remainder = (remainder * 10 + 1) % n;
                count++;
            }

            System.out.println(count);
        }

        scanner.close();
    }
}