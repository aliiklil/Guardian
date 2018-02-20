package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Item;

public class ItemManager {
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Item> removeList = new ArrayList<Item>();
	
	private Item helmet = new Item(288, 384, 32, 32, 100, "resources/items/helmet.png", "Helmet", 250);
	
	private Item helmet1 = new Item(288, 480, 32, 32, 100, "resources/items/helmet.png", "Helmet", 250);
	
	private Item helmet2 = new Item(416, 384, 32, 32, 100, "resources/items/helmet.png", "Helmet", 250);
	
	private Item gold = new Item(480, 416, 32, 32, 100, "resources/items/goldcoin.png", "Gold", 1);
	private Item gold1 = new Item(512, 416, 32, 32, 100, "resources/items/goldcoin.png", "Gold", 1);
	private Item gold2 = new Item(416, 480, 32, 32, 100, "resources/items/goldcoin.png", "Gold", 1);
	
	private Item apple = new Item(544, 544, 32, 32, 100, "resources/items/apple.png", "Apple", 5);
	private Item apple1 = new Item(672, 544, 32, 32, 100, "resources/items/apple.png", "Apple", 5);
	
	private Item metalArmor = new Item(608, 640, 32, 32, 100, "resources/items/metalarmor.png", "Metal Armor", 500);
	
	private Item boots = new Item(640, 672, 32, 32, 100, "resources/items/boots.png", "Boots", 100);
	
	private Item woodSword = new Item(640, 480, 32, 32, 100, "resources/items/woodsword.png", "Wood Sword", 35);
	
	private Item leatherHelmet = new Item(672, 480, 32, 32, 100, "resources/items/leatherhelmet.png", "Leather Helmet", 160);
	

	
	private Item arrow = new Item(768, 480, 32, 32, 100, "resources/items/arrow.png", "Arrow", 10);
	
	private Item bone = new Item(800, 320, 32, 32, 100, "resources/items/bone.png", "Bone", 3);
	private Item bone1 = new Item(832, 320, 32, 32, 100, "resources/items/bone.png", "Bone", 3);
	private Item bone2 = new Item(864, 1472, 32, 32, 100, "resources/items/bone.png", "Bone", 3);
	private Item bone3 = new Item(896, 384, 32, 32, 100, "resources/items/bone.png", "Bone", 3);
	
	private Item bow = new Item(960, 256, 32, 32, 100, "resources/items/bow.png", "Bow", 400);

	private Item bread = new Item(864, 320, 32, 32, 100, "resources/items/bread.png", "Bread", 20);
	
	private Item carrot = new Item(736, 480, 32, 32, 100, "resources/items/carrot.png", "Carrot", 6);
	
	private Item rawChicken = new Item(64, 1024, 32, 32, 100, "resources/items/raw_chicken.png", "Raw Chicken", 33);
	private Item cookedChicken = new Item(32, 1024, 32, 32, 100, "resources/items/cooked_chicken.png", "Cooked Chicken", 33);
	
	private Item rawFish = new Item(1024, 64, 32, 32, 100, "resources/items/raw_fish.png", "Raw Fish", 26);
	private Item cookedFish = new Item(1024, 0, 32, 32, 100, "resources/items/cooked_fish.png", "Cooked Fish", 26);
	
	private Item rawMeat = new Item(1024, 64, 32, 32, 100, "resources/items/raw_meat.png", "Raw Meat", 38);
	private Item cookedMeat = new Item(1024, 0, 32, 32, 100, "resources/items/cooked_meat.png", "Cooked Meat", 38);
	
	private Item rawPork = new Item(256, 704, 32, 32, 100, "resources/items/raw_pork.png", "Raw Pork", 29);
	private Item cookedPork = new Item(128, 768, 32, 32, 100, "resources/items/cooked_pork.png", "Cooked Pork", 29);
	
	private Item copperBar = new Item(1024, 512, 32, 32, 100, "resources/items/copper_bar.png", "Copper Bar", 80);
	private Item ironBar = new Item(1280, 128, 32, 32, 100, "resources/items/iron_bar.png", "Iron Bar", 160);
	private Item goldBar = new Item(736, 32, 32, 32, 100, "resources/items/gold_bar.png", "Gold Bar", 250);
	
	private Item feather = new Item(512, 0, 32, 32, 100, "resources/items/feather.png", "Feather", 3);
	
