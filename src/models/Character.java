package models;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Main;
import util.CollisionBox;

public abstract class Character extends Mob {

	private final int spriteSize = 64;
	private final int overSizeSpriteSize = 192;

	//The normal default speed
	private final float defaultMovementSpeed = 2f*2;
	private final float defaultDiagonalMovementSpeed = 1.6f*2;
	
	//Current speed of the character
	private float movementSpeed = 2f*2;
	private float diagonalMovementSpeed = 1.6f*2;
				
	private Bar healthBar;
	
	private SpriteSheet bloodSpriteSheet;
	private Animation bloodAnimation;
	private boolean drawBlood;
	
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
		
	private ArrayList<Item> inventoryList = new ArrayList<Item>();
	private ArrayList<Integer> itemCountList = new ArrayList<Integer>();
	
	public Character(float relativeToMapX, float relativeToMapY, String spriteSheetPath, boolean alive) throws SlickException {
		
		super(relativeToMapX, relativeToMapY, alive);
						
		bloodSpriteSheet = new SpriteSheet("resources/blood.png", 64, 64);
		bloodAnimation = new Animation(bloodSpriteSheet, 0, 0, 7, 0, true, 50, true);
		bloodAnimation.setLooping(false);
		drawBlood = false;
				
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
		
		slayUpAnimation = new Animation(overSizeSpriteSheet, 2, 7, 5, 7, true, 100, true);
		slayDownAnimation = new Animation(overSizeSpriteSheet, 2, 9, 5, 9, true, 100, true);
		slayLeftAnimation = new Animation(overSizeSpriteSheet, 2, 8, 5, 8, true, 100, true);
		slayRightAnimation = new Animation(overSizeSpriteSheet, 2, 10, 5, 10, true, 100, true);
		
		prepareSlayUpAnimation = new Animation(overSizeSpriteSheet, 2, 7, 2, 7, true, 100, true);
		prepareSlayDownAnimation = new Animation(overSizeSpriteSheet, 2, 9, 2, 9, true, 100, true);
		prepareSlayLeftAnimation = new Animation(overSizeSpriteSheet, 2, 8, 2, 8, true, 100, true);
		prepareSlayRightAnimation = new Animation(overSizeSpriteSheet, 2, 10, 2, 10, true, 100, true);

		thrustUpAnimation = new Animation(overSizeSpriteSheet, 3, 11, 7, 11, true, 100, true);
		thrustDownAnimation = new Animation(overSizeSpriteSheet, 3, 13, 7, 13, true, 100, true);
		thrustLeftAnimation = new Animation(overSizeSpriteSheet, 3, 12, 7, 12, true, 100, true);
		thrustRightAnimation = new Animation(overSizeSpriteSheet, 3, 14, 7, 14, true, 100, true);
		
		prepareThrustUpAnimation = new Animation(overSizeSpriteSheet, 3, 11, 3, 11, true, 100, true);
		prepareThrustDownAnimation = new Animation(overSizeSpriteSheet, 3, 13, 3, 13, true, 100, true);
		prepareThrustLeftAnimation = new Animation(overSizeSpriteSheet, 3, 12, 3, 12, true, 100, true);
		prepareThrustRightAnimation = new Animation(overSizeSpriteSheet, 3, 14, 3, 14, true, 100, true);

		shootUpAnimation = new Animation(spriteSheet, 0, 16, 8, 16, true, 100, true);
		shootDownAnimation = new Animation(spriteSheet, 0, 18, 8, 18, true, 100, true);
		shootLeftAnimation = new Animation(spriteSheet, 0, 17, 8, 17, true, 100, true);
		shootRightAnimation = new Animation(spriteSheet, 0, 19, 8, 19, true, 100, true);
		
		spellUpAnimation = new Animation(spriteSheet, 0, 0, 6, 0, true, 100, true);
		spellDownAnimation = new Animation(spriteSheet, 0, 2, 6, 2, true, 100, true);
		spellLeftAnimation = new Animation(spriteSheet, 0, 1, 6, 1, true, 100, true);
		spellRightAnimation = new Animation(spriteSheet, 0, 3, 6, 3, true, 100, true);
		
		dieAnimation = new Animation(spriteSheet, 0, 20, 5, 20, true, 100, true);

		
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
		
		setCurrentAnimation(lookDownAnimation);
		
		setCenterX(getRelativeToMapX() + Main.TILE_SIZE/2);
		setCenterY(getRelativeToMapY() + Main.TILE_SIZE/2);
		
		setCenterXTile((int) (getCenterX() / Main.TILE_SIZE));
		setCenterYTile((int) (getCenterY() / Main.TILE_SIZE));
			
	}
	
