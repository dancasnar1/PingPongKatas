import java.util.Arrays;

public class CreateOrder {
    public static Player[] createOrder(Player[] players) {
        for (Player player : players) {
            int diceRoll = player.diceRoll();
            player.setDiceRoll(diceRoll);
        }


        Arrays.sort(players);
        return players;
    }
}
