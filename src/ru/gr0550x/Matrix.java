package ru.gr0550x;

import java.util.Arrays;

public class Matrix {
    private final int[][] elems;

    /**
     * Создание нулевой матрицы
     * @param rows количество строк
     * @param cols количество столбцов
     */
    public Matrix(int rows, int cols){
        elems = new int[rows][cols];
    }

    /**
     * Создание матрицы по двумерному массиву
     * @param elems двумерный массив, содержащий элементы матрицы
     */
    public Matrix(int[][] elems){
        this(elems.length, elems[0].length);
        for (int i = 0; i < elems.length; i++){
            this.elems[i] = Arrays.copyOf(elems[i], elems[i].length);
        }
    }

    /**
     * Создание матрицы с заданным количеством строк и столбцов,
     * и элементами
     * @param rows количество строк в матрице
     * @param cols количество столбцов в матрице
     * @param elems элементы матрицы
     */
    public Matrix(int rows, int cols, int... elems) throws IllegalArgumentException{
        this(rows, cols);
        if (elems.length < rows * cols){
            throw new IllegalArgumentException("Недостаточное количество элементов для матрицы указанного размера");
        }
        for (int i = 0; i < rows; i++){
            this.elems[i] = Arrays.copyOfRange(elems, i * cols, (i + 1) * cols);
        }
    }

    /**
     * Получение строкового представления матрицы
     * @return Строка, элементы матрицы, заключенные в квадратные скобки.
     * Строки отделяются друг от друга символом \n
     */
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

    /**
     * Получение количества строк в матрице
     * @return количество строк в матрице
     */
    public int getRows(){
        return elems.length;
    }

    /**
     * Получение количества столбцов в матрице
     * @return количество столбцов в матрице
     */
    public int getColumns(){
        return elems[0].length;
    }

    /**
     * Получение массива, содержащего элементы на главной или побочной диагонали матрицы.
     * @param type тип диагонали. Может принимать значение {@link DiagonalType#MAIN} для
     *             получения элементов главной диагонали или {@link DiagonalType#SIDE}, для
     *             получения элементов побочной диагонали
     * @return массив значений элементов на указанной диагонали
     * @see DiagonalType
     */
    public int[] getDiagonal(DiagonalType type) throws Exception{
        return getDiagonal(type, DiagonalPosition.MIDDLE, 0);
    }

    /**
     * Получение массива, содержащего элементы на одной из диагоналей матрицы
     * @param type тип диагонали (может быть {@link DiagonalType#MAIN} или {@link DiagonalType#SIDE})
     * @param pos позиция диагонали (может быть
     *              {@link DiagonalPosition#UPPER},
     *              {@link DiagonalPosition#LOWER},
     *              {@link DiagonalPosition#MIDDLE})
     * @param num номер диагонали по порядку
     *            {@link DiagonalPosition#MIDDLE MIDDLE} диагональ всегда имеет номер =0,
     *            Для диагоналей, параллельных главной
     *            {@link DiagonalType#MAIN MAIN} отсчитывается вниз при {@link DiagonalPosition#LOWER LOWER}
     *            или вправо при {@link DiagonalPosition#UPPER UPPER} и в обоих случаях должен быть >0.
     *            Для диагоналей, параллельных побочной
     *            {@link DiagonalType#SIDE SIDE} num отсчитывается вниз (>0) при {@link DiagonalPosition#LOWER LOWER}
     *            или влево (<0) при {@link DiagonalPosition#UPPER UPPER}
     * @return массив значений элементов на указанной диагонали
     * @see DiagonalPosition
     */
    public int[] getDiagonal(DiagonalType type, DiagonalPosition pos, int num) throws Exception {
        if (getRows() != getColumns()){
            throw new Exception("Матрица должна быть квадратной");
        }
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
                        case UPPER -> elems[i][count - 1 - i];
                        case LOWER -> elems[Math.abs(num) + i][getColumns() - 1 - i];
                        default -> elems[i][count - 1 - i];
                    };
                }
            };
        }
        return result;
    }
}
