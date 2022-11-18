package RockPaperScissors;

public class Match {
    public static String match(Player player1, Player player2) {

        String gesture1 = player1.getGesture();
        String gesture2 = player2.getGesture();

        //if (gesture1.equals(gesture2))
        // return "Draw";

        if (gesture1.equals("rock")) {
            if (gesture2.equals("paper")) {
                return player2.getName() + " win";
            } else if (gesture2.equals("scissors")) {
                return player1.getName() + " win";
            }
        } else if (gesture1.equals("paper")) {
            if (gesture2.equals("scissors")) {
                return player2.getName() + " win";
            } else if (gesture2.equals("rock")) {
                return player1.getName() + " win";
            }
        } else if (gesture1.equals("scissors")) {
            if (gesture2.equals("rock")) {
                return player2.getName() + " win";
            } else if (gesture2.equals("paper")) {
                return player1.getName() + " win";
            }
        } return "Draw";
    }
}