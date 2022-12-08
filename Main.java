import assets.Enemy;
import assets.Player;

public class Main {
    public static void main(String[] args){
        Player player = new Player();
        Enemy enemy = new Enemy();
        int round = 1;
        enemy.setIsLoaded(false);
        System.out.println("Round " + round);
        enemy.setEnemyAction();
        System.out.println("Enemy action at main: " + enemy.getAction());
    }
}
