package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.Sys;
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
	private int aggressionCircleRadius = 320;
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
			
	private CollisionBox currentAttackBox;
	private CollisionBox horizontalAttackBox;
	private CollisionBox verticalAttackBox;
		
	private boolean isRoaming = true;
	
	private boolean isRoamingUp = false;
	private boolean isRoamingDown = false;
	private boolean isRoamingLeft = true;
	private boolean isRoamingRight = false;
	
	private long roamingTimestamp; //Timestamp when wolf was roaming last time
	private int durationBetweenRoaming = 7000;
	
	public Wolf(float relativeToMapX, float relativeToMapY, String spriteSheetPath, int maxHealth, Item itemDrop, int experienceForPlayer, int damageOutput, boolean alive, boolean hostileToPlayer) throws SlickException {
		
		super(relativeToMapX, relativeToMapY, alive);
				
		spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 100, true);
		lookDownAnimation = new Animation(spriteSheet, 0, 2, 0, 2, true, 100, true);
		lookLeftAnimation = new Animation(spriteSheet, 0, 1, 0, 1, true, 100, true);
		lookRightAnimation = new Animation(spriteSheet, 0, 3, 0, 3, true, 100, true);
		
		howlUpAnimation = new Animation(spriteSheet, 0, 0, 0, 0, true, 300, true);
		howlDownAnimation = new Animation(spriteSheet, 0, 2, 4, 2, true, 300, true);
		howlLeftAnimation = new Animation(spriteSheet, 0, 1, 3, 1, true, 300, true);
		howlRightAnimation = new Animation(spriteSheet, 0, 3, 3, 3, true, 300, true);
		
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
				
		howlUpAnimation.setLooping(false);
		howlDownAnimation.setLooping(false);
		howlLeftAnimation.setLooping(false);
		howlRightAnimation.setLooping(false);
		
		attackUpAnimation.setLooping(false);
		attackDownAnimation.setLooping(false);
		attackLeftAnimation.setLooping(false);
		attackRightAnimation.setLooping(false);
		
		dieUpAnimation.setLooping(false);
		dieDownAnimation.setLooping(false);
		dieLeftAnimation.setLooping(false);
		dieRightAnimation.setLooping(false);
				
		setCurrentAnimation(lookDownAnimation);
		
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
				
		spriteSize = 64;
		
		aggressionCircle = new Circle(getCenterX(), getCenterY(), aggressionCircleRadius);
		
		horizontalAttackBox = new CollisionBox(relativeToMapX, relativeToMapY - 32, 32, 64);
		verticalAttackBox = new CollisionBox(relativeToMapX - 16, relativeToMapY - 16, 64, 32);
		
		setHostileToPlayer(hostileToPlayer);
		
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
		
		if(isAlive() && !isIceblocked()) {
			updateMove();
			updateAttackPlayer();
			updateRoaming();
		}
		
		setCenterX(getRelativeToMapX() + Main.TILE_SIZE/2);
		setCenterY(getRelativeToMapY() + Main.TILE_SIZE/2);
		
		setCenterXTile((int) (getCenterX() / Main.TILE_SIZE));
		setCenterYTile((int) (getCenterY() / Main.TILE_SIZE));
		
		checkIfIceblockIsOver();
		checkIfBloodtheft();
				
	}

	public void render(Graphics g) {
		getCurrentAnimation().draw(screenRelativeX, screenRelativeY);
		
		if(drawBlood) {
			drawBlood(screenRelativeX, screenRelativeY);
		}
		
		if(healthBar.getCurrentValue() > 0) {
			healthBar.render(g);
		}
		
		if(isIceblocked()) {
			getIceblockAnimation().draw(screenRelativeX, screenRelativeY + 2);
		}

	}
	
	public void renderHealthBar(Graphics g) {
		if(healthBar.getCurrentValue() > 0) {
			healthBar.render(g);
		}
	}
	

	private void updateRoaming() {
		
		if(isRoaming && !isGoingToPlayer()) {
		
			if((Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0 && System.currentTimeMillis() - roamingTimestamp > durationBetweenRoaming) {
				
				roamingTimestamp = System.currentTimeMillis();
				
				if(isRoamingUp) {
					isRoamingUp = false;
					isRoamingDown = false;
					isRoamingLeft = true;
					isRoamingRight = false;
				} else if(isRoamingDown) {
					isRoamingUp = false;
					isRoamingDown = false;
					isRoamingLeft = false;
					isRoamingRight = true;
				} else if(isRoamingLeft) {
					isRoamingUp = false;
					isRoamingDown = true;
					isRoamingLeft = false;
					isRoamingRight = false;
				} else if(isRoamingRight) {
					isRoamingUp = true;
					isRoamingDown = false;
					isRoamingLeft = false;
					isRoamingRight = false;
				}
				
			}
			
			if(isRoamingUp && !isUpCollision(movementSpeed)) {	
				if(getCenterYTile() - getStartCenterYTile() == 0 && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
					setCurrentAnimation(lookUpAnimation);
				} else {
					setRelativeToMapY(getRelativeToMapY() - movementSpeed);
					setCurrentAnimation(walkUpAnimation);
				}
			}
			
			if(isRoamingDown && !isDownCollision(movementSpeed)) {		
				if(getCenterYTile() - getStartCenterYTile() == 2 && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
					setCurrentAnimation(lookDownAnimation);
				} else {
					setRelativeToMapY(getRelativeToMapY() + movementSpeed);
					setCurrentAnimation(walkDownAnimation);
				}
			}
			
			if(isRoamingLeft && !isLeftCollision(movementSpeed)) {
				if(getCenterXTile() - getStartCenterXTile() == 0 && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
					setCurrentAnimation(howlLeftAnimation);
	
					if(howlLeftAnimation.isStopped()) {
						setCurrentAnimation(lookLeftAnimation);
					}
				} else {
					setRelativeToMapX(getRelativeToMapX() - movementSpeed);
					setCurrentAnimation(walkLeftAnimation);
				}
			}
			
			if(isRoamingRight && !isRightCollision(movementSpeed)) {
				if(getCenterXTile() - getStartCenterXTile() == 2 && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
					setCurrentAnimation(howlRightAnimation);
					
					if(howlRightAnimation.isStopped()) {
						setCurrentAnimation(lookRightAnimation);
					}
				} else {
					setRelativeToMapX(getRelativeToMapX() + movementSpeed);
					setCurrentAnimation(walkRightAnimation);
				}
			}
			
		} else {
			isRoamingUp = false;
			isRoamingDown = false;
			isRoamingLeft = false;
			isRoamingRight = false;
		}
		
	}

	private void updateAttackBox() {
		
		horizontalAttackBox.setX(getRelativeToMapX());
		horizontalAttackBox.setY(getRelativeToMapY() - 32);
		
		verticalAttackBox.setX(getRelativeToMapX() - 16);
		verticalAttackBox.setY(getRelativeToMapY() - 16);
		
		if(getCurrentAnimation() == lookUpAnimation || getCurrentAnimation() == howlUpAnimation || getCurrentAnimation() == walkUpAnimation || getCurrentAnimation() == runUpAnimation || getCurrentAnimation() == attackUpAnimation ||
				getCurrentAnimation() == lookDownAnimation || getCurrentAnimation() == howlDownAnimation || getCurrentAnimation() == walkDownAnimation || getCurrentAnimation() == runDownAnimation || getCurrentAnimation() == attackDownAnimation) {
			currentAttackBox = horizontalAttackBox;
		}
		
		if(getCurrentAnimation() == lookLeftAnimation || getCurrentAnimation() == howlLeftAnimation || getCurrentAnimation() == walkLeftAnimation || getCurrentAnimation() == runLeftAnimation || getCurrentAnimation() == attackLeftAnimation ||
				getCurrentAnimation() == lookRightAnimation || getCurrentAnimation() == howlRightAnimation || getCurrentAnimation() == walkRightAnimation || getCurrentAnimation() == runRightAnimation || getCurrentAnimation() == attackRightAnimation) {
			currentAttackBox = verticalAttackBox;
		}
				
	}
	
	private void updateMove() {
		
		if(!isGoingToPlayer() && aggressionCircle.contains(player.getCenterX(), player.getCenterY()) && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
			setGoingToPlayer(true);
			aggressionTimestamp = System.currentTimeMillis();
		}
	
		if(isGoingToPlayer() && System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
			movementSpeed = runMovementSpeed;
			diagonalMovementSpeed = diagonalRunMovementSpeed;
		}
		
		if(isGoingToPlayer()) {
			path = findPath(player.getCenterYTile(), player.getCenterXTile());
		}
		
		if(isGoingToPlayer() && path != null && !path.isEmpty() && getCenterYTile() == path.get(0).getRow() && getCenterXTile() == path.get(0).getCol() && (Math.round(getCenterX())+16) % 32 == 0 && (Math.round(getCenterY())+16) % 32 == 0) {
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
		
		if(isGoingToPlayer() && path != null && !path.isEmpty() && !isAttacking) {
		
			if(goUp && !isUpCollision(movementSpeed)) {		
				setRelativeToMapY(getRelativeToMapY() - movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					setCurrentAnimation(runUpAnimation);
				} else {
					setCurrentAnimation(walkUpAnimation);
				}
				isAttacking = false;
			}
			
			if(goDown && !isDownCollision(movementSpeed)) {	
				setRelativeToMapY(getRelativeToMapY() + movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					setCurrentAnimation(runDownAnimation);
				} else {
					setCurrentAnimation(walkDownAnimation);
				}
				isAttacking = false;
			}
						
			if(goLeft && !isLeftCollision(movementSpeed)) {	
				setRelativeToMapX(getRelativeToMapX() - movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					setCurrentAnimation(runLeftAnimation);
				} else {
					setCurrentAnimation(walkLeftAnimation);
				}
				isAttacking = false;
			}
			
			if(goRight && !isRightCollision(movementSpeed)) {	
				setRelativeToMapX(getRelativeToMapX() + movementSpeed);
				if(System.currentTimeMillis() - aggressionTimestamp > durationBeforeRunning) {
					setCurrentAnimation(runRightAnimation);
				} else {
					setCurrentAnimation(walkRightAnimation);
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
					setCurrentAnimation(runLeftAnimation);
				} else {
					setCurrentAnimation(walkLeftAnimation);
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
					setCurrentAnimation(runRightAnimation);
				} else {
					setCurrentAnimation(walkRightAnimation);
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
					setCurrentAnimation(runLeftAnimation);
				} else {
					setCurrentAnimation(walkLeftAnimation);
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
					setCurrentAnimation(runRightAnimation);
				} else {
					setCurrentAnimation(walkRightAnimation);
				}
				isAttacking = false;
			}
						
		}
		
		if(path != null && path.isEmpty()) {
			
			if(getCenterXTile() == player.getCenterXTile() && getCenterYTile() - 1 == player.getCenterYTile()) {
				setCurrentAnimation(lookUpAnimation);
			}
			
			if(getCenterXTile() == player.getCenterXTile() && getCenterYTile() + 1 == player.getCenterYTile()) {
				setCurrentAnimation(lookDownAnimation);
			}
			
			if(getCenterXTile() - 1 == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) {
				setCurrentAnimation(lookLeftAnimation);
			}
			
			if(getCenterXTile() + 1 == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) {
				setCurrentAnimation(lookRightAnimation);
			}
			
		}
		
	}
	
	
	private void updateAttackPlayer() {
		
		if(isGoingToPlayer() && player.isAlive()) {
			if(path.isEmpty() || (getCenterXTile() == player.getCenterXTile() && getCenterYTile() == player.getCenterYTile()) || isTouchingPlayer() || currentAttackBox.intersects(player.getHitBox())) {
				
				if((getCurrentAnimation() == lookUpAnimation || getCurrentAnimation() == runUpAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					setCurrentAnimation(attackUpAnimation);
					isAttacking = true;
					damageDealt = false;
				}
				
				if((getCurrentAnimation() == lookDownAnimation || getCurrentAnimation() == runDownAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					setCurrentAnimation(attackDownAnimation);
					isAttacking = true;
					damageDealt = false;
				}
				
				if((getCurrentAnimation() == lookLeftAnimation || getCurrentAnimation() == runLeftAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					setCurrentAnimation(attackLeftAnimation);
					isAttacking = true;
					damageDealt = false;
				}
				
				if((getCurrentAnimation() == lookRightAnimation || getCurrentAnimation() == runRightAnimation) && currentAttackBox.intersects(player.getHitBox())) {
					setCurrentAnimation(attackRightAnimation);
					isAttacking = true;
					damageDealt = false;
				}
				
				if(isAttacking && getCurrentAnimation().isStopped()) {
					getCurrentAnimation().restart();
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
						
			if(getCurrentAnimation() == attackUpAnimation && getCurrentAnimation().getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
				}
			}
			
			if(getCurrentAnimation() == attackDownAnimation && getCurrentAnimation().getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
					}
			}
			
			if(getCurrentAnimation() == attackLeftAnimation && getCurrentAnimation().getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
				}		
			}
			
			if(getCurrentAnimation() == attackRightAnimation && getCurrentAnimation().getFrame() == 1) {
					if(currentAttackBox.intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(damageToDeal);
						damageDealt = true;
					}						
			}		
			
		}
		
		if(!currentAttackBox.intersects(player.getHitBox())) {
			
			if(getCurrentAnimation() == attackUpAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookUpAnimation);
				isAttacking = false;
			}
			
			if(getCurrentAnimation() == attackDownAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookDownAnimation);
				isAttacking = false;
			}
			
			if(getCurrentAnimation() == attackLeftAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookLeftAnimation);
				isAttacking = false;
			}
			
			if(getCurrentAnimation() == attackRightAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookRightAnimation);
				isAttacking = false;
			}
			
		}
		
		if(!player.isAlive()) {
			
			if(getCurrentAnimation() == attackUpAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookUpAnimation);
				isAttacking = false;
			}
			
			if(getCurrentAnimation() == attackDownAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookDownAnimation);
				isAttacking = false;
			}
			
			if(getCurrentAnimation() == attackLeftAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookLeftAnimation);
				isAttacking = false;
			}
			
			if(getCurrentAnimation() == attackRightAnimation) {
				getCurrentAnimation().restart();
				setCurrentAnimation(lookRightAnimation);
				isAttacking = false;
			}
			
		}
						
	}
	
	
	private List<Node> findPath(int targetTileY, int targetTileX) {
		
		Node initialNode = new Node(getCenterYTile(), getCenterXTile());
        Node finalNode = new Node(targetTileY, targetTileX);
        
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
				
				if(getCurrentAnimation() == lookUpAnimation || getCurrentAnimation() == howlUpAnimation || getCurrentAnimation() == walkUpAnimation || getCurrentAnimation() == runUpAnimation || getCurrentAnimation() == attackUpAnimation) {
					setCurrentAnimation(dieUpAnimation);
				}
				
				if(getCurrentAnimation() == lookDownAnimation || getCurrentAnimation() == howlDownAnimation || getCurrentAnimation() == walkDownAnimation || getCurrentAnimation() == runDownAnimation || getCurrentAnimation() == attackDownAnimation) {
					setCurrentAnimation(dieDownAnimation);
				}
				
				if(getCurrentAnimation() == lookLeftAnimation || getCurrentAnimation() == howlLeftAnimation || getCurrentAnimation() == walkLeftAnimation || getCurrentAnimation() == runLeftAnimation || getCurrentAnimation() == attackLeftAnimation) {
					setCurrentAnimation(dieLeftAnimation);
				}
				
				if(getCurrentAnimation() == lookRightAnimation || getCurrentAnimation() == howlRightAnimation || getCurrentAnimation() == walkRightAnimation || getCurrentAnimation() == runRightAnimation || getCurrentAnimation() == attackRightAnimation) {
					setCurrentAnimation(dieRightAnimation);
				}
				
				isAlive = false;
				setBloodtheft(false);
				setBloodtheftCounter(0);
				
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
	
	private void checkIfIceblockIsOver() {

		if(System.currentTimeMillis() - getIceblockedTimestamp() > 10000) {
			setIceblocked(false);
			getCurrentAnimation().start();
		}
		
	}
		
	private void checkIfBloodtheft() {

		if(isBloodtheft() && getBloodtheftCounter() < 10 && System.currentTimeMillis() - getBloodtheftTimestamp() >= 1000) {
			decreaseHealth(15);
			player.getHealthBar().setCurrentValue(player.getHealthBar().getCurrentValue() + 15);
			
			setBloodtheftTimestamp(System.currentTimeMillis());
			setBloodtheftCounter(getBloodtheftCounter() + 1);
			
			drawBlood = true;

		}
		
		if(isBloodtheft() && getBloodtheftCounter() >= 10) {
			setBloodtheft(false);
			setBloodtheftCounter(0);
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}