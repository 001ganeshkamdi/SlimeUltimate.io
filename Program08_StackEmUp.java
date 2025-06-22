import java.util.*;

public class Program08_StackEmUp {
    static String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    static List<String> generateDeck() {
        List<String> deck = new ArrayList<>();
        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " of " + suit);
            }
        }
        return deck;
    }

    static List<String> applyShuffle(List<String> deck, int[] shuffle) {
        List<String> newDeck = new ArrayList<>(Collections.nCopies(52, ""));
        for (int i = 0; i < 52; i++) {
            newDeck.set(i, deck.get(shuffle[i] - 1));
        }
        return newDeck;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine().trim());
        sc.nextLine(); // Skip blank line

        StringBuilder output = new StringBuilder();

        for (int c = 0; c < cases; c++) {
            if (c > 0) output.append("\n");
            int n = Integer.parseInt(sc.nextLine().trim());
            int[][] shuffles = new int[n][52];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 52; j++) {
                    shuffles[i][j] = sc.nextInt();
                }
            }
            sc.nextLine(); // Consume remaining newline

            List<String> deck = generateDeck();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) break;
                int k = Integer.parseInt(line) - 1;
                deck = applyShuffle(deck, shuffles[k]);
            }
            for (String card : deck) {
                output.append(card).append("\n");
            }
        }
        System.out.print(output.toString());
        sc.close();
    }
}