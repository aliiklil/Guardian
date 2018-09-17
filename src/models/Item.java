package models;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import util.CollisionBox;

public class Item {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
			
	private final ItemType itemType;
	
	private boolean isEquipped;
	
	public Item(float x, float y, ItemType itemType) throws SlickException {
		
		this.itemType = itemType;
		
		relativeToMapX = x;
		relativeToMapY = y;
				
		relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, itemType.getSpriteWidth(), itemType.getSpriteHeight());
				
		isEquipped = false;
		
	}

	public void update() {
				
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		
		collisionBox.setX(relativeToMapX);
		collisionBox.setY(relativeToMapY);
		
	}
	
	public void render(Graphics g) {
		
		itemType.getMapAnimation().draw(relativeToScreenX, relativeToScreenY);
		
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}
	
	public ItemType getItemType() {
		return itemType;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	
}
