package logic.brick;

import controller.GameController;

public class WoodenBrick extends AbstractBrick {
    public WoodenBrick(){super(3,200);}
    @Override
    public void accept(GameController game){
        game.addWoodenPoints(this);
    }}

