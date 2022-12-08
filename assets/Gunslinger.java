package assets;

//Abstract superclass to create characters
public abstract class Gunslinger{
    //Atributes
    protected short health, action;
    protected boolean isLoaded;

    //Methods

    //Constructor
    public Gunslinger(short hp){
        this.health = hp;
    }

    //Getters {
    public boolean getIsLoaded(){
        /* Method to check if the
         *  gunslinger can fire
         */
        return this.isLoaded;
    }

    public short getHealth(){
        /*  Method to check if gunslinger
         *  is dead (hp <= 0)
         */
        return this.health;
    }
    //}

    public abstract short setAction();
    /* Method to check what the gunslinger will do
     *  based on an option (fire, dodge or reload).
     *  Action will be manual for player and random-ish
     *  for the npc.
     */

    public void gotShot(){
        // Reduce HP if got hit
        this.health--;
    }

    public void reloadGun(){
        // Reload gun / change isLoaded value
        this.isLoaded = true;
    }
}

public class Player extends Gunslinger{

    public short setAction(short op){
        this.action = short;
    }
}

public class Enemy extends Gunslinger{

    public short setAction(){
        //Random method to choose enemy's action
        /*  The enemy will have to choose between
         *  firing, reloading or dodging, but, some
         *  conditions have to be considered. For
         *  example: the enemy can't reload if the
         *  bullet is already in the chamber, or
         *  firing if it isn't.
         */

        //parei aqui
    }
}