import java.util.*;

public class Program13_StackOfFlapjacks {

    // Flip the top k+1 elements (0-based indexing)
    static void flip(List<Integer> stack, int k) {
        for (int i = 0, j = k; i < j; i++, j--) {
            // Swap stack[i] with stack[j]
            int temp = stack.get(i);
            stack.set(i, stack.get(j));
            stack.set(j, temp);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Process until EOF
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue; // Skip empty lines

            // Parse input line into integers (pancake diameters)
            String[] tokens = line.split("\\s+");
            List<Integer> stack = new ArrayList<>();
            for (String token : tokens) {
                stack.add(Integer.parseInt(token));
            }

            // Echo the original stack
            System.out.println(line);

            List<Integer> flips = new ArrayList<>();
            int n = stack.size();

            // Pancake sorting logic
            for (int currSize = n; currSize > 1; currSize--) {
                // Find the index of the largest pancake in unsorted portion (0 to currSize - 1)
                int maxIndex = 0;
                for (int i = 1; i < currSize; i++) {
                    if (stack.get(i) > stack.get(maxIndex)) {
                        maxIndex = i;
                    }
                }

                // If already in correct position, skip
                if (maxIndex == currSize - 1) continue;

                // If largest pancake is not at the top, flip it to the top
                if (maxIndex != 0) {
                    flips.add(n - maxIndex); // Position from bottom
                    flip(stack, maxIndex);
                }

                // Now flip it to its correct position (bottom of current unsorted portion)
                flips.add(n - (currSize - 1)); // Position from bottom
                flip(stack, currSize - 1);
            }

            // Print the flip sequence, ending with 0
            for (int flip : flips) {
                System.out.print(flip + " ");
            }
            System.out.println("0");
        }

        scanner.close();
    }
}