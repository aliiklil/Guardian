package models;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.Game;
import util.CollisionBox;

public class Item {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
			
	private Image image;
	
	private CollisionBox collisionBox;
	
	private final int width;
	private final int height;

	public Item(float x, float y, String path) throws SlickException {
		
		image = new Image(path);
		
		width = image.getWidth();
		height = image.getHeight();
						
		relativeToMapX = x;
		relativeToMapY = y;
				
		relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, width, height);
				
	}

	public void update() {
				
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		
		collisionBox.setX(relativeToMapX);
		collisionBox.setY(relativeToMapY);
		
	}
	
	public void render(Graphics g) {
		
		image.draw(relativeToScreenX, relativeToScreenY);
		
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}
	
	public Image getImage() {
		return image;
	}
	
}
