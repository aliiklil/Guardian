package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import util.CollisionBox;

public class Chest {

	private Item item;
	
	private int tileX;
	private int tileY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
	
	private SpriteSheet spriteSheet;
	
	private Animation animation;
	
	private boolean opened = false;
	
	private int chestLevel; //Can be level 0, 1, 2 or 3 chest
	
	public Chest(int tileX, int tileY, Item item, int chestLevel) throws SlickException {
		
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.chestLevel = chestLevel;
		
		this.item = item;
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
		
		collisionBox = new CollisionBox(tileX * 32, tileY * 32, 32, 32);
		
		
		if(chestLevel == 0) {
			spriteSheet = new SpriteSheet("resources/assets/chest0.png", 32, 32);
		} else if(chestLevel == 1) {
			spriteSheet = new SpriteSheet("resources/assets/chest1.png", 32, 32);
		} if(chestLevel == 2) {
			spriteSheet = new SpriteSheet("resources/assets/chest2.png", 32, 32);
		} if(chestLevel == 3) {
			spriteSheet = new SpriteSheet("resources/assets/chest3.png", 32, 32);
		}
		
		animation = new Animation(spriteSheet, 0, 0, 4, 0, true, 100, true);
		animation.stop();
		animation.setLooping(false);
		
	}
	
	public void update() throws SlickException {
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
		
	}
	
	public void render(Graphics g) {
		animation.draw(relativeToScreenX, relativeToScreenY);
	}

	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

	public Animation getAnimation() {
		return animation;
	}

	public Item getItem() {
		return item;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public int getChestLevel() {
		return chestLevel;
	}

	public void setChestLevel(int chestLevel) {
		this.chestLevel = chestLevel;
	}
	
}