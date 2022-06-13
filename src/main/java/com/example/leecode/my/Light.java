package com.example.leecode.my;

import java.util.*;

public class Light {
    public static void huaweitmp01(){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] lights = new int[N];

        for (int i = 0; i < N; i++) {
            lights[i] = sc.nextInt();
        }


        long res = 0;

        ArrayList<int[]> arrayList = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            int[] tmpArr = new int[2];

            tmpArr[0] = i * 100 - lights[i];
            tmpArr[1] = i * 100 + lights[i];

            arrayList.add(tmpArr);
        }



        arrayList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] >= o2[0]){
                    return 1;
                }else{
                    return -1;
                }

            }
        });


        int leftEdge = 0, rightEdge = arrayList.get(0)[1];
        int LEFT = 0, RIGHT = (N-1) * 100;


        for (int i = 1; i < arrayList.size(); i++) {

            int[] tmp = arrayList.get(i);

            if(tmp[0] > rightEdge){
                res += tmp[0] - rightEdge;
            }

            if(tmp[0] == tmp[1])

                leftEdge = Math.min(leftEdge , tmp[1] == tmp[0] ? Integer.MAX_VALUE : tmp[0]);
            rightEdge = Math.max(rightEdge, tmp[1] == tmp[0] ? Integer.MIN_VALUE : tmp[1]);

            leftEdge = leftEdge < LEFT ? LEFT : leftEdge;
            rightEdge = rightEdge > RIGHT ? RIGHT : rightEdge;
        }

        System.out.println(res);

    }
}
