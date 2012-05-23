package ch.bbbaden.space.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import ch.bbbaden.space.Entity;
import ch.bbbaden.space.Game;
import ch.bbbaden.space.GameObject;
import ch.bbbaden.space.Space;

public class Shot extends Entity implements GameObject {
	
	Image image;
	Sound sound;
	String player;

	public Shot(Space space, float x, float y, String player) throws SlickException {
		super(space);
		
		this.x = x;
		this.y = y;
		
		image = new Image("images/shot.png");
		size = (image.getWidth() + image.getHeight()) / 2;
		
		sound = new Sound("sounds/laser.wav");
		sound.play();
		
		this.player = player;
	}

	@Override
	public void update(GameContainer container, int delta) {
		y -= Game.SHOT_SPEED * delta;
		
		for (GameObject object : space.objects) {
			if (((Entity)object).boom == false &&
					((Entity)object).getType().equals("Meteor") &&
					collision((Entity)object)) {
				((Entity)object).boom = true;
				if (player.equals("A")) {
					((Meteor)object).points = 50 + (((Meteor)object).mType * 10);
					space.pointsA += 50 + (((Meteor)object).mType * 10);
				} else {
					((Meteor)object).points = 50 + (((Meteor)object).mType * 10);
					space.pointsB += 50 + (((Meteor)object).mType * 10);
				}
				space.objects.remove(this);
				return;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		image.draw(x, y);
	}
	
	@Override
	public String getType() {
		return "Shot";
	}

}
