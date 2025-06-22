import java.util.Scanner;

public class Program18_ReverseAndAdd {
    // Function to check if a number is a palindrome
    public static boolean isPalindrome(long num) {
        String str = Long.toString(num);
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // Function to reverse the digits of a number
    public static long reverse(long num) {
        long rev = 0;
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            long number = Long.parseLong(scanner.nextLine());
            int iterations = 0;

            while (!isPalindrome(number)) {
                long reversed = reverse(number);
                number += reversed;
                iterations++;
            }

            System.out.println(iterations + " " + number);
        }

        scanner.close();
    }
}