package ch.bbbaden.space;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.SlickException;

/**
 * @author avi
 */
abstract public class Game extends BasicGame {

    public final static int SCREEN_WIDTH = 640;
    public final static int SCREEN_HEIGHT = 480;
    
    public final static float PLAYER_SPEED = 0.5f;
    public final static float SHOT_SPEED = 0.7f;
    public final static float METEOR_SPEED = 0.01f;
    
    public final static int ROTATION_SPEED = 10;
    public final static int SPAWN_SPEED = 5; // every x frame
    
    public Game() {
        super("iSpace");
    }
    
    public static void main(String[] args) throws SlickException {
    	Space space = new Space();
        AppGameContainer app = new AppGameContainer(space);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.setTargetFrameRate(60);
        app.setMouseGrabbed(false);
        app.start();
    }

}