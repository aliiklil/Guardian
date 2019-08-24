package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import manager.MobManager;
import models.Item;
import models.Player;

public class Alchemy {

	public void checkIfPlayerIsUsingAlchemy(Dialogue currentDialogue) throws SlickException {
				
		createSmallHpPotion(currentDialogue);
		createSmallManaPotion(currentDialogue);
		createMediumHpPotion(currentDialogue);
		createMediumManaPotion(currentDialogue);
		
		createBigHpPotion(currentDialogue);
		createBigManaPotion(currentDialogue);
		createSpeedPotion(currentDialogue);
		
		createMaxHpBonusPotion(currentDialogue);
		createMaxManaBonusPotion(currentDialogue);
		createStrengthPotion(currentDialogue);
		createDexterityPotion(currentDialogue);
		
	}
	
	private void createSmallHpPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Small HP Potion (Healberry, Small Bottle)")) {
			
			int healberriesCount = 0;
			int smallBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healberry")) {
					healberriesCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Small Bottle")) {
					smallBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(healberriesCount >= 1 && smallBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healberry);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().smallBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().smallHpPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Small HP Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createSmallManaPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Small Mana Potion (Manaberry, Small Bottle)")) {
			
			int manaberriesCount = 0;
			int smallBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Manaberry")) {
					manaberriesCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Small Bottle")) {
					smallBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(manaberriesCount >= 1 && smallBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().manaberry);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().smallBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().smallManaPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Small Mana Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void createMediumHpPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Medium HP Potion (Healplant, Medium Bottle)")) {
			
			int healplantCount = 0;
			int mediumBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healplant")) {
					healplantCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Medium Bottle")) {
					mediumBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(healplantCount >= 1 && mediumBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healplant);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mediumBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mediumHpPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Medium HP Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void createMediumManaPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Medium Mana Potion (Manaplant, Medium Bottle)")) {
			
			int manaplantCount = 0;
			int mediumBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Manaplant")) {
					manaplantCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Medium Bottle")) {
					mediumBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(manaplantCount >= 1 && mediumBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().manaplant);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mediumBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mediumManaPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Medium Mana Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createBigHpPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Big HP Potion (Healroot, Big Bottle)")) {
			
			int healrootCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healroot")) {
					healrootCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(healrootCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healroot);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().bigHpPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Big HP Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	private void createBigManaPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Big Mana Potion (Manaroot, Big Bottle)")) {
			
			int manarootCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Manaroot")) {
					manarootCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(manarootCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().manaroot);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().bigManaPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Big Mana Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	private void createSpeedPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Speed Potion (Wolfnettel, Big Bottle)")) {
			
			int wolfnettelCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Wolfnettel")) {
					wolfnettelCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(wolfnettelCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().wolfnettel);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().speedPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Speed Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void createMaxHpBonusPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Max HP Bonus Potion (Healroot, Goldtruffle, Big Bottle)")) {
			
			int healrootCount = 0;
			int goldtruffleCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healroot")) {
					healrootCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Goldtruffle")) {
					goldtruffleCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(healrootCount >= 1 && goldtruffleCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healroot);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldtruffle);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().maxHpBonusPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Max HP Bonus Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	private void createMaxManaBonusPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Max Mana Bonus Potion (Manaroot, Goldtruffle, Big Bottle)")) {
			
			int manarootCount = 0;
			int goldtruffleCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Manaroot")) {
					manarootCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Goldtruffle")) {
					goldtruffleCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(manarootCount >= 1 && goldtruffleCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().manaroot);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldtruffle);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().maxManaBonusPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Max Mana Bonus Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	
	private void createStrengthPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Strength Potion (Dragonroot, Goldtruffle, Big Bottle)")) {
			
			int dragonrootCount = 0;
			int goldtruffleCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Dragonroot")) {
					dragonrootCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Goldtruffle")) {
					goldtruffleCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(dragonrootCount >= 1 && goldtruffleCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().dragonroot);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldtruffle);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().strengthPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Strength Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	
	
	private void createDexterityPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Create Dexterity Potion (Goblinweed, Goldtruffle, Big Bottle)")) {
			
			int goblinweedCount = 0;
			int goldtruffleCount = 0;
			int bigBottleCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Goblinweed")) {
					goblinweedCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Goldtruffle")) {
					goldtruffleCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Big Bottle")) {
					bigBottleCount = player.getItemCountList().get(i);
				}
			}
			
			if(goblinweedCount >= 1 && goldtruffleCount >= 1 && bigBottleCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goblinweed);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldtruffle);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bigBottle);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().dexterityPotion));
				currentDialogue.getSentences().get(1).setText("I have successfully created a Dexterity Potion.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
}
