package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main {

	private static final String TITLE = "Guardian";
	private static final int MENU_STATE_ID = 0;
	private static final int GAME_STATE_ID = 1;
	
	private static final int WIDTH = 512;
	private static final int HEIGHT = 512;
	
	private static final int FRAME_RATE = 60;
	
	private static final boolean FULL_SCREEN = false;
	
	public static void main(String[] args) {
		
		StateBasedGame stateBasedGame = new StateBasedGame(TITLE) {
			
			@Override
			public void initStatesList(GameContainer gameContainer) throws SlickException {
				
				this.getState(MENU_STATE_ID).init(gameContainer, this);
				this.getState(GAME_STATE_ID).init(gameContainer, this);
				this.enterState(GAME_STATE_ID);
				
			}
		};
		
		stateBasedGame.addState(new Menu(MENU_STATE_ID));
		stateBasedGame.addState(new Game(GAME_STATE_ID));
		
		try {
			
			AppGameContainer appGameContainer = new AppGameContainer(stateBasedGame);
			appGameContainer.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
			appGameContainer.setTargetFrameRate(FRAME_RATE);
			appGameContainer.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
}
