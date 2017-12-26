package main;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import util.CollisionBox;

public class Player {
	
	private final int playerSize = 64;
	
	private final int playerAttackSize = 192;

	private int relativeToScreenX = Main.WIDTH / 2 - playerSize / 2;
	private int relativeToScreenY = Main.HEIGHT / 2 - playerSize / 2;
	
	private int relativeToScreenAttackX = Main.WIDTH / 2 - playerAttackSize / 2;
	private int relativeToScreenAttackY = Main.HEIGHT / 2 - playerAttackSize / 2;
					
	private float relativeToMapX = relativeToScreenX + playerSize / 4 - Game.getCurrentMap().getX();
	private float relativeToMapY = relativeToScreenY + playerSize / 2 - Game.getCurrentMap().getY(); 
		
	private CollisionBox collisionBox = new CollisionBox(relativeToMapX + 6, relativeToMapY + 16, playerSize/2 - 12, playerSize/2 - 18);
	
	private CollisionBox collisionShowBox = new CollisionBox(relativeToMapX + 6, relativeToMapY + 16, playerSize/2 - 12, playerSize/2 - 18);

	private float playerSpeed = 1.5f;
	
	private float diagonalPlayerSpeed = (float) (1/Math.sqrt(Math.pow(playerSpeed, 2) + Math.pow(playerSpeed, 2))) * playerSpeed * playerSpeed;
	
	private SpriteSheet spriteSheet = new SpriteSheet("resources/HumanSpriteSheet.png", 64, 64);
	private SpriteSheet overSizeWeaponSpriteSheet = new SpriteSheet("resources/HumanSpriteSheet.png", 192, 192);
	
