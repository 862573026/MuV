package com.newx.muv.utils;

/**
 * Created by xuzhijian on 2018/5/23 0023.
 */

public class BinarySearch {

    public static int binarySearch(int[] array, int start, int end, int value) {

        int middle = (start + end) / 2;

        if (end < start) {
            return -1;
        }

        if (end == (start + 1)) {
            if (array[start] == value) {
                return start;
            }
            else if(array[end] == value) {
                return end;
            }

        } else if(array[middle] == value) {
            return middle;
        }
        else if(value > array[middle]) {
            return binarySearch(array, middle + 1, end, value);
        }
        else if(value < array[middle]) {
            return binarySearch(array, start, middle - 1, value);
        }

        return -1;
    }


}
