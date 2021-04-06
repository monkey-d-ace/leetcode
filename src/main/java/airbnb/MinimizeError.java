package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Was asked in airbnb phone screen
 *
 * When you book on airbnb the total price is:
 *
 * Total price = base price + service fee + cleaning fee + ...
 *
 * input : array of decimals ~ X
 * output : array of int ~ Y
 *
 * But they need to satisfy the condition:
 *
 * sum(Y) = round(sum(x))
 * minmize (|y1-x1| + |y2-x2| + ... + |yn-xn|)
 * Example1:
 * input = 30.3, 2.4, 3.5
 * output = 30 2 4
 *
 * Example2:
 * input = 30.9, 2.4, 3.9
 * output = 31 2 4
 */
public class MinimizeError {
    public static void main(String[] args) {
        MinimizeError error = new MinimizeError();
        float input1[] = { 2.4f, 3.3f, 3.9f, 20.1f, 30.3f };
        float input2[] = { 2.4f, 3.4f, 3.4f, 20.4f, 30.4f };
        float input3[] = { 2.5f, 3.5f, 3.5f, 20.5f, 30.5f };
        int[] output = error.minimize(input3);
        for (int val : output) {
            System.out.println(val);
        }
    }
    class RoundedNumber {
        float input, res;
        int index, output;

        public RoundedNumber(int index, float input, int output, float res) {
            this.index = index;
            this.input = input;
            this.output = output;
            this.res = res;
        }
    }
    private int[] minimize(float[] input) {
        if (null == input) {
            return new int[-1];
        }

        int len = input.length;
        int output[] = new int[len];

        if (len == 1) {
            output[0] = Math.round(input[0]);
            return output;
        }

        List<RoundedNumber> list = new ArrayList<>();

        float sumInputFloat = 0.0f;
        for (float val : input) {
            sumInputFloat += val;
        }

        int sumInput = Math.round(sumInputFloat);

        int sumOutput = 0;
        for (int index = 0; index < len; index++) {
            output[index] = Math.round(input[index]);
            sumOutput += output[index];

            list.add(new RoundedNumber(index, input[index], output[index], Math.abs(input[index] - output[index])));
        }

        int res = sumInput - sumOutput;

        Collections.sort(list, new Comparator<RoundedNumber>() {
            @Override
            public int compare(RoundedNumber r1, RoundedNumber r2) {
                if (r1.res > r2.res) {
                    return 1;
                } else if (r1.res < r2.res) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (RoundedNumber val : list) {
            // System.out.print(val.input + " ");
        }

        int start = 0, end = len - 1;
        while (res > 0 && end >= 0) {
            int val = list.get(end).output + 1;
            float valRes = Math.abs(val - list.get(end).input);

            if (valRes >= 0 && valRes <= 1) {
                list.set(end, new RoundedNumber(list.get(end).index, list.get(end).input, val, list.get(end).res));
                --res;
            }

            --end;
        }

        while (res < 0 && start < len) {
            int val = list.get(start).output - 1;
            float valRes = Math.abs(val - list.get(start).input);

            if (valRes >= 0 && valRes <= 1) {
                list.set(start,
                        new RoundedNumber(list.get(start).index, list.get(start).input, val, list.get(start).res));
                ++res;
            }

            ++start;
        }

        for (RoundedNumber rNum : list) {
            output[rNum.index] = rNum.output;
        }

        return output;
    }
}
