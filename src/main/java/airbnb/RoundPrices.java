package airbnb;

import java.util.*;

/**
 * Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). We want to find a way to round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T where yi = Floor(xi) or Ceil(xi), ceiling or floor of xi.
 *
 * We also want to minimize sum |x_i-y_i|
 */
public class RoundPrices {
    public static void main(String[] args) {
        RoundPrices roundPrices = new RoundPrices();
        double[] arr = {1.2, 3.7, 2.3, 4.8};
        int[] res = roundPrices.roundUp(arr);
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(res[2]);
        System.out.println(res[3]);
        arr = new double[] {30.3, 2.4, 3.5};
        res = roundPrices.roundUp(arr);
        System.out.println(Arrays.toString(res));
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(res[2]);
    }

    public int[] roundUp(double[] arr) {
        double doubleSum = 0.0;
        int floorSum = 0;
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            double d = arr[i];
            doubleSum += d;
            floorSum += Math.floor(d);
            Pair p = new Pair(i, d - Math.floor(d));
            list.add(p);
        }
        int numToUp = (int) Math.round(doubleSum) - floorSum;

        Collections.sort(list, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (Double.compare(o1.diff, o2.diff)) * (-1);
            }
        });
        int[] res = new int[arr.length];
        for (int i = 0; i < numToUp; i++) {
            Pair p = list.get(i);
            res[p.idx] = (int)Math.ceil(arr[p.idx]);
        }
        for (int i = numToUp; i < arr.length; i++) {
            Pair p = list.get(i);
            res[p.idx] = (int)Math.floor(arr[p.idx]);
        }
        return res;
    }

    class Pair {
        int idx;
        double diff;

        public Pair(int index, double dif) {
            this.diff = dif;
            this.idx = index;
        }
    }
}
