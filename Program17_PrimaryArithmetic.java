import java.util.Scanner;

public class Program17_PrimaryArithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            long a = scanner.nextLong();
            long b = scanner.nextLong();

            if (a == 0 && b == 0) break;

            int carry = 0;
            int carryCount = 0;

            while (a > 0 || b > 0) {
                int digitA = (int)(a % 10);
                int digitB = (int)(b % 10);
                int sum = digitA + digitB + carry;

                if (sum >= 10) {
                    carry = 1;
                    carryCount++;
                } else {
                    carry = 0;
                }

                a /= 10;
                b /= 10;
            }

            if (carryCount == 0) {
                System.out.println("No carry operation.");
            } else if (carryCount == 1) {
                System.out.println("1 carry operation.");
            } else {
                System.out.println(carryCount + " carry operations.");
            }
        }

        scanner.close();
    }
}