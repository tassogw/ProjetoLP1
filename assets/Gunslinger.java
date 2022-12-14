package assets;

//Abstract superclass to create characters
public abstract class Gunslinger {
    //Atributes
    private int health;
    private int action;
    private boolean isLoaded;

    //Methods
    //Constructor
    public Gunslinger() {
        this.health = 100;
        this.isLoaded = true;
    }

    public Gunslinger(int hp) {
        this.health = hp;
        this.isLoaded = true;
    }

    //Getters {
    public boolean getIsLoaded() {
        /*  Method to check if the
         *  gunslinger can fire
         */
        return this.isLoaded;
    }

    public int getHealth() {
        /*  Method to check if gunslinger
         *  is dead (hp <= 0)
         */
        return this.health;
    }


    public void setAction(int act) {
        this.action = act;
        if (this.getAction() == 0) {
            this.setIsLoaded(false);
        }
    }

    public int getAction() {
        return action;
    }
    /*  Method to check what the gunslinger will do
     *  based on an option (fire, dodge or reload).
     *  Action will be manual for player and random-ish
     *  for the npc. List for values to each action:
     *  0 - Fire
     *  1 - Reload
     *  2 - Dodge
     */

    public void gotShot(int damage) {
        // Reduce HP if got hit
        if (this.health - damage < 0) {
            this.health = 0;
        } else {
            this.health -= damage;
        }
    }

    public void reloadGun() {
        // Reload gun / change isLoaded value
        this.isLoaded = true;
    }

    public void setIsLoaded(boolean value) {
        this.isLoaded = value;
    }
}

