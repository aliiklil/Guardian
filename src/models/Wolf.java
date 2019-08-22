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

	
	
	
	
	private float relativeToMapX;
	private float relativeToMapY;
	
	private float screenRelativeX;
	private float screenRelativeY;
	
	private float centerX;
	private float centerY;
	
	private int centerXTile;
	private int centerYTile;

	//Current speed of the character
	private float movementSpeed = 2f;
	private float diagonalMovementSpeed = 1.5f;
	
	private SpriteSheet spriteSheet;
	
	private boolean isAlive;
	
	private int spriteSize;
	
	private CollisionBox collisionBox;
	private CollisionBox hitBox;
	
	private Bar healthBar;
	
	private SpriteSheet bloodSpriteSheet;
	private Animation bloodAnimation;
	private boolean drawBlood;
	
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
		
		lookUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, true);
		lookDownAnimation = new Animation(spriteSheet, 0, 2, 0, 2, true, 100, true);
		lookLeftAnimation = new Animation(spriteSheet, 0, 1, 0, 1, true, 100, true);
		lookRightAnimation = new Animation(spriteSheet, 0, 3, 0, 3, true, 100, true);
		
		howlUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, true);
		howlDownAnimation = new Animation(spriteSheet, 0, 2, 4, 2, true, 100, true);
		howlLeftAnimation = new Animation(spriteSheet, 0, 1, 3, 1, true, 100, true);
		howlRightAnimation = new Animation(spriteSheet, 0, 3, 3, 3, true, 100, true);
		
		walkUpAnimation = new Animation(spriteSheet, 0, 4, 3, 4, true, 100, true);
		walkDownAnimation = new Animation(spriteSheet, 0, 6, 3, 6, true, 100, true);
		walkLeftAnimation = new Animation(spriteSheet, 0, 5, 4, 5, true, 100, true);
		walkRightAnimation = new Animation(spriteSheet, 0, 7, 4, 7, true, 100, true);
		
		runUpAnimation = new Animation(spriteSheet, 0, 8, 4, 8, true, 100, true);
		runDownAnimation = new Animation(spriteSheet, 0, 10, 4, 10, true, 100, true);
		runLeftAnimation = new Animation(spriteSheet, 0, 9, 4, 9, true, 100, true);
		runRightAnimation = new Animation(spriteSheet, 0, 11, 4, 11, true, 100, true);
		
		attackUpAnimation = new Animation(spriteSheet, 0, 12, 4, 12, true, 100, true);
		attackDownAnimation = new Animation(spriteSheet, 0, 14, 4, 14, true, 100, true);
		attackLeftAnimation = new Animation(spriteSheet, 0, 13, 4, 13, true, 100, true);
		attackRightAnimation = new Animation(spriteSheet, 0, 15, 4, 15, true, 100, true);
		
		dieUpAnimation = new Animation(spriteSheet, 0, 16, 3, 16, true, 100, true);
		dieDownAnimation = new Animation(spriteSheet, 0, 18, 3, 18, true, 100, true);
		dieLeftAnimation = new Animation(spriteSheet, 0, 17, 3, 17, true, 100, true);
		dieRightAnimation = new Animation(spriteSheet, 0, 19, 3, 19, true, 100, true);
		
		attackUpAnimation = new Animation(spriteSheet, 0, 12, 4, 12, true, 100, true);
		attackDownAnimation = new Animation(spriteSheet, 0, 14, 4, 14, true, 100, true);
		attackLeftAnimation = new Animation(spriteSheet, 0, 13, 4, 13, true, 100, true);
		attackRightAnimation = new Animation(spriteSheet, 0, 15, 4, 15, true, 100, true);
		
		dieUpAnimation = new Animation(spriteSheet, 0, 16, 3, 16, true, 100, true);
		dieDownAnimation = new Animation(spriteSheet, 0, 18, 3, 18, true, 100, true);
		dieLeftAnimation = new Animation(spriteSheet, 0, 17, 3, 17, true, 100, true);
		dieRightAnimation = new Animation(spriteSheet, 0, 19, 3, 19, true, 100, true);
		
		attackUpAnimation.setLooping(false);
		attackDownAnimation.setLooping(false);
		attackLeftAnimation.setLooping(false);
		attackRightAnimation.setLooping(false);
		
		dieUpAnimation.setLooping(false);
		dieDownAnimation.setLooping(false);
		dieLeftAnimation.setLooping(false);
		dieRightAnimation.setLooping(false);
				
		currentAnimation = lookDownAnimation;
		
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
		
		centerX = relativeToMapX + Main.TILE_SIZE/2;
		centerY = relativeToMapY + Main.TILE_SIZE/2;
		
		centerXTile = (int) (centerX / Main.TILE_SIZE);
		centerYTile = (int) (centerY / Main.TILE_SIZE);
		
		
		System.out.println("centerX " + centerX);
		System.out.println("centerY " + centerY);
		System.out.println("centerXTile " + centerXTile);
		System.out.println("centerYTile " + centerYTile);
		
		aggressionCircle = new Circle(centerX, centerY, aggressionCircleRadius);
		
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
		
		if(isAlive() && !iceblocked) {
			goToPlayer();
			//attackPlayer();
		}
		
	}

	public void render(Graphics g) {
		currentAnimation.draw(screenRelativeX, screenRelativeY);
		
		if(drawBlood) {
			drawBlood(screenRelativeX, screenRelativeY);
		}

	}
	
	public void renderHealthBar(Graphics g) {
		if(healthBar.getCurrentValue() > 0) {
			healthBar.render(g);
		}
	}
	
	private void goToPlayer() {
		
		if(!isGoingToPlayer && aggressionCircle.contains(player.getCenterX(), player.getCenterY())) {
			isGoingToPlayer = true;
		}
	
		if(isGoingToPlayer) {
			path = findPath();
		}
		
		if(isGoingToPlayer && path != null && !path.isEmpty() && centerYTile == path.get(0).getRow() && centerXTile == path.get(0).getCol() && (centerX+16) % 32 == 0 && (centerY+16) % 32 == 0) {
			path.remove(0);
			
			if(!path.isEmpty()) {
			
				if(!goUp && centerYTile > path.get(0).getRow() && centerXTile == path.get(0).getCol()) {		
					goUp = true;
					goDown = false;
					goLeft = false;
					goRight = false;
					
					goUpLeft = false;
					goUpRight = false;
					goDownLeft = false;
					goDownRight = false;
					
					isAttacking = false;
				}
				
				if(!goDown && centerYTile < path.get(0).getRow() && centerXTile == path.get(0).getCol()) {	
					goUp = false;
					goDown = true;
					goLeft = false;
					goRight = false;
					
					goUpLeft = false;
					goUpRight = false;
					goDownLeft = false;
					goDownRight = false;
					
					isAttacking = false;
				}
							
				if(!goLeft && centerXTile > path.get(0).getCol() && centerYTile == path.get(0).getRow()) {	
					goUp = false;
					goDown = false;
					goLeft = true;
					goRight = false;
					
					goUpLeft = false;
					goUpRight = false;
					goDownLeft = false;
					goDownRight = false;
					
					isAttacking = false;
				}
				
				if(!goRight && centerXTile < path.get(0).getCol() && centerYTile == path.get(0).getRow()) {	
					goUp = false;
					goDown = false;
					goLeft = false;
					goRight = true;
					
					goUpLeft = false;
					goUpRight = false;
					goDownLeft = false;
					goDownRight = false;

					isAttacking = false;
				}
				
				if(!goUpLeft && centerYTile > path.get(0).getRow() && centerXTile > path.get(0).getCol()) {		
					goUp = false;
					goDown = false;
					goLeft = false;
					goRight = false;
					
					goUpLeft = true;
					goUpRight = false;
					goDownLeft = false;
					goDownRight = false;

					isAttacking = false;
				}
				
				if(!goUpRight && centerYTile > path.get(0).getRow() && centerXTile < path.get(0).getCol()) {		
					goUp = false;
					goDown = false;
					goLeft = false;
					goRight = false;
					
					goUpLeft = false;
					goUpRight = true;
					goDownLeft = false;
					goDownRight = false;
				
					isAttacking = false;
				}
				
				if(!goDownLeft && centerYTile < path.get(0).getRow() && centerXTile > path.get(0).getCol()) {		
					goUp = false;
					goDown = false;
					goLeft = false;
					goRight = false;
					
					goUpLeft = false;
					goUpRight = false;
					goDownLeft = true;
					goDownRight = false;
					
					isAttacking = false;
				}
				
				if(!goDownRight && centerYTile < path.get(0).getRow() && centerXTile < path.get(0).getCol()) {		
					goUp = false;
					goDown = false;
					goLeft = false;
					goRight = false;
					
					goUpLeft = false;
					goUpRight = false;
					goDownLeft = false;
					goDownRight = true;

					isAttacking = false;
				}
			
			} else {
				goUp = false;
				goDown = false;
				goLeft = false;
				goRight = false;
				
				goUpLeft = false;
				goUpRight = false;
				goDownLeft = false;
				goDownRight = false;
				
				isAttacking = false;			
			}
	
		}
		
		if(isGoingToPlayer && path != null && !path.isEmpty() && !isAttacking) {
			
			if(goUp && !isUpCollision(movementSpeed)) {		
				relativeToMapY = relativeToMapY - movementSpeed;
				currentAnimation = runUpAnimation;
				isAttacking = false;
			}
			
			if(goDown && !isDownCollision(movementSpeed)) {	
				relativeToMapY = relativeToMapY + movementSpeed;
				currentAnimation = runDownAnimation;
				isAttacking = false;
			}
						
			if(goLeft && !isLeftCollision(movementSpeed)) {	
				relativeToMapX = relativeToMapX - movementSpeed;
				currentAnimation = runLeftAnimation;
				isAttacking = false;
			}
			
			if(goRight && !isRightCollision(movementSpeed)) {	
				relativeToMapX = relativeToMapX + movementSpeed;
				currentAnimation = runRightAnimation;
				isAttacking = false;
			}
			
			if(goUpLeft && !isUpCollision(movementSpeed) && !isLeftCollision(movementSpeed)) {
				
				if(!isUpCollision(movementSpeed)) {
					relativeToMapY = relativeToMapY - diagonalMovementSpeed;
				}
				
				if(!isLeftCollision(movementSpeed)) {
					relativeToMapX = relativeToMapX - diagonalMovementSpeed;
				}
				
				currentAnimation = runLeftAnimation;
				isAttacking = false;
			}
			
			if(goUpRight && !isUpCollision(movementSpeed) && !isRightCollision(movementSpeed)) {
				
				if(!isUpCollision(movementSpeed)) {
					relativeToMapY = relativeToMapY - diagonalMovementSpeed;
				}
				
				if(!isRightCollision(movementSpeed)) {
					relativeToMapX = relativeToMapX + diagonalMovementSpeed;
				}
				
				currentAnimation = runRightAnimation;
				isAttacking = false;
			}
			
			if(goDownLeft && !isDownCollision(movementSpeed) && !isLeftCollision(movementSpeed)) {
				
				if(!isDownCollision(movementSpeed)) {
					relativeToMapY = relativeToMapY + diagonalMovementSpeed;
				}
				
				if(!isLeftCollision(movementSpeed)) {
					relativeToMapX = relativeToMapX - diagonalMovementSpeed;
				}
				currentAnimation = runLeftAnimation;
				isAttacking = false;
			}
			
			if(goDownRight && !isDownCollision(movementSpeed) && !isRightCollision(movementSpeed)) {
				
				if(!isDownCollision(movementSpeed)) {
					relativeToMapY = relativeToMapY + diagonalMovementSpeed;
				}
				
				if(!isRightCollision(movementSpeed)) {
					relativeToMapX = relativeToMapX + diagonalMovementSpeed;
				}
				currentAnimation = runRightAnimation;
				isAttacking = false;
			}
						
		}
		
		if(path != null && path.isEmpty()) {
			
			if(centerXTile == player.getCenterXTile() && centerYTile - 1 == player.getCenterYTile()) {
				currentAnimation = lookUpAnimation;
			}
			
			if(centerXTile == player.getCenterXTile() && centerYTile + 1 == player.getCenterYTile()) {
				currentAnimation = lookDownAnimation;
			}
			
			if(centerXTile - 1 == player.getCenterXTile() && centerYTile == player.getCenterYTile()) {
				currentAnimation = lookLeftAnimation;
			}
			
			if(centerXTile + 1 == player.getCenterXTile() && centerYTile == player.getCenterYTile()) {
				currentAnimation = lookRightAnimation;
			}
			
		}
		
	}
	
	/*
	private void attackPlayer() {
		
		if(isGoingToPlayer && player.isAlive()) {
			if(path.isEmpty() || (getCenterXTile() == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) || isTouchingPlayer()
					|| equippedMelee.getAttackUpCollisionBox().intersects(player.getHitBox()) || equippedMelee.getAttackDownCollisionBox().intersects(player.getHitBox()) 
					|| equippedMelee.getAttackLeftCollisionBox().intersects(player.getHitBox()) || equippedMelee.getAttackRightCollisionBox().intersects(player.getHitBox())) {
				
				if((super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation()) && equippedMelee.getAttackUpCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getSlayUpAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if((super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation()) && equippedMelee.getAttackDownCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getSlayDownAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if((super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation()) && equippedMelee.getAttackLeftCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getSlayLeftAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if((super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation()) && equippedMelee.getAttackRightCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getSlayRightAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if(isAttacking && super.getCurrentAnimation().isStopped()) {
					super.getCurrentAnimation().restart();
					isAttacking = true;
					damageDealt = false;
				}
			}
		}
		
		if(!damageDealt) {
			
			int damageToDeal = damageOutput;
			
			if(critChance > new Random().nextDouble()) {
				damageToDeal = damageToDeal * 5;
			}
			
			damageToDeal = (int) (damageToDeal * (1 - player.getArmorProtection()/100.0));
						
			if(super.getCurrentAnimation() == super.getSlayUpAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(equippedMelee.getAttackUpCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
				}
			}
			
			if(super.getCurrentAnimation() == super.getSlayDownAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(equippedMelee.getAttackDownCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
					}
			}
			
			if(super.getCurrentAnimation() == super.getSlayLeftAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(equippedMelee.getAttackLeftCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
				}		
			}
			
			if(super.getCurrentAnimation() == super.getSlayRightAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(equippedMelee.getAttackRightCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
					}						
			}		
			
		}
		
		if(!equippedMelee.getAttackUpCollisionBox().intersects(player.getHitBox()) || !equippedMelee.getAttackDownCollisionBox().intersects(player.getHitBox()) 
				|| !equippedMelee.getAttackLeftCollisionBox().intersects(player.getHitBox()) || !equippedMelee.getAttackRightCollisionBox().intersects(player.getHitBox())) {
			
			if(super.getCurrentAnimation() == super.getSlayUpAnimation() && !equippedMelee.getAttackUpCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookUpAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getSlayDownAnimation() && !equippedMelee.getAttackDownCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookDownAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getSlayLeftAnimation() && !equippedMelee.getAttackLeftCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getSlayRightAnimation() && !equippedMelee.getAttackRightCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookRightAnimation());
				isAttacking = false;
			}
			
		}
		
		if(!player.isAlive()) {
			
			if(super.getCurrentAnimation() == super.getSlayUpAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookUpAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getSlayDownAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookDownAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getSlayLeftAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getSlayRightAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookRightAnimation());
				isAttacking = false;
			}
			
		}
						
	}
	*/
	
	private List<Node> findPath() {
		
		Node initialNode = new Node(centerYTile, centerXTile);
        Node finalNode = new Node(player.getCenterYTile(), player.getCenterXTile());
        
        int rows = Game.getCurrentMap().getTiledMap().getHeight();
        int cols = Game.getCurrentMap().getTiledMap().getWidth();
                
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        
        int[][] blocksArray = new int[rows * cols][2];
        
        int notWalkableLayerIndex = Game.getNotWalkableLayerIndex();
        
        int k = 0;
        
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < cols; j++) {
        		if(Game.getCurrentMap().getTiledMap().getTileId(j, i, notWalkableLayerIndex) != 0) {
        			blocksArray[k][0] = i;
        			blocksArray[k][1] = j;
        			k++;
        		}
        	}
        }
        
        
        ArrayList<Wolf> wolfList = new ArrayList<Wolf>(WolfManager.getWolfList());
        wolfList.remove(this);
        for(Wolf wolf : wolfList) {
        	blocksArray[k][0] = wolf.getCenterYTile();
			blocksArray[k][1] = wolf.getCenterXTile();
			k++;
        }
        
        aStar.setBlocks(blocksArray);
        
        List<Node> path = aStar.findPath();
        
        if(path.size() >= 2) {
        
	        Node lastNode = path.get(path.size() - 1);
	        Node foreLastNode = path.get(path.size() - 2);
	        
	        if(foreLastNode.getRow() + 1 == lastNode.getRow() && foreLastNode.getCol() + 1 == lastNode.getCol()) {
	        	path.remove(path.size() - 1);
	        	if(Game.getCurrentMap().getTiledMap().getTileId(foreLastNode.getCol(), foreLastNode.getRow() + 1, notWalkableLayerIndex) != 0) {
	        		path.add(new Node(foreLastNode.getRow(), foreLastNode.getCol() + 1));
	        	} else {
	        		path.add(new Node(foreLastNode.getRow() + 1, foreLastNode.getCol()));
	        	}
	        } else if(foreLastNode.getRow() + 1 == lastNode.getRow() && foreLastNode.getCol() - 1 == lastNode.getCol()) {
	        	path.remove(path.size() - 1);
	        	if(Game.getCurrentMap().getTiledMap().getTileId(foreLastNode.getCol(), foreLastNode.getRow() + 1, notWalkableLayerIndex) != 0) {
	        		path.add(new Node(foreLastNode.getRow(), foreLastNode.getCol() - 1));
	        	} else {
	        		path.add(new Node(foreLastNode.getRow() + 1, foreLastNode.getCol()));
	        	}
	        } else if(foreLastNode.getRow() - 1 == lastNode.getRow() && foreLastNode.getCol() - 1 == lastNode.getCol()) {
	        	path.remove(path.size() - 1);
	        	if(Game.getCurrentMap().getTiledMap().getTileId(foreLastNode.getCol(), foreLastNode.getRow() - 1, notWalkableLayerIndex) != 0) {
	        		path.add(new Node(foreLastNode.getRow(), foreLastNode.getCol() - 1));
	        	} else {
	        		path.add(new Node(foreLastNode.getRow() - 1, foreLastNode.getCol()));
	        	}
	        } else  if(foreLastNode.getRow() - 1 == lastNode.getRow() && foreLastNode.getCol() + 1 == lastNode.getCol()) {
	        	path.remove(path.size() - 1);
	        	if(Game.getCurrentMap().getTiledMap().getTileId(foreLastNode.getCol(), foreLastNode.getRow() - 1, notWalkableLayerIndex) != 0) {
	        		path.add(new Node(foreLastNode.getRow(), foreLastNode.getCol() + 1));
	        	} else {
	        		path.add(new Node(foreLastNode.getRow() - 1, foreLastNode.getCol()));
	        	}
	        } else {
	        	path.remove(path.size() - 1);
	        }
	        
		}
            
        return path;
               		
	}
	
	public void decreaseHealth(int amount) {
		
		if(isAlive()) {
			
			healthBar.setCurrentValue(healthBar.getCurrentValue() - amount);
			
			if(healthBar.getCurrentValue() <= 0) {
				healthBar.setCurrentValue(0);
				
				if(currentAnimation == lookUpAnimation || currentAnimation == howlUpAnimation || currentAnimation == walkUpAnimation || currentAnimation == runUpAnimation || currentAnimation == attackUpAnimation) {
					currentAnimation = dieUpAnimation;
				}
				
				if(currentAnimation == lookDownAnimation || currentAnimation == howlDownAnimation || currentAnimation == walkDownAnimation || currentAnimation == runDownAnimation || currentAnimation == attackDownAnimation) {
					currentAnimation = dieDownAnimation;
				}
				
				if(currentAnimation == lookLeftAnimation || currentAnimation == howlLeftAnimation || currentAnimation == walkLeftAnimation || currentAnimation == runLeftAnimation || currentAnimation == attackLeftAnimation) {
					currentAnimation = dieLeftAnimation;
				}
				
				if(currentAnimation == lookRightAnimation || currentAnimation == howlRightAnimation || currentAnimation == walkRightAnimation || currentAnimation == runRightAnimation || currentAnimation == attackRightAnimation) {
					currentAnimation = dieRightAnimation;
				}
				
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
	
	
	
}