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
        this.isLoaded = true;
    }

    //Getters {
    public boolean getIsLoaded(){
        /*  Method to check if the
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

    public abstract void setAction(short act);
    /*  Method to check what the gunslinger will do
     *  based on an option (fire, dodge or reload).
     *  Action will be manual for player and random-ish
     *  for the npc. List for values to each action:
     *  0 - Fire
     *  1 - Reload
     *  2 - Dodge
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

    public void setAction(short act){
        this.action = act;
    }
}

public class Enemy extends Gunslinger{

    public void setAction(short act){
        //Random method to choose enemy's action
        /*  The enemy will have to choose between
         *  firing, reloading or dodging, but, some
         *  conditions have to be considered. For
         *  example: the enemy can't reload if the
         *  bullet is already in the chamber, or
         *  firing if it isn't.
         */

        //Choose random value among 0, 1 or 2
        act = (short) (Math.random()*3);

        //Gun is loaded (can't reload)
        if (this.getIsLoaded()){
            //Change value until 0 or 2
            while (act == 1){
                act = (short) (Math.random()*3);
            }
        } else { //Gun isn't loaded (can't fire)
            //Pick value 0 or 1 and add (now 1 or 2)
            act = (short) ((Math.random()*2) + 1);
        }

        this.action = act;

    }
}