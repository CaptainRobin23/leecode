package com.example.leecode;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class FindWords {
    //存放路径
    public static List<Path> path = new ArrayList<>();
    //字符串存储
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            String[][] strArray = new String[n][n];
            //接收字符矩阵
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(",");
                for (int j = 0; j < n; j++) {
                    strArray[i][j] = input[j];
                }
            }
            //目标字符串
            String temp = br.readLine();
            //用来标记路径是否遍历过
            int[][] resMap = new int[n][n];
            for (int i = 0; i < resMap.length; i++) {
                Arrays.fill(resMap[i], 0);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //找到第一个符合的字符时，开始遍历搜索
                    if (strArray[i][j].equals(String.valueOf(temp.charAt(0)))) {
                        dfs(strArray, i, j, temp, resMap);
                    }
                }
            }
            StringBuilder strSb = new StringBuilder();
            for (Path p : path) {
                strSb.append(p.x).append(",").append(p.y).append(",");
            }
            if (strSb.length() > 0) {
                strSb.deleteCharAt(strSb.length() - 1);
            }
            System.out.println(strSb.toString());
        }
    }

    private static boolean dfs(String[][] map, int x, int y, String temp, int[][] resMap) {
        int M = map.length;
        int N = map[0].length;
        //标记已走过
        resMap[x][y] = 1;
        String s = String.valueOf(temp.charAt(sb.length()));
        //当前坐标 等于 下一个字符串
        if (map[x][y].equals(s)) {
            path.add(new Path(x, y));
            sb.append(map[x][y]);
        }
        //已经找到对应的路径，一直往上一步返回
        if (sb.length() == temp.length()) {
            return true;
        }
        //向上坐标走，如果上面的字符为下一个要取的字符
        if (x > 0 && resMap[x - 1][y] == 0 && map[x - 1][y].equals(String.valueOf(temp.charAt(sb.length())))) {
            if (dfs(map, x - 1, y, temp, resMap)) return true;
        }
        if (x + 1 < M && resMap[x + 1][y] == 0 && map[x + 1][y].equals(String.valueOf(temp.charAt(sb.length())))) {
            if (dfs(map, x + 1, y, temp, resMap)) return true;
        }
        if (y > 0 && resMap[x][y - 1] == 0 && map[x][y - 1].equals(String.valueOf(temp.charAt(sb.length())))) {
            if (dfs(map, x, y - 1, temp, resMap)) return true;
        }
        if (y + 1 < N && resMap[x][y + 1] == 0 && map[x][y + 1].equals(String.valueOf(temp.charAt(sb.length())))) {
            if (dfs(map, x, y + 1, temp, resMap)) return true;
        }
        //走不下去了，回溯。
        //将该点标记为未走过
        resMap[x][y] = 0;
        //移除该点（因为走不下去了，该点不符合）
        path.remove(path.size() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return false;
    }
}

class Path {
    int x;
    int y;

    public Path(int x, int y) {
        this.x = x;
        this.y = y;
    }
}