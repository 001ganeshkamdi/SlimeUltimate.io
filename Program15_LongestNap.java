import java.util.*;

public class Program15_LongestNap {
    // Class to represent an appointment
    static class Appointment implements Comparable<Appointment> {
        int start, end;

        // Constructor that converts time strings to minutes
        Appointment(String time1, String time2) {
            this.start = timeToMinutes(time1);
            this.end = timeToMinutes(time2);
        }

        // Converts HH:MM to minutes
        static int timeToMinutes(String time) {
            String[] parts = time.split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }
        // Converts minutes to HH:MM format
        static String minutesToTime(int minutes) {
            int h = minutes / 60;
            int m = minutes % 60;
            return String.format("%02d:%02d", h, m);
        }
        // Compare appointments by start time
        @Override
        public int compareTo(Appointment o) {
            return Integer.compare(this.start, o.start);
        }
    }
 public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dayCount = 1;

        // Read until EOF
        while (sc.hasNextInt()) {
            int s = sc.nextInt();
            sc.nextLine(); // consume newline

            List<Appointment> appointments = new ArrayList<>();

            // Read all appointments
            for (int i = 0; i < s; i++) {
                String line = sc.nextLine();
                String[] parts = line.split(" ", 3);
                appointments.add(new Appointment(parts[0], parts[1]));
            }

            // Sort and merge overlapping appointments
            Collections.sort(appointments);
            List<Appointment> merged = new ArrayList<>();
            int i = 0;
            while (i < appointments.size()) {
                int start = appointments.get(i).start;
                int end = appointments.get(i).end;
                i++;
                while (i < appointments.size() && appointments.get(i).start <= end) {
                    end = Math.max(end, appointments.get(i).end);
                    i++;
                }
                merged.add(new Appointment(Appointment.minutesToTime(start), Appointment.minutesToTime(end)));
            }

            // Working hours: 10:00 (600 mins) to 18:00 (1080 mins)
            int workStart = 600, workEnd = 1080;
            int longestNapStart = workStart;
            int longestNapDuration = 0;

            int prevEnd = workStart;
            for (Appointment appt : merged) {
                // Check gap between current and previous appointment
                if (appt.start > prevEnd) {
                    int napLength = appt.start - prevEnd;
                    if (napLength > longestNapDuration) {
                        longestNapDuration = napLength;
                        longestNapStart = prevEnd;
                    }
                }
                prevEnd = Math.max(prevEnd, appt.end);
            }

            // Check gap after last appointment
            if (prevEnd < workEnd) {
                int napLength = workEnd - prevEnd;
                if (napLength > longestNapDuration) {
                    longestNapDuration = napLength;
                    longestNapStart = prevEnd;
                }
            }

            // Print result
            String startTime = Appointment.minutesToTime(longestNapStart);
            System.out.print("Day #" + dayCount + ": the longest nap starts at " + startTime + " and will last for ");
            if (longestNapDuration >= 60) {
                int h = longestNapDuration / 60;
                int m = longestNapDuration % 60;
                System.out.println(h + " hours and " + m + " minutes.");
            } else {
                System.out.println(longestNapDuration + " minutes.");
            }

            dayCount++;
        }
        sc.close();
    }
}