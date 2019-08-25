package models;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import main.Main;
import manager.MobManager;
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
	
	private ArrayList<Mob> mobList;
		
	private int travelledDistance = 0;
	private final int travelledDistanceRemove = Main.TILE_SIZE * 33;
	
	private final int width;
	private final int height;
	
	private int damage;
	
	private boolean isBlocking; //If the projectile should stop the NPC from moving
	
	private boolean isFirerainProjectile; //If projectile is firerain
	
	private boolean spriteChanged = false; //If sprite was changed for firerain to the ending of the firerain proectile
	
	private boolean isBloodtheft;

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

	public void update() throws SlickException {
		
		if(direction == 0) {
			
			relativeToMapY = relativeToMapY - velocity;
			
		} else if(direction == 1) {
			
			relativeToMapY = relativeToMapY + velocity;
			
		} else if(direction == 2) {
			
			relativeToMapX = relativeToMapX - velocity;

		} else if(direction == 3) {
			
			relativeToMapX = relativeToMapX + velocity;
			
		} else if(direction == 4) { //For fire rain, which comes from top right and goes bottom left
			
			relativeToMapX = relativeToMapX - velocity/5;
			relativeToMapY = relativeToMapY + velocity;
			
		} else {
			
			throw(new IllegalArgumentException("Direction must be an integer from 0 to 4"));
			
		}
		
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		
		travelledDistance = travelledDistance + velocity;
		
		if(isFirerainProjectile && travelledDistance > travelledDistanceRemove - 256 && !spriteChanged) { //If a projectile from firerain is before it ends, it will become black
			
			SpriteSheet spriteSheet = new SpriteSheet("resources/firerain_end.png", 64, 64);
			animation = new Animation(spriteSheet, 0, 0, 4, 0, true, 100, true);
			animation.setLooping(false);
			spriteChanged = true;
			
		}
		
		if(travelledDistance > travelledDistanceRemove) {
			
			Game.getProjectileManager().removeProjectile(this);
			spriteChanged = false;
			
		} else {
			
			collisionBox.setX(relativeToMapX + 16);
			collisionBox.setY(relativeToMapY + 16);
			
			mobList = MobManager.getMobListWithoutPlayer();
			
			for(Mob mob : mobList) {
					if(collisionBox.intersects(mob.getHitBox()) && mob.isAlive() && mob.isHostileToPlayer()) {
						mob.decreaseHealth(damage);
						
						if(isBlocking) {
							mob.setIceblocked(true);
							mob.setIceblockedTimestamp(System.currentTimeMillis());
							mob.getCurrentAnimation().stop();
						}
						
						if(isBloodtheft) {
							mob.setBloodtheft(true);
							mob.setBloodtheftTimestamp(System.currentTimeMillis());
						}
						
						mob.setGoingToPlayer(true);
						Game.getProjectileManager().removeProjectile(this);
					}
			}
		}
	}
	
	public void render(Graphics g) {
		animation.draw(relativeToScreenX, relativeToScreenY);
	}

	public boolean isBlocking() {
		return isBlocking;
	}

	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}
	
	public boolean isFirerainProjectile() {
		return isFirerainProjectile;
	}

	public void setFirerainProjectile(boolean isFirerainProjectile) {
		this.isFirerainProjectile = isFirerainProjectile;
	}
	
	public boolean isBloodtheft() {
		return isBloodtheft;
	}
	
	public void setBloodtheft(boolean isBloodtheft) {
		this.isBloodtheft = isBloodtheft;
	}
	
}