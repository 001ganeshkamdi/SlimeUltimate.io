import java.util.Scanner;

public class Program20_SternBrocotNumberSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            if (m == 1 && n == 1) break;

            sternBrocotPath(m, n);
        }

        scanner.close();
    }

    // Method to find and print the Stern-Brocot path
    public static void sternBrocotPath(int m, int n) {
        int leftNum = 0, leftDen = 1;
        int rightNum = 1, rightDen = 0;
        int midNum, midDen;

        StringBuilder path = new StringBuilder();

        while (true) {
            midNum = leftNum + rightNum;
            midDen = leftDen + rightDen;

            if (m * midDen < n * midNum) {
                // Target is smaller => go left
                path.append("L");
                rightNum = midNum;
                rightDen = midDen;
            } else if (m * midDen > n * midNum) {
                // Target is larger => go right
                path.append("R");
                leftNum = midNum;
                leftDen = midDen;
            } else {
                // Found the fraction
                break;
            }
        }

        System.out.println(path.toString());
    }
}