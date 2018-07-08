package models;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import main.Game;
import main.Main;
import manager.CharacterManager;

public class LootingAndVendorInventory {

	private boolean inventoryOpen = false;
	private Image inventoryImage = new Image("resources/looting_and_vendor_inventory.png");
	private Image selectedCellImage = new Image("resources/inventory_selected_cell.png");
	private int selectedCellX = 0;
	private int selectedCellY = 0;
	
	private final int amountRows = 6;
	private final int amountColumns = 5;
	private final int amountCells = 30;
	
	private int leftScrollOffset = 0;
	private int rightScrollOffset = 0;
	
	private int goldCounter = 0;
	
	private Input input = Main.appGameContainer.getInput();
	
	private ArrayList<Item> inventoryList = new ArrayList<Item>();
	private ArrayList<Integer> itemCountList = new ArrayList<Integer>();
	
	private long timestamp = 0;

	private boolean holdUpKey = false;
	private boolean holdDownKey = false;
	private boolean holdLeftKey = false;
	private boolean holdRightKey = false;
		
	private SpriteSheet arrowUpSpriteSheet = new SpriteSheet("resources/arrowUpImage.png", 44, 44);
	private SpriteSheet arrowDownSpriteSheet = new SpriteSheet("resources/arrowDownImage.png", 44, 44);
	
	private Animation arrowUpAnimation = new Animation(arrowUpSpriteSheet, 0, 0, 1, 0, true, 750, true);
	private Animation arrowDownAnimation = new Animation(arrowDownSpriteSheet, 0, 0, 1, 0, true, 750, true);
	
	private Player player;
	private TiledMap tiledMap;
	private int chestLayerIndex;
	
	public LootingAndVendorInventory() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = CharacterManager.getPlayer();
		tiledMap = Game.getTiledMap();
		chestLayerIndex = Game.getChestLayerIndex();
		
		if(player.getCurrentAnimation() == player.getLookUpAnimation() && input.isKeyPressed(Input.KEY_Y) && !player.getInventory().isInventoryOpen() && tiledMap.getTileId(player.getCenterXTile(), player.getCenterYTile() - 1, chestLayerIndex) != 0) {
			if(!inventoryOpen) {
				inventoryOpen = true;
			} else {
				inventoryOpen = false;
			}
		}		
		
