import java.util.*;

public class Program14_VitosFamilyimport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        
        while (testCases-- > 0) {
            int r = sc.nextInt();
            int[] streets = new int[r];
            
            for (int i = 0; i < r; i++) {
                streets[i] = sc.nextInt();
            }
            
            Arrays.sort(streets);
            int median = streets[r / 2];
            int totalDistance = 0;
            
            for (int i = 0; i < r; i++) {
                totalDistance += Math.abs(streets[i] - median);
            }
            
            System.out.println(totalDistance);
        }
    }
}
