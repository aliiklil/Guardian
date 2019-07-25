package models;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import gui.DialogueWindow;
import gui.CenteredText;
import gui.InventoryWindow;
import gui.NewItemWindow;
import gui.TradingWindow;
import main.Game;
import main.Main;
import manager.AnvilManager;
import manager.CharacterManager;
import manager.ChestManager;
import manager.ItemTypeManager;
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

	private InventoryWindow inventoryWindow = new InventoryWindow();
	private TradingWindow tradingWindow = new TradingWindow();

	private Bar prepareAttackBar;
	private Bar prepareShotBar;
	private Bar prepareSpellBar;

	private int damageToDeal = 0;

	private NewItemWindow newItemWindow = new NewItemWindow();
	private DialogueWindow dialogueWindow = new DialogueWindow();
	private CenteredText centeredText = new CenteredText();
	private CenteredText levelUpText = new CenteredText();

	private boolean yPressed = false;
	private boolean escapePressed = false;

	private Animation currentMeleeAnimation;
	private Animation currentBowAnimation;
	private Animation currentSpellAnimation;
	private Animation currentHeadAnimation;
	private Animation currentChestAnimation;
	private Animation currentLegsAnimation;
	private Animation currentHandsAnimation;
	private Animation currentFeetAnimation;

	private ItemType equippedMelee;
	private ItemType equippedBow;
	private ItemType equippedSpell;
	private ItemType equippedHead;
	private ItemType equippedTorso;
	private ItemType equippedLegs;
	private ItemType equippedHands;
	private ItemType equippedBoots;
	
	private int[] levelBorders = {100, 500, 1000, 2000, 5000, 10000, 20000};
	
	private int level = 0;
	private int experience = 0;
	private int nextLevelExperience = levelBorders[level];
	private int learningPoints = 100;
	
	private int strength = 200;
	private int dexterity = 200;
	private int wisdom = 10;
	
	private int healthPoints = 200;
	private int mana = 100;
	
	private int meleeSkill = 10;
	private int bowSkill = 10;
	private int spellSkill = 10;

	private int lockPickingSkill = 0;
	private int alchemySkill = 0;
	private int blacksmithingSkill = 0;
	
	private boolean takeFurs = false;
	private boolean takeTrophies = false;
	private boolean hpRegeneration = false;
	private boolean manaRegeneration = false;
	
	private int armorProtection;
	
	private Bar manaBar;
	
	public Player() throws SlickException {

		super(224, 64, "resources/player_sprites/player_base.png");

		super.setCollisionBox(new CollisionBox(super.getRelativeToMapX() + 6, super.getRelativeToMapY() + 10, super.getSpriteSize() / 2 - 12, super.getSpriteSize() / 2 - 12));
		super.setHitBox(new CollisionBox(super.getRelativeToMapX(), super.getRelativeToMapY() - 10, super.getSpriteSize() / 2, super.getSpriteSize() / 2));

		super.setHealthBar(new Bar(20, Main.HEIGHT - 40, 350, 25, 5, healthPoints, healthPoints, Color.red));

		manaBar = new Bar(Main.WIDTH - 20 - 350, Main.HEIGHT - 40, 350, 25, 5, mana, mana, Color.blue);
		
		prepareAttackBar = new Bar(getRelativeToMapX() + Game.getCurrentMap().getX() - 16, getRelativeToMapY() + Game.getCurrentMap().getY() - 32, 64, 5, 1, 0, 100, Color.cyan);
		prepareShotBar = new Bar(getRelativeToMapX() + Game.getCurrentMap().getX() - 16, getRelativeToMapY() + Game.getCurrentMap().getY() - 32, 64, 5, 1, 0, 100, Color.cyan);
		prepareSpellBar = new Bar(getRelativeToMapX() + Game.getCurrentMap().getX() - 16, getRelativeToMapY() + Game.getCurrentMap().getY() - 32, 64, 5, 1, 0, 100, Color.cyan);

		Game.getCurrentMap().setX(screenRelativeX - super.getRelativeToMapX() + super.getSpriteSize() / 4);
		Game.getCurrentMap().setY(screenRelativeY - super.getRelativeToMapY() + super.getSpriteSize() / 2);

		npcList = CharacterManager.getNpcList();

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

		
		if(equippedMelee != null) {
			
			equippedMelee.getAttackUpCollisionBox().setX(super.getRelativeToMapX() + equippedMelee.getAttackUpOffsetX());
			equippedMelee.getAttackUpCollisionBox().setY(super.getRelativeToMapY() + equippedMelee.getAttackUpOffsetY());
	
			equippedMelee.getAttackDownCollisionBox().setX(super.getRelativeToMapX() + equippedMelee.getAttackDownOffsetX());
			equippedMelee.getAttackDownCollisionBox().setY(super.getRelativeToMapY() + equippedMelee.getAttackDownOffsetY());
	
			equippedMelee.getAttackLeftCollisionBox().setX(super.getRelativeToMapX() + equippedMelee.getAttackLeftOffsetX());
			equippedMelee.getAttackLeftCollisionBox().setY(super.getRelativeToMapY() + equippedMelee.getAttackLeftOffsetY());
	
			equippedMelee.getAttackRightCollisionBox().setX(super.getRelativeToMapX() + equippedMelee.getAttackRightOffsetX());
			equippedMelee.getAttackRightCollisionBox().setY(super.getRelativeToMapY() + equippedMelee.getAttackRightOffsetY());
			
		}

		if(input.isKeyPressed(Input.KEY_Y)) {
			yPressed = true;
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			escapePressed = true;
		}

		if(isAlive()) {
			inventoryWindow.update();
			newItemWindow.update();
			updateDialogueNPC();
			updateDialogueAnvil();
			dialogueWindow.update();
			centeredText.update();
			levelUpText.update();
			tradingWindow.update();

			if(!dialogueWindow.isWindowOpen()) {
				updateMove();
				updateAttack();
				updateShoot();
				updateSpell();
				updatePickUpItem();
				updateOpenChest();

			}
		}

		yPressed = false;
		escapePressed = false;


		if(currentMeleeAnimation != null) {
			currentMeleeAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentBowAnimation != null) {
			currentBowAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentSpellAnimation != null) {
			currentSpellAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentHeadAnimation != null) {
			currentHeadAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentChestAnimation != null) {
			currentChestAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentHandsAnimation != null) {
			currentHandsAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentLegsAnimation != null) {
			currentLegsAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}

		if(currentFeetAnimation != null) {
			currentFeetAnimation.setCurrentFrame(getCurrentAnimation().getFrame());
		}
		
		
		
		computeArmorProtection();
		
		

	}

	public void render(Graphics g) {

		if((isAttacking || isPreparingAttack) && isAlive()) {

			super.getCurrentAnimation().draw(screenRelativeX - 64, screenRelativeY - 64);
			
			if(currentHeadAnimation != null) {
				currentHeadAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}

			if(currentHandsAnimation != null) {
				currentHandsAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}

			if(currentLegsAnimation != null) {
				currentLegsAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}
			
			if(currentChestAnimation != null) {
				currentChestAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}

			if(currentFeetAnimation != null) {
				currentFeetAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}
			
			if(currentMeleeAnimation != null) {
				currentMeleeAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}

			if(currentBowAnimation != null) {
				currentBowAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}

			if(currentSpellAnimation != null) {
				currentSpellAnimation.draw(screenRelativeX - 64, screenRelativeY - 64);
			}

		} else {

			super.getCurrentAnimation().draw(screenRelativeX, screenRelativeY);

			if(currentHeadAnimation != null) {
				currentHeadAnimation.draw(screenRelativeX, screenRelativeY);
			}

			if(currentHandsAnimation != null) {
				currentHandsAnimation.draw(screenRelativeX, screenRelativeY);
			}

			if(currentLegsAnimation != null) {
				currentLegsAnimation.draw(screenRelativeX, screenRelativeY);
			}
			
			if(currentChestAnimation != null) {
				currentChestAnimation.draw(screenRelativeX, screenRelativeY);
			}

			if(currentFeetAnimation != null) {
				currentFeetAnimation.draw(screenRelativeX, screenRelativeY);
			}
			
			if(currentMeleeAnimation != null) {
				currentMeleeAnimation.draw(screenRelativeX, screenRelativeY);
			}

			if(currentBowAnimation != null) {
				currentBowAnimation.draw(screenRelativeX, screenRelativeY);
			}

			if(currentSpellAnimation != null) {
				currentSpellAnimation.draw(screenRelativeX, screenRelativeY);
			}

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

		if(!isAttacking && !isPreparingAttack && !isPreparingShot && !isPreparingSpell) {

			if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

				if(isUpCollision(super.getMovementSpeed())) {

					setAnimationsToLookUp();

				} else {

					if(screenRelativeY > 440) {
						screenRelativeY = screenRelativeY - getMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() + getMovementSpeed());
					}

					setAnimationsToGoUp();

				}

				lookUp = true;
				lookDown = false;
				lookLeft = false;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {
				if(isDownCollision(super.getMovementSpeed())) {

					setAnimationsToLookDown();

				} else {

					if(screenRelativeY < 576) {
						screenRelativeY = screenRelativeY + getMovementSpeed();
					} else {
						Game.getCurrentMap().setY(Game.getCurrentMap().getY() - getMovementSpeed());
					}

					setAnimationsToGoDown();

				}

				lookUp = false;
				lookDown = true;
				lookLeft = false;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

				if(isLeftCollision(super.getMovementSpeed())) {

					setAnimationsToLookLeft();

				} else {

					if(screenRelativeX > 860) {
						screenRelativeX = screenRelativeX - getMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() + getMovementSpeed());
					}

					setAnimationsToGoLeft();

				}

				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

				if(isRightCollision(super.getMovementSpeed())) {

					setAnimationsToLookRight();

				} else {

					if(screenRelativeX < 996) {
						screenRelativeX = screenRelativeX + getMovementSpeed();
					} else {
						Game.getCurrentMap().setX(Game.getCurrentMap().getX() - getMovementSpeed());
					}

					setAnimationsToGoRight();

				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;

			} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

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
					setAnimationsToGoLeft();
				} else {
					setAnimationsToLookLeft();
				}

				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

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
					setAnimationsToGoRight();
				} else {
					setAnimationsToLookRight();
				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;

			} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_RIGHT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

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
					setAnimationsToGoLeft();
				} else {
					setAnimationsToLookLeft();
				}

				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;

			} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

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
					setAnimationsToGoRight();
				} else {
					setAnimationsToLookRight();
				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;

			} else {

				if(lookUp) {
					setAnimationsToLookUp();
				}

				if(lookDown) {
					setAnimationsToLookDown();
				}

				if(lookLeft) {
					setAnimationsToLookLeft();
				}

				if(lookRight) {
					setAnimationsToLookRight();
				}

				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = false;

			}

		}

	}

	private void updateAttack() {

		if(input.isKeyDown(Input.KEY_X) && !isAttacking && !isPreparingShot && !isPreparingSpell && !inventoryWindow.isWindowOpen() && equippedMelee != null && !tradingWindow.isWindowOpen()) {

			if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation() || input.isKeyDown(Input.KEY_UP)) {
				setAnimationsToPrepareAttackUp();
			}

			if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation() || input.isKeyDown(Input.KEY_DOWN)) {
				setAnimationsToPrepareAttackDown();
			}

			if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation() || input.isKeyDown(Input.KEY_LEFT)) {
				setAnimationsToPrepareAttackLeft();
			}

			if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation() || input.isKeyDown(Input.KEY_RIGHT)) {
				setAnimationsToPrepareAttackRight();
			}

			startAllAnimations();
			isPreparingAttack = true;

		}

		if(isPreparingAttack && prepareAttackBar.getCurrentValue() < prepareAttackBar.getMaxValue() && isAlive()) {
			prepareAttackBar.setCurrentValue(prepareAttackBar.getCurrentValue() + 1);
		}

		if(!input.isKeyDown(Input.KEY_X) && isPreparingAttack) {

			if(super.getCurrentAnimation() == super.getPrepareSlayUpAnimation() || super.getCurrentAnimation() == super.getPrepareThrustUpAnimation()) {
				setAnimationsToAttackUp();
				isPreparingAttack = false;
			}

			if(super.getCurrentAnimation() == super.getPrepareSlayDownAnimation() || super.getCurrentAnimation() == super.getPrepareThrustDownAnimation()) {
				setAnimationsToAttackDown();
				isPreparingAttack = false;
			}

			if(super.getCurrentAnimation() == super.getPrepareSlayLeftAnimation() || super.getCurrentAnimation() == super.getPrepareThrustLeftAnimation()) {
				setAnimationsToAttackLeft();
				isPreparingAttack = false;
			}

			if(super.getCurrentAnimation() == super.getPrepareSlayRightAnimation() || super.getCurrentAnimation() == super.getPrepareThrustRightAnimation()) {
				setAnimationsToAttackRight();
				isPreparingAttack = false;
			}

			startAllAnimations();
			damageToDeal = equippedMelee.getDamage() + strength + (int) ((equippedMelee.getDamage() + strength) * prepareAttackBar.getCurrentValue()/100.0) * 2;

			if((equippedMelee.getItemCategory().equals("melee_slay") || equippedMelee.getItemCategory().equals("melee_thrust")) && meleeSkill/100.0 > new Random().nextDouble()) {
				damageToDeal = damageToDeal * 5;
			}
			
			isAttacking = true;
			damageDealt = false;
			prepareAttackBar.setCurrentValue(0);

		}

		if(isAttacking && (super.getSlayUpAnimation().isStopped() || super.getThrustUpAnimation().isStopped())) {
			restartAllAnimations();
			setAnimationsToLookUp();
			isAttacking = false;
		}

		if(isAttacking && (super.getSlayDownAnimation().isStopped() || super.getThrustDownAnimation().isStopped())) {
			restartAllAnimations();
			setAnimationsToLookDown();
			isAttacking = false;
		}

		if(isAttacking && (super.getSlayLeftAnimation().isStopped() || super.getThrustLeftAnimation().isStopped())) {
			restartAllAnimations();
			setAnimationsToLookLeft();
			isAttacking = false;
		}

		if(isAttacking && (super.getSlayRightAnimation().isStopped() || super.getThrustRightAnimation().isStopped())) {
			restartAllAnimations();
			setAnimationsToLookRight();
			isAttacking = false;
		}

		if(!damageDealt) {

			if(super.getCurrentAnimation().getFrame() == 3 && (super.getCurrentAnimation() == super.getSlayUpAnimation() || super.getCurrentAnimation() == super.getThrustUpAnimation())) {
				for (NPC npc : npcList) {
					if(equippedMelee.getAttackUpCollisionBox().intersects(npc.getHitBox()) && npc.isAlive() /*&& npc.isHostileToPlayer()*/) {
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

			if(super.getCurrentAnimation().getFrame() == 3 && (super.getCurrentAnimation() == super.getSlayDownAnimation() || super.getCurrentAnimation() == super.getThrustDownAnimation())) {
				for (NPC npc : npcList) {
					if(equippedMelee.getAttackDownCollisionBox().intersects(npc.getHitBox()) && npc.isAlive() /*&& npc.isHostileToPlayer()*/) {
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

			if(super.getCurrentAnimation().getFrame() == 3 && (super.getCurrentAnimation() == super.getSlayLeftAnimation() || super.getCurrentAnimation() == super.getThrustLeftAnimation())) {
				for (NPC npc : npcList) {
					if(equippedMelee.getAttackLeftCollisionBox().intersects(npc.getHitBox()) && npc.isAlive() /*&& npc.isHostileToPlayer()*/) {
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

			if(super.getCurrentAnimation().getFrame() == 3 && (super.getCurrentAnimation() == super.getSlayRightAnimation() || super.getCurrentAnimation() == super.getThrustRightAnimation())) {
				for (NPC npc : npcList) {
					if(equippedMelee.getAttackRightCollisionBox().intersects(npc.getHitBox()) && npc.isAlive() /*&& npc.isHostileToPlayer()*/) {
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
		
		boolean arrowExists = false;

		for(Item item : getInventoryList()) {
			if(item.getItemType().equals(Game.getItemTypeManager().arrow)) {
				arrowExists = true;
				break;
			}
		}
				
		if(input.isKeyDown(Input.KEY_A) && !isAttacking && !isPreparingAttack && !isPreparingSpell && !inventoryWindow.isWindowOpen() & equippedBow != null && !tradingWindow.isWindowOpen()) {

			if(!arrowExists) {
				
				String text = "No arrows to shoot";
				centeredText.showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				
			} else {
											
				if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation() || input.isKeyDown(Input.KEY_UP)) {
					if(isPreparingShot && super.getCurrentAnimation() != super.getShootUpAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToShootUp();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToShootUp();
					}
					arrowCreated = false;
				}
	
				if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation() || input.isKeyDown(Input.KEY_DOWN)) {
					if(isPreparingShot && super.getCurrentAnimation() != super.getShootDownAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToShootDown();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToShootDown();
					}
					arrowCreated = false;
				}
	
				if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation() || input.isKeyDown(Input.KEY_LEFT)) {
					if(isPreparingShot && super.getCurrentAnimation() != super.getShootLeftAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToShootLeft();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToShootLeft();
					}
					arrowCreated = false;
				}
	
				if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation() || input.isKeyDown(Input.KEY_RIGHT)) {
					if(isPreparingShot && super.getCurrentAnimation() != super.getShootRightAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToShootRight();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToShootRight();
					}
					arrowCreated = false;
				}
	
				startAllAnimations();
				isPreparingShot = true;
				
			}
			
		}

		if(isPreparingShot && prepareShotBar.getCurrentValue() < prepareShotBar.getMaxValue() && super.getCurrentAnimation().getFrame() == 8) {
			prepareShotBar.setCurrentValue(prepareShotBar.getCurrentValue() + 1);
		}

		if(!input.isKeyDown(Input.KEY_A) && isPreparingShot && super.getCurrentAnimation().isStopped() && !arrowCreated) {

			damageToDeal = equippedBow.getDamage() + dexterity + (int) ((equippedBow.getDamage() + dexterity) * prepareShotBar.getCurrentValue()/100.0) * 2;

			if(bowSkill/100.0 > new Random().nextDouble()) {
				damageToDeal = damageToDeal * 5;
			}
			
			int arrowVelocity = 4 + prepareShotBar.getCurrentValue()/10 + bowSkill/10 * 2;
			prepareShotBar.setCurrentValue(0);
			
			
			//Delete arrow from inventory, or decrement arrowCount
			for(int i = 0; i < getInventoryList().size(); i++) {
				if(getInventoryList().get(i).getItemType().equals(Game.getItemTypeManager().arrow)) {
					
					int arrowCount = getItemCountList().get(i);
					
					if(arrowCount > 1) {
						getItemCountList().set(i, arrowCount - 1);
					}
					
					if(arrowCount == 1) {
						getInventoryList().remove(i);
					}
					
					break;
				}
			}
			
			

			if(super.getCurrentAnimation() == super.getShootUpAnimation()) {
				restartAllAnimations();
				setAnimationsToLookUp();
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 1, 0, 1, 0, true, 100, true), 0, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getShootDownAnimation()) {
				restartAllAnimations();
				setAnimationsToLookDown();
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 3, 0, 3, 0, true, 100, true), 1, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getShootLeftAnimation()) {
				restartAllAnimations();
				setAnimationsToLookLeft();
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 0, 0, 0, 0, true, 100, true), 2, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getShootRightAnimation()) {
				restartAllAnimations();
				setAnimationsToLookRight();
				isPreparingShot = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/arrow.png", 64, 64), 2, 0, 2, 0, true, 100, true), 3, damageToDeal, arrowVelocity);
				arrowCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

		}

	}

	private void updateSpell() throws SlickException {

		
		
		
		if(input.isKeyDown(Input.KEY_S) && !isAttacking && !isPreparingAttack && !isPreparingShot && !inventoryWindow.isWindowOpen() && equippedSpell != null && !tradingWindow.isWindowOpen()) {
			
			boolean enoughMana;
			
			if(getManaBar().getCurrentValue() - equippedSpell.getManaCost() >= 0) {
				enoughMana = true;
			} else {
				enoughMana = false;
			}
			
			if(!enoughMana) {
				
				String text = "Not enough mana";
				centeredText.showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				
			} else {

				if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation() || input.isKeyDown(Input.KEY_UP)) {
					if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellUpAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToSpellUp();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToSpellUp();
					}
					spellCreated = false;
				}
	
				if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation() || input.isKeyDown(Input.KEY_DOWN)) {
					if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellDownAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToSpellDown();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToSpellDown();
					}
					spellCreated = false;
				}
	
				if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation() || input.isKeyDown(Input.KEY_LEFT)) {
					if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellLeftAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToSpellLeft();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToSpellLeft();
					}
					spellCreated = false;
				}
	
				if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation() || input.isKeyDown(Input.KEY_RIGHT)) {
					if(isPreparingSpell && super.getCurrentAnimation() != super.getSpellRightAnimation()) {
						int frameIndex = super.getCurrentAnimation().getFrame();
						restartAllAnimations();
						setAnimationsToSpellRight();
						setAllAnimationsToFrame(frameIndex);
					} else {
						setAnimationsToSpellRight();
					}
					spellCreated = false;
				}
	
				startAllAnimations();
				isPreparingSpell = true;
			
			}

		}

		if(isPreparingSpell && prepareSpellBar.getCurrentValue() < prepareSpellBar.getMaxValue() && super.getCurrentAnimation().getFrame() == 6) {
			prepareSpellBar.setCurrentValue(prepareSpellBar.getCurrentValue() + 1);
		}

		if(!input.isKeyDown(Input.KEY_S) && isPreparingSpell && super.getCurrentAnimation().isStopped() && !spellCreated) {
			
			damageToDeal = equippedSpell.getDamage() + wisdom + (int) ((equippedSpell.getDamage() + wisdom) * prepareSpellBar.getCurrentValue()/100.0) * 2;
			if(spellSkill/100.0 > new Random().nextDouble()) {
				damageToDeal = damageToDeal * 5;
			}
			
			int spellVelocity = 4 + prepareSpellBar.getCurrentValue()/10 + spellSkill/10 * 2;
			
			//Decrease mana
			getManaBar().setCurrentValue(getManaBar().getCurrentValue() - equippedSpell.getManaCost());
			
			prepareSpellBar.setCurrentValue(0);

			if(super.getCurrentAnimation() == super.getSpellUpAnimation()) {
				restartAllAnimations();
				setAnimationsToLookUp();
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/firespell.png", 64, 64), 0, 1, 7, 1, true, 100, true), 0, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);

			}

			if(super.getCurrentAnimation() == super.getSpellDownAnimation()) {
				restartAllAnimations();
				setAnimationsToLookDown();
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/firespell.png", 64, 64), 0, 3, 7, 3, true, 100, true), 1, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getSpellLeftAnimation()) {
				restartAllAnimations();
				setAnimationsToLookLeft();
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/firespell.png", 64, 64), 0, 0, 7, 0, true, 100, true), 2, damageToDeal, spellVelocity);
				spellCreated = true;
				Game.getProjectileManager().addProjectile(projectile);
			}

			if(super.getCurrentAnimation() == super.getSpellRightAnimation()) {
				restartAllAnimations();
				setAnimationsToLookRight();
				isPreparingSpell = false;

				Projectile projectile = new Projectile(super.getRelativeToMapX() + 16, super.getRelativeToMapY(), new Animation(new SpriteSheet("resources/firespell.png", 64, 64), 0, 2, 7, 2, true, 100, true), 3, damageToDeal, spellVelocity);
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
				inventoryWindow.addItem(item);
				newItemWindow.showWindow(item);

				if(item.getItemType().getName().equals("Gold")) {
					inventoryWindow.incrementGoldCounter();
				}

			}

		}

	}

	private void updateOpenChest() throws SlickException {

		if(yPressed && getCurrentAnimation() == getLookUpAnimation() && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

			ArrayList<Chest> chestList = ChestManager.getChestList();

			for (Chest chest : chestList) {

				if(super.getCollisionBox().willIntersectUp(chest.getCollisionBox(), 5) && !chest.isOpened()) {
					
					if(lockPickingSkill >= chest.getChestLevel()) {
					
						chest.getAnimation().start();
						inventoryWindow.addItem(chest.getItem());
						chest.setOpened(true);
						newItemWindow.showWindow(chest.getItem());
					
					} else {
						
						String text = "Lockpicking skill is too low";
						centeredText.showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
						
					}
				}

			}

		}

	}

	private void updateDialogueNPC() throws SlickException {

		if(yPressed && !dialogueWindow.isWindowOpen() && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

			ArrayList<NPC> npcList = CharacterManager.getNpcList();

			for (NPC npc : npcList) {

				if(!npc.isHostileToPlayer() && npc.getStartingDialogues() != null) {

					if(super.getCollisionBox().willIntersectUp(npc.getCollisionBox(), 5) && getCurrentAnimation() == getLookUpAnimation()) {
						npc.setCurrentAnimation(npc.getLookDownAnimation());
						
						dialogueWindow.showWindow(npc.getStartingDialogues());
						tradingWindow.setNpc(npc);
						yPressed = false;
					}

					if(super.getCollisionBox().willIntersectDown(npc.getCollisionBox(), 5) && getCurrentAnimation() == getLookDownAnimation()) {
						npc.setCurrentAnimation(npc.getLookUpAnimation());
						
						dialogueWindow.showWindow(npc.getStartingDialogues());
						tradingWindow.setNpc(npc);
						yPressed = false;
					}

					if(super.getCollisionBox().willIntersectLeft(npc.getCollisionBox(), 5) && getCurrentAnimation() == getLookLeftAnimation()) {
						npc.setCurrentAnimation(npc.getLookRightAnimation());
						
						dialogueWindow.showWindow(npc.getStartingDialogues());
						tradingWindow.setNpc(npc);
						yPressed = false;
					}

					if(super.getCollisionBox().willIntersectRight(npc.getCollisionBox(), 5) && getCurrentAnimation() == getLookRightAnimation()) {
						npc.setCurrentAnimation(npc.getLookLeftAnimation());
						
						dialogueWindow.showWindow(npc.getStartingDialogues());
						tradingWindow.setNpc(npc);
						yPressed = false;
					}


				}

			}

		}

	}
	
	private void updateDialogueAnvil() throws SlickException {

		if(yPressed && !dialogueWindow.isWindowOpen() && !inventoryWindow.isWindowOpen() && !tradingWindow.isWindowOpen()) {

			ArrayList<Anvil> anvilList = AnvilManager.getAnvilList();

			for (Anvil anvil : anvilList) {

				if(anvil.getStartingDialogues() != null) {

					if(super.getCollisionBox().willIntersectUp(anvil.getCollisionBox(), 5) && getCurrentAnimation() == getLookUpAnimation()) {
						dialogueWindow.showWindow(anvil.getStartingDialogues());
						yPressed = false;
					}

					if(super.getCollisionBox().willIntersectDown(anvil.getCollisionBox(), 5) && getCurrentAnimation() == getLookDownAnimation()) {
						dialogueWindow.showWindow(anvil.getStartingDialogues());
						yPressed = false;
					}

					if(super.getCollisionBox().willIntersectLeft(anvil.getCollisionBox(), 5) && getCurrentAnimation() == getLookLeftAnimation()) {
						dialogueWindow.showWindow(anvil.getStartingDialogues());
						yPressed = false;
					}

					if(super.getCollisionBox().willIntersectRight(anvil.getCollisionBox(), 5) && getCurrentAnimation() == getLookRightAnimation()) {
						dialogueWindow.showWindow(anvil.getStartingDialogues());
						yPressed = false;
					}


				}

			}

		}

	}

	public void setAnimationsToLookUp() {

		super.setCurrentAnimation(super.getLookUpAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getLookUpAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getLookUpAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getLookUpAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getLookUpAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getLookUpAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getLookUpAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getLookUpAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getLookUpAnimation();
		}

	}

	public void setAnimationsToLookDown() {

		super.setCurrentAnimation(super.getLookDownAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getLookDownAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getLookDownAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getLookDownAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getLookDownAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getLookDownAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getLookDownAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getLookDownAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getLookDownAnimation();
		}

	}

	public void setAnimationsToLookLeft() {

		super.setCurrentAnimation(super.getLookLeftAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getLookLeftAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getLookLeftAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getLookLeftAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getLookLeftAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getLookLeftAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getLookLeftAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getLookLeftAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getLookLeftAnimation();
		}

	}

	public void setAnimationsToLookRight() {

		super.setCurrentAnimation(super.getLookRightAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getLookRightAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getLookRightAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getLookRightAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getLookRightAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getLookRightAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getLookRightAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getLookRightAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getLookRightAnimation();
		}

	}

	public void setAnimationsToGoUp() {

		super.setCurrentAnimation(super.getGoUpAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getGoUpAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getGoUpAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getGoUpAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getGoUpAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getGoUpAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getGoUpAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getGoUpAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getGoUpAnimation();
		}

	}

	public void setAnimationsToGoDown() {

		super.setCurrentAnimation(super.getGoDownAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getGoDownAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getGoDownAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getGoDownAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getGoDownAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getGoDownAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getGoDownAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getGoDownAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getGoDownAnimation();
		}

	}

	public void setAnimationsToGoLeft() {

		super.setCurrentAnimation(super.getGoLeftAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getGoLeftAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getGoLeftAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getGoLeftAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getGoLeftAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getGoLeftAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getGoLeftAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getGoLeftAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getGoLeftAnimation();
		}

	}

	public void setAnimationsToGoRight() {

		super.setCurrentAnimation(super.getGoRightAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getGoRightAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getGoRightAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getGoRightAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getGoRightAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getGoRightAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getGoRightAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getGoRightAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getGoRightAnimation();
		}

	}

	public void setAnimationsToAttackUp() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
		
			super.setCurrentAnimation(super.getSlayUpAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getSlayUpAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getSlayUpAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getSlayUpAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getSlayUpAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getSlayUpAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getSlayUpAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getSlayUpAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getSlayUpAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getThrustUpAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getThrustUpAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getThrustUpAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getThrustUpAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getThrustUpAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getThrustUpAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getThrustUpAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getThrustUpAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getThrustUpAnimation();
			}
			
		}

	}

	public void setAnimationsToAttackDown() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getSlayDownAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getSlayDownAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getSlayDownAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getSlayDownAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getSlayDownAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getSlayDownAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getSlayDownAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getSlayDownAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getSlayDownAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getThrustDownAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getThrustDownAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getThrustDownAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getThrustDownAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getThrustDownAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getThrustDownAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getThrustDownAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getThrustDownAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getThrustDownAnimation();
			}
			
		}

	}

	public void setAnimationsToAttackLeft() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getSlayLeftAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getSlayLeftAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getSlayLeftAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getSlayLeftAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getSlayLeftAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getSlayLeftAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getSlayLeftAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getSlayLeftAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getSlayLeftAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getThrustLeftAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getThrustLeftAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getThrustLeftAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getThrustLeftAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getThrustLeftAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getThrustLeftAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getThrustLeftAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getThrustLeftAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getThrustLeftAnimation();
			}
			
		}

	}

	public void setAnimationsToAttackRight() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getSlayRightAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getSlayRightAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getSlayRightAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getSlayRightAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getSlayRightAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getSlayRightAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getSlayRightAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getSlayRightAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getSlayRightAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getThrustRightAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getThrustRightAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getThrustRightAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getThrustRightAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getThrustRightAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getThrustRightAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getThrustRightAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getThrustRightAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getThrustRightAnimation();
			}
			
		}

	}

	public void setAnimationsToPrepareAttackUp() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getPrepareSlayUpAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareSlayUpAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareSlayUpAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareSlayUpAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareSlayUpAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareSlayUpAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareSlayUpAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareSlayUpAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareSlayUpAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getPrepareThrustUpAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareThrustUpAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareThrustUpAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareThrustUpAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareThrustUpAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareThrustUpAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareThrustUpAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareThrustUpAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareThrustUpAnimation();
			}
			
		}

	}

	public void setAnimationsToPrepareAttackDown() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getPrepareSlayDownAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareSlayDownAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareSlayDownAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareSlayDownAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareSlayDownAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareSlayDownAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareSlayDownAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareSlayDownAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareSlayDownAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getPrepareThrustDownAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareThrustDownAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareThrustDownAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareThrustDownAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareThrustDownAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareThrustDownAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareThrustDownAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareThrustDownAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareThrustDownAnimation();
			}
			
		}

	}

	public void setAnimationsToPrepareAttackLeft() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getPrepareSlayLeftAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareSlayLeftAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareSlayLeftAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareSlayLeftAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareSlayLeftAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareSlayLeftAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareSlayLeftAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareSlayLeftAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareSlayLeftAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getPrepareThrustLeftAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareThrustLeftAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareThrustLeftAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareThrustLeftAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareThrustLeftAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareThrustLeftAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareThrustLeftAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareThrustLeftAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareThrustLeftAnimation();
			}
			
		}

	}

	public void setAnimationsToPrepareAttackRight() {

		if(equippedMelee.getItemCategory().equals("melee_slay")) {
			
			super.setCurrentAnimation(super.getPrepareSlayRightAnimation());
	
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareSlayRightAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareSlayRightAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareSlayRightAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareSlayRightAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareSlayRightAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareSlayRightAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareSlayRightAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareSlayRightAnimation();
			}
			
		} 
		
		if(equippedMelee.getItemCategory().equals("melee_thrust")) {
			
			super.setCurrentAnimation(super.getPrepareThrustRightAnimation());
			
			if(equippedMelee != null) {
				currentMeleeAnimation = equippedMelee.getPrepareThrustRightAnimation();
			}
	
			if(equippedBow != null) {
				currentBowAnimation = equippedBow.getPrepareThrustRightAnimation();
			}
	
			if(equippedSpell != null) {
				currentSpellAnimation = equippedSpell.getPrepareThrustRightAnimation();
			}
	
			if(equippedHead != null) {
				currentHeadAnimation = equippedHead.getPrepareThrustRightAnimation();
			}
	
			if(equippedTorso != null) {
				currentChestAnimation = equippedTorso.getPrepareThrustRightAnimation();
			}
	
			if(equippedHands != null) {
				currentHandsAnimation = equippedHands.getPrepareThrustRightAnimation();
			}
	
			if(equippedLegs != null) {
				currentLegsAnimation = equippedLegs.getPrepareThrustRightAnimation();
			}
	
			if(equippedBoots != null) {
				currentFeetAnimation = equippedBoots.getPrepareThrustRightAnimation();
			}
			
		}

	}

	public void setAnimationsToShootUp() {

		super.setCurrentAnimation(super.getShootUpAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getShootUpAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getShootUpAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getShootUpAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getShootUpAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getShootUpAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getShootUpAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getShootUpAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getShootUpAnimation();
		}

	}

	public void setAnimationsToShootDown() {

		super.setCurrentAnimation(super.getShootDownAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getShootDownAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getShootDownAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getShootDownAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getShootDownAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getShootDownAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getShootDownAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getShootDownAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getShootDownAnimation();
		}

	}

	public void setAnimationsToShootLeft() {

		super.setCurrentAnimation(super.getShootLeftAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getShootLeftAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getShootLeftAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getShootLeftAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getShootLeftAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getShootLeftAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getShootLeftAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getShootLeftAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getShootLeftAnimation();
		}

	}

	public void setAnimationsToShootRight() {

		super.setCurrentAnimation(super.getShootRightAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getShootRightAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getShootRightAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getShootRightAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getShootRightAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getShootRightAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getShootRightAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getShootRightAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getShootRightAnimation();
		}

	}

	public void setAnimationsToSpellUp() {

		super.setCurrentAnimation(super.getSpellUpAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getSpellUpAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getSpellUpAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getSpellUpAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getSpellUpAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getSpellUpAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getSpellUpAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getSpellUpAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getSpellUpAnimation();
		}

	}

	public void setAnimationsToSpellDown() {

		super.setCurrentAnimation(super.getSpellDownAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getSpellDownAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getSpellDownAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getSpellDownAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getSpellDownAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getSpellDownAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getSpellDownAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getSpellDownAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getSpellDownAnimation();
		}

	}

	public void setAnimationsToSpellLeft() {

		super.setCurrentAnimation(super.getSpellLeftAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getSpellLeftAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getSpellLeftAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getSpellLeftAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getSpellLeftAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getSpellLeftAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getSpellLeftAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getSpellLeftAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getSpellLeftAnimation();
		}

	}

	public void setAnimationsToSpellRight() {

		super.setCurrentAnimation(super.getSpellRightAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getSpellRightAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getSpellRightAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getSpellRightAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getSpellRightAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getSpellRightAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getSpellRightAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getSpellRightAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getSpellRightAnimation();
		}

	}

	public void setAnimationsToDie() {

		super.setCurrentAnimation(super.getDieAnimation());

		if(equippedMelee != null) {
			currentMeleeAnimation = equippedMelee.getDieAnimation();
		}

		if(equippedBow != null) {
			currentBowAnimation = equippedBow.getDieAnimation();
		}

		if(equippedSpell != null) {
			currentSpellAnimation = equippedSpell.getDieAnimation();
		}

		if(equippedHead != null) {
			currentHeadAnimation = equippedHead.getDieAnimation();
		}

		if(equippedTorso != null) {
			currentChestAnimation = equippedTorso.getDieAnimation();
		}

		if(equippedHands != null) {
			currentHandsAnimation = equippedHands.getDieAnimation();
		}

		if(equippedLegs != null) {
			currentLegsAnimation = equippedLegs.getDieAnimation();
		}

		if(equippedBoots != null) {
			currentFeetAnimation = equippedBoots.getDieAnimation();
		}

	}

	public void restartAllAnimations() {

		super.getCurrentAnimation().restart();

		if(equippedMelee != null) {
			currentMeleeAnimation.restart();
		}

		if(equippedBow != null) {
			currentBowAnimation.restart();
		}

		if(equippedSpell != null) {
			currentSpellAnimation.restart();
		}

		if(equippedHead != null) {
			currentHeadAnimation.restart();
		}

		if(equippedTorso != null) {
			currentChestAnimation.restart();
		}

		if(equippedHands != null) {
			currentHandsAnimation.restart();
		}

		if(equippedLegs != null) {
			currentLegsAnimation.restart();
		}

		if(equippedBoots != null) {
			currentFeetAnimation.restart();
		}

	}

	public void setAllAnimationsToFrame(int index) {

		super.getCurrentAnimation().setCurrentFrame(index);

		if(equippedMelee != null) {
			currentMeleeAnimation.setCurrentFrame(index);
		}

		if(equippedBow != null) {
			currentBowAnimation.setCurrentFrame(index);
		}

		if(equippedSpell != null) {
			currentSpellAnimation.setCurrentFrame(index);
		}

		if(equippedHead != null) {
			currentHeadAnimation.setCurrentFrame(index);
		}

		if(equippedTorso != null) {
			currentChestAnimation.setCurrentFrame(index);
		}

		if(equippedHands != null) {
			currentHandsAnimation.setCurrentFrame(index);
		}

		if(equippedLegs != null) {
			currentLegsAnimation.setCurrentFrame(index);
		}

		if(equippedBoots != null) {
			currentFeetAnimation.setCurrentFrame(index);
		}

	}

	private void startAllAnimations() {

		super.getCurrentAnimation().start();

		if(equippedMelee != null) {
			currentMeleeAnimation.start();
		}

		if(equippedBow != null) {
			currentBowAnimation.start();
		}

		if(equippedSpell != null) {
			currentSpellAnimation.start();
		}

		if(equippedHead != null) {
			currentHeadAnimation.start();
		}

		if(equippedTorso != null) {
			currentChestAnimation.start();
		}

		if(equippedHands != null) {
			currentHandsAnimation.start();
		}

		if(equippedLegs != null) {
			currentLegsAnimation.start();
		}

		if(equippedBoots != null) {
			currentFeetAnimation.start();
		}

	}

	private boolean isUpCollision(float distance) {

		for (NPC npc : npcList) {

			if(super.getCollisionBox().willIntersectUp(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}

		}

		if(Game.getTiledMap().getTileId((int) super.getCollisionBox().getTopLeftX() / Main.TILE_SIZE, (int) (super.getCollisionBox().getTopLeftY() - distance) / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0 && Game.getTiledMap().getTileId((int) super.getCollisionBox().getTopRightX() / Main.TILE_SIZE, (int) (super.getCollisionBox().getTopRightY() - distance) / Main.TILE_SIZE, Game.getNotWalkableLayerIndex()) == 0) {
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
	
	public void computeArmorProtection() {
		
		armorProtection = 0;
		
		for(Item i : getInventoryList()) {
			
			if(i.isEquipped()) {
				armorProtection = armorProtection + i.getItemType().getProtection();
			}
			
		}
		
	}

	public InventoryWindow getInventoryWindow() {
		return inventoryWindow;
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

	public boolean isEscapePressed() {
		return escapePressed;
	}

	public void setEquippedMelee(ItemType equippedMelee) {
		this.equippedMelee = equippedMelee;
	}

	public void setEquippedBow(ItemType equippedBow) {
		this.equippedBow = equippedBow;
	}

	public void setEquippedSpell(ItemType equippedSpell) {
		this.equippedSpell = equippedSpell;
	}

	public void setEquippedHead(ItemType equippedHead) {
		this.equippedHead = equippedHead;
	}

	public void setEquippedTorso(ItemType equippedTorso) {
		this.equippedTorso = equippedTorso;
	}

	public void setEquippedLegs(ItemType equippedLegs) {
		this.equippedLegs = equippedLegs;
	}

	public void setEquippedHands(ItemType equippedHands) {
		this.equippedHands = equippedHands;
	}

	public void setEquippedBoots(ItemType equippedBoots) {
		this.equippedBoots = equippedBoots;
	}

	public void setCurrentMeleeAnimation(Animation currentMeleeAnimation) {
		this.currentMeleeAnimation = currentMeleeAnimation;
	}

	public void setCurrentBowAnimation(Animation currentBowAnimation) {
		this.currentBowAnimation = currentBowAnimation;
	}

	public void setCurrentSpellAnimation(Animation currentSpellAnimation) {
		this.currentSpellAnimation = currentSpellAnimation;
	}

	public void setCurrentHeadAnimation(Animation currentHeadAnimation) {
		this.currentHeadAnimation = currentHeadAnimation;
	}

	public void setCurrentChestAnimation(Animation currentChestAnimation) {
		this.currentChestAnimation = currentChestAnimation;
	}

	public void setCurrentLegsAnimation(Animation currentLegsAnimation) {
		this.currentLegsAnimation = currentLegsAnimation;
	}

	public void setCurrentHandsAnimation(Animation currentHandsAnimation) {
		this.currentHandsAnimation = currentHandsAnimation;
	}

	public void setCurrentFeetAnimation(Animation currentFeetAnimation) {
		this.currentFeetAnimation = currentFeetAnimation;
	}
	
	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}
	
	public void addExperience(int experienceToAdd) {
		experience = experience + experienceToAdd;
		
		String text = "Experience + " + experienceToAdd;
		centeredText.showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
	
		if(experience >= levelBorders[level]) {
			level++;
			learningPoints = learningPoints + 10;
			nextLevelExperience = levelBorders[level];
			
			text = "LEVEL UP";
			levelUpText.showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2 - 20);
		}
	}
	
	public int getNextLevelExperience() {
		return nextLevelExperience;
	}

	public int getLearningPoints() {
		return learningPoints;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getDexterity() {
		return dexterity;
	}

	public int getWisdom() {
		return wisdom;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public int getMana() {
		return mana;
	}

	public int getMeleeSkill() {
		return meleeSkill;
	}

	public int getBowSkill() {
		return bowSkill;
	}

	public int getSpellSkill() {
		return spellSkill;
	}

	public boolean isTakeFurs() {
		return takeFurs;
	}

	public boolean isTakeTrophies() {
		return takeTrophies;
	}

	public boolean isHpRegeneration() {
		return hpRegeneration;
	}

	public boolean isManaRegeneration() {
		return manaRegeneration;
	}
	
	public CenteredText getCenteredText() {
		return centeredText;
	}
	
	public CenteredText getLevelUpText() {
		return levelUpText;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setMeleeSkill(int meleeSkill) {
		this.meleeSkill = meleeSkill;
	}

	public void setBowSkill(int bowSkill) {
		this.bowSkill = bowSkill;
	}

	public void setSpellSkill(int spellSkill) {
		this.spellSkill = spellSkill;
	}

	public void setTakeFurs(boolean takeFurs) {
		this.takeFurs = takeFurs;
	}

	public void setTakeTrophies(boolean takeTrophies) {
		this.takeTrophies = takeTrophies;
	}

	public void setHpRegeneration(boolean hpRegeneration) {
		this.hpRegeneration = hpRegeneration;
	}

	public void setManaRegeneration(boolean manaRegeneration) {
		this.manaRegeneration = manaRegeneration;
	}

	public void setLearningPoints(int learningPoints) {
		this.learningPoints = learningPoints;
	}

	public ItemType getEquippedHead() {
		return equippedHead;
	}

	public ItemType getEquippedTorso() {
		return equippedTorso;
	}

	public ItemType getEquippedLegs() {
		return equippedLegs;
	}

	public ItemType getEquippedHands() {
		return equippedHands;
	}

	public ItemType getEquippedBoots() {
		return equippedBoots;
	}
	
	public int getArmorProtection() {
		return armorProtection;
	}

	public void setArmorProtection(int armorProtection) {
		this.armorProtection = armorProtection;
	}

	public Bar getManaBar() {
		return manaBar;
	}

	public TradingWindow getTradingWindow() {
		return tradingWindow;
	}

	public int getLockPickingSkill() {
		return lockPickingSkill;
	}

	public void setLockPickingSkill(int lockPickingSkill) {
		this.lockPickingSkill = lockPickingSkill;
	}

	public int getAlchemySkill() {
		return alchemySkill;
	}

	public void setAlchemySkill(int alchemySkill) {
		this.alchemySkill = alchemySkill;
	}

	public int getBlacksmithingSkill() {
		return blacksmithingSkill;
	}

	public void setBlacksmithingSkill(int blacksmithingSkill) {
		this.blacksmithingSkill = blacksmithingSkill;
	}
	
}