package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {

	private int playerSize = 64;
	
	private int x = Main.WIDTH / 2 - playerSize / 2 + Main.TILE_SIZE / 2;
	private int y = Main.HEIGHT / 2 - playerSize / 2;

	private int playerSpeed = 2;

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
	
	private boolean goUp = false;
	private boolean goDown = false;
	private boolean goLeft = false;
	private boolean goRight = false;
	
	private boolean lookUp = false;
	private boolean lookDown = false;
	private boolean lookLeft = false;
	private boolean lookRight = false;
	
	private boolean runMode = false;
	
	private final int framesPerTile = Main.TILE_SIZE / playerSpeed;
	
	private int framesPerTileLeft = framesPerTile;
	
	public Player () throws SlickException {

	}
	
	public void move() {
		
		Input input = Main.appGameContainer.getInput();
		
		if(!runMode){
		
			if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
				
				  goUp = true;
				  goDown = false;
				  goLeft = false;
				  goRight = false;
				
			}
			
			if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {

				  goUp = false;
				  goDown = true;
				  goLeft = false;
				  goRight = false;
				
			}
			
			if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {

				  goUp = false;
				  goDown = false;
				  goLeft = true;
				  goRight = false;
				
			}
	
			if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT)) {

				  goUp = false;
				  goDown = false;
				  goLeft = false;
				  goRight = true;
				
			}
		
		}
			
		if(goUp) {
			
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() + playerSpeed);
			playerCurrentAnimation = playerGoUpAnimation;
			lookUp = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
		
		if(goDown) {
				
			Game.getCurrentMap().setY(Game.getCurrentMap().getY() - playerSpeed);
			playerCurrentAnimation = playerGoDownAnimation;
			lookDown = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
		
		if(goLeft) {
			
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() + playerSpeed);
			playerCurrentAnimation = playerGoLeftAnimation;
			lookLeft = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
		
		if(goRight) {
		
			Game.getCurrentMap().setX(Game.getCurrentMap().getX() - playerSpeed);
			playerCurrentAnimation = playerGoRightAnimation;
			lookRight = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
				
		if(!input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) || !runMode) {
			
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
				
		if(framesPerTileLeft <= 0){
			
			framesPerTileLeft = framesPerTile;
			
			lookUp = goUp;
			lookDown = goDown;
			lookLeft = goLeft;
			lookRight = goRight;
			
			goUp = false;
			goDown = false;
			goLeft = false;
			goRight = false;
			
			runMode = false;
			
		}

		
	}
	
	public void render() {
		
		playerCurrentAnimation.draw(x, y);
		
	}
	
}