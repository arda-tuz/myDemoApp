package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App
{

    public static Integer quickSelect(Integer[] arr, int k){     // k'th smallest  

        if(k >= arr.length){

            throw new RuntimeException();
        }

        int l = 0; int r = arr.length - 1; 

        while(l <= r){

            int p1 = partition(arr, l, r);

            if(p1 == k){

                return arr[p1];
            }
            else if(k < p1){    // sol'da ara

                // l = same
                r = p1 - 1;
            }
            else{   // sag'da ara

                l = p1 + 1;
                // r = same
            }
        }

        return null;
    }

    public static int partition(Integer[] arr, int l, int r){    // Bu klasik olan

        int p1 = arr[r]; int j = l; 

        for(int i = l; i <= r - 1; i ++){

            if(arr[i] < p1){

                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

                j++;
            }
        }  
        
        int temp = arr[j];
        arr[j] = arr[r];
        arr[r] = temp;

        return j;
    }

    public static void mergeSort(String[] arr) {

        if(arr == null){

            throw new RuntimeException();
        }

        if (arr.length <= 1) { // base case 

            return;
        }

        String[] left = new String[arr.length / 2];
        String[] right = new String[arr.length - (arr.length / 2)];

        for (int i = 0; i < arr.length; i++) {

            if (i < arr.length / 2) {

                left[i] = arr[i];
            } else {

                right[i - (arr.length / 2)] = arr[i];
            }
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(String[] arr, String[] left, String[] right) {
        int r = 0, l = 0, i = 0;

        while (l < left.length && r < right.length) {

            if (left[l].compareTo(right[r]) < 0) {
                arr[i] = left[l];
                l++;
            } else {
                arr[i] = right[r];
                r++;
            }
            i++;
        }

        while (l < left.length) {
            arr[i] = left[l];
            i++; l++;
        }

        while (r < right.length) {
            arr[i] = right[r];
            i++; r++;
        }
    }

    public static boolean method(Integer[] intArr1, Integer[] intArr2, String[] strArr, int k){

        // StrLisit lexicographic olarak sortlanacak
        // intList'lerde 2'sininde k'th order statistic bulunacak quickselect() ile if 1 >= 2 return true else return false

        mergeSort(strArr);

        int number1 = quickSelect(intArr1, k);
        int number2 = quickSelect(intArr2, k);

        if(number1 >= number2){

            return true;
        }
        else{
            
            return false;
        }
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {

            String list = req.queryParams("list");
            java.util.Scanner sc1 = new java.util.Scanner(list);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                inputList.add(value);
            }
            System.out.println(inputList);

            String list2 = req.queryParams("list2");
            java.util.Scanner sc2 = new java.util.Scanner(list2);
            sc2.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
            while (sc2.hasNext())
            {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
                inputList2.add(value);
            }
            System.out.println(inputList2);

            String str1 = req.queryParams("str1").replaceAll("\\s","");
            String str2 = req.queryParams("str2").replaceAll("\\s","");

            String k = req.queryParams("k").replaceAll("\\s","");
            int k_value = Integer.parseInt(k);

            boolean result = App.method(inputList.toArray(new Integer[0]), inputList2.toArray(new Integer[0]), new String[]{str1, str2}, k_value);

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/compute",
                (rq, rs) -> {
                    Map map = new HashMap();
                    map.put("result", "not computed yet!");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}