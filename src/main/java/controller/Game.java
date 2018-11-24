package controller;

import logic.brick.Brick;
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
public class Game implements Observer {
    private GameLevel GameList=new GameLevel();
    private int balls;
    private int Points;


    public Game(int balls) {
        this.balls=balls;


    }
    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        GameLevel NewLevel = new GameLevel(name);
        double numberOfMetalBricks=NumberOfMetal(probOfMetal,numberOfBricks,seed);
        double numberOfGlassBricks=NumberOfGlass(probOfGlass,numberOfBricks,seed);
        double numberOfWoodenBricks=numberOfBricks-numberOfGlassBricks;
        NewLevel.addGlassBricks((int) numberOfGlassBricks);
        NewLevel.addWoodenBricks((int)numberOfWoodenBricks);
        NewLevel.addMetalBricks((int)numberOfMetalBricks);
        return NewLevel;
    }
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
            double r =O.nextDouble();
            if (r<=probOfMetal){
                numberOfMetalBricks++; }
        }
        return numberOfMetalBricks;

    }

    public double NumberOfGlass(double probOfGlass, int numberOfBricks,int seed) {
        Random O =new Random(seed);
        double numberOfGlassBricks = 0;
        if (probOfGlass == 0) {
            return numberOfGlassBricks;
        }
        if (probOfGlass == 1) {
            return numberOfBricks;
        }
        for (int j=0;j<numberOfBricks;j++){
            double r =O.nextDouble();
            if (r<=probOfGlass){
                numberOfGlassBricks++; }
        }
        return numberOfGlassBricks;

    }

    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        GameLevel NewLevel = new GameLevel(name);
        double numberOfGlassBricks=NumberOfGlass(probOfGlass,numberOfBricks,seed);
        double numberOfWoodenBricks=numberOfBricks-numberOfGlassBricks;
        NewLevel.addGlassBricks((int) numberOfGlassBricks);
        NewLevel.addWoodenBricks((int)numberOfWoodenBricks);
        NewLevel.Connect(this,numberOfBricks);
        return NewLevel;

    }


    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return false;
    }

    public String getLevelName() {
        return GameList.getName();
    }

    public int getBallsLeft() {
        return this.balls;
    }

    public boolean hasNextLevel() {
       return GameList.hasNextLevel();

    }

    public boolean hasCurrentLevel() {
        return GameList.isPlayableLevel();
    }

    public int numberOfBricks() {
        return GameList.getNumberOfBricks();
    }

    public List<Brick> getBricks() {
        return GameList.getBricks();
    }

    public Level getCurrentLevel() {
        return GameList;
    }
    public void addPlayingLevel(Level level){
        GameList.addPlayingLevel(level);
    }

    public void setCurrentLevel(Level level) {
        this.GameList=(GameLevel) level;
    }

    public void goNextLevel() {
        this.GameList= (GameLevel)GameList.getNextLevel();
    }

    public int getCurrentPoints() {
        int points = this.Points;
        return points;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer){
            int a=(Integer)arg;
            this.Points=this.getCurrentPoints()+a;
            System.out.println("Se sumaron"+a+"puntos");
        }


    }
}
