package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import models.Item;

public class ItemManager {
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Item> removeList = new ArrayList<Item>();
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();
	
	public ItemManager() throws SlickException {
			
		
		Item stick = new Item(480, 416, itemTypeManager.stick);
		Item stick1 = new Item(480, 416, itemTypeManager.stick);
						
		Item dagger = new Item(480, 416, itemTypeManager.dagger);
		Item mace = new Item(480, 416, itemTypeManager.mace);
		Item ironsword = new Item(480, 416, itemTypeManager.ironsword);
		Item goldensword = new Item(480, 416, itemTypeManager.goldensword);
		Item mithrilsword = new Item(480, 416, itemTypeManager.mithrilsword);
		Item blacksword = new Item(480, 416, itemTypeManager.blacksword);
		
		Item sabre = new Item(480, 416, itemTypeManager.sabre);
		Item rapier = new Item(480, 416, itemTypeManager.rapier);
		Item mithrilrapier = new Item(480, 416, itemTypeManager.mithrilrapier);
		
		Item shortspear = new Item(480, 416, itemTypeManager.shortspear);
		Item longspear = new Item(480, 416, itemTypeManager.longspear);
		Item mithrilspear = new Item(480, 416, itemTypeManager.mithrilspear);
		Item goldenspear = new Item(480, 416, itemTypeManager.goldenspear);
		
		Item bow = new Item(480, 416, itemTypeManager.bow);
		Item longbow = new Item(480, 416, itemTypeManager.longbow);
		Item recurvedbow = new Item(480, 416, itemTypeManager.recurvedbow);
		
		Item firespell = new Item(480, 416, itemTypeManager.firespell);
		Item firespell2 = new Item(480, 416, itemTypeManager.firespell);
		Item icespell = new Item(480, 416, itemTypeManager.icespell);
		Item icespell2 = new Item(480, 416, itemTypeManager.icespell);
		
		Item chainhat = new Item(480, 416, itemTypeManager.chainhat);
		Item chainhat2 = new Item(480, 416, itemTypeManager.chainhat);
		Item chainhat3 = new Item(480, 416, itemTypeManager.chainhat);
		Item chainhelmet = new Item(480, 416, itemTypeManager.chainhelmet);
		Item chainhelmet2 = new Item(480, 416, itemTypeManager.chainhelmet);
		Item clothhood = new Item(480, 416, itemTypeManager.clothhood);
		Item goldenhelmet = new Item(480, 416, itemTypeManager.goldenhelmet);
		Item leathercap = new Item(480, 416, itemTypeManager.leathercap);
		Item ironhelmet = new Item(480, 416, itemTypeManager.ironhelmet);
		Item mithrilhelmet = new Item(480, 416, itemTypeManager.mithrilhelmet);
		
		Item shirt = new Item(480, 416, itemTypeManager.shirt);
		Item leatherchest = new Item(480, 416, itemTypeManager.leatherchest);
		Item chainchest = new Item(480, 416, itemTypeManager.chainchest);
		Item ironchest = new Item(480, 416, itemTypeManager.ironchest);
		Item goldenchest = new Item(480, 416, itemTypeManager.goldenchest);
		Item mithrilchest = new Item(480, 416, itemTypeManager.mithrilchest);

		Item irongloves = new Item(480, 416, itemTypeManager.irongloves);
		Item goldengloves = new Item(480, 416, itemTypeManager.goldengloves);
		Item mithrilgloves = new Item(480, 416, itemTypeManager.mithrilgloves);
		
		Item skirt = new Item(480, 416, itemTypeManager.skirt);
		Item irongreaves = new Item(480, 416, itemTypeManager.irongreaves);
		Item goldengreaves = new Item(480, 416, itemTypeManager.goldengreaves);
		Item mithrilgreaves = new Item(480, 416, itemTypeManager.mithrilgreaves);
				
		Item boots = new Item(480, 416, itemTypeManager.boots);
		Item leatherboots = new Item(480, 416, itemTypeManager.leatherboots);
		Item ironboots = new Item(480, 416, itemTypeManager.ironboots);
		Item goldenboots = new Item(480, 416, itemTypeManager.goldenboots);
		Item mithrilboots = new Item(480, 416, itemTypeManager.mithrilboots);
		
		
		Item gold1 = new Item(480, 416, itemTypeManager.gold);
		Item gold2 = new Item(704, 416, itemTypeManager.gold);
		Item gold3 = new Item(416, 480, itemTypeManager.gold);

		Item apple1 = new Item(544, 544, itemTypeManager.apple);
		Item apple2 = new Item(672, 544, itemTypeManager.apple);



		Item arrow1 = new Item(768, 480, itemTypeManager.arrow);
		Item arrow2 = new Item(768, 544, itemTypeManager.arrow);
		Item arrow3 = new Item(768, 608, itemTypeManager.arrow);
	
		Item bone1 = new Item(800, 320, itemTypeManager.bone);
		Item bone2 = new Item(832, 320, itemTypeManager.bone);
		Item bone3 = new Item(864, 640, itemTypeManager.bone);
		Item bone4 = new Item(896, 384, itemTypeManager.bone);
		Item multipleSticks1 = new Item(256, 0, itemTypeManager.multipleSticks);
		Item wheat1 = new Item(320, 32, itemTypeManager.wheat);
		Item trophy1 = new Item(364, 32, itemTypeManager.trophy);
		
		Item bread1 = new Item(864, 320, itemTypeManager.bread);
		Item carrot1 = new Item(736, 480, itemTypeManager.carrot);
		Item rawChicken1 = new Item(64, 1024, itemTypeManager.rawChicken);
		Item cookedChicken1 = new Item(32, 1024, itemTypeManager.cookedChicken);
		Item rawFish1 = new Item(1024, 64, itemTypeManager.rawFish);
		Item cookedFish1 = new Item(1024, 0, itemTypeManager.cookedFish);
		Item rawMeat1 = new Item(1024, 64, itemTypeManager.rawMeat);
		Item cookedMeat1 = new Item(1024, 0, itemTypeManager.cookedMeat);
		Item rawPork1 = new Item(256, 704, itemTypeManager.rawPork);
		Item cookedPork1 = new Item(128, 768, itemTypeManager.cookedPork);
		
		Item ironBar = new Item(64, 128, itemTypeManager.ironBar);
		Item goldenBar = new Item(64, 192, itemTypeManager.goldenBar);
		Item mithrilBar = new Item(64, 192, itemTypeManager.mithrilBar);
		
		Item feather1 = new Item(512, 0, itemTypeManager.feather);
		Item treasureChest1 = new Item(576, 0, itemTypeManager.treasureChest);
		Item goldCrown1 = new Item(512, 96, itemTypeManager.goldCrown);
		Item treasure1 = new Item(576, 64, itemTypeManager.treasure);
		
		Item smallHealingPotion1 = new Item(512, 128, itemTypeManager.smallHealingPotion);
		Item mediumHealingPotion1 = new Item(0, 96, itemTypeManager.mediumHealingPotion);
		Item bigHealingPotion1 = new Item(320, 32, itemTypeManager.bigHealingPotion);
		
		Item smallManaPotion1 = new Item(128, 192, itemTypeManager.smallManaPotion);
		Item mediumManaPotion1 = new Item(128, 128, itemTypeManager.mediumManaPotion);
		Item bigManaPotion1 = new Item(128, 96, itemTypeManager.bigManaPotion);
		
		Item permanentHpBonusPotion1 = new Item(128, 128, itemTypeManager.permanentHpBonusPotion);
		Item permanentManaBonusPotion1 = new Item(128, 128, itemTypeManager.permanentManaBonusPotion);
		Item strengthPotion1 = new Item(128, 128, itemTypeManager.strengthPotion);
		Item dexterityPotion1 = new Item(128, 128, itemTypeManager.dexterityPotion);
		Item wisdomPotion1 = new Item(128, 128, itemTypeManager.wisdomPotion);
		Item speedPotion1 = new Item(128, 128, itemTypeManager.speedPotion);
		
		
		Item dragonroot1 = new Item(0, 0, itemTypeManager.dragonroot);
		Item goblinweed1 = new Item(0, 32, itemTypeManager.goblinweed);
		Item godnettel1 = new Item(0, 64, itemTypeManager.godnettel);
		Item goldtruffel1 = new Item(64, 96, itemTypeManager.goldtruffel);
		Item wolfnettel1 = new Item(96, 64, itemTypeManager.wolfnettel);
		
		Item healberry1 = new Item(32, 32, itemTypeManager.healberry);
		Item healplant1 = new Item(32, 64, itemTypeManager.healplant);
		Item healroot1 = new Item(32, 96, itemTypeManager.healroot);
		
		Item manaberry1 = new Item(64, 0, itemTypeManager.manaberry);
		Item manaplant1 = new Item(96, 96, itemTypeManager.manaplant);
		Item manaroot1 = new Item(64, 64, itemTypeManager.manaroot);
		
		
		itemList.add(stick);
		itemList.add(stick1);
		
		itemList.add(dagger);
		itemList.add(mace);
		itemList.add(ironsword);
		itemList.add(goldensword);
		itemList.add(mithrilsword);
		itemList.add(blacksword);

		itemList.add(sabre);
		itemList.add(rapier);
		itemList.add(mithrilrapier);
		
		itemList.add(shortspear);
		itemList.add(longspear);
		itemList.add(mithrilspear);
		itemList.add(goldenspear);
		
		
		itemList.add(bow);
		itemList.add(longbow);
		itemList.add(recurvedbow);
		
		itemList.add(firespell);
		itemList.add(firespell2);
		itemList.add(icespell);
		itemList.add(icespell2);
		
		itemList.add(chainhat);
		itemList.add(chainhat2);
		itemList.add(chainhat3);
		itemList.add(chainhelmet);
		itemList.add(chainhelmet2);
		itemList.add(clothhood);
		itemList.add(goldenhelmet);
		itemList.add(leathercap);
		itemList.add(ironhelmet);
		itemList.add(mithrilhelmet);
		
		itemList.add(shirt);
		itemList.add(leatherchest);
		itemList.add(chainchest);
		itemList.add(ironchest);
		itemList.add(goldenchest);
		itemList.add(mithrilchest);
		
		itemList.add(irongloves);
		itemList.add(goldengloves);
		itemList.add(mithrilgloves);
		
		itemList.add(skirt);
		itemList.add(irongreaves);
		itemList.add(goldengreaves);
		itemList.add(mithrilgreaves);
				
		itemList.add(boots);
		itemList.add(leatherboots);
		itemList.add(ironboots);
		itemList.add(goldenboots);
		itemList.add(mithrilboots);
		
		itemList.add(arrow1);
		itemList.add(arrow2);
		itemList.add(arrow3);
		
		
		itemList.add(gold1);
		itemList.add(gold2);
		itemList.add(gold3);
		itemList.add(apple1);
		itemList.add(apple2);
		itemList.add(bone1);
		itemList.add(bone2);
		itemList.add(bone3);
		itemList.add(bone4);
		itemList.add(bread1);
		itemList.add(carrot1);
		itemList.add(rawChicken1);
		itemList.add(cookedChicken1);
		itemList.add(rawFish1);
		itemList.add(cookedFish1);
		itemList.add(rawMeat1);
		itemList.add(cookedMeat1);
		itemList.add(rawPork1);
		itemList.add(cookedPork1);
		
		itemList.add(ironBar);
		itemList.add(ironBar);
		itemList.add(ironBar);
		itemList.add(ironBar);
		itemList.add(ironBar);
		
		itemList.add(goldenBar);
		itemList.add(goldenBar);
		itemList.add(goldenBar);
		itemList.add(goldenBar);
		itemList.add(goldenBar);
		
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		itemList.add(mithrilBar);
		
		itemList.add(feather1);
		itemList.add(treasureChest1);
		itemList.add(goldCrown1);
		itemList.add(treasure1);
		itemList.add(smallHealingPotion1);
		itemList.add(mediumHealingPotion1);
		itemList.add(bigHealingPotion1);
		itemList.add(smallManaPotion1);
		itemList.add(mediumManaPotion1);
		itemList.add(bigManaPotion1);
		itemList.add(multipleSticks1);
		itemList.add(wheat1);
		itemList.add(trophy1);
		
		
		itemList.add(permanentHpBonusPotion1);
		itemList.add(permanentManaBonusPotion1);
		itemList.add(strengthPotion1);
		itemList.add(dexterityPotion1);
		itemList.add(wisdomPotion1);
		itemList.add(speedPotion1);
		
		
		itemList.add(dragonroot1);
		itemList.add(goblinweed1);
		itemList.add(godnettel1);
		itemList.add(goldtruffel1);
		itemList.add(wolfnettel1);
		
		itemList.add(healberry1);
		itemList.add(healplant1);
		itemList.add(healroot1);
		
		itemList.add(manaberry1);
		itemList.add(manaplant1);
		itemList.add(manaroot1);
		
	}
	
	public void update() {
		
		for(Item item : itemList) {
			item.update();
		}
		
		itemList.removeAll(removeList);
		
		removeList.clear();
		
	}

	public void render(Graphics g) {
	
		for(Item item : itemList) {
			item.render(g);
		}
		
	}
	
	public void addItem(Item item) {
		
		itemList.add(item);
		
	}
	
	public void removeItem(Item item) {
		
		removeList.add(item);
		
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
}