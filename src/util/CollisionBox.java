package util;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class CollisionBox extends Rectangle {

	public CollisionBox(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public boolean willIntersectUp(Shape s, float distance) {
		
		CollisionBox collisionBox = new CollisionBox(this.getX(), this.getY() - distance, this.getWidth(), this.getHeight());
		
		if(collisionBox.intersects(s)){
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean willIntersectDown(Shape s, float distance) {
		
		CollisionBox collisionBox = new CollisionBox(this.getX(), this.getY() + distance, this.getWidth(), this.getHeight());
		
		if(collisionBox.intersects(s)){
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean willIntersectLeft(Shape s, float distance) {
		
		CollisionBox collisionBox = new CollisionBox(this.getX() - distance, this.getY(), this.getWidth(), this.getHeight());
		
		if(collisionBox.intersects(s)){
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean willIntersectRight(Shape s, float distance) {
		
		CollisionBox collisionBox = new CollisionBox(this.getX() + distance, this.getY(), this.getWidth(), this.getHeight());
		
		if(collisionBox.intersects(s)){
			return true;
		} else {
			return false;
		}
		
	}
	
	public float getTopLeftX() {
		return this.getX();
	}
	
	public float getTopLeftY() {
		return this.getY();
	}
	
	public float getTopRightX() {
		return this.getX() + this.getWidth();
	}
	
	public float getTopRightY() {
		return this.getY();
	}
	
	public float getBottomLeftX() {
		return this.getX();
	}
	
	public float getBottomLeftY() {
		return this.getY() + this.getHeight();	
	}

	public float getBottomRightX() {
		return this.getX() + this.getWidth();	
	}
	
	public float getBottomRightY() {
		return this.getY() + this.getHeight();	
	}
	
}
