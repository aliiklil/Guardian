package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import util.CollisionBox;

public class NPC {
	
	private final int spriteSize = 64;
	
	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
						
	private final int maxHealth;
	private int currentHealth;
	
	private CollisionBox collisionBox;

	private SpriteSheet spriteSheet;
	private SpriteSheet bloodSpriteSheet;
	
	private Animation lookUpAnimation;
	private Animation lookDownAnimation;
	private Animation lookLeftAnimation;
	private Animation lookRightAnimation;
	
	private Animation dieAnimation;

	private Animation currentAnimation;
	
	private HealthBar healthBar;
	
	private boolean alive;
	
	private Animation bloodAnimation;
	private boolean drawBlood;
	
	public NPC(float x, float y, int maxHealth, String spriteSheetPath) throws SlickException {
		
		this.relativeToMapX = x - spriteSize / 4;
		this.relativeToMapY = y - spriteSize / 2;
		
		this.relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		this.relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		
		this.spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, true);
		lookDownAnimation = new Animation(spriteSheet, 0, 10, 0, 10, true, 100, true);
		lookLeftAnimation = new Animation(spriteSheet, 0, 9, 0, 9, true, 100, true);
		lookRightAnimation = new Animation(spriteSheet, 0, 11, 0, 11, true, 100, true);
		
		dieAnimation = new Animation(spriteSheet, 0, 20, 5, 20, true, 100, true);
		dieAnimation.setLooping(false);
		
		currentAnimation = lookRightAnimation;
		
		collisionBox = new CollisionBox(relativeToMapX + spriteSize/4, relativeToMapY + spriteSize/2, spriteSize/2, spriteSize/2);
		
		healthBar = new HealthBar(relativeToScreenX, relativeToScreenY, spriteSize, 5, 1, maxHealth);
		
		alive = true;
		
		bloodSpriteSheet = new SpriteSheet("resources/blood.png", 64, 64);
		
		bloodAnimation = new Animation(bloodSpriteSheet, 0, 0, 5, 0, true, 100, true);
		
		bloodAnimation.setLooping(false);
		
		drawBlood = false;
		
	}
	
	public void update() {
				
		if(currentHealth <= 0) {
			currentAnimation = dieAnimation;
		}
		
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		
		healthBar.setX(relativeToScreenX);
		healthBar.setY(relativeToScreenY);
																			
	}

	public void render(Graphics g) {
		
		currentAnimation.draw(relativeToScreenX, relativeToScreenY);

		if(currentHealth > 0) {
			healthBar.render(g);
		}
		
		if(drawBlood) {
			bloodAnimation.draw(relativeToScreenX, relativeToScreenY);
			if(bloodAnimation.isStopped()) {
				drawBlood = false;
				bloodAnimation.restart();
			}
		}
		
	}
	
	public void decreaseHealth(int amount) {
		
		if(alive) {
			
			currentHealth = currentHealth - amount;
			
			if(currentHealth <= 0) {
				currentHealth = 0;
				alive = false;
			}
			
			healthBar.setCurrentHealth(currentHealth);
			
			drawBlood = true;
		
		}
		
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}
	
	public float getRelativeToMapY() {
		return relativeToMapY;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
