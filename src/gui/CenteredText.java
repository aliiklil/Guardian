package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.Main;
import manager.CharacterManager;
import models.Item;

public class CenteredText {

	private boolean isDisplayed = false;
	
	private long startTime;
	private int duration = 2000;
	
	private String text;
	
	private int x;
	private int y;
		
	public CenteredText() throws SlickException {
		
	}
	
	public void showText(String text, int x, int y) {
		
		isDisplayed = true;
		startTime = System.currentTimeMillis();
		this.text = text;
		
		this.x = x;
		this.y = y;
		
	}
	
	public void update() throws SlickException {
		
		if(System.currentTimeMillis() - startTime >= duration) {
			isDisplayed = false;
		}
		
	}
	
	public void render(Graphics g) {
		if(isDisplayed) {
			g.setColor(Color.white);
			g.drawString(text, x, y);
		}
	}
	
}
