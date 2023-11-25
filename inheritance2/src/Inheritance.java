// Базовый класс для фигуры
class Shape {
    public void draw() {
        System.out.println("Drawing a shape.");
    }
}

// Подкласс Circle, наследуется от класса Shape
class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }

    public void calculateArea(double radius) {
        System.out.println("Area of the circle: " + (Math.PI * radius * radius));
    }
}

// Подкласс Rectangle, наследуется от класса Shape
class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle.");
    }

    public void calculateArea(double length, double width) {
        System.out.println("Area of the rectangle: " + (length * width));
    }
}
