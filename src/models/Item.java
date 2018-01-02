package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import util.CollisionBox;

public class Item {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
			
	private final Animation mapAnimation;
	private final Animation inventoryAnimation;
	private final Animation descriptionAnimation;
	
	private CollisionBox collisionBox;
	
	private final String name;
	
	public Item(float x, float y, int width, int height, int duration, String path, String name) throws SlickException {
		
		mapAnimation = new Animation(new SpriteSheet(path, width, height), duration);
		inventoryAnimation = new Animation(new SpriteSheet(new Image(path).getScaledCopy(2), width * 2, height * 2), duration);
		descriptionAnimation = new Animation(new SpriteSheet(new Image(path).getScaledCopy(4), width * 4, height * 4), duration);
		
		relativeToMapX = x;
		relativeToMapY = y;
				
		relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, width, height);
		
		this.name = name;
				
	}

	public void update() {
				
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		
		collisionBox.setX(relativeToMapX);
		collisionBox.setY(relativeToMapY);
		
	}
	
	public void render(Graphics g) {
		
		mapAnimation.draw(relativeToScreenX, relativeToScreenY);
		
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
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
	
	
}
