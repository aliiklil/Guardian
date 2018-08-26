package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

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
	
	public Chest(int tileX, int tileY, Item item) throws SlickException {
		
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.item = item;
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
		
		collisionBox = new CollisionBox(tileX * 32, tileY * 32, 32, 32);
		
		spriteSheet = new SpriteSheet("resources/chest.png", 32, 32);
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
	
}