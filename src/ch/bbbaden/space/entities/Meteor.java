package ch.bbbaden.space.entities;

import javax.xml.soap.Text;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import ch.bbbaden.space.Entity;
import ch.bbbaden.space.Game;
import ch.bbbaden.space.GameObject;
import ch.bbbaden.space.Space;

public class Meteor extends Entity implements GameObject {
	
	Image image;
	Types type;
	int lastRotation = 0;
	public int rotation = 1;
	
	SpriteSheet sprite = new SpriteSheet(new Image("images/explosion.png"), 64, 64);
	int spriteX = 0;
	int spriteY = 0;
	float textY = 0;
	int frame = 0;
	public int points = 50;
	public int mType;
	public float speed;
	
	public enum Types {
		Large,
		Medium, 
		Small};

	public Meteor(Types type, Space space) throws SlickException {
		super(space);
		switch (type) {
			case Large:
				this.mType = 0;
				image = new Image("images/meteor0.png");
				break;
			case Medium:
				this.mType = 1;
				image = new Image("images/meteor1.png");
				break;
			case Small:
				this.mType = 2;
				image = new Image("images/meteor2.png");
				break;
		}
		
		size = (image.getWidth() + image.getHeight()) / 4;
	}

	public Meteor(int i, Space space) throws SlickException {
		super(space);
		switch (i) {
			case 0:
				this.mType = 0;
				image = new Image("images/meteor0.png");
				break;
			case 1:
				this.mType = 1;
				image = new Image("images/meteor1.png");
				break;
			case 2:
				this.mType = 2;
				image = new Image("images/meteor2.png");
				break;
		}
		
		size = (image.getWidth() + image.getHeight()) / 4;
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (y - 100 > Space.SCREEN_HEIGHT) {
			if (!space.pa.boom) {
				space.pointsA -= 10;
			}
			if (!space.pb.boom) {
				space.pointsB -= 10;
			}
			space.objects.remove(this);
		}
		
		if (boom == true) {
			
			if (spriteX >= 3 && spriteY >= 3) {
				space.objects.remove(this);
			}
		} else {
			y += speed * delta;
			lastRotation += delta + (Math.random() * 100);
			
			if (lastRotation >= Game.ROTATION_SPEED) {
				image.rotate(rotation);
				lastRotation = 0;
			}
		}
	}
	
	void boom() {
		if (spriteY >= 3 && spriteX >= 3) {
			return;
		}
		if (spriteX >= 3) {
			spriteX = 0;
			spriteY++;
		} else {
			spriteX++;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		frame++;
		image.draw(x, y);
		
		if (boom == true) {
			if (spriteX == 0 && spriteY == 0) {
//				x -= (64 - getSize()) / 2;
//				y -= (64 - getSize()) / 2;
			}
			image = sprite.getSprite(spriteX, spriteY);
			if (frame % 5 == 0) {
				boom();
			}
			
			if (textY == 0) {
				Sound sound;
				try {
					sound = new Sound("sounds/explosion.wav");
					sound.play();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textY = y;
			}
			if (y - textY < 10) {
				g.setColor(Color.green);
				g.drawString("+" + points, x, textY);
			}
			
			if (frame % 5 == 0) {
				textY--;
			}
		}
	}
	
	public String getType() {
		return "Meteor";
	}

}
