package models;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Main;
import util.CollisionBox;

public abstract class Character {

	private final int spriteSize = 64;
	private final int overSizeSpriteSize = 192;

	//The normal default speed
	private final float defaultMovementSpeed = 2f;
	private final float defaultDiagonalMovementSpeed = 1.5f;
	
	//Current speed of the character
	private float movementSpeed = 2f;
	private float diagonalMovementSpeed = 1.5f;
	
	private float relativeToMapX;
	private float relativeToMapY;
	
	private float centerX;
	private float centerY;
	
	private int centerXTile;
	private int centerYTile;
	
	private boolean alive = true;
	
	private Bar healthBar;
	
	private SpriteSheet bloodSpriteSheet;
	private Animation bloodAnimation;
	private boolean drawBlood;
	
	private CollisionBox collisionBox;
	private CollisionBox hitBox;
	
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
	
	private Animation slayUpAnimation;
	private Animation slayDownAnimation;
	private Animation slayLeftAnimation;
	private Animation slayRightAnimation;
	
	private Animation prepareSlayUpAnimation;
	private Animation prepareSlayDownAnimation;
	private Animation prepareSlayLeftAnimation;
	private Animation prepareSlayRightAnimation;
	
	private Animation thrustUpAnimation;
	private Animation thrustDownAnimation;
	private Animation thrustLeftAnimation;
	private Animation thrustRightAnimation;
	
	private Animation prepareThrustUpAnimation;
	private Animation prepareThrustDownAnimation;
	private Animation prepareThrustLeftAnimation;
	private Animation prepareThrustRightAnimation;
	
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
	
	private ArrayList<Item> inventoryList = new ArrayList<Item>();
	private ArrayList<Integer> itemCountList = new ArrayList<Integer>();
	
