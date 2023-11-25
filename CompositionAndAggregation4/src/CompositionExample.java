// Пример композиции: Автомобиль содержит в себе объект: двигатель

// Класс, представляющий двигатель
class Engine {
    public void start() {
        System.out.println("Двигатель запущен");
    }

    public void stop() {
        System.out.println("Двигатель остановлен");
    }
}

// Класс, представляющий автомобиль
class Car {
    private Engine engine; // Композиция: Автомобиль содержит в себе объект двигателя

    public Car() {
        this.engine = new Engine(); // Создаем объект двигателя в конструкторе автомобиля
    }

    public void startCar() {
        engine.start(); // Вызываем метод запуска двигателя
        System.out.println("Автомобиль заведен");
    }

    public void stopCar() {
        engine.stop(); // Вызываем метод остановки двигателя
        System.out.println("Автомобиль заглушен");
    }
}

public class CompositionExample {
    public static void main(String[] args) {
        Car car = new Car(); // Создаем объект автомобиля
        car.startCar(); // Заводим автомобиль
        car.stopCar(); // Останавливаем автомобиль
    }
}
