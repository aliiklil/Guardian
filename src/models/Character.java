package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import util.CollisionBox;

public abstract class Character {

	private final int spriteSize = 64;
	private final int overSizeSpriteSize = 192;

	private float movementSpeed = 3f;
	private float diagonalMovementSpeed = (float) (1/Math.sqrt(Math.pow(movementSpeed, 2) + Math.pow(movementSpeed, 2))) * movementSpeed * movementSpeed;
	
	private float relativeToMapX;
	private float relativeToMapY;
	
	private boolean alive = true;
	
	private HealthBar healthBar;
	
	private SpriteSheet bloodSpriteSheet;
	private Animation bloodAnimation;
	private boolean drawBlood;
	
	private CollisionBox environmentCollisionBox;
	private CollisionBox characterCollisionBox;

	private CollisionBox attackUpCollisionBox;
	private CollisionBox attackDownCollisionBox;
	private CollisionBox attackLeftCollisionBox;
	private CollisionBox attackRightCollisionBox;	

	private SpriteSheet spriteSheet;
	private SpriteSheet overSizeSpriteSheet;
	
	private Animation lookUpAnimation;
	private Animation lookDownAnimation;
	private Animation lookLeftAnimation;
	private Animation lookRightAnimation;
	
	private Animation goUpAnimation;
	private Animation goDownAnimation;
	private Animation goLeftAnimation;
	private Animation goRightAnimation;
	
	private Animation attackUpAnimation;
	private Animation attackDownAnimation;
	private Animation attackLeftAnimation;
	private Animation attackRightAnimation;

	private Animation shootUpAnimation;
	private Animation shootDownAnimation;
	private Animation shootLeftAnimation;
	private Animation shootRightAnimation;
	
	private Animation spellUpAnimation;
	private Animation spellDownAnimation;
	private Animation spellLeftAnimation;
	private Animation spellRightAnimation;
	
	private Animation dieAnimation;
	
	private Animation currentAnimation;
		
	public Character(float relativeToMapX, float relativeToMapY, String spriteSheetPath) throws SlickException {
		
		this.relativeToMapX = relativeToMapX;
		this.relativeToMapY = relativeToMapY;
				
		bloodSpriteSheet = new SpriteSheet("resources/blood.png", 64, 64);
		bloodAnimation = new Animation(bloodSpriteSheet, 0, 0, 5, 0, true, 100, true);
		bloodAnimation.setLooping(false);
		drawBlood = false;
		
		attackUpCollisionBox = new CollisionBox(relativeToMapX - 28, relativeToMapY - 37, 89, 38);
		attackDownCollisionBox =  new CollisionBox(relativeToMapX - 28, relativeToMapY + 12, 89, 38);
		attackLeftCollisionBox = new CollisionBox(relativeToMapX - 67, relativeToMapY - 16, 68, 36);
		attackRightCollisionBox =  new CollisionBox(relativeToMapX + 31, relativeToMapY - 16, 68, 36);
		
		spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		overSizeSpriteSheet = new SpriteSheet(spriteSheetPath, 192, 192);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, true);
		lookDownAnimation = new Animation(spriteSheet, 0, 10, 0, 10, true, 100, true);
		lookLeftAnimation = new Animation(spriteSheet, 0, 9, 0, 9, true, 100, true);
		lookRightAnimation = new Animation(spriteSheet, 0, 11, 0, 11, true, 100, true);
		
		goUpAnimation = new Animation(spriteSheet, 1, 8, 8, 8, true, 100, true);
		goDownAnimation = new Animation(spriteSheet, 1, 10, 8, 10, true, 100, true);
		goLeftAnimation = new Animation(spriteSheet, 1, 9, 8, 9, true, 100, true);
		goRightAnimation = new Animation(spriteSheet, 1, 11, 8, 11, true, 100, true);
		
		attackUpAnimation = new Animation(overSizeSpriteSheet, 0, 7, 5, 7, true, 100, true);
		attackDownAnimation = new Animation(overSizeSpriteSheet, 0, 9, 5, 9, true, 100, true);
		attackLeftAnimation = new Animation(overSizeSpriteSheet, 0, 8, 5, 8, true, 100, true);
		attackRightAnimation = new Animation(overSizeSpriteSheet, 0, 10, 5, 10, true, 100, true);

		shootUpAnimation = new Animation(spriteSheet, 0, 16, 11, 16, true, 100, true);
		shootDownAnimation = new Animation(spriteSheet, 0, 18, 11, 18, true, 100, true);
		shootLeftAnimation = new Animation(spriteSheet, 0, 17, 11, 17, true, 100, true);
		shootRightAnimation = new Animation(spriteSheet, 0, 19, 11, 19, true, 100, true);
		
		spellUpAnimation = new Animation(spriteSheet, 0, 0, 6, 0, true, 100, true);
		spellDownAnimation = new Animation(spriteSheet, 0, 2, 6, 2, true, 100, true);
		spellLeftAnimation = new Animation(spriteSheet, 0, 1, 6, 1, true, 100, true);
		spellRightAnimation = new Animation(spriteSheet, 0, 3, 6, 3, true, 100, true);
		
		dieAnimation = new Animation(spriteSheet, 0, 20, 5, 20, true, 100, true);

		attackUpAnimation.setLooping(false);
		attackDownAnimation.setLooping(false);
		attackLeftAnimation.setLooping(false);
		attackRightAnimation.setLooping(false);
		
		shootUpAnimation.setLooping(false);
		shootDownAnimation.setLooping(false);
		shootLeftAnimation.setLooping(false);
		shootRightAnimation.setLooping(false);
		
