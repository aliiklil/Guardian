package models;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import main.Main;
import util.CollisionBox;
import pathfinding.Node;
import pathfinding.AStar;

public class NPC extends Character {

	private float screenRelativeX;
	private float screenRelativeY;

	public NPC(float relativeToMapX, float relativeToMapY, int currentHealth, int maxHealth, String spriteSheetPath) throws SlickException {

		super(relativeToMapX, relativeToMapY, spriteSheetPath);

		super.setCharacterCollisionBox(new CollisionBox(super.getRelativeToMapX(),  super.getRelativeToMapY(), super.getSpriteSize()/2, super.getSpriteSize()/2));

		super.setBar(new Bar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, currentHealth, maxHealth, Color.red));

		this.screenRelativeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;
		this.screenRelativeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;

	}

	public void update() {

		screenRelativeX = (int) Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;

		super.getBar().setX(screenRelativeX);
		super.getBar().setY(screenRelativeY);
		
		super.getCharacterCollisionBox().setX(super.getRelativeToMapX());
		super.getCharacterCollisionBox().setY(super.getRelativeToMapY());
		
		
		Node initialNode = new Node(5, 13);
        Node finalNode = new Node(6, 14);
        
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
                
        for (Node node : path) {
        	
            System.out.println(node);
            
        }
         		
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
