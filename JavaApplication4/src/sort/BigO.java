/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author tsch
 */
public class BigO {

    private static int N = 10000000;
    private static Random r = new Random();

    public static void main(String[] args) {
        Integer[] a1 = new Integer[N];
        Integer[] a2 = new Integer[N];
        Integer[] a3 = new Integer[N];

        initial(a1);

        System.arraycopy(a1, 0, a2, 0, a1.length);
        System.arraycopy(a1, 0, a3, 0, a1.length);

        System.out.println("");
        System.out.println("Counting Sort");
        long begin = System.currentTimeMillis();
        sort(a1);
        System.out.println("Duration: " + (System.currentTimeMillis() - begin) + "millseconds");
       

        System.out.println("");
        System.out.println("Arrays.sort");
        begin = System.currentTimeMillis();
        Arrays.sort(a2);
        System.out.println("Duration: " + (System.currentTimeMillis() - begin) + "millseconds");

        System.out.println("");
        System.out.println("Heap Sort");
        begin = System.currentTimeMillis();
        Arrays.sort(a3);
        System.out.println("Duration: " + (System.currentTimeMillis() - begin) + "millseconds");

        //System.out.print(Arrays.toString(a1));
    }

    private static void initial(Integer[] a1) {
        for (int i = 0; i < a1.length; i++) {
            a1[i] = r.nextInt(N);
        }
    }

    public static void buildheap(Integer[] arr) {

        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }
    }

    public static void heapify(Integer[] arr, Integer i, Integer size) {
        Integer left = 2 * i + 1;
        Integer right = 2 * i + 2;
        Integer max;
        if (left <= size && arr[left] > arr[i]) {
            max = left;
        } else {
            max = i;
        }

        if (right <= size && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            exchange(arr, i, max);
            heapify(arr, max, size);
        }
    }

    public static void exchange(Integer[] arr, Integer i, Integer j) {
        Integer t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static Integer[] heapSort(Integer[] arr) {

        buildheap(arr);
        Integer sizeOfHeap = arr.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            exchange(arr, 0, i);
            sizeOfHeap = sizeOfHeap - 1;
            heapify(arr, 0, sizeOfHeap);
        }
        return arr;
    }

    public static void sort(Integer[] arr) {
        int arrayLength = arr.length;
        if (arrayLength == 0) {
            return;
        }
        /**
         * find maximum and minimum values *
         */
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arrayLength; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int range = max - min + 1;

        int[] count = new int[range];
        /**
         * initialize the occurrence of each element in the count array *
         */
        for (int i = 0; i < arrayLength; i++) {
            count[arr[i] - min]++;
        }
        /**
         * calculate sum of indexes *
         */
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        /**
         * modify original array according to the sum count *
         */
        int j = 0;
        for (int i = 0; i < range; i++) {
            while (j < count[i]) {
                arr[j++] = i + min;
            }
        }
    }

}