	public void update() throws SlickException {
		setCenterX(getRelativeToMapX() + Main.TILE_SIZE/2);
		setCenterY(getRelativeToMapY() + Main.TILE_SIZE/2);
		
		setCenterXTile((int) (getCenterX() / Main.TILE_SIZE));
		setCenterYTile((int) (getCenterY() / Main.TILE_SIZE));
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

	public void setLookUpAnimation(Animation lookUpAnimation) {
		this.lookUpAnimation = lookUpAnimation;
	}

	public void setLookDownAnimation(Animation lookDownAnimation) {
		this.lookDownAnimation = lookDownAnimation;
	}

	public void setLookLeftAnimation(Animation lookLeftAnimation) {
		this.lookLeftAnimation = lookLeftAnimation;
	}

	public void setLookRightAnimation(Animation lookRightAnimation) {
		this.lookRightAnimation = lookRightAnimation;
	}

	public void setGoUpAnimation(Animation goUpAnimation) {
		this.goUpAnimation = goUpAnimation;
	}

	public void setGoDownAnimation(Animation goDownAnimation) {
		this.goDownAnimation = goDownAnimation;
	}

	public void setGoLeftAnimation(Animation goLeftAnimation) {
		this.goLeftAnimation = goLeftAnimation;
	}

	public void setGoRightAnimation(Animation goRightAnimation) {
		this.goRightAnimation = goRightAnimation;
	}

	public void setSlayUpAnimation(Animation slayUpAnimation) {
		this.slayUpAnimation = slayUpAnimation;
	}

	public void setSlayDownAnimation(Animation slayDownAnimation) {
		this.slayDownAnimation = slayDownAnimation;
	}

	public void setSlayLeftAnimation(Animation slayLeftAnimation) {
		this.slayLeftAnimation = slayLeftAnimation;
	}

	public void setSlayRightAnimation(Animation slayRightAnimation) {
		this.slayRightAnimation = slayRightAnimation;
	}

	public void setPrepareSlayUpAnimation(Animation prepareSlayUpAnimation) {
		this.prepareSlayUpAnimation = prepareSlayUpAnimation;
	}

	public void setPrepareSlayDownAnimation(Animation prepareSlayDownAnimation) {
		this.prepareSlayDownAnimation = prepareSlayDownAnimation;
	}

	public void setPrepareSlayLeftAnimation(Animation prepareSlayLeftAnimation) {
		this.prepareSlayLeftAnimation = prepareSlayLeftAnimation;
	}

	public void setPrepareSlayRightAnimation(Animation prepareSlayRightAnimation) {
		this.prepareSlayRightAnimation = prepareSlayRightAnimation;
	}

	public void setThrustUpAnimation(Animation thrustUpAnimation) {
		this.thrustUpAnimation = thrustUpAnimation;
	}

	public void setThrustDownAnimation(Animation thrustDownAnimation) {
		this.thrustDownAnimation = thrustDownAnimation;
	}

	public void setThrustLeftAnimation(Animation thrustLeftAnimation) {
		this.thrustLeftAnimation = thrustLeftAnimation;
	}

	public void setThrustRightAnimation(Animation thrustRightAnimation) {
		this.thrustRightAnimation = thrustRightAnimation;
	}

	public void setPrepareThrustUpAnimation(Animation prepareThrustUpAnimation) {
		this.prepareThrustUpAnimation = prepareThrustUpAnimation;
	}

	public void setPrepareThrustDownAnimation(Animation prepareThrustDownAnimation) {
		this.prepareThrustDownAnimation = prepareThrustDownAnimation;
	}

	public void setPrepareThrustLeftAnimation(Animation prepareThrustLeftAnimation) {
		this.prepareThrustLeftAnimation = prepareThrustLeftAnimation;
	}

	public void setPrepareThrustRightAnimation(Animation prepareThrustRightAnimation) {
		this.prepareThrustRightAnimation = prepareThrustRightAnimation;
	}

	public void setShootUpAnimation(Animation shootUpAnimation) {
		this.shootUpAnimation = shootUpAnimation;
	}

	public void setShootDownAnimation(Animation shootDownAnimation) {
		this.shootDownAnimation = shootDownAnimation;
	}

	public void setShootLeftAnimation(Animation shootLeftAnimation) {
		this.shootLeftAnimation = shootLeftAnimation;
	}

	public void setShootRightAnimation(Animation shootRightAnimation) {
		this.shootRightAnimation = shootRightAnimation;
	}

	public void setSpellUpAnimation(Animation spellUpAnimation) {
		this.spellUpAnimation = spellUpAnimation;
	}

	public void setSpellDownAnimation(Animation spellDownAnimation) {
		this.spellDownAnimation = spellDownAnimation;
	}

	public void setSpellLeftAnimation(Animation spellLeftAnimation) {
		this.spellLeftAnimation = spellLeftAnimation;
	}

	public void setSpellRightAnimation(Animation spellRightAnimation) {
		this.spellRightAnimation = spellRightAnimation;
	}

	public void setDieAnimation(Animation dieAnimation) {
		this.dieAnimation = dieAnimation;
	}
		
}
