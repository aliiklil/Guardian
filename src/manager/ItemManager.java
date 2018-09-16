package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import models.Item;
import models.ItemType;

public class ItemManager {
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Item> removeList = new ArrayList<Item>();
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();
	
	public ItemManager() throws SlickException {
			
		
		


		Item gold1 = new Item(480, 416, itemTypeManager.gold);
		Item gold2 = new Item(704, 416, itemTypeManager.gold);
		Item gold3 = new Item(416, 480, itemTypeManager.gold);

		Item apple1 = new Item(544, 544, itemTypeManager.apple);
		Item apple2 = new Item(672, 544, itemTypeManager.apple);



		Item arrow1 = new Item(768, 480, itemTypeManager.arrow);
	
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
		
		Item ironBar1 = new Item(1280, 128, itemTypeManager.ironBar);
		
		Item goldBar1 = new Item(736, 32, itemTypeManager.goldBar);

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
	
		itemList.add(gold1);
		itemList.add(gold2);
		itemList.add(gold3);
		itemList.add(apple1);
		itemList.add(apple2);
		itemList.add(arrow1);
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
		itemList.add(goldBar1);
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