package logic.brick;

import controller.GameController;

public class MetalBrick extends AbstractBrick {
    public MetalBrick(){super(10,0);}
    @Override
    public void accept(GameController game){
        game.addBall(this);
    }
}
