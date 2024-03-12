package com.mycompany.app;

import java.util.Arrays;
import java.util.Random;
import org.junit.*;

public class AppTest {

    @Test
    public void test1(){    // valid sorting condition of the strArr

        Random rand = new Random(); 

        String[] arr1str = new String[100];
        String[] arr2str = new String[100];

        for (int i = 0; i < 100; i++) {

                char[] string = new char[rand.nextInt(10)]; 

            for (int j = 0; j < string.length; j++) {

                string[j] = (char) (rand.nextInt(26) + 'a'); // generate a random lowercase letter
            }

            arr1str[i] = new String(string);
            arr2str[i] = new String(string);
        }

        Integer[] arr1 = new Integer[]{9, 12, 13, 14, 15};
        Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5};

        Arrays.sort(arr1str);
        App.method(arr1, arr2, arr2str, 3);

        Assert.assertArrayEquals(arr1str, arr2str);
    }

    @Test(expected = RuntimeException.class)
    public void test2(){    // strArr = null condition

        Integer[] arr1 = new Integer[]{9, 12, 13, 14, 15};
        Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5};

        String[] strArr = null;
        App.method(arr1, arr2, strArr, 2);
    }

    @Test()
    public void test3(){

        Integer[] arr1 = new Integer[]{9, 12, 13, 14, 15};
        Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5};
        String[] strArr = new String[5];

        for(int i = 0; i < strArr.length; i ++){

            strArr[i] = "str" + i;
        }

        boolean flag = App.method(arr1, arr2, strArr, 2);

        Assert.assertEquals(flag, true);
    }

    @Test
    public void test4(){

        Integer[] arr2 = new Integer[]{9, 12, 13, 14, 15};
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        String[] strArr = new String[5];

        for(int i = 0; i < strArr.length; i ++){

            strArr[i] = "str" + i;
        }

        boolean flag = App.method(arr1, arr2, strArr, 2);

        Assert.assertEquals(flag, false);
    }

    @Test(expected = RuntimeException.class)
    public void test5(){

        Integer[] arr2 = new Integer[]{9, 12, 13, 14, 15};
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        String[] strArr = new String[5];

        for(int i = 0; i < strArr.length; i ++){

            strArr[i] = "str";
        }

        App.method(arr1, arr2, strArr, 9);
    }
}
