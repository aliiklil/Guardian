package models;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import dialogue.DialogueWindow;
import main.Game;
import main.Main;
import manager.CharacterManager;
import manager.ChestManager;
import util.CollisionBox;

public class Player extends Character {

	private float screenRelativeX = Main.WIDTH / 2 - super.getSpriteSize() / 2;
	private float screenRelativeY = Main.HEIGHT / 2 - super.getSpriteSize() / 2;

	private boolean lookUp = false;
	private boolean lookDown = false;
	private boolean lookLeft = false;
	private boolean lookRight = false;

	private Input input = Main.appGameContainer.getInput();

	private ArrayList<NPC> npcList;

	private boolean damageDealt = false;

	private boolean isAttacking = false;
	private boolean isPreparingAttack = false;
	private boolean isPreparingShot = false;
	private boolean isPreparingSpell = false;

	private boolean arrowCreated = false;
	private boolean spellCreated = false;

	private Inventory inventory = new Inventory();

	private Bar prepareAttackBar;
	private Bar prepareShotBar;
	private Bar prepareSpellBar;

	private int damageToDeal = 0;
	
	private NewItemWindow newItemWindow = new NewItemWindow();
	
	private DialogueWindow dialogueWindow = new DialogueWindow();
	
	private boolean yPressed = false;
		
	public Player() throws SlickException {

		super(224, 64, "resources/HumanSpriteSheet.png");

		super.setCollisionBox(new CollisionBox(super.getRelativeToMapX() + 6, super.getRelativeToMapY() + 10, super.getSpriteSize() / 2 - 12, super.getSpriteSize() / 2 - 12));
		super.setHitBox(new CollisionBox(super.getRelativeToMapX(), super.getRelativeToMapY() - 10, super.getSpriteSize() / 2, super.getSpriteSize() / 2));

		super.setHealthBar(new Bar(20, Main.HEIGHT - 40, 350, 25, 5, 200, 200, Color.red));

		prepareAttackBar = new Bar(getRelativeToMapX() + Game.getCurrentMap().getX() - 16, getRelativeToMapY() + Game.getCurrentMap().getY() - 32, 64, 5, 1, 0, 100, Color.cyan);
		prepareShotBar = new Bar(getRelativeToMapX()  + Game.getCurrentMap().getX() - 16, getRelativeToMapY() + Game.getCurrentMap().getY() - 32, 64, 5, 1, 0, 100, Color.cyan);
		prepareSpellBar = new Bar(getRelativeToMapX()  + Game.getCurrentMap().getX() - 16, getRelativeToMapY() + Game.getCurrentMap().getY() - 32, 64, 5, 1, 0, 100, Color.cyan);

		Game.getCurrentMap().setX(screenRelativeX - super.getRelativeToMapX() + super.getSpriteSize() / 4);
		Game.getCurrentMap().setY(screenRelativeY - super.getRelativeToMapY() + super.getSpriteSize() / 2);

		npcList = CharacterManager.getNpcList();

		setAttackUpCollisionBox(new CollisionBox(getRelativeToMapX() - 28, getRelativeToMapY() - 37, 89, 38));
		setAttackDownCollisionBox(new CollisionBox(getRelativeToMapX() - 28, getRelativeToMapY() + 12, 89, 38));
		setAttackLeftCollisionBox(new CollisionBox(getRelativeToMapX() - 67, getRelativeToMapY() - 16, 68, 36));
		setAttackRightCollisionBox(new CollisionBox(getRelativeToMapX() + 31, getRelativeToMapY() - 16, 68, 36));
		
	}