		if(inventoryOpen) {
			
			if(input.isKeyPressed(Input.KEY_UP) || holdUpKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(rightScrollOffset > 0 && selectedCellY == 0) {
					rightScrollOffset--;
					timestamp = System.currentTimeMillis();
				} 

				if(selectedCellY > 0) {
					selectedCellY--;
					timestamp = System.currentTimeMillis();
				}
				
			}
						
			if(input.isKeyPressed(Input.KEY_DOWN) || holdDownKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(selectedCellY == amountRows - 1 && inventoryList.size() > amountCells + rightScrollOffset * amountColumns) {
					
					if((selectedCellX + (selectedCellY + rightScrollOffset) * amountColumns + amountColumns + 1) > inventoryList.size()) {
						
						if(inventoryList.size() % amountColumns == 0) {
							selectedCellX = amountColumns - 1;
						} else {
							selectedCellX = inventoryList.size() % amountColumns - 1;
						}
						
					}

					rightScrollOffset++;
					timestamp = System.currentTimeMillis();
					
				}
				
				if(selectedCellY < amountRows - 1 && inventoryList.size() > selectedCellX + (selectedCellY + rightScrollOffset + 1) * amountColumns) {
					selectedCellY++;
					timestamp = System.currentTimeMillis();
				}

			}
			
			if(input.isKeyPressed(Input.KEY_LEFT) || holdLeftKey && System.currentTimeMillis() - timestamp > 100) {
				if(selectedCellX > 0) {
					selectedCellX--;
					timestamp = System.currentTimeMillis();
				}
			}
			
			if(input.isKeyPressed(Input.KEY_RIGHT) || holdRightKey && System.currentTimeMillis() - timestamp > 100) {
				if(selectedCellX < amountColumns - 1 && inventoryList.size() > selectedCellX + (selectedCellY + rightScrollOffset) * amountColumns + 1) {
					selectedCellX++;
					timestamp = System.currentTimeMillis();
				}
			}
				
			checkIfKeyDown();

		}				
	}
		
	private void checkIfKeyDown() {

		if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
			if(System.currentTimeMillis() - timestamp > 300 && timestamp != 0) {
				holdUpKey = true;
				timestamp = System.currentTimeMillis();
			}
		} else {
			holdUpKey = false;
		}
		
		if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
			if(System.currentTimeMillis() - timestamp > 300 && timestamp != 0) {
				holdDownKey = true;
				timestamp = System.currentTimeMillis();
			}
		} else {
			holdDownKey = false;
		}
		
		if(input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT)) {
			if(System.currentTimeMillis() - timestamp > 300 && timestamp != 0) {
				holdLeftKey = true;
				timestamp = System.currentTimeMillis();
			}
		} else {
			holdLeftKey = false;
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_LEFT)) {
			if(System.currentTimeMillis() - timestamp > 300 && timestamp != 0) {
				holdRightKey = true;
				timestamp = System.currentTimeMillis();
			}
		} else {
			holdRightKey = false;
		}
		
	}
	
	public void render(Graphics g) {
				
		if(inventoryOpen) {
			g.drawImage(inventoryImage, 0, 0);
			g.drawImage(selectedCellImage, 1484 + selectedCellX * 78, 305 + selectedCellY * 78);
			
			g.setColor(Color.white);
			g.drawString("Gold: ", 1733, 239);
			g.drawString(String.valueOf(goldCounter), 1853 - Integer.toString(goldCounter).length() * 9, 239);
			
			int row = 0;
			int column = 0;
			
			for(int i = rightScrollOffset * amountColumns; i < inventoryList.size(); i++) {
				
				if(row >= amountRows) {
					break;
				}

				inventoryList.get(i).getItemType().getInventoryAnimation().draw(1492 + column * 78, 313 + row * 78);
				
				if(itemCountList.get(i) > 1) {
					g.drawString(itemCountList.get(i).toString(), 1550 + column * 78, 365 + row * 78);
				}
				
				column++;
				
				if(column >= 5) {
					column = 0;
					row++;
				}
				
			}
			
			if(!inventoryList.isEmpty()) {
				
				String name = inventoryList.get(selectedCellX + (selectedCellY + rightScrollOffset) * amountColumns).getItemType().getName();
				g.drawString(name, Main.WIDTH/2 - (name.length() * 9)/2, 818);
				
				g.drawString("Value in Gold:", 652, 963);
				String value = String.valueOf(inventoryList.get(selectedCellX + (selectedCellY + rightScrollOffset) * amountColumns).getItemType().getValue());
				g.drawString(value, 1098 - value.length() * 9, 963);
				
				inventoryList.get(selectedCellX + (selectedCellY + rightScrollOffset) * amountColumns).getItemType().getDescriptionAnimation().draw(1126, 836);
				
			}
			
			drawArrow(g);
			
		}
							
	}
	
	private void drawArrow(Graphics g) {
		
		arrowUpAnimation.updateNoDraw();
		arrowDownAnimation.updateNoDraw();
		
		if(rightScrollOffset > 0) { 
			arrowUpAnimation.draw(1876, 305);
		}
		
		if(inventoryList.size() > amountCells + rightScrollOffset * amountColumns) {
			arrowDownAnimation.draw(1876, 731);
		}
		
	}
	
	public void addItem(Item item) {
		
		for(int i = 0; i < inventoryList.size(); i++) {
			if(item.getItemType().getInventoryAnimation().getImage(0).getResourceReference().equals(inventoryList.get(i).getItemType().getInventoryAnimation().getImage(0).getResourceReference())) {	
				itemCountList.set(i, itemCountList.get(i) + 1);
				return;
			}
		}
		
		itemCountList.add(1);
		inventoryList.add(item);
		
		sortInventory();
		
	}
	
	private void sortInventory() {
		
		for (int i = 0; i < inventoryList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < inventoryList.size(); j++) {
				if (inventoryList.get(j).getItemType().getInventoryPriority() < inventoryList.get(index).getItemType().getInventoryPriority()) {
					index = j;
				}
			}
			Collections.swap(inventoryList, index, i);
			Collections.swap(itemCountList, index, i);
		}
	}
	
	public boolean isInventoryOpen() {
		return inventoryOpen;
	}
	
	public void incrementGoldCounter() {
		goldCounter++;
	}
	
}