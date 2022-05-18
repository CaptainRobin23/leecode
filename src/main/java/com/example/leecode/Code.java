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
        int ans = 0;
        int max = 0;
        int onetonplus = 0;
        while (onetonplus < N) {
            if ((N - onetonplus) % (max + 1) == 0) {
                ans++;
            }
            max++;
            onetonplus += max;
        }
        System.out.println(ans);
    }

    // 最大值 数字重新排序  求最大值 10 9 ---910
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
    public static int eg7() {
        Scanner sc = new Scanner(System.in);
        // 1、输入正整数的和S和数列里数的个数N
        String source = sc.next();
        String target = sc.next();
        // write your code here
        if (source == null || target == null) {
            return -1;
        }
        int index1 = source.indexOf(target);
        return index1;
    }

    // 字符串解压 4fj7h
    public static void eg8() {
        Scanner sc = new Scanner(System.in);
        String str = "";
        while (sc.hasNext()) {
            str = sc.nextLine();
        }
        char[] ch = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            if (!(ch[i] >= 'a' && ch[i] <= 'z')) {
                System.out.print("!error");
                return;
            }
            int tarNum = 1;
            char tar = ch[i];
            while (i + 1 < ch.length && ch[i + 1] == ch[i]) {
                tarNum++;
                i++;
            }
            sb.append(tarNum);
            sb.append(tar);
        }
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1') {
                sb.deleteCharAt(i);
                i--;
            }
            if (sb.charAt(i) == '2') {
                sb.replace(i, i + 1, sb.charAt(i + 1) + "");
                i--;
            }
        }
        System.out.print(sb.toString());
    }

    // 数组合并
    public static void eg9() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s1 = sc.next().split(",");
        String[] s2 = sc.next().split(",");
        ArrayList<String> s3 = new ArrayList<String>();
        ArrayList<String> s4 = new ArrayList<String>();
        ArrayList<String> s5 = new ArrayList<String>();

        for (int i = 0; i < s1.length; i++) {
            s4.add(s1[i]);
        }

        for (int i = 0; i < s2.length; i++) {
            s5.add(s2[i]);
        }

        boolean b = true;
        while (b) {
            if (s4.size() > 0) {
                if (s4.size() >= n) {
                    for (int i = 0; i < n; i++) {
                        s3.add(s4.get(i));
                    }

                    Iterator i = s4.iterator();
                    int count = 0;
                    while (i.hasNext()) {
                        if (count >= n) {
                            break;
                        }
                        s4.remove(0);
                        count++;
                    }
                } else {
                    for (int i = 0; i < s4.size(); i++) {
                        s3.add(s4.get(i));
                    }

                    Iterator i = s4.iterator();
                    int count = 0;
                    int siz = s4.size();
                    while (i.hasNext()) {
                        if (count >= siz) {
                            break;
                        }
                        s4.remove(0);
                        count++;
                    }
                }
            }

            if (s5.size() > 0) {
                if (s5.size() >= n) {
                    for (int i = 0; i < n; i++) {
                        s3.add(s5.get(i));
                    }

                    Iterator i = s5.iterator();
                    int count = 0;
                    while (i.hasNext()) {
                        if (count >= n) {
                            break;
                        }
                        s5.remove(0);
                        count++;
                    }
                } else {
                    for (int i = 0; i < s5.size(); i++) {
                        s3.add(s5.get(i));
                    }

                    Iterator i = s4.iterator();
                    int count = 0;
                    int siz = s5.size();
                    while (i.hasNext()) {
                        if (count >= siz) {
                            break;
                        }
                        s5.remove(0);
                        count++;
                    }
                }
            }

            if (s4.size() == 0 && s5.size() == 0) {
                b = false;
            }
        }

        for (int i = 0; i < s3.size(); i++) {
            if (i == (s3.size() - 1))
                System.out.print(s3.get(i));
            else
                System.out.print(s3.get(i) + " ,");
        }
    }


}
