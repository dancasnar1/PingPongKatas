import java.util.Random;

public class SpecialSquares {

    public int specialSquares(int actualSquare) {
        int snakes[] = new int[9];
        int ladders[] = new int[9];

        Random random = new Random();
        snakes[0] = random.nextInt(1,11);
        ladders[0] = random.nextInt(1,11);
        while (snakes[0] == ladders[0]) {
            ladders[0] = random.nextInt(1,11);
        }

        for (int i = 1; i < snakes.length; i++) {
            snakes[i] = snakes[i - 1] + 10;
            ladders[i] = ladders[i - 1] + 10;
        }

        for (int i = 1; i < snakes.length; i++) {
            if (snakes[i] == actualSquare) {
                System.out.println("YOU USED A SNAKE");
                return snakes[i - 1];
            }
        }
        for (int i = 0; i < ladders.length - 1; i++) {
            if (ladders[i] == actualSquare) {
                System.out.println("YOU USED A LADDER");
                return ladders[i + 1];
            }
        }
        return actualSquare;
    }
}