	public Character(float relativeToMapX, float relativeToMapY, String spriteSheetPath) throws SlickException {
		
		this.relativeToMapX = relativeToMapX;
		this.relativeToMapY = relativeToMapY;
				
		bloodSpriteSheet = new SpriteSheet("resources/blood.png", 64, 64);
		bloodAnimation = new Animation(bloodSpriteSheet, 0, 0, 7, 0, true, 50, true);
		bloodAnimation.setLooping(false);
		drawBlood = false;
				
		spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		overSizeSpriteSheet = new SpriteSheet(spriteSheetPath, 192, 192);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		lookDownAnimation = new Animation(spriteSheet, 0, 10, 0, 10, true, 100, false);
		lookLeftAnimation = new Animation(spriteSheet, 0, 9, 0, 9, true, 100, false);
		lookRightAnimation = new Animation(spriteSheet, 0, 11, 0, 11, true, 100, false);
		
		goUpAnimation = new Animation(spriteSheet, 1, 8, 8, 8, true, 100, false);
		goDownAnimation = new Animation(spriteSheet, 1, 10, 8, 10, true, 100, false);
		goLeftAnimation = new Animation(spriteSheet, 1, 9, 8, 9, true, 100, false);
		goRightAnimation = new Animation(spriteSheet, 1, 11, 8, 11, true, 100, false);
		
		slayUpAnimation = new Animation(overSizeSpriteSheet, 2, 7, 5, 7, true, 100, false);
		slayDownAnimation = new Animation(overSizeSpriteSheet, 2, 9, 5, 9, true, 100, false);
		slayLeftAnimation = new Animation(overSizeSpriteSheet, 2, 8, 5, 8, true, 100, false);
		slayRightAnimation = new Animation(overSizeSpriteSheet, 2, 10, 5, 10, true, 100, false);
		
		prepareSlayUpAnimation = new Animation(overSizeSpriteSheet, 2, 7, 2, 7, true, 100, false);
		prepareSlayDownAnimation = new Animation(overSizeSpriteSheet, 2, 9, 2, 9, true, 100, false);
		prepareSlayLeftAnimation = new Animation(overSizeSpriteSheet, 2, 8, 2, 8, true, 100, false);
		prepareSlayRightAnimation = new Animation(overSizeSpriteSheet, 2, 10, 2, 10, true, 100, false);

		thrustUpAnimation = new Animation(overSizeSpriteSheet, 3, 11, 7, 11, true, 100, false);
		thrustDownAnimation = new Animation(overSizeSpriteSheet, 3, 13, 7, 13, true, 100, false);
		thrustLeftAnimation = new Animation(overSizeSpriteSheet, 3, 12, 7, 12, true, 100, false);
		thrustRightAnimation = new Animation(overSizeSpriteSheet, 3, 14, 7, 14, true, 100, false);
		
		prepareThrustUpAnimation = new Animation(overSizeSpriteSheet, 3, 11, 3, 11, true, 100, false);
		prepareThrustDownAnimation = new Animation(overSizeSpriteSheet, 3, 13, 3, 13, true, 100, false);
		prepareThrustLeftAnimation = new Animation(overSizeSpriteSheet, 3, 12, 3, 12, true, 100, false);
		prepareThrustRightAnimation = new Animation(overSizeSpriteSheet, 3, 14, 3, 14, true, 100, false);

		shootUpAnimation = new Animation(spriteSheet, 0, 16, 8, 16, true, 100, false);
		shootDownAnimation = new Animation(spriteSheet, 0, 18, 8, 18, true, 100, false);
		shootLeftAnimation = new Animation(spriteSheet, 0, 17, 8, 17, true, 100, false);
		shootRightAnimation = new Animation(spriteSheet, 0, 19, 8, 19, true, 100, false);
		
		spellUpAnimation = new Animation(spriteSheet, 0, 0, 6, 0, true, 100, false);
		spellDownAnimation = new Animation(spriteSheet, 0, 2, 6, 2, true, 100, false);
		spellLeftAnimation = new Animation(spriteSheet, 0, 1, 6, 1, true, 100, false);
		spellRightAnimation = new Animation(spriteSheet, 0, 3, 6, 3, true, 100, false);
		
		dieAnimation = new Animation(spriteSheet, 0, 20, 5, 20, true, 100, false);

		
		slayUpAnimation.setLooping(false);
		slayDownAnimation.setLooping(false);
		slayLeftAnimation.setLooping(false);
		slayRightAnimation.setLooping(false);
		
		thrustUpAnimation.setLooping(false);
		thrustDownAnimation.setLooping(false);
		thrustLeftAnimation.setLooping(false);
		thrustRightAnimation.setLooping(false);
		
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
		
		centerX = relativeToMapX - Main.TILE_SIZE/2;
		centerY = relativeToMapY - Main.TILE_SIZE/2;
		
		centerXTile = (int) (centerX / Main.TILE_SIZE);
		centerYTile = (int) (centerY / Main.TILE_SIZE);
		
		lookUpAnimation.setAutoUpdate(true);
		lookDownAnimation.setAutoUpdate(true);
		lookLeftAnimation.setAutoUpdate(true);
		lookRightAnimation.setAutoUpdate(true);
		
		goUpAnimation.setAutoUpdate(true);
		goDownAnimation.setAutoUpdate(true);
		goLeftAnimation.setAutoUpdate(true);
		goRightAnimation.setAutoUpdate(true);
		
		slayUpAnimation.setAutoUpdate(true);
		slayDownAnimation.setAutoUpdate(true);
		slayLeftAnimation.setAutoUpdate(true);
		slayRightAnimation.setAutoUpdate(true);
		
		prepareSlayUpAnimation.setAutoUpdate(true);
		prepareSlayDownAnimation.setAutoUpdate(true);
		prepareSlayLeftAnimation.setAutoUpdate(true);
		prepareSlayRightAnimation.setAutoUpdate(true);

		thrustUpAnimation.setAutoUpdate(true);
		thrustDownAnimation.setAutoUpdate(true);
		thrustLeftAnimation.setAutoUpdate(true);
		thrustRightAnimation.setAutoUpdate(true);
		
		prepareThrustUpAnimation.setAutoUpdate(true);
		prepareThrustDownAnimation.setAutoUpdate(true);
		prepareThrustLeftAnimation.setAutoUpdate(true);
		prepareThrustRightAnimation.setAutoUpdate(true);

		shootUpAnimation.setAutoUpdate(true);
		shootDownAnimation.setAutoUpdate(true);
		shootLeftAnimation.setAutoUpdate(true);
		shootRightAnimation.setAutoUpdate(true);
		
		spellUpAnimation.setAutoUpdate(true);
		spellDownAnimation.setAutoUpdate(true);
		spellLeftAnimation.setAutoUpdate(true);
		spellRightAnimation.setAutoUpdate(true);
		
		dieAnimation.setAutoUpdate(true);
		
	}
	
	public void update() throws SlickException {
		centerX = relativeToMapX + Main.TILE_SIZE/2;
		centerY = relativeToMapY + Main.TILE_SIZE/2;
		
		centerXTile = (int) (centerX / Main.TILE_SIZE);
		centerYTile = (int) (centerY / Main.TILE_SIZE);
	}
	
	public abstract void render(Graphics g);
	
	public abstract void decreaseHealth(int amount);
	
	public void drawBlood(float screenRelativeX, float screenRelativeY) {
		
		bloodAnimation.draw(screenRelativeX, screenRelativeY);
		if(bloodAnimation.isStopped()) {
			drawBlood = false;
			bloodAnimation.restart();
		}
		
	}
	
