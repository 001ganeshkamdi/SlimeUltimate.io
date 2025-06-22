import java.util.*;

public class Program12_WhereIsWaldorf {
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine().trim());
        sc.nextLine(); // skip blank line

        for (int t = 0; t < cases; t++) {
            if (t > 0) System.out.println();

            String[] dims = sc.nextLine().trim().split("\\s+");
            int m = Integer.parseInt(dims[0]);
            int n = Integer.parseInt(dims[1]);
            char[][] grid = new char[m][n];

            for (int i = 0; i < m; i++) {
                String line = sc.nextLine().trim().toLowerCase();
                grid[i] = line.toCharArray();
            }

            int k = Integer.parseInt(sc.nextLine().trim());
            String[] words = new String[k];
            for (int i = 0; i < k; i++) {
                words[i] = sc.nextLine().trim().toLowerCase();
            }

            for (String word : words) {
                int[] pos = findWord(grid, m, n, word);
                System.out.println(pos[0] + " " + pos[1]);
            }

            if (sc.hasNextLine()) sc.nextLine(); // skip blank line between cases
        }
        sc.close();
    }

    static int[] findWord(char[][] grid, int m, int n, String word) {
        int len = word.length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 8; d++) {
                    int x = i, y = j, k;
                    for (k = 0; k < len; k++) {
                        if (x < 0 || x >= m || y < 0 || y >= n) break;
                        if (grid[x][y] != word.charAt(k)) break;
                        x += dx[d];
                        y += dy[d];
                    }
                    if (k == len) {
                        // Output is 1-based
                        return new int[]{i + 1, j + 1};
                    }
                }
            }
        }
        // Should never reach here as per problem statement
        return new int[]{-1, -1};
    }
}