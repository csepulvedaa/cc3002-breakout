package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

/**Controls the ball Movement
 * @author csepu
 * @version Dic 2018
 *
 */
public class BallControl extends Component {
    private PhysicsComponent physics;

    /**
     * Move ball to the left if it's attached to the bat
     */
    public void left() {

        physics.reposition(new Point2D(entity.getX() - 5, entity.getY()));
        if (entity.getX()==40) {
            physics.reposition(new Point2D(40,480));

        }
    }
    /**
     * Move ball to the right if it's attached to the bat
     */

    public void right() {
        physics.reposition(new Point2D(entity.getX() + 5, entity.getY()));
        if (entity.getX() == 540) {
            physics.reposition(new Point2D(540, 480));

        }
    }}
