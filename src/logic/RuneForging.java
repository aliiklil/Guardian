package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import manager.CharacterManager;
import models.Item;
import models.Player;

public class RuneForging {

	public void checkIfPlayerIsUsingRuneTable(Dialogue currentDialogue) throws SlickException {
				
		createSmallHpPotion(currentDialogue);
		
	}
	
	private void createSmallHpPotion(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
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
		
}
