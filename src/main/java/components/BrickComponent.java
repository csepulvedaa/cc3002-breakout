package components;

/**Interface thar represents a Brick Logic Component
 * @author csepu
 * @version Dic 2018
 *
 */
public interface BrickComponent {
     /**
      * Hits a Logic Brick COmponent
      *
      */
     void OnHit();

     /**
      * Gets Whether the brick object is destroyed or not
      *
      * @return true is the brick is destroyed, false otherwise
      */
     boolean isDestroyed();
}
