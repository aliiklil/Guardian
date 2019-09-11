package gui;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Main;
import manager.MobManager;
import models.Item;
import models.ItemType;
import models.Player;
import models.Character;

public class InventoryWindow {

	private boolean windowOpen = false;
	private Image inventoryImage = new Image("resources/inventory.png");
	private Image selectedCellImage = new Image("resources/inventory_selected_cell.png");
	private Image equippedItemImage = new Image("resources/inventory_equipped_item.png");
	private int selectedCellX = 0;
	private int selectedCellY = 0;
	
	private final int amountRows = 6;
	private final int amountColumns = 5;
	private final int amountCells = 30;
	
	private int scrollOffset = 0;
	
	private int goldCounter = 0;
	
	private Input input = Main.appGameContainer.getInput();

	private long timestamp = 0;

	private boolean holdUpKey = false;
	private boolean holdDownKey = false;
	private boolean holdLeftKey = false;
	private boolean holdRightKey = false;
		
	private SpriteSheet arrowUpSpriteSheet = new SpriteSheet("resources/arrowUpImage.png", 22, 22);
	private SpriteSheet arrowDownSpriteSheet = new SpriteSheet("resources/arrowDownImage.png", 22, 22);
	
	private Animation arrowUpAnimation = new Animation(arrowUpSpriteSheet, 0, 0, 1, 0, true, 750, true);
	private Animation arrowDownAnimation = new Animation(arrowDownSpriteSheet, 0, 0, 1, 0, true, 750, true);
	
	private Player player;
	
	private ArrayList<Item> playerInventoryList;
	private ArrayList<Integer> playerItemCountList;
	
	
	private Image zeroOfThree = new Image("resources/zero_of_three.png");
	private Image oneOfThree = new Image("resources/one_of_three.png");
	private Image twoOfThree = new Image("resources/two_of_three.png");
	private Image threeOfThree = new Image("resources/three_of_three.png");
	
	public InventoryWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = MobManager.getPlayer();
		
		playerInventoryList = player.getInventoryList();
		playerItemCountList = player.getItemCountList();
		
		if(player.isTabPressed() && !player.getDialogueWindow().isWindowOpen() && !player.getTradingWindow().isWindowOpen() &&!player.getQuestLogWindow().isWindowOpen() && !player.getReadingWindow().isWindowOpen()) {
			if(!windowOpen) {
				windowOpen = true;
			} else {
				windowOpen = false;
			}
		}	
		
		
		if(player.isTranformedToWolf() || player.isTranformedToSkeleton() || player.isTranformedToOrc()) {
			input.consumeEvent();
		}
		
