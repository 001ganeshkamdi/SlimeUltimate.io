import java.util.Scanner;

public class Program01_ThreeNPlusOne {
    // Function to compute the cycle length for a given n
    public static int cycleLength(int n) {
        int count = 1;
        while (n != 1) {
            if ((n & 1) == 1) { // n is odd
                n = 3 * n + 1;
            } else {
                n = n / 2;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int start = Math.min(i, j);
            int end = Math.max(i, j);
            int maxCycle = 0;
            for (int n = start; n <= end; n++) {
                int length = cycleLength(n);
                if (length > maxCycle) {
                    maxCycle = length;
                }
            }
            System.out.println(i + " " + j + " " + maxCycle);
        }
        sc.close();
    }
}