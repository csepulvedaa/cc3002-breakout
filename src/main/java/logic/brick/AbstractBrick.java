package logic.brick;

import controller.Game;
import controller.GameController;

import java.util.Observable;
import java.util.Observer;

/**Clase Abstracta para cualquier tipo de brick
 * Se definen metodos y parametros para cualquier brick por defecto
 * @author csepu
 * @version Nov 2018
 */
public class AbstractBrick extends Observable implements Brick {
    private int Hp;
    private int Score;
    private int dmg;

    public AbstractBrick(int Hp, int Score){
        this.Hp=Hp;
        this.Score=Score;
        this.dmg=0;
    }

    public void connect(Observer game){
            addObserver(game);
        }

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
