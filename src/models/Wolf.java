package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

import dialogue.Dialogue;
import main.Game;
import main.Main;
import manager.CharacterManager;
import manager.ItemTypeManager;
import manager.WolfManager;
import util.CollisionBox;
import pathfinding.Node;
import pathfinding.AStar;

public class Wolf {

	private Animation lookUpAnimation;
	private Animation lookDownAnimation;
	private Animation lookLeftAnimation;
	private Animation lookRightAnimation;
	
	private Animation howlUpAnimation;
	private Animation howlDownAnimation;
	private Animation howlLeftAnimation;
	private Animation howlRightAnimation;
	
	private Animation walkUpAnimation;
	private Animation walkDownAnimation;
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	
	private Animation runUpAnimation;
	private Animation runDownAnimation;
	private Animation runLeftAnimation;
	private Animation runRightAnimation;
	
	private Animation attackUpAnimation;
	private Animation attackDownAnimation;
	private Animation attackLeftAnimation;
	private Animation attackRightAnimation;
	
	private Animation dieUpAnimation;
	private Animation dieDownAnimation;
	private Animation dieLeftAnimation;
	private Animation dieRightAnimation;
	
	private Animation currentAnimation;
	
	
	
	//So one part of the dog is drawn over the player for example, which looks better
	private Animation lookUpAnimationUpperLayer;
	private Animation lookDownAnimationUpperLayer;

	private Animation howlUpAnimationUpperLayer;
	private Animation howlDownAnimationUpperLayer;

	private Animation walkUpAnimationUpperLayer;
	private Animation walkDownAnimationUpperLayer;
	
	private Animation runUpAnimationUpperLayer;
	private Animation runDownAnimationUpperLayer;

	private Animation attackUpAnimationUpperLayer;
	private Animation attackDownAnimationUpperLayer;
	
	private Animation dieUpAnimationUpperLayer;
	private Animation dieDownAnimationUpperLayer;

	private Animation currentAnimationUpperLayer;
	
	
	
	
	
	
	
	
	private Bar healthBar;
	
	private SpriteSheet bloodSpriteSheet;
	private Animation bloodAnimation;
	private boolean drawBlood;
	
	
	
	

	
	private float relativeToMapX;
	private float relativeToMapY;
	
	private float screenRelativeX;
	private float screenRelativeY;
	
	private SpriteSheet spriteSheet;
	
	private boolean isAlive;
	
	private int spriteSize;
	
	private CollisionBox collisionBox;
	private CollisionBox hitBox;
	
	private Circle aggressionCircle;
	private int aggressionCircleRadius = 320;
	
	private boolean isGoingToPlayer = false;
	
	private List<Node> path;

	private Player player = CharacterManager.getPlayer();

	private int notWalkableLayerIndex;
	private TiledMap tiledMap;
	
	private boolean goUp;
	private boolean goDown;
	private boolean goLeft;
	private boolean goRight;
	
	private boolean goUpLeft;
	private boolean goUpRight;
	private boolean goDownLeft;
	private boolean goDownRight;
		
	private boolean isAttacking;
	
	private boolean damageDealt = false;
		
	private Item itemDrop;
	
	private int experienceForPlayer;
	
	private int damageOutput;
	
	private boolean iceblocked; //If NPC is blocked by iceblock
	private long iceblockedTimestamp; //Time when player was iceblocked
	
	private Animation iceblockAnimation;
	
	private boolean bloodtheft;
	private int bloodtheftCounter;
	private long bloodtheftTimestamp;
	
	public Wolf(float relativeToMapX, float relativeToMapY, int maxHealth, Item itemDrop, int experienceForPlayer, int damageOutput) throws SlickException {
		
		spriteSheet = new SpriteSheet("resources/WolfSpriteSheet.png", 64, 64);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, false);
		lookDownAnimation = new Animation(spriteSheet, 0, 2, 0, 2, true, 100, false);
		lookLeftAnimation = new Animation(spriteSheet, 0, 1, 0, 1, true, 100, false);
		lookRightAnimation = new Animation(spriteSheet, 0, 3, 0, 3, true, 100, false);
		
		howlUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, false);
		howlDownAnimation = new Animation(spriteSheet, 0, 2, 4, 2, true, 100, false);
		howlLeftAnimation = new Animation(spriteSheet, 0, 1, 3, 1, true, 100, false);
		howlRightAnimation = new Animation(spriteSheet, 0, 3, 3, 3, true, 100, false);
		
		walkUpAnimation = new Animation(spriteSheet, 0, 4, 3, 4, true, 100, false);
		walkDownAnimation = new Animation(spriteSheet, 0, 6, 3, 6, true, 100, false);
		walkLeftAnimation = new Animation(spriteSheet, 0, 5, 4, 5, true, 100, false);
		walkRightAnimation = new Animation(spriteSheet, 0, 7, 4, 7, true, 100, false);
		
		runUpAnimation = new Animation(spriteSheet, 0, 8, 4, 8, true, 100, false);
		runDownAnimation = new Animation(spriteSheet, 0, 10, 4, 10, true, 100, false);
		runLeftAnimation = new Animation(spriteSheet, 0, 9, 4, 9, true, 100, false);
		runRightAnimation = new Animation(spriteSheet, 0, 11, 4, 11, true, 100, false);
		
		attackUpAnimation = new Animation(spriteSheet, 0, 12, 4, 12, true, 100, false);
		attackDownAnimation = new Animation(spriteSheet, 0, 14, 4, 14, true, 100, false);
		attackLeftAnimation = new Animation(spriteSheet, 0, 13, 4, 13, true, 100, false);
		attackRightAnimation = new Animation(spriteSheet, 0, 15, 4, 15, true, 100, false);
		
		dieUpAnimation = new Animation(spriteSheet, 0, 16, 3, 16, true, 100, false);
		dieDownAnimation = new Animation(spriteSheet, 0, 18, 3, 18, true, 100, false);
		dieLeftAnimation = new Animation(spriteSheet, 0, 17, 3, 17, true, 100, false);
		dieRightAnimation = new Animation(spriteSheet, 0, 19, 3, 19, true, 100, false);
		
		attackUpAnimation = new Animation(spriteSheet, 0, 12, 4, 12, true, 100, false);
		attackDownAnimation = new Animation(spriteSheet, 0, 14, 4, 14, true, 100, false);
		attackLeftAnimation = new Animation(spriteSheet, 0, 13, 4, 13, true, 100, false);
		attackRightAnimation = new Animation(spriteSheet, 0, 15, 4, 15, true, 100, false);
		
		dieUpAnimation = new Animation(spriteSheet, 0, 16, 3, 16, true, 100, false);
		dieDownAnimation = new Animation(spriteSheet, 0, 18, 3, 18, true, 100, false);
		dieLeftAnimation = new Animation(spriteSheet, 0, 17, 3, 17, true, 100, false);
		dieRightAnimation = new Animation(spriteSheet, 0, 19, 3, 19, true, 100, false);
		
		attackUpAnimation.setLooping(false);
		attackDownAnimation.setLooping(false);
		attackLeftAnimation.setLooping(false);
		attackRightAnimation.setLooping(false);
		
		dieUpAnimation.setLooping(false);
		dieDownAnimation.setLooping(false);
		dieLeftAnimation.setLooping(false);
		dieRightAnimation.setLooping(false);
				
		currentAnimation = lookDownAnimation;
		
		
		
		lookUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 0, 0, 0, true, 100, false);
		lookDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 4, 0, 4, true, 100, false);

		howlUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 0, 0, 0, true, 100, false);
		howlDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 4, 4, 4, true, 100, false);
		
		walkUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 8, 3, 8, true, 100, false);
		walkDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 12, 3, 12, true, 100, false);

		runUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 16, 4, 16, true, 100, false);
		runDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 20, 4, 20, true, 100, false);

		attackUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 24, 4, 24, true, 100, false);
		attackDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 28, 4, 28, true, 100, false);

		dieUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 32, 3, 32, true, 100, false);
		dieDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 36, 3, 36, true, 100, false);

		attackUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 24, 4, 24, true, 100, false);
		attackDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 28, 4, 28, true, 100, false);

		dieUpAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 32, 3, 32, true, 100, false);
		dieDownAnimationUpperLayer = new Animation(new SpriteSheet("resources/WolfSpriteSheet.png", 64, 32), 0, 36, 3, 36, true, 100, false);

		
		currentAnimationUpperLayer = lookDownAnimationUpperLayer;
		
		
		this.relativeToMapX = relativeToMapX;
		this.relativeToMapY = relativeToMapY;
		
		collisionBox = new CollisionBox(relativeToMapX + 8, relativeToMapY + 8, 16, 32);
		setHitBox(new CollisionBox(relativeToMapX + 8, relativeToMapY + 8, 16, 32));
		
		healthBar = new Bar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, maxHealth, maxHealth, Color.red);
		
		isAlive = true;
		bloodSpriteSheet = new SpriteSheet("resources/blood.png", 64, 64);
		bloodAnimation = new Animation(bloodSpriteSheet, 0, 0, 7, 0, true, 50, true);
		bloodAnimation.setLooping(false);
		drawBlood = false;
		
		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();
		
		goUp = false;
		goDown = false;
		goLeft = false;
		goRight = false;
		
		goUpLeft = false;
		goUpRight = false;
		goDownLeft = false;
		goDownRight = false;
		
		this.itemDrop = itemDrop;
		this.experienceForPlayer = experienceForPlayer;
		this.damageOutput = damageOutput;
		
		iceblockAnimation = new Animation(new SpriteSheet("resources/iceblockSprite.png", 64, 64), 0, 0, 0, 0, true, 100, true);
		
		spriteSize = 64;
		
	}

	public void update() throws SlickException {
				
		screenRelativeX = (int) Game.getCurrentMap().getX() + relativeToMapX - spriteSize / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + relativeToMapY  - spriteSize / 4;

		healthBar.setX(screenRelativeX);
		healthBar.setY(screenRelativeY);
		
		collisionBox.setX(getRelativeToMapX() + 8);
		collisionBox.setY(getRelativeToMapY() + 8);
		
		hitBox.setX(getRelativeToMapX() + 8);
		hitBox.setY(getRelativeToMapY() + 8);
		
		
	}

	public void render(Graphics g) {
		currentAnimation.draw(screenRelativeX, screenRelativeY);
		
		if(drawBlood) {
			drawBlood(screenRelativeX, screenRelativeY);
		}

	}
	
	public void renderUpperLayer(Graphics g) {
		currentAnimationUpperLayer.draw(screenRelativeX, screenRelativeY);
		
		if(healthBar.getCurrentValue() > 0) {
			healthBar.render(g);
		}
	}
	
	public void decreaseHealth(int amount) {
		
		if(isAlive()) {
			
			healthBar.setCurrentValue(healthBar.getCurrentValue() - amount);
			
			if(healthBar.getCurrentValue() <= 0) {
				healthBar.setCurrentValue(0);
				currentAnimation = dieDownAnimation;
				currentAnimationUpperLayer = dieDownAnimationUpperLayer;
				isAlive = false;
				bloodtheft = false;
				bloodtheftCounter = 0;
				
				player.addExperience(experienceForPlayer);
								
				if(itemDrop != null) {
					CharacterManager.getPlayer().getInventoryWindow().addItem(itemDrop);
					CharacterManager.getPlayer().getNewItemWindow().showWindow(itemDrop);
				}
			}
						
			drawBlood = true;
		
		}
		
	}
	
	public void drawBlood(float screenRelativeX, float screenRelativeY) {
		
		bloodAnimation.draw(screenRelativeX, screenRelativeY);
		if(bloodAnimation.isStopped()) {
			drawBlood = false;
			bloodAnimation.restart();
		}
		
	}
	
	public boolean isUpCollision(float distance) {
		
		if(collisionBox.willIntersectUp(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		for(NPC npc : npcList) {
			if(collisionBox.willIntersectUp(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
		}
		
		ArrayList<Wolf> wolfList = new ArrayList<Wolf>(WolfManager.getWolfList());
		wolfList.remove(this);
		
		for(Wolf wolf : wolfList) {
			if(collisionBox.willIntersectUp(wolf.getCollisionBox(), 5) && wolf.isAlive()) {
				return true;
			}
		}
				
		if(tiledMap.getTileId((int) collisionBox.getTopLeftX()/Main.TILE_SIZE, (int) (collisionBox.getTopLeftY() - distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) collisionBox.getTopRightX()/Main.TILE_SIZE, (int) (collisionBox.getTopRightY() - distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean isDownCollision(float distance) {
		
		if(collisionBox.willIntersectDown(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		for(NPC npc : npcList) {
			if(collisionBox.willIntersectDown(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
		}
				
		ArrayList<Wolf> wolfList = new ArrayList<Wolf>(WolfManager.getWolfList());
		wolfList.remove(this);
		
		for(Wolf wolf : wolfList) {
			if(collisionBox.willIntersectDown(wolf.getCollisionBox(), 5) && wolf.isAlive()) {
				return true;
			}
		}
		
		if(tiledMap.getTileId((int) collisionBox.getBottomLeftX()/Main.TILE_SIZE, (int) (collisionBox.getBottomLeftY() + distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) collisionBox.getBottomRightX()/Main.TILE_SIZE, (int) (collisionBox.getBottomRightY() + distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isLeftCollision(float distance) {
		
		if(collisionBox.willIntersectLeft(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		for(NPC npc : npcList) {
			if(collisionBox.willIntersectLeft(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
		}
			
		ArrayList<Wolf> wolfList = new ArrayList<Wolf>(WolfManager.getWolfList());
		wolfList.remove(this);
		
		for(Wolf wolf : wolfList) {
			if(collisionBox.willIntersectLeft(wolf.getCollisionBox(), 5) && wolf.isAlive()) {
				return true;
			}
		}
		
		if(tiledMap.getTileId((int) (collisionBox.getTopLeftX() - distance)/Main.TILE_SIZE, (int) collisionBox.getTopLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (collisionBox.getBottomLeftX() - distance)/Main.TILE_SIZE, (int) collisionBox.getBottomLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean isRightCollision(float distance) {
		
		if(collisionBox.willIntersectRight(player.getCollisionBox(), 5)) {
			return true;
		}
	
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		for(NPC npc : npcList) {
			if(collisionBox.willIntersectRight(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
		}
		
		ArrayList<Wolf> wolfList = new ArrayList<Wolf>(WolfManager.getWolfList());
		wolfList.remove(this);
		
		for(Wolf wolf : wolfList) {
			if(collisionBox.willIntersectRight(wolf.getCollisionBox(), 5) && wolf.isAlive()) {
				return true;
			}
		}
		
		if(tiledMap.getTileId((int) (collisionBox.getTopRightX() + distance)/Main.TILE_SIZE, (int) collisionBox.getTopRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (collisionBox.getBottomRightX() + distance)/Main.TILE_SIZE, (int) collisionBox.getBottomRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

	public int getExperienceForPlayer() {
		return experienceForPlayer;
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public CollisionBox getHitBox() {
		return hitBox;
	}

	public void setHitBox(CollisionBox hitBox) {
		this.hitBox = hitBox;
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
	
}