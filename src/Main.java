public class Main {
    public static void main(String[] args) {
        ComplexNumber num1 = new ComplexNumber(2, 1);
        ComplexNumber num2 = new ComplexNumber(2, 0);

        ComplexNumber added, subtracted, multiplied, divided;
        added = num1.add(num2);
        subtracted = num1.subtract(num2);
        multiplied = num1.multiply(num2);
        divided = num1.divide(num2);

        System.out.println(num1 + " and " + num2);
        System.out.println("Add: " + added);
        System.out.println("Subtract: " + subtracted);
        System.out.println("Multiply: " + multiplied);
        System.out.println("Divide: " + divided);

        Matrix m1 = new Matrix(2,2);
        m1.randomFill(10);
        m1.print();
        System.out.println();
        Matrix m2 = new Matrix(2, 2);
        m2.randomFill(10);
        m2.print();

        System.out.println("Multiplying:");
        Matrix res = m1.multiply(m2);
        res.print();
    }
}