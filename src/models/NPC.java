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

public class NPC extends Character {

	private float screenRelativeX;
	private float screenRelativeY;
	
	private float screenRelativeOverSizeX = Main.WIDTH / 2 - super.getOverSizeSpriteSize() / 2;
	private float screenRelativeOverSizeY = Main.HEIGHT / 2 - super.getOverSizeSpriteSize() / 2;
	
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
	
	private boolean hostileToPlayer;
	
	private Item itemDrop;

	private ArrayList<Dialogue> startingDialogues = new ArrayList<Dialogue>();
	
	private int experienceForPlayer;
	
	private int damageOutput;
	private double critChance;
	
	private ItemType equippedMelee;
	
	private boolean iceblocked; //If NPC is blocked by iceblock
	private long iceblockedTimestamp; //Time when player was iceblocked
	
	private Animation iceblockAnimation;
	
	private boolean bloodtheft;
	private int bloodtheftCounter;
	private long bloodtheftTimestamp;
	
	public NPC(float relativeToMapX, float relativeToMapY, int currentHealth, int maxHealth, String spriteSheetPath, boolean hostileToPlayer, Item itemDrop, ArrayList<Dialogue> startingDialogues, int experienceForPlayer, int damageOutput, double critChance) throws SlickException {

		super(relativeToMapX, relativeToMapY, spriteSheetPath);
		
		super.setCollisionBox(new CollisionBox(super.getRelativeToMapX() + 6, super.getRelativeToMapY() + 10, super.getSpriteSize()/2 - 12, super.getSpriteSize()/2 - 12));
		super.setHitBox(new CollisionBox(super.getRelativeToMapX(), super.getRelativeToMapY() - 10, super.getSpriteSize()/2, super.getSpriteSize()/2));
		
		super.setHealthBar(new Bar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, currentHealth, maxHealth, Color.red));

		this.screenRelativeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;
		this.screenRelativeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;

		screenRelativeOverSizeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getOverSizeSpriteSize() / 2 + 16;
		screenRelativeOverSizeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getOverSizeSpriteSize() / 2;
		
		aggressionCircle = new Circle(super.getCenterX(), super.getCenterY(), aggressionCircleRadius);
		
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
						
		this.hostileToPlayer = hostileToPlayer;
		
		this.itemDrop = itemDrop;
		
		this.startingDialogues = startingDialogues;
		
		this.experienceForPlayer = experienceForPlayer;
		
		this.damageOutput = damageOutput;
		this.critChance = critChance;
		
