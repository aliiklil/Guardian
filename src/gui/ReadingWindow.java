package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.SlickException;

import manager.MobManager;


public class ReadingWindow {

	private boolean windowOpen = false;
	private Image readableImage;
	
	public ReadingWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		if(MobManager.getPlayer().isEscapePressed() && windowOpen) {
			windowOpen = false;
			MobManager.getPlayer().getInventoryWindow().showWindow();
			readableImage = null;
		}
	}
	
	public void render(Graphics g) {
				
		if(windowOpen) {
			readableImage.draw();
		}
	}

	public void showWindow(Image readableImage) {
		windowOpen = true;
		this.readableImage = readableImage;
	}
	
	public boolean isWindowOpen() {
		return windowOpen;
	}
	
	
}