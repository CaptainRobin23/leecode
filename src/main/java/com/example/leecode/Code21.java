package com.example.leecode;

import java.util.*;

public class Code21 {
    public static void main(String[] args) {
        eg21();

    }

    private static void eg21() {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int x = sc.nextInt();
        int[] arr = new int[c];
        for (int i = 0; i < c; i++) {
            arr[i] = sc.nextInt();
        }
        long sum = 0, count = 0;
        int j = 0;
        for (int i = 0; i < c; i++) {
            sum += arr[i];
            while (sum >= x && j <= i) {
                count += c - i;
                sum -= arr[j];
                j++;
            }
        }
        System.out.println(count);
    }


}
