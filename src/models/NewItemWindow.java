package models;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.Main;
import manager.CharacterManager;

public class NewItemWindow {

	private Image image = new Image("resources/new_item_picked_up.png");
	private boolean active = false;
	
	private long startTime;
	private int duration = 1500;
	
	private Item currentItem;
	
	public NewItemWindow() throws SlickException {
		
	}
	
	public void showWindow(Item item) {
		
		currentItem = item; 
		active = true;
		startTime = System.currentTimeMillis();
		
	}
	
	public void update() throws SlickException {
		
		if(System.currentTimeMillis() - startTime >= duration || CharacterManager.getPlayer().getInventory().isInventoryOpen() || CharacterManager.getPlayer().getDialogueWindow().isActive()) {
			active = false;
		}
		
	}
	
	public void render(Graphics g) {
		if(active) {
			g.drawImage(image, 0, 0);
			currentItem.getItemType().getInventoryAnimation().draw(773, 823);
			g.setColor(Color.white);
			
			String itemName = currentItem.getItemType().getName();
			g.drawString(itemName, Main.WIDTH/2 - (itemName.length() * 9)/2 + 32, 844);
		}
	}
}
