package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Item;
import models.ItemType;

public class ItemManager {
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Item> removeList = new ArrayList<Item>();
	
	public ItemManager() throws SlickException {
		
		
		ItemType woodSword = new ItemType(32, 32, 1, "resources/items/1melee/woodsword.png", "Wood Sword", 35, 1);
		ItemType stick = new ItemType(32, 32, 1, "resources/items/1melee/stick.png", "Stick", 1, 1);
		
		
		ItemType bow = new ItemType(32, 32, 1, "resources/items/2bows/bow.png", "Bow", 400, 2);
		
		
		ItemType arrow = new ItemType(32, 32, 1, "resources/items/3arrows/arrow.png", "Arrow", 10, 3);
	
		
		ItemType helmet = new ItemType(32, 32, 1, "resources/items/4armor/helmet.png", "Helmet", 250, 4);
		ItemType metalArmor = new ItemType(32, 32, 1, "resources/items/4armor/metalarmor.png", "Metal Armor", 500, 4);
		ItemType boots = new ItemType(32, 32, 1, "resources/items/4armor/boots.png", "Boots", 100, 4);
		ItemType leatherHelmet = new ItemType(32, 32, 1, "resources/items/4armor/leatherhelmet.png", "Leather Helmet", 160, 4);
		ItemType leatherArmor = new ItemType(32, 32, 1, "resources/items/4armor/leather_armor.png", "Leather Armor", 180, 4);
		
		
		ItemType verySmallHealingPotion = new ItemType(32, 32, 1,"resources/items/5healing_potions/very_small_healing_potion.png", "Very Small Healing Potion", 20, 5);
		ItemType smallHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/small_healing_potion.png", "Small Healing Potionn", 35, 5);
		ItemType mediumHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/medium_healing_potion.png", "Medium Healing Potionn", 35, 5);
		ItemType bigHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/big_healing_potion.png", "Big Healing Potion", 25, 5);
		ItemType veryBigHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/very_big_healing_potion.png", "Very Big Healing Potion", 10, 5);
		
		
		ItemType verySmallManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/very_small_mana_potion.png", "Very Small Mana Potion", 20, 6);
		ItemType smallManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/small_mana_potion.png", "Small Mana Potionn", 35, 6);
		ItemType mediumManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/medium_mana_potion.png", "Medium Mana Potionn", 35, 6);
		ItemType bigManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/big_mana_potion.png", "Big Mana Potion", 25, 6);
		ItemType veryBigManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/very_big_mana_potion.png", "Very Big Mana Potion", 10, 6);
		
		
		ItemType bread = new ItemType(32, 32, 1, "resources/items/7food/bread.png", "Bread", 20, 7);
		ItemType carrot = new ItemType(32, 32, 1, "resources/items/7food/carrot.png", "Carrot", 6, 7);
		ItemType rawChicken = new ItemType(32, 32, 1, "resources/items/7food/raw_chicken.png", "Raw Chicken", 33, 7);
		ItemType cookedChicken = new ItemType(32, 32, 1, "resources/items/7food/cooked_chicken.png", "Cooked Chicken", 33, 7);
		ItemType rawFish = new ItemType(32, 32, 1, "resources/items/7food/raw_fish.png", "Raw Fish", 26, 7);
		ItemType cookedFish = new ItemType(32, 32, 1, "resources/items/7food/cooked_fish.png", "Cooked Fish", 26, 7);
		ItemType rawMeat = new ItemType(32, 32, 1, "resources/items/7food/raw_meat.png", "Raw Meat", 38, 7);
		ItemType cookedMeat = new ItemType(32, 32, 1, "resources/items/7food/cooked_meat.png", "Cooked Meat", 38, 7);
		ItemType rawPork = new ItemType(32, 32, 1, "resources/items/7food/raw_pork.png", "Raw Pork", 29, 7);
		ItemType cookedPork = new ItemType(32, 32, 1, "resources/items/7food/cooked_pork.png", "Cooked Pork", 29, 7);
		ItemType wheat = new ItemType(32, 32, 1, "resources/items/7food/wheat.png", "Wheat", 4, 7);
		ItemType apple = new ItemType(32, 32, 1, "resources/items/7food/apple.png", "Apple", 5, 7);
		
		
		ItemType bone = new ItemType(32, 32, 1, "resources/items/8misc/bone.png", "Bone", 3, 8);
		ItemType copperBar = new ItemType(32, 32, 1, "resources/items/8misc/copper_bar.png", "Copper Bar", 80, 8);
		ItemType ironBar = new ItemType(32, 32, 1, "resources/items/8misc/iron_bar.png", "Iron Bar", 160, 8);
		ItemType goldBar = new ItemType(32, 32, 1, "resources/items/8misc/gold_bar.png", "Gold Bar", 250, 8);
		ItemType feather = new ItemType(32, 32, 1, "resources/items/8misc/feather.png", "Feather", 3, 8);
		ItemType treasureChest = new ItemType(32, 32, 1, "resources/items/8misc/treasure_chest.png", "Treasure Chest", 320, 8);
		ItemType goldCrown = new ItemType(32, 32, 1, "resources/items/8misc/gold_crown.png", "Gold Crown", 280, 8);
		ItemType treasure = new ItemType(32, 32, 1, "resources/items/8misc/treasure.png", "Treasure", 280, 8);
		ItemType multipleSticks = new ItemType(32, 32, 1, "resources/items/8misc/multiple_sticks.png", "Multiple Sticks", 5, 8);
		ItemType trophy = new ItemType(32, 32, 1, "resources/items/8misc/trophy.png", "Trophy", 250, 8);
		
		ItemType gold  = new ItemType(32, 32, 100, "resources/items/9gold/goldcoin.png", "Gold", 1, 9);
		
		
		Item helmet1 = new Item(288, 384, helmet);
		Item helmet2 = new Item(288, 480, helmet);
		Item helmet3 = new Item(416, 384, helmet);

		Item gold1 = new Item(480, 416, gold);
		Item gold2 = new Item(512, 416, gold);
		Item gold3 = new Item(416, 480, gold);

		Item apple1 = new Item(544, 544, apple);
		Item apple2 = new Item(672, 544, apple);

		Item metalArmor1 = new Item(608, 640, metalArmor);
		
		Item boots1 = new Item(640, 672, boots);

		Item woodSword1 = new Item(640, 480, woodSword);
				
		Item leatherHelmet1 = new Item(672, 480, leatherHelmet);

		Item arrow1 = new Item(768, 480, arrow);
	
		Item bone1 = new Item(800, 320, bone);
		Item bone2 = new Item(832, 320, bone);
		Item bone3 = new Item(864, 1472, bone);
		Item bone4 = new Item(896, 384, bone);

		Item bow1 = new Item(960, 256, bow);
		
		Item bread1 = new Item(864, 320, bread);

		Item carrot1 = new Item(736, 480, carrot);
		
		Item rawChicken1 = new Item(64, 1024, rawChicken);
		
		Item cookedChicken1 = new Item(32, 1024, cookedChicken);

		Item rawFish1 = new Item(1024, 64, rawFish);
		
		Item cookedFish1 = new Item(1024, 0, cookedFish);
		
		Item rawMeat1 = new Item(1024, 64, rawMeat);
		
		Item cookedMeat1 = new Item(1024, 0, cookedMeat);
		
		Item rawPork1 = new Item(256, 704, rawPork);
				
		Item cookedPork1 = new Item(128, 768, cookedPork);
		
		Item copperBar1 = new Item(1024, 512, copperBar);
		
		Item ironBar1 = new Item(1280, 128, ironBar);
		
		Item goldBar1 = new Item(736, 32, goldBar);

		Item feather1 = new Item(512, 0, feather);
		
		Item treasureChest1 = new Item(576, 0, treasureChest);
		
		Item goldCrown1 = new Item(512, 96, goldCrown);
		
		Item treasure1 = new Item(576, 64, treasure);
		
		Item verySmallHealingPotion1 = new Item(512, 128, verySmallHealingPotion);
		
		Item smallHealingPotion1 = new Item(256, 64, smallHealingPotion);
		
		Item mediumHealingPotion1 = new Item(0, 96, mediumHealingPotion);
		
		Item bigHealingPotion1 = new Item(320, 32, bigHealingPotion);
		
		Item veryBigHealingPotion1 = new Item(1280, 64, veryBigHealingPotion);
		
		Item verySmallManaPotion1 = new Item(64, 32, verySmallManaPotion);
		
		Item smallManaPotion1 = new Item(32, 64, smallManaPotion);
		
		Item mediumManaPotion1 = new Item(128, 0, mediumManaPotion);
		
		Item bigManaPotion1 = new Item(32, 0, bigManaPotion);
		
		Item veryBigManaPotion1 = new Item(64, 0, veryBigManaPotion);
		
		Item leatherArmor1 = new Item(128, 0, leatherArmor);
		
		Item multipleSticks1 = new Item(256, 0, multipleSticks);
		
		Item stick1 = new Item(320, 0, stick);
		
		Item wheat1 = new Item(320, 32, wheat);
		
		Item trophy1 = new Item(364, 32, trophy);
	
		
		
		
		itemList.add(helmet1);
		itemList.add(helmet2);
		itemList.add(helmet3);
		itemList.add(gold1);
		itemList.add(gold2);
		itemList.add(gold3);
		itemList.add(apple1);
		itemList.add(apple2);
		itemList.add(metalArmor1);
		itemList.add(boots1);
		itemList.add(woodSword1);
		itemList.add(leatherHelmet1);
		itemList.add(arrow1);
		itemList.add(bone1);
		itemList.add(bone2);
		itemList.add(bone3);
		itemList.add(bone4);
		itemList.add(bow1);
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
		itemList.add(leatherArmor1);
		itemList.add(multipleSticks1);
		itemList.add(stick1);
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