package controller;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

/**
 * Interface for the game controller
 * controls the action of the game, adding points for each hitted brick
 *
 */

public interface GameController {
    /**
     * receives a WoodenBrick as parameter,
     * adds 50 points to the Level Gamescore
     *
     * @param wbrick
     */
    void addWoodenPoints(WoodenBrick wbrick);

    /**
     * receives a GlassBrick as parameter,
     * adds 100 points to the Level Gamescore
     *
     * @param gbrick
     */
    void addGlassPoints(GlassBrick gbrick);

    /**
     * receives a MetalBrick as Parameter
     * adds 100 points to the level GameScore
     * @param mbrick
     */
    void addBall(MetalBrick mbrick);

}
