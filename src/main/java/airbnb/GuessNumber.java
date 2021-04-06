package airbnb;

import java.util.*;

/**
 * The system has a secret four digits number, in which each digit is in range of one to 6 [1, 6].
 *
 * Given a four digital number, the system also provide a API that returns the number of correct matched digits.
 *
 * Design a method to guess this secret number.
 */
public class GuessNumber {
    public static void main(String[] args) {
        String guess = "2345";
        GuessNumber guessNumber = new GuessNumber(guess);
        System.out.println(guessNumber.guess());

        guess = "3456";
        guessNumber = new GuessNumber(guess);
        System.out.println(guessNumber.guess());
    }
    String target;
    public GuessNumber(String target) {
        this.target = target;
    }

    private int guessServer(String guess) {
        int res = 0;
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : target.toCharArray())
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        Map<Character, Integer> guessMap = new HashMap<>();
        for (char c : guess.toCharArray())
            guessMap.put(c, guessMap.getOrDefault(c, 0) + 1);
        for (char k : guessMap.keySet()) {
            if (targetMap.containsKey(k))
                res += Math.min(guessMap.get(k), targetMap.get(k));
        }
        return res;
    }

    private String genNumber(List<Integer> guessed, int c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < guessed.size(); i++) {
            stringBuilder.append(guessed.get(i));
        }
        for (int i = guessed.size(); i < 4; i++) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private String genNumber(List<Integer> guessed) {
        if (guessed == null || guessed.size() == 0) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < guessed.size(); i++) {
            stringBuilder.append(guessed.get(i));
        }
        return stringBuilder.toString();
    }

    public String guess() {
        List<Integer> res = new ArrayList<>();
        List<Integer> cands = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};

        int counter = 0;
        Iterator<Integer> iterator = cands.iterator();
        while (iterator.hasNext() && res.size() < 4) {
            int cand = iterator.next();
            counter++;
            int guessedCount = res.size();
            String guessCand = genNumber(res, cand);
            int guessRes = guessServer(guessCand);
            if (guessRes == guessedCount) {
                iterator.remove();
            } else if (guessRes > guessedCount) {
                for (int i = guessedCount; i < guessRes; i++) {
                    res.add(cand);
                }
                iterator.remove();
            } else {
                return genNumber(res);
            }
        }
        if (res.size() < 4) {
            for (int i = res.size(); i < 4; i++) {
                res.add(6);
            }
        }
        return genNumber(res);
    }
}
