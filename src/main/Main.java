package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main {

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	public static final int TILE_SIZE = 32;
	
	private static final String TITLE = "Guardian";

	private static final int GAME_STATE_ID = 0;
	
	private static final int FRAME_RATE = 60;
	
	private static final boolean FULL_SCREEN = true;
	
	public static AppGameContainer appGameContainer;
	
	public static void main(String[] args) {
		
		StateBasedGame stateBasedGame = new StateBasedGame(TITLE) {
			
			@Override
			public void initStatesList(GameContainer gameContainer) throws SlickException {
				
				this.getState(GAME_STATE_ID).init(gameContainer, this);
				this.enterState(GAME_STATE_ID);
				
			}
			
		};
		
		stateBasedGame.addState(new Game());
		
		try {
			
			appGameContainer = new AppGameContainer(stateBasedGame);
			appGameContainer.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
			appGameContainer.setTargetFrameRate(FRAME_RATE);
			appGameContainer.setVSync(true);
			appGameContainer.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
}
