package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ItemType {

	private final int spriteWidth;
	private final int spriteHeight;
	
	private final Animation mapAnimation;
	private final Animation inventoryAnimation;
	private final Animation descriptionAnimation;
	
	private final String name;
	private final int value;
	
	public ItemType(int spriteWidth, int spriteHeight, int duration, String path, String name, int value) throws SlickException {
		
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		
		mapAnimation = new Animation(new SpriteSheet(path, spriteWidth, spriteHeight), duration);
		inventoryAnimation = new Animation(new SpriteSheet(new Image(path).getScaledCopy(2), spriteWidth * 2, spriteHeight * 2), duration);
		descriptionAnimation = new Animation(new SpriteSheet(new Image(path).getScaledCopy(4), spriteWidth * 4, spriteHeight * 4), duration);
		
		this.name = name;
		this.value = value;
		
		
				
	}
	
	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public Animation getMapAnimation() {
		return mapAnimation;
	}
	
	public Animation getInventoryAnimation() {
		return inventoryAnimation;
	}
	
	public Animation getDescriptionAnimation() {
		return descriptionAnimation;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
}
