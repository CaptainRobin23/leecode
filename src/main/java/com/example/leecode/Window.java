package com.example.leecode;

import java.util.Arrays;
import java.util.Scanner;
 
public class Window {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int[] array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = sc.nextInt();
			}
			// 生成一个新数组，记录窗口大小m。
			int m = sc.nextInt();
			int[] arrM = new int[n - m + 1];
			for (int i = 0; i < n - m + 1; i++) {
				arrM[i] = 0;
				for (int j = 0; j < m; j++) {
					arrM[i] += array[i + j];
				}
//				System.out.println(arrM[i]);
			}
			// 排序，取出最后一个最大的值
			Arrays.sort(arrM);
			System.out.println(arrM[n - m]);
 
		}
 
	}
 
}