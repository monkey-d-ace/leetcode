package niuke;

import java.util.Scanner;

/**
 * 小镇广告牌
 * 题目描述：已知某小镇的房子沿直线分布，给定一个有序整数数组arr，里面的每个镇代表小镇每栋房子的一维坐标点。现在需要建N个广告牌，广告牌只能建立在这些坐标点上，使得每个坐标点离广告牌的总距离最短，求这个最短的总距离。
 *
 * 输入描述：输入最后一个为N值，其余的为arr值，需要考生自行处理。
 *
 * 例如：输入1 2 3 4 5 1000 2，输出6。
 */
public class TownGuanggao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] arr = scanner.nextLine().split(" ");
            int N = Integer.parseInt(arr[arr.length - 1]);
            int[][] dp = new int[arr.length][N];
        }
        scanner.close();
    }
}
