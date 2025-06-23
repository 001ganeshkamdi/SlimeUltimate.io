import java.util.*;

public class Program06_Poker {
    static final String VALUES = "23456789TJQKA";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] cards = line.split("\\s+");
            Hand black = new Hand(Arrays.copyOfRange(cards, 0, 5));
            Hand white = new Hand(Arrays.copyOfRange(cards, 5, 10));
            int cmp = black.compareTo(white);
            if (cmp > 0) {
                System.out.println("Black wins.");
            } else if (cmp < 0) {
                System.out.println("White wins.");
            } else {
                System.out.println("Tie.");
            }
        }
    }

    static class Hand implements Comparable<Hand> {
        int[] valueCounts = new int[15]; // 2..14
        int[] suitCounts = new int[4];   // C D H S
        int[] values = new int[5];
        char[] suits = new char[5];
        int rank; // 0=high card, 1=pair, ..., 8=straight flush
        List<Integer> rankValues = new ArrayList<>();

        Hand(String[] cards) {
            for (int i = 0; i < 5; i++) {
                char v = cards[i].charAt(0);
                char s = cards[i].charAt(1);
                int vi = VALUES.indexOf(v) + 2;
                values[i] = vi;
                suits[i] = s;
                valueCounts[vi]++;
                suitCounts[suitIndex(s)]++;
            }
            Arrays.sort(values);
            analyze();
        }

        private int suitIndex(char s) {
            switch (s) {
                case 'C': return 0;
                case 'D': return 1;
                case 'H': return 2;
                case 'S': return 3;
            }
            return -1;
        }

        private void analyze() {
            boolean flush = false, straight = false;
            // Check flush
            for (int c : suitCounts) if (c == 5) flush = true;
            // Check straight
            int start = values[0];
            straight = true;
            for (int i = 1; i < 5; i++) {
                if (values[i] != start + i) straight = false;
            }
            // Special case: Ace-low straight (A 2 3 4 5)
            if (!straight && values[0] == 2 && values[1] == 3 && values[2] == 4 && values[3] == 5 && values[4] == 14) {
                straight = true;
                values[4] = 1; // treat Ace as 1 for comparison
                Arrays.sort(values);
            }

            // Count occurrences
            Map<Integer, Integer> countMap = new TreeMap<>(Collections.reverseOrder());
            for (int v = 14; v >= 2; v--) {
                if (valueCounts[v] > 0) {
                    countMap.put(v, valueCounts[v]);
                }
            }
            List<Integer> pairs = new ArrayList<>();
            int three = 0, four = 0;
            for (Map.Entry<Integer, Integer> e : countMap.entrySet()) {
                if (e.getValue() == 4) four = e.getKey();
                else if (e.getValue() == 3) three = e.getKey();
                else if (e.getValue() == 2) pairs.add(e.getKey());
            }

            if (straight && flush) {
                rank = 8;
                rankValues.add(values[4]);
            } else if (four > 0) {
                rank = 7;
                rankValues.add(four);
                for (int v : countMap.keySet()) if (v != four) rankValues.add(v);
            } else if (three > 0 && pairs.size() == 1) {
                rank = 6;
                rankValues.add(three);
                rankValues.add(pairs.get(0));
            } else if (flush) {
                rank = 5;
                for (int i = 4; i >= 0; i--) rankValues.add(values[i]);
            } else if (straight) {
                rank = 4;
                rankValues.add(values[4]);
            } else if (three > 0) {
                rank = 3;
                rankValues.add(three);
                for (int v : countMap.keySet()) if (v != three) rankValues.add(v);
            } else if (pairs.size() == 2) {
                rank = 2;
                Collections.sort(pairs, Collections.reverseOrder());
                rankValues.addAll(pairs);
                for (int v : countMap.keySet()) if (!pairs.contains(v)) rankValues.add(v);
            } else if (pairs.size() == 1) {
                rank = 1;
                rankValues.add(pairs.get(0));
                for (int v : countMap.keySet()) if (v != pairs.get(0)) rankValues.add(v);
            } else {
                rank = 0;
                for (int i = 4; i >= 0; i--) rankValues.add(values[i]);
            }
        }

        @Override
        public int compareTo(Hand o) {
            if (this.rank != o.rank) return Integer.compare(this.rank, o.rank);
            for (int i = 0; i < this.rankValues.size(); i++) {
                if (i >= o.rankValues.size()) return 1;
                int cmp = Integer.compare(this.rankValues.get(i), o.rankValues.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        }
    }
}    
