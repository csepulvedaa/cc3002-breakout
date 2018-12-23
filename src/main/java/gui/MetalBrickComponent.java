package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;
import logic.brick.MetalBrick;
/**
 * Class for a METALBRICK component
 * Implements BrickComponent Interface
 *
 * @author csepu
 */

public class MetalBrickComponent extends Component implements BrickComponent{
    private MetalBrick MBrick;
    /**
     * Creates a MetalBrick Component
     * @param brick a logic brick
     */
    public MetalBrickComponent(Brick brick){
        MBrick=(MetalBrick)brick;
    }
    @Override
    public void OnHit(){
        MBrick.hit();
        if (MBrick.remainingHits()==7){
            entity.setViewFromTexture("metal2.png");}

        if(MBrick.remainingHits()==3){
            entity.setViewFromTexture("metal1.png");
        }
        if (MBrick.isDestroyed()){
            entity.removeFromWorld();
        }
    }

    @Override
    public boolean isDestroyed() {
        return MBrick.isDestroyed();
    }
}
