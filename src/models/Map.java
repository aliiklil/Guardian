package models;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import main.Main;
import manager.MobManager;

public class Map {

	private final TiledMap tiledMap;
	
	private float x = 0;
	private float y = 0;
	
	private int offsetXTile;
	private int offsetYTile;
	
	private float offsetX;
	private float offsetY;
	
	public Map(String path) throws SlickException {
		
		tiledMap = new TiledMap(path);
		
	}
	
	public void render(Graphics g) {
		
		offsetXTile = (int) (offsetX / 32);
		offsetYTile = (int) (offsetY / 32);

		System.out.println(offsetXTile);
		System.out.println(offsetYTile);
		
		tiledMap.render((int) (x + offsetX), (int) (y + offsetY), offsetXTile, offsetYTile, 41,  24);
	
		
		
	}
	 
	public void renderUpperLayer(Graphics g) {
		
		//tiledMap.render((int) x, (int) y, relativeToMapX - Main.WIDTH, relativeToMapY - Main.HEIGHT, 50, 50, tiledMap.getLayerIndex("UpperLayer"), false);
		//tiledMap.render((int) x, (int) y, relativeToMapX - Main.WIDTH, relativeToMapY - Main.HEIGHT, 50, 50, tiledMap.getLayerIndex("UpperLayer2"), false);
		//tiledMap.render((int) x, (int) y, relativeToMapX - Main.WIDTH, relativeToMapY - Main.HEIGHT, 50, 50, tiledMap.getLayerIndex("UpperLayer3"), false);
		
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

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

	public int getOffsetXTile() {
		return offsetXTile;
	}

	public void setOffsetXTile(int offsetXTile) {
		this.offsetXTile = offsetXTile;
	}

	public int getOffsetYTile() {
		return offsetYTile;
	}

	public void setOffsetYTile(int offsetYTile) {
		this.offsetYTile = offsetYTile;
	}
	
}
