// Пример агрегации: Автомобиль содержит в себе ссылку на объект двигателя

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
    private Engine engine; // Агрегация: Автомобиль содержит ссылку на объект двигателя

    public Car(Engine engine) {
        this.engine = engine; // Инициализируем ссылку на объект двигателя в конструкторе автомобиля
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

public class AggregationExample {
    public static void main(String[] args) {
        Engine engine = new Engine(); // Создаем объект двигателя
        Car car = new Car(engine); // Создаем объект автомобиля, передавая ему ссылку на двигатель
        car.startCar(); // Заводим автомобиль
        car.stopCar(); // Останавливаем автомобиль
    }
}
