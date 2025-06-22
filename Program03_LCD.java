import java.util.*;

public class Program03_LCD {
    // Segment mapping for digits 0-9
    // Each array: {top, top-left, top-right, middle, bottom-left, bottom-right, bottom}
    private static final int[][] DIGIT_SEGMENTS = {
        {1,1,1,0,1,1,1}, // 0
        {0,0,1,0,0,1,0}, // 1
        {1,0,1,1,1,0,1}, // 2
        {1,0,1,1,0,1,1}, // 3
        {0,1,1,1,0,1,0}, // 4
        {1,1,0,1,0,1,1}, // 5
        {1,1,0,1,1,1,1}, // 6
        {1,0,1,0,0,1,0}, // 7
        {1,1,1,1,1,1,1}, // 8
        {1,1,1,1,0,1,1}  // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int s = sc.nextInt();
            int n = sc.nextInt();
            if (s == 0 && n == 0) break;
            printLCD(s, String.valueOf(n));
            System.out.println();
        }
        sc.close();
    }

    private static void printLCD(int s, String number) {
        int rows = 2 * s + 3;
        int cols = (s + 2) * number.length() + number.length() - 1;
        char[][] display = new char[rows][cols];
        for (char[] row : display) Arrays.fill(row, ' ');

        for (int idx = 0; idx < number.length(); idx++) {
            int digit = number.charAt(idx) - '0';
            int offset = idx * (s + 3);

            // Top
            if (DIGIT_SEGMENTS[digit][0] == 1)
                for (int i = 1; i <= s; i++)
                    display[0][offset + i] = '-';
            // Middle
            if (DIGIT_SEGMENTS[digit][3] == 1)
                for (int i = 1; i <= s; i++)
                    display[s + 1][offset + i] = '-';
            // Bottom
            if (DIGIT_SEGMENTS[digit][6] == 1)
                for (int i = 1; i <= s; i++)
                    display[2 * s + 2][offset + i] = '-';
            // Top-left
            if (DIGIT_SEGMENTS[digit][1] == 1)
                for (int i = 1; i <= s; i++)
                    display[i][offset] = '|';
            // Top-right
            if (DIGIT_SEGMENTS[digit][2] == 1)
                for (int i = 1; i <= s; i++)
                    display[i][offset + s + 1] = '|';
            // Bottom-left
            if (DIGIT_SEGMENTS[digit][4] == 1)
                for (int i = 1; i <= s; i++)
                    display[s + 1 + i][offset] = '|';
            // Bottom-right
            if (DIGIT_SEGMENTS[digit][5] == 1)
                for (int i = 1; i <= s; i++)
                    display[s + 1 + i][offset + s + 1] = '|';
        }

        for (char[] row : display)
            System.out.println(new String(row));
    }
}