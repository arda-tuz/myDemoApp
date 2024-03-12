package com.mycompany.app;

public class App{
    public static void main( String[] args ){


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
}
