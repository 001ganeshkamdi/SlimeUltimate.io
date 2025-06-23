import java.util.*;

public class Program05_JollyJumper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            int n = Integer.parseInt(parts[0]);
            int[] seq = new int[n];
            for (int i = 0; i < n; i++) {
                seq[i] = Integer.parseInt(parts[i + 1]);
            }
            if (isJolly(seq)) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
        
        }
    }

    private static boolean isJolly(int[] seq) {
        int n = seq.length;
        if (n == 1) return true;
        boolean[] diffs = new boolean[n];
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(seq[i] - seq[i - 1]);
            if (diff < 1 || diff >= n || diffs[diff]) {
                return false;
            }
            diffs[diff] = true;
        }
        for (int i = 1; i < n; i++) {
            if (!diffs[i]) return false;
        }
        return true;
    }
    
}
