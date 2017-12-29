package main;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import util.CollisionBox;

public class Arrow {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
	
	private int spriteWidth = 33;
	private int spriteHeight = 5;
	
	private SpriteSheet spriteSheet = new SpriteSheet("resources/arrow.png", spriteWidth, spriteHeight);
	
	private int velocity;
	private int direction;
	
	private ArrayList<NPC> npcList;
		
	private int travelledDistance = 0;
	private final int travelledDistanceRemove = Main.TILE_SIZE * 30;

	public Arrow(float x, float y, int direction, int velocity) throws SlickException {
		
		this.relativeToMapX = x - spriteWidth/2;
		this.relativeToMapY = y - spriteHeight/2;
		
		this.relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		this.relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		this.velocity = velocity;
		this.direction = direction;
		
		if(direction == 0) {
			
			spriteSheet.rotate(90);
			collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, spriteHeight, spriteWidth);
			
		} else if(direction == 1) {
			
			spriteSheet.rotate(270);
			collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, spriteHeight, spriteWidth);
			
		} else if(direction == 2) {
			
			spriteSheet.rotate(0);
			collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, spriteWidth, spriteHeight);
			
		} else if(direction == 3) {
			
			spriteSheet.rotate(180);
			collisionBox = new CollisionBox(relativeToMapX, relativeToMapY, spriteWidth, spriteHeight);
			
		} else {
			throw(new IllegalArgumentException("Direction must be an integer from 0 to 3"));
		}
		
	}

	public void update() {
		
		if(direction == 0) {
			
			relativeToMapY = relativeToMapY - velocity;
			
		} else if(direction == 1) {
			
			relativeToMapY = relativeToMapY + velocity;
			
		} else if(direction == 2) {
			
			relativeToMapX = relativeToMapX - velocity;

		} else if(direction == 3) {
			
			relativeToMapX = relativeToMapX + velocity;
			
		} else {
			
			throw(new IllegalArgumentException("Direction must be an integer from 0 to 3"));
			
		}
		
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		
		travelledDistance = travelledDistance + velocity;
		
		if(travelledDistance > travelledDistanceRemove) {
			
			Game.getArrowManager().removeArrow(this);
			
		} else {
			
			collisionBox.setX(relativeToMapX);
			collisionBox.setY(relativeToMapY);
			
			npcList = Game.getNpcList();
			
			for(NPC npc : npcList) {
					if(collisionBox.intersects(npc.getCollisionBox()) && npc.isAlive()) {
						npc.decreaseHealth(10);
						Game.getArrowManager().removeArrow(this);
					}
			}
		}
	}
	
	public void render(Graphics g) {
		spriteSheet.draw(relativeToScreenX, relativeToScreenY);
	}
	
}