		spellUpAnimation.setLooping(false);
		spellDownAnimation.setLooping(false);
		spellLeftAnimation.setLooping(false);
		spellRightAnimation.setLooping(false);
		
		dieAnimation.setLooping(false);
		
		currentAnimation = lookDownAnimation;
		
	}
	
	public abstract void update() throws SlickException;
	
	public abstract void render(Graphics g);
	
	public void decreaseHealth(int amount) {
		
		if(alive) {
			
			healthBar.setCurrentHealth(getHealthBar().getCurrentHealth() - amount);
			
			if(healthBar.getCurrentHealth() <= 0) {
				healthBar.setCurrentHealth(0);
				currentAnimation = dieAnimation;
				alive = false;
			}
						
			drawBlood = true;
		
		}
		
	}
	
	public void drawBlood(float screenRelativeX, float screenRelativeY) {
		
		bloodAnimation.draw(screenRelativeX, screenRelativeY);
		if(bloodAnimation.isStopped()) {
			drawBlood = false;
			bloodAnimation.restart();
		}
		
	}

	public int getSpriteSize() {
		return spriteSize;
	}

	public int getOverSizeSpriteSize() {
		return overSizeSpriteSize;
	}
	
	public float getMovementSpeed() {
		return movementSpeed;
	}
	
	public float getDiagonalMovementSpeed() {
		return diagonalMovementSpeed;
	}
	
	public float getRelativeToMapX() {
		return relativeToMapX;
	}
	
	public void setRelativeToMapX(float relativeToMapX) {
		this.relativeToMapX = relativeToMapX;
	}

	public float getRelativeToMapY() {
		return relativeToMapY;
	}
	
	public void setRelativeToMapY(float relativeToMapY) {
		this.relativeToMapY = relativeToMapY;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}
	
	public void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	public Animation getBloodAnimation() {
		return bloodAnimation;
	}
	
	public boolean isDrawBlood() {
		return drawBlood;
	}
	
	public void setDrawBlood(boolean drawBlood) {
		this.drawBlood = drawBlood;
	}

	public CollisionBox getEnvironmentCollisionBox() {
		return environmentCollisionBox;
	}

	public void setEnvironmentCollisionBox(CollisionBox environmentCollisionBox) {
		this.environmentCollisionBox = environmentCollisionBox;
	}
	
	public CollisionBox getCharacterCollisionBox() {
		return characterCollisionBox;
	}
	
	public void setCharacterCollisionBox(CollisionBox characterCollisionBox) {
		this.characterCollisionBox = characterCollisionBox;
	}
	
	public CollisionBox getAttackUpCollisionBox() {
		return attackUpCollisionBox;
	}

	public void setAttackUpCollisionBox(CollisionBox attackUpCollisionBox) {
		this.attackUpCollisionBox = attackUpCollisionBox;
	}

	public CollisionBox getAttackDownCollisionBox() {
		return attackDownCollisionBox;
	}

	public void setAttackDownCollisionBox(CollisionBox attackDownCollisionBox) {
		this.attackDownCollisionBox = attackDownCollisionBox;
	}

	public CollisionBox getAttackLeftCollisionBox() {
		return attackLeftCollisionBox;
	}

	public void setAttackLeftCollisionBox(CollisionBox attackLeftCollisionBox) {
		this.attackLeftCollisionBox = attackLeftCollisionBox;
	}

	public CollisionBox getAttackRightCollisionBox() {
		return attackRightCollisionBox;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public SpriteSheet getOverSizeWeaponSpriteSheet() {
		return overSizeSpriteSheet;
	}

	public void setOverSizeSpriteSheet(SpriteSheet overSizeSpriteSheet) {
		this.overSizeSpriteSheet = overSizeSpriteSheet;
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(Animation currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public Animation getLookUpAnimation() {
		return lookUpAnimation;
	}

	public Animation getLookDownAnimation() {
		return lookDownAnimation;
	}

	public Animation getLookLeftAnimation() {
		return lookLeftAnimation;
	}

	public Animation getLookRightAnimation() {
		return lookRightAnimation;
	}

	public Animation getGoUpAnimation() {
		return goUpAnimation;
	}

	public Animation getGoDownAnimation() {
		return goDownAnimation;
	}

	public Animation getGoLeftAnimation() {
		return goLeftAnimation;
	}

	public Animation getGoRightAnimation() {
		return goRightAnimation;
	}

	public Animation getAttackUpAnimation() {
		return attackUpAnimation;
	}

	public Animation getAttackDownAnimation() {
		return attackDownAnimation;
	}

	public Animation getAttackLeftAnimation() {
		return attackLeftAnimation;
	}

	public Animation getAttackRightAnimation() {
		return attackRightAnimation;
	}

	public Animation getShootUpAnimation() {
		return shootUpAnimation;
	}

	public Animation getShootDownAnimation() {
		return shootDownAnimation;
	}

	public Animation getShootLeftAnimation() {
		return shootLeftAnimation;
	}

	public Animation getShootRightAnimation() {
		return shootRightAnimation;
	}

	public Animation getSpellUpAnimation() {
		return spellUpAnimation;
	}

	public Animation getSpellDownAnimation() {
		return spellDownAnimation;
	}

	public Animation getSpellLeftAnimation() {
		return spellLeftAnimation;
	}

	public Animation getSpellRightAnimation() {
		return spellRightAnimation;
	}
	
	public Animation getDieAnimation() {
		return dieAnimation;
	}
	
}
