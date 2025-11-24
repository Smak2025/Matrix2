package ru.gr0550x;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var m1 = new Matrix(3, 3);
        System.out.println(m1);
        var m2 = new Matrix(new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 9}});
        System.out.println(m2);
        var m3 = new Matrix(4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        System.out.println(m3);
        System.out.println(Arrays.toString(m3.getDiagonal(DiagonalType.MAIN)));
        System.out.println(Arrays.toString(m3.getDiagonal(DiagonalType.SIDE)));
        System.out.println(Arrays.toString(m3.getDiagonal(DiagonalType.MAIN, DiagonalPosition.UPPER, 1)));
        System.out.println(Arrays.toString(m3.getDiagonal(DiagonalType.MAIN, DiagonalPosition.LOWER, 2)));
        System.out.println(Arrays.toString(m3.getDiagonal(DiagonalType.SIDE, DiagonalPosition.UPPER, -1)));
        System.out.println(Arrays.toString(m3.getDiagonal(DiagonalType.SIDE, DiagonalPosition.LOWER, 2)));
    }
}
