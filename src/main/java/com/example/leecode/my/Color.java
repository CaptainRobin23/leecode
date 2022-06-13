package com.example.leecode.my;

import java.util.Arrays;
import java.util.Scanner;
 
public class Color {
 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int[] array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = sc.nextInt();
			}
			// 先对数组进行排序，方便遍历
			Arrays.sort(array);
			// 记录最少需要的颜色数量
			int count = 0;
			int[] s = new int[n];
			for (int i = 0; i < n; i++) {
				if (s[i] > 0) {
					continue;
				} else {
					for (int j = i; j < n; j++) {
						if ((array[j] % array[i]) == 0) {
							s[j]++;
						}
					}
					count++;
				}
			}
			System.out.println(count);
		}
	}
}