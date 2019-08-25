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
import manager.ItemTypeManager;
import manager.MobManager;
import util.CollisionBox;
import pathfinding.Node;
import pathfinding.AStar;

public class Wolf extends Mob {

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
	
	private float screenRelativeX;
	private float screenRelativeY;
	
	private float runMovementSpeed = 2f;
	private float diagonalRunMovementSpeed = 1.6f;
	
	private float walkMovementSpeed = runMovementSpeed/2;
	private float diagonalWalkMovementSpeed = diagonalRunMovementSpeed/2;

	private float movementSpeed = walkMovementSpeed;
	private float diagonalMovementSpeed = diagonalWalkMovementSpeed;
	
	private SpriteSheet spriteSheet;
	private int spriteSize;
		
	private Bar healthBar;
	private boolean isAlive;
	
	private SpriteSheet bloodSpriteSheet;
	private Animation bloodAnimation;
	private boolean drawBlood;
	
	private Circle aggressionCircle;
	private int aggressionCircleRadius = 640;
	private boolean isGoingToPlayer = false;
	private List<Node> path;
	private long aggressionTimestamp; //Timestamp when the player pulls aggro
	private int durationBeforeRunning = 2000; //Time in milliseconds to run out before wolf starts to run instead of walk towards player
	
	private Player player = MobManager.getPlayer();
	
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
	
	private CollisionBox currentAttackBox;
	private CollisionBox horizontalAttackBox;
	private CollisionBox verticalAttackBox;
	
	public Wolf(float relativeToMapX, float relativeToMapY, String spriteSheetPath, int maxHealth, Item itemDrop, int experienceForPlayer, int damageOutput, boolean alive) throws SlickException {
		
		super(relativeToMapX, relativeToMapY, alive);
				
		spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		
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
		
		screenRelativeX = (int) Game.getCurrentMap().getX() + relativeToMapX - spriteSize / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + relativeToMapY  - spriteSize / 2;
						
		setCollisionBox(new CollisionBox(relativeToMapX + 6, relativeToMapY + 10, 20, 20));
		setHitBox(new CollisionBox(relativeToMapX, relativeToMapY - 16, 32, 32));
			
		healthBar = new Bar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, maxHealth, maxHealth, Color.red);
		
		isAlive = true;
		bloodSpriteSheet = new SpriteSheet("resources/blood.png", 64, 64);
		bloodAnimation = new Animation(bloodSpriteSheet, 0, 0, 7, 0, true, 50, true);
		bloodAnimation.setLooping(false);
		drawBlood = false;
				
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
		
		aggressionCircle = new Circle(getCenterX(), getCenterY(), aggressionCircleRadius);
		
