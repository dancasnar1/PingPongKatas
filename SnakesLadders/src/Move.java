public class Move {

    public static Player move(Player player) {
        int actualSquare = player.getActualSquare() + player.getDiceRoll();

        SpecialSquares specialSquares = new SpecialSquares();
        actualSquare = specialSquares.specialSquares(actualSquare);

        if(actualSquare > 100) {
            return player;
        }

        player.setActualSquare(actualSquare);
        return player;
    }
}
