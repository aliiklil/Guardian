package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	private int x = 0;
	private int y = 0;
	private Image image;
	
	public Menu(int state) {
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		image = new Image ("C:/Users/All/Documents/Workspaces/WorkspaceJava/Guardian/resources/1.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {

		arg2.drawImage(image, x, y);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		
		if(input.isKeyDown(Input.KEY_UP))
			x++;
		
	}

	@Override
	public int getID() {

		return 0;
	}

}
