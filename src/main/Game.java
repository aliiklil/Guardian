package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGameState {
	
	private TiledMap tiledMap;
	
	private int mapX = 0;
	private int mapY = 0;
	
	private int playerSize = 64;
	
	private int playerSpeed = 2;
	
	private int playerX = Main.WIDTH / 2 - playerSize / 2 + 16;
	private int playerY = Main.HEIGHT / 2 - playerSize / 2;
	
	private SpriteSheet playerSpriteSheet;
	
	
	private Animation playerCurrentAnimation;
	
	private Animation playerLookUpAnimation;
	private Animation playerLookDownAnimation;
	private Animation playerLookLeftAnimation;
	private Animation playerLookRightAnimation;
	
	private Animation playerGoUpAnimation;
	private Animation playerGoDownAnimation;
	private Animation playerGoLeftAnimation;
	private Animation playerGoRightAnimation;

	private boolean goUp = false;
	private boolean goDown = false;
	private boolean goLeft = false;
	private boolean goRight = false;
	
	private boolean lookUp = false;
	private boolean lookDown = false;
	private boolean lookLeft = false;
	private boolean lookRight = false;
	
	private boolean runMode = false;
	
	// 32 is tile size
	private final int framesPerTile = 32 / playerSpeed;
	private int framesPerTileLeft = framesPerTile;
		
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		tiledMap = new TiledMap("resources/World.tmx");
		
		playerSpriteSheet = new SpriteSheet("resources/PlayerSpriteSheet.png", 64, 64);
				
		playerLookUpAnimation = new Animation(playerSpriteSheet, 0, 8, 0, 8, true, 100, true);
		playerLookDownAnimation = new Animation(playerSpriteSheet, 0, 10, 0, 10, true, 100, true);
		playerLookLeftAnimation = new Animation(playerSpriteSheet, 0, 9, 0, 9, true, 100, true);
		playerLookRightAnimation = new Animation(playerSpriteSheet, 0, 11, 0, 11, true, 100, true);
		
		playerGoUpAnimation = new Animation(playerSpriteSheet, 1, 8, 8, 8, true, 100, true);
		playerGoDownAnimation = new Animation(playerSpriteSheet, 1, 10, 8, 10, true, 100, true);
		playerGoLeftAnimation = new Animation(playerSpriteSheet, 1, 9, 8, 9, true, 100, true);
		playerGoRightAnimation = new Animation(playerSpriteSheet, 1, 11, 8, 11, true, 100, true);
		
		playerCurrentAnimation = playerLookDownAnimation;
		

	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int arg) throws SlickException {

		Input input = gameContainer.getInput();
		
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
			
			mapY = mapY + playerSpeed;
			playerCurrentAnimation = playerGoUpAnimation;
			lookUp = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
		
		if(goDown) {
				
			mapY = mapY - playerSpeed;
			playerCurrentAnimation = playerGoDownAnimation;
			lookDown = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
		
		if(goLeft) {
			
			mapX = mapX + playerSpeed;
			playerCurrentAnimation = playerGoLeftAnimation;
			lookLeft = true;
			runMode = true;
			framesPerTileLeft--;
			
		}
		
		if(goRight) {
		
			mapX = mapX - playerSpeed;
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

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		tiledMap.render(mapX, mapY);
		playerCurrentAnimation.draw(playerX, playerY);
	}
	
	@Override
	public int getID() {

		return 1;
	}

}



