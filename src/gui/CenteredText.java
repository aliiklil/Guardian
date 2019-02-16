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
		
	public CenteredText() throws SlickException {
		
	}
	
	public void showText(String text) {
		
		isDisplayed = true;
		startTime = System.currentTimeMillis();
		this.text = text;
		
	}
	
	public void update() throws SlickException {
		
		if(System.currentTimeMillis() - startTime >= duration || CharacterManager.getPlayer().getInventoryWindow().isWindowOpen() || CharacterManager.getPlayer().getDialogueWindow().isWindowOpen()) {
			isDisplayed = false;
		}
		
	}
	
	public void render(Graphics g) {
		if(isDisplayed) {
			g.setColor(Color.white);
			g.drawString(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
		}
	}
	
}
