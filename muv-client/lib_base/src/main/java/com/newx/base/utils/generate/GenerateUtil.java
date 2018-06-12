package com.newx.base.utils.generate;

import android.os.Build;
import android.view.View;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuzhijian on 2018/4/18 0018.
 * 自动生成类
 * ViewId - Int - String
 */

public class GenerateUtil {

    // ------------------------------ generate Id start ------------------------------
    private static final AtomicInteger nextGenerateId = new AtomicInteger(1);

    /**
     * 生成 ViewId
     * @return
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < 17) {
            for (; ; ) {
                final int result = nextGenerateId.get();
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) {
                    newValue = 1;
                }
                if (result != 0 && nextGenerateId.compareAndSet(result, newValue)) { //View layoutId不能为0
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }
    // ------------------------------ generate Id end ------------------------------


    // ------------------------------ generate Number start ------------------------------

    private static Random random = new Random();

    /**
     * 生成随机 Integer
     * @return
     */
    public static int generateRandomNumber() {
        return random.nextInt();
    }

    /**
     * 生成随机 正数
     * @return
     */
    public static int generateRandomPositiveNumber() {
        return Math.abs(random.nextInt());
    }

    /**
     * 生成 0 - n
     * @param max
     * @return
     */
    public static int generateRandomNumber(int max) {
        return random.nextInt(max);
    }

    /**
     * 生成 min - max
     * @param min
     * @param max
     * @return
     */
    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 生成 Integer 数组
     * @param num
     * @return
     */
    public static int[] generateRandomNumbers(int num){
        int[] numbers = new int[num];
        for (int i = 0; i < num;i++){
            numbers[i] = generateRandomNumber();
        }
        return numbers;
    }

    /**
     * 生成 Integer 正数 数组
     * @param num
     * @return
     */
    public static int[] generateRandomPositiveNumbers(int num){
        int[] numbers = new int[num];
        for (int i = 0; i < num;i++){
            numbers[i] = generateRandomPositiveNumber();
        }
        return numbers;
    }

    /**
     * 生成 0 - n 数组
     * @param num
     * @param max
     * @return
     */
    public static int[] generateRandomNumbers(int num,int max) {
        int[] numbers = new int[num];
        for (int i = 0; i < num;i++){
            numbers[i] = generateRandomNumber(max);
        }
        return numbers;
    }

    /**
     * 生成 min - max 数组
     * @param num
     * @param min
     * @param max
     * @return
     */
    public static int[] generateRandomNumbers(int num,int min,int max) {
        int[] numbers = new int[num];
        for (int i = 0; i < num;i++){
            numbers[i] = generateRandomNumber(min,max);
        }
        return numbers;
    }
    // ------------------------------ generate Number end ------------------------------


    // ------------------------------ generate String start ------------------------------

    /**
     * 通过 UUID 生成随机字符串
     * @return
     */
    public static String generateString() {
        return UUID.randomUUID().toString();
    }

    // ------------------------------ generate String end ------------------------------


}
