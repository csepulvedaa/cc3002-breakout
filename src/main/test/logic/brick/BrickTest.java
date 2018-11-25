package logic.brick;

import logic.level.GameLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BrickTest {
    private GlassBrick;
    private WoodenBrick;
    private MetalBrick;

    @Before
    public void setUp() throws Exception {
        Nivel0= new GameLevel();
        Nivel1=new GameLevel("1");
        Nivel2=new GameLevel("1");
        Nivel0.addPlayingLevel(Nivel1);
        b1=new ArrayList<>();

    }

    @Test
    public void hit() {
    }

    @Test
    public void isDestroyed() {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void remainingHits() {
    }

    @Test
    public void accept() {
    }

    @Test
    public void connect() {
    }
}