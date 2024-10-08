

public class ComplexNumber {
    // Поля
    private double real;
    private double img;

    // Конструктор
    public ComplexNumber(double real, double img) {
        this.real = real;
        this.img = img;
    }

    // Getters
    public double getRealPart() {
        return real;
    }

    public double getImaginaryPart() {
        return img;
    }

    // Setters
    public void setRealPart(double real) {
        this.real = real;
    }

    public void setImaginaryPart(double img) {
        this.img = img;
    }

    // Methods

    // Сложение
    public ComplexNumber add(ComplexNumber otherNum) {
        return new ComplexNumber(this.real + otherNum.real, this.img + otherNum.img);
    }

    // Вычитание
    public ComplexNumber subtract(ComplexNumber otherNum) {
        return new ComplexNumber(this.real - otherNum.real, this.img - otherNum.img);
    }

    // Умножение
    public ComplexNumber multiply(ComplexNumber otherNum) {
        ComplexNumber result = new ComplexNumber(0, 0);
        result.real = this.real * otherNum.real - this.img * otherNum.img;
        result.img = this.real * otherNum.img + this.img * otherNum.real;
        return result;
    }

    // Деление
    public ComplexNumber divide(ComplexNumber divider) {
        // Проверка на деление на ноль
        if (divider.real == 0 && divider.img == 0) {
            throw new ArithmeticException("Divided by zero operation cannot possible ＞﹏＜");
        }

        ComplexNumber result = new ComplexNumber(0, 0);
        double divisionCoefficient = Math.pow(divider.real, 2) + Math.pow(divider.img, 2);
        result.real = (this.real * divider.real + this.img * divider.img) / divisionCoefficient;
        result.img = (this.img * divider.real - this.real * divider.img) / divisionCoefficient;
        return result;
    }

    // Проверяет, равны ли два комплексных числа
    public boolean equals(ComplexNumber otherNum) {
        return (real == otherNum.real && img == otherNum.img);
    }

    // Переопределение метода для красивого вывода в консоль
    @Override
    public String toString() {
        /* Преобразуем части числа в соотвествии с тем, какие они (целые/дробные).
           У дробных оставим 3 знака после запятой. */
        String realStr = real % 1 == 0 ? String.format("%d", (long) real) : String.format("%.3f", real);
        String imgStr = img % 1 == 0 ? String.format("%d", (long) img) : String.format("%.3f", img);

        /* Из-за floating-point арифметики real и img могут содержать "лишние", неправильные цифры после запятой, из-за
        чего из ошибочного 0.000...001 после преобразования в строку могут получаться вещи по типу 0.000.
        Преобразуем такое просто в 0. */
        if (realStr.contains(",000")) {
            realStr = realStr.substring(0, realStr.indexOf(','));
        }
        if (imgStr.contains(",000")) {
            imgStr = imgStr.substring(0, imgStr.indexOf(','));
        }

        // Если обе части числа нулевые, значит число 0
        if ((realStr.equals("0") || realStr.equals("-0") && (imgStr.equals("0") || imgStr.equals("-0")))) {
            return "0";
        }
        // Если только real нуль, то возвращаем img (если img==1, вернем без единицы, т.к. она не ставится перед i
        if (realStr.equals("0") || realStr.equals("-0")) {
            return imgStr.equals("1") ? "i" : String.format("%si", imgStr);
        }
        // Если img == 0, вернем real
        if (imgStr.equals("0") || imgStr.equals("-0")) {
            return realStr;
        }

        // Проверки на img == +-1 в случае если real != 0
        if (imgStr.equals("1")) return String.format("%s+i", realStr);
        if (imgStr.equals("-1")) return String.format("%s-i", realStr);

        // Если обе части ненулевые и img != +-1, возвращаем "real"+"img"i либо "real"-"img"i, в завимости от знакак img
        return img > 0 ? String.format("%s+%si", realStr, imgStr) : String.format("%s%si", realStr, imgStr);
    }

}
