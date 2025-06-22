import java.util.*;

public class Program07_Hartals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt(); // Number of days
            int P = sc.nextInt(); // Number of political parties
            int[] hartals = new int[P];
            for (int i = 0; i < P; i++) {
                hartals[i] = sc.nextInt();
            }
            boolean[] lostDays = new boolean[N + 1]; // Track lost working days
            for (int h : hartals) {
                for (int day = h; day <= N; day += h) {
                    if (day % 7 != 6 && day % 7 != 0) { // Skip Fridays (6) and Saturdays (0)
                        lostDays[day] = true;
                    }
                }
            }
            int lostCount = 0;
            for (int day = 1; day <= N; day++) {
                if (lostDays[day]) {
                    lostCount++;
                }
            }
            System.out.println(lostCount);
        }
        sc.close();
    }
}      