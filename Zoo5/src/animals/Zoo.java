// Недоделал, так как не понял как, и в чем была задача,
// часть кода удалена, так как было много неправильных наработок
// до конца не понял работу с пакетами




package animals;

// Абстрактный класс Animal
public abstract class Zoo {
    protected String name;
    protected int age;

    public Zoo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void performAction();
}

// Интерфейс для животных, которые умеют играть на музыкальных инструментах
public interface Musician {
    void playInstrument();
}

// Конкретный класс Penguin, который наследуется от Animal
public class Penguin extends Zoo {
    public Penguin(String name, int age) {
        super(name, age);
    }

    @Override
    public void performAction() {
        System.out.println("Penguin is dancing!");
    }
}

// Конкретный класс Monkey, который наследуется от Animal и реализует интерфейс Musician
public class Monkey extends Zoo implements Musician {
    public Monkey(String name, int age) {
        super(name, age);
    }

    @Override
    public void performAction() {
        System.out.println("Monkey is climbing trees.");
    }

    @Override
    public void playInstrument() {
        System.out.println("Monkey is playing drums.");
    }
}