public class Game {

    public static void game(Player[] players, int turn) {
        System.out.println("Turn: " + turn);


        for (int i = 0; i < players.length; i++) {
            System.out.println("Player" + players[i].getId() + " actual position " + players[i].getActualSquare());
            int diceRoll = players[i].diceRoll();
            System.out.println("DiceRoll: " + diceRoll);
            players[i].setDiceRoll(diceRoll);
            Move.move(players[i]);
            if (players[i].getActualSquare() == 100) {
                System.out.println("Player" + players[i].getId() + "WIN");
                return;
            } else {
                System.out.println("Player" + players[i].getId() + " new position " + players[i].getActualSquare());
            }
        }
        game(players, turn + 1);
    }
}
