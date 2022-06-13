package com.example.leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @since 2022年4月18日
 */
public class DivideClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String teamStr = scanner.nextLine();
        String[] childs = teamStr.split(" ");
        if (childs.length > 999) {
            System.out.println("ERROR");
        }
        ArrayList<String> classA = new ArrayList<>();
        ArrayList<String> classB = new ArrayList<>();
        String classNum = "";
        for (int i = 0; i < childs.length; i++) {
            if (i == 0) {
                String first = childs[0];
                classA.add(first.split("/")[0]);
                classNum = "A";
                continue;
            }
            String follow = childs[i];
            boolean isSameClass = follow.endsWith("Y");
            String stu = follow.split("/")[0];
            if ("A".equals(classNum)) {
                if (isSameClass) {
                    classA.add(stu);
                } else {
                    classB.add(stu);
                }
                classNum = isSameClass ? "A" : "B";
            } else {
                if (isSameClass) {
                    classB.add(stu);
                } else {
                    classA.add(stu);
                }
                classNum = isSameClass ? "B" : "A";
            }
        }
        classA.sort(Comparator.comparingInt(Integer::parseInt));
        classB.sort(Comparator.comparingInt(Integer::parseInt));
        System.out.println(classA.toString().replace("[", "").replace("]", "").replace(",", " "));
        System.out.println(classB.toString().replace("[", "").replace("]", "").replace(",", " "));
    }
}