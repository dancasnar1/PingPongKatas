import java.util.Random;

public class Player implements Comparable<Player> {

    private int id;
    private int actualSquare;
    private int diceRoll;

    private int order;

    public Player(int id, int actualSquare, int diceRoll, int order) {
        this.id = id;
        this.actualSquare = actualSquare;
        this.diceRoll = diceRoll;
        this.order = order;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return this.order - otherPlayer.order;
    }

    public int diceRoll() {
        Random random = new Random();
        return random.nextInt(1, 7);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActualSquare() {
        return actualSquare;
    }

    public void setActualSquare(int actualSquare) {
        this.actualSquare = actualSquare;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }
}
