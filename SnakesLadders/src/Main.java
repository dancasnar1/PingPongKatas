public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(1, 1, 0, 0);
        Player player2 = new Player(2, 1, 0, 0);

        Player players[] = {player1, player2};

        Game.game(CreateOrder.createOrder(players), 0);

    }

}