package logic.brick;

import controller.GameController;

public class GlassBrick extends AbstractBrick {
    public GlassBrick(){super(1,50);}

    @Override
    public void accept(GameController game){
        game.addGlassPoints(this);



}}
