package com.example.leecode;

import java.util.*;

public class Code {
    public static void main(String[] args) {
//        eg1();
//        eg2_numbersum();
        eg3_bignum();
        eg4();
        eg5();
        eg7();
        eg8();
        eg9();
    }

    // 求连续数列 525 6  --》85 86 87。。。90 不存在 返回-1
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
        } else a1 = (2 * s / n + 1 - n) / 2;

        for (int i = 0; i < n; i++) {
            list.add(a1 + i);
        }
        System.out.println(list);
    }

    // 求那些连续自然数之和 等于N
    public static void eg2_numbersum() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Stack<String> str = new Stack<>();
        for (int j = 1; j <= N; j++) {
            int sum = 0;
            String ansEnv = "";
            int i = j;
            while (i <= N) {
                sum += i;
                ansEnv += i + "+";
                if (sum == N) {
                    ansEnv = N + "=" + ansEnv;
                    str.push(ansEnv.substring(0, ansEnv.length() - 1));
                }
                if (sum > N) {
                    break;
                }
                i++;
            }
        }
        int size = str.size();
        while (!str.isEmpty()) {
            System.out.println(str.pop());
        }
        System.out.println("Reuslt:" + size);
    }

    // 最大值 数字重新排序  求最大值 10 9 ---910
    private static void eg3_bignum() {
        Scanner sc = new Scanner(System.in);
        String numsStr = sc.nextLine();
        String[] numStr = numsStr.split(" ");
        Arrays.sort(numStr, (k1, k2) -> {
            String ab = k1 + k2;
            String ba = k2 + k1;
            return ba.compareTo(ab);
        });
        StringBuilder sb = new StringBuilder();
        for (String s : numStr) sb.append(s);
        int len = numStr.length;
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') k++;
        System.out.println(sb.substring(k));
    }


    // 输出字符串中包含所有整数的最小和
    public static void eg4() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] ch = str.toCharArray();
        int sum = 0;
        boolean flag = false; // 负数
        StringBuilder sb = new StringBuilder();
        for (char c : ch) {
            if (c >= '0' && c <= '9') {  // 如果是数字
                if (flag) {  // 如果是负号后面的数字，加到sb中
                    sb.append(c);
                } else {  // 正数直接求和
                    sum += Integer.parseInt(c + "");
                }
            } else if ('-' == c) {  // 如果是'-'
                if (flag) {
                    if (!sb.toString().isEmpty()) {  // 遇到负号后，将负号后面的连续数字组成字符串
                        sum -= Integer.parseInt(sb.toString());  // 如果sb不空
                        sb = new StringBuilder();
                    }
                }
                flag = true;
            } else {  // 其它字符
                flag = false;  // 用来判断负号后的字符串结束
                if (!sb.toString().isEmpty()) {  // 队列不空，说明负号字符串到此为止，将去整体
                    sum -= Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        if (flag) {
            if (!sb.toString().isEmpty()) {
                sum -= Integer.parseInt(sb.toString());
            }
        }
        System.out.print(sum);
    }


    // 查找字符串中第k个最小ascii码值的字母(k>=1)
    public static void eg5() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], i);
            }
        }
        Arrays.sort(chars);
        if (k > s.length()) System.out.println(map.get(chars[s.length() - 1]));
        System.out.println(map.get(chars[k - 1]));
    }


    // 二阶矩阵求最长直线距离 F M
    public int eg6(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int ans = 0;
        int[][] horizontal = new int[M.length][M[0].length];
        int[][] vertical = new int[M.length][M[0].length];
        int[][] diagonal = new int[M.length][M[0].length];
        int[][] antidiagonal = new int[M.length][M[0].length];
        for (int i = 0; i != M.length; ++i) {
            for (int j = 0; j != M[0].length; ++j) {
                if (M[i][j] == 0) {
                    horizontal[i][j] = 0;
                    vertical[i][j] = 0;
                    diagonal[i][j] = 0;
                    antidiagonal[i][j] = 0;
                } else {
                    horizontal[i][j] = j > 0 ? horizontal[i][j - 1] + 1 : 1;
                    vertical[i][j] = i > 0 ? vertical[i - 1][j] + 1 : 1;
                    diagonal[i][j] = i > 0 && j > 0 ? diagonal[i - 1][j - 1] + 1 : 1;
                    antidiagonal[i][j] = i > 0 && j < M[0].length - 1 ? antidiagonal[i - 1][j + 1] + 1 : 1;
                    ans = Math.max(ans, horizontal[i][j]);
                    ans = Math.max(ans, vertical[i][j]);
                    ans = Math.max(ans, diagonal[i][j]);
                    ans = Math.max(ans, antidiagonal[i][j]);
                }
            }
        }
        return ans;
    }

    // 字符串子序列 下标
    public static void eg7() {
        Scanner sc = new Scanner(System.in);
        String t = sc.nextLine();    //target
        String s = sc.nextLine();    //source
        int ti = t.length() - 1;
        int si = s.length() - 1;
        while (ti >= 0 && si >= 0) {
            if (t.charAt(ti) == s.charAt(si)) {
                if (ti == 0) {
                    System.out.println(si);
                    return;
                }
                ti--;
            }
            si--;
        }
        System.out.println(-1);
    }

    // 字符串解压 4fj7h
    public static void eg8() {
        Scanner sc = new Scanner(System.in);
        String str = "";
        str = sc.nextLine();
        char[] ch = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i] + "").matches("[0-9]")) {
                count = count * 10 + Integer.parseInt(ch[i] + "");
            } else if ((ch[i] + "").matches("[a-zA-Z]")) {
                if (count == 0) {
                    sb.append(ch[i] + "");
                }
                for (int j = 0; j < count; j++) {
                    sb.append(ch[i]);
                }
                count = 0;
            }
        }
        System.out.print(sb.toString());
    }

    // 数组合并
    public static void eg9() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        ArrayList<List<String>> lists = new ArrayList<>();
        int count = 0;
        while (count < m) {
            String s = scanner.nextLine();
            count++;
            if ("".equals(s)) {
                break;
            }
            lists.add(Arrays.asList(s.split(",")));
        }
        scanner.close();
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; counter != n; i++) {
            for (List<String> list : lists) {
                int start = i * n;
                int end = Math.min(start + n, list.size());
                if (start >= list.size()) {
                    counter++;
                    break;
                }
                for (int j = start; j < end; j++) {
                    sb.append(list.get(j)).append(",");
                }
            }
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }


}
