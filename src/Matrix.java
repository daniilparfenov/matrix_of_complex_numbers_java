import java.util.Random;

public class Matrix {
    private ComplexNumber[][] mat;
    private int rows, columns;

    Matrix() {
        mat = new ComplexNumber[1][1];
        rows = 1;
        columns = 1;
        mat[0][0] = new ComplexNumber();
    }

    Matrix(int rows, int columns) {
        mat = new ComplexNumber[rows][columns];
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j] = new ComplexNumber();
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setElement(ComplexNumber num, int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            mat[row][column] = num;
        } else {
            System.out.println("Invalid index (ㆆ_ㆆ)");
        }
    }

    public ComplexNumber getElement(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return mat[row][column];
        } else {
            System.out.println("Invalid index (ㆆ_ㆆ)");
            return new ComplexNumber();
        }
    }

    public void randomFill(double mod) {
        Random randomizer = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j].setRealPart(randomizer.nextDouble(mod));
                mat[i][j].setImaginaryPart(randomizer.nextDouble(mod));
            }
        }
    }

    public void randomFill(int mod) {
        Random randomizer = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j].setRealPart(randomizer.nextInt(mod));
                mat[i][j].setImaginaryPart(randomizer.nextInt(mod));
            }
        }
    }

    public Matrix add(Matrix otherMatrix) {
        Matrix result = new Matrix(rows, columns);

        if (rows != otherMatrix.rows || columns != otherMatrix.columns) {
            System.out.println("Incompatible matrices for addition ＞﹏＜");
            return result;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.mat[i][j] = this.mat[i][j].add(otherMatrix.mat[i][j]);
            }
        }
        return result;
    }

    // CHEEEEEEEEEEEEEEEEEEEEEECK
    public Matrix subtract(Matrix otherMatrix) {
        Matrix result = new Matrix(rows, columns);

        if (rows != otherMatrix.rows || columns != otherMatrix.columns) {
            throw new IllegalArgumentException("Incompatible matrices for multiplying ＞﹏＜");
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                result.mat[i][j] = this.mat[i][j].subtract(otherMatrix.mat[i][j]);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix otherMatrix) {
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

    public ComplexNumber getDeterminant() {
        if (rows != columns) {
            throw new IllegalArgumentException("Incompatible matrix for determinant calculating ＞﹏＜");
        }
        if (rows == 1) {
            return mat[0][0];
        }

        ComplexNumber det = new ComplexNumber(0, 0);
        for (int row = 0; row < rows; row++) {
            det = det.add(getCofactor(row, 0).multiply(mat[row][0]));
        }
        return det;
    }

    private ComplexNumber getCofactor(int i, int j) {
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
        return (i + j) % 2 == 0 ? minor.getDeterminant() : minor.getDeterminant().multiply(new ComplexNumber(-1, 0));
    }

    public Matrix transpose() {
        Matrix transposedMat = new Matrix(columns, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedMat.mat[j][i] = mat[i][j];
            }
        }
        return transposedMat;
    }

    private Matrix getAdjugate() {
        Matrix adjudicateMat = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                adjudicateMat.mat[i][j] = getCofactor(i, j);
            }
        }
        return adjudicateMat.transpose();
    }

    public Matrix multiplyByScalar(ComplexNumber scalar) {
        Matrix multipliedMat = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                multipliedMat.mat[i][j] = mat[i][j].multiply(scalar);
            }
        }
        return multipliedMat;
    }

    public Matrix getInverse() {
        ComplexNumber det = getDeterminant();
        if (det.getRealPart() == 0 && det.getImaginaryPart() == 0) {
            throw new IllegalArgumentException("Incompatible matrix for inverse calculating ＞﹏＜");
        }
        return getAdjugate().multiplyByScalar(new ComplexNumber(1, 0).divide(det));
    }
}

