package com.example.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 单词接龙
 */
public class Test14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(scanner.next());
        }
        String word = words.remove(k);
        List<String> longWords = new ArrayList<>();
        while (word != null) {
            longWords.add(word);
            word = getNextWords(word, words);
        }
        System.out.println(String.join("", longWords));
    }
    private static String getNextWords(String startWord, List<String> words) {
        if (words == null || words.isEmpty()) {
            return null;
        }
        String result = null;
        String next;
        int index = -1;
        for (int i = 0; i < words.size(); i++) {
            next = words.get(i);// 可用于接龙的单词 首字母必须要与前一个单词的尾字母相同
            if (startWord.charAt(startWord.length() - 1) == next.charAt(0)) {
                if (result == null) {
                    result = next;
                    index = i;
                } else {
                    if (next.length() > result.length()) {//当存在多个首字母相同的单词时，取长度最长的单词
                        result = next;
                        index = i;
                    } else if (next.length() == result.length()) { //如果长度也相等，则取字典序最小的单词
                        if (next.compareTo(result) < 0) {
                            result = next;
                            index = i;
                        }
                    }
                }
            }
        }
        if (index == -1) {
            return null;
        }
        return words.remove(index);
    }
}