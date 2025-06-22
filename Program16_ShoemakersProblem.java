import java.util.*;

public class Program16_ShoemakersProblem {
    // Class to represent a job with time, fine and original index
    static class Job {
        int time, fine, index;

        Job(int time, int fine, int index) {
            this.time = time;
            this.fine = fine;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.nextLine());
        sc.nextLine(); // skip initial blank line
       for (int t = 0; t < testCases; t++) {
            if (t > 0) System.out.println(); // print blank line between test cases
            int n = Integer.parseInt(sc.nextLine());
            List<Job> jobs = new ArrayList<>();
            // Read all jobs
            for (int i = 0; i < n; i++) {
                String[] parts = sc.nextLine().trim().split("\\s+");
                int time = Integer.parseInt(parts[0]);
                int fine = Integer.parseInt(parts[1]);
                jobs.add(new Job(time, fine, i + 1)); // index starts from 1
            }
            // Sort jobs by (fine / time) descending, using cross multiplication to avoid division
            jobs.sort((a, b) -> {
                long lhs = (long) a.fine * b.time;
                long rhs = (long) b.fine * a.time;
                if (lhs != rhs) {
                    return Long.compare(rhs, lhs); // descending
                }
                return Integer.compare(a.index, b.index); // tie breaker: smaller index first
            });
            // Output the job sequence
            for (int i = 0; i < jobs.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(jobs.get(i).index);
            }
            System.out.println();

            // Skip blank line between test cases
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
        sc.close();
    }
}