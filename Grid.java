/*
file name:      CellTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea CellTests
*/
import java.util.*;
public class Grid {
    public static void main(String[] args) {
        checkUsage(args);
        int rows = 3;
        int cols = 5;
        int randomMax = 100;

        if(Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < 10){
            rows = Integer.parseInt(args[0]);
        }

        if(Integer.parseInt(args[1]) > 10 && Integer.parseInt(args[1]) < 500){
            randomMax = Integer.parseInt(args[1]);
        }
        
        Integer[][] grid = new Integer[rows][cols];
        Random rand = new Random();
        //adds values to each element of the array
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
               grid[i][j] = rand.nextInt(randomMax);
            }
          }
        // print contents of the array
        for (Integer[] integers : grid) {
            for (int i : integers) {
                System.err.print(i + "  ");
            }
            System.out.println("");
        }

        // checks my grid equals fnction
        int[][] arr1 = new int[2][2];
        int[][] arr2 = new int[2][2];
        int[][] arr3;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                arr1[i][j] = i+j;
                arr2[i][j] = i+j;
            }
        } arr3 = arr1;
        System.out.println(gridEquals(arr1, arr2));
        System.out.println(gridEquals(arr1, arr3));

        // testing clockwise rotation
        arr2 = rotate(arr2);
        System.out.println(gridEquals(arr1, arr2));
        arr2 = rotate(arr2);
        arr2 = rotate(arr2);
        arr2 = rotate(arr2);
        
    }
    public static void checkUsage(String[] args){
        if(args.length > 0){
            for (String str : args) {
                System.out.println(str);
            }
        }
        else{
            System.out.println("Usage: java Grid [printed args]" +
                               "\n 1st arg: Updates row length" + 
                               "\n 2nd arg: Updates Maximum random number");
        }
    }
    public static boolean gridEquals(int[][] arr1, int[][] arr2){
        boolean equal = true;
        for(int i=0;i<arr1.length;i++) {
            for(int j=0;j<arr1[i].length;j++) {
               if(arr1[i][j] != arr2[i][j]){
                equal = false;
               }

            }
          }
        return equal;
    }
    public static int[][] rotate(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] rotatedArr = new int[cols][rows];

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
               rotatedArr[j][rows - 1 - i] = arr[i][j];

            }
          }
        return rotatedArr;
    }
}