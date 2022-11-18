package PingPong;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Connect4 {
    public int numOfPlayers = 2;
    public int winningCriteria = 4;

    public Connect4() {}
    public Connect4(int numOfPlayers, int winningCriteria) {
        this.numOfPlayers = numOfPlayers;
        this.winningCriteria = winningCriteria;
    }

    public int getWinner(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] counts = new int[this.numOfPlayers + 1];
        Map<Integer, Integer> maxCount = new HashMap<>();

        if(m < this.winningCriteria && n < this.winningCriteria) return -1;

        // check horizontally
        Arrays.fill(counts, 1);
        for(int i = 0; i < m; i++) {
            for(int j = 1; j < n; j++) {
                int curr = board[i][j];
                if(j == 1) counts[curr] = 1;
                if(curr == board[i][j-1]) {
                    counts[curr]++;
                    if(counts[curr] >= this.winningCriteria)
                        maxCount.put(curr, Math.max(counts[curr], maxCount.getOrDefault(curr, -1)));
                }
                else counts[curr] = 1;
            }
        }

        // check vertically
        Arrays.fill(counts, 1);
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < m; j++) {
                int curr = board[j][i];
                if(j == 1) counts[curr] = 1;
                if(curr == board[j-1][i]) {
                    counts[curr]++;
                    if(counts[curr] >= this.winningCriteria)
                        maxCount.put(curr, Math.max(counts[curr], maxCount.getOrDefault(curr, -1)));
                }
                else counts[curr] = 1;
            }
        }

        // check diagonally, upper left to lower right
        Arrays.fill(counts, 1);
        // left side
        for(int i = 1, j = 0; i < m; i++) {
            for(int row = i+1, col = j+1; row < m && col < n; row++, col++) {
                int curr = board[row][col];
                if(col == j + 1) counts[curr] = 1;
                if(curr == board[row-1][col-1]) {
                    counts[curr]++;
                    if(counts[curr] >= this.winningCriteria)
                        maxCount.put(curr, Math.max(counts[curr], maxCount.getOrDefault(curr, -1)));
                }
                else counts[curr] = 1;
            }
        }
        // right side
        for(int i = 0, j = 0; j < n; j++) {
            for(int row = i+1, col = j+1; row < m && col < n; row++, col++) {
                int curr = board[row][col];
                if(row == i + 1) counts[curr] = 1;
                if(curr == board[row-1][col-1]) {
                    counts[curr]++;
                    if(counts[curr] >= this.winningCriteria)
                        maxCount.put(curr, Math.max(counts[curr], maxCount.getOrDefault(curr, -1)));
                }
                else counts[curr] = 1;
            }
        }

        // check diagonally upper right to lower left
        Arrays.fill(counts, 1);
        // left side
        for(int i = 0, j = n-2; j > 0; j--) {
            for(int row = i+1, col = j-1; row < m && col >= 0; row++, col--) {
                int curr = board[row][col];
                if(row == i + 1) counts[curr] = 1;
                if(curr == board[row-1][col+1]) {
                    counts[curr]++;
                    if(counts[curr] >= this.winningCriteria)
                        maxCount.put(curr, Math.max(counts[curr], maxCount.getOrDefault(curr, -1)));
                }
                else counts[curr] = 1;
            }
        }
        // right side
        for(int i = 0, j = n-1; i < m; i++) {
            for(int row = i+1, col = j-1; row < m && col >= 0; row++, col--) {
                int curr = board[row][col];
                if(col == j - 1) counts[curr] = 1;
                if(curr == board[row-1][col+1]) {
                    counts[curr]++;
                    if(counts[curr] >= this.winningCriteria)
                        maxCount.put(curr, Math.max(counts[curr], maxCount.getOrDefault(curr, -1)));
                }
                else counts[curr] = 1;
            }
        }

        // determine who's the winner
        int max = -1, winner = -1;
        for(int player : maxCount.keySet()) {
            if(player == 0) continue;
            if(maxCount.get(player) > max) {
                winner = player;
                max = maxCount.get(player);
            }
            else if(maxCount.get(player) == max) winner = -1;
        }
        return winner;
    }

    public int[][] generateBoard(int m, int n) {
        Random random = new Random();
        int[][] board = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = random.nextInt(this.numOfPlayers + 1);
            }
        }
        return board;
    }
}
