package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class Player {
	
	private final int playerSize = 64;

	private int drawPositionX = Main.WIDTH / 2 - playerSize / 2;
	private int drawPositionY = Main.HEIGHT / 2 - playerSize / 2;
				
	private float collisionX = (drawPositionX + playerSize / 2) - Game.getCurrentMap().getX();
	private float collisionY = (drawPositionY + playerSize / 2 + playerSize / 4) - Game.getCurrentMap().getY(); 

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
		
		System.out.println("drawPositionX:" + drawPositionX);
		System.out.println("drawPositionY:" + drawPositionY);
		
		System.out.println("mapX:" + Game.getCurrentMap().getX());
		System.out.println("mapY:" + Game.getCurrentMap().getY());
		
		collisionX = (drawPositionX + playerSize / 2) - Game.getCurrentMap().getX();
		collisionY = (drawPositionY + playerSize / 2 + playerSize / 4) - Game.getCurrentMap().getY(); 
 		
		System.out.println("collisionX:" + collisionX);
		System.out.println("collisionY:" + collisionY);
				
		if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) && detectUpCollision()) {
					
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() + playerSpeed);
			playerCurrentAnimation = playerGoUpAnimation;
			
			lookUp = true;
			lookDown = false;
			lookLeft = false;
			lookRight = false;
				
		} else if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) && detectDownCollision()) {
				
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() - playerSpeed);
			playerCurrentAnimation = playerGoDownAnimation;
			
			lookUp = false;
			lookDown = true;
			lookLeft = false;
			lookRight = false;
			
		} else if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) && detectLeftCollision()) {
			
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() + playerSpeed);
			playerCurrentAnimation = playerGoLeftAnimation;
			
			lookUp = false;
			lookDown = false;
			lookLeft = true;
			lookRight = false;
			
		} else if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && detectRightCollision()) {
		
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() - playerSpeed);
			playerCurrentAnimation = playerGoRightAnimation;
			
			lookUp = false;
			lookDown = false;
			lookLeft = false;
			lookRight = true;
			
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) && detectUpCollision() && detectLeftCollision()) {
						
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() + diagonalPlayerSpeed);
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() + diagonalPlayerSpeed);
			playerCurrentAnimation = playerGoLeftAnimation;
			
			lookUp = false;
			lookDown = false;
			lookLeft = true;
			lookRight = false;
			
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && detectUpCollision() && detectRightCollision()) {
			
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() - diagonalPlayerSpeed);
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() + diagonalPlayerSpeed);
			playerCurrentAnimation = playerGoRightAnimation;
			
			lookUp = false;
			lookDown = false;
			lookLeft = false;
			lookRight = true;
			
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_RIGHT) && detectDownCollision() && detectLeftCollision()) {
			
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() + diagonalPlayerSpeed);
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() - diagonalPlayerSpeed);
			playerCurrentAnimation = playerGoLeftAnimation;
			
			lookUp = false;
			lookDown = false;
			lookLeft = true;
			lookRight = false;

		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && detectDownCollision() && detectRightCollision()) {
						
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() - diagonalPlayerSpeed);
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() - diagonalPlayerSpeed);
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
	
	public void render() {
		
		playerCurrentAnimation.draw(drawPositionX, drawPositionY);
		
		
		
	}
	
	private boolean detectUpCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) collisionX/Main.TILE_SIZE, (int) (collisionY - Main.TILE_SIZE/2)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	private boolean detectDownCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) collisionX/Main.TILE_SIZE, (int) (collisionY + Main.TILE_SIZE/2)/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	private boolean detectLeftCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) (collisionX - Main.TILE_SIZE/2)/Main.TILE_SIZE, (int) collisionY/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	private boolean detectRightCollision() {
		
		int notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		
		TiledMap tiledMap = Game.getCurrentMap().getTiledMap();
		
		if(tiledMap.getTileId((int) (collisionX + Main.TILE_SIZE/2)/Main.TILE_SIZE, (int) collisionY/Main.TILE_SIZE, notWalkableLayerIndex) == 0) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
}