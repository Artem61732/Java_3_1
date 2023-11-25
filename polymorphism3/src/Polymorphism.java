// Базовый класс для геометрической фигуры
abstract class Shape {
    abstract double calculateArea();
}

// Подкласс Круг
class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    public void draw() {
    }

    public void calculateArea(int i) {
    }
}

// Подкласс Прямоугольник
class Rectangle extends Shape {
    private double length;
    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }

    public void calculateArea(int i, int j) {
    }

    public void draw() {
    }
}
