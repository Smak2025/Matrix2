package ru.gr0550x;

import java.util.Arrays;

public class Matrix {
    private final int[][] elems;

    public Matrix(int rows, int cols){
        elems = new int[rows][cols];
    }

    public Matrix(int[][] elems){
        this(elems.length, elems[0].length);
        for (int i = 0; i < elems.length; i++){
            this.elems[i] = Arrays.copyOf(elems[i], elems[i].length);
        }
    }

    public Matrix(int rows, int cols, int... elems){
        this(rows, cols);
        //TODO: Исправить!
        for (int i = 0; i < rows; i++){
            this.elems[i] = Arrays.copyOfRange(elems, i * cols, (i + 1) * cols);
        }
    }

    public String toString(){
        var strResult = new StringBuilder();
        for (var row: elems) {
            strResult.append("[");
            for (int i = 0; i < row.length; i++) {
                strResult.append(row[i]);
                if (i < row.length - 1) strResult.append(" ");
            }
            strResult.append("]\n");
        }
        return strResult.toString();
    }

    public int getRows(){
        return elems.length;
    }

    public int getColumns(){
        return elems[0].length;
    }

    public int[] getDiagonal(DiagonalType type){
        return getDiagonal(type, DiagonalPosition.MIDDLE, 0);
    }

    public int[] getDiagonal(DiagonalType type, DiagonalPosition pos, int num){
        var count = getRows() - Math.abs(num);
        var result = new int[count];
        for (int i = 0; i < count; i++) {
            switch (type) {
                case MAIN -> {
                    result[i] = switch (pos){
                        case UPPER -> elems[i][Math.abs(num)+i];
                        case LOWER -> elems[Math.abs(num) + i][i];
                        default -> elems[i][i];
                    };
                }
                case SIDE -> {
                    result[i] = switch(pos){
                        case UPPER -> elems[i][count - 1 - i - Math.abs(num)];
                        case LOWER -> elems[Math.abs(num) + i][count - 1 - i];
                        default -> elems[i][count - 1 - i];
                    };
                }
            };
        }
        return result;
    }
}
