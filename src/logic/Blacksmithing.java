package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import manager.MobManager;
import models.Item;
import models.Player;

public class Blacksmithing {

	public void checkIfPlayerIsBlacksmithing(Dialogue currentDialogue) throws SlickException {
		
		forgeIronSword(currentDialogue);
		forgeLongspear(currentDialogue);
		forgeSabre(currentDialogue);
		forgeIronHelmet(currentDialogue);
		forgeIronChest(currentDialogue);
		forgeIronGloves(currentDialogue);
		forgeIronGreaves(currentDialogue);
		forgeIronBoots(currentDialogue);
		
		
		forgeGoldenSword(currentDialogue);
		forgeGoldenSpear(currentDialogue);
		forgeRapier(currentDialogue);
		forgeGoldenHelmet(currentDialogue);
		forgeGoldenChest(currentDialogue);
		forgeGoldenGloves(currentDialogue);
		forgeGoldenGreaves(currentDialogue);
		forgeGoldenBoots(currentDialogue);

		
		forgeMithrilSword(currentDialogue);
		forgeMithrilSpear(currentDialogue);
		forgeMithrilRapier(currentDialogue);
		forgeMithrilHelmet(currentDialogue);
		forgeMithrilChest(currentDialogue);
		forgeMithrilGloves(currentDialogue);
		forgeMithrilGreaves(currentDialogue);
		forgeMithrilBoots(currentDialogue);
			
	}
	
	private void forgeIronSword(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Iron Sword (3 Iron Bar)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(ironBarsCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().ironsword));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Iron Sword.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeLongspear(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Longspear (Iron Bar, 3 Stick)")) {
			
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
			
			if(ironBarsCount >= 1 && stickCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().stick);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().stick);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().stick);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().longspear));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Longspear.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeSabre(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Sabre (2 Iron Bar)")) {
			
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(ironBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().sabre));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Sabre.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeIronHelmet(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
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
		
	}
	
	
	private void forgeIronChest(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
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
	
	}
	
	
	private void forgeIronGloves(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
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
	
	}
	
	
	
	
	private void forgeIronGreaves(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
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
	
	}
	
	
	
	private void forgeIronBoots(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void forgeGoldenSword(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Sword (3 Golden Bars)")) {
			
			int goldenBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldensword));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Golden Sword.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeGoldenSpear(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Spear (3 Golden Bars, 2 Iron Bars)")) {
			
			int goldenBarsCount = 0;
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 3 && ironBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldenspear));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Golden Spear.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
			
	}
	
	
	private void forgeRapier(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Rapier (1 Golden Bar, 1 Iron Bar)")) {
			
			int goldenBarsCount = 0;
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 1 && ironBarsCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().rapier));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Rapier.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeGoldenHelmet(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Helmet (3 Golden Bars)")) {
				
			int goldenBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldenhelmet));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Golden Helmet.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeGoldenChest(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Chest (5 Golden Bars)")) {
				
			int goldenBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 5) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldenchest));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Golden Chest.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeGoldenGloves(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Gloves (2 Golden Bars)")) {
				
			int goldenBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldengloves));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Golden Gloves.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	private void forgeGoldenGreaves(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Greaves (4 Golden Bars)")) {
				
			int goldenBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 4) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldengreaves));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Golden Greaves.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeGoldenBoots(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Golden Boots (2 Golden Bars)")) {
				
			int goldenBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Golden Bar")) {
					goldenBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(goldenBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().goldenBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().goldenboots));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Golden Boots.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void forgeMithrilSword(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Sword (3 Mithril Bars)")) {
			
			int mithrilBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);

				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilsword));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Mithril Sword.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeMithrilSpear(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Spear (3 Mithril Bars, 2 Iron Bars)")) {
			
			int mithrilBarsCount = 0;
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 3 && ironBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilspear));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Mithril Spear.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
			
	}
	
	
	private void forgeMithrilRapier(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Rapier (1 Mithril Bar, 1 Iron Bar)")) {
			
			int mithrilBarsCount = 0;
			int ironBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Iron Bar")) {
					ironBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 1 && ironBarsCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().ironBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilrapier));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Mithril Rapier.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeMithrilHelmet(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Helmet (3 Mithril Bars)")) {
				
			int mithrilBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 3) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilhelmet));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Mithril Helmet.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeMithrilChest(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Chest (5 Mithril Bars)")) {
				
			int mithrilBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 5) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilchest));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Mithril Chest.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeMithrilGloves(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Gloves (2 Mithril Bars)")) {
				
			int mithrilBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilgloves));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Mithril Gloves.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	
	private void forgeMithrilGreaves(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Greaves (4 Mithril Bars)")) {
				
			int mithrilBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 4) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilgreaves));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Mithril Greaves.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
	private void forgeMithrilBoots(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
	
		if(currentDialogue.getSentences().get(0).getText().equals("Forge Mithril Boots (2 Mithril Bars)")) {
				
			int mithrilBarsCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Mithril Bar")) {
					mithrilBarsCount = player.getItemCountList().get(i);
				}
			}
			
			if(mithrilBarsCount >= 2) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().mithrilBar);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().mithrilboots));
				currentDialogue.getSentences().get(1).setText("I have successfully forged Mithril Boots.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	
}