	private Animation lookUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, true);
	private Animation lookDownAnimation = new Animation(spriteSheet, 0, 10, 0, 10, true, 100, true);
	private Animation lookLeftAnimation = new Animation(spriteSheet, 0, 9, 0, 9, true, 100, true);
	private Animation lookRightAnimation = new Animation(spriteSheet, 0, 11, 0, 11, true, 100, true);
	
	private Animation goUpAnimation = new Animation(spriteSheet, 1, 8, 8, 8, true, 100, true);
	private Animation goDownAnimation = new Animation(spriteSheet, 1, 10, 8, 10, true, 100, true);
	private Animation goLeftAnimation = new Animation(spriteSheet, 1, 9, 8, 9, true, 100, true);
	private Animation goRightAnimation = new Animation(spriteSheet, 1, 11, 8, 11, true, 100, true);
	
	private Animation attackUpAnimation = new Animation(overSizeWeaponSpriteSheet, 0, 7, 5, 7, true, 100, true);
	private Animation attackDownAnimation = new Animation(overSizeWeaponSpriteSheet, 0, 9, 5, 9, true, 100, true);
	private Animation attackLeftAnimation = new Animation(overSizeWeaponSpriteSheet, 0, 8, 5, 8, true, 100, true);
	private Animation attackRightAnimation = new Animation(overSizeWeaponSpriteSheet, 0, 10, 5, 10, true, 100, true);

	private Animation currentAnimation = lookUpAnimation;
		
	private boolean lookUp = false;
	private boolean lookDown = false;
	private boolean lookLeft = false;
	private boolean lookRight = false;
	
	private Input input = Main.appGameContainer.getInput();
	
	private boolean isAttacking = false;
	
	private int notWalkableLayerIndex;
	private TiledMap tiledMap;
	private ArrayList<NPC> npcList;
	
	public Player() throws SlickException {

		attackUpAnimation.setLooping(false);
		attackDownAnimation.setLooping(false);
		attackLeftAnimation.setLooping(false);
		attackRightAnimation.setLooping(false);
		
		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();
		npcList = Game.getNpcList();
		
	}
	
	public void update() {
		
		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();
		npcList = Game.getNpcList();
		
		move();
		attack();
		
	}
	
	public void render(Graphics g) {
		
		if(isAttacking) {
		
			currentAnimation.draw(relativeToScreenAttackX, relativeToScreenAttackY);
			
		} else {
			
			currentAnimation.draw(relativeToScreenX, relativeToScreenY);
			
		}
		
		g.draw(collisionShowBox);

	}
	
	private void attack() {
		
		if(input.isKeyDown(Input.KEY_X)) {
						
			if(currentAnimation == lookUpAnimation || currentAnimation == goUpAnimation) {
				currentAnimation = attackUpAnimation;
				attackUpAnimation.start();
				isAttacking = true;
			}
			
			if(currentAnimation == lookDownAnimation || currentAnimation == goDownAnimation) {
				currentAnimation = attackDownAnimation;
				attackDownAnimation.start();
				isAttacking = true;
			}
			
			if(currentAnimation == lookLeftAnimation || currentAnimation == goLeftAnimation) {
				currentAnimation = attackLeftAnimation;
				attackLeftAnimation.start();
				isAttacking = true;
			}
			
			if(currentAnimation == lookRightAnimation || currentAnimation == goRightAnimation) {
				currentAnimation = attackRightAnimation;
				attackRightAnimation.start();
				isAttacking = true;
			}
			
		}
		
		if(isAttacking && attackUpAnimation.isStopped()) {
			attackUpAnimation.restart();
			currentAnimation = lookUpAnimation;
			isAttacking = false;
		}
		
		if(isAttacking && attackDownAnimation.isStopped()) {
			attackDownAnimation.restart();
			currentAnimation = lookDownAnimation;
			isAttacking = false;
		}
		
		if(isAttacking && attackLeftAnimation.isStopped()) {
			attackLeftAnimation.restart();
			currentAnimation = lookLeftAnimation;
			isAttacking = false;
		}
		
		if(isAttacking && attackRightAnimation.isStopped()) {
			attackRightAnimation.restart();
			currentAnimation = lookRightAnimation;
			isAttacking = false;
		}
		
	}

	private void move() {
		
		relativeToMapX = (relativeToScreenX + playerSize / 4) - Game.getCurrentMap().getX();
		relativeToMapY = (relativeToScreenY + playerSize / 2) - Game.getCurrentMap().getY(); 
												
		collisionBox.setX(relativeToMapX + 6);
		collisionBox.setY(relativeToMapY + 16);
		
		if(!isAttacking) {
						
			if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
				
				if(isUpCollision()) {
						
					currentAnimation = lookUpAnimation;
					
				} else {
					
					Game.getCurrentMap().setY(Game.getCurrentMap().getY() + playerSpeed);
					currentAnimation = goUpAnimation;
				
				}
				
				lookUp = true;
				lookDown = false;
				lookLeft = false;
				lookRight = false;
				
	
			} else if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
					
				
				if(isDownCollision()) {
					
					currentAnimation = lookDownAnimation;
					
				} else {
				
					Game.getCurrentMap().setY(Game.getCurrentMap().getY() - playerSpeed);
					currentAnimation = goDownAnimation;
								
				}
				
				lookUp = false;
				lookDown = true;
				lookLeft = false;
				lookRight = false;
				
			} else if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {
				
				if(isLeftCollision()) {
					
					currentAnimation = lookLeftAnimation;
					
				} else {
				
					Game.getCurrentMap().setX(Game.getCurrentMap().getX() + playerSpeed);
					currentAnimation = goLeftAnimation;
	
				}
						
				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;
				
			} else if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT)) {
			
				if(isRightCollision()) {
					
					currentAnimation = lookRightAnimation;
					
				} else {
				
					Game.getCurrentMap().setX(Game.getCurrentMap().getX() - playerSpeed);
					currentAnimation = goRightAnimation;
					
				}
				
				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;
				
			} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {
				
				if(!isUpCollision()) {
					Game.getCurrentMap().setY(Game.getCurrentMap().getY() + diagonalPlayerSpeed);
				}
				
				if(!isLeftCollision()) {
					Game.getCurrentMap().setX(Game.getCurrentMap().getX() + diagonalPlayerSpeed);
				}
				
				if(!isUpCollision() || !isLeftCollision()) {
					currentAnimation = goLeftAnimation;
				} else {
					currentAnimation = lookLeftAnimation;
				}
		
				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;
				
			} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT)) {
				
				if(!isUpCollision()) {
					Game.getCurrentMap().setY(Game.getCurrentMap().getY() + diagonalPlayerSpeed);
				}
				
				if(!isRightCollision()) {
					Game.getCurrentMap().setX(Game.getCurrentMap().getX() - diagonalPlayerSpeed);
				}
				
				if(!isUpCollision() || !isRightCollision()) {
				
					currentAnimation = goRightAnimation;
					
				} else {
					
					currentAnimation = lookRightAnimation;
					
				}
				
				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;
				
			} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_RIGHT)) {
				
				
				if(!isDownCollision()) {
					Game.getCurrentMap().setY(Game.getCurrentMap().getY() - diagonalPlayerSpeed);
				} 
				
				if(!isLeftCollision()) {
					Game.getCurrentMap().setX(Game.getCurrentMap().getX() + diagonalPlayerSpeed);
				}
				
				if(!isDownCollision() || !isLeftCollision()) {
				
					currentAnimation = goLeftAnimation;
	
				} else {
					
					currentAnimation = lookLeftAnimation;
					
				}
				
				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;
	
			} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT)) {
					
				if(!isDownCollision()) {
					Game.getCurrentMap().setY(Game.getCurrentMap().getY() - diagonalPlayerSpeed);
				} 
				
				if(!isRightCollision()) {
					Game.getCurrentMap().setX(Game.getCurrentMap().getX() - diagonalPlayerSpeed);
				}
				
				if(!isDownCollision() || !isRightCollision()) {
				
					currentAnimation = goRightAnimation;
				
				} else {
				
					currentAnimation = lookRightAnimation;
				
				}
				
				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;
		
			} else {
				
				if(lookUp) {
					currentAnimation = lookUpAnimation;
				}
				
				if(lookDown) {
					currentAnimation = lookDownAnimation;	
				}
				
				if(lookLeft) {
					currentAnimation = lookLeftAnimation;
				}
				
				if(lookRight) {
					currentAnimation = lookRightAnimation;
				}
	
				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = false;
				
			}
		
		}
							
	}
		
	private boolean isUpCollision() {
		
		for(NPC npc : npcList) {
			
			if(collisionBox.intersects(npc.getCollisionBox())) {
				Game.getCurrentMap().setY(Game.getCurrentMap().getY() - playerSpeed);
				return true;
			}
			
		}
				
		if(tiledMap.getTileId((int) collisionBox.getTopLeftX()/Main.TILE_SIZE, (int) (collisionBox.getTopLeftY() - playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) collisionBox.getTopRightX()/Main.TILE_SIZE, (int) (collisionBox.getTopRightY() - playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	private boolean isDownCollision() {
		
		for(NPC npc : npcList) {
			
			if(collisionBox.intersects(npc.getCollisionBox())) {
				Game.getCurrentMap().setY(Game.getCurrentMap().getY() + playerSpeed);
				return true;
			}
			
		}
			
				
		if(tiledMap.getTileId((int) collisionBox.getBottomLeftX()/Main.TILE_SIZE, (int) (collisionBox.getBottomLeftY() + playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) collisionBox.getBottomRightX()/Main.TILE_SIZE, (int) (collisionBox.getBottomRightY() + playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
		
	}
	
	private boolean isLeftCollision() {
		
		for(NPC npc : npcList) {
			
			if(collisionBox.intersects(npc.getCollisionBox())) {
				Game.getCurrentMap().setX(Game.getCurrentMap().getX() - playerSpeed);
				return true;
			}
			
		}
					
		if(tiledMap.getTileId((int) (collisionBox.getTopLeftX() - playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getTopLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (collisionBox.getBottomLeftX() - playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getBottomLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			
			return false;
			
		} else {
			
			return true;
			
		}
		
		
	}
	
	private boolean isRightCollision() {
		
		for(NPC npc : npcList) {
			
			if(collisionBox.intersects(npc.getCollisionBox())) {
				Game.getCurrentMap().setX(Game.getCurrentMap().getX() + playerSpeed);
				return true;
			}
			
		}
			
		if(tiledMap.getTileId((int) (collisionBox.getTopRightX() + playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getTopRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (collisionBox.getBottomRightX() + playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getBottomRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}