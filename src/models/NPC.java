package models;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

import dialogue.Dialogue;
import dialogue.Sentence;
import main.Game;
import main.Main;
import manager.CharacterManager;
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
	
	public NPC(float relativeToMapX, float relativeToMapY, int currentHealth, int maxHealth, String spriteSheetPath, boolean hostileToPlayer, Item itemDrop, ArrayList<Dialogue> startingDialogues) throws SlickException {

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
		
		super.setMovementSpeed(2f);
		super.setDiagonalMovementSpeed(1f);
		
		setAttackUpCollisionBox(new CollisionBox(getRelativeToMapX() - 28, getRelativeToMapY() - 37, 89, 38));
		setAttackDownCollisionBox(new CollisionBox(getRelativeToMapX() - 28, getRelativeToMapY() + 12, 89, 38));
		setAttackLeftCollisionBox(new CollisionBox(getRelativeToMapX() - 67, getRelativeToMapY() - 16, 68, 36));
		setAttackRightCollisionBox(new CollisionBox(getRelativeToMapX() + 31, getRelativeToMapY() - 16, 68, 36));
		
		this.hostileToPlayer = hostileToPlayer;
		
		this.itemDrop = itemDrop;
		
		this.startingDialogues = startingDialogues;

		
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
		
		if(isAlive() && hostileToPlayer) {
			goToPlayer();
			attackPlayer();
		}
				
		getAttackUpCollisionBox().setX(getRelativeToMapX() - 28);
		getAttackUpCollisionBox().setY(getRelativeToMapY() - 37);
		
		getAttackDownCollisionBox().setX(getRelativeToMapX() - 28);
		getAttackDownCollisionBox().setY(getRelativeToMapY() + 12);
		
		getAttackLeftCollisionBox().setX(getRelativeToMapX() - 67);
		getAttackLeftCollisionBox().setY(getRelativeToMapY() - 16);
		
		getAttackRightCollisionBox().setX(getRelativeToMapX() + 31);
		getAttackRightCollisionBox().setY(getRelativeToMapY() - 16);
	
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
					
	}
	
	private void goToPlayer() {
		
		if(!isGoingToPlayer && aggressionCircle.contains(player.getCenterX(), player.getCenterY())) {
			isGoingToPlayer = true;
		}
	
		if(isGoingToPlayer) {
			path = findPath();
		}
		
		if(isGoingToPlayer && path != null && !path.isEmpty() && super.getCenterYTile() == path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol() && (super.getCenterX()+16) % 32 == 0 && (super.getCenterY()+16) % 32 == 0) {
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
					|| super.getAttackUpCollisionBox().intersects(player.getHitBox()) || super.getAttackDownCollisionBox().intersects(player.getHitBox()) 
					|| super.getAttackLeftCollisionBox().intersects(player.getHitBox()) || super.getAttackRightCollisionBox().intersects(player.getHitBox())) {
				
				if((super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation()) && super.getAttackUpCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getAttackUpAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if((super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation()) && super.getAttackDownCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getAttackDownAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if((super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation()) && super.getAttackLeftCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getAttackLeftAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if((super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation()) && super.getAttackRightCollisionBox().intersects(player.getHitBox())) {
					super.setCurrentAnimation(super.getAttackRightAnimation());
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
			
			if(super.getCurrentAnimation() == super.getAttackUpAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(super.getAttackUpCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
				}
			}
			
			if(super.getCurrentAnimation() == super.getAttackDownAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(super.getAttackDownCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
					}
			}
			
			if(super.getCurrentAnimation() == super.getAttackLeftAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(super.getAttackLeftCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
				}		
			}
			
			if(super.getCurrentAnimation() == super.getAttackRightAnimation() && super.getCurrentAnimation().getFrame() == 1) {
					if(super.getAttackRightCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
					}						
			}		
			
		}
		
		if(!super.getAttackUpCollisionBox().intersects(player.getHitBox()) || !super.getAttackDownCollisionBox().intersects(player.getHitBox()) 
				|| !super.getAttackLeftCollisionBox().intersects(player.getHitBox()) || !super.getAttackRightCollisionBox().intersects(player.getHitBox())) {
			
			if(super.getCurrentAnimation() == super.getAttackUpAnimation() && !super.getAttackUpCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookUpAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackDownAnimation() && !super.getAttackDownCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookDownAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackLeftAnimation() && !super.getAttackLeftCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackRightAnimation() && !super.getAttackRightCollisionBox().intersects(player.getHitBox())) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookRightAnimation());
				isAttacking = false;
			}
			
		}
		
		if(!player.isAlive()) {
			
			if(super.getCurrentAnimation() == super.getAttackUpAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookUpAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackDownAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookDownAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackLeftAnimation()) {
				super.getCurrentAnimation().restart();
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackRightAnimation()) {
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
        
        ArrayList<NPC> npcList = CharacterManager.getNpcList();
        npcList.remove(this);
        
        for(NPC npc : npcList) {
        	blocksArray[k][0] = npc.getCenterYTile();
			blocksArray[k][1] = npc.getCenterXTile();
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
				
				if(itemDrop != null) {
					CharacterManager.getPlayer().getInventory().addItem(itemDrop);
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

}
