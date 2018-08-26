package manager;

import org.newdawn.slick.SlickException;

import models.ItemType;

public class ItemTypeManager {

	public ItemType woodSword = new ItemType(32, 32, 1, "resources/items/1melee/woodsword.png", "Wood Sword", 35, 1);
	public ItemType stick = new ItemType(32, 32, 1, "resources/items/1melee/stick.png", "Stick", 1, 1);
	
	public ItemType bow = new ItemType(32, 32, 1, "resources/items/2bows/bow.png", "Bow", 400, 2);
	
	public ItemType arrow = new ItemType(32, 32, 1, "resources/items/3arrows/arrow.png", "Arrow", 10, 3);

	public ItemType helmet = new ItemType(32, 32, 1, "resources/items/4armor/helmet.png", "Helmet", 250, 4);
	public ItemType metalArmor = new ItemType(32, 32, 1, "resources/items/4armor/metalarmor.png", "Metal Armor", 500, 4);
	public ItemType boots = new ItemType(32, 32, 1, "resources/items/4armor/boots.png", "Boots", 100, 4);
	public ItemType leatherHelmet = new ItemType(32, 32, 1, "resources/items/4armor/leatherhelmet.png", "Leather Helmet", 160, 4);
	public ItemType leatherArmor = new ItemType(32, 32, 1, "resources/items/4armor/leather_armor.png", "Leather Armor", 180, 4);
	
	public ItemType verySmallHealingPotion = new ItemType(32, 32, 1,"resources/items/5healing_potions/very_small_healing_potion.png", "Very Small Healing Potion", 20, 5);
	public ItemType smallHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/small_healing_potion.png", "Small Healing Potionn", 35, 5);
	public ItemType mediumHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/medium_healing_potion.png", "Medium Healing Potionn", 35, 5);
	public ItemType bigHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/big_healing_potion.png", "Big Healing Potion", 25, 5);
	public ItemType veryBigHealingPotion = new ItemType(32, 32, 1, "resources/items/5healing_potions/very_big_healing_potion.png", "Very Big Healing Potion", 10, 5);
	
	public ItemType verySmallManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/very_small_mana_potion.png", "Very Small Mana Potion", 20, 6);
	public ItemType smallManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/small_mana_potion.png", "Small Mana Potionn", 35, 6);
	public ItemType mediumManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/medium_mana_potion.png", "Medium Mana Potionn", 35, 6);
	public ItemType bigManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/big_mana_potion.png", "Big Mana Potion", 25, 6);
	public ItemType veryBigManaPotion = new ItemType(32, 32, 1, "resources/items/6mana_potions/very_big_mana_potion.png", "Very Big Mana Potion", 10, 6);
	
	public ItemType bread = new ItemType(32, 32, 1, "resources/items/7food/bread.png", "Bread", 20, 7);
	public ItemType carrot = new ItemType(32, 32, 1, "resources/items/7food/carrot.png", "Carrot", 6, 7);
	public ItemType rawChicken = new ItemType(32, 32, 1, "resources/items/7food/raw_chicken.png", "Raw Chicken", 33, 7);
	public ItemType cookedChicken = new ItemType(32, 32, 1, "resources/items/7food/cooked_chicken.png", "Cooked Chicken", 33, 7);
	public ItemType rawFish = new ItemType(32, 32, 1, "resources/items/7food/raw_fish.png", "Raw Fish", 26, 7);
	public ItemType cookedFish = new ItemType(32, 32, 1, "resources/items/7food/cooked_fish.png", "Cooked Fish", 26, 7);
	public ItemType rawMeat = new ItemType(32, 32, 1, "resources/items/7food/raw_meat.png", "Raw Meat", 38, 7);
	public ItemType cookedMeat = new ItemType(32, 32, 1, "resources/items/7food/cooked_meat.png", "Cooked Meat", 38, 7);
	public ItemType rawPork = new ItemType(32, 32, 1, "resources/items/7food/raw_pork.png", "Raw Pork", 29, 7);
	public ItemType cookedPork = new ItemType(32, 32, 1, "resources/items/7food/cooked_pork.png", "Cooked Pork", 29, 7);
	public ItemType wheat = new ItemType(32, 32, 1, "resources/items/7food/wheat.png", "Wheat", 4, 7);
	public ItemType apple = new ItemType(32, 32, 1, "resources/items/7food/apple.png", "Apple", 5, 7);
	
	public ItemType bone = new ItemType(32, 32, 1, "resources/items/8misc/bone.png", "Bone", 3, 8);
	public ItemType copperBar = new ItemType(32, 32, 1, "resources/items/8misc/copper_bar.png", "Copper Bar", 80, 8);
	public ItemType ironBar = new ItemType(32, 32, 1, "resources/items/8misc/iron_bar.png", "Iron Bar", 160, 8);
	public ItemType goldBar = new ItemType(32, 32, 1, "resources/items/8misc/gold_bar.png", "Gold Bar", 250, 8);
	public ItemType feather = new ItemType(32, 32, 1, "resources/items/8misc/feather.png", "Feather", 3, 8);
	public ItemType treasureChest = new ItemType(32, 32, 1, "resources/items/8misc/treasure_chest.png", "Treasure Chest", 320, 8);
	public ItemType goldCrown = new ItemType(32, 32, 1, "resources/items/8misc/gold_crown.png", "Gold Crown", 280, 8);
	public ItemType treasure = new ItemType(32, 32, 1, "resources/items/8misc/treasure.png", "Treasure", 280, 8);
	public ItemType multipleSticks = new ItemType(32, 32, 1, "resources/items/8misc/multiple_sticks.png", "Multiple Sticks", 5, 8);
	public ItemType trophy = new ItemType(32, 32, 1, "resources/items/8misc/trophy.png", "Trophy", 250, 8);
	
	public ItemType gold  = new ItemType(32, 32, 100, "resources/items/9gold/goldcoin.png", "Gold", 1, 9);
	
	public ItemTypeManager() throws SlickException {
		
	}
	
}
