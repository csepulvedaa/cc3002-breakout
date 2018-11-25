package logic.brick;

import controller.GameController;

/**
 * Brick WoodenBrick
 * Contains methods and default constructor for a WoodenBrick
 */
public class WoodenBrick extends AbstractBrick {
    public WoodenBrick(){super(3,200);}
    //Override for accept method
    @Override
    public void accept(GameController game){
        game.addWoodenPoints(this);
    }}

