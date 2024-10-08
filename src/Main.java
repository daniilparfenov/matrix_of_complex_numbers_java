public class Main {
    public static void main(String[] args) {
        System.out.println("----- Testing a ComplexNumber class -----");
        testComplexNum();
        System.out.println("----- Testing a Matrix class -----");
        testMatrix();
    }

    public static void testComplexNum() {
        // Инициализация комплексных чисел для теста
        ComplexNumber num1 = new ComplexNumber(2, 1);
        ComplexNumber num2 = new ComplexNumber(4, 4);

        // Тесты операций
        System.out.printf("%s and %s:\n", num1, num2);
        System.out.printf("(%s) + (%s) = %s\n", num1, num2, num1.add(num2));
        System.out.printf("(%s) - (%s) = %s\n", num1, num2, num1.subtract(num2));
        System.out.printf("(%s) * (%s) = %s\n", num1, num2, num1.multiply(num2));
        System.out.printf("(%s) / (%s) = %s\n", num1, num2, num1.divide(num2));
        System.out.printf("(%s) == (%s): %b\n", num1, num2, num1.equals(num2));
    }

    public static void testMatrix() {
        // Инициализация данных для тестов
        ComplexNumber[][] data1 = {
                {new ComplexNumber(4, 2), new ComplexNumber(6, 8), new ComplexNumber(10, 12)},
                {new ComplexNumber(12, 16), new ComplexNumber(18, 20), new ComplexNumber(22, 24)},
                {new ComplexNumber(26, 28), new ComplexNumber(30, 32), new ComplexNumber(36, 40)},
        };
        ComplexNumber[][] data2 = {
                {new ComplexNumber(2, 1), new ComplexNumber(3, 4), new ComplexNumber(5, 6)},
                {new ComplexNumber(6, 8), new ComplexNumber(9, 10), new ComplexNumber(11, 12)},
                {new ComplexNumber(13, 14), new ComplexNumber(15, 16), new ComplexNumber(18, 20)},
        };

        // Создание тестовых матриц
        Matrix m1 = new Matrix(data1), m2 = new Matrix(data2);
        System.out.printf("Matrix 1:\n%s", m1);
        System.out.printf("Matrix 2:\n%s", m2);

        // Тесты операций
        System.out.printf("Matrix 1 + Matrix 2:\n%s", m1.add(m2));
        System.out.printf("Matrix 1 - Matrix 2:\n%s", m1.subtract(m2));
        System.out.printf("Matrix 1 * Matrix 2:\n%s", m1.multiply(m2));
        System.out.printf("Matrix 1 / Matrix 2:\n%s", m1.divide(m2));
        System.out.printf("Matrix 1 * (2+i):\n%s", m1.multiplyByScalar(new ComplexNumber(2, 1)));
        System.out.printf("Transposed Matrix 2:\n%s", m2.transpose());
        System.out.printf("Determinant of the Matrix 2: %s\n", m2.getDeterminant());
        System.out.printf("Inverse of the Matrix 2:\n%s", m2.getInverse());

        // Тесты заполнений матрицы
        Matrix randomMat = new Matrix(3, 3);
        randomMat.randomDoubleFill(10);
        System.out.printf("Random Double matrix:\n%s", randomMat);
        randomMat.randomIntFill(10);
        System.out.printf("Random Int matrix:\n%s", randomMat);

        // Тесты геттеров и сеттеров
        System.out.println("Getters and Setters Test");
        System.out.println("Set 0 to randomMat[0][0");
        randomMat.setElement(new ComplexNumber(0, 0), 0, 0);
        System.out.printf("Element[0][0] of Random Int matrix:%s\n", randomMat.getElement(0, 0));
        System.out.printf("Rows number of Random Int matrix:%s\n", randomMat.getRowsNumber());
        System.out.printf("Columns number of Random Int matrix:%s\n", randomMat.getColumnsNumber());

    }
}