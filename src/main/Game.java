package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGameState {
	
	private TiledMap tiledMap;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {

		tiledMap = new TiledMap("resources/World.tmx");
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {

		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {

		tiledMap.render(0, 0);
		
	}
	
	@Override
	public int getID() {

		return 1;
	}

}