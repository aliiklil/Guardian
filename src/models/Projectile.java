package models;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import main.Main;
import manager.CharacterManager;
import util.CollisionBox;

public class Projectile {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
	
	private CollisionBox collisionBox;
		
	private Animation animation;
	
	private final int velocity;
	private final int direction;
	
	private ArrayList<NPC> npcList;
		
	private int travelledDistance = 0;
	private final int travelledDistanceRemove = Main.TILE_SIZE * 30;
	
	private final int width;
	private final int height;
	
	private int damage;
	
	public Projectile(float x, float y, Animation animation, int direction, int damage, int velocity) throws SlickException {
		
		this.animation = animation;
		
		width = animation.getWidth();
		height = animation.getHeight();
						
		relativeToMapX = x - width/2;
		relativeToMapY = y - height/2;
				
		relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;

		this.direction = direction;
		
		this.damage = damage;
			
		this.velocity = velocity;
		
		collisionBox = new CollisionBox(relativeToMapX + width/4, relativeToMapY + height/4, width/4, height/4);
				
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
			
			npcList = CharacterManager.getNpcList();
			
			for(NPC npc : npcList) {
					if(collisionBox.intersects(npc.getCharacterCollisionBox()) && npc.isAlive()) {
						npc.decreaseHealth(damage);
						Game.getProjectileManager().removeProjectile(this);
					}
			}
		}
	}
	
	public void render(Graphics g) {
		animation.draw(relativeToScreenX, relativeToScreenY);
	}
	
}