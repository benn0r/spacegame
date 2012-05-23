package ch.bbbaden.space.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import ch.bbbaden.space.Entity;
import ch.bbbaden.space.Game;
import ch.bbbaden.space.GameObject;
import ch.bbbaden.space.Space;

public class Player extends Entity implements GameObject {
	
	Image spaceship;
	
	int lastShot = 0;
	
	String name;
	int keyUp;
	int keyDown;
	int keyLeft;
	int keyRight;
	int keyShoot;
		
	public Player(Space space, String name, int keyUp, int keyDown, 
			int keyLeft, int keyRight, int keyShoot) throws SlickException {
		super(space);
		
		spaceship = new Image("images/player.png");
		
		x = Space.SCREEN_WIDTH / 2;
		y = Space.SCREEN_HEIGHT / 2;
		size = (spaceship.getWidth() + spaceship.getHeight()) / 4;
		
		this.name = name;
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
		this.keyShoot = keyShoot;
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (boom) {
			return;
		}
		
		Input input = container.getInput();
		
		if (input.isKeyDown(keyUp)) {
			y -= Game.PLAYER_SPEED * delta;
		} else if (input.isKeyDown(keyDown)) {
			y += Game.PLAYER_SPEED * delta;
		}
		
		if (input.isKeyDown(keyLeft)) {
			x -= Game.PLAYER_SPEED * delta;
		} else if (input.isKeyDown(keyRight)) {
			x += Game.PLAYER_SPEED * delta;
		}
		
		if (y < 0) {
			y = 0;
		}
		if (x < 0) {
			x = Space.SCREEN_WIDTH - spaceship.getWidth();
		}
		
		if (y + spaceship.getHeight() > Space.SCREEN_HEIGHT) {
			y = Space.SCREEN_HEIGHT - spaceship.getHeight();
		}
		if (x + spaceship.getWidth() > Space.SCREEN_WIDTH) {
			x = 0;
		}
		
		lastShot += delta;
		if (input.isKeyDown(keyShoot) && lastShot > 200) {
			try {
				lastShot = 0;
				if (name.equals("A")) {
					space.pointsA -= 10;
				} else {
					space.pointsB -= 10;
				}
				space.add(new Shot(space, x + spaceship.getWidth() / 2, y, name));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		for (GameObject object : space.objects) {
			if (((Entity)object).boom == false &&
					((Entity)object).getType().equals("Meteor") &&
					collision((Entity)object)) {
//				space.pause = true;
				boom = true;
				return;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		//Font font = new UnicodeFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 14));
		//g.setFont(new UnicodeFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 14)));
		//g.setColor(Color.white);
		//g.drawString(name, x, y - spaceship.getHeight());
		if (!boom) {
			spaceship.draw(x, y);
		}
	}

}
