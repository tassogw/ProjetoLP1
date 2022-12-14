import assets.Enemy;
import assets.Player;
//import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Variables and Objects
        Player player = new Player();
        Enemy enemy = new Enemy();
        Scanner sc = new Scanner(System.in);
        String playerName;
        boolean gameEnd = false, validAction = true;
        int round = 1; //Round output
        int action = 0, random = 0, damage = 30;

        //Initial message
        System.out.println("--- Welcome to Java's Western! ---");
        System.out.println("Insert your name:");
        playerName = sc.nextLine();
        //Checking if string is empty
        if (!(playerName.trim().equals(""))) {
            playerName = playerName.trim(); //If not, set name normally
        } else {
            playerName = "Player"; //If it is, set name to "Player"
        }
        player.setName(playerName);
        System.out.println(); //Skip a line

        //enemy.setIsLoaded(false);

        //Game setup
        do {
            System.out.printf("Round %d:%n", round);
            //Pick player's action until valid
            do {
                System.out.println("Choose an action:");
                System.out.printf("0 - Fire%n1 - Reload%n2 - Dodge%n");
                action = sc.nextInt();

                if ((action == 0) && (!player.getIsLoaded())) { //Trying to shoot w/ unloaded gun
                    System.out.println("Can't fire with unloaded gun, try again.");
                    validAction = false;
                } else if ((action == 1) && (player.getIsLoaded())) { //Trying to reload already loaded gun
                    System.out.println("Gun is already loaded, try again.");
                    validAction = false;
                } else {
                    validAction = true;
                    player.setAction(action);
                    System.out.println(); //Skip a line
                }
            } while (!validAction);

            //Choose enemy's action randomly
            enemy.setEnemyAction();


            //Compare results
            //Same actions
            if (player.getAction() == enemy.getAction() && player.getAction() == 0) {
                //Both shot
                System.out.println("Both shot...");
                //Check who got shot (one, both, or none)
                random = randomAction(4);
                switch (random) {
                    case 0:
                        //Player got shot
                        player.gotShot(damage);
                        //Show player's hp and skip a line
                        printGunslingerHealths(player, enemy, 0);
                        break;
                    case 1:
                        //Enemy got shot
                        enemy.gotShot(damage);
                        //Show enemy's hp and skip a line
                        printGunslingerHealths(player, enemy, 1);

                        break;
                    case 2:
                        //Both got shot
                        //Dealing damage to both
                        player.gotShot(damage);
                        enemy.gotShot(damage);
                        //Show characters' hp and skip a line
                        printGunslingerHealths(player, enemy, 2);

                        break;
                    case 3:
                        //None got shot
                        System.out.println("both missed.");
                        System.out.println(); //Skip a line
                        break;
                }

            } else if (player.getAction() == enemy.getAction() && player.getAction() == 1) {
                //Both reloaded
                System.out.println("Both reloaded.");
                //Reload guns
                player.reloadGun();
                enemy.reloadGun();
                System.out.println(); //Skip a line
            } else if (player.getAction() == enemy.getAction() && player.getAction() == 2) {
                //Both dodged
                System.out.println("Both dodged.");
                System.out.println(); //Skip a line
            } else if (player.getAction() != enemy.getAction() && player.getAction() == 0 && enemy.getAction() == 1) {
                //Player shot while enemy was reloading
                enemy.gotShot(damage);
                printGunslingerHealths(player, enemy, 1);
            } else if (player.getAction() != enemy.getAction() && player.getAction() == 1 && enemy.getAction() == 0) {
                //Enemy shot while player was reloading
                player.gotShot(damage);
                printGunslingerHealths(player, enemy, 0);
            } else if (player.getAction() != enemy.getAction() && player.getAction() == 0 && enemy.getAction() == 2) {
                //Player shot and enemy tried to dodge
                random = randomAction(2);
                switch (random) {
                    case 0:
                        //Enemy got shot
                        enemy.gotShot(damage);
                        //Show enemy's hp and skip a line
                        System.out.println("Enemy's dodge failed.");
                        printGunslingerHealths(player, enemy, 1);
                        break;
                    case 1:
                        //Enemy dodged
                        System.out.printf("%s Shot.%nEnemy dodged.%n", player.getName());
                        //Show enemy's hp and skip a line
                        System.out.printf("Enemy's health: %d%n%n", enemy.getHealth());
                        break;
                }
            } else if (player.getAction() != enemy.getAction() && player.getAction() == 2 && enemy.getAction() == 0) {
                //Enemy shot and player tried to dodge
                random = randomAction(2);
                switch (random) {
                    case 0:
                        //Player got shot
                        player.gotShot(damage);
                        //Show player's hp and skip a line
                        System.out.printf("%s's dodge failed.%n", player.getName());
                        printGunslingerHealths(player, enemy, 0);
                        break;
                    case 1:
                        //Player dodged
                        System.out.printf("Enemy shot.%n%s dodged.%n", player.getName());
                        //Show player's hp and skip a line
                        System.out.printf("%s's health: %d%n%n", player.getName(), player.getHealth());
                        break;
                }
            } else if (player.getAction() != enemy.getAction() && player.getAction() == 1 && enemy.getAction() == 2) {
                //Enemy dodged and player reloaded
                System.out.printf("Enemy dodged.%n");
                player.reloadGun();
                System.out.printf("%s reloaded.%n", player.getName());

            } else if (player.getAction() != enemy.getAction() && player.getAction() == 2 && enemy.getAction() == 1) {
                //Player dodged and enemy reloaded
                System.out.printf("Enemy reloaded.%n");
                enemy.reloadGun();
                System.out.printf("%s dodged.%n", player.getName());

            }

            //Check if game ended
            if (verifyGameEnd(player, enemy)) {
                gameEnd = true;
            }

            //Round update
            round++;
        } while (!gameEnd);
    }

    public static int randomAction(int bound) {
        Random random = new Random();

        //Random number between 0 and the given bound - 1
        int rand = random.nextInt(bound);

        return rand;
    }

    public static boolean verifyGameEnd(Player player, Enemy enemy) {
        if (enemy.getHealth() == 0 && player.getHealth() > 0) {
            System.out.printf("Game Over. Winner is: %s!%n", player.getName());
            return true;
        } else if (enemy.getHealth() > 0 && player.getHealth() == 0) {
            System.out.println("Game Over. Winner is: Enemy.");
            return true;
        } else if (enemy.getHealth() == player.getHealth() && player.getHealth() == 0) {
            System.out.println("Game Over. It's a tie.");
            return true;
        } else {
            return false;
        }
    }

    public static void printGunslingerHealths(Player player, Enemy enemy, int valueToBePrinted) {
        //0 = player got shot, 1 = enemy got shot, 2 = both got shot
        if (valueToBePrinted == 0) {
            System.out.printf("%s got shot.%n", player.getName());
            System.out.printf("%s's health: %d%n%n", player.getName(), player.getHealth());
        } else if (valueToBePrinted == 1) {
            System.out.printf("Enemy got shot.%n");
            System.out.printf("Enemy's health: %d%n%n", enemy.getHealth());
        } else if (valueToBePrinted == 2) {
            System.out.printf("and hit.%n");
            System.out.printf("%s's health: %d%nEnemy's health: %d%n%n", player.getName(),
                    player.getHealth(), enemy.getHealth());
        } else {
            System.out.println("Both shot each other.");
            System.out.printf("%s's health: %d%nEnemy's health: %d%n%n", player.getName(),
                    player.getHealth(), enemy.getHealth());
        }
    }

}

