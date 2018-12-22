package components;

import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;
import logic.brick.WoodenBrick;
/**
 * Class for a WOODENBRICK component
 * Implements BrickComponent Interface
 *
 * @author csepu
 */

public class WoodenBrickComponent extends Component implements BrickComponent {
    private WoodenBrick WBrick;

    public WoodenBrickComponent(Brick brick) {
        WBrick=(WoodenBrick)brick;
    }
    @Override
    public void OnHit(){
        WBrick.hit();
        if (WBrick.remainingHits()==2){
        entity.setViewFromTexture("wooden2.png");}

        if(WBrick.remainingHits()==1){
            entity.setViewFromTexture("wooden1.png");
        }
        if (WBrick.isDestroyed()){
            entity.removeFromWorld();
        }
    }

    @Override
    public boolean isDestroyed() {
        return WBrick.isDestroyed();
    }
}
