import java.util.*;

public class Program14_VitosFamily {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of test cases
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int t = 0; t < testCases; t++) {
            // Read the line and split into tokens
            String[] input = scanner.nextLine().split(" ");
            int r = Integer.parseInt(input[0]); // Number of relatives

            // Read street numbers
            int[] streets = new int[r];
            for (int i = 0; i < r; i++) {
                streets[i] = Integer.parseInt(input[i + 1]);
            }

            // Sort to find the median
            Arrays.sort(streets);
            int median = streets[r / 2]; // Median minimizes total absolute distance

            // Calculate total distance to the median
            int totalDistance = 0;
            for (int street : streets) {
                totalDistance += Math.abs(street - median);
            }

            // Output the result
            System.out.println(totalDistance);
        }

        scanner.close();
    }
}