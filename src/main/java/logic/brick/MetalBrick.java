package logic.brick;

import controller.GameController;

/** Brick Metalbrick
 * Contains Methods and Default Constructor For GlassBrick
 * @author csepu
 * @version nov2018
 *
 */

public class MetalBrick extends AbstractBrick {
    public MetalBrick(){super(10,0);}
    //Override for accept method
    @Override
    public void accept(GameController game){
        game.addBall(this);
    }
}
