import java.util.*;

// Класс, представляющий карту
class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

// Класс, представляющий колоду карт
class Deck {
    private final List<Card> cards;
    private final List<Card> dealtCards;

    public Deck() {
        cards = new ArrayList<>();
        dealtCards = new ArrayList<>();

        initializeDeck();
    }

    // Инициализация колоды
    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // Перемешивание колоды
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    // Получение количества оставшихся карт в колоде
    public int remainingCards() {
        return cards.size();
    }

    // Выдача карты из колоды
    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Колода пуста");
        }

        Card dealtCard = cards.remove(cards.size() - 1);
        dealtCards.add(dealtCard);
        return dealtCard;
    }

    // Возврат карты в колоду с проверкой дублирования
    public void returnCard(Card card) {
        if (dealtCards.contains(card)) {
            dealtCards.remove(card);
            cards.add(card);
        } else {
            throw new IllegalArgumentException("Карта не была выдана из этой колоды или уже была возвращена");
        }
    }
}

// Пример использования класса колоды карт
public class CardDeck {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("Колода карт:");
        System.out.println("Количество карт в колоде: " + deck.remainingCards());

        deck.shuffleDeck();
        System.out.println("\nКолода карт после перемешивания:");
        System.out.println("Количество карт в колоде: " + deck.remainingCards());

        Card drawnCard = deck.dealCard();
        System.out.println("\nКарта, вытащенная из колоды: " + drawnCard);
        System.out.println("Количество карт в колоде после сдачи: " + deck.remainingCards());

        deck.returnCard(drawnCard);
        System.out.println("\nКарта возвращена в колоду.");
        System.out.println("Количество карт в колоде после возврата: " + deck.remainingCards());
    }
}