	public void addItem(Item item) {
		
		for(int i = 0; i < inventoryList.size(); i++) {
			if(item.getItemType().getInventoryAnimation().getImage(0).getResourceReference().equals(inventoryList.get(i).getItemType().getInventoryAnimation().getImage(0).getResourceReference())) {	
				itemCountList.set(i, itemCountList.get(i) + 1);
				return;
			}
		}
		
		itemCountList.add(1);
		inventoryList.add(item);
		
		sortInventory();
			
	}
	
	private void sortInventory() {
		
		//Sort after inventory priority
		for (int i = 0; i < inventoryList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < inventoryList.size(); j++) {
				if (inventoryList.get(j).getItemType().getInventoryPriority() < inventoryList.get(index).getItemType().getInventoryPriority()) {
					index = j;
				}
			}
			Collections.swap(inventoryList, index, i);
			Collections.swap(itemCountList, index, i);
		}
		
		//Sort after value in gold if inventory priority is the same
		for (int i = 0; i < inventoryList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < inventoryList.size(); j++) {
				if (inventoryList.get(j).getItemType().getInventoryPriority() == inventoryList.get(index).getItemType().getInventoryPriority() && inventoryList.get(j).getItemType().getValue() > inventoryList.get(index).getItemType().getValue()) {
					index = j;
				}
			}
			Collections.swap(inventoryList, index, i);
			Collections.swap(itemCountList, index, i);
		}
			
	}

	public int getSpriteSize() {
		return spriteSize;
	}

	public int getOverSizeSpriteSize() {
		return overSizeSpriteSize;
	}
	
	public float getDefaultMovementSpeed() {
		return defaultMovementSpeed;
	}

	public float getDefaultDiagonalMovementSpeed() {
		return defaultDiagonalMovementSpeed;
	}

	public float getMovementSpeed() {
		return movementSpeed;
	}
	
	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	
	public float getDiagonalMovementSpeed() {
		return diagonalMovementSpeed;
	}
	
	public void setDiagonalMovementSpeed(float diagonalMovementSpeed) {
		this.diagonalMovementSpeed = diagonalMovementSpeed;
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
	
	public float getCenterX() {
		return centerX;
	}

	public float getCenterY() {
		return centerY;
	}
	
	public void setCenterX(float centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(float centerY) {
		this.centerY = centerY;
	}
	
	public int getCenterXTile() {
		return centerXTile;
	}

	public int getCenterYTile() {
		return centerYTile;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Bar getHealthBar() {
		return healthBar;
	}
	
	public void setHealthBar(Bar healthBar) {
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

	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(CollisionBox collisionBox) {
		this.collisionBox = collisionBox;
	}
	
	public CollisionBox getHitBox() {
		return hitBox;
	}
	
	public void setHitBox(CollisionBox hitBox) {
		this.hitBox = hitBox;
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

	public Animation getSlayUpAnimation() {
		return slayUpAnimation;
	}

	public Animation getSlayDownAnimation() {
		return slayDownAnimation;
	}

	public Animation getSlayLeftAnimation() {
		return slayLeftAnimation;
	}

	public Animation getSlayRightAnimation() {
		return slayRightAnimation;
	}

	public Animation getPrepareSlayUpAnimation() {
		return prepareSlayUpAnimation;
	}

	public Animation getPrepareSlayDownAnimation() {
		return prepareSlayDownAnimation;
	}

	public Animation getPrepareSlayLeftAnimation() {
		return prepareSlayLeftAnimation;
	}

	public Animation getPrepareSlayRightAnimation() {
		return prepareSlayRightAnimation;
	}

	public Animation getThrustUpAnimation() {
		return thrustUpAnimation;
	}

	public Animation getThrustDownAnimation() {
		return thrustDownAnimation;
	}

	public Animation getThrustLeftAnimation() {
		return thrustLeftAnimation;
	}

	public Animation getThrustRightAnimation() {
		return thrustRightAnimation;
	}

	public Animation getPrepareThrustUpAnimation() {
		return prepareThrustUpAnimation;
	}

	public Animation getPrepareThrustDownAnimation() {
		return prepareThrustDownAnimation;
	}

	public Animation getPrepareThrustLeftAnimation() {
		return prepareThrustLeftAnimation;
	}

	public Animation getPrepareThrustRightAnimation() {
		return prepareThrustRightAnimation;
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
	
	public ArrayList<Item> getInventoryList() {
		return inventoryList;
	}

	public ArrayList<Integer> getItemCountList() {
		return itemCountList;
	}
		
}
