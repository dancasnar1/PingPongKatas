package RockPaperScissors;

public class Player {
    private String name;
    private String gesture;

    public Player(String name, String gesture) {
        this.name = name;
        this.gesture = gesture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }
}