import java.util.*;

public class Program11_CommonPermutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String a = sc.nextLine();
            if (!sc.hasNextLine()) break;
            String b = sc.nextLine();
            int[] countA = new int[26];
            int[] countB = new int[26];
            for (char ch : a.toCharArray()) countA[ch - 'a']++;
            for (char ch : b.toCharArray()) countB[ch - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                int min = Math.min(countA[i], countB[i]);
                for (int j = 0; j < min; j++) {
                    sb.append((char) ('a' + i));
                }
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}