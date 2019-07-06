package gui;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Main;
import manager.CharacterManager;
import models.Item;
import models.Player;

public class TradingWindow {

	private boolean windowOpen = false;
	private Image inventoryImage = new Image("resources/trading_window.png");
	private Image selectedCellImage = new Image("resources/inventory_selected_cell.png");
	private Image equippedItemImage = new Image("resources/inventory_equipped_item.png");
	private int selectedCellX = 0;
	private int selectedCellY = 0;
	
	private final int amountRows = 6;
	private final int amountColumns = 5;
	private final int amountCells = 30;
	
	private int scrollOffset = 0;
	
	private Input input = Main.appGameContainer.getInput();

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
	
	private ArrayList<Item> playerInventoryList;
	private ArrayList<Integer> playerItemCountList;
	
	
	public TradingWindow() throws SlickException {	

	}
	
	public void update() throws SlickException {
		
		
		player = CharacterManager.getPlayer();
		
		playerInventoryList = player.getInventoryWindow().getInventoryList();
		playerItemCountList = player.getInventoryWindow().getItemCountList();
		
				
		if(windowOpen && input.isKeyPressed(Input.KEY_ESCAPE)) {
			
			windowOpen = false;
			CharacterManager.getPlayer().getDialogueWindow().setWindowOpen(true);
	
		}
			
		if(input.isKeyPressed(Input.KEY_UP) || holdUpKey && System.currentTimeMillis() - timestamp > 100) {
			
			if(scrollOffset > 0 && selectedCellY == 0) {
				scrollOffset--;
				timestamp = System.currentTimeMillis();
			} 

			if(selectedCellY > 0) {
				selectedCellY--;
				timestamp = System.currentTimeMillis();
			}
			
		}
					
		if(input.isKeyPressed(Input.KEY_DOWN) || holdDownKey && System.currentTimeMillis() - timestamp > 100) {
			
			if(selectedCellY == amountRows - 1 && playerInventoryList.size() > amountCells + scrollOffset * amountColumns) {
				
				if((selectedCellX + (selectedCellY + scrollOffset) * amountColumns + amountColumns + 1) > playerInventoryList.size()) {
					
					if(playerInventoryList.size() % amountColumns == 0) {
						selectedCellX = amountColumns - 1;
					} else {
						selectedCellX = playerInventoryList.size() % amountColumns - 1;
					}
					
				}

				scrollOffset++;
				timestamp = System.currentTimeMillis();
				
			}
			
			if(selectedCellY < amountRows - 1 && playerInventoryList.size() > selectedCellX + (selectedCellY + scrollOffset + 1) * amountColumns) {
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
			if(selectedCellX < amountColumns - 1 && playerInventoryList.size() > selectedCellX + (selectedCellY + scrollOffset) * amountColumns + 1) {
				selectedCellX++;
				timestamp = System.currentTimeMillis();
			}
		}
			
		checkIfKeyDown();

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
		
		if(windowOpen) {			
			g.drawImage(inventoryImage, 0, 0);
			g.drawImage(selectedCellImage, 1484 + selectedCellX * 78, 305 + selectedCellY * 78);
			
			g.setColor(Color.white);
			
			g.drawString("Gold: ", 1733, 239);
			g.drawString(String.valueOf(player.getInventoryWindow().getGoldCounter()), 1853 - Integer.toString(player.getInventoryWindow().getGoldCounter()).length() * 9, 239);
			
			int row = 0;
			int column = 0;
			
			for(int i = scrollOffset * amountColumns; i < playerInventoryList.size(); i++) {
				
				if(row >= amountRows) {
					break;
				}

				playerInventoryList.get(i).getItemType().getInventoryAnimation().draw(1492 + column * 78, 313 + row * 78);
				
				if(playerItemCountList.get(i) > 1) {
					g.drawString(playerItemCountList.get(i).toString(), 1550 + column * 78, 365 + row * 78);
				}
				
				if(playerInventoryList.get(i).isEquipped()) {
					g.drawImage(equippedItemImage, 1484 + column * 78, 305 + row * 78);
								
				}
						
				column++;
				
				if(column >= 5) {
					column = 0;
					row++;
				}
				
			}
			
			if(!playerInventoryList.isEmpty()) {
				
				String name = playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getName();
				g.drawString(name, Main.WIDTH/2 - (name.length() * 9)/2, 818);
				

				if(!playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getItemCategory().equals("spell")) {
				
					//Display minStrength, minDexterity if there is a requirement
					if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getMinStrength() > 0) {
						g.drawString("Needed Strength:", 652, 943);
						String minStrength = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getMinStrength());
						g.drawString(minStrength, 1098 - minStrength.length() * 9, 943);
					}
				
					if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getMinDexterity() > 0) {
						g.drawString("Needed Dexterity:", 652, 943);
						String minDexterity = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getMinDexterity());
						g.drawString(minDexterity, 1098 - minDexterity.length() * 9, 943);
					}
	
					//Display damage if the item has damage
					if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getDamage() > 0) {
						g.drawString("Damage:", 652, 923);
						String damage = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getDamage());
						g.drawString(damage, 1098 - damage.length() * 9, 923);
					}
				
				} else {
				
					if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getMinMagicKnowledge() > 0) {
						g.drawString("Needed Magic Knowledge:", 652, 923);
						String minMagicKnowledge = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getMinMagicKnowledge());
						g.drawString(minMagicKnowledge, 1098 - minMagicKnowledge.length() * 9, 923);
					}
					
					if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getManaCost() > 0) {
						g.drawString("Mana Cost:", 652, 943);
						String manaCost = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getManaCost());
						g.drawString(manaCost, 1098 - manaCost.length() * 9, 943);
					}
					
					//Display damage if the item has damage
					if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getDamage() > 0) {
						g.drawString("Damage:", 652, 903);
						String damage = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getDamage());
						g.drawString(damage, 1098 - damage.length() * 9, 903);
					}
				
				}
				
				
				//Display protection if the item has protection
				if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getProtection() > 0) {
					g.drawString("Protection:", 652, 943);
					String protection = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getProtection()) + "%";
					g.drawString(protection, 1098 - protection.length() * 9, 943);
				}
				
				
				
				if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getHealthBoost() > 0) {
					g.drawString("Health Boost:", 652, 943);
					String healthBoost = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getHealthBoost());
					g.drawString(healthBoost, 1098 - healthBoost.length() * 9, 943);
				}
				
				if(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getManaBoost() > 0) {
					g.drawString("Mana Boost:", 652, 943);
					String manaBoost = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getManaBoost());
					g.drawString(manaBoost, 1098 - manaBoost.length() * 9, 943);
				}


				g.drawString("Value in Gold:", 652, 963);
				String value = String.valueOf(playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getValue());
				g.drawString(value, 1098 - value.length() * 9, 963);
				
				playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getDescriptionAnimation().draw(1126, 836);
				
			}
			
			//drawArrow(g);
			
		}
							
	}
		
	public boolean isWindowOpen() {
		return windowOpen;
	}

	public void setWindowOpen(boolean windowOpen) {
		this.windowOpen = windowOpen;
	}

}