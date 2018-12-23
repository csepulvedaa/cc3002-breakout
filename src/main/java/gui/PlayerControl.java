package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 * Class for Controlling the bat Movement
 * @author csepu
 * @version Dic 2018
 *
 */
public class PlayerControl extends Component {
    private PhysicsComponent physics;

    /**
     * Move bat to the left
     */
public void left() {

        physics.reposition(new Point2D(entity.getX() - 5, entity.getY()));
        if (entity.getX()==0.0) {
            physics.reposition(new Point2D(0.0,500));

        }
    }
    /**
     * Move bat to the right
     */

    public void right() {
        physics.reposition(new Point2D(entity.getX()+5,entity.getY()));
        if (entity.getX()==500) {
            physics.reposition(new Point2D(500,500));

        }

    }
}