		iceblockAnimation = new Animation(new SpriteSheet("resources/iceblockSprite.png", 64, 64), 0, 0, 0, 0, true, 100, true);
		
	}

	public void update() throws SlickException {
		
		super.update();
			
		screenRelativeX = (int) Game.getCurrentMap().getX() + getRelativeToMapX() - getSpriteSize() / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + getRelativeToMapY()  - getSpriteSize() / 2;

		screenRelativeOverSizeX = Game.getCurrentMap().getX() + getRelativeToMapX() - getOverSizeSpriteSize() / 2 + 16;
		screenRelativeOverSizeY = Game.getCurrentMap().getY() + getRelativeToMapY()  - getOverSizeSpriteSize() / 2;
		
		getHealthBar().setX(screenRelativeX);
		getHealthBar().setY(screenRelativeY);
		
		getCollisionBox().setX(getRelativeToMapX() + 6);
		getCollisionBox().setY(getRelativeToMapY() + 10);
		
		getHitBox().setX(getRelativeToMapX());
		getHitBox().setY(getRelativeToMapY() - 10);
		
		if(isAlive() && hostileToPlayer && !iceblocked) {
			goToPlayer();
			//attackPlayer();
		}
				
		
		if(equippedMelee != null) {
			
			equippedMelee.getAttackUpCollisionBox().setX(getRelativeToMapX() - 28);
			equippedMelee.getAttackUpCollisionBox().setY(getRelativeToMapY() - 37);
			
			equippedMelee.getAttackDownCollisionBox().setX(getRelativeToMapX() - 28);
			equippedMelee.getAttackDownCollisionBox().setY(getRelativeToMapY() + 12);
			
			equippedMelee.getAttackLeftCollisionBox().setX(getRelativeToMapX() - 67);
			equippedMelee.getAttackLeftCollisionBox().setY(getRelativeToMapY() - 16);
			
			equippedMelee.getAttackRightCollisionBox().setX(getRelativeToMapX() + 31);
			equippedMelee.getAttackRightCollisionBox().setY(getRelativeToMapY() - 16);
			
		}
		
		checkIfIceblockIsOver();
		
		checkIfBloodtheft();
		
		
		System.out.println("goUp " + goUp);
		System.out.println("goDown " + goDown);
		System.out.println("goLeft " + goLeft);
		System.out.println("goRight " + goRight);
		
		System.out.println("goUpLeft " + goUpLeft);
		System.out.println("goUpRight " + goUpRight);
		System.out.println("goDownLeft " + goDownLeft);
		System.out.println("goDownRight " + goDownRight);
	
	}

	public void render(Graphics g) {
		
		if(isAttacking && isAlive()) {
			
			super.getCurrentAnimation().draw(screenRelativeOverSizeX, screenRelativeOverSizeY);
			
		} else {
			
			super.getCurrentAnimation().draw(screenRelativeX, screenRelativeY);
			
		}
		
		if(super.getHealthBar().getCurrentValue() > 0) {
			super.getHealthBar().render(g);
		}
		
		if(super.isDrawBlood()) {
			super.drawBlood(screenRelativeX, screenRelativeY);
		}
		
		if(iceblocked) {
			iceblockAnimation.draw(screenRelativeX, screenRelativeY + 2);
		}
					
	}
	
	private void goToPlayer() {
		
		if(!isGoingToPlayer && aggressionCircle.contains(player.getCenterX(), player.getCenterY())) {
			isGoingToPlayer = true;
		}
	
		if(isGoingToPlayer) {
			path = findPath();
		}
		
		if(isGoingToPlayer && path != null && !path.isEmpty() && super.getCenterYTile() == path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol() && (Math.round(super.getCenterX())+16) % 32 == 0 && (Math.round(super.getCenterY())+16) % 32 == 0) {
			path.remove(0);
			
			if(!path.isEmpty()) {
			
				if(!goUp && super.getCenterYTile() > path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol()) {		
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
				
				if(!goDown && super.getCenterYTile() < path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol()) {	
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
							
				if(!goLeft && super.getCenterXTile() > path.get(0).getCol() && super.getCenterYTile() == path.get(0).getRow()) {	
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
				
				if(!goRight && super.getCenterXTile() < path.get(0).getCol() && super.getCenterYTile() == path.get(0).getRow()) {	
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
				
				if(!goUpLeft && super.getCenterYTile() > path.get(0).getRow() && super.getCenterXTile() > path.get(0).getCol()) {		
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
				
				if(!goUpRight && super.getCenterYTile() > path.get(0).getRow() && super.getCenterXTile() < path.get(0).getCol()) {		
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
				
				if(!goDownLeft && super.getCenterYTile() < path.get(0).getRow() && super.getCenterXTile() > path.get(0).getCol()) {		
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
				
				if(!goDownRight && super.getCenterYTile() < path.get(0).getRow() && super.getCenterXTile() < path.get(0).getCol()) {		
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
			
			if(goUp && !isUpCollision(super.getMovementSpeed())) {		
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoUpAnimation());
				isAttacking = false;
			}
			
			if(goDown && !isDownCollision(super.getMovementSpeed())) {	
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoDownAnimation());
				isAttacking = false;
			}
						
			if(goLeft && !isLeftCollision(super.getMovementSpeed())) {	
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
				isAttacking = false;
			}
			
			if(goRight && !isRightCollision(super.getMovementSpeed())) {	
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
				isAttacking = false;
			}
			
			if(goUpLeft && !isUpCollision(super.getMovementSpeed()) && !isLeftCollision(super.getMovementSpeed())) {
				
				if(!isUpCollision(super.getMovementSpeed())) {
					super.setRelativeToMapY(super.getRelativeToMapY() - super.getDiagonalMovementSpeed());
				}
				
				if(!isLeftCollision(super.getMovementSpeed())) {
					super.setRelativeToMapX(super.getRelativeToMapX() - super.getDiagonalMovementSpeed());
				}
				
				super.setCurrentAnimation(super.getGoLeftAnimation());
				isAttacking = false;
			}
			
			if(goUpRight && !isUpCollision(super.getMovementSpeed()) && !isRightCollision(super.getMovementSpeed())) {
				
				if(!isUpCollision(super.getMovementSpeed())) {
					super.setRelativeToMapY(super.getRelativeToMapY() - super.getDiagonalMovementSpeed());
				}
				
				if(!isRightCollision(super.getMovementSpeed())) {
					super.setRelativeToMapX(super.getRelativeToMapX() + super.getDiagonalMovementSpeed());
				}
				
				super.setCurrentAnimation(super.getGoRightAnimation());
				isAttacking = false;
			}
			
			if(goDownLeft && !isDownCollision(super.getMovementSpeed()) && !isLeftCollision(super.getMovementSpeed())) {
				
				if(!isDownCollision(super.getMovementSpeed())) {
					super.setRelativeToMapY(super.getRelativeToMapY() + super.getDiagonalMovementSpeed());
				}
				
				if(!isLeftCollision(super.getMovementSpeed())) {
					super.setRelativeToMapX(super.getRelativeToMapX() - super.getDiagonalMovementSpeed());
				}
				super.setCurrentAnimation(super.getGoLeftAnimation());
				isAttacking = false;
			}
			
			if(goDownRight && !isDownCollision(super.getMovementSpeed()) && !isRightCollision(super.getMovementSpeed())) {
				
				if(!isDownCollision(super.getMovementSpeed())) {
					super.setRelativeToMapY(super.getRelativeToMapY() + super.getDiagonalMovementSpeed());
				}
				
				if(!isRightCollision(super.getMovementSpeed())) {
					super.setRelativeToMapX(super.getRelativeToMapX() + super.getDiagonalMovementSpeed());
				}
				super.setCurrentAnimation(super.getGoRightAnimation());
				isAttacking = false;
			}
						
		}
		
		if(path != null && path.isEmpty()) {
			
			if(super.getCenterXTile() == player.getCenterXTile() && super.getCenterYTile() - 1 == player.getCenterYTile()) {
				super.setCurrentAnimation(super.getLookUpAnimation());
			}
			
			if(super.getCenterXTile() == player.getCenterXTile() && super.getCenterYTile() + 1 == player.getCenterYTile()) {
				super.setCurrentAnimation(super.getLookDownAnimation());
			}
			
			if(super.getCenterXTile() - 1 == player.getCenterXTile() && super.getCenterYTile() == player.getCenterYTile()) {
				super.setCurrentAnimation(super.getLookLeftAnimation());
			}
			
			if(super.getCenterXTile() + 1 == player.getCenterXTile() && super.getCenterYTile() == player.getCenterYTile()) {
				super.setCurrentAnimation(super.getLookRightAnimation());
			}
			
		}
		
	}
	
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
	
	private List<Node> findPath() {
		
		Node initialNode = new Node(super.getCenterYTile(), super.getCenterXTile());
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
        
        ArrayList<NPC> npcList = new ArrayList<NPC>(CharacterManager.getNpcList());
        npcList.remove(this);
        
        for(NPC npc : npcList) {
        	blocksArray[k][0] = npc.getCenterYTile();
			blocksArray[k][1] = npc.getCenterXTile();
			k++;
        }
        
        ArrayList<Wolf> wolfList = new ArrayList<Wolf>(WolfManager.getWolfList());
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
	
	private void checkIfIceblockIsOver() {

		if(System.currentTimeMillis() - iceblockedTimestamp > 10000) {
			iceblocked = false;
			getCurrentAnimation().start();
		}
		
	}
		
	private void checkIfBloodtheft() {

		if(bloodtheft && bloodtheftCounter < 10 && System.currentTimeMillis() - bloodtheftTimestamp >= 1000) {
			decreaseHealth(15);
			player.getHealthBar().setCurrentValue(player.getHealthBar().getCurrentValue() + 15);
			
			bloodtheftTimestamp = System.currentTimeMillis();
			bloodtheftCounter++;
			
			setDrawBlood(true);
				
		}
		
		if(bloodtheft && bloodtheftCounter >= 10) {
			bloodtheft = false;
			bloodtheftCounter = 0;
		}
		
	}
	
	public boolean isUpCollision(float distance) {
		
		if(super.getCollisionBox().willIntersectUp(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		npcList.remove(this);

		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectUp(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
				
		if(tiledMap.getTileId((int) super.getCollisionBox().getTopLeftX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getTopLeftY() - distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) super.getCollisionBox().getTopRightX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getTopRightY() - distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean isDownCollision(float distance) {
		
		if(super.getCollisionBox().willIntersectDown(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		npcList.remove(this);

		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectDown(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
				
		if(tiledMap.getTileId((int) super.getCollisionBox().getBottomLeftX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getBottomLeftY() + distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) super.getCollisionBox().getBottomRightX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getBottomRightY() + distance)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return false;
		} else {
			return true;
		}
		
		
	}
	
	public boolean isLeftCollision(float distance) {
		
		if(super.getCollisionBox().willIntersectLeft(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		npcList.remove(this);

		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectLeft(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
					
		if(tiledMap.getTileId((int) (super.getCollisionBox().getTopLeftX() - distance)/Main.TILE_SIZE, (int) super.getCollisionBox().getTopLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (super.getCollisionBox().getBottomLeftX() - distance)/Main.TILE_SIZE, (int) super.getCollisionBox().getBottomLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			return false;
		} else {
			return true;
		}
		
		
	}
	
	public boolean isRightCollision(float distance) {
		
		if(super.getCollisionBox().willIntersectRight(player.getCollisionBox(), 5)) {
			return true;
		}
		
		ArrayList<NPC> npcList = CharacterManager.getNpcList();
		npcList.remove(this);

		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectRight(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
		
		if(tiledMap.getTileId((int) (super.getCollisionBox().getTopRightX() + distance)/Main.TILE_SIZE, (int) super.getCollisionBox().getTopRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (super.getCollisionBox().getBottomRightX() + distance)/Main.TILE_SIZE, (int) super.getCollisionBox().getBottomRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return false;
		} else {
			return true;
		}
		
	}
				
	public void setGoingToPlayer(boolean isGoingToPlayer) {
		this.isGoingToPlayer = isGoingToPlayer;
	}
	
	
	private boolean isTouchingPlayer() {
		
		if(super.getCollisionBox().willIntersectUp(player.getCollisionBox(), 5)) {
			return true;
		}
		
		if(super.getCollisionBox().willIntersectDown(player.getCollisionBox(), 5)) {
			return true;
		}
		
		if(super.getCollisionBox().willIntersectLeft(player.getCollisionBox(), 5)) {
			return true;
		}
		
		if(super.getCollisionBox().willIntersectRight(player.getCollisionBox(), 5)) {
			return true;
		}
		
		return false;
		
	}
	
	public void decreaseHealth(int amount) {
		
		if(isAlive()) {
			
			getHealthBar().setCurrentValue(getHealthBar().getCurrentValue() - amount);
			
			if(getHealthBar().getCurrentValue() <= 0) {
				getHealthBar().setCurrentValue(0);
				setCurrentAnimation(getDieAnimation());
				setAlive(false);
				bloodtheft = false;
				bloodtheftCounter = 0;
				
				player.addExperience(experienceForPlayer);
								
				if(itemDrop != null) {
					CharacterManager.getPlayer().getInventoryWindow().addItem(itemDrop);
					CharacterManager.getPlayer().getNewItemWindow().showWindow(itemDrop);
				}
			}
						
			setDrawBlood(true);
		
		}
		
	}
	
	public boolean isHostileToPlayer() {
		return hostileToPlayer;
	}

	public ArrayList<Dialogue> getStartingDialogues() {
		return startingDialogues;
	}
	
	public int getExperienceForPlayer() {
		return experienceForPlayer;
	}

	public ItemType getEquippedMelee() {
		return equippedMelee;
	}

	public void setEquippedMelee(ItemType equippedMelee) {
		this.equippedMelee = equippedMelee;
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