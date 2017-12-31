package main;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import util.CollisionBox;

public class Projectile {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
		
	private Animation animation;
	
	private int velocity;
	private int direction;
	
	private ArrayList<NPC> npcList;
		
	private int travelledDistance = 0;
	private final int travelledDistanceRemove = Main.TILE_SIZE * 30;

	public Projectile(float x, float y, Animation animation, int direction, int velocity) throws SlickException {
		
		this.animation = animation;
						
		relativeToMapX = x - 32;
		relativeToMapY = y - 32;
		
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
		
		relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
		
		this.velocity = velocity;
		this.direction = direction;
			
		collisionBox = new CollisionBox(relativeToMapX + 16, relativeToMapY + 16, 16, 16);
		
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
			
			Game.getProjectileManager().removeProjectile(this);
			
		} else {
			
			collisionBox.setX(relativeToMapX + 16);
			collisionBox.setY(relativeToMapY + 16);
			
			npcList = Game.getNpcList();
			
			for(NPC npc : npcList) {
					if(collisionBox.intersects(npc.getCollisionBox()) && npc.isAlive()) {
						npc.decreaseHealth(10);
						Game.getProjectileManager().removeProjectile(this);
					}
			}
		}
	}
	
	public void render(Graphics g) {
		animation.draw(relativeToScreenX, relativeToScreenY);
	}
	
}