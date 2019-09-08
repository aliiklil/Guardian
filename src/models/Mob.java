package models;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import main.Main;
import manager.MobManager;
import util.CollisionBox;

public abstract class Mob {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float centerX;
	private float centerY;
	
	private int centerXTile;
	private int centerYTile;
	
	private int startCenterXTile; //Tile on which the mob spawns at the start
	private int startCenterYTile;
	
	private boolean alive;
	
	private CollisionBox collisionBox;
	private CollisionBox hitBox;

	private boolean iceblocked; //If NPC is blocked by iceblock
	private long iceblockedTimestamp; //Time when player was iceblocked
	private Animation iceblockAnimation;
	
	private Animation currentAnimation;
	
	private boolean isGoingToPlayer;
	
	private boolean bloodtheft;
	private int bloodtheftCounter;
	private long bloodtheftTimestamp;
	
	private boolean hostileToPlayer;
	
	//Is set to true when mob is killed, the event can be consumed for quest logic by setting it to false again
	private boolean killEvent;
	
	public Mob(float relativeToMapX, float relativeToMapY, boolean alive) throws SlickException {
		
		this.relativeToMapX = relativeToMapX;
		this.relativeToMapY = relativeToMapY;
		this.alive = alive;
		
		this.centerX = relativeToMapX - Main.TILE_SIZE/2;
		this.centerY = relativeToMapY - Main.TILE_SIZE/2;
		
		this.centerXTile = (int) (centerX / Main.TILE_SIZE);
		this.centerYTile = (int) (centerY / Main.TILE_SIZE);
		
		this.startCenterXTile = centerXTile;
		this.startCenterYTile = centerYTile;
				
		
		iceblockAnimation = new Animation(new SpriteSheet("resources/iceblockSprite.png", 64, 64), 0, 0, 0, 0, true, 100, true);
	}

	public abstract void update() throws SlickException;
	
	public abstract void render(Graphics g) throws SlickException;
	
	public abstract void decreaseHealth(int amount);
	
