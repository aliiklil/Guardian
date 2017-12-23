package main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

	private final TiledMap tiledMap;
	
	private int x = 0;
	private int y = 0;
	
	public Map(String path) throws SlickException {
		
		tiledMap = new TiledMap(path);
		
	}
	
	public void render() {
		
		tiledMap.render(x, y);
		
	}

	public int getX() {
		
		return x;
		
	}

	public void setX(int x) {
		
		this.x = x;
		
	}

	public int getY() {
		
		return y;
		
	}

	public void setY(int y) {
		
		this.y = y;
		
	}
	
}
