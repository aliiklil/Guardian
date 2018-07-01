package models;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

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
	private boolean pathCalculationNeeded = false;
	
	private List<Node> path;

	private Player player = CharacterManager.getPlayer();
	
	private float lastPlayerCenterXTile;
	private float lastPlayerCenterYTile;
	
	private int notWalkableLayerIndex;
	private TiledMap tiledMap;
	private ArrayList<NPC> npcList;
	
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

	public NPC(float relativeToMapX, float relativeToMapY, int currentHealth, int maxHealth, String spriteSheetPath) throws SlickException {

		super(relativeToMapX, relativeToMapY, spriteSheetPath);
		
		super.setCollisionBox(new CollisionBox(super.getRelativeToMapX() + 6, super.getRelativeToMapY() + 10, super.getSpriteSize()/2 - 12, super.getSpriteSize()/2 - 12));
		super.setHitBox(new CollisionBox(super.getRelativeToMapX(), super.getRelativeToMapY() - 10, super.getSpriteSize()/2, super.getSpriteSize()/2));
		
		super.setHealthBar(new Bar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, currentHealth, maxHealth, Color.red));

		this.screenRelativeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;
		this.screenRelativeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;

		screenRelativeOverSizeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getOverSizeSpriteSize() / 2 + 16;
		screenRelativeOverSizeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getOverSizeSpriteSize() / 2;
		
		aggressionCircle = new Circle(super.getCenterX(), super.getCenterY(), aggressionCircleRadius);
		
		lastPlayerCenterXTile = player.getCenterXTile();
		lastPlayerCenterYTile = player.getCenterYTile();
		
		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();
		npcList = CharacterManager.getNpcList();
		
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
		
		if(isAlive()) {
			updateGoToPlayer();
			//updateAttackPlayer();
		}
				
		super.getAttackUpCollisionBox().setX(super.getRelativeToMapX() - 28);
		super.getAttackUpCollisionBox().setY(super.getRelativeToMapY() - 37);
		
		super.getAttackDownCollisionBox().setX(super.getRelativeToMapX() - 28);
		super.getAttackDownCollisionBox().setY(super.getRelativeToMapY() + 12);
		
		super.getAttackLeftCollisionBox().setX(super.getRelativeToMapX() - 67);
		super.getAttackLeftCollisionBox().setY(super.getRelativeToMapY() - 16);
		
		super.getAttackRightCollisionBox().setX(super.getRelativeToMapX() + 31);
		super.getAttackRightCollisionBox().setY(super.getRelativeToMapY() - 16);
	
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
	
	private void updateGoToPlayer() {
				
		if(!isGoingToPlayer && aggressionCircle.contains(player.getCenterX(), player.getCenterY())) {
			isGoingToPlayer = true;
			pathCalculationNeeded = true;	
		}
		
		if(isGoingToPlayer && (player.getCenterXTile() != lastPlayerCenterXTile || player.getCenterYTile() != lastPlayerCenterYTile)) {
			pathCalculationNeeded = true;
			lastPlayerCenterXTile = player.getCenterXTile();
			lastPlayerCenterYTile = player.getCenterYTile();
		}
		
		if(pathCalculationNeeded && (super.getCenterX()+16) % 32 == 0 && (super.getCenterY()+16) % 32 == 0) {
			path = findPath();
			pathCalculationNeeded = false;
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
		
		if(isGoingToPlayer && path != null && !path.isEmpty()) {
			
			if(goUp && !isUpCollision()) {		
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoUpAnimation());
				isAttacking = false;
			}
			
			if(goDown && !isDownCollision()) {	
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoDownAnimation());
				isAttacking = false;
			}
						
			if(goLeft && !isLeftCollision()) {	
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
				isAttacking = false;
			}
			
			if(goRight && !isRightCollision()) {	
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
				isAttacking = false;
			}
			
			if(goUpLeft && !isUpCollision() && !isLeftCollision()) {		
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
				isAttacking = false;
			}
			
			if(goUpRight && !isUpCollision() && !isRightCollision()) {		
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
				isAttacking = false;
			}
			
			if(goDownLeft && !isDownCollision() && !isLeftCollision()) {		
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
				isAttacking = false;
			}
			
			if(goDownRight && !isDownCollision() && !isRightCollision()) {		
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
				isAttacking = false;
			}
						
		}
		
		if(path != null && path.isEmpty()) {
			
			if(super.getCurrentAnimation() == super.getGoUpAnimation()) {
				super.setCurrentAnimation(super.getLookUpAnimation());
			}
			
			if(super.getCurrentAnimation() == super.getGoDownAnimation()) {
				super.setCurrentAnimation(super.getLookDownAnimation());
			}
			
			if(super.getCurrentAnimation() == super.getGoLeftAnimation()) {
				super.setCurrentAnimation(super.getLookLeftAnimation());
			}
			
			if(super.getCurrentAnimation() == super.getGoRightAnimation()) {
				super.setCurrentAnimation(super.getLookRightAnimation());
			}
			
		}
		
	}
	
	private void updateAttackPlayer() {
		
		if(isGoingToPlayer && player.isAlive()) {
			if(isInAttackRange()) {
				if(super.getCurrentAnimation() == super.getLookUpAnimation() || super.getCurrentAnimation() == super.getGoUpAnimation()) {
					super.setCurrentAnimation(super.getAttackUpAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if(super.getCurrentAnimation() == super.getLookDownAnimation() || super.getCurrentAnimation() == super.getGoDownAnimation()) {
					super.setCurrentAnimation(super.getAttackDownAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if(super.getCurrentAnimation() == super.getLookLeftAnimation() || super.getCurrentAnimation() == super.getGoLeftAnimation()) {
					super.setCurrentAnimation(super.getAttackLeftAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if(super.getCurrentAnimation() == super.getLookRightAnimation() || super.getCurrentAnimation() == super.getGoRightAnimation()) {
					super.setCurrentAnimation(super.getAttackRightAnimation());
					isAttacking = true;
					damageDealt = false;
				}
				
				if(isAttacking && super.getCurrentAnimation().isStopped()) {
					super.getCurrentAnimation().restart();
					isAttacking = true;
					damageDealt = false;
				}
				
				isGoingToPlayer = false;
			}
		}
		
		if(!damageDealt && !player.isBlocking()) {
			
			if(super.getCurrentAnimation() == super.getAttackUpAnimation() && super.getCurrentAnimation().getFrame() == 3) {
					if(super.getAttackUpCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
				}
			}
			
			if(super.getCurrentAnimation() == super.getAttackDownAnimation() && super.getCurrentAnimation().getFrame() == 3) {
					if(super.getAttackDownCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
					}
			}
			
			if(super.getCurrentAnimation() == super.getAttackLeftAnimation() && super.getCurrentAnimation().getFrame() == 3) {
					if(super.getAttackLeftCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
				}		
			}
			
			if(super.getCurrentAnimation() == super.getAttackRightAnimation() && super.getCurrentAnimation().getFrame() == 3) {
					if(super.getAttackRightCollisionBox().intersects(player.getHitBox()) && player.isAlive()) {
						player.decreaseHealth(10);
						damageDealt = true;
					}						
			}		
			
		}
		
		if(!player.isAlive()) {
			
			if(super.getCurrentAnimation() == super.getAttackUpAnimation()) {
				super.setCurrentAnimation(super.getLookUpAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackDownAnimation()) {
				super.setCurrentAnimation(super.getLookDownAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackLeftAnimation()) {
				super.setCurrentAnimation(super.getLookLeftAnimation());
				isAttacking = false;
			}
			
			if(super.getCurrentAnimation() == super.getAttackRightAnimation()) {
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
        
        int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
        
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
                        
        aStar.setBlocks(blocksArray);
        
        List<Node> path = aStar.findPath();
        
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
                
        return path;
               		
	}
		
	private boolean isUpCollision() {
		
		if(super.getCollisionBox().willIntersectUp(player.getCollisionBox(), 5)) {
			return true;
		}
		
		npcList.remove(this);
		
		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectUp(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
				
		if(tiledMap.getTileId((int) super.getCollisionBox().getTopLeftX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getTopLeftY() - super.getMovementSpeed())/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) super.getCollisionBox().getTopRightX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getTopRightY() - super.getMovementSpeed())/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	private boolean isDownCollision() {
		
		if(super.getCollisionBox().willIntersectDown(player.getCollisionBox(), 5)) {
			return true;
		}
		
		npcList.remove(this);
		
		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectDown(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
				
		if(tiledMap.getTileId((int) super.getCollisionBox().getBottomLeftX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getBottomLeftY() + super.getMovementSpeed())/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) super.getCollisionBox().getBottomRightX()/Main.TILE_SIZE, (int) (super.getCollisionBox().getBottomRightY() + super.getMovementSpeed())/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
		
	}
	
	private boolean isLeftCollision() {
		
		if(super.getCollisionBox().willIntersectLeft(player.getCollisionBox(), 5)) {
			return true;
		}
		
		npcList.remove(this);
		
		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectLeft(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
					
		if(tiledMap.getTileId((int) (super.getCollisionBox().getTopLeftX() - super.getMovementSpeed())/Main.TILE_SIZE, (int) super.getCollisionBox().getTopLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (super.getCollisionBox().getBottomLeftX() - super.getMovementSpeed())/Main.TILE_SIZE, (int) super.getCollisionBox().getBottomLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			
			return false;
			
		} else {
			
			return true;
			
		}
		
		
	}
	
	private boolean isRightCollision() {
		
		if(super.getCollisionBox().willIntersectRight(player.getCollisionBox(), 5)) {
			return true;
		}
		
		npcList.remove(this);
		
		for(NPC npc : npcList) {
			
			if(super.getCollisionBox().willIntersectRight(npc.getCollisionBox(), 5) && npc.isAlive()) {
				return true;
			}
			
		}
		
		if(tiledMap.getTileId((int) (super.getCollisionBox().getTopRightX() + super.getMovementSpeed())/Main.TILE_SIZE, (int) super.getCollisionBox().getTopRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (super.getCollisionBox().getBottomRightX() + super.getMovementSpeed())/Main.TILE_SIZE, (int) super.getCollisionBox().getBottomRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	private boolean isInAttackRange() {
		
		if(super.getAttackUpCollisionBox().intersects((player.getCollisionBox()))) {
			return true;
		}
		
		if(super.getAttackDownCollisionBox().intersects((player.getCollisionBox()))) {
			return true;
		}
		
		if(super.getAttackLeftCollisionBox().intersects((player.getCollisionBox()))) {
			return true;
		}
		
		if(super.getAttackRightCollisionBox().intersects((player.getCollisionBox()))) {
			return true;
		}
		
		return false;
		
	}
	
	public void setPathCalculationNeeded(boolean pathCalculationNeeded) {
		this.pathCalculationNeeded = pathCalculationNeeded;
	}

					
}
