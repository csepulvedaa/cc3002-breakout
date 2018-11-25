package logic.brick;

import logic.level.GameLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BrickTest {
    private GlassBrick  glassBrick;
    private WoodenBrick woodenBrick;
    private MetalBrick metalBrick;

    @Before
    public void setUp() throws Exception {
    glassBrick=new GlassBrick();
    woodenBrick=new WoodenBrick();
    metalBrick=new MetalBrick();
    }

    @Test
    public void hit() {
        glassBrick.hit();;
        assertEquals(glassBrick.remainingHits(),0);
        metalBrick.hit();;
        assertEquals(metalBrick.remainingHits(),9);
        woodenBrick.hit();
        woodenBrick.hit();
        assertEquals(woodenBrick.remainingHits(),1);
        woodenBrick.hit();
        assertTrue(woodenBrick.isDestroyed());
    }

    @Test
    public void isDestroyed() {
        glassBrick.hit();;
        assertTrue(glassBrick.isDestroyed());
        metalBrick.hit();;
        assertFalse(metalBrick.isDestroyed());
        woodenBrick.hit();
        woodenBrick.hit();
        assertFalse(woodenBrick.isDestroyed());
        woodenBrick.hit();
        assertTrue(woodenBrick.isDestroyed());

    }

    @Test
    public void getScore() {
        assertEquals(50,glassBrick.getScore());
        assertEquals(200,woodenBrick.getScore());
        assertEquals(0,metalBrick.getScore());
    }}

