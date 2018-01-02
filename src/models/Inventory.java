package models;

import java.util.ArrayList;

import org.newdawn.slick.AngelCodeFont;
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
	
	private int goldCounter = 0;
	
	private Input input = Main.appGameContainer.getInput();
	
	private ArrayList<Item> inventoryList = new ArrayList<Item>();
	private ArrayList<Integer> itemCountList = new ArrayList<Integer>();
	
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
			g.drawString(String.valueOf(goldCounter), 1853 - Integer.toString(goldCounter).length() * 10, 239);
			
			int row = 0;
			int column = 0;
			
			for(int i = 0; i < inventoryList.size(); i++) {
				
				inventoryList.get(i).getInventoryAnimation().draw(1492 + column * 78, 313 + row * 78);
				
				if(itemCountList.get(i) > 1) {
					g.drawString(itemCountList.get(i).toString(), 1550 + column * 78, 365 + row * 78);
				}
				
				column++;
				
				if(column >= 5) {
					column = 0;
					row++;
				}
				
			}
			
			if(inventoryList.size() > selectedCellX + selectedCellY * 5) {
				
				String name = inventoryList.get(selectedCellX + selectedCellY * 5).getName();
				g.drawString(name, Main.WIDTH/2 - (name.length() * 10)/2, 818);
				
				g.drawString("Value in Gold:", 652, 963);
				String value = String.valueOf(inventoryList.get(selectedCellX + selectedCellY * 5).getValue());
				g.drawString(value, 1098 - value.length() * 9, 963);
				
				inventoryList.get(selectedCellX + selectedCellY * 5).getDescriptionAnimation().draw(1126, 836);
				
			}
			
		}
							
	}
	
	public void addItem(Item item) {
		
		for(int i = 0; i < inventoryList.size(); i++) {
			if(item.getInventoryAnimation().getImage(0).getResourceReference().equals(inventoryList.get(i).getInventoryAnimation().getImage(0).getResourceReference())) {	
				itemCountList.set(i, itemCountList.get(i) + 1);
				return;
			}
		}
		
		itemCountList.add(1);
		inventoryList.add(item);
	
	}
	
	public void removeItem(Item item) {
		
		inventoryList.add(item);
		
	}
	
	public boolean isInventoryOpen() {
		return inventoryOpen;
	}
	
	public void incrementGoldCounter() {
		goldCounter++;
	}
	
}