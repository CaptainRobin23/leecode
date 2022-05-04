package com.example.leecode;

import java.util.*;

public class Code {
    public static void main(String[] args) {
//        eg1();
//        eg2_numbersum();
        eg3_bignum();
    }

    private static void eg3_bignum() {
        Scanner sc = new Scanner(System.in);
        String numsStr = sc.nextLine();
//        int[] nums = Arrays.(numsStr.split(" "));
        // write code here
//        int n = nums.length;
        String[] numStr = numsStr.split(" ");
//        String[] numStr = new String[n];
//        for (int i = 0; i < n; i++) {
//            numStr[i] = "" + nums[i];
//        }
        Arrays.sort(numStr,(k1, k2)-> {
            String ab = k1+ k2;
            String ba = k2+k1;
            return ba.compareTo(ab);
        });
        StringBuilder sb = new StringBuilder();
        for (String s : numStr) sb.append(s);
        int len = numStr.length;
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') k++;
        System.out.println(sb.substring(k));
    }

    private static void eg1() {
        Scanner sc = new Scanner(System.in);
// 1、输入正整数的和S和数列里数的个数N
        int s = sc.nextInt();
        int n = sc.nextInt();

        int a1 = 1;
        List list = new ArrayList();
        if (2 * s % n != 0) {
            System.out.println("无解,返回：" + -1);
            return;
        } else if ((2 * s / n - n) % 2 == 0) {
            System.out.println("无解,返回：" + -1);
            return;
        } else
            a1 = (2 * s / n + 1 - n) / 2;

        for (int i = 0; i < n; i++) {
            list.add(a1 + i);
        }
        System.out.println(list);
    }

    public static void eg2_numbersum() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int ans=0;
        int max=0;
        int onetonplus=0;
        while(onetonplus<N){
            if((N-onetonplus)%(max+1)==0){ans++;}
            max++;
            onetonplus+=max;
        }
        System.out.println(ans);
    }
}
