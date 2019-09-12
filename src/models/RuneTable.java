package models;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import dialogue.Dialogue;
import main.Game;
import util.CollisionBox;

public class RuneTable {
	
	private int tileX;
	private int tileY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
	
	private SpriteSheet spriteSheet;
	
	private Animation topAnimation; // Is drawn over the player
	private Animation bottomAnimation; // Is drawn under the player
	
	private ArrayList<Dialogue> startingDialogues = new ArrayList<Dialogue>();
		
	public RuneTable(int tileX, int tileY, ArrayList<Dialogue> startingDialogues) throws SlickException {
		
		this.tileX = tileX;
		this.tileY = tileY;
				
		this.startingDialogues = startingDialogues;
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
		
		collisionBox = new CollisionBox(tileX * 32, tileY * 32, 32, 32);

		spriteSheet = new SpriteSheet("resources/assets/runeTable.png", 32, 32);
		
		topAnimation = new Animation(spriteSheet, 0, 0, 9, 0, true, 100, true);
		topAnimation.setLooping(true);
		
		bottomAnimation = new Animation(spriteSheet, 0, 1, 9, 1, true, 100, true);
		bottomAnimation.stop();
		
	}
	
	public void update() throws SlickException {
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
				
	}
	
	public void renderTopAnimation(Graphics g) {
		topAnimation.draw(relativeToScreenX, relativeToScreenY - 32);
	}

	public void renderBottomAnimation(Graphics g) {
		bottomAnimation.draw(relativeToScreenX, relativeToScreenY);
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

	public ArrayList<Dialogue> getStartingDialogues() {
		return startingDialogues;
	}
	
}