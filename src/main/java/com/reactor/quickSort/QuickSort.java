package com.reactor.quickSort;

public class QuickSort {
    public static void quickSort(int[] arr,int left,int right){
        if (left>=right){
            return;
        }
        int key=arr[left];
        int i=left;
        int j=right;
        while(i<j){
            while (arr[j]>=key&&i<j){
                j--;
            }
            while (arr[i]<=key&&i<j){
                i++;
            }
            if (i<j){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        arr[left]=arr[i];
        arr[i]=key;
        quickSort(arr,left,i-1);
        quickSort(arr,i+1,right);
    }

    public static void main(String[] args) {
        int[] arr ={3,45,2,56,45,4,64,72,51,851,71,85,5,7,77,8,88,44,33,6};
        quick(arr,0,arr.length-1);
        for (int i:arr){
            System.out.print(i+" ");
        }
    }
    public static void quick(int[] arr,int left,int right){
        if (left>=right){
            return;
        }
        int key=arr[left];
        int i=left;
        int j=right;
        while (i<j){
            while (arr[j]>=key&&i<j){
                j--;
            }
            while (arr[i]<=key&&i<j){
                i++;
            }
            if (i<j){
                arr[i]=arr[i]^arr[j];
                arr[j]=arr[i]^arr[j];
                arr[i]=arr[i]^arr[j];
            }
        }
        arr[left]=arr[i];
        arr[i]=key;
        quick(arr,left,i-1);
        quick(arr,i+1,right);
    }
}
