package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	
	private static Map world;
	private static Map currentMap;
	private static Player player;
		
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		world = new Map("resources/World.tmx");
		currentMap = world;
		player = new Player();
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		player.move();
				
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render();
		player.render(g);

	}
	
	@Override
	public int getID() {

		return 0;
		
	}
	
	public static Map getCurrentMap() {
		
		return currentMap;
		
	}

}



