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
		Item diamondsword = new Item(480, 416, itemTypeManager.diamondsword);
		Item blacksword = new Item(480, 416, itemTypeManager.blacksword);
		
		
		Item rapier = new Item(480, 416, itemTypeManager.rapier);
		Item sabre = new Item(480, 416, itemTypeManager.sabre);
		
		Item shortspear = new Item(480, 416, itemTypeManager.shortspear);
		Item longspear = new Item(480, 416, itemTypeManager.longspear);
		Item dragonspear = new Item(480, 416, itemTypeManager.dragonspear);
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
		Item diamondhelmet = new Item(480, 416, itemTypeManager.diamondhelmet);
		
		Item shirt = new Item(480, 416, itemTypeManager.shirt);
		Item leatherchest = new Item(480, 416, itemTypeManager.leatherchest);
		Item chainchest = new Item(480, 416, itemTypeManager.chainchest);
		Item ironchest = new Item(480, 416, itemTypeManager.ironchest);
		Item goldenchest = new Item(480, 416, itemTypeManager.goldenchest);
		Item diamondchest = new Item(480, 416, itemTypeManager.diamondchest);

		Item irongloves = new Item(480, 416, itemTypeManager.irongloves);
		Item goldengloves = new Item(480, 416, itemTypeManager.goldengloves);
		Item diamondgloves = new Item(480, 416, itemTypeManager.diamondgloves);
		
		Item skirt = new Item(480, 416, itemTypeManager.skirt);
		Item irongreaves = new Item(480, 416, itemTypeManager.irongreaves);
		Item goldengreaves = new Item(480, 416, itemTypeManager.goldengreaves);
		Item diamondgreaves = new Item(480, 416, itemTypeManager.diamondgreaves);
				
		Item boots = new Item(480, 416, itemTypeManager.boots);
		Item leatherboots = new Item(480, 416, itemTypeManager.leatherboots);
		Item ironboots = new Item(480, 416, itemTypeManager.ironboots);
		Item goldenboots = new Item(480, 416, itemTypeManager.goldenboots);
		Item diamondboots = new Item(480, 416, itemTypeManager.diamondboots);
		
		
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
		
		Item copperBar1 = new Item(1024, 512, itemTypeManager.copperBar);
		
		Item ironBar1 = new Item(64, 128, itemTypeManager.ironBar);
		Item ironBar2 = new Item(64, 128, itemTypeManager.ironBar);
		Item ironBar3 = new Item(64, 128, itemTypeManager.ironBar);
		Item ironBar4 = new Item(64, 128, itemTypeManager.ironBar);
		Item ironBar5 = new Item(64, 128, itemTypeManager.ironBar);
		
		Item goldenBar1 = new Item(64, 192, itemTypeManager.goldenBar);
		Item goldenBar2 = new Item(64, 192, itemTypeManager.goldenBar);
		Item goldenBar3 = new Item(64, 192, itemTypeManager.goldenBar);
		Item goldenBar4 = new Item(64, 192, itemTypeManager.goldenBar);
		Item goldenBar5 = new Item(64, 192, itemTypeManager.goldenBar);

		Item feather1 = new Item(512, 0, itemTypeManager.feather);
		
		Item treasureChest1 = new Item(576, 0, itemTypeManager.treasureChest);
		
		Item goldCrown1 = new Item(512, 96, itemTypeManager.goldCrown);
		
		Item treasure1 = new Item(576, 64, itemTypeManager.treasure);
		
		Item verySmallHealingPotion1 = new Item(512, 128, itemTypeManager.verySmallHealingPotion);
		
		Item smallHealingPotion1 = new Item(256, 64, itemTypeManager.smallHealingPotion);
		
		Item mediumHealingPotion1 = new Item(0, 96, itemTypeManager.mediumHealingPotion);
		
		Item bigHealingPotion1 = new Item(320, 32, itemTypeManager.bigHealingPotion);
		
		Item veryBigHealingPotion1 = new Item(1280, 64, itemTypeManager.veryBigHealingPotion);
		
		Item verySmallManaPotion1 = new Item(64, 32, itemTypeManager.verySmallManaPotion);
		
		Item smallManaPotion1 = new Item(32, 64, itemTypeManager.smallManaPotion);
		
		Item mediumManaPotion1 = new Item(128, 0, itemTypeManager.mediumManaPotion);
		
		Item bigManaPotion1 = new Item(32, 0, itemTypeManager.bigManaPotion);
		
		Item veryBigManaPotion1 = new Item(64, 0, itemTypeManager.veryBigManaPotion);
		
		
		Item multipleSticks1 = new Item(256, 0, itemTypeManager.multipleSticks);
		
		
		Item wheat1 = new Item(320, 32, itemTypeManager.wheat);
		
		
		Item trophy1 = new Item(364, 32, itemTypeManager.trophy);

		itemList.add(stick);
		itemList.add(stick1);
		
		itemList.add(dagger);
		itemList.add(mace);
		itemList.add(ironsword);
		itemList.add(goldensword);
		itemList.add(diamondsword);
		itemList.add(blacksword);

		itemList.add(rapier);
		itemList.add(sabre);
		
		itemList.add(shortspear);
		itemList.add(longspear);
		itemList.add(dragonspear);
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
		itemList.add(diamondhelmet);
		
		itemList.add(shirt);
		itemList.add(leatherchest);
		itemList.add(chainchest);
		itemList.add(ironchest);
		itemList.add(goldenchest);
		itemList.add(diamondchest);
		
		itemList.add(irongloves);
		itemList.add(goldengloves);
		itemList.add(diamondgloves);
		
		itemList.add(skirt);
		itemList.add(irongreaves);
		itemList.add(goldengreaves);
		itemList.add(diamondgreaves);
				
		itemList.add(boots);
		itemList.add(leatherboots);
		itemList.add(ironboots);
		itemList.add(goldenboots);
		itemList.add(diamondboots);
		
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
		itemList.add(copperBar1);
		
		itemList.add(ironBar1);
		itemList.add(ironBar2);
		itemList.add(ironBar3);
		itemList.add(ironBar4);
		itemList.add(ironBar5);
		
		itemList.add(goldenBar1);
		itemList.add(goldenBar1);
		itemList.add(goldenBar1);
		itemList.add(goldenBar1);
		itemList.add(goldenBar1);
		
		
		itemList.add(feather1);
		itemList.add(treasureChest1);
		itemList.add(goldCrown1);
		itemList.add(treasure1);
		itemList.add(verySmallHealingPotion1);
		itemList.add(smallHealingPotion1);
		itemList.add(mediumHealingPotion1);
		itemList.add(bigHealingPotion1);
		itemList.add(veryBigHealingPotion1);
		itemList.add(verySmallManaPotion1);
		itemList.add(smallManaPotion1);
		itemList.add(mediumManaPotion1);
		itemList.add(bigManaPotion1);
		itemList.add(veryBigManaPotion1);
		itemList.add(multipleSticks1);
		itemList.add(wheat1);
		itemList.add(trophy1);
		
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