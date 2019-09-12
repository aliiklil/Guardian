package models;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import dialogue.Dialogue;
import main.Game;
import util.CollisionBox;

public class Anvil {
	
	private int tileX;
	private int tileY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
	
	private SpriteSheet spriteSheet;
	
	private Animation animation;
	
	private ArrayList<Dialogue> startingDialogues = new ArrayList<Dialogue>();
	
	private long lastTimeAnvilUsed;
	
	private int hotAnvilDuration = 120; // In seconds 

	public Anvil(int tileX, int tileY, ArrayList<Dialogue> startingDialogues) throws SlickException {
		
		this.tileX = tileX;
		this.tileY = tileY;
				
		this.startingDialogues = startingDialogues;
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
		
		collisionBox = new CollisionBox(tileX * 32 + 32, tileY * 32, 32, 32);

		spriteSheet = new SpriteSheet("resources/assets/anvil.png", 64, 32);

		animation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, true);
		animation.stop();
		animation.setLooping(false);
		
	}
	
	public void update() throws SlickException {
		
		relativeToScreenX = Game.getCurrentMap().getX() + tileX * 32;
		relativeToScreenY = Game.getCurrentMap().getY() + tileY * 32;
		
		if(System.currentTimeMillis() > lastTimeAnvilUsed + hotAnvilDuration * 1000) {
			spriteSheet = new SpriteSheet("resources/assets/anvil.png", 64, 32);
			animation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, true);
		}
		
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

	public ArrayList<Dialogue> getStartingDialogues() {
		return startingDialogues;
	}
	
	public void makeAnvilHot() throws SlickException {
		
		SpriteSheet spriteSheet = new SpriteSheet("resources/assets/hotanvil.png", 64, 32);
		animation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, true);
		lastTimeAnvilUsed = System.currentTimeMillis();
	}

}