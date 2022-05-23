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
        eg15();
        eg16(12);
        eg17();
        eg18();
        eg19();
        eg20();

    }

    static char[][] arr = null;
    private static void eg20() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            char[] ch = sc.nextLine().replaceAll(" ", "").toCharArray();
            int N = sc.nextInt();
            int M = sc.nextInt();
            arr = new char[N][M];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = sc.next().charAt(0);
                }
            }
            fun(arr, ch);
        }
    }

    static void fun(char[][] arr, char[] ch) {
        LinkedList<int[]> snake = new LinkedList<>();
        int[] start = new int[2];
        //找到起点
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'H') {
                    start[0] = i;
                    start[1] = j;
                    break;
                }
            }
        }
        //起点入队
        snake.add(start);
        //遍历操作列表
        for (int i = 0; i < ch.length; i++) {
            int flag = 0;
            if (ch[i] == 'U') {
                flag = 1;
                continue;
            } else if (ch[i] == 'D') {
                flag = -1;
                continue;
            } else if (ch[i] == 'L') {
                flag = -2;
                continue;
            } else if (ch[i] == 'R') {
                flag = 2;
                continue;
            } else if (ch[i] == 'G') {
                switch (flag) {
                    case 1:
                        start[0]++;
                        break;
                    case -1:
                        start[0]--;
                        break;
                    case 2:
                        start[1]++;
                        break;
                    case -2:
                        start[1]--;
                        break;
                }
                if (isEnd(snake, start)) {
                    break;
                }
                //吃到，只加不减
                if (arr[start[0]][start[1]] == 'F') {
                    snake.addFirst(start);
                } else {
                    //没吃到，加头去尾
                    snake.addFirst(start);
                    snake.removeLast();
                }
            }
        }
        System.out.println(snake.size());


    }

    static boolean isEnd(LinkedList<int[]> list, int[] a) {
        //撞墙了
        if (a[0] >= arr.length || a[1] >= arr[0].length)
            return true;
        //撞到自己了
        for (int[] i : list) {
            if (i[0] == a[0] && i[1] == a[1])
                return true;
        }
        return false;
    }


    // 机器人走迷宫
    public static void eg19() {
        // 0 是未踩过的。 1是墙。 2是踩过的。
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int[][] room = new int[x][y];
        int wall = in.nextInt();
        while (wall-- > 0) {
            int wallX = in.nextInt();
            int wallY = in.nextInt();
            room[wallX][wallY] = 1;
        }
        path(room, 0, 0, x - 1, y - 1);
        int badPath = 0; //陷阱
        int noWay = 0; // 不可达
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (room[i][j] == 9) {
                    badPath += 1;
                } else if (room[i][j] == 0) {
                    noWay += 1;
                }
            }
        }
        System.out.println(badPath + " " + noWay);
    }

    //不可达方格 : 机器人无法通过增加X Y值到的方格.走完还是0的代表不可达
    //陷阱方格 : 走到该位置不能正确走到终点的方格。 向前/向上不可达/同为陷阱方格则也标记为陷阱方格 9
    //走过的为2
    private static void path(int[][] room, int nextX, int nextY, int x, int y) {
        //判断是墙直接跳过
        if (room[nextX][nextY] == 1) {
            return;
        }
        if (room[nextX][nextY] != 0) {
            return;
        }
        if (nextX == x && nextY == y) {
            room[nextX][nextY] = 2;
            return;
        }
        if (nextX < x) {
            path(room, nextX + 1, nextY, x, y);
        }
        if (nextY < y) {
            path(room, nextX, nextY + 1, x, y);
        }

        //该点向上/向前均为不可达/陷阱方格则为陷阱方格
        if (nextX == x || nextY == y) {
            if (nextX == x && nextY < y && room[nextX][nextY + 1] != 2) {
                room[nextX][nextY] = 9;
            } else if (nextY == y && nextX < x && room[nextX + 1][nextY] != 2) {
                room[nextX][nextY] = 9;
            } else {
                room[nextX][nextY] = 2;
            }
        } else if (room[nextX + 1][nextY] != 2 && room[nextX][nextY + 1] != 2) {
            room[nextX][nextY] = 9;
        } else {
            room[nextX][nextY] = 2;
        }
        return;
    }

    // TLV解码
    public static void eg18() {
        Scanner in = new Scanner(System.in);
        String tag = in.nextLine();
        String[] tlv = in.nextLine().split(" ");
        for (int i = 0; i < tlv.length; ) {
            int length = Integer.parseInt(tlv[i + 2] + tlv[i + 1], 16);  // 将字符串的Length转为16进制，小端，需要反过来
            if (tag.equals(tlv[i])) {
                StringBuilder sb = new StringBuilder();
                for (int j = i + 3; j < i + 3 + length; j++) {
                    sb.append(tlv[j]).append(" ");
                }
                System.out.println(sb.toString());
                break;
            } else {
                i += length + 3;
            }
        }
    }

    // 用户调度 3个用户abc 去他们任务耗时总和最短  3*3行数  求和最小
    public static void eg17() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] res = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = sc.nextInt();
            }
        }
        int count = 0;
        int index = 0;
        int[] array = new int[3];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {

                for (int j = 0; j < 3; j++) {
                    array[j] = res[i][j];
                }
                Arrays.sort(array);
                for (int j = 0; j < 3; j++) {
                    if (array[0] == res[i][j]) {
                        //获得该用户选择的策略
                        index = j;
                    }
                }
                count = count + array[0];
            } else {
                //拿到该用户的三种资源消耗并将上一个用户选择的策略排除
                for (int j = 0; j < 3; j++) {
                    if (j != index) {
                        arrayList.add(res[i][j]);
                    }
                }
                if (arrayList.get(0) >= arrayList.get(1)) {
                    count = count + arrayList.get(1);
                    for (int j = 0; j < 3; j++) {
                        if (arrayList.get(1) == res[i][j]) {
                            //获得该用户选择的策略
                            index = j;
                        }
                    }
                } else {
                    count = count + arrayList.get(0);
                    for (int j = 0; j < 3; j++) {
                        if (arrayList.get(0) == res[i][j]) {
                            //获得该用户选择的策略
                            index = j;
                        }
                    }
                }
                arrayList.clear();
            }
        }
        System.out.println(count);
    }

    // a[n]的描述是a[n+1] 1 11 21
    public static String eg16(int n) {
        if (n == 0) {
            return "1";
        } else {
            String str = eg16(n - 1);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < str.length(); ) {
                int count = 1;
                char tmp = str.charAt(i);
                if (i == str.length() - 1) {
                    buffer.append(count);
                    buffer.append(tmp);
                    return buffer.toString();
                }
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(i) != str.charAt(j)) {
                        buffer.append(count);
                        buffer.append(tmp);
                        i = j;
                        break;
                    } else {
                        count++;
                        i++;
                        if (j == str.length() - 1) {
                            buffer.append(count);
                            buffer.append(tmp);
                            return buffer.toString();
                        }
                    }
                }
            }
            return buffer.toString();
        }
    }

    // 墓碑碎片  所有组合升序排列输出  abc。。。
    private static void eg15() {
        // todo
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
