package util;

import org.newdawn.slick.geom.Rectangle;

public class CollisionBox extends Rectangle {

	public CollisionBox(float x, float y, float width, float height) {
		super(x, y, width, height);
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
