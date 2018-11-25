package controller;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.GameLevel;
import logic.level.Level;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer,GameController {
    private GameLevel GameList=new GameLevel();
    private int balls;
    private int Points;

    /**
     * Default Constructor For a Game
     * @param balls number of balls
     */
    public Game(int balls) {
        this.balls=balls;

    }
    /**
     * Creates a new level with the given parameters.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link GlassBrick}
     * @param probOfMetal    the probability of a {@link MetalBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */

    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        GameLevel NewLevel = new GameLevel(name);
        double numberOfMetalBricks=NumberOfMetal(probOfMetal,numberOfBricks,seed);
        double numberOfGlassBricks=NumberOfGlass(probOfGlass,numberOfBricks,seed);
        double numberOfWoodenBricks=numberOfBricks-numberOfGlassBricks;
        NewLevel.addGlassBricks((int) numberOfGlassBricks,this);
        NewLevel.addWoodenBricks((int)numberOfWoodenBricks,this);
        NewLevel.addMetalBricks((int)numberOfMetalBricks,this);
        return NewLevel;
    }

    /**
     * Override for Observer
     * Add points to the game
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Brick){
            ((Brick) arg).accept(this);
        }
    }

    /**
     * NumberOfMetal
     * Gets te number of Metal bricks to add
     * @param probOfMetal
     * @param numberOfBricks
     * @param seed
     * @return Numer Of Metal Bricks
     */
    public double NumberOfMetal(double probOfMetal, int numberOfBricks,int seed) {
        Random O =new Random(seed);
        double numberOfMetalBricks = 0;
        if (probOfMetal == 1) {
            return numberOfBricks;
        }
        if (probOfMetal == 0) {
            return numberOfMetalBricks;
        }
        for (int j=0;j<=numberOfBricks;j++){
            double r =O.nextDouble();}
        for (int j=0;j<=numberOfBricks;j++){
            double r =O.nextDouble();
            if (r<=probOfMetal){
                numberOfMetalBricks++; }
        }
        return numberOfMetalBricks;

    }

    /**
     * NumberOfGlass Bricks to add
     *
     * @param probOfGlass
     * @param numberOfBricks
     * @param seed
     * @return Number Of Glass bricks
     */
    public double NumberOfGlass(double probOfGlass, int numberOfBricks,int seed) {
        Random O =new Random(seed);
        double numberOfGlassBricks = 0;
        if (probOfGlass == 0) {
            return numberOfGlassBricks;
        }
        if (probOfGlass == 1) {
            return numberOfBricks;
        }
        for (int j=0;j<=numberOfBricks;j++){
            double r =O.nextDouble();
            if (r<=probOfGlass){
                numberOfGlassBricks++; }
        }
        return numberOfGlassBricks;

    }
    /**
     * Creates a new level with the given parameters with no metal bricks.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */
    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        GameLevel NewLevel = new GameLevel(name);
        double numberOfGlassBricks=NumberOfGlass(probOfGlass,numberOfBricks,seed);
        double numberOfWoodenBricks=numberOfBricks-numberOfGlassBricks;
        NewLevel.addGlassBricks((int) numberOfGlassBricks,this);

        NewLevel.addWoodenBricks((int)numberOfWoodenBricks,this);
        return NewLevel;

    }


    /**
     * Gets the state of the player.
     *
     * @return true if the player won the game, false otherwise
     */
    public boolean winner() {
        if(GameList.getNextLevel()==null){
            return true;
        }
        return false;
    }
    /**
     * Gets the name of the current {@link Level}.
     *
     * @return the name of the current level
     */
    public String getLevelName() {
        return GameList.getName();
    }
    /**
     * Gets the current number of available balls to play.
     *
     * @return the number of available balls
     */
    public int getBallsLeft() {
        return this.balls;
    }
    /**
     * Whether the current {@link Level}'s next level is playable or not.
     *
     * @return true if the current level's next level is playable, false otherwise
     */
    public boolean hasNextLevel() {
       return GameList.hasNextLevel();

    }
    /**
     * Gets whether the current {@link Level} is playable or not.
     *
     * @return true if the current level is playable, false otherwise
     */
    public boolean hasCurrentLevel() {
        return GameList.isPlayableLevel();
    }
    /**
     * Gets the number of {@link Brick} in the current level, that are still not destroyed
     *
     * @return the number of intact bricks in the current level
     */
    public int numberOfBricks() {
        return GameList.getNumberOfBricks();
    }

    /**
     * Gets the list of {@link Brick} in the current level.
     *
     * @return the list of bricks
     */

    public List<Brick> getBricks() {
        return GameList.getBricks();
    }

    /**
     * Gets the current {@link Level}.
     *
     * @return the current level
     * @see Level
     */
    public Level getCurrentLevel() {
        return GameList;
    }
    /**
     * Adds a level to the list of {@link Level} to play. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    public void addPlayingLevel(Level level){
        GameList.addPlayingLevel(level);
    }

    /**
     * Sets a {@link Level} as the current playing level.
     *
     * @param level the level to be used as the current level
     * @see Level
     */
    public void setCurrentLevel(Level level) {
        this.GameList=(GameLevel) level;
    }
    /**
     * Pass to the next level of the current {@link Level}. Ignores all conditions and skip to the next level.
     */
    public void goNextLevel() {
        GameLevel templevel=(GameLevel)GameList.getNextLevel();
        this.GameList= templevel;
    }
    /**
     * Gets the accumulated points through all levels and current {@link Level}.
     *
     * @return the cumulative points
     */
    public int getCurrentPoints() {
        return Points;
    }



    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0 or the player won the game.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        if (balls<=0){
            return true;
        }
        return false;
    }
    /**
     * Reduces the count of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        if (balls==0){return 0;}
        this.balls=balls-1;
        return balls;
    }
    //Override for GameController Methods
    @Override
    public void addWoodenPoints(WoodenBrick wbrick) {
        this.Points=Points+wbrick.getScore();
        if (Points==GameList.getPoints()){
            this.goNextLevel();
        }

    }

    @Override
    public void addGlassPoints(GlassBrick gbrick) {
        this.Points=Points+gbrick.getScore();
        if (Points==GameList.getPoints()){
            this.goNextLevel();
        }

    }

    @Override
    public void addBall(MetalBrick mbrick) {
        this.balls=balls+1;

    }
    /**
     * Gets the number of points required to pass to the next level. Gets the points obtainable in the current {@link Level}.
     *
     * @return the number of points in the current level
     */
    public int getLevelPoints() {
        return GameList.getPoints();

        }

}
