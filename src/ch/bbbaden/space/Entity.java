package ch.bbbaden.space;

public class Entity {
	
	public float x;
	
	public float y;
	
	public int size;
	
	public Space space;
	
	public boolean boom = false;
	
	public Entity(Space space) {
		this.space = space;
	}
	
	public String getType() {
		return "Entity";
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean collision(Entity entity) {
		float a = this.y - entity.y;
		float b = this.x - entity.x;
		float c = (float)Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		
		if (c < getSize() + entity.getSize()) {
			return true;
		} else {
			return false;
		}
	}

}
