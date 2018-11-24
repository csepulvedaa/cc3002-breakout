package logic.brick;

import controller.Game;

import java.util.Observable;

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


    @Override
    public void hit() {
        if (this.isDestroyed()){
            setChanged();
            notifyObservers(this.getScore());
        }
        else{
        dmg=dmg+1;}
    }

    @Override
    public boolean isDestroyed() {
        if( Hp<=dmg){ return true;}
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
}
