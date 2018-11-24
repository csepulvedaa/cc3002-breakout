package logic.level;

import controller.Game;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import java.util.ArrayList;
import java.util.List;

public class GameLevel implements Level {
    private String Name;
    private Level NextLevel;
    private List<Brick> BrickList = new ArrayList<>();
    private boolean playable;
    private int Points=0;
    private int Goal;
    public GameLevel(){
        Name="";
        playable=false;
        Points=0;
    }
    public GameLevel(String Nombre){
        Name=Nombre;
        playable=true;

    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getNumberOfBricks() {
        return BrickList.size();
    }

    @Override
    public List<Brick> getBricks() {
        return BrickList;
    }

    @Override
    public Level getNextLevel() {
        if (this.getName()=="" && NextLevel==null){
            return this;
        }
        return NextLevel;
    }

    @Override
    public boolean isPlayableLevel() {
        return playable;
    }

    @Override
    public boolean hasNextLevel() {
        if (NextLevel==null){return false;}
        return NextLevel.isPlayableLevel();
    }

    @Override
    public int getPoints() {
        return Goal;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        Level newLevel = level;
        if (this.getName()==""){
            newLevel.setNextLevel(null);
            this.NextLevel=newLevel;
        }
        else{
            newLevel.setNextLevel(this.NextLevel);
            this.NextLevel=newLevel;
        }
        return NextLevel;
    }

    @Override
    public void setNextLevel(Level level) {
        this.NextLevel=level;

    }
    public  void addGlassBricks(int numberOfGlassBricks){
        for (int i=0;i<numberOfGlassBricks;i++){
            GlassBrick g = new GlassBrick();
            this.BrickList.add(g);
            this.Goal=Goal+50;
        }
    }
    public void addMetalBricks(int numbeOfMetalbricks){
        for (int i=0;i<numbeOfMetalbricks;i++){
            MetalBrick m = new MetalBrick();
            this.BrickList.add(m);
        }}

    public void addWoodenBricks(int numberOWoodenbricks){
        for (int i=0;i<numberOWoodenbricks;i++){
            WoodenBrick w = new WoodenBrick();
            this.BrickList.add(w);
            this.Goal=Goal+200;
        }}

    public int getCurrentPoints() {
        return this.Points;
    }

    public void Connect(Game game,int numberOfBricks) {
        for (int i=0;i<numberOfBricks;i++){

        }

    }
}
