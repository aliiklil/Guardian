package models;

import org.newdawn.slick.Color;
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
	
	//Between 0 and 32
	private float tileOffsetX;
	private float tileOffsetY;
	
	public Map(String path) throws SlickException {
		
		tiledMap = new TiledMap(path);
		
	}
	
	public void render(Graphics g) {
				
		g.setBackground(new Color(21, 120, 153));
		
		if(tileOffsetX <= 0) {
			offsetX = offsetX + 32;
			tileOffsetX = 32;
		} else if(tileOffsetX >= 32) {
			offsetX = offsetX - 32;
			tileOffsetX = 0;
		}
		
		if(tileOffsetY <= 0) {
			offsetY = offsetY + 32;
			tileOffsetY = 32;
		} else if(tileOffsetY >= 32) {
			offsetY = offsetY - 32;
			tileOffsetY = 0;
		}
		
		offsetXTile = Math.round(offsetX / 32);
		offsetYTile = Math.round(offsetY / 32);
		
		tiledMap.render(Math.round((x + offsetX - 32)), Math.round((y + offsetY - 32)), offsetXTile-1, offsetYTile-1, 42, 25);
		
	}
	 
	public void renderUpperLayer(Graphics g) {
		
		tiledMap.render(Math.round((x + offsetX - 32)), Math.round((y + offsetY - 32)), offsetXTile-1, offsetYTile-1, 42, 25, tiledMap.getLayerIndex("UpperLayer"), false);
		tiledMap.render(Math.round((x + offsetX - 32)), Math.round((y + offsetY - 32)), offsetXTile-1, offsetYTile-1, 42, 25, tiledMap.getLayerIndex("UpperLayer2"), false);
		tiledMap.render(Math.round((x + offsetX - 32)), Math.round((y + offsetY - 32)), offsetXTile-1, offsetYTile-1, 42, 25, tiledMap.getLayerIndex("UpperLayer3"), false);
		
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

	public float getTileOffsetX() {
		return tileOffsetX;
	}

	public void setTileOffsetX(float tileOffsetX) {
		this.tileOffsetX = tileOffsetX;
	}

	public float getTileOffsetY() {
		return tileOffsetY;
	}

	public void setTileOffsetY(float tileOffsetY) {
		this.tileOffsetY = tileOffsetY;
	}
	
}