	public void update() throws SlickException {
		
		super.update();
		
		prepareAttackBar.setX(getRelativeToMapX() + Game.getCurrentMap().getX() - 16);
		prepareAttackBar.setY(getRelativeToMapY() + Game.getCurrentMap().getY() - 32);
		
		prepareShotBar.setX(getRelativeToMapX() + Game.getCurrentMap().getX() - 16);
		prepareShotBar.setY(getRelativeToMapY() + Game.getCurrentMap().getY() - 32);
		
		prepareSpellBar.setX(getRelativeToMapX() + Game.getCurrentMap().getX() - 16);
		prepareSpellBar.setY(getRelativeToMapY() + Game.getCurrentMap().getY() - 32);

		npcList = CharacterManager.getNpcList();

		super.setRelativeToMapX((screenRelativeX + super.getSpriteSize() / 4) - Game.getCurrentMap().getX());
		super.setRelativeToMapY((screenRelativeY + super.getSpriteSize() / 2) - Game.getCurrentMap().getY());

		super.getCollisionBox().setX(super.getRelativeToMapX() + 6);
		super.getCollisionBox().setY(super.getRelativeToMapY() + 10);

		super.getHitBox().setX(super.getRelativeToMapX());
		super.getHitBox().setY(super.getRelativeToMapY() - 10);

		super.getAttackUpCollisionBox().setX(super.getRelativeToMapX() - 28);
		super.getAttackUpCollisionBox().setY(super.getRelativeToMapY() - 37);

		super.getAttackDownCollisionBox().setX(super.getRelativeToMapX() - 28);
		super.getAttackDownCollisionBox().setY(super.getRelativeToMapY() + 12);

		super.getAttackLeftCollisionBox().setX(super.getRelativeToMapX() - 67);
		super.getAttackLeftCollisionBox().setY(super.getRelativeToMapY() - 16);

		super.getAttackRightCollisionBox().setX(super.getRelativeToMapX() + 31);
		super.getAttackRightCollisionBox().setY(super.getRelativeToMapY() - 16);

		if(input.isKeyPressed(input.KEY_Y)) {
			yPressed = true;
		}
		
		if(isAlive()) {
			inventory.update();
			newItemWindow.update();
			updateDialogue();
			dialogueWindow.update();
			
			if(!dialogueWindow.isActive()) {
				updateMove();
				updateAttack();
				updateShoot();
				updateSpell();
				updatePickUpItem();
				updateOpenChest();	
				
			}
		}
		
		yPressed = false;

		input.clearKeyPressedRecord();
	}

	public void render(Graphics g) {

		if((isAttacking || isPreparingAttack) && isAlive()) {

			super.getCurrentAnimation().draw(screenRelativeX - 64, screenRelativeY - 64);

		} else {

			super.getCurrentAnimation().draw(screenRelativeX, screenRelativeY);

		}

		if(super.isDrawBlood()) {
			super.drawBlood(screenRelativeX, screenRelativeY);
		}

		if(isPreparingAttack && isAlive() && prepareAttackBar.getCurrentValue() > 10) {
			prepareAttackBar.render(g);
		}

		if(isPreparingShot && super.getCurrentAnimation().getFrame() == 8 && isAlive() && prepareShotBar.getCurrentValue() > 10) {
			prepareShotBar.render(g);
		}

		if(isPreparingSpell && super.getCurrentAnimation().getFrame() == 6 && isAlive() && prepareSpellBar.getCurrentValue() > 10) {
			prepareSpellBar.render(g);
		}
		
	}

	private void updateMove() {

		if(!isAttacking && !isPreparingAttack && !isPreparingShot && !isPreparingSpell ) {

			if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) && !inventory.isInventoryOpen()) {

				if(isUpCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getLookUpAnimation());

				} else {

					if(screenRelativeY > 440) {
						screenRelativeY = screenRelativeY - getMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() + getMovementSpeed());
					}
					
