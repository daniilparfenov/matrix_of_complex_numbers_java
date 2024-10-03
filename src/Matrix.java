import java.util.Random;

public class Matrix {
    private ComplexNumber matrix[][];
    private int rows, columns;

    Matrix() {
        matrix = new ComplexNumber[1][1];
        rows = 1;
        columns = 1;
        matrix[0][0] = new ComplexNumber();
    }

    Matrix(int rows, int columns) {
        matrix = new ComplexNumber[rows][columns];
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new ComplexNumber();
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setElement(ComplexNumber num, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            matrix[row][column] = num;
        } else {
            System.out.println("Invalid index (ㆆ_ㆆ)");
        }
    }

    public ComplexNumber getElement(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return matrix[row][column];
        } else {
            System.out.println("Invalid index (ㆆ_ㆆ)");
            return new ComplexNumber();
        }
    }

    public void randomFill(double mod) {
        Random randomizer = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j].setRealPart(randomizer.nextDouble(mod));
                matrix[i][j].setImaginaryPart(randomizer.nextDouble(mod));
            }
        }
    }

    public void randomFill(int mod) {
        Random randomizer = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j].setRealPart(randomizer.nextInt(mod));
                matrix[i][j].setImaginaryPart(randomizer.nextInt(mod));
            }
        }
    }

    public Matrix add(Matrix otherMatrix) {
        int rows = this.rows, columns = otherMatrix.columns;
        Matrix result = new Matrix(rows, columns);

        if (this.rows != otherMatrix.rows || this.columns != otherMatrix.columns) {
            System.out.println("Incompatible matrices for addition ＞﹏＜");
            return result;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.matrix[i][j] = this.matrix[i][j].add(otherMatrix.matrix[i][j]);
            }
        }
        return result;
    }

    public Matrix subtract(Matrix otherMatrix) {
        Matrix result = new Matrix(rows, columns);

        if (this.rows != otherMatrix.rows || this.columns != otherMatrix.columns) {
            System.out.println("Incompatible matrices for subtracting ＞﹏＜");
            return result;
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                result.matrix[i][j] = this.matrix[i][j].subtract(otherMatrix.matrix[i][j]);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix otherMatrix) {
        Matrix result = new Matrix(this.rows, otherMatrix.columns);
        if (this.columns != otherMatrix.rows) {
            System.out.println("Incompatible matrices for multiplying ＞﹏＜");
            return result;
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    result.matrix[i][j] = result.matrix[i][j].add(this.matrix[i][k].multiply(otherMatrix.matrix[k][j]));
                }
            }
        }

        return result;
    }


}
