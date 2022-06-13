package com.example.leecode; /**
 * @ClassName Main
 * @Description TODO
 * @Author ylqdh
 * @Date 2020/4/7
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        System.out.println(eg9(4));
//        eg9();
//        eg19();
        eg20();

    }

    private static void eg20() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("00*0"); // 匹配出方波信号之间的间隔点
        Matcher matcher = pattern.matcher(line);
        String replace_line = matcher.replaceAll("0 0"); // 替换为空格隔开
        String[] strings = replace_line.split(" "); // 切割
        int len = 0;
        String lenStr = "";
        for (String string : strings) {
            boolean b = string.matches("0(10)+"); // 判断得到的方波信号是否为完全连续交替方波信号
            if (b && string.length() > len) {
                len = strings.length;
                lenStr = string;
            }
        }
        System.out.println(lenStr.equals("") ? -1 : lenStr);
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

    public static void eg9() {
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
}
