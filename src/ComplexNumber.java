import java.util.zip.ZipFile;

public class ComplexNumber {
    private double real;
    private double img;

    public ComplexNumber() {
        real = 0;
        img = 0;
    }

    public ComplexNumber(double real, double img) {
        this.real = real;
        this.img = img;
    }

    @Override
    public String toString() {
        String realStr = real % 1 == 0 ? String.format("%d", (long) real) : String.format("%.3f", real);
        String imgStr = img % 1 == 0 ? String.format("%d", (long) img) : String.format("%.3f", img);

        if (realStr.contains(",000")) {
            realStr = realStr.substring(0, realStr.indexOf(','));
        }
        if (imgStr.contains(",000")) {
            imgStr = imgStr.substring(0, imgStr.indexOf(','));
        }

        if ((realStr.equals("0") || realStr.equals("-0") && (imgStr.equals("0") || imgStr.equals("-0")))) {
            return "0";
        }
        if (realStr.equals("0") || realStr.equals("-0")) {
            return imgStr.equals("1") ? "i" : String.format("%si", imgStr);
        }
        if (imgStr.equals("0") || imgStr.equals("-0")) {
            return realStr;
        }
        if (imgStr.equals("1")) return String.format("%s+i", realStr);
        if (imgStr.equals("-1")) return String.format("%s-i", realStr);
        return img > 0 ? String.format("%s+%si", realStr, imgStr) : String.format("%s%si", realStr, imgStr);
    }

    public double getRealPart() {
        return real;
    }

    public void setRealPart(double real) {
        this.real = real;
    }

    public double getImaginaryPart() {
        return img;
    }

    public void setImaginaryPart(double img) {
        this.img = img;
    }

    public ComplexNumber add(ComplexNumber otherNum) {
        return new ComplexNumber(this.real + otherNum.real, this.img + otherNum.img);
    }

    public ComplexNumber subtract(ComplexNumber otherNum) {
        return new ComplexNumber(this.real - otherNum.real, this.img - otherNum.img);
    }

    public ComplexNumber multiply(ComplexNumber otherNum) {
        ComplexNumber result = new ComplexNumber();
        result.real = this.real * otherNum.real - this.img * otherNum.img;
        result.img = this.real * otherNum.img + this.img * otherNum.real;
        return result;
    }

    public ComplexNumber divide(ComplexNumber divider) {
        ComplexNumber result = new ComplexNumber();
        if (divider.real == 0 && divider.img == 0) {
            System.out.println("Division by zero •`_´•");
            return result;
        }

        double divisionCoefficient = Math.pow(divider.real, 2) + Math.pow(divider.img, 2);
        result.real = (this.real * divider.real + this.img * divider.img) / divisionCoefficient;
        result.img = (this.img * divider.real - this.real * divider.img) / divisionCoefficient;
        return result;
    }


}
