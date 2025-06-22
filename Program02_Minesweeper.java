import java.util.Scanner;

public class Program02_Minesweeper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fieldNum = 1;
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) break;
            sc.nextLine(); // consume the rest of the line

            char[][] field = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                field[i] = line.toCharArray();
            }

            if (fieldNum > 1) System.out.println();
            System.out.println("Field #" + fieldNum + ":");

            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == '*') {
                        sb.append('*');
                    } else {
                        int count = 0;
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dy = -1; dy <= 1; dy++) {
                                if (dx == 0 && dy == 0) continue;
                                int ni = i + dx, nj = j + dy;
                                if (ni >= 0 && ni < n && nj >= 0 && nj < m && field[ni][nj] == '*') {
                                    count++;
                                }
                            }
                        }
                        sb.append(count);
                    }
                }
                System.out.println(sb);
            }
            fieldNum++;
        }
        sc.close();
    }
}