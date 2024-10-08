import java.util.Random;

public class Matrix {
    // Поля
    private ComplexNumber[][] mat;
    private int rows, columns;

    // Конструктор. Создает матрицу размера rows x columns, заполненную нулями
    public Matrix(int rows, int columns) {
        mat = new ComplexNumber[rows][columns];
        this.rows = rows;
        this.columns = columns;

        // Инициализациями нулями
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    // Setter
    public void setElement(ComplexNumber num, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            mat[row][column] = num;
        } else {
            throw new IllegalArgumentException("Invalid element indexes (ㆆ_ㆆ)");
        }
    }

    // Getters
    public ComplexNumber getElement(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return mat[row][column];
        } else {
            throw new IllegalArgumentException("Invalid element indexes (ㆆ_ㆆ)");
        }
    }

    public int getRowsNumber() {
        return rows;
    }

    public int getColumnsNumber() {
        return columns;
    }


    // Methods

    // Заполняет матрицу случайными вещественными значениями от 0 до mod
    public void randomDoubleFill(double mod) {
        Random randomizer = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j].setRealPart(randomizer.nextDouble(mod));
                mat[i][j].setImaginaryPart(randomizer.nextDouble(mod));
            }
        }
    }

    // Заполняет матрицу случайными целыми значениями от 0 до mod
    public void randomIntFill(int mod) {
        Random randomizer = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j].setRealPart(randomizer.nextInt(mod));
                mat[i][j].setImaginaryPart(randomizer.nextInt(mod));
            }
        }
    }

    // Складывает две матрицы
    public Matrix add(Matrix otherMatrix) {
        // Проверка на неравенство размеров операндов
        if (rows != otherMatrix.rows || columns != otherMatrix.columns) {
            throw new IllegalArgumentException("Incompatible matrices for addition ＞﹏＜");
        }

        // Поэлементное сложение матриц
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.mat[i][j] = this.mat[i][j].add(otherMatrix.mat[i][j]);
            }
        }
        return result;
    }

    // Вычитает две матрицы
    public Matrix subtract(Matrix otherMatrix) {
        // Проверка на неравенство размеров операндов
        if (rows != otherMatrix.rows || columns != otherMatrix.columns) {
            throw new IllegalArgumentException("Incompatible matrices for subtraction ＞﹏＜");
        }

        // Поэлементное вычитание матриц
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                result.mat[i][j] = this.mat[i][j].subtract(otherMatrix.mat[i][j]);
            }
        }
        return result;
    }

    // Умножает две матрицы
    public Matrix multiply(Matrix otherMatrix) {
        // Для умножения количество строк первого операнда должно равняться количеству строк второго
        if (this.columns != otherMatrix.rows) {
            throw new IllegalArgumentException("Incompatible matrices for multiplying ＞﹏＜");
        }

        Matrix result = new Matrix(this.rows, otherMatrix.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    result.mat[i][j] = result.mat[i][j].add(this.mat[i][k].multiply(otherMatrix.mat[k][j]));
                }
            }
        }
        return result;
    }

    // Возвращает определитель квадратной матрицы. Вычисляется рекурсивно (косвенная рекурсия с getCofactor)
    public ComplexNumber getDeterminant() {
        // Проверка, что матрица квадратная
        if (rows != columns) {
            throw new IllegalArgumentException("Incompatible matrix for determinant calculating ＞﹏＜");
        }
        // Базовый случай рекурсии
        if (rows == 1) {
            return mat[0][0];
        }

        ComplexNumber det = new ComplexNumber(0, 0);
        /* Вычисляется детерминант путем разложения по первому столбцу. Его элементы домножаются на соответствующие
           алгебраические дополнения. Результат складывается */
        for (int row = 0; row < rows; row++) {
            det = det.add(getCofactor(row, 0).multiply(mat[row][0]));
        }
        return det;
    }

    // Возвращает алгебраическое дополнение к элементу i строки, j столбца матрицы
    private ComplexNumber getCofactor(int i, int j) {
        // Заполнение минора
        Matrix minor = new Matrix(rows - 1, columns - 1);
        int minorRow = 0, minorCol = 0;
        for (int row = 0; row < rows; row++) {
            if (row == i) continue;
            for (int col = 0; col < columns; col++) {
                if (col == j) continue;
                minor.mat[minorRow][minorCol] = mat[row][col];
                minorCol++;
            }
            minorRow++;
            minorCol = 0;
        }
        /* Если сумма индексов элемента, для которого считаем алг. дополнение, нечетна,
           домножаем определитель минора на -1 */
        return (i + j) % 2 == 0 ? minor.getDeterminant() : minor.getDeterminant().multiply(new ComplexNumber(-1, 0));
    }

    // Возвращает транспонированную матрицу
    public Matrix transpose() {
        Matrix transposedMat = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedMat.mat[j][i] = mat[i][j];
            }
        }
        return transposedMat;
    }

    // Возвращает транспонированную матрицу алгебраических дополнений всех элементов заданной матрицы
    private Matrix getAdjugate() {
        // Заполнение матрицы алгебраическими дополнениями к соответствующим элементам
        Matrix adjudicateMat = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                adjudicateMat.mat[i][j] = getCofactor(i, j);
            }
        }
        return adjudicateMat.transpose();
    }

    // Умножает матрицу на число (скаляр)
    public Matrix multiplyByScalar(ComplexNumber scalar) {
        Matrix multipliedMat = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                multipliedMat.mat[i][j] = mat[i][j].multiply(scalar);
            }
        }
        return multipliedMat;
    }

    // Возвращает обратную матрицу
    public Matrix getInverse() {
        ComplexNumber det = getDeterminant();
        // Проверка, что обратная матрица существует (определитель != 0)
        if (det.getRealPart() == 0 && det.getImaginaryPart() == 0) {
            throw new IllegalArgumentException("Incompatible matrix for inverse calculating ＞﹏＜");
        }
        // Возвращает транспонированную матрицу алгебраических дополнений деленную на детерминант
        return getAdjugate().multiplyByScalar(new ComplexNumber(1, 0).divide(det));
    }

    public Matrix divide(Matrix otherMat) {
        // Проверка, что деление возможно
        if ((columns != otherMat.columns) || (otherMat.columns != otherMat.rows) ||
                otherMat.getDeterminant().equals(new ComplexNumber(0, 0))) {
            throw new IllegalArgumentException("Incompatible matrices for dividing ＞﹏＜");
        }

        return this.multiply(otherMat.getInverse());
    }

    // Переопределение метода toString для вывода матрицы
    @Override
    public String toString() {
        // StringBuilder для эффективной работы со строками (не создает при каждой конкатенации новую строку)
        StringBuilder sb = new StringBuilder();

        // Поиск максимальной длины элементов матрицы (нужна для ровного вывода матрицы)
        int maxLength = -1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (mat[row][col].toString().length() > maxLength) {
                    maxLength = mat[row][col].toString().length();
                }
            }
        }
        // Создание строкового представления матрицы через StringBuilder
        for (int row = 0; row < rows; row++) {
            sb.append('[');
            for (int col = 0; col < columns; col++) {
                sb.append(String.format("%" + maxLength + "s", mat[row][col].toString())); // Ровный вывод элемента
                if (col != columns - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}

