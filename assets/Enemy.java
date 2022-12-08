package assets;

import java.util.Random;

public class Enemy extends Gunslinger{
    Random random = new Random();

    public void setEnemyAction(){
        //Random method to choose enemy's action
        /*  The enemy will have to choose between
         *  firing, reloading or dodging, but, some
         *  conditions have to be considered. For
         *  example: the enemy can't reload if the
         *  bullet is already in the chamber, or
         *  firing if it isn't.
         */

        //Choose random value among 0, 1 or 2
        int act = random.nextInt(3);
        if(act == 0 && this.getIsLoaded()){
            this.setAction(act);
        }else if(act == 0 && !this.getIsLoaded()) {
            this.setAction(1);
        }else if(act == 1 && !this.getIsLoaded()){
            this.setAction(act);
        }else if(act == 1 && this.getIsLoaded()){
            this.setAction(0);
        }else if(act == 2){
            this.setAction(act);
        }

    }
}