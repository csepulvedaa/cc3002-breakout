package logic.brick;

import controller.GameController;
/**
 * Brick GlassBrick
 * Contains methods and default constructor for a GlassBrick
 * @author csepu
 * @version nov 2018
 */

public class GlassBrick extends AbstractBrick {
    public GlassBrick(){super(1,50);}
    //Override for accept method
    @Override
    public void accept(GameController game){
        game.addGlassPoints(this);



}}
