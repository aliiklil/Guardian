package models;

import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.Game;
import main.Main;
import manager.CharacterManager;
import util.CollisionBox;
import pathfinding.Node;
import pathfinding.AStar;

public class NPC extends Character {

	private float screenRelativeX;
	private float screenRelativeY;
	
	private Circle aggressionCircle;
	private int aggressionCircleRadius = 320;
	
	private boolean isGoingToPlayer = false;
	private boolean pathCalculationNeeded = false;
	
	private List<Node> path;
	
	private Player player = CharacterManager.getPlayer();
	
	private float lastPlayerPosX;
	private float lastPlayerPosY;

	public NPC(float relativeToMapX, float relativeToMapY, int currentHealth, int maxHealth, String spriteSheetPath) throws SlickException {

		super(relativeToMapX, relativeToMapY, spriteSheetPath);
		
		super.setCollisionBox(new CollisionBox(super.getRelativeToMapX() + 6, super.getRelativeToMapY() + 10, super.getSpriteSize()/2 - 12, super.getSpriteSize()/2 - 12));
		super.setHitBox(new CollisionBox(super.getRelativeToMapX(), super.getRelativeToMapY() - 10, super.getSpriteSize()/2, super.getSpriteSize()/2));
		
		super.setBar(new Bar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, currentHealth, maxHealth, Color.red));

		this.screenRelativeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;
		this.screenRelativeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;
		
		aggressionCircle = new Circle(super.getCenterX(), super.getCenterY(), aggressionCircleRadius);
		
		lastPlayerPosX = player.getRelativeToMapX();
		lastPlayerPosY = player.getRelativeToMapY();
		
	}

	public void update() throws SlickException {
		
		super.update();
		
		screenRelativeX = (int) Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;

		super.getBar().setX(screenRelativeX);
		super.getBar().setY(screenRelativeY);
		
		super.getCollisionBox().setX(super.getRelativeToMapX() + 6);
		super.getCollisionBox().setY(super.getRelativeToMapY() + 10);
		
		super.getHitBox().setX(super.getRelativeToMapX());
		super.getHitBox().setY(super.getRelativeToMapY() - 10);
		
		if(isAlive()) {
			//updateAttackPlayer();
		}
         		
	}
	
	public void updateAttackPlayer() {
		
		if(!isGoingToPlayer && aggressionCircle.contains(player.getCenterX(), player.getCenterY())) {
			
			isGoingToPlayer = true;
			pathCalculationNeeded = true;
						
		}
		
		if(isGoingToPlayer && (player.getRelativeToMapX() != lastPlayerPosX || player.getRelativeToMapY() != lastPlayerPosY)) {
			pathCalculationNeeded = true;
			lastPlayerPosX = player.getRelativeToMapX();
			lastPlayerPosY = player.getRelativeToMapY();
		}
		
		if(pathCalculationNeeded) {
			path = findPath();
			pathCalculationNeeded = false;
			
			path.remove(0);
			System.out.println("New PAth calc");
		}
		
		if(isGoingToPlayer && !path.isEmpty() && path != null) {
			System.out.println("getCenterYTile" + getCenterYTile());
			System.out.println("getCenterXTile" + getCenterXTile());
			System.out.println("row" + path.get(0).getRow());
			System.out.println("col" + path.get(0).getCol());
			if(super.getCenterYTile() > path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol()) {		
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoUpAnimation());
			}
			
			if(super.getCenterYTile() < path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol()) {
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoDownAnimation());
			}
						
			if(super.getCenterXTile() > path.get(0).getCol() && super.getCenterYTile() == path.get(0).getRow()) {
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
			}
			
			if(super.getCenterXTile() < path.get(0).getCol() && super.getCenterYTile() == path.get(0).getRow()) {
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
			}
			
			if(super.getCenterYTile() > path.get(0).getRow() && super.getCenterXTile() > path.get(0).getCol()) {
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
			}
			
			if(super.getCenterYTile() > path.get(0).getRow() && super.getCenterXTile() < path.get(0).getCol()) {
				super.setRelativeToMapY(super.getRelativeToMapY() - super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
			}
			
			if(super.getCenterYTile() < path.get(0).getRow() && super.getCenterXTile() > path.get(0).getCol()) {
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() - super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoLeftAnimation());
			}
			
			if(super.getCenterYTile() < path.get(0).getRow() && super.getCenterXTile() < path.get(0).getCol()) {
				super.setRelativeToMapY(super.getRelativeToMapY() + super.getDiagonalMovementSpeed());
				super.setRelativeToMapX(super.getRelativeToMapX() + super.getDiagonalMovementSpeed());
				super.setCurrentAnimation(super.getGoRightAnimation());
			}

			if(super.getCenterYTile() == path.get(0).getRow() && super.getCenterXTile() == path.get(0).getCol()) {
				path.remove(0);
			}
						
			if(path.size() == 0) {
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
        
        return aStar.findPath();
               		
	}
	
	public void render(Graphics g) {
		
		super.getCurrentAnimation().draw(screenRelativeX, screenRelativeY);

		if(super.getBar().getCurrentValue() > 0) {
			super.getBar().render(g);
		}
		
		if(super.isDrawBlood()) {
			super.drawBlood(screenRelativeX, screenRelativeY);
		}
				
	}
					
}
