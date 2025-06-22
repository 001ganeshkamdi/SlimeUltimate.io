import java.util.*;

public class Program09_Wertyu {
    // QWERTY keyboard layout as a single string for mapping
    static final String keys = 
        "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringBuilder decoded = new StringBuilder();
            for (char ch : line.toCharArray()) {
                if (ch == ' ') {
                    decoded.append(' ');
                } else {
                    int idx = keys.indexOf(ch);
                    if (idx > 0) {
                        decoded.append(keys.charAt(idx - 1));
                    } else {
                        decoded.append(ch); // If not found, just append as is
                    }
                }
            }
            System.out.println(decoded.toString());
        }
        sc.close();
    }
}