	public boolean isUpCollision(float distance) {
		
		ArrayList<Mob> mobList = new ArrayList<Mob>(MobManager.getMobList());
		mobList.remove(this);
		for(Mob mob : mobList) {
			if(getCollisionBox().willIntersectUp(mob.getCollisionBox(), 5) && mob.isAlive()) {
				return true;
			}
		}
				
		if(Game.getTiledMap().getTileId((int) getCollisionBox().getTopLeftX()/Main.TILE_SIZE, (int) (getCollisionBox().getTopLeftY() - distance)/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 &&
				Game.getTiledMap().getTileId((int) getCollisionBox().getTopRightX()/Main.TILE_SIZE, (int) (getCollisionBox().getTopRightY() - distance)/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {	
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean isDownCollision(float distance) {
		
		ArrayList<Mob> mobList = new ArrayList<Mob>(MobManager.getMobList());
		mobList.remove(this);
		for(Mob mob : mobList) {
			if(getCollisionBox().willIntersectDown(mob.getCollisionBox(), 5) && mob.isAlive()) {
				return true;
			}
		}
		if(Game.getTiledMap().getTileId((int) getCollisionBox().getBottomLeftX()/Main.TILE_SIZE, (int) (getCollisionBox().getBottomLeftY() + distance)/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 &&
			Game.getTiledMap().getTileId((int) getCollisionBox().getBottomRightX()/Main.TILE_SIZE, (int) (getCollisionBox().getBottomRightY() + distance)/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isLeftCollision(float distance) {
		
		ArrayList<Mob> mobList = new ArrayList<Mob>(MobManager.getMobList());
		mobList.remove(this);
		for(Mob mob : mobList) {
			if(getCollisionBox().willIntersectLeft(mob.getCollisionBox(), 5) && mob.isAlive()) {
				return true;
			}
		}		
		if(Game.getTiledMap().getTileId((int) (getCollisionBox().getTopLeftX() - distance)/Main.TILE_SIZE, (int) getCollisionBox().getTopLeftY()/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 &&
			Game.getTiledMap().getTileId((int) (getCollisionBox().getBottomLeftX() - distance)/Main.TILE_SIZE, (int) getCollisionBox().getBottomLeftY()/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {	
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean isRightCollision(float distance) {
		
		ArrayList<Mob> mobList = new ArrayList<Mob>(MobManager.getMobList());
		mobList.remove(this);
		for(Mob mob : mobList) {
			if(getCollisionBox().willIntersectRight(mob.getCollisionBox(), 5) && mob.isAlive()) {
				return true;
			}
		}
			
		if(Game.getTiledMap().getTileId((int) (getCollisionBox().getTopRightX() + distance)/Main.TILE_SIZE, (int) getCollisionBox().getTopRightY()/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 &&
			Game.getTiledMap().getTileId((int) (getCollisionBox().getBottomRightX() + distance)/Main.TILE_SIZE, (int) getCollisionBox().getBottomRightY()/Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public float getRelativeToMapX() {
		return relativeToMapX;
	}

	public void setRelativeToMapX(float relativeToMapX) {
		this.relativeToMapX = relativeToMapX;
	}

	public float getRelativeToMapY() {
		return relativeToMapY;
	}

	public void setRelativeToMapY(float relativeToMapY) {
		this.relativeToMapY = relativeToMapY;
	}

	public boolean isAlive() {
		return alive;
	}

	public float getCenterX() {
		return centerX;
	}

	public void setCenterX(float centerX) {
		this.centerX = centerX;
	}

	public float getCenterY() {
		return centerY;
	}

	public void setCenterY(float centerY) {
		this.centerY = centerY;
	}

	public int getCenterXTile() {
		return centerXTile;
	}

	public void setCenterXTile(int centerXTile) {
		this.centerXTile = centerXTile;
	}

	public int getCenterYTile() {
		return centerYTile;
	}

	public void setCenterYTile(int centerYTile) {
		this.centerYTile = centerYTile;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(CollisionBox collisionBox) {
		this.collisionBox = collisionBox;
	}

	public CollisionBox getHitBox() {
		return hitBox;
	}

	public void setHitBox(CollisionBox hitBox) {
		this.hitBox = hitBox;
	}

	public int getStartCenterXTile() {
		return startCenterXTile;
	}

	public void setStartCenterXTile(int startCenterXTile) {
		this.startCenterXTile = startCenterXTile;
	}

	public int getStartCenterYTile() {
		return startCenterYTile;
	}

	public void setStartCenterYTile(int startCenterYTile) {
		this.startCenterYTile = startCenterYTile;
	}

	public boolean isIceblocked() {
		return iceblocked;
	}

	public void setIceblocked(boolean iceblocked) {
		this.iceblocked = iceblocked;
	}

	public long getIceblockedTimestamp() {
		return iceblockedTimestamp;
	}

	public void setIceblockedTimestamp(long iceblockedTimestamp) {
		this.iceblockedTimestamp = iceblockedTimestamp;
	}

	public Animation getIceblockAnimation() {
		return iceblockAnimation;
	}

	public void setIceblockAnimation(Animation iceblockAnimation) {
		this.iceblockAnimation = iceblockAnimation;
	}

	public Animation getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(Animation currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public boolean isGoingToPlayer() {
		return isGoingToPlayer;
	}

	public void setGoingToPlayer(boolean isGoingToPlayer) {
		this.isGoingToPlayer = isGoingToPlayer;
	}

	public boolean isBloodtheft() {
		return bloodtheft;
	}

	public void setBloodtheft(boolean bloodtheft) {
		this.bloodtheft = bloodtheft;
	}

	public int getBloodtheftCounter() {
		return bloodtheftCounter;
	}

	public void setBloodtheftCounter(int bloodtheftCounter) {
		this.bloodtheftCounter = bloodtheftCounter;
	}

	public long getBloodtheftTimestamp() {
		return bloodtheftTimestamp;
	}

	public void setBloodtheftTimestamp(long bloodtheftTimestamp) {
		this.bloodtheftTimestamp = bloodtheftTimestamp;
	}

	public boolean isHostileToPlayer() {
		return hostileToPlayer;
	}

	public void setHostileToPlayer(boolean hostileToPlayer) {
		this.hostileToPlayer = hostileToPlayer;
	}

	public boolean isKillEvent() {
		return killEvent;
	}

	public void fireKillEvent() {
		this.killEvent = true;
	}
	
	public void consumeKillEvent() {
		this.killEvent = false;
	}
	
}
