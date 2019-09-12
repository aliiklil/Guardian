package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.Main;
import manager.MobManager;
import models.Item;

public class NewItemWindow {

	private Image image = new Image("resources/gui/new_item_picked_up.png");
	private boolean isWindowOpen = false;
	
	private long startTime;
	private int duration = 1500;
	
	private Item currentItem;
	
	public NewItemWindow() throws SlickException {
		
	}
	
	public void showWindow(Item item) {
		
		currentItem = item; 
		isWindowOpen = true;
		startTime = System.currentTimeMillis();
		
	}
	
	public void update() throws SlickException {
		
		if(System.currentTimeMillis() - startTime >= duration || MobManager.getPlayer().getInventoryWindow().isWindowOpen() || MobManager.getPlayer().getDialogueWindow().isWindowOpen() || MobManager.getPlayer().getQuestLogWindow().isWindowOpen() || MobManager.getPlayer().getReadingWindow().isWindowOpen()) {
			isWindowOpen = false;
		}
		
	}
	
	public void render(Graphics g) {
		if(isWindowOpen) {
			g.drawImage(image, 0, 0);
			currentItem.getItemType().getInventoryAnimation().draw(459, 559);
			g.setColor(Color.black);
			
			String itemName = currentItem.getItemType().getName();
			g.drawString(itemName, Main.WIDTH/2 - (itemName.length() * 9)/2 + 48, 581);
		}
	}
}