		if(player.isEscapePressed() && windowOpen) {
			windowOpen = false;
		}

		
		if(windowOpen) {
			
			if(player.isYPressed() && playerInventoryList.size() > 0) {
				
				Item selectedItem = playerInventoryList.get((selectedCellY + scrollOffset) * amountColumns + selectedCellX);
				
				if(selectedItem.getItemType().isEquippable()) {
					if(selectedItem.isEquipped()) {
						selectedItem.setEquipped(false);
						
						if(selectedItem.getItemType().getItemCategory().equals("melee_slay") || selectedItem.getItemType().getItemCategory().equals("melee_thrust")) {
							player.setEquippedMelee(null);
							player.setCurrentMeleeAnimation(null);
						} else if(selectedItem.getItemType().getItemCategory().equals("bow")) {
							player.setEquippedBow(null);
							player.setCurrentBowAnimation(null);
						} else if(selectedItem.getItemType().getItemCategory().equals("rune") || selectedItem.getItemType().getItemCategory().equals("spell")) {
							player.setEquippedSpell(null);
							player.setCurrentSpellAnimation(null);
						} else if(selectedItem.getItemType().getItemCategory().equals("armor")) {
							player.setEquippedArmor(null);
							player.setCurrentArmorAnimation(null);
						}
						
						if(player.getCurrentAnimation() == player.getLookUpAnimation()) {
							player.setAnimationsToLookUp();
						} else  if(player.getCurrentAnimation() == player.getLookDownAnimation()) {
							player.setAnimationsToLookDown();
						} else if(player.getCurrentAnimation() == player.getLookLeftAnimation()) {
							player.setAnimationsToLookLeft();
						} else if(player.getCurrentAnimation() == player.getLookRightAnimation()) {
							player.setAnimationsToLookRight();
						}
												
					} else {
						if (player.getStrength() >= selectedItem.getItemType().getMinStrength() && player.getDexterity() >= selectedItem.getItemType().getMinDexterity()) {
							for(Item item : playerInventoryList) {
								if(item.isEquipped() && item.getItemType().getItemCategory().equals(selectedItem.getItemType().getItemCategory())) {
									item.setEquipped(false);
								}							
								
								if(item.isEquipped() && (item.getItemType().getItemCategory().equals("melee_slay") && selectedItem.getItemType().getItemCategory().equals("melee_thrust") 
													 || item.getItemType().getItemCategory().equals("melee_thrust") && selectedItem.getItemType().getItemCategory().equals("melee_slay"))) {
								
									item.setEquipped(false);
									
								}
								
								if(item.isEquipped() && (item.getItemType().getItemCategory().equals("rune") && selectedItem.getItemType().getItemCategory().equals("spell") 
										 || item.getItemType().getItemCategory().equals("spell") && selectedItem.getItemType().getItemCategory().equals("rune"))) {
					
									item.setEquipped(false);
									
								}
							}
							
							selectedItem.setEquipped(true);

							if(selectedItem.getItemType().getItemCategory().equals("melee_slay") || selectedItem.getItemType().getItemCategory().equals("melee_thrust")) {
								player.setEquippedMelee(selectedItem.getItemType());
							} else if(selectedItem.getItemType().getItemCategory().equals("bow")) {
								player.setEquippedBow(selectedItem.getItemType());
							} else if(selectedItem.getItemType().getItemCategory().equals("rune") || selectedItem.getItemType().getItemCategory().equals("spell")) {
								player.setEquippedSpell(selectedItem.getItemType());
							} else if(selectedItem.getItemType().getItemCategory().equals("armor")) {
								player.setEquippedArmor(selectedItem.getItemType());
							}
							
							if(player.getCurrentAnimation() == player.getLookUpAnimation()) {
								player.setAnimationsToLookUp();
							} else  if(player.getCurrentAnimation() == player.getLookDownAnimation()) {
								player.setAnimationsToLookDown();
							} else if(player.getCurrentAnimation() == player.getLookLeftAnimation()) {
								player.setAnimationsToLookLeft();
							} else if(player.getCurrentAnimation() == player.getLookRightAnimation()) {
								player.setAnimationsToLookRight();
							}
							
						} else {
							
							if(player.getStrength() < selectedItem.getItemType().getMinStrength()) {
								
								String text = "You need " + (selectedItem.getItemType().getMinStrength() - player.getStrength()) + " more strength points";
								player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
								
							}
							
							
							if(player.getDexterity() < selectedItem.getItemType().getMinDexterity()) {
								
								String text = "You need " + (selectedItem.getItemType().getMinDexterity() - player.getDexterity()) + " more dexterity points";
								player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
								
							}
														
						}
					} 
				}

				
				//If player consumes item, which gives health
				if(selectedItem.getItemType().getHealthBoost() > 0 && !selectedItem.getItemType().getItemCategory().equals("rune") && !selectedItem.getItemType().getItemCategory().equals("spell")) {

					player.getHealthBar().setCurrentValue(player.getHealthBar().getCurrentValue() + selectedItem.getItemType().getHealthBoost());
					removeSelectedItem();
					
					String text = "Health Points + " + selectedItem.getItemType().getHealthBoost();
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
				
				
				//If player consumes item, which gives mana
				if(selectedItem.getItemType().getManaBoost() > 0) {

					player.getManaBar().setCurrentValue(player.getManaBar().getCurrentValue() + selectedItem.getItemType().getManaBoost());
					removeSelectedItem();
					
					String text = "Mana Points + " + selectedItem.getItemType().getManaBoost();
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}

				//If player consumes item, which gives strength
				if(selectedItem.getItemType().getStrengthBoost() > 0) {

					player.setStrength(player.getStrength() + selectedItem.getItemType().getStrengthBoost());
					removeSelectedItem();
					
					String text = "Strength + " + selectedItem.getItemType().getStrengthBoost();
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
				
				//If player consumes item, which gives dexterity
				if(selectedItem.getItemType().getDexterityBoost() > 0) {

					player.setDexterity(player.getDexterity() + selectedItem.getItemType().getDexterityBoost());
					removeSelectedItem();
					
					String text = "Dexterity + " + selectedItem.getItemType().getDexterityBoost();
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
								
				//If player consumes item, which gives max hp bonus
				if(selectedItem.getItemType().getMaxHealthBoost() > 0) {

					player.setHealthPoints(player.getHealthPoints() + selectedItem.getItemType().getMaxHealthBoost());
					removeSelectedItem();
					
					String text = "Max. HP + " + selectedItem.getItemType().getMaxHealthBoost();
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
				
				//If player consumes item, which gives max mana bonus
				if(selectedItem.getItemType().getMaxManaBoost() > 0) {

					player.setMana(player.getMana() + selectedItem.getItemType().getMaxManaBoost());
					removeSelectedItem();
					
					String text = "Mana + " + selectedItem.getItemType().getMaxManaBoost();
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
				
				//If player consumes item, which gives speed bonus
				if(selectedItem.getItemType().getName().equals("Speed Potion")) {

					player.setSpeedBoostTimeStamp(System.currentTimeMillis());
					
					player.setMovementSpeed(player.getDefaultMovementSpeed() + 1);
					player.setDiagonalMovementSpeed(player.getDefaultDiagonalMovementSpeed() + 0.5f);
					
					removeSelectedItem();
					
					String text = "Speed bonus for 1 minute";
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
				
				
				if(selectedItem.getItemType().getName().equals("Wolfnettel")) {

					player.setSpeedBoostTimeStamp(System.currentTimeMillis() - 50*1000); // So it stops after 10 seconds, not after 60 seconds
					
					player.setMovementSpeed(player.getDefaultMovementSpeed() + 1);
					player.setDiagonalMovementSpeed(player.getDefaultDiagonalMovementSpeed() + 0.5f);
					
					removeSelectedItem();
					
					String text = "Speed bonus for 10 seconds";
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
				}
				

				if(selectedItem.getItemType().getName().equals("Goldtruffle")) {

					removeSelectedItem();
										
				}
				
				if(selectedItem.getItemType().getItemCategory().equals("readable")) {

					windowOpen = false;
					player.getReadingWindow().showWindow(selectedItem.getItemType().getReadableImage());
										
				}
	
			}
			
			if(player.isKeyUpPressed() || holdUpKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(scrollOffset > 0 && selectedCellY == 0) {
					scrollOffset--;
					timestamp = System.currentTimeMillis();
				} 

				if(selectedCellY > 0) {
					selectedCellY--;
					timestamp = System.currentTimeMillis();
				}
				
			}
						
			if(player.isKeyDownPressed() || holdDownKey && System.currentTimeMillis() - timestamp > 100) {
				
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
			
			if(player.isKeyLeftPressed() || holdLeftKey && System.currentTimeMillis() - timestamp > 100) {
				if(selectedCellX > 0) {
					selectedCellX--;
					timestamp = System.currentTimeMillis();
				}
			}
			
			if(player.isKeyRightPressed() || holdRightKey && System.currentTimeMillis() - timestamp > 100) {
				if(selectedCellX < amountColumns - 1 && playerInventoryList.size() > selectedCellX + (selectedCellY + scrollOffset) * amountColumns + 1) {
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
				
		if(windowOpen) {			
			g.drawImage(inventoryImage, 0, 0);
			g.drawImage(selectedCellImage, 863 + selectedCellX * 78, 25 + selectedCellY * 78);
			
			g.setColor(Color.black);
			
			drawPlayerStats(g);
			
			g.drawString("Gold: ", 722, 37);
			g.drawString(String.valueOf(goldCounter), 834 - Integer.toString(goldCounter).length() * 9, 37);

			int row = 0;
			int column = 0;
			
			for(int i = scrollOffset * amountColumns; i < playerInventoryList.size(); i++) {
				
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
			
			for(Item item : playerInventoryList) {
				if(item.isEquipped()) {
					if(item.getItemType().getItemCategory().equals("melee_slay") || item.getItemType().getItemCategory().equals("melee_thrust")) {
						item.getItemType().getInventoryAnimation().draw(34, 34);
					}
					
					if(item.getItemType().getItemCategory().equals("bow")) {
						item.getItemType().getInventoryAnimation().draw(112, 34);
					}
					
					if(item.getItemType().getItemCategory().equals("rune") || item.getItemType().getItemCategory().equals("spell")) {
						item.getItemType().getInventoryAnimation().draw(190, 34);
					}
				}
			}
			
			if(!playerInventoryList.isEmpty() && playerInventoryList.size() > selectedCellX + (selectedCellY + scrollOffset) * amountColumns) {
				
				String name = playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getName();
				g.drawString(name, Main.WIDTH/2 - (name.length() * 9)/2, 525);
				

				Item selectedItem = playerInventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns);
				
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
			
			drawArrow(g);
			
		}
							
	}
	
	private void drawPlayerStats(Graphics g) {
			
		g.drawString("Level: ", 32, 111);
		g.drawString("Experience: ", 32, 131);
		g.drawString("Next Level: ", 32, 151);
		g.drawString("Learning Points: ", 32, 171);
		
		g.drawString("Strength: ", 32, 211);
		g.drawString("Dexterity: ", 32, 231);
		
		g.drawString("Health Points: ", 32, 271);
		g.drawString("Mana: ", 32, 291);
		
		g.drawString("Melee Skill: ", 32, 331);
		g.drawString("Bow Skill: ", 32, 351);
		
		g.drawString("Lockpicking: ", 32, 391);
		g.drawString("Alchemy: ", 32, 411);
		g.drawString("Blacksmithing: ", 32, 431);
		g.drawString("Runeforging: ", 32, 451);
		
		g.drawString("Take Furs: ", 32, 491);
		g.drawString("Take Trophies: ", 32, 511);
		g.drawString("HP Regeneration: ", 32, 531);
		g.drawString("Mana Regeneration: ", 32, 551);
		
		
		g.drawString("Armor Protection: ", 32, 591);
		
		
		g.drawString(String.valueOf(player.getLevel()), 195, 111);
		g.drawString(String.valueOf(player.getExperience()), 195, 131);
		g.drawString(String.valueOf(player.getNextLevelExperience()), 195, 151);
		g.drawString(String.valueOf(player.getLearningPoints()), 195, 171);
		
		g.drawString(String.valueOf(player.getStrength()), 195, 211);
		g.drawString(String.valueOf(player.getDexterity()), 195, 231);
		
		g.drawString(player.getHealthBar().getCurrentValue() + "/" + String.valueOf(player.getHealthPoints()), 195, 271);
		g.drawString(player.getManaBar().getCurrentValue() + "/" + String.valueOf(player.getMana()), 195, 291);
		
		g.drawString(String.valueOf(player.getMeleeSkill()) + "%", 195, 331);
		g.drawString(String.valueOf(player.getBowSkill()) + "%", 195, 351);
	
		
		if(player.getLockPickingSkill() == 0) {
			zeroOfThree.draw(195, 391);
		} else if(player.getLockPickingSkill() == 1) {
			oneOfThree.draw(195, 391);
		} else if(player.getLockPickingSkill() == 2) {
			twoOfThree.draw(195, 391);
		} else if(player.getLockPickingSkill() == 3) {
			threeOfThree.draw(195, 391);
		}
		
		if(player.getAlchemySkill() == 0) {
			zeroOfThree.draw(195, 411);
		} else if(player.getAlchemySkill() == 1) {
			oneOfThree.draw(195, 411);
		} else if(player.getAlchemySkill() == 2) {
			twoOfThree.draw(195, 411);
		} else if(player.getAlchemySkill() == 3) {
			threeOfThree.draw(195, 411);
		}
		
		if(player.getBlacksmithingSkill() == 0) {
			zeroOfThree.draw(195, 431);
		} else if(player.getBlacksmithingSkill() == 1) {
			oneOfThree.draw(195, 431);
		} else if(player.getBlacksmithingSkill() == 2) {
			twoOfThree.draw(195, 431);
		} else if(player.getBlacksmithingSkill() == 3) {
			threeOfThree.draw(195, 431);
		}
		
		if(player.getRuneForgingSkill() == 0) {
			zeroOfThree.draw(195, 451);
		} else if(player.getRuneForgingSkill() == 1) {
			oneOfThree.draw(195, 451);
		} else if(player.getRuneForgingSkill() == 2) {
			twoOfThree.draw(195, 451);
		} else if(player.getRuneForgingSkill() == 3) {
			threeOfThree.draw(195, 451);
		}
		
		
		
		if(player.isTakeFurs()) {
			g.drawString("Learned", 195, 491);
		} else {
			g.drawString("-", 195, 491);
		}
		
		if(player.isTakeTrophies()) {
			g.drawString("Learned", 195, 511);
		} else {
			g.drawString("-", 195, 511);
		}
		
		if(player.isHpRegeneration()) {
			g.drawString("Learned", 195, 531);
		} else {
			g.drawString("-", 195, 531);
		}
		
		if(player.isManaRegeneration()) {
			g.drawString("Learned", 195, 551);
		} else {
			g.drawString("-", 195, 551);
		}
		
		g.drawString(String.valueOf(player.getArmorProtection() + "%"), 195, 591);
				
	}
	
	private void drawArrow(Graphics g) {
		
		arrowUpAnimation.updateNoDraw();
		arrowDownAnimation.updateNoDraw();
		
		if(scrollOffset > 0) { 
			arrowUpAnimation.draw(1257, 24);
		}
		
		if(playerInventoryList.size() > amountCells + scrollOffset * amountColumns) {
			arrowDownAnimation.draw(1257, 474);
		}
		
	}
	
	public void addItem(Item item) {
		
		for(int i = 0; i < playerInventoryList.size(); i++) {
			if(item.getItemType().getInventoryAnimation().getImage(0).getResourceReference().equals(playerInventoryList.get(i).getItemType().getInventoryAnimation().getImage(0).getResourceReference())) {	
				playerItemCountList.set(i, playerItemCountList.get(i) + 1);
				return;
			}
		}
		
		playerItemCountList.add(1);
		playerInventoryList.add(item);
		
		sortInventory();
			
	}
	
	public void removeSelectedItem() {

		int index = selectedCellX + (selectedCellY + scrollOffset) * amountColumns;
		
		if(playerItemCountList.get(index) > 1) {
			playerItemCountList.set(index, playerItemCountList.get(index) - 1);
		} else {
			playerInventoryList.remove(index);
			playerItemCountList.remove(index);
		}
		
		if(playerInventoryList.size() == selectedCellX + (selectedCellY + scrollOffset) * amountColumns) {
			
			if(selectedCellX > 0) {
				selectedCellX = selectedCellX - 1;
			} else if(selectedCellX == 0) {
				if(selectedCellY > 0) {
					selectedCellY = selectedCellY - 1;
					selectedCellX = 4;
				}
			}

		}
		
	}
	
	public void removeItem(ItemType itemType) {

		for(int i = 0; i < playerInventoryList.size(); i++) {
			
			if(playerInventoryList.get(i).getItemType() == itemType) {
				
				if(playerItemCountList.get(i) > 1) {
					playerItemCountList.set(i, playerItemCountList.get(i) - 1);
					break;
				}
				
				if(playerItemCountList.get(i) == 1) {
					playerInventoryList.remove(i);
					playerItemCountList.remove(i);
					break;
				}

			}
			
		}
		
		if(playerInventoryList.size() == selectedCellX + (selectedCellY + scrollOffset) * amountColumns) {
			
			if(selectedCellX > 0) {
				selectedCellX = selectedCellX - 1;
			} else if(selectedCellX == 0) {
				if(selectedCellY > 0) {
					selectedCellY = selectedCellY - 1;
					selectedCellX = 4;
				}
			}

		}
		
	}
	
	private void sortInventory() {
		
		//Sort after inventory priority
		for (int i = 0; i < playerInventoryList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < playerInventoryList.size(); j++) {
				if (playerInventoryList.get(j).getItemType().getInventoryPriority() < playerInventoryList.get(index).getItemType().getInventoryPriority()) {
					index = j;
				}
			}
			Collections.swap(playerInventoryList, index, i);
			Collections.swap(playerItemCountList, index, i);
		}
		
		//Sort after value in gold if inventory priority is the same
		for (int i = 0; i < playerInventoryList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < playerInventoryList.size(); j++) {
				if (playerInventoryList.get(j).getItemType().getInventoryPriority() == playerInventoryList.get(index).getItemType().getInventoryPriority() && playerInventoryList.get(j).getItemType().getValue() > playerInventoryList.get(index).getItemType().getValue()) {
					index = j;
				}
			}
			Collections.swap(playerInventoryList, index, i);
			Collections.swap(playerItemCountList, index, i);
		}
		
		
		
	}
	
	public boolean isWindowOpen() {
		return windowOpen;
	}
	
	public void showWindow() {
		this.windowOpen = true;
	}
	
	public void incrementGoldCounter() {
		goldCounter++;
	}
	
	public void decrementGoldCounter() {
		goldCounter--;
	}

	public ArrayList<Item> getPlayerInventoryList() {
		return playerInventoryList;
	}

	public ArrayList<Integer> getPlayerItemCountList() {
		return playerItemCountList;
	}

	public int getGoldCounter() {
		return goldCounter;
	}
		
}