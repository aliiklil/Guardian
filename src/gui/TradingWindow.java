package gui;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import main.Main;
import manager.ItemTypeManager;
import manager.MobManager;
import models.Item;
import models.NPC;
import models.Player;

public class TradingWindow {

	private boolean windowOpen = false;
	private Image inventoryImage = new Image("resources/gui/trading_window.png");
	private Image selectedCellImage = new Image("resources/gui/inventory_selected_cell.png");
	private Image equippedItemImage = new Image("resources/gui/inventory_equipped_item.png");
	
	private int playerSelectedCellX = 0;
	private int playerSelectedCellY = 0;
	private int playerScrollOffset = 0;
	
	private int npcSelectedCellX = 0;
	private int npcSelectedCellY = 0;
	private int npcScrollOffset = 0;
	
	//If cursor is in player or npc inventory
	private boolean playerInventoryActive = false; 
	private boolean npcInventoryActive = true;
	
	private final int amountRows = 6;
	private final int amountColumns = 5;
	private final int amountCells = 30;
	
	private Input input = Main.appGameContainer.getInput();

	private long timestamp = 0; //Needed otherwise the centeredText is displayed immediately, that player has not enough gold because Y is pressed

	private boolean holdUpKey = false;
	private boolean holdDownKey = false;
	private boolean holdLeftKey = false;
	private boolean holdRightKey = false;
		
	private SpriteSheet arrowUpSpriteSheet = new SpriteSheet("resources/gui/arrowUpImage.png", 22, 22);
	private SpriteSheet arrowDownSpriteSheet = new SpriteSheet("resources/gui/arrowDownImage.png", 22, 22);
	
	private Animation arrowUpAnimation = new Animation(arrowUpSpriteSheet, 0, 0, 1, 0, true, 750, true);
	private Animation arrowDownAnimation = new Animation(arrowDownSpriteSheet, 0, 0, 1, 0, true, 750, true);
	
	private Player player;
	
	private NPC npc; //NPC which the player is currently trading with or last NPC the player traded with
	
	private ArrayList<Item> playerInventoryList;
	private ArrayList<Integer> playerItemCountList;
	
	private boolean cursorMoved = false; //Needed otherwise the centeredText, which says "You need ... gold" will be displayed immediately when opening the trading window
	
	public TradingWindow() throws SlickException {	

	}
	
	public void update() throws SlickException {
		
		player = MobManager.getPlayer();
		
		playerInventoryList = player.getInventoryList();
		playerItemCountList = player.getItemCountList();
		
		if(windowOpen) {
			
			if(player.isEscapePressed()) {
				
				windowOpen = false;
				MobManager.getPlayer().getDialogueWindow().setWindowOpen(true);
						
			}
			
			if(playerInventoryActive && player.isYPressed() && playerInventoryList.size() > 0 && playerInventoryList.size() > (playerSelectedCellY + playerScrollOffset) * amountColumns + playerSelectedCellX) {
				
				Item selectedItem = playerInventoryList.get((playerSelectedCellY + playerScrollOffset) * amountColumns + playerSelectedCellX);
				
				if(!selectedItem.getItemType().getItemCategory().equals("gold")) {
				
					if(!selectedItem.getItemType().getItemCategory().equals("quest_item")) {
					
						if(selectedItem.isEquipped()) {
							selectedItem.setEquipped(false);
							
							if(selectedItem.getItemType().getItemCategory().equals("melee_slay") || selectedItem.getItemType().getItemCategory().equals("melee_thrust")) {
								player.setEquippedMelee(null);
								player.setCurrentMeleeAnimation(null);
							} else if(selectedItem.getItemType().getItemCategory().equals("bow")) {
								player.setEquippedBow(null);
								player.setCurrentBowAnimation(null);
							} else if(selectedItem.getItemType().getItemCategory().equals("spell")) {
								player.setEquippedSpell(null);
								player.setCurrentSpellAnimation(null);
							} else if(selectedItem.getItemType().getItemCategory().equals("armor")) {
								player.setEquippedArmor(null);
								player.setCurrentArmorAnimation(null);
							} 
							
						}
						
						removeSelectedPlayerItem();
						
						for(int i = 0; i < selectedItem.getItemType().getValue(); i++) {
							player.getInventoryWindow().addItem(new Item(0, 0, Game.getItemTypeManager().gold));
							player.getInventoryWindow().incrementGoldCounter();
						}
						
						npc.addItem(selectedItem);
					
					} else {
						
						String text = "I need this item for a quest";
						player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
						
					}
				
				}
				
			}
			
			if(npcInventoryActive && player.isYPressed() && npc.getInventoryList().size() > 0) {
				
				Item selectedItem = npc.getInventoryList().get((npcSelectedCellY + npcScrollOffset) * amountColumns + npcSelectedCellX);
				
				
				if(player.getInventoryWindow().getGoldCounter() >= selectedItem.getItemType().getBuyValue()) {
					removeSelectedNPCItem();
					player.getInventoryWindow().addItem(selectedItem);
					
					for(int i = 0; i < selectedItem.getItemType().getBuyValue(); i++) {
						player.getInventoryWindow().removeItem(Game.getItemTypeManager().gold);
						player.getInventoryWindow().decrementGoldCounter();
					}
					
				} else {
					
					if(System.currentTimeMillis() - timestamp > 100) {
						String text = "You need " + selectedItem.getItemType().getBuyValue() + " gold";
						player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					}
					
				}
			}
		}
		
		//Player inventory controls
		if(playerInventoryActive) {
			
			if(player.isKeyUpPressed() || holdUpKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(playerScrollOffset > 0 && playerSelectedCellY == 0) {
					playerScrollOffset--;
					timestamp = System.currentTimeMillis();
				} 
	
				if(playerSelectedCellY > 0) {
					playerSelectedCellY--;
					timestamp = System.currentTimeMillis();
				}
				
			}
						
			if(player.isKeyDownPressed() || holdDownKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(playerSelectedCellY == amountRows - 1 && playerInventoryList.size() > amountCells + playerScrollOffset * amountColumns) {
					
					if((playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns + amountColumns + 1) > playerInventoryList.size()) {
						
						if(playerInventoryList.size() % amountColumns == 0) {
							playerSelectedCellX = amountColumns - 1;
						} else {
							playerSelectedCellX = playerInventoryList.size() % amountColumns - 1;
						}
						
					}
	
					playerScrollOffset++;
					timestamp = System.currentTimeMillis();
					
				}
				
				if(playerSelectedCellY < amountRows - 1 && playerInventoryList.size() > playerSelectedCellX + (playerSelectedCellY + playerScrollOffset + 1) * amountColumns) {
					playerSelectedCellY++;
					timestamp = System.currentTimeMillis();
				}
	
			}
			
			if(player.isKeyLeftPressed() || holdLeftKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(playerSelectedCellX == 0 && playerInventoryActive) {
					npcInventoryActive = true;
					playerInventoryActive = false;
					
					
					
					npcSelectedCellY = playerSelectedCellY;
					npcSelectedCellX = 4;
					
					
					if((npc.getInventoryList().size() - 1) / amountColumns <= playerSelectedCellY) {
						
						npcSelectedCellY = (npc.getInventoryList().size() - 1) / amountColumns;
						npcSelectedCellX = npc.getInventoryList().size() % amountColumns - 1;
						
						if(npcSelectedCellX == -1) {
							npcSelectedCellX = 4;
						}

					}
					
					timestamp = System.currentTimeMillis();
					
				}
				
				if(playerSelectedCellX > 0) {
					playerSelectedCellX--;
					timestamp = System.currentTimeMillis();
				}
				
				player.setKeyLeftPressed(false);
		
			}
			
			if(player.isKeyRightPressed() || holdRightKey && System.currentTimeMillis() - timestamp > 100) {
				if(playerSelectedCellX < amountColumns - 1 && playerInventoryList.size() > playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns + 1) {
					playerSelectedCellX++;
					timestamp = System.currentTimeMillis();
				}
			}
				
			checkIfKeyDown();
			
		
		}
		
		
		
		if(npcInventoryActive) {
		
			//NPC inventory controls
			if(player.isKeyUpPressed() || holdUpKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(npcScrollOffset > 0 && npcSelectedCellY == 0) {
					npcScrollOffset--;
					timestamp = System.currentTimeMillis();
				} 
	
				if(npcSelectedCellY > 0) {
					npcSelectedCellY--;
					timestamp = System.currentTimeMillis();
				}
				
			}
						
			if(player.isKeyDownPressed() || holdDownKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(npcSelectedCellY == amountRows - 1 && npc.getInventoryList().size() > amountCells + npcScrollOffset * amountColumns) {
					
					if((npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns + amountColumns + 1) > npc.getInventoryList().size()) {
						
						if(npc.getInventoryList().size() % amountColumns == 0) {
							npcSelectedCellX = amountColumns - 1;
						} else {
							npcSelectedCellX = npc.getInventoryList().size() % amountColumns - 1;
						}
						
					}
	
					npcScrollOffset++;
					timestamp = System.currentTimeMillis();
					
				}
				
				if(npcSelectedCellY < amountRows - 1 && npc != null && npc.getInventoryList().size() > npcSelectedCellX + (npcSelectedCellY + npcScrollOffset + 1) * amountColumns) {
					npcSelectedCellY++;
					timestamp = System.currentTimeMillis();
				}
	
			}
			
			if(player.isKeyLeftPressed() && System.currentTimeMillis() - timestamp > 100 || holdLeftKey && System.currentTimeMillis() - timestamp > 100) {
				if(npcSelectedCellX > 0) {
					npcSelectedCellX--;
					timestamp = System.currentTimeMillis();
				}
				
			}
			
			if(player.isKeyRightPressed() || holdRightKey && System.currentTimeMillis() - timestamp > 100) {
				
				if((npcSelectedCellX == 4 || npc != null && npcSelectedCellX == npc.getInventoryList().size() % amountColumns - 1 && npcSelectedCellY == npc.getInventoryList().size() / amountColumns)  && npcInventoryActive) {
					npcInventoryActive = false;
					playerInventoryActive = true;
					
					
					playerSelectedCellY = npcSelectedCellY;
					playerSelectedCellX = 0;
					
					if(playerInventoryList.size() < playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns - 1) {
						playerSelectedCellY = playerInventoryList.size() / amountColumns;
					}
					
					timestamp = System.currentTimeMillis();
				}
				
				if(npcSelectedCellX < amountColumns - 1 && npc != null && npc.getInventoryList().size() > npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns + 1) {
					npcSelectedCellX++;
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
		
		if(windowOpen) {
			
			drawPlayerInventory(g);
			drawNPCInventory(g);

			drawItemDescription(g);
			
			drawArrowForPlayerInventory(g);
			drawArrowForNPCInventory(g);

		}
							
	}
	
	private void drawPlayerInventory(Graphics g) {
		
		//Draw inventory of player
		g.drawImage(inventoryImage, 0, 0);
		
		if(playerInventoryActive) {
			g.drawImage(selectedCellImage, 863 + playerSelectedCellX * 78, 25 + playerSelectedCellY * 78);	
		}
		
		g.setColor(Color.black);
		
		g.drawString("Gold: ", 722, 37);
		g.drawString(String.valueOf(player.getInventoryWindow().getGoldCounter()), 834 - Integer.toString(player.getInventoryWindow().getGoldCounter()).length() * 9, 37);
		
		int row = 0;
		int column = 0;
		
		for(int i = playerScrollOffset * amountColumns; i < playerInventoryList.size(); i++) {
			
			if(row >= amountRows) {
				break;
			}

			playerInventoryList.get(i).getItemType().getInventoryAnimation().draw(873 + column * 78, 35 + row * 78);
			
			if(playerItemCountList.get(i) > 1) {
				g.drawString(playerItemCountList.get(i).toString(), 938 + column * 78 - Integer.toString(playerItemCountList.get(i)).length() * 9, 85 + row * 78);
			}
			
			if(playerInventoryList.get(i).isEquipped()) {
				g.drawImage(equippedItemImage, 864 + column * 78, 26 + row * 78);
							
			}
					
			column++;
			
			if(column >= 5) {
				column = 0;
				row++;
			}
			
		}
		
	}
	
	private void drawNPCInventory(Graphics g) {
		
		if(npcInventoryActive) {
			g.drawImage(selectedCellImage, 24 + npcSelectedCellX * 78, 25 + npcSelectedCellY * 78);	
		}
		
		//Draw inventory of NPC		
		int row = 0;
		int column = 0;
		
		for(int i = npcScrollOffset * amountColumns; i < npc.getInventoryList().size(); i++) {
			
			if(row >= amountRows) {
				break;
			}

			npc.getInventoryList().get(i).getItemType().getInventoryAnimation().draw(33 + column * 78, 34 + row * 78);
			
			if(npc.getItemCountList().get(i) > 1) {
				g.drawString(npc.getItemCountList().get(i).toString(), 90 + column * 78, 85 + row * 78);
			}
			
			if(npc.getInventoryList().get(i).isEquipped()) {
				g.drawImage(equippedItemImage, 33 + column * 78, 34 + row * 78);
							
			}
					
			column++;
			
			if(column >= 5) {
				column = 0;
				row++;
			}
			
		}
		
	}
	
	private void drawItemDescription(Graphics g) {
		
		//Draw item description if cursor is on the on the right side (npc inventory)
		if(playerInventoryActive && !playerInventoryList.isEmpty() && playerInventoryList.size() > playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns) {
			
			String name = playerInventoryList.get(playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns).getItemType().getName();
			g.drawString(name, Main.WIDTH/2 - (name.length() * 9)/2, 525);
			
			Item selectedItem = playerInventoryList.get(playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns);
			
			if(selectedItem.getItemType().getItemCategory().equals("melee_slay") || selectedItem.getItemType().getItemCategory().equals("melee_thrust") || selectedItem.getItemType().getItemCategory().equals("bow")) {
			
				//Display minStrength, minDexterity if there is a requirement
				if(selectedItem.getItemType().getMinStrength() > 0) {
					g.drawString("Needed Strength:", 330, 650);
					String minStrength = String.valueOf(selectedItem.getItemType().getMinStrength());
					g.drawString(minStrength, 780 - minStrength.length() * 9, 650);
				}
			
				if(selectedItem.getItemType().getMinDexterity() > 0) {
					g.drawString("Needed Dexterity:", 330, 650);
					String minDexterity = String.valueOf(selectedItem.getItemType().getMinDexterity());
					g.drawString(minDexterity, 780 - minDexterity.length() * 9, 650);
				}

				//Display damage if the item has damage
				if(selectedItem.getItemType().getDamage() > 0) {
					g.drawString("Damage:", 330, 630);
					String damage = String.valueOf(selectedItem.getItemType().getDamage());
					g.drawString(damage, 780 - damage.length() * 9, 630);
				}
			
			}

			if(selectedItem.getItemType().getItemCategory().equals("rune") || selectedItem.getItemType().getItemCategory().equals("spell")) {
							
				//For heal runes and heal spells
				if(selectedItem.getItemType().getHealthBoost() > 0) {
					g.drawString("Health Boost:", 330, 630);
					String healthBoost = String.valueOf(selectedItem.getItemType().getHealthBoost());
					g.drawString(healthBoost, 780 - healthBoost.length() * 9, 630);
				}
				
				//Relevant for transformation spells and runes
				if(selectedItem.getItemType().getEffectDuration() > 0) {
					g.drawString("Duration:", 330, 630);
					String duration = String.valueOf(selectedItem.getItemType().getEffectDuration()) + " seconds";
					g.drawString(duration, 780 - duration.length() * 9, 630);
				}
				
				//Relevant for all runes and spells, because they consume mana
				if(selectedItem.getItemType().getManaCost() > 0) {
					g.drawString("Mana Cost:", 330, 650);
					String manaCost = String.valueOf(selectedItem.getItemType().getManaCost());
					g.drawString(manaCost, 780 - manaCost.length() * 9, 650);
				}
				
				//Display damage if the item has damage
				if(selectedItem.getItemType().getDamage() > 0) {
					g.drawString("Damage:", 330, 630);
					String damage = String.valueOf(selectedItem.getItemType().getDamage());
					g.drawString(damage, 780 - damage.length() * 9, 630);
				}
			
			}
			
			
			//Display protection if the item has protection
			if(selectedItem.getItemType().getProtection() > 0) {
				g.drawString("Protection:", 330, 650);
				String protection = String.valueOf(selectedItem.getItemType().getProtection()) + "%";
				g.drawString(protection, 780 - protection.length() * 9, 650);
			}
			
			if(!selectedItem.getItemType().getItemCategory().equals("rune") && !selectedItem.getItemType().getItemCategory().equals("spell")) {
			
				if(selectedItem.getItemType().getHealthBoost() > 0) {
					g.drawString("Health Boost:", 330, 650);
					String healthBoost = String.valueOf(selectedItem.getItemType().getHealthBoost());
					g.drawString(healthBoost, 780 - healthBoost.length() * 9, 650);
				}
				
				//Relevant for speed potion and wolfnettel
				if(selectedItem.getItemType().getEffectDuration() > 0) {
					g.drawString("Duration:", 330, 650);
					String duration = String.valueOf(selectedItem.getItemType().getEffectDuration()) + " seconds";
					g.drawString(duration, 780 - duration.length() * 9, 650);
				}
			
			}
			
			if(selectedItem.getItemType().getManaBoost() > 0) {
				g.drawString("Mana Boost:", 330, 650);
				String manaBoost = String.valueOf(selectedItem.getItemType().getManaBoost());
				g.drawString(manaBoost, 780 - manaBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getManaBoost() > 0) {
				g.drawString("Mana Boost:", 330, 650);
				String manaBoost = String.valueOf(selectedItem.getItemType().getManaBoost());
				g.drawString(manaBoost, 780 - manaBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getMaxHealthBoost() > 0) {
				g.drawString("Maximum HP Boost:", 330, 650);
				String maxHealthBoost = String.valueOf(selectedItem.getItemType().getMaxHealthBoost());
				g.drawString(maxHealthBoost, 780 - maxHealthBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getMaxManaBoost() > 0) {
				g.drawString("Maximum Mana Boost:", 330, 650);
				String maxManaBoost = String.valueOf(selectedItem.getItemType().getMaxManaBoost());
				g.drawString(maxManaBoost, 780 - maxManaBoost.length() * 9, 650);
			}

			if(selectedItem.getItemType().getStrengthBoost() > 0) {
				g.drawString("Strength Boost:", 330, 650);
				String strengthBoost = String.valueOf(selectedItem.getItemType().getStrengthBoost());
				g.drawString(strengthBoost, 780 - strengthBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getDexterityBoost() > 0) {
				g.drawString("Dexterity Boost:", 330, 650);
				String dexterityBoost = String.valueOf(selectedItem.getItemType().getDexterityBoost());
				g.drawString(dexterityBoost, 780 - dexterityBoost.length() * 9, 650);
			}
			
			g.drawString("Value in Gold:", 330, 670);
			String value = String.valueOf(selectedItem.getItemType().getValue());
			g.drawString(value, 780 - value.length() * 9, 670);
			
			selectedItem.getItemType().getDescriptionAnimation().draw(806, 541);
			
		}

		
		//Draw item description if cursor is on the on the left side (npc inventory)
		if(npcInventoryActive && !npc.getInventoryList().isEmpty() && npc.getInventoryList().size() > npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns) {
			
			String name = npc.getInventoryList().get(npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns).getItemType().getName();
			g.drawString(name, Main.WIDTH/2 - (name.length() * 9)/2, 525);
			

			Item selectedItem = npc.getInventoryList().get(npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns);
			
			if(selectedItem.getItemType().getItemCategory().equals("melee_slay") || selectedItem.getItemType().getItemCategory().equals("melee_thrust") || selectedItem.getItemType().getItemCategory().equals("bow")) {
			
				//Display minStrength, minDexterity if there is a requirement
				if(selectedItem.getItemType().getMinStrength() > 0) {
					g.drawString("Needed Strength:", 330, 650);
					String minStrength = String.valueOf(selectedItem.getItemType().getMinStrength());
					g.drawString(minStrength, 780 - minStrength.length() * 9, 650);
				}
			
				if(selectedItem.getItemType().getMinDexterity() > 0) {
					g.drawString("Needed Dexterity:", 330, 650);
					String minDexterity = String.valueOf(selectedItem.getItemType().getMinDexterity());
					g.drawString(minDexterity, 780 - minDexterity.length() * 9, 650);
				}

				//Display damage if the item has damage
				if(selectedItem.getItemType().getDamage() > 0) {
					g.drawString("Damage:", 330, 630);
					String damage = String.valueOf(selectedItem.getItemType().getDamage());
					g.drawString(damage, 780 - damage.length() * 9, 630);
				}
			
			}

			if(selectedItem.getItemType().getItemCategory().equals("rune") || selectedItem.getItemType().getItemCategory().equals("spell")) {
							
				//For heal runes and heal spells
				if(selectedItem.getItemType().getHealthBoost() > 0) {
					g.drawString("Health Boost:", 330, 630);
					String healthBoost = String.valueOf(selectedItem.getItemType().getHealthBoost());
					g.drawString(healthBoost, 780 - healthBoost.length() * 9, 630);
				}
				
				//Relevant for transformation spells and runes
				if(selectedItem.getItemType().getEffectDuration() > 0) {
					g.drawString("Duration:", 330, 630);
					String duration = String.valueOf(selectedItem.getItemType().getEffectDuration()) + " seconds";
					g.drawString(duration, 780 - duration.length() * 9, 630);
				}
				
				//Relevant for all runes and spells, because they consume mana
				if(selectedItem.getItemType().getManaCost() > 0) {
					g.drawString("Mana Cost:", 330, 650);
					String manaCost = String.valueOf(selectedItem.getItemType().getManaCost());
					g.drawString(manaCost, 780 - manaCost.length() * 9, 650);
				}
				
				//Display damage if the item has damage
				if(selectedItem.getItemType().getDamage() > 0) {
					g.drawString("Damage:", 330, 630);
					String damage = String.valueOf(selectedItem.getItemType().getDamage());
					g.drawString(damage, 780 - damage.length() * 9, 630);
				}
			
			}
			
			
			//Display protection if the item has protection
			if(selectedItem.getItemType().getProtection() > 0) {
				g.drawString("Protection:", 330, 650);
				String protection = String.valueOf(selectedItem.getItemType().getProtection()) + "%";
				g.drawString(protection, 780 - protection.length() * 9, 650);
			}
			
			if(!selectedItem.getItemType().getItemCategory().equals("rune") && !selectedItem.getItemType().getItemCategory().equals("spell")) {
			
				if(selectedItem.getItemType().getHealthBoost() > 0) {
					g.drawString("Health Boost:", 330, 650);
					String healthBoost = String.valueOf(selectedItem.getItemType().getHealthBoost());
					g.drawString(healthBoost, 780 - healthBoost.length() * 9, 650);
				}
				
				//Relevant for speed potion and wolfnettel
				if(selectedItem.getItemType().getEffectDuration() > 0) {
					g.drawString("Duration:", 330, 650);
					String duration = String.valueOf(selectedItem.getItemType().getEffectDuration()) + " seconds";
					g.drawString(duration, 780 - duration.length() * 9, 650);
				}
			
			}
			
			if(selectedItem.getItemType().getManaBoost() > 0) {
				g.drawString("Mana Boost:", 330, 650);
				String manaBoost = String.valueOf(selectedItem.getItemType().getManaBoost());
				g.drawString(manaBoost, 780 - manaBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getManaBoost() > 0) {
				g.drawString("Mana Boost:", 330, 650);
				String manaBoost = String.valueOf(selectedItem.getItemType().getManaBoost());
				g.drawString(manaBoost, 780 - manaBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getMaxHealthBoost() > 0) {
				g.drawString("Maximum HP Boost:", 330, 650);
				String maxHealthBoost = String.valueOf(selectedItem.getItemType().getMaxHealthBoost());
				g.drawString(maxHealthBoost, 780 - maxHealthBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getMaxManaBoost() > 0) {
				g.drawString("Maximum Mana Boost:", 330, 650);
				String maxManaBoost = String.valueOf(selectedItem.getItemType().getMaxManaBoost());
				g.drawString(maxManaBoost, 780 - maxManaBoost.length() * 9, 650);
			}

			if(selectedItem.getItemType().getStrengthBoost() > 0) {
				g.drawString("Strength Boost:", 330, 650);
				String strengthBoost = String.valueOf(selectedItem.getItemType().getStrengthBoost());
				g.drawString(strengthBoost, 780 - strengthBoost.length() * 9, 650);
			}
			
			if(selectedItem.getItemType().getDexterityBoost() > 0) {
				g.drawString("Dexterity Boost:", 330, 650);
				String dexterityBoost = String.valueOf(selectedItem.getItemType().getDexterityBoost());
				g.drawString(dexterityBoost, 780 - dexterityBoost.length() * 9, 650);
			}
			
			g.drawString("Value in Gold:", 330, 670);
			String value = String.valueOf(selectedItem.getItemType().getBuyValue());
			g.drawString(value, 780 - value.length() * 9, 670);
			
			selectedItem.getItemType().getDescriptionAnimation().draw(806, 541);
			
		}
		
	}

	private void drawArrowForPlayerInventory(Graphics g) {
		
		arrowUpAnimation.updateNoDraw();
		arrowDownAnimation.updateNoDraw();
		
		if(playerScrollOffset > 0) { 
			arrowUpAnimation.draw(1257, 24);
		}
		
		if(playerInventoryList.size() > amountCells + playerScrollOffset * amountColumns) {
			arrowDownAnimation.draw(1257, 474);
		}
		
	}
	
	private void drawArrowForNPCInventory(Graphics g) {
		
		arrowUpAnimation.updateNoDraw();
		arrowDownAnimation.updateNoDraw();
		
		if(npcScrollOffset > 0) { 
			arrowUpAnimation.draw(0, 24);
		}
		
		if(npc.getInventoryList().size() > amountCells + npcScrollOffset * amountColumns) {
			arrowDownAnimation.draw(0, 474);
		}
		
	}
	
	public void removeSelectedPlayerItem() {

		int index = playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns;
				
		if(playerItemCountList.get(index) > 1) {
			playerItemCountList.set(index, playerItemCountList.get(index) - 1);
		} else {
			playerInventoryList.remove(index);
			playerItemCountList.remove(index);
		}
		
		if(playerInventoryList.size() == playerSelectedCellX + (playerSelectedCellY + playerScrollOffset) * amountColumns) {
			
			if(playerSelectedCellX > 0) {
				playerSelectedCellX = playerSelectedCellX - 1;
			} else if(playerSelectedCellX == 0) {
				if(playerSelectedCellY > 0) {
					playerSelectedCellY = playerSelectedCellY - 1;
					playerSelectedCellX = 4;
				}
			}

		}
	
	}
	
	public void removeSelectedNPCItem() {
		
		int index = npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns;
				
		if(npc.getItemCountList().get(index) > 1) {
			npc.getItemCountList().set(index, npc.getItemCountList().get(index) - 1);
		} else {
			npc.getInventoryList().remove(index);
			npc.getItemCountList().remove(index);
		}
		
		if(npc.getInventoryList().size() == npcSelectedCellX + (npcSelectedCellY + npcScrollOffset) * amountColumns) {
			
			if(npcSelectedCellX > 0) {
				npcSelectedCellX = npcSelectedCellX - 1;
			} else if(npcSelectedCellX == 0) {
				if(npcSelectedCellY > 0) {
					npcSelectedCellY = npcSelectedCellY - 1;
					npcSelectedCellX = 4;
				}
			}

		}
		
	}
	
	public boolean isWindowOpen() {
		return windowOpen;
	}

	public void setWindowOpen(boolean windowOpen) {
		this.windowOpen = windowOpen;
	}

	public void setNpc(NPC npc) {
		this.npc = npc;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}