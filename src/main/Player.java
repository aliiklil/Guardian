package main;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import util.CollisionBox;

public class Player {
	
	private final int spriteSize = 64;
	private final int attackSpriteSize = 192;
	
	private final float relativeToScreenX = Main.WIDTH / 2 - spriteSize / 2;
	private final float relativeToScreenY = Main.HEIGHT / 2 - spriteSize / 2;
	
	private final float relativeToScreenAttackX = Main.WIDTH / 2 - attackSpriteSize / 2;
	private final float relativeToScreenAttackY = Main.HEIGHT / 2 - attackSpriteSize / 2;
	
	private float relativeToMapX = 256;
	private float relativeToMapY = 256;
				
	private CollisionBox collisionBox = new CollisionBox(relativeToMapX + 6, relativeToMapY + 16, spriteSize/2 - 12, spriteSize/2 - 18);
	
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
	
	private CollisionBox attackUpCollisionBox = new CollisionBox(relativeToMapX - 28, relativeToMapY - 37, 89, 38);
	private CollisionBox attackDownCollisionBox =  new CollisionBox(relativeToMapX - 28, relativeToMapY + 12, 89, 38);
	private CollisionBox attackLeftCollisionBox = new CollisionBox(relativeToMapX - 67, relativeToMapY - 16, 68, 36);
	private CollisionBox attackRightCollisionBox =  new CollisionBox(relativeToMapX + 31, relativeToMapY - 16, 68, 36);
	
	private boolean damageDealt = false;
	
	private final int maxHealth = 200;
	private int currentHealth = 200;
	
	private HealthBar healthBar = new HealthBar(20, Main.HEIGHT - 40, 350, 25, 5, 200);
	
	private Animation shootUpAnimation = new Animation(spriteSheet, 0, 16, 11, 16, true, 100, true);
	private Animation shootDownAnimation = new Animation(spriteSheet, 0, 18, 11, 18, true, 100, true);
	private Animation shootLeftAnimation = new Animation(spriteSheet, 0, 17, 11, 17, true, 100, true);
	private Animation shootRightAnimation = new Animation(spriteSheet, 0, 19, 11, 19, true, 100, true);
	
	private boolean isShooting = false;
	
	public Player() throws SlickException {
				
		Game.getCurrentMap().setX(relativeToScreenX - relativeToMapX + spriteSize / 4);
		Game.getCurrentMap().setY(relativeToScreenY - relativeToMapY + spriteSize / 2);
		
		attackUpAnimation.setLooping(false);
		attackDownAnimation.setLooping(false);
		attackLeftAnimation.setLooping(false);
		attackRightAnimation.setLooping(false);
		
		shootUpAnimation.setLooping(false);
		shootDownAnimation.setLooping(false);
		shootLeftAnimation.setLooping(false);
		shootRightAnimation.setLooping(false);
		
		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();
		npcList = Game.getNpcList();
		
	}
	
	public void update() {
		
		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();
		npcList = Game.getNpcList();
		
		relativeToMapX = (relativeToScreenX + spriteSize / 4) - Game.getCurrentMap().getX();
		relativeToMapY = (relativeToScreenY + spriteSize / 2) - Game.getCurrentMap().getY(); 
												
		collisionBox.setX(relativeToMapX + 6);
		collisionBox.setY(relativeToMapY + 16);
		 		
		attackUpCollisionBox.setX(relativeToMapX - 28);
		attackUpCollisionBox.setY(relativeToMapY - 37);
		
		attackDownCollisionBox.setX(relativeToMapX - 28);
		attackDownCollisionBox.setY(relativeToMapY + 12);
		
		attackLeftCollisionBox.setX(relativeToMapX - 67);
		attackLeftCollisionBox.setY(relativeToMapY - 16);
		
		attackRightCollisionBox.setX(relativeToMapX + 31);
		attackRightCollisionBox.setY(relativeToMapY - 16);
		
		move();
		attack();
		shoot();
	
	}
	
	public void render(Graphics g) {
		
		if(isAttacking) {
		
			currentAnimation.draw(relativeToScreenAttackX, relativeToScreenAttackY);
			
		} else {
			
			currentAnimation.draw(relativeToScreenX, relativeToScreenY);
			
		}
		
		g.setColor(Color.white);
		g.drawString("relativeToMapX:  " + relativeToMapX, 50, 50);
		g.drawString("relativeToMapY:  " + relativeToMapY, 50, 100);
		
		healthBar.render(g);
							
	}
	
	private void shoot() {
 		
		
		if(input.isKeyDown(Input.KEY_Y) && !isShooting) {
			
			if(currentAnimation == lookUpAnimation || currentAnimation == goUpAnimation) {
				currentAnimation = shootUpAnimation;
			}
			
			if(currentAnimation == lookDownAnimation || currentAnimation == goDownAnimation) {
				currentAnimation = shootDownAnimation;
			}
			
			if(currentAnimation == lookLeftAnimation || currentAnimation == goLeftAnimation) {
				currentAnimation = shootLeftAnimation;
			}
			
			if(currentAnimation == lookRightAnimation || currentAnimation == goRightAnimation) {
				currentAnimation = shootRightAnimation;
			}
			
			currentAnimation.start();
			isShooting = true;
			damageDealt = false;
			
		}
		
		if(isShooting && shootUpAnimation.isStopped()) {
			shootUpAnimation.restart();
			currentAnimation = lookUpAnimation;
			isShooting = false;
		}
		
		if(isShooting && shootDownAnimation.isStopped()) {
			shootDownAnimation.restart();
			currentAnimation = lookDownAnimation;
			isShooting = false;
		}
		
		if(isShooting && shootLeftAnimation.isStopped()) {
			shootLeftAnimation.restart();
			currentAnimation = lookLeftAnimation;
			isShooting = false;
		}
		
		if(isShooting && shootRightAnimation.isStopped()) {
			shootRightAnimation.restart();
			currentAnimation = lookRightAnimation;
			isShooting = false;
		}
		
	
	}
	
	private void attack() {
		 		
		if(input.isKeyDown(Input.KEY_X) && !isAttacking) {
						
			if(currentAnimation == lookUpAnimation || currentAnimation == goUpAnimation) {
				currentAnimation = attackUpAnimation;
			}
			
			if(currentAnimation == lookDownAnimation || currentAnimation == goDownAnimation) {
				currentAnimation = attackDownAnimation;
			}
			
			if(currentAnimation == lookLeftAnimation || currentAnimation == goLeftAnimation) {
				currentAnimation = attackLeftAnimation;
			}
			
			if(currentAnimation == lookRightAnimation || currentAnimation == goRightAnimation) {
				currentAnimation = attackRightAnimation;
			}
			
			currentAnimation.start();
			isAttacking = true;
			damageDealt = false;
			
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
		
		if(!damageDealt) {
		
			if(currentAnimation == attackUpAnimation && currentAnimation.getFrame() == 3) {
				for(NPC npc : npcList) {
					if(attackUpCollisionBox.intersects(npc.getCollisionBox())) {
						npc.decreaseHealth(10);
						damageDealt = true;
					}
				}
			}
			
			if(currentAnimation == attackDownAnimation && currentAnimation.getFrame() == 3) {
				for(NPC npc : npcList) {
					if(attackDownCollisionBox.intersects(npc.getCollisionBox())) {
						npc.decreaseHealth(10);
						damageDealt = true;
					}
				}
			}
			
			if(currentAnimation == attackLeftAnimation && currentAnimation.getFrame() == 3) {
				for(NPC npc : npcList) {
					if(attackLeftCollisionBox.intersects(npc.getCollisionBox())) {
						npc.decreaseHealth(10);
						damageDealt = true;
					}
				}		
			}
			
			if(currentAnimation == attackRightAnimation && currentAnimation.getFrame() == 3) {
				for(NPC npc : npcList) {
					if(attackRightCollisionBox.intersects(npc.getCollisionBox())) {
						npc.decreaseHealth(10);
						damageDealt = true;
					}				
				}			
			}		
		}
	}

	private void move() {
				
		if(!isAttacking && !isShooting) {
						
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
			
			if(collisionBox.willIntersectUp(npc.getCollisionBox(), playerSpeed) && npc.isAlive()) {
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
			
			if(collisionBox.willIntersectDown(npc.getCollisionBox(), playerSpeed) && npc.isAlive()) {
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
			
			if(collisionBox.willIntersectLeft(npc.getCollisionBox(), playerSpeed) && npc.isAlive()) {
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
			
			if(collisionBox.willIntersectRight(npc.getCollisionBox(), playerSpeed) && npc.isAlive()) {
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
	
	public void decreaseHealth(int amount) {
		
		if(currentHealth > 0) {
			currentHealth = currentHealth - amount;
		}
		
		if(currentHealth < 0) {
			currentHealth = 0;
		}
		
		healthBar.setCurrentHealth(currentHealth);
		
	}
		
}