package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import manager.CharacterManager;
import models.Item;
import models.Player;

public class Blacksmithing {

	public void checkIfPlayerIsBlacksmithing(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Sword (Iron Bar, Stick)")) {
			
			int ironBarsCount = 0;
			int stickCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Stick")) {
					stickCount = player.getItemCountList().get(i);
				}
			}
			
			if(ironBarsCount >= 1 && stickCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().stick);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().ironsword));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Iron Sword.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
		
		
		
		
		
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Helmet (3 Iron Bars)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
					break;
				}
			}
			
			if(ironBarsCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().ironhelmet));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Iron Helmet.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Chest (5 Iron Bars)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
					break;
				}
			}
			
			if(ironBarsCount >= 5) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().ironchest));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Iron Chest.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
		
		
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Gloves (2 Iron Bars)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
					break;
				}
			}
			
			if(ironBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().irongloves));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Iron Gloves.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
		
		
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Greaves (4 Iron Bars)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
					break;
				}
			}
			
			if(ironBarsCount >= 4) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().irongreaves));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Iron Greaves.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
		
		
		
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Boots (2 Iron Bars)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
					break;
				}
			}
			
			if(ironBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().ironboots));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Iron Boots.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
		
			
		
	}
	
}
