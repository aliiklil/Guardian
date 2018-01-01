package models;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.Main;

public class Inventory {

	private boolean inventoryOpen = false;
	private Image inventoryImage = new Image("resources/inventory.png");
	private Image selectedCellImage = new Image("resources/inventory_selected_cell.png");
	private int selectedCellX = 0;
	private int selectedCellY = 0;
	
	private int goldAmount = 0;
	
	private Input input = Main.appGameContainer.getInput();
	
	private ArrayList<Item> inventoryList = new ArrayList<Item>();
	
	public Inventory() throws SlickException {
		
	}
	
	public void update() throws SlickException {

		if(input.isKeyPressed(Input.KEY_TAB)) {
			
			if(!inventoryOpen) {
				
				inventoryOpen = true;
				
			} else {
				
				inventoryOpen = false;
				
			}
		}
		
		if(inventoryOpen) {
			
			if(input.isKeyPressed(Input.KEY_UP) && selectedCellY > 0) {
				selectedCellY--;
			}
			
			if(input.isKeyPressed(Input.KEY_DOWN) && selectedCellY < 5) {
				selectedCellY++;
			}
			
			if(input.isKeyPressed(Input.KEY_LEFT) && selectedCellX > 0) {
				selectedCellX--;
			}
			
			if(input.isKeyPressed(Input.KEY_RIGHT) && selectedCellX < 4) {
				selectedCellX++;
			}
		
		}
				
	}
	
	public void render(Graphics g) {
				
		if(inventoryOpen) {
			g.drawImage(inventoryImage, 0, 0);
			g.drawImage(selectedCellImage, 1484 + selectedCellX * 78, 305 + selectedCellY * 78);
			
			g.setColor(Color.white);
			g.drawString("Gold: ", 1733, 239);
			g.drawString(String.valueOf(goldAmount), 1853 - Integer.toString(goldAmount).length() * 7, 239);
			
			int i = 0;
			int j = 0;
			
			for(Item item : inventoryList) {
				
				item.getImage().draw(1492 + i * 78, 313 + j * 78, 2);
				i++;
				
				if(i >= 5) {
					i = 0;
					j++;
				}
				
			}
			
		}
							
	}
	
	public void addItem(Item item) {
		
		inventoryList.add(item);
		
	}
	
	public void removeItem(Item item) {
		
		inventoryList.add(item);
		
	}
	
	public boolean isInventoryOpen() {
		return inventoryOpen;
	}
	
}