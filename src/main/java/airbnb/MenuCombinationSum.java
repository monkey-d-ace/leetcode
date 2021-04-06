package airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a menu (list of items prices), find all possible combinations of items that sum a particular value K. (A variation of the typical 2sum/Nsum questions).
 */
public class MenuCombinationSum {
    public static void main(String[] args) {
        MenuCombinationSum sum = new MenuCombinationSum();
        double[] prices = {10.02, 1.11, 2.22, 3.01, 4.02, 2.00, 5.03};
        List<List<Double>> combos = sum.getCombos(prices, 7.03);
        System.out.println(combos);
        System.out.println(combos.size());
    }
    private void search(List<List<Double>> res, int[] centsPrices, int start, int centsTarget,
                        List<Double> curCombo, double[] prices) {
        if (centsTarget == 0) {
            res.add(new ArrayList<>(curCombo));
            return;
        }

        for (int i = start; i < centsPrices.length; i++) {
            if (i > start && centsPrices[i] == centsPrices[i - 1]) {
                continue;
            }
            if (centsPrices[i] > centsTarget) {
                break;
            }
            curCombo.add(prices[i]);
            search(res, centsPrices, i + 1, centsTarget - centsPrices[i], curCombo, prices);
            curCombo.remove(curCombo.size() - 1);
        }
    }
    public List<List<Double>> getCombos(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        if (prices == null || prices.length == 0 || target <= 0) {
            return res;
        }

        int centsTarget = (int) Math.round(target * 100);
        Arrays.sort(prices);
        int[] centsPrices = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            centsPrices[i] = (int) Math.round(prices[i] * 100);
        }

        search(res, centsPrices, 0, centsTarget, new ArrayList<>(), prices);
        return res;
    }
}
