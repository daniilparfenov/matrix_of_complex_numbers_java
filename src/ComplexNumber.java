public class ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    ComplexNumber() {
        realPart = 0;
        imaginaryPart = 0;
    }

    ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public String toString() {
        return ("(" + String.format("%.2f", realPart) + ", " + String.format("%.2f", imaginaryPart) + ")");
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber add(ComplexNumber otherNum) {
        return new ComplexNumber(this.realPart + otherNum.realPart, this.imaginaryPart + otherNum.imaginaryPart);
    }

    public ComplexNumber subtract(ComplexNumber otherNum) {
        return new ComplexNumber(this.realPart - otherNum.realPart, this.imaginaryPart - otherNum.imaginaryPart);
    }

    public ComplexNumber multiply(ComplexNumber otherNum) {
        ComplexNumber result = new ComplexNumber();
        result.realPart = this.realPart * otherNum.realPart - this.imaginaryPart * otherNum.imaginaryPart;
        result.imaginaryPart = this.realPart * otherNum.imaginaryPart + this.imaginaryPart * otherNum.realPart;
        return result;
    }

    public ComplexNumber divide(ComplexNumber divider) {
        ComplexNumber result = new ComplexNumber();
        if (divider.realPart == 0 && divider.imaginaryPart == 0) {
            System.out.println("Division by zero •`_´•");
            return result;
        }

        double divisionCoefficient = Math.pow(divider.realPart, 2) + Math.pow(divider.imaginaryPart, 2);
        result.realPart = (this.realPart * divider.realPart + this.imaginaryPart * divider.imaginaryPart) / divisionCoefficient;
        result.imaginaryPart = (this.imaginaryPart * divider.realPart - this.realPart * divider.imaginaryPart) / divisionCoefficient;
        return result;
    }
}
