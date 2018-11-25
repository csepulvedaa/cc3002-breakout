package logic.level;

import controller.Game;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**Level Class
 * Implements level interface contains methods for a linked level list
 * @author csepu
 * @version  nov 2018
 */

public class GameLevel implements Level {
    private String Name;
    private Level NextLevel;
    private List<Brick> BrickList = new ArrayList<>();
    private boolean playable;
    private int Goal;

    /**
     * Default Constructor For a Level
     */
    public GameLevel(){
        Name="";
        playable=false;
    }

    /**
     * Constructor for a level with string
     * @param Nombre Level Name
     */
    public GameLevel(String Nombre){
        Name=Nombre;
        playable=true;

    }

    /**
     * sets te level name
     *
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }
    //Override for Level Interface Methods
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
        newLevel.setNextLevel(null);
        if (this.getNextLevel()==null){
            this.setNextLevel(level);
        }
        else{
            newLevel.setNextLevel(this.NextLevel);
            this.setNextLevel(newLevel);
        }
        return NextLevel;

    }

    @Override
    public void setNextLevel(Level level) {
        this.NextLevel=level;

    }

    /**
     * Add GlassBricks To Level and connects them with observer
     * @param numberOfGlassBricks Number Of Glassbricks to add
     * @param O Game Observer
     */

    public  void addGlassBricks(int numberOfGlassBricks, Observer O){
        for (int i=0;i<numberOfGlassBricks;i++){
            GlassBrick g = new GlassBrick();
            g.connect(O);
            this.BrickList.add(g);
            this.Goal=Goal+50;
        }
    }

    /**
     * Add Metalbricks To Level and connects them with observer
     * @param numbeOfMetalbricks Number Of MetalBricks to add
     * @param O Observer
     */
    public void addMetalBricks(int numbeOfMetalbricks,Observer O){
        for (int i=0;i<numbeOfMetalbricks;i++){
            MetalBrick m = new MetalBrick();
            m.connect(O);
            this.BrickList.add(m);
        }}

    /**
     * Add WoodenBricks To Level and connects them with observer
     * @param numberOWoodenbricks Number Of WoodenBriks to add
     * @param O Observer
     */
    public void addWoodenBricks(int numberOWoodenbricks,Observer O){

        for (int i=0;i<numberOWoodenbricks;i++){
            WoodenBrick w = new WoodenBrick();
            w.connect(O);
            this.BrickList.add(w);
            this.Goal=Goal+200;
        }}





}
