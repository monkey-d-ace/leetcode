package airbnb;

import java.util.Arrays;

/**
 * Input is a array represent how the height of water is at each position, the number of water will be poured, and the pour position. Print the land after all water are poured.
 *
 * Example: input land height int[]{5,4,2,1,3,2,2,1,0,1,4,3} The land is looks ike:
 *
 * +
 * ++        +
 * ++  +     ++
 * +++ +++   ++
 * ++++++++ +++
 * ++++++++++++
 * 012345678901
 * water quantity is 8 and pour at position 5. The land becomes:
 *
 * +
 * ++        +
 * ++www+    ++
 * +++w+++www++
 * ++++++++w+++
 * ++++++++++++
 * 012345678901
 */
public class WaterLand {
    public static void main(String[] args) {
        int[] waterLand = new int[]{1,2,3,4};
        WaterLand land = new WaterLand();
        land.pourWater(waterLand, 2, 2);
        System.out.println(Arrays.toString(waterLand));
    }

    public int[] pourWater(int[] heights, int water, int location) {
        int[] waters = new int[heights.length];

        while (water-- > 0) droplet: {
            for (int d = -1; d <= 1; d += 2) {
                int i = location, best = location;
                while (i + d >= 0 && i + d < heights.length && heights[i + d] + waters[i + d] <= heights[i] + waters[i]) {
                    if (heights[i + d] + waters[i + d] < heights[i] + waters[i]) {
                        best = i + d;
                    }
                    i += d;
                }
                if (heights[best] + waters[best] < heights[location] + waters[location]) {
                    waters[best]++;
                    break droplet;
                }
            }
            waters[location]++;
        }
        print(heights, waters);
        for (int i = 0; i < heights.length; i++) {
            waters[i] = waters[i] + heights[i];
        }
        System.out.println(Arrays.toString(waters));
        return waters;
    }
    /*
    public void pourWater(int[] heights, int location, int water) {
        int[] waters = new int[heights.length];
        int pourLocation;

        while (water > 0) {
            int left = location - 1;
            while (left >= 0) {
                if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
                    break;
                }
                left--;
            }
            if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
                pourLocation = left + 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            int right = location + 1;
            while (right < heights.length) {
                if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
                    break;
                }
                right++;
            }
            if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
                pourLocation = right - 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            pourLocation = location;
            waters[pourLocation]++;
            water--;
        }

        int[] results = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            results[i] = heights[i] + waters[i];
        }
        System.out.println(Arrays.toString(results));
        print(heights, waters);
    }*/

    private void print(int[] heights, int[] waters) {
        int n = heights.length;

        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }

        for (int height = maxHeight; height >= 0; height--) {
            for (int i = 0; i < n; i++) {
                if (height <= heights[i]) {
                    System.out.print("+");
                } else if (height > heights[i] && height <= heights[i] + waters[i]) {
                    System.out.print("W");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
