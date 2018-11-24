package logic.level;

import logic.brick.Brick;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameLevelTest {
    private GameLevel Nivel0,Nivel1,Nivel2;
    private List<Brick>  b1;


    @Before
    public void setUp() throws Exception {
        Nivel0= new GameLevel();
        Nivel1=new GameLevel("1");
        Nivel2=new GameLevel("1");
        Nivel0.addPlayingLevel(Nivel1);
        b1=new ArrayList<>();

    }

    @Test
    public void getName() {
        assertEquals("",Nivel0.getName());
    }

    @Test
    public void getNumberOfBricks() {
        assertEquals(0,Nivel0.getNumberOfBricks());
    }

    @Test
    public void getBricks() {
        assertEquals(b1,Nivel0.getBricks());
    }

    @Test
    public void getNextLevel() {
        assertEquals(Nivel1,Nivel0.getNextLevel());
    }

    @Test
    public void isPlayableLevel() {
        assertTrue(Nivel1.isPlayableLevel());
        assertFalse(Nivel0.isPlayableLevel());
    }

    @Test
    public void hasNextLevel() {
        assertTrue(Nivel0.hasNextLevel());
        assertFalse(Nivel1.hasNextLevel());
    }

    @Test
    public void getPoints() {
        assertEquals(0,Nivel0.getPoints());
        assertEquals(0,Nivel0.getPoints());
    }

    @Test
    public void addPlayingLevel() {
        Nivel1.addPlayingLevel(Nivel2);
        assertEquals(Nivel1.getNextLevel(),Nivel2);


    }}