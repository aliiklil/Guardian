package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import util.CollisionBox;

public class Player {
	
	private final int playerSize = 64;

	private int drawPositionX = Main.WIDTH / 2 - playerSize / 2;
	private int drawPositionY = Main.HEIGHT / 2 - playerSize / 2;
					
	private float topLeftX = (drawPositionX + playerSize / 4) - Game.getCurrentMap().getX();
	private float topLeftY = (drawPositionY + playerSize / 2) - Game.getCurrentMap().getY(); 
	
	private CollisionBox collisionBox = new CollisionBox(topLeftX + 6, topLeftY + 16, playerSize/2 - 12, playerSize/2 - 18);

	private float playerSpeed = 1.5f;
	
	private float diagonalPlayerSpeed = (float) (1/Math.sqrt(Math.pow(playerSpeed, 2) + Math.pow(playerSpeed, 2))) * playerSpeed * playerSpeed;
	
	private SpriteSheet playerSpriteSheet = new SpriteSheet("resources/PlayerSpriteSheet.png", 64, 64);
	
	private Animation playerLookUpAnimation = new Animation(playerSpriteSheet, 0, 8, 0, 8, true, 100, true);
	private Animation playerLookDownAnimation = new Animation(playerSpriteSheet, 0, 10, 0, 10, true, 100, true);
	private Animation playerLookLeftAnimation = new Animation(playerSpriteSheet, 0, 9, 0, 9, true, 100, true);
	private Animation playerLookRightAnimation = new Animation(playerSpriteSheet, 0, 11, 0, 11, true, 100, true);
	
	private Animation playerGoUpAnimation = new Animation(playerSpriteSheet, 1, 8, 8, 8, true, 100, true);
	private Animation playerGoDownAnimation = new Animation(playerSpriteSheet, 1, 10, 8, 10, true, 100, true);
	private Animation playerGoLeftAnimation = new Animation(playerSpriteSheet, 1, 9, 8, 9, true, 100, true);
	private Animation playerGoRightAnimation = new Animation(playerSpriteSheet, 1, 11, 8, 11, true, 100, true);

	private Animation playerCurrentAnimation = playerLookDownAnimation;
		
	private boolean lookUp = false;
	private boolean lookDown = false;
	private boolean lookLeft = false;
	private boolean lookRight = false;
	
	public Player () throws SlickException {

	}
	
	public void move() {
								
		Input input = Main.appGameContainer.getInput();
		
		collisionBox.setX((drawPositionX + playerSize / 4) - Game.getCurrentMap().getX() + 6);
		collisionBox.setY((drawPositionY + playerSize / 2) - Game.getCurrentMap().getY() + 16);
				
		if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
			
			if(isUpCollision()) {
					
				playerCurrentAnimation = playerLookUpAnimation;
				
			} else {
				
				Game.getCurrentMap().setY(Game.getCurrentMap().getY() + playerSpeed);
				playerCurrentAnimation = playerGoUpAnimation;
			
				lookUp = true;
				lookDown = false;
				lookLeft = false;
				lookRight = false;
				
			}
			

		} else if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
				
			
			if(isDownCollision()) {
				
				playerCurrentAnimation = playerLookDownAnimation;
				
			} else {
			
				Game.getCurrentMap().setY(Game.getCurrentMap().getY() - playerSpeed);
				playerCurrentAnimation = playerGoDownAnimation;
				
				lookUp = false;
				lookDown = true;
				lookLeft = false;
				lookRight = false;
			
			}
			
		} else if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {
			
			if(isLeftCollision()) {
				
				playerCurrentAnimation = playerLookLeftAnimation;
				
			} else {
			
				Game.getCurrentMap().setX(Game.getCurrentMap().getX() + playerSpeed);
				playerCurrentAnimation = playerGoLeftAnimation;
				
				lookUp = false;
				lookDown = false;
				lookLeft = true;
				lookRight = false;
			
			}
			
		} else if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT)) {
		
			if(isRightCollision()) {
				
				playerCurrentAnimation = playerLookRightAnimation;
				
			} else {
			
				Game.getCurrentMap().setX(Game.getCurrentMap().getX() - playerSpeed);
				playerCurrentAnimation = playerGoRightAnimation;
				
				lookUp = false;
				lookDown = false;
				lookLeft = false;
				lookRight = true;
			
			}
			
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {
			
			if(!isUpCollision()) {
				Game.getCurrentMap().setY(Game.getCurrentMap().getY() + diagonalPlayerSpeed);
			}
			
			if(!isLeftCollision()) {
				Game.getCurrentMap().setX(Game.getCurrentMap().getX() + diagonalPlayerSpeed);
			}
			
			playerCurrentAnimation = playerGoLeftAnimation;
							
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
			
			playerCurrentAnimation = playerGoRightAnimation;
			
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
			
			playerCurrentAnimation = playerGoLeftAnimation;
						
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
			
			playerCurrentAnimation = playerGoRightAnimation;
						
			lookUp = false;
			lookDown = false;
			lookLeft = false;
			lookRight = true;
	
		} else {
			
			if(lookUp)
				playerCurrentAnimation = playerLookUpAnimation;
			
			if(lookDown)
				playerCurrentAnimation = playerLookDownAnimation;	
			
			if(lookLeft)
				playerCurrentAnimation = playerLookLeftAnimation;
			
			if(lookRight)
				playerCurrentAnimation = playerLookRightAnimation;

			lookUp = false;
			lookDown = false;
			lookLeft = false;
			lookRight = false;
			
		}
							
	}
	
	public void render(Graphics g) {
		
		playerCurrentAnimation.draw(drawPositionX, drawPositionY);
		
	}
	
	private boolean isUpCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) collisionBox.getTopLeftX()/Main.TILE_SIZE, (int) collisionBox.getTopLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) collisionBox.getTopRightX()/Main.TILE_SIZE, (int) (collisionBox.getTopRightY() - playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	private boolean isDownCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) collisionBox.getBottomLeftX()/Main.TILE_SIZE, (int) (collisionBox.getBottomLeftY() + playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) collisionBox.getBottomRightX()/Main.TILE_SIZE, (int) (collisionBox.getBottomRightY() + playerSpeed)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
		
	}
	
	private boolean isLeftCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) (collisionBox.getTopLeftX() - playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getTopLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (collisionBox.getBottomLeftX() - playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getBottomLeftY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {	
			
			return false;
			
		} else {
			
			return true;
			
		}
		
		
	}
	
	private boolean isRightCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) (collisionBox.getTopRightX() + playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getTopRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0 &&
		   tiledMap.getTileId((int) (collisionBox.getBottomRightX() + playerSpeed)/Main.TILE_SIZE, (int) collisionBox.getBottomRightY()/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}