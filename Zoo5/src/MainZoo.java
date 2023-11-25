import animals.*;

public class MainZoo {
    public static void main(String[] args) {
        Zoo myZoo = new Zoo(5);

        Animal penguin = new Penguin("Pingo", 3);
        Animal monkey = new Monkey("Milo", 6);

        myZoo.addAnimal(penguin, 0);
        myZoo.addAnimal(monkey, 1);

        myZoo.performActions();
    }
}

