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
import manager.CharacterManager;
import models.Item;
import models.Player;

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
	
	public InventoryWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = CharacterManager.getPlayer();
		
		if(input.isKeyPressed(Input.KEY_TAB) && !player.getDialogueWindow().isWindowOpen()) {
			if(!windowOpen) {
				windowOpen = true;
			} else {
				windowOpen = false;
			}
		}	
		
		if(windowOpen) {
			
			if(player.isYPressed() && inventoryList.size() > 0) {
				
				Item itemToEquip = inventoryList.get((selectedCellY + scrollOffset) * amountColumns + selectedCellX);
				
				if(itemToEquip.getItemType().isEquippable()) {
					if(itemToEquip.isEquipped()) {
						itemToEquip.setEquipped(false);
						
						if(itemToEquip.getItemType().getItemCategory().equals("melee_slay") || itemToEquip.getItemType().getItemCategory().equals("melee_thrust")) {
							player.setEquippedMelee(null);
							player.setCurrentMeleeAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("bow")) {
							player.setEquippedBow(null);
							player.setCurrentBowAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("spell")) {
							player.setEquippedSpell(null);
							player.setCurrentSpellAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("head")) {
							player.setEquippedHead(null);
							player.setCurrentHeadAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("chest")) {
							player.setEquippedTorso(null);
							player.setCurrentChestAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("hands")) {
							player.setEquippedHands(null);
							player.setCurrentHandsAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("legs")) {
							player.setEquippedLegs(null);
							player.setCurrentLegsAnimation(null);
						} else if(itemToEquip.getItemType().getItemCategory().equals("feet")) {
							player.setEquippedBoots(null);
							player.setCurrentFeetAnimation(null);
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
						for(Item item : inventoryList) {
							if(item.isEquipped() && item.getItemType().getItemCategory().equals(itemToEquip.getItemType().getItemCategory())) {
								item.setEquipped(false);
							}							
							
							if(item.isEquipped() && (item.getItemType().getItemCategory().equals("melee_slay") && itemToEquip.getItemType().getItemCategory().equals("melee_thrust") 
												 || item.getItemType().getItemCategory().equals("melee_thrust") && itemToEquip.getItemType().getItemCategory().equals("melee_slay"))) {
							
								item.setEquipped(false);
								
							}
						}
						
						itemToEquip.setEquipped(true);
						
						if(itemToEquip.getItemType().getItemCategory().equals("melee_slay") || itemToEquip.getItemType().getItemCategory().equals("melee_thrust")) {
							player.setEquippedMelee(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("bow")) {
							player.setEquippedBow(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("spell")) {
							player.setEquippedSpell(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("head")) {
							player.setEquippedHead(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("chest")) {
							player.setEquippedTorso(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("hands")) {
							player.setEquippedHands(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("legs")) {
							player.setEquippedLegs(itemToEquip.getItemType());
						} else if(itemToEquip.getItemType().getItemCategory().equals("feet")) {
							player.setEquippedBoots(itemToEquip.getItemType());
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
											
					}
				} 
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
				
				if(selectedCellY == amountRows - 1 && inventoryList.size() > amountCells + scrollOffset * amountColumns) {
					
					if((selectedCellX + (selectedCellY + scrollOffset) * amountColumns + amountColumns + 1) > inventoryList.size()) {
						
						if(inventoryList.size() % amountColumns == 0) {
							selectedCellX = amountColumns - 1;
						} else {
							selectedCellX = inventoryList.size() % amountColumns - 1;
						}
						
					}

					scrollOffset++;
					timestamp = System.currentTimeMillis();
					
				}
				
				if(selectedCellY < amountRows - 1 && inventoryList.size() > selectedCellX + (selectedCellY + scrollOffset + 1) * amountColumns) {
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
				if(selectedCellX < amountColumns - 1 && inventoryList.size() > selectedCellX + (selectedCellY + scrollOffset) * amountColumns + 1) {
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
			g.drawImage(selectedCellImage, 1484 + selectedCellX * 78, 305 + selectedCellY * 78);
			
			g.setColor(Color.white);
			
			drawPlayerStats(g);
			
			g.drawString("Gold: ", 1733, 239);
			g.drawString(String.valueOf(goldCounter), 1853 - Integer.toString(goldCounter).length() * 9, 239);
			
			int row = 0;
			int column = 0;
			
			for(int i = scrollOffset * amountColumns; i < inventoryList.size(); i++) {
				
				if(row >= amountRows) {
					break;
				}

				inventoryList.get(i).getItemType().getInventoryAnimation().draw(1492 + column * 78, 313 + row * 78);
				
				if(itemCountList.get(i) > 1) {
					g.drawString(itemCountList.get(i).toString(), 1550 + column * 78, 365 + row * 78);
				}
				
				if(inventoryList.get(i).isEquipped()) {
					g.drawImage(equippedItemImage, 1484 + column * 78, 305 + row * 78);
								
				}
						
				column++;
				
				if(column >= 5) {
					column = 0;
					row++;
				}
				
			}
			
			for(Item item : inventoryList) {
				if(item.isEquipped()) {
					if(item.getItemType().getItemCategory().equals("melee_slay") || item.getItemType().getItemCategory().equals("melee_thrust")) {
						item.getItemType().getInventoryAnimation().draw(52, 313);
					}
					
					if(item.getItemType().getItemCategory().equals("bow")) {
						item.getItemType().getInventoryAnimation().draw(130, 313);
					}
					
					if(item.getItemType().getItemCategory().equals("spell")) {
						item.getItemType().getInventoryAnimation().draw(208, 313);
					}
					
					if(item.getItemType().getItemCategory().equals("head")) {
						item.getItemType().getInventoryAnimation().draw(130, 469);
					}
					
					if(item.getItemType().getItemCategory().equals("chest")) {
						item.getItemType().getInventoryAnimation().draw(130, 547);
					}
					
					if(item.getItemType().getItemCategory().equals("hands")) {
						item.getItemType().getInventoryAnimation().draw(52, 547);
					}
					
					if(item.getItemType().getItemCategory().equals("hands")) {
						item.getItemType().getInventoryAnimation().draw(208, 547);
					}
					
					if(item.getItemType().getItemCategory().equals("legs")) {
						item.getItemType().getInventoryAnimation().draw(130, 625);
					}
					
					if(item.getItemType().getItemCategory().equals("feet")) {
						item.getItemType().getInventoryAnimation().draw(130, 703);
					}
				}
			}
			
			if(!inventoryList.isEmpty()) {
				
				String name = inventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getName();
				g.drawString(name, Main.WIDTH/2 - (name.length() * 9)/2, 818);
				
				g.drawString("Value in Gold:", 652, 963);
				String value = String.valueOf(inventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getValue());
				g.drawString(value, 1098 - value.length() * 9, 963);
				
				inventoryList.get(selectedCellX + (selectedCellY + scrollOffset) * amountColumns).getItemType().getDescriptionAnimation().draw(1126, 836);
				
			}
			
			drawArrow(g);
			
		}
							
	}
	
	private void drawPlayerStats(Graphics g) {
		
		g.drawString("Level: ", 299, 317);
		g.drawString("Experience: ", 299, 337);
		g.drawString("Next Level: ", 299, 357);
		g.drawString("Learning Points: ", 299, 377);
		
		g.drawString("Strength: ", 299, 417);
		g.drawString("Dexterity: ", 299, 437);
		g.drawString("Magic Knowledge: ", 299, 457);
		
		g.drawString("Health Points: ", 299, 497);
		g.drawString("Mana: ", 299, 517);
		
		g.drawString("Sword Skill: ", 299, 557);
		g.drawString("Spear Skill: ", 299, 577);
		g.drawString("Bow Skill: ", 299, 597);
		g.drawString("Spell Skill: ", 299, 617);
		
		g.drawString("Pick Locks: ", 299, 657);
		g.drawString("Take Furs: ", 299, 677);
		g.drawString("Take Trophies: ", 299, 697);
		g.drawString("HP Regeneration: ", 299, 717);
		g.drawString("Mana Regeneration: ", 299, 737);
		
		
		
		g.drawString(String.valueOf(player.getLevel()), 471, 317);
		g.drawString(String.valueOf(player.getExperience()), 471, 337);
		g.drawString(String.valueOf(player.getNextLevelExperience()), 471, 357);
		g.drawString(String.valueOf(player.getLearningPoints()), 471, 377);
		
		g.drawString(String.valueOf(player.getStrength()), 471, 417);
		g.drawString(String.valueOf(player.getDexterity()), 471, 437);
		g.drawString(String.valueOf(player.getMagicKnowledge()), 471, 457);
		
		g.drawString(String.valueOf(player.getHealthPoints()), 471, 497);
		g.drawString(String.valueOf(player.getMana()), 471, 517);
		
		g.drawString(String.valueOf(player.getSwordSkill()) + "%", 471, 557);
		g.drawString(String.valueOf(player.getSpearSkill()) + "%", 471, 577);
		g.drawString(String.valueOf(player.getBowSkill()) + "%", 471, 597);
		g.drawString(String.valueOf(player.getSpellSkill()) + "%", 471, 617);
		
		
		if(player.isPickLocks()) {
			g.drawString("Learned", 471, 657);
		} else {
			g.drawString("-", 471, 657);
		}
		
		if(player.isTakeFurs()) {
			g.drawString("Learned", 471, 677);
		} else {
			g.drawString("-", 471, 677);
		}
		
		if(player.isTakeTrophies()) {
			g.drawString("Learned", 471, 697);
		} else {
			g.drawString("-", 471, 697);
		}
		
		if(player.isHpRegeneration()) {
			g.drawString("Learned", 471, 717);
		} else {
			g.drawString("-", 471, 717);
		}
		
		if(player.isManaRegeneration()) {
			g.drawString("Learned", 471, 737);
		} else {
			g.drawString("-", 471, 737);
		}
				
	}
	
	private void drawArrow(Graphics g) {
		
		arrowUpAnimation.updateNoDraw();
		arrowDownAnimation.updateNoDraw();
		
		if(scrollOffset > 0) { 
			arrowUpAnimation.draw(1876, 305);
		}
		
		if(inventoryList.size() > amountCells + scrollOffset * amountColumns) {
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
	
	public boolean isWindowOpen() {
		return windowOpen;
	}
	
	public void incrementGoldCounter() {
		goldCounter++;
	}

	public ArrayList<Item> getInventoryList() {
		return inventoryList;
	}
	
	
	
}