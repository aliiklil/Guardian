package models;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

	private final TiledMap tiledMap;
	
	private float x = 0;
	private float y = 0;
	
	public Map(String path) throws SlickException {
		
		tiledMap = new TiledMap(path);
		
	}
	
	public void render(Graphics g) {
				
		tiledMap.render((int) x, (int) y);
		
		g.setColor(Color.white);
		g.drawString("mapX:  " + x, 50, 150);
		g.drawString("mapY:  " + y, 50, 200);
		
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
