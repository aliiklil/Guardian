package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import util.CollisionBox;

public class NPC {
	
	private final int spriteSize = 64;
	
	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
						
	private int health;
	
	private CollisionBox collisionBox;

	private SpriteSheet spriteSheet;
	
	private Animation lookUpAnimation;
	private Animation lookDownAnimation;
	private Animation lookLeftAnimation;
	private Animation lookRightAnimation;
	
	private Animation dieAnimation;

	private Animation currentAnimation;

	public NPC(float x, float y, int health, String spriteSheetPath) throws SlickException {
		
		this.relativeToMapX = x - spriteSize / 4;
		this.relativeToMapY = y - spriteSize / 2;
		
		this.relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		this.relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		this.health = health;
		
		this.spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, true);
		lookDownAnimation = new Animation(spriteSheet, 0, 10, 0, 10, true, 100, true);
		lookLeftAnimation = new Animation(spriteSheet, 0, 9, 0, 9, true, 100, true);
		lookRightAnimation = new Animation(spriteSheet, 0, 11, 0, 11, true, 100, true);
		
		dieAnimation = new Animation(spriteSheet, 0, 20, 5, 20, true, 100, true);
		dieAnimation.setLooping(false);
		
		currentAnimation = lookRightAnimation;
		
		collisionBox = new CollisionBox(relativeToMapX + spriteSize/4, relativeToMapY + spriteSize/2, spriteSize/2, spriteSize/2);
		
	}
	
	public void update() {
				
		if(health <= 0) {
			currentAnimation = dieAnimation;
		}
		
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
																	
	}

	public void render(Graphics g) {
		
		currentAnimation.draw(relativeToScreenX, relativeToScreenY);
				
	}
	
	public void decreaseHealth(int amount) {
		
		health = health - amount;
		
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}
	
	public float getRelativeToMapY() {
		return relativeToMapY;
	}
	
}
