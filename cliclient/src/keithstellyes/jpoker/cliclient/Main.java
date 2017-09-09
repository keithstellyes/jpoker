/**
 * A basic command line client for the keithstellyes.jpoker library
 */
package keithstellyes.jpoker.cliclient;

import keithstellyes.jpoker.logic.Deck;
import keithstellyes.jpoker.logic.Hand;
import keithstellyes.jpoker.logic.HandResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        while (true) {
            Deck deck = new Deck();
            deck.shuffle();

            Hand hand = deck.drawHand();
            System.out.println(hand);

            int[] integersToReplace = new int[0];
            try {
                integersToReplace = readNumbers();
            } catch(IOException e) {

            }
            for(int i : integersToReplace) {
                hand.set(i, deck.draw());
            }
            System.out.println(hand);
            System.out.println(HandResult.calculateHandResult(hand));
        }
    }

    private static String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    private static int[] readNumbers() throws IOException {
        String line = readLine();
        Scanner scanner = new Scanner(line);
        List<Integer> integerList = new ArrayList<>();
        while(scanner.hasNextInt()) {
            integerList.add(scanner.nextInt());
        }

        int[] integerArray = new int[integerList.size()];

        for(int i = 0; i < integerArray.length; i++) {
            integerArray[i] = integerList.get(i);
        }

        return integerArray;
    }
}