					super.setCurrentAnimation(super.getGoUpAnimation());

				}

				lookUp = true;
				lookDown = false;
				lookLeft = false;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) && !inventory.isInventoryOpen()) {
				if(isDownCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getLookDownAnimation());

				} else {

					if(screenRelativeY < 576) {
						screenRelativeY = screenRelativeY + getMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() - getMovementSpeed());
					}
					
					super.setCurrentAnimation(super.getGoDownAnimation());

				}

				lookUp = false;
				lookDown = true;
				lookLeft = false;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) && !inventory.isInventoryOpen()) {

				if(isLeftCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getLookLeftAnimation());

				} else {

					if(screenRelativeX > 860) {
						screenRelativeX = screenRelativeX - getMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() + getMovementSpeed());
					}
					
					super.setCurrentAnimation(super.getGoLeftAnimation());

				}

				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !inventory.isInventoryOpen()) {

				if(isRightCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getLookRightAnimation());

				} else {

					if(screenRelativeX < 996) {
						screenRelativeX = screenRelativeX + getMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() - getMovementSpeed());
					}
					
					super.setCurrentAnimation(super.getGoRightAnimation());

				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;

			} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) && !inventory.isInventoryOpen()) {

				if(!isUpCollision(super.getMovementSpeed())) {
					
					if(screenRelativeY > 440) {
						screenRelativeY = screenRelativeY - getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() + getDiagonalMovementSpeed());
					}

				}

				if(!isLeftCollision(super.getMovementSpeed())) {
					
					if(screenRelativeX > 860) {
						screenRelativeX = screenRelativeX - getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() + getDiagonalMovementSpeed());
					}

				}

				if(!isUpCollision(super.getMovementSpeed()) || !isLeftCollision(super.getMovementSpeed())) {
					super.setCurrentAnimation(super.getGoLeftAnimation());
				} else {
					super.setCurrentAnimation(super.getLookLeftAnimation());
				}

				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !inventory.isInventoryOpen()) {

				if(!isUpCollision(super.getMovementSpeed())) {
					if(screenRelativeY > 440) {
						screenRelativeY = screenRelativeY - getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() + getDiagonalMovementSpeed());
					}
				}

				if(!isRightCollision(super.getMovementSpeed())) {
					if(screenRelativeX < 996) {
						screenRelativeX = screenRelativeX + getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() - getDiagonalMovementSpeed());
					}
				}

				if(!isUpCollision(super.getMovementSpeed()) || !isRightCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getGoRightAnimation());

				} else {

					super.setCurrentAnimation(super.getLookRightAnimation());

				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;

			} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_RIGHT) && !inventory.isInventoryOpen()) {

				if(!isDownCollision(super.getMovementSpeed())) {
					if(screenRelativeY < 576) {
						screenRelativeY = screenRelativeY + getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() - getDiagonalMovementSpeed());
					}
				}

				if(!isLeftCollision(super.getMovementSpeed())) {
					if(screenRelativeX > 860) {
						screenRelativeX = screenRelativeX - getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() + getDiagonalMovementSpeed());
					}
				}

				if(!isDownCollision(super.getMovementSpeed()) || !isLeftCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getGoLeftAnimation());

				} else {

					super.setCurrentAnimation(super.getLookLeftAnimation());

				}

				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !inventory.isInventoryOpen()) {

				if(!isDownCollision(super.getMovementSpeed())) {
					if(screenRelativeY < 576) {
						screenRelativeY = screenRelativeY + getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() - getDiagonalMovementSpeed());
					}
				}

				if(!isRightCollision(super.getMovementSpeed())) {
					if(screenRelativeX < 996) {
						screenRelativeX = screenRelativeX + getDiagonalMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() - getDiagonalMovementSpeed());
					}
				}

				if(!isDownCollision(super.getMovementSpeed()) || !isRightCollision(super.getMovementSpeed())) {

					super.setCurrentAnimation(super.getGoRightAnimation());

				} else {

					super.setCurrentAnimation(super.getLookRightAnimation());

				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;

			} else {

				if(lookUp) {
					super.setCurrentAnimation(super.getLookUpAnimation());
				}

				if(lookDown) {
					super.setCurrentAnimation(super.getLookDownAnimation());
				}

				if(lookLeft) {
					super.setCurrentAnimation(super.getLookLeftAnimation());
				}

				if(lookRight) {
					super.setCurrentAnimation(super.getLookRightAnimation());
				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = false;

			}

		}

	}

	private void updateAttack() {
 		
		if(input.isKeyDown(Input.KEY_X) && !isAttacking && !isPreparingShot && !isPreparingSpell && !inventory.isInventoryOpen()) {
						
			if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation() || input.isKeyDown(Input.KEY_UP)) {
				super.setCurrentAnimation(super.getPrepareAttackUpAnimation());
			}
			
			if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation() || input.isKeyDown(Input.KEY_DOWN)) {
				super.setCurrentAnimation(super.getPrepareAttackDownAnimation());
			}
			
			if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation() || input.isKeyDown(Input.KEY_LEFT)) {
				super.setCurrentAnimation(super.getPrepareAttackLeftAnimation());
			}
			
			if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation() || input.isKeyDown(Input.KEY_RIGHT)) {
				super.setCurrentAnimation(super.getPrepareAttackRightAnimation());
			}
			
			super.getCurrentAnimation().start();
			isPreparingAttack = true;
			
		}
		
		if(isPreparingAttack && prepareAttackBar.getCurrentValue() < prepareAttackBar.getMaxValue() && isAlive()) {
			prepareAttackBar.setCurrentValue(prepareAttackBar.getCurrentValue() + 1);
		}
		
		if(!input.isKeyDown(Input.KEY_X) && isPreparingAttack) {
					
			if(super.getCurrentAnimation() == super.getPrepareAttackUpAnimation()) {
				super.setCurrentAnimation(super.getAttackUpAnimation());
				isPreparingAttack = false;
			}
			
			if(super.getCurrentAnimation() == super.getPrepareAttackDownAnimation()) {
				super.setCurrentAnimation(super.getAttackDownAnimation());
				isPreparingAttack = false;
			}
			
			if(super.getCurrentAnimation() == super.getPrepareAttackLeftAnimation()) {
				super.setCurrentAnimation(super.getAttackLeftAnimation());
				isPreparingAttack = false;
			}
			
			if(super.getCurrentAnimation() == super.getPrepareAttackRightAnimation()) {
				super.setCurrentAnimation(super.getAttackRightAnimation());
				isPreparingAttack = false;
			}
			
			super.getCurrentAnimation().start();
			damageToDeal = 20 + prepareAttackBar.getCurrentValue();
			isAttacking = true;
			damageDealt = false;
			prepareAttackBar.setCurrentValue(0);
		
		}
		
		if(isAttacking && super.getAttackUpAnimation().isStopped()) {
			super.getAttackUpAnimation().restart();
			super.setCurrentAnimation(super.getLookUpAnimation());
			isAttacking = false;
		}
		
		if(isAttacking && super.getAttackDownAnimation().isStopped()) {
			super.getAttackDownAnimation().restart();
			super.setCurrentAnimation(super.getLookDownAnimation());
			isAttacking = false;
		}
		
		if(isAttacking && super.getAttackLeftAnimation().isStopped()) {
			super.getAttackLeftAnimation().restart();
			super.setCurrentAnimation(super.getLookLeftAnimation());
			isAttacking = false;
		}
		
		if(isAttacking && super.getAttackRightAnimation().isStopped()) {
			super.getAttackRightAnimation().restart();
			super.setCurrentAnimation(super.getLookRightAnimation());
			isAttacking = false;
		}
		
		if(!damageDealt) {
		
			if(super.getCurrentAnimation() == super.getAttackUpAnimation() && super.getCurrentAnimation().getFrame() == 3) {
				for(NPC npc : npcList) {
					if(super.getAttackUpCollisionBox().intersects(npc.getHitBox()) && npc.isAlive()) {
						npc.decreaseHealth(damageToDeal);
						damageDealt = true;

						if(!npc.isUpCollision(Main.TILE_SIZE * 3) && !npc.isUpCollision(Main.TILE_SIZE * 2) && !npc.isUpCollision(Main.TILE_SIZE * 1)) {

							npc.setRelativeToMapY(npc.getRelativeToMapY() - Main.TILE_SIZE * 3);
							
						} else if(!npc.isUpCollision(Main.TILE_SIZE * 2) && !npc.isUpCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapY(npc.getRelativeToMapY() - Main.TILE_SIZE * 2);
							
						} else if(!npc.isUpCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapY(npc.getRelativeToMapY() - Main.TILE_SIZE * 1);
							
						}
					
						prepareAttackBar.setCurrentValue(0);
					}
				}
			}
			
			if(super.getCurrentAnimation() == super.getAttackDownAnimation() && super.getCurrentAnimation().getFrame() == 3) {
				for(NPC npc : npcList) {
					if(super.getAttackDownCollisionBox().intersects(npc.getHitBox()) && npc.isAlive()) {
						npc.decreaseHealth(damageToDeal);
						damageDealt = true;

						if(!npc.isDownCollision(Main.TILE_SIZE * 3) && !npc.isDownCollision(Main.TILE_SIZE * 2) && !npc.isDownCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapY(npc.getRelativeToMapY() + Main.TILE_SIZE * 3);
							
						} else if(!npc.isDownCollision(Main.TILE_SIZE * 2) && !npc.isDownCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapY(npc.getRelativeToMapY() + Main.TILE_SIZE * 2);
							
						} else if(!npc.isDownCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapY(npc.getRelativeToMapY() + Main.TILE_SIZE * 1);
							
						}
						
						prepareAttackBar.setCurrentValue(0);
					}
				}
			}
			
			if(super.getCurrentAnimation() == super.getAttackLeftAnimation() && super.getCurrentAnimation().getFrame() == 3) {
				for(NPC npc : npcList) {
					if(super.getAttackLeftCollisionBox().intersects(npc.getHitBox()) && npc.isAlive()) {
						npc.decreaseHealth(damageToDeal);
						damageDealt = true;

						if(!npc.isLeftCollision(Main.TILE_SIZE * 3) && !npc.isLeftCollision(Main.TILE_SIZE * 2) && !npc.isLeftCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapX(npc.getRelativeToMapX() - Main.TILE_SIZE * 3);
							
						} else if(!npc.isLeftCollision(Main.TILE_SIZE * 2) && !npc.isLeftCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapX(npc.getRelativeToMapX() - Main.TILE_SIZE * 2);
							
						} else if(!npc.isLeftCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapX(npc.getRelativeToMapX() - Main.TILE_SIZE * 1);
							
						}
					
						prepareAttackBar.setCurrentValue(0);
					}
				}		
			}
			
			if(super.getCurrentAnimation() == super.getAttackRightAnimation() && super.getCurrentAnimation().getFrame() == 3) {
				for(NPC npc : npcList) {
					if(super.getAttackRightCollisionBox().intersects(npc.getHitBox()) && npc.isAlive()) {
						npc.decreaseHealth(damageToDeal);
						damageDealt = true;
					
						if(!npc.isRightCollision(Main.TILE_SIZE * 3) && !npc.isRightCollision(Main.TILE_SIZE * 2) && !npc.isRightCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapX(npc.getRelativeToMapX() + Main.TILE_SIZE * 3);
							
						} else if(!npc.isRightCollision(Main.TILE_SIZE * 2) && !npc.isRightCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapX(npc.getRelativeToMapX() + Main.TILE_SIZE * 2);
							
						} else if(!npc.isRightCollision(Main.TILE_SIZE * 1)) {
							
							npc.setRelativeToMapX(npc.getRelativeToMapX() + Main.TILE_SIZE * 1);
							
						}
						
						prepareAttackBar.setCurrentValue(0);
					}				
				}			
			}		
		}
	}

	private void updateShoot() throws SlickException {

		if(input.isKeyDown(Input.KEY_A) && !isAttacking && !isPreparingAttack && !isPreparingSpell && !inventory.isInventoryOpen()) {

			if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation() || input.isKeyDown(Input.KEY_UP)) {
				if(isPreparingShot && super.getCurrentAnimation() != super.getShootUpAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getShootUpAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getShootUpAnimation());
				}
				arrowCreated = false;
			}

			if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation() || input.isKeyDown(Input.KEY_DOWN)) {
				if(isPreparingShot && super.getCurrentAnimation() != super.getShootDownAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getShootDownAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getShootDownAnimation());
				}
				arrowCreated = false;
			}

			if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation() || input.isKeyDown(Input.KEY_LEFT)) {
				if(isPreparingShot && super.getCurrentAnimation() != super.getShootLeftAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getShootLeftAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getShootLeftAnimation());
				}
				arrowCreated = false;
			}

			if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation() || input.isKeyDown(Input.KEY_RIGHT)) {
				if(isPreparingShot && super.getCurrentAnimation() != super.getShootRightAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getShootRightAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getShootRightAnimation());
				}
				arrowCreated = false;
			}

			super.getCurrentAnimation().start();
			isPreparingShot = true;

		}

		if(isPreparingShot && prepareShotBar.getCurrentValue() < prepareShotBar.getMaxValue() && super.getCurrentAnimation().getFrame() == 8) {
			prepareShotBar.setCurrentValue(prepareShotBar.getCurrentValue() + 1);
		}

		if(!input.isKeyDown(Input.KEY_A) && isPreparingShot && super.getCurrentAnimation().isStopped() && !arrowCreated) {

			int damageToDeal = 20 + prepareShotBar.getCurrentValue();
			int arrowVelocity = 7 + prepareShotBar.getCurrentValue() / 10;
			prepareShotBar.setCurrentValue(0);

			if(super.getCurrentAnimation() == super.getShootUpAnimation()) {
				super.getShootUpAnimation().restart();
				super.setCurrentAnimation(super.getLookUpAnimation());
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 1, 0, 1, 0, true, 100, true), 0, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getShootDownAnimation()) {
				super.getShootDownAnimation().restart();
				super.setCurrentAnimation(super.getLookDownAnimation());
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 3, 0, 3, 0, true, 100, true), 1, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getShootLeftAnimation()) {
				super.getShootLeftAnimation().restart();
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 0, 0, 0, 0, true, 100, true), 2, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getShootRightAnimation()) {
				super.getShootRightAnimation().restart();
				super.setCurrentAnimation(super.getLookRightAnimation());
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 2, 0, 2, 0, true, 100, true), 3, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

		}

	}

	private void updateSpell() throws SlickException {

		if(input.isKeyDown(Input.KEY_S) && !isAttacking && !isPreparingAttack && !isPreparingShot && !inventory.isInventoryOpen()) {

			if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation() || input.isKeyDown(Input.KEY_UP)) {
				if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellUpAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getSpellUpAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getSpellUpAnimation());
				}
				spellCreated = false;
			}

			if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation() || input.isKeyDown(Input.KEY_DOWN)) {
				if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellDownAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getSpellDownAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getSpellDownAnimation());
				}
				spellCreated = false;
			}

			if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation() || input.isKeyDown(Input.KEY_LEFT)) {
				if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellLeftAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getSpellLeftAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getSpellLeftAnimation());
				}
				spellCreated = false;
			}

			if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation() || input.isKeyDown(Input.KEY_RIGHT)) {
				if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellRightAnimation()) {
					int frameIndex = super.getCurrentAnimation().getFrame();
					super.getCurrentAnimation().restart();
					super.setCurrentAnimation(super.getSpellRightAnimation());
					super.getCurrentAnimation().setCurrentFrame(frameIndex);
				} else {
					super.setCurrentAnimation(super.getSpellRightAnimation());
				}
				spellCreated = false;
			}

			super.getCurrentAnimation().start();
			isPreparingSpell = true;

		}

		if(isPreparingSpell && prepareSpellBar.getCurrentValue() < prepareSpellBar.getMaxValue() && super.getCurrentAnimation().getFrame() == 6) {
			prepareSpellBar.setCurrentValue(prepareSpellBar.getCurrentValue() + 1);
		}

		if(!input.isKeyDown(Input.KEY_S) && isPreparingSpell && super.getCurrentAnimation().isStopped() && !spellCreated) {

			int damageToDeal = 20 + prepareSpellBar.getCurrentValue();
			int spellVelocity = 7 + prepareSpellBar.getCurrentValue() / 10;
			prepareSpellBar.setCurrentValue(0);

			if(super.getCurrentAnimation() == super.getSpellUpAnimation()) {
				super.getSpellUpAnimation().restart();
				super.setCurrentAnimation(super.getLookUpAnimation());
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/fireball1.png", 64, 64), 0, 1, 7, 1, true, 100, true), 0, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);

			}

			if(super.getCurrentAnimation() == super.getSpellDownAnimation()) {
				super.getSpellDownAnimation().restart();
				super.setCurrentAnimation(super.getLookDownAnimation());
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/fireball1.png", 64, 64), 0, 3, 7, 3, true, 100, true), 1, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getSpellLeftAnimation()) {
				super.getSpellLeftAnimation().restart();
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/fireball1.png", 64, 64), 0, 0, 7, 0, true, 100, true), 2, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getSpellRightAnimation()) {
				super.getSpellRightAnimation().restart();
				super.setCurrentAnimation(super.getLookRightAnimation());
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/fireball1.png", 64, 64), 0, 2, 7, 2, true, 100, true), 3, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

		}

	}

	private void updatePickUpItem() throws SlickException {

		ArrayList<Item> itemList = Game.getItemManager().getItemList();

		for (Item item : itemList) {

			if(super.getCollisionBox().intersects(item.getCollisionBox())) {

				Game.getItemManager().removeItem(item);
				inventory.addItem(item);
				newItemWindow.showWindow(item);
				
				if(item.getItemType().getName().equals("Gold")) {
					inventory.incrementGoldCounter();
				}

			}

		}

	}
	
	private void updateOpenChest() throws SlickException {

		if(yPressed && getCurrentAnimation() == getLookUpAnimation() && !inventory.isInventoryOpen()) {
		
			ArrayList<Chest> chestList = ChestManager.getChestList();
			
			for (Chest chest : chestList) {
			
				if(super.getCollisionBox().willIntersectUp(chest.getCollisionBox(), 5) && !chest.isOpened()) {
					chest.getAnimation().start();
					inventory.addItem(chest.getItem());
					chest.setOpened(true);
					newItemWindow.showWindow(chest.getItem());
				}
			
			}
		
		}
		
	}
	
	private void updateDialogue() throws SlickException {

		if(yPressed && !dialogueWindow.isActive()) {

			ArrayList<NPC> npcList = CharacterManager.getNpcList();
			
			for (NPC npc : npcList) {
			
				if(super.getCollisionBox().willIntersectAnyDirection(npc.getCollisionBox(), 5) && !npc.isHostileToPlayer() && npc.getStartingDialogues() != null) {

					if(super.getCollisionBox().willIntersectUp(npc.getCollisionBox(), 5)) {
						setCurrentAnimation(getLookUpAnimation());
						npc.setCurrentAnimation(npc.getLookDownAnimation());
					}
					
					if(super.getCollisionBox().willIntersectDown(npc.getCollisionBox(), 5)) {
						setCurrentAnimation(getLookDownAnimation());
						npc.setCurrentAnimation(npc.getLookUpAnimation());
					}
					
					if(super.getCollisionBox().willIntersectLeft(npc.getCollisionBox(), 5)) {
						setCurrentAnimation(getLookLeftAnimation());
						npc.setCurrentAnimation(npc.getLookRightAnimation());
					}
					
					if(super.getCollisionBox().willIntersectRight(npc.getCollisionBox(), 5)) {
						setCurrentAnimation(getLookRightAnimation());
						npc.setCurrentAnimation(npc.getLookLeftAnimation());
					}
					
					dialogueWindow.showWindow(npc.getStartingDialogues());
					yPressed = false;
				}
			
			}
		
		}
		
	}

	private boolean isUpCollision(float distance) {

		for (NPC npc : npcList) {

			if(super.getCollisionBox().willIntersectUp(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}

		}

		if(Game.getTiledMap().getTileId((int) super.getCollisionBox().getTopLeftX() / Main.TILE_SIZE, (int) (super.getCollisionBox().getTopLeftY() - distance) / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 && Game.getTiledMap().getTileId((int) super.getCollisionBox().getTopRightX() / Main.TILE_SIZE, (int) (super.getCollisionBox().getTopRightY() - distance) / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 ) {
			return false;
		} else {
			return true;
		}

	}

	private boolean isDownCollision(float distance) {

		for (NPC npc : npcList) {

			if(super.getCollisionBox().willIntersectDown(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}

		}

		if(Game.getTiledMap().getTileId((int) super.getCollisionBox().getBottomLeftX() / Main.TILE_SIZE, (int) (super.getCollisionBox().getBottomLeftY() + distance) / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 && Game.getTiledMap().getTileId((int) super.getCollisionBox().getBottomRightX() / Main.TILE_SIZE, (int) (super.getCollisionBox().getBottomRightY() + distance) / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {
			return false;
		} else {
			return true;
		}

	}

	private boolean isLeftCollision(float distance) {

		for (NPC npc : npcList) {

			if(super.getCollisionBox().willIntersectLeft(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}

		}

		if(Game.getTiledMap().getTileId((int) (super.getCollisionBox().getTopLeftX() - distance) / Main.TILE_SIZE, (int) super.getCollisionBox().getTopLeftY() / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 && Game.getTiledMap().getTileId((int) (super.getCollisionBox().getBottomLeftX() - distance) / Main.TILE_SIZE, (int) super.getCollisionBox().getBottomLeftY() / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {
			return false;
		} else {
			return true;
		}

	}

	private boolean isRightCollision(float distance) {

		for (NPC npc : npcList) {

			if(super.getCollisionBox().willIntersectRight(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}

		}

		if(Game.getTiledMap().getTileId((int) (super.getCollisionBox().getTopRightX() + distance) / Main.TILE_SIZE, (int) super.getCollisionBox().getTopRightY() / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 && Game.getTiledMap().getTileId((int) (super.getCollisionBox().getBottomRightX() + distance) / Main.TILE_SIZE, (int) super.getCollisionBox().getBottomRightY() / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {
			return false;
		} else {
			return true;
		}

	}
	
	public void decreaseHealth(int amount) {
		
		if(isAlive()) {
			
			getHealthBar().setCurrentValue(getHealthBar().getCurrentValue() - amount);
			
			if(getHealthBar().getCurrentValue() <= 0) {
				getHealthBar().setCurrentValue(0);
				setCurrentAnimation(getDieAnimation());
				setAlive(false);
			}
						
			setDrawBlood(true);
		
		}
		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public NewItemWindow getNewItemWindow() {
		return newItemWindow;
	}
	
	public DialogueWindow getDialogueWindow() {
		return dialogueWindow;
	}

	public boolean isYPressed() {
		return yPressed;
	}

	
	
}