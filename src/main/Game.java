package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
	
	private Image playerImage;
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		tiledMap = new TiledMap("resources/World.tmx");
		playerImage = new Image("resources/PlayerSpriteSheet.png").getSubImage(0, 0, 64, 64);
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int arg) throws SlickException {

		Input input = gameContainer.getInput();
		
		if(input.isKeyDown(Input.KEY_W)) {
			mapY = mapY + 1;
		}
		
		if(input.isKeyDown(Input.KEY_S)) {
			mapY = mapY - 1;
		}
		
		if(input.isKeyDown(Input.KEY_A)) {
			mapX = mapX + 1;
		}

		if(input.isKeyDown(Input.KEY_D)) {
			mapX = mapX - 1;
		}
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		tiledMap.render(mapX, mapY);
		playerImage.draw(playerX, playerY);
	}
	
	@Override
	public int getID() {

		return 1;
	}

}