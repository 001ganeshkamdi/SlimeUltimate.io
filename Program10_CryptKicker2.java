import java.util.*;

public class Program10_CryptKicker2 {
    static final String KEY = "the quick brown fox jumps over the lazy dog";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine().trim());
        sc.nextLine(); // skip blank line

        for (int t = 0; t < cases; t++) {
            if (t > 0) System.out.println();

            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) break;
                lines.add(line);
            }

            Map<Character, Character> decodeMap = null;
            for (String enc : lines) {
                if (enc.length() != KEY.length()) continue;
                Map<Character, Character> map = new HashMap<>();
                Map<Character, Character> rev = new HashMap<>();
                boolean valid = true;
                for (int i = 0; i < KEY.length(); i++) {
                    char k = KEY.charAt(i);
                    char e = enc.charAt(i);
                    if (k == ' ' && e != ' ' || k != ' ' && e == ' ') {
                        valid = false;
                        break;
                    }
                    if (k != ' ') {
                        if (map.containsKey(e)) {
                            if (map.get(e) != k) {
                                valid = false;
                                break;
                            }
                        } else {
                            map.put(e, k);
                        }
                        if (rev.containsKey(k)) {
                            if (rev.get(k) != e) {
                                valid = false;
                                break;
                            }
                        } else {
                            rev.put(k, e);
                        }
                    }
                }
                if (valid && map.size() == 26) {
                    decodeMap = map;
                    break;
                }
            }

            if (decodeMap == null) {
                System.out.println("No solution.");
            } else {
                for (String enc : lines) {
                    StringBuilder sb = new StringBuilder();
                    for (char ch : enc.toCharArray()) {
                        if (ch == ' ') sb.append(' ');
                        else sb.append(decodeMap.getOrDefault(ch, ch));
                    }
                    System.out.println(sb.toString());
                }
            }
        }
        sc.close();
    }
}