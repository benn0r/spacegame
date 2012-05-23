package ch.bbbaden.space;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ch.bbbaden.space.entities.Meteor;
import ch.bbbaden.space.entities.Player;

public class Space extends Game {
	
	Image background;
	int frame;
	public int pointsA;
	public int pointsB;
	public boolean pause = false;
	
	public Player pa;
	public Player pb;
	
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		background = new Image("images/background1.png");
		
		objects.add(pa = new Player(this, "A", 
			Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE));
		
		objects.add(pb = new Player(this, "B",
			Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_RSHIFT));
		
		pa.x = Space.SCREEN_WIDTH / 2 - 100; pa.y = Space.SCREEN_HEIGHT - 100;
		pb.x = Space.SCREEN_WIDTH / 2 + 100; pb.y = Space.SCREEN_HEIGHT - 100;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.draw();
		
		for(GameObject object : objects) {
			object.render(container, g);
		}
		
		g.setColor(Color.green);
		g.drawString(pointsA + "", 10, 30);
		g.setColor(Color.blue);
		g.drawString(pointsB + "", 70, 30);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_P)) {
			pause = pause ? false : true;
		}
		
		if (pa.boom && pb.boom) {
			pause = true;
		}
		
		if (!pause) {
			frame++;
			
			for(int i = objects.size() - 1; i >= 0; i--) {
				objects.get(i).update(container, delta);
			}
			
			// spawn meteors
			if (frame % SPAWN_SPEED == 0) {
				Meteor meteor = new Meteor((int)(Math.random() * 3), this);
				meteor.y = -100;
				meteor.x = (int)(Math.random() * SCREEN_WIDTH);
				meteor.rotation = Math.random() > 0.5 ? 1 : -1;
				meteor.speed = (float) (Game.METEOR_SPEED + Math.random() / 4);
				
				objects.add(meteor);
			}
		}
	}
	
	public void add(GameObject object) {
		objects.add(object);
	}

}
