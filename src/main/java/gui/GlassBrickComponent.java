package gui;


import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.particle.ParticleEmitter;
import logic.brick.Brick;
import logic.brick.GlassBrick;

/**
 * Class for a GLASSBRICK component
 * Implements BrickComponent Interface
 *
 * @author csepu
 */


public class GlassBrickComponent extends Component implements BrickComponent {
    private GlassBrick GBrick;
    private ParticleEmitter emitter;

    /**
     * Creates a Glassbrick Component
     * @param brick a logic brick
     */
    public GlassBrickComponent(Brick brick){
        GBrick=(GlassBrick)brick;
    }



    @Override
    public void OnHit(){
        GBrick.hit();

        if (GBrick.isDestroyed()){


            entity.removeFromWorld();
        }
    }
    public boolean isDestroyed(){
        return GBrick.isDestroyed();
    }
}