		horizontalAttackBox = new CollisionBox(relativeToMapX, relativeToMapY - 32, 32, 64);
		verticalAttackBox = new CollisionBox(relativeToMapX - 16, relativeToMapY - 16, 64, 32);

	}

	public void update() throws SlickException {
				
		screenRelativeX = (int) Game.getCurrentMap().getX() + getRelativeToMapX() - spriteSize / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + getRelativeToMapY()  - spriteSize / 2;

		healthBar.setX(screenRelativeX);
		healthBar.setY(screenRelativeY);
		
		getCollisionBox().setX(getRelativeToMapX() + 6);
		getCollisionBox().setY(getRelativeToMapY() + 10);
		
		getHitBox().setX(getRelativeToMapX());
		getHitBox().setY(getRelativeToMapY() - 16);
	
		updateAttackBox();
		
		if(isAlive() && !iceblocked) {
			goToPlayer();
			attackPlayer();
		}
		
		setCenterX(getRelativeToMapX() + Main.TILE_SIZE/2);
		setCenterY(getRelativeToMapY() + Main.TILE_SIZE/2);
		
		setCenterXTile((int) (getCenterX() / Main.TILE_SIZE));
		setCenterYTile((int) (getCenterY() / Main.TILE_SIZE));
		

				
	}

	private void updateAttackBox() {
		
		horizontalAttackBox.setX(getRelativeToMapX());
		horizontalAttackBox.setY(getRelativeToMapY() - 32);
		
		verticalAttackBox.setX(getRelativeToMapX() - 16);
		verticalAttackBox.setY(getRelativeToMapY() - 16);
		
		if(currentAnimation == lookUpAnimation || currentAnimation == howlUpAnimation || currentAnimation == walkUpAnimation || currentAnimation == runUpAnimation || currentAnimation == attackUpAnimation ||
		   currentAnimation == lookDownAnimation || currentAnimation == howlDownAnimation || currentAnimation == walkDownAnimation || currentAnimation == runDownAnimation || currentAnimation == attackDownAnimation) {
			currentAttackBox = horizontalAttackBox;
		}
		
		if(currentAnimation == lookLeftAnimation || currentAnimation == howlLeftAnimation || currentAnimation == walkLeftAnimation || currentAnimation == runLeftAnimation || currentAnimation == attackLeftAnimation ||
		   currentAnimation == lookRightAnimation || currentAnimation == howlRightAnimation || currentAnimation == walkRightAnimation || currentAnimation == runRightAnimation || currentAnimation == attackRightAnimation) {
			currentAttackBox = verticalAttackBox;
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
			aggressionTimestamp = System.currentTimeMillis();
		}
	
		if(isGoingToPlayer && System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
			movementSpeed = runMovementSpeed;
			diagonalMovementSpeed = diagonalRunMovementSpeed;
		}
		
		if(isGoingToPlayer) {
			path = findPath();
		}
		
		if(isGoingToPlayer && path != null && !path.isEmpty() && getCenterYTile() == path.get(0).getRow() && getCenterXTile() == path.get(0).getCol() && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
			path.remove(0);
			
			if(!path.isEmpty()) {
			
				if(!goUp && getCenterYTile() > path.get(0).getRow() && getCenterXTile() == path.get(0).getCol()) {		
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
				
				if(!goDown && getCenterYTile() < path.get(0).getRow() && getCenterXTile() == path.get(0).getCol()) {	
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
							
				if(!goLeft && getCenterXTile() > path.get(0).getCol() && getCenterYTile() == path.get(0).getRow()) {	
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
				
				if(!goRight && getCenterXTile() < path.get(0).getCol() && getCenterYTile() == path.get(0).getRow()) {	
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
				
				if(!goUpLeft && getCenterYTile() > path.get(0).getRow() && getCenterXTile() > path.get(0).getCol()) {		
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
				
				if(!goUpRight && getCenterYTile() > path.get(0).getRow() && getCenterXTile() < path.get(0).getCol()) {		
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
				
				if(!goDownLeft && getCenterYTile() < path.get(0).getRow() && getCenterXTile() > path.get(0).getCol()) {		
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
				
				if(!goDownRight && getCenterYTile() < path.get(0).getRow() && getCenterXTile() < path.get(0).getCol()) {		
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
				setRelativeToMapY(getRelativeToMapY() - movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runUpAnimation;
				} else {
					currentAnimation = walkUpAnimation;
				}
				isAttacking = false;
			}
			
			if(goDown && !isDownCollision(movementSpeed)) {	
				setRelativeToMapY(getRelativeToMapY() + movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runDownAnimation;
				} else {
					currentAnimation = walkDownAnimation;
				}
				isAttacking = false;
			}
						
			if(goLeft && !isLeftCollision(movementSpeed)) {	
				setRelativeToMapX(getRelativeToMapX() - movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runLeftAnimation;
				} else {
					currentAnimation = walkLeftAnimation;
				}
				isAttacking = false;
			}
			
			if(goRight && !isRightCollision(movementSpeed)) {	
				setRelativeToMapX(getRelativeToMapX() + movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runRightAnimation;
				} else {
					currentAnimation = walkRightAnimation;
				}
				isAttacking = false;
			}
			
			if(goUpLeft && !isUpCollision(movementSpeed) && !isLeftCollision(movementSpeed)) {
				
				if(!isUpCollision(movementSpeed)) {
					setRelativeToMapY(getRelativeToMapY() - diagonalMovementSpeed);
				}
				
				if(!isLeftCollision(movementSpeed)) {
					setRelativeToMapX(getRelativeToMapX() - diagonalMovementSpeed);
				}
				
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runLeftAnimation;
				} else {
					currentAnimation = walkLeftAnimation;
				}
				isAttacking = false;
			}
			
			if(goUpRight && !isUpCollision(movementSpeed) && !isRightCollision(movementSpeed)) {
				
				if(!isUpCollision(movementSpeed)) {
					setRelativeToMapY(getRelativeToMapY() - diagonalMovementSpeed);
				}
				
				if(!isRightCollision(movementSpeed)) {
					setRelativeToMapX(getRelativeToMapX() + diagonalMovementSpeed);
				}
				
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runRightAnimation;
				} else {
					currentAnimation = walkRightAnimation;
				}
				isAttacking = false;
			}
			
			if(goDownLeft && !isDownCollision(movementSpeed) && !isLeftCollision(movementSpeed)) {
				
				if(!isDownCollision(movementSpeed)) {
					setRelativeToMapY(getRelativeToMapY() + diagonalMovementSpeed);
				}
				
				if(!isLeftCollision(movementSpeed)) {
					setRelativeToMapX(getRelativeToMapX() - diagonalMovementSpeed);
				}
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runLeftAnimation;
				} else {
					currentAnimation = walkLeftAnimation;
				}
				isAttacking = false;
			}
			
			if(goDownRight && !isDownCollision(movementSpeed) && !isRightCollision(movementSpeed)) {
				
				if(!isDownCollision(movementSpeed)) {
					setRelativeToMapY(getRelativeToMapY() + diagonalMovementSpeed);
				}
				
				if(!isRightCollision(movementSpeed)) {
					setRelativeToMapX(getRelativeToMapX() + diagonalMovementSpeed);
				}
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					currentAnimation = runRightAnimation;
				} else {
					currentAnimation = walkRightAnimation;
				}
				isAttacking = false;
			}
						
		}
		
		if(path != null && path.isEmpty()) {
			
			if(getCenterXTile() == player.getCenterXTile() && getCenterYTile() - 1 == player.getCenterYTile()) {
				currentAnimation = lookUpAnimation;
			}
			
			if(getCenterXTile() == player.getCenterXTile() && getCenterYTile() + 1 == player.getCenterYTile()) {
				currentAnimation = lookDownAnimation;
			}
			
			if(getCenterXTile() - 1 == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) {
				currentAnimation = lookLeftAnimation;
			}
			
			if(getCenterXTile() + 1 == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) {
				currentAnimation = lookRightAnimation;
			}
			
		}
		
	}
	
	
	private void attackPlayer() {
		
		if(isGoingToPlayer && player.isAlive()) {
			if(path.isEmpty() || (getCenterXTile() == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) || isTouchingPlayer() || currentAttackBox.intersects(player.getHitBox())) {
				
				if((currentAnimation == lookUpAnimation || currentAnimation == runUpAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					currentAnimation = attackUpAnimation;
					isAttacking = true;
					damageDealt = false;
				}
				
				if((currentAnimation == lookDownAnimation || currentAnimation == runDownAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					currentAnimation = attackDownAnimation;
					isAttacking = true;
					damageDealt = false;
				}
				
				if((currentAnimation == lookLeftAnimation || currentAnimation == runLeftAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					currentAnimation = attackLeftAnimation;
					isAttacking = true;
					damageDealt = false;
				}
				
				if((currentAnimation == lookRightAnimation || currentAnimation == runRightAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					currentAnimation = attackRightAnimation;
					isAttacking = true;
					damageDealt = false;
				}
				
				if(isAttacking && currentAnimation.isStopped()) {
					currentAnimation.restart();
					isAttacking = true;
					damageDealt = false;
				}
			}
		}
		
		if(!damageDealt) {
			
			int damageToDeal = damageOutput;
			
			if(0.1 > new Random().nextDouble()) {
				damageToDeal = damageToDeal * 5;
			}
			
			damageToDeal = (int) (damageToDeal * (1 - player.getArmorProtection()/100.0));
						
			if(currentAnimation == attackUpAnimation && currentAnimation.getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
				}
			}
			
			if(currentAnimation == attackDownAnimation && currentAnimation.getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
					}
			}
			
			if(currentAnimation == attackLeftAnimation && currentAnimation.getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
				}		
			}
			
			if(currentAnimation == attackRightAnimation && currentAnimation.getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
					}						
			}		
			
		}
		
		if(!currentAttackBox.intersects(player.getHitBox())) {
			
			if(currentAnimation == attackUpAnimation) {
				currentAnimation.restart();
				currentAnimation = lookUpAnimation;
				isAttacking = false;
			}
			
			if(currentAnimation == attackDownAnimation) {
				currentAnimation.restart();
				currentAnimation = lookDownAnimation;
				isAttacking = false;
			}
			
			if(currentAnimation == attackLeftAnimation) {
				currentAnimation.restart();
				currentAnimation = lookLeftAnimation;
				isAttacking = false;
			}
			
			if(currentAnimation == attackRightAnimation) {
				currentAnimation.restart();
				currentAnimation = lookRightAnimation;
				isAttacking = false;
			}
			
		}
		
		if(!player.isAlive()) {
			
			if(currentAnimation == attackUpAnimation) {
				currentAnimation.restart();
				currentAnimation = lookUpAnimation;
				isAttacking = false;
			}
			
			if(currentAnimation == attackDownAnimation) {
				currentAnimation.restart();
				currentAnimation = lookDownAnimation;
				isAttacking = false;
			}
			
			if(currentAnimation == attackLeftAnimation) {
				currentAnimation.restart();
				currentAnimation = lookLeftAnimation;
				isAttacking = false;
			}
			
			if(currentAnimation == attackRightAnimation) {
				currentAnimation.restart();
				currentAnimation = lookRightAnimation;
				isAttacking = false;
			}
			
		}
						
	}
	
	
	private List<Node> findPath() {
		
		Node initialNode = new Node(getCenterYTile(), getCenterXTile());
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
        
        ArrayList<Mob> mobList = new ArrayList<Mob>(MobManager.getMobListWithoutPlayer());
        mobList.remove(this);
        
        for(Mob mob : mobList) {
        	blocksArray[k][0] = mob.getCenterYTile();
			blocksArray[k][1] = mob.getCenterXTile();
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
					MobManager.getPlayer().getInventoryWindow().addItem(itemDrop);
					MobManager.getPlayer().getNewItemWindow().showWindow(itemDrop);
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
	
	private boolean isTouchingPlayer() {
		
		if(getCollisionBox().willIntersectUp(player.getCollisionBox(), 5)) {
			return true;
		}
		
		if(getCollisionBox().willIntersectDown(player.getCollisionBox(), 5)) {
			return true;
		}
		
		if(getCollisionBox().willIntersectLeft(player.getCollisionBox(), 5)) {
			return true;
		}
		
		if(getCollisionBox().willIntersectRight(player.getCollisionBox(), 5)) {
			return true;
		}
		
		return false;
		
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
	
}