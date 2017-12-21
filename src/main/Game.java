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
	
	private int playerX = Main.WIDTH / 2 - playerSize / 2;
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

	private boolean lookUp = false;
	private boolean lookDown = false;
	private boolean lookLeft = false;
	private boolean lookRight = false;
	
	private boolean moving = false;
		
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
		
		if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
			mapY = mapY + 1;
			playerCurrentAnimation = playerGoUpAnimation;
			lookUp = true;
			moving = true;
		}
		
		if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
			mapY = mapY - 1;
			playerCurrentAnimation = playerGoDownAnimation;
			lookDown = true;
			moving = true;
		}
		
		if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {
			mapX = mapX + 1;
			playerCurrentAnimation = playerGoLeftAnimation;
			lookLeft = true;
			moving = true;
		}

		if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT)) {
			mapX = mapX - 1;
			playerCurrentAnimation = playerGoRightAnimation;
			lookRight = true;
			moving = true;
		}
		
		if(!input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT) || !moving) {
			
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
		
		moving = false;
		
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



