package logic.brick;

import controller.Game;
import controller.GameController;

import java.util.Observable;
import java.util.Observer;

/**Abstract Class For Any Brick
 * Contains All Bricks Methods
 * @author csepu
 * @version Nov 2018
 */
public class AbstractBrick extends Observable implements Brick {
    private int Hp;
    private int Score;
    private int dmg;

    /**
     * Default Constructor for a Brick
     * @param Hp Hp
     * @param Score Points For Breaking
     */
    public AbstractBrick(int Hp, int Score){
        this.Hp=Hp;
        this.Score=Score;
        this.dmg=0;
    }

    /**Connects to a Game Observer
     *
     * @param game
     */
    public void connect(Observer game){
            addObserver(game);
        }
    //Override of Brick Interface
    @Override
    public void hit() {
        int rh=Hp-dmg;
        if (rh-1==0){
            setChanged();
            notifyObservers(this);
            clearChanged();
            dmg=dmg+1;
        }
        else{
        dmg=dmg+1;}
    }

    @Override
    public boolean isDestroyed() {
        if( Hp<=dmg){
            return true;}
        return false;

    }

    @Override
    public int getScore() {
        return Score;
    }

    @Override
    public int remainingHits() {
        if( Hp<=dmg){return 0;}
        return Hp-dmg;
    }

    @Override
    public void accept(GameController Game) {}
}