	private Item treasureChest = new Item(576, 0, 32, 32, 100, "resources/items/treasure_chest.png", "Treasure Chest", 320);
	private Item goldCrown = new Item(512, 96, 32, 32, 100, "resources/items/gold_crown.png", "Gold Crown", 280);
	private Item treasure = new Item(576, 64, 32, 32, 100, "resources/items/treasure.png", "Treasure", 280);
	
	private Item verySmallHealingPotion = new Item(512, 128, 32, 32, 100, "resources/items/healing_potions/very_small_healing_potion.png", "Very Small Healing Potion", 20);
	private Item smallHealingPotion = new Item(256, 64, 32, 32, 100, "resources/items/healing_potions/small_healing_potion.png", "Small Healing Potionn", 35);
	private Item mediumHealingPotion = new Item(0, 96, 32, 32, 100, "resources/items/healing_potions/medium_healing_potion.png", "Medium Healing Potionn", 35);	
	private Item bigHealingPotion = new Item(320, 32, 32, 32, 100, "resources/items/healing_potions/big_healing_potion.png", "Big Healing Potion", 25);
	private Item veryBigHealingPotion = new Item(1280, 64, 32, 32, 100, "resources/items/healing_potions/very_big_healing_potion.png", "Very Big Healing Potion", 10);
	
	private Item verySmallManaPotion = new Item(64, 32, 32, 32, 100, "resources/items/mana_potions/very_small_mana_potion.png", "Very Small Mana Potion", 20);
	private Item smallManaPotion = new Item(32, 64, 32, 32, 100, "resources/items/mana_potions/small_mana_potion.png", "Small Mana Potionn", 35);
	private Item mediumManaPotion = new Item(128, 0, 32, 32, 100, "resources/items/mana_potions/medium_mana_potion.png", "Medium Mana Potionn", 35);	
	private Item bigManaPotion = new Item(32, 0, 32, 32, 100, "resources/items/mana_potions/big_mana_potion.png", "Big Mana Potion", 25);
	private Item veryBigManaPotion = new Item(64, 0, 32, 32, 100, "resources/items/mana_potions/very_big_mana_potion.png", "Very Big Mana Potion", 10);
	
	private Item leatherArmor = new Item(128, 0, 32, 32, 100, "resources/items/leather_armor.png", "Leather Armor", 180);
	
	private Item multipleSticks = new Item(256, 0, 32, 32, 100, "resources/items/multiple_sticks.png", "Multiple Sticks", 5);
	private Item stick = new Item(320, 0, 32, 32, 100, "resources/items/stick.png", "Stick", 1);
	
	private Item wheat = new Item(320, 32, 32, 32, 100, "resources/items/wheat.png", "Wheat", 4);
	

	public ItemManager() throws SlickException {

		itemList.add(helmet);
		itemList.add(helmet1);
		itemList.add(helmet2);
		
		itemList.add(gold);
		itemList.add(gold1);
		itemList.add(gold2);
		
		itemList.add(apple);
		itemList.add(apple1);
		
		itemList.add(metalArmor);
		
		itemList.add(boots);
		itemList.add(woodSword);
		itemList.add(leatherHelmet);


		
		
		
		itemList.add(arrow);
		itemList.add(bone);
		itemList.add(bone1);
		itemList.add(bone2);
		itemList.add(bone3);
		itemList.add(bow);
		itemList.add(bread);
		itemList.add(carrot);
		itemList.add(rawChicken);
		itemList.add(cookedChicken);
		itemList.add(rawFish);
		itemList.add(cookedFish);
		itemList.add(rawMeat);
		itemList.add(cookedMeat);
		itemList.add(rawPork);
		itemList.add(cookedPork);
		itemList.add(copperBar);
		itemList.add(ironBar);
		itemList.add(goldBar);
		itemList.add(feather);
		itemList.add(treasureChest);
		itemList.add(goldCrown);
		itemList.add(treasure);
		itemList.add(verySmallHealingPotion);
		itemList.add(smallHealingPotion);
		itemList.add(mediumHealingPotion);
		itemList.add(bigHealingPotion);
		itemList.add(veryBigHealingPotion);
		itemList.add(verySmallManaPotion);
		itemList.add(smallManaPotion);
		itemList.add(mediumManaPotion);
		itemList.add(bigManaPotion);
		itemList.add(veryBigManaPotion);
		itemList.add(leatherArmor);
		itemList.add(multipleSticks);
		itemList.add(stick);
		itemList.add(wheat);
		
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