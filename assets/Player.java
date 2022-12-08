package assets;
import assets.Gunslinger;

public class Player extends Gunslinger{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player() {
        this.name = "Player";
    }
    public Player(String name) {
        this.name = name;
    }
}

