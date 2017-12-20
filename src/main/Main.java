package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public static final String title = "Guardian";
	public static final int menu = 0;
	public static final int play = 1;
	
	public Main(String name) {
		super(name);
		this.addState(new Menu(menu));
		this.addState(new Play(play));

	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(menu).init(gameContainer, this);
		this.getState(play).init(gameContainer, this);
		this.enterState(menu);
	}

	public static void main(String[] args) {
		
		try {
			
			AppGameContainer appGameContainer = new AppGameContainer(new Main(title));
			appGameContainer.setDisplayMode(640, 360, false);
			appGameContainer.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
