public class MainInheritance {
    public static void main(String[] args) {
        Circle circle = new Circle(0);
        circle.draw(); // Рисуем круг
        circle.calculateArea(5); // Вычисляем площадь круга

        System.out.println();

        Rectangle rectangle = new Rectangle(0, 0);
        rectangle.draw(); // Рисуем прямоугольник
        rectangle.calculateArea(4, 6); // Вычисляем площадь прямоугольника
    }
}
