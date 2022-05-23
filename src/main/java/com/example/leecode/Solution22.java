package com.example.leecode;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 内存分配
 */
class Solution22 {
    static class AllocatedMemory {
        private TreeMap<Integer, Integer> hasAllocated;
        private int ADDRESS_DEFAULT_HEAD = 0;
        private int ADDRESS_DEFAULT_END = 100;
        AllocatedMemory() {
            hasAllocated = new TreeMap<>();
        }

        String request(int size) {
            int addressHead = ADDRESS_DEFAULT_HEAD;
            if (size <= 0 || size > 100) {
                return "error";
            }
            if (hasAllocated.isEmpty()) {
                hasAllocated.put(ADDRESS_DEFAULT_HEAD, size);
            } else {
                List<Integer> headList = new ArrayList<>(hasAllocated.keySet());
                for (int i=0; i<headList.size(); i++) {
                    if (headList.get(i) - addressHead >= size) {
                        hasAllocated.put(addressHead, addressHead + size);
                    } else {
                        addressHead = hasAllocated.get(headList.get(i));
                    }
                }
                if (size <= ADDRESS_DEFAULT_END - addressHead) {
                    hasAllocated.put(addressHead, addressHead + size);
                } else {
                    return "error";
                }
            }
            return String.valueOf(addressHead);
        }

        boolean release(int startAddress) {
            if (hasAllocated.containsKey(startAddress)) {
                hasAllocated.remove(startAddress);
                return true;
            }
            return false;
        }

        public static void main(String[] args) {
            Solution22.AllocatedMemory allocatedMemory =
                    new Solution22.AllocatedMemory();
            Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
            int line = Integer.parseInt(cin.nextLine());
            String[][] ins = new String[line][2];
            for (int i=0; i<line; i++) {
                ins[i] = cin.nextLine().split("=");
                if (ins[i][0].startsWith("REQUEST")) {
                    System.out.println(allocatedMemory.request(Integer.parseInt(ins[i][1])));
                } else {
                    boolean ret =
                            allocatedMemory.release(Integer.parseInt(ins[i][1]));
                    if (!ret) {
                        System.out.println("error");
                    }
                }
            }

            cin.close();
        }
    }
}