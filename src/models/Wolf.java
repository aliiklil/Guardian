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
import util.CollisionBox;
import pathfinding.Node;
import pathfinding.AStar;

public class Wolf {

	private float screenRelativeX;
	private float screenRelativeY;
	
	private SpriteSheet spriteSheet;
	
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
	private Animation attackeftAnimation;
	private Animation attackRightAnimation;
	
	private Animation dieAnimation;
	
	private Animation currentAnimation;
	
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
	
	public Wolf(float relativeToMapX, float relativeToMapY, int maxHealth, String spriteSheetPath, Item itemDrop, int experienceForPlayer, int damageOutput) throws SlickException {
		
		spriteSheet = new SpriteSheet("resources/WolfSpriteSheet.png", 64, 64);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, false);
		lookDownAnimation = new Animation(spriteSheet, 0, 2, 0, 2, true, 100, false);
		lookLeftAnimation = new Animation(spriteSheet, 0, 1, 0, 1, true, 100, false);
		lookRightAnimation = new Animation(spriteSheet, 0, 3, 0, 3, true, 100, false);
		
		howlUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, false);
		howlDownAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		howlLeftAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		howlRightAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		
		walkUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		walkDownAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		walkLeftAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		walkRightAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		
		runUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		runDownAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		runLeftAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		runRightAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		
		attackUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		ttackDownAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		attackeftAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		attackRightAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		
		dieAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		
		
		
		
		
		
		
		
		
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
		
	}

	public void update() throws SlickException {
				
		screenRelativeX = (int) Game.getCurrentMap().getX() + getRelativeToMapX() - getSpriteSize() / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + getRelativeToMapY()  - getSpriteSize() / 2;

	}

	public void render(Graphics g) {
		
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
	
}