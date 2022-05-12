package com.example.leecode;

import java.util.*;
import java.util.Scanner;

public class Code10 {
    public static void main(String[] args) {
        eg10();
        eg11();
        eg12();
        eg13();
        eg14();
    }

    // 分配内存池
    public static void eg14() {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(",");
        String[] asks = sc.nextLine().split(",");
        //用treeMap将大小和数量对应起来，并按大小排序
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (String s : split) {
            Integer size = Integer.parseInt(s.split(":")[0]);
            Integer num = Integer.parseInt(s.split(":")[1]);
            map.put(size, num);
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>(map.keySet()); //有参构造！！
        for (String s : asks) {
            int flag = 0;
            for (int i : list) {
                int ask = Integer.parseInt(s);
                if (ask <= i && map.get(i) > 0) {
                    sb.append("true").append(",");
                    map.put(i, map.get(i) - 1); //更新内存池
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                sb.append("false").append(",");
            }
        }
        System.out.println(sb.toString().substring(0, sb.length() - 1));
    }

    // 找车位 1000111
    public static void eg13() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strs = str.split(",");
        int seats[] = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            seats[i] = Integer.parseInt(strs[i]);
        }
        int prev = -1;
        int max = 1;
        for (int i = 0; i < seats.length; i++) {
            //遇到1时计算有多少个0
            if (seats[i] == 1) {
                //前面遇到过1，要除以2
                if (prev >= 0) max = Math.max((i - prev) / 2, max);
                    //前面没有1不需要除2
                else max = i;
                prev = i;
            }
        }
        //最后的0个数再判断一次
        max = Math.max((seats.length - 1) - prev, max);
        System.out.println(max);
        ;
    }

    // 勾股数元组（素勾股数）  abc互质
    private static void eg12() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<int[]> res = new ArrayList<>();
        for (int a = n; a <= m - 2; ++a) {
            for (int b = a + 1; b <= m - 1; ++b) {
                //求c
                double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                long cz = (long) c;
                if (c - cz == 0 && c <= m && isPrim(a, b) && isPrim(a, (int) c) && isPrim(b, (int) c)) {
                    res.add(new int[]{a, b, (int) c});
                } else if (c > m) {
                    break;
                }
            }
        }
        res.forEach(res1 -> {
            System.out.println(res1[0] + " " + res1[1] + " " + res1[2]);
        });
    }

    //判断a,b,c互质
    public static boolean isPrim(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c;
        //辗转相除
        while ((c = a % b) != 0) {
            a = b;
            b = c;
        }
        return b == 1;
    }


    // 寻找子串 t和p p第一次出现的位置
    private static void eg11() {
        Scanner sc = new Scanner(System.in);
        String target = sc.next();
        String source = sc.next();
        // write your code here
        if (source == null || target == null) {
            System.out.println("OK");
        }
        int index1 = source.indexOf(target);
        System.out.println(index1);
    }

    // 打印任务排序1-9
    private static void eg10() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            String[] strs = str.split(",");
            // 数组有可能为零
            if (strs.length > 0) {
                Integer[] ints = new Integer[strs.length];
                int vi[] = new int[ints.length];
                for (int i = 0; i < strs.length; i++) {
                    ints[i] = Integer.parseInt(strs[i]);// 无序的数组

                }
                //降序排序，9 3 3 5
                Arrays.sort(ints, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer arg0, Integer arg1) {
                        // TODO Auto-generated method stub
                        return arg1 - arg0;
                    }

                });
                StringBuilder sb = new StringBuilder();
                // 输出所在的坐标
                for (int j = 0; j < strs.length; j++) {
                    int c = Integer.parseInt(strs[j]);
                    int k = 0;
                    while (k < ints.length) {
                        if (c == ints[k] && vi[k] == 0) {
                            vi[k] = 1;
                            break;
                        }
                        k++;
                    }
                    sb.append(k + ",");
                }
                System.out.println(sb.substring(0, sb.length() - 1));
            }
        }
    }


}
