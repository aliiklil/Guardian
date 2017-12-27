package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import util.CollisionBox;

public class Arrow {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
	
	private int spriteWidth = 33;
	private int spriteHeight = 5;
	
	private SpriteSheet spriteSheet = new SpriteSheet("resources/arrow.png", 33, 5);
	
	private int velocity = 1;
	
	private int direction;

	public Arrow(float x, float y, int direction) throws SlickException {
		
		this.relativeToMapX = x - spriteWidth/2;
		this.relativeToMapY = y - spriteHeight/2;
		
		this.relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		this.relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		this.direction = direction;
		
		if(direction == 0) {
			spriteSheet.rotate(90);
		} else if(direction == 1) {
			spriteSheet.rotate(270);
		} else if(direction == 2) {
			spriteSheet.rotate(0);
		} else if(direction == 3) {
			spriteSheet.rotate(180);
		} else {
			throw(new IllegalArgumentException("Direction must be an integer from 0 to 3"));
		}
		
	}

	public void update() {
		
		if(direction == 0) {
			relativeToMapY = relativeToMapY - velocity;
		} else if(direction == 1) {
			relativeToMapY = relativeToMapY + velocity;
		} else if(direction == 2) {
			relativeToMapX = relativeToMapX - velocity;
		} else if(direction == 3) {
			relativeToMapX = relativeToMapX + velocity;
		} else {
			throw(new IllegalArgumentException("Direction must be an integer from 0 to 3"));
		}
		
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
	}
	
	public void render(Graphics g) {
		spriteSheet.draw(relativeToScreenX, relativeToScreenY);
	}
	
}