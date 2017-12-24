package main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

	private final TiledMap tiledMap;
	
	private float x = 0;
	private float y = 0;
	
	public Map(String path) throws SlickException {
		
		tiledMap = new TiledMap(path);
		
	}
	
	public void render() {
				
		tiledMap.render((int) x, (int) y);
		
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public float getX() {
		
		return x;
		
	}

	public void setX(float x) {
		
		this.x = x;
		
	}

	public float getY() {
		
		return y;
		
	}

	public void setY(float y) {
		
		this.y = y;
		
	}
	
}
