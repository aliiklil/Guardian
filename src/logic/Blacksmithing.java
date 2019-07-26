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
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Longsword (Iron Bar, Stick)")) {
			
			boolean ironBarExists = false;
			boolean stickExists = false;
			
			for(Item item : player.getInventoryList()) {
				if(item.getItemType().getName().equals("Iron Bar")) {
					ironBarExists = true;
				}
				if(item.getItemType().getName().equals("Stick")) {
					stickExists = true;
				}
			}
			
			if(ironBarExists && stickExists) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().stick);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().longsword));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Longsword.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
			
		
	}
	
}
