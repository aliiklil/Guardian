package manager;

import org.newdawn.slick.SlickException;

import models.ItemType;

public class ItemTypeManager {

	public ItemType dagger = new ItemType(32, 32, 1, "resources/items/1melee/dagger.png", "Dagger", 50, 1);
	public ItemType longspear = new ItemType(32, 32, 1, "resources/items/1melee/longspear.png", "Longspear", 200, 1);
	public ItemType dragonspear = new ItemType(32, 32, 1, "resources/items/1melee/dragonspear.png", "Dragonspear", 500, 1);
	public ItemType goldspear = new ItemType(32, 32, 1, "resources/items/1melee/goldenspear.png", "Golden Spear", 400, 1);
	public ItemType longsword = new ItemType(32, 32, 1, "resources/items/1melee/longsword.png", "Longsword", 300, 1);
	public ItemType mace = new ItemType(32, 32, 1, "resources/items/1melee/mace.png", "Mace", 100, 1);
	public ItemType rapier = new ItemType(32, 32, 1, "resources/items/1melee/rapier.png", "Rapier", 200, 1);
	public ItemType sabre = new ItemType(32, 32, 1, "resources/items/1melee/sabre.png", "Sabre", 300, 1);
	public ItemType shield = new ItemType(32, 32, 1, "resources/items/1melee/shield.png", "Shield", 100, 1);
	public ItemType shortspear = new ItemType(32, 32, 1, "resources/items/1melee/shortspear.png", "Short Spear", 50, 1);
	public ItemType stick = new ItemType(32, 32, 1, "resources/items/1melee/stick.png", "Stick", 20, 1);
	
	public ItemType bow = new ItemType(32, 32, 1, "resources/items/2bows/bow.png", "Bow", 50, 1);
	public ItemType longbow = new ItemType(32, 32, 1, "resources/items/2bows/longbow.png", "Long Bow", 200, 1);
	public ItemType recurvedbow = new ItemType(32, 32, 1, "resources/items/2bows/recurvedbow.png", "Recurved Bow", 400, 1);
	
	public ItemType arrow = new ItemType(32, 32, 1, "resources/items/3arrows/arrow.png", "Arrow", 10, 3);

	public ItemType chainhat = new ItemType(32, 32, 1, "resources/items/4helmets/chainhat.png", "Chain Hat", 100, 3);
	public ItemType chainhelmet = new ItemType(32, 32, 1, "resources/items/4helmets/chainhelmet.png", "Chain Helmet", 150, 3);
	public ItemType clothhoods = new ItemType(32, 32, 1, "resources/items/4helmets/clothhood.png", "Cloth Hood", 20, 3);
	public ItemType goldhelmet = new ItemType(32, 32, 1, "resources/items/4helmets/goldhelmet.png", "Gold Helmet", 300, 3);
	public ItemType leathercap = new ItemType(32, 32, 1, "resources/items/4helmets/leathercap.png", "Leather Cap", 50, 3);
	public ItemType metalhelmet = new ItemType(32, 32, 1, "resources/items/4helmets/metalhelmet.png", "Metal Helmet", 200, 3);

	public ItemType brownshirt = new ItemType(32, 32, 1, "resources/items/5torso/brownshirt.png", "Brown Shirt", 10, 3);
	public ItemType lilashirt = new ItemType(32, 32, 1, "resources/items/5torso/lilashirt.png", "Lila Shirt", 10, 3);
	public ItemType redshirt = new ItemType(32, 32, 1, "resources/items/5torso/redshirt.png", "Red Shirt", 10, 3);
	public ItemType leatherchest = new ItemType(32, 32, 1, "resources/items/5torso/leatherchest.png", "Leather Chest", 300, 3);
	public ItemType chainchest = new ItemType(32, 32, 1, "resources/items/5torso/chainchest.png", "Chain Chest", 500, 3);
	public ItemType metalchest = new ItemType(32, 32, 1, "resources/items/5torso/metalchest.png", "Metal Chest", 700, 3);
	public ItemType goldenchest = new ItemType(32, 32, 1, "resources/items/5torso/goldenchest.png", "Golden Chest", 1400, 3);
	
	public ItemType leathergloves = new ItemType(32, 32, 1, "resources/items/6hands/leathergloves.png", "Leather Gloves", 300, 3);
	public ItemType metalgloves = new ItemType(32, 32, 1, "resources/items/6hands/metalgloves.png", "Metal Gloves", 600, 3);
	public ItemType goldengloves = new ItemType(32, 32, 1, "resources/items/6hands/goldengloves.png", "Golden Gloves", 900, 3);
	
	public ItemType pants = new ItemType(32, 32, 1, "resources/items/7legarmor/pants.png", "Pants", 50, 3);
	public ItemType skirt = new ItemType(32, 32, 1, "resources/items/7legarmor/skirt.png", "Skirt", 100, 3);
	public ItemType metallegarmor = new ItemType(32, 32, 1, "resources/items/7legarmor/metallegarmor.png", "Metal Leg Armor", 300, 3);
	public ItemType goldenlegarmor = new ItemType(32, 32, 1, "resources/items/7legarmor/goldenlegarmor.png", "Golden Leg Armor", 1200, 3);
	
	public ItemType boots = new ItemType(32, 32, 1, "resources/items/8boots/boots.png", "Boots", 100, 3);
	public ItemType leatherboots = new ItemType(32, 32, 1, "resources/items/8boots/leatherboots.png", "Leather Boots", 200, 3);
	public ItemType metalboots = new ItemType(32, 32, 1, "resources/items/8boots/metalboots.png", "Metal Boots", 500, 3);
	public ItemType goldenboots = new ItemType(32, 32, 1, "resources/items/8boots/goldenboots.png", "Golden Boots", 700, 3);

	public ItemType verySmallHealingPotion = new ItemType(32, 32, 1,"resources/items/9healing_potions/very_small_healing_potion.png", "Very Small Healing Potion", 20, 5);
	public ItemType smallHealingPotion = new ItemType(32, 32, 1, "resources/items/9healing_potions/small_healing_potion.png", "Small Healing Potionn", 35, 5);
	public ItemType mediumHealingPotion = new ItemType(32, 32, 1, "resources/items/9healing_potions/medium_healing_potion.png", "Medium Healing Potionn", 35, 5);
	public ItemType bigHealingPotion = new ItemType(32, 32, 1, "resources/items/9healing_potions/big_healing_potion.png", "Big Healing Potion", 25, 5);
	public ItemType veryBigHealingPotion = new ItemType(32, 32, 1, "resources/items/9healing_potions/very_big_healing_potion.png", "Very Big Healing Potion", 10, 5);
	
	public ItemType verySmallManaPotion = new ItemType(32, 32, 1, "resources/items/10mana_potions/very_small_mana_potion.png", "Very Small Mana Potion", 20, 6);
	public ItemType smallManaPotion = new ItemType(32, 32, 1, "resources/items/10mana_potions/small_mana_potion.png", "Small Mana Potionn", 35, 6);
	public ItemType mediumManaPotion = new ItemType(32, 32, 1, "resources/items/10mana_potions/medium_mana_potion.png", "Medium Mana Potionn", 35, 6);
	public ItemType bigManaPotion = new ItemType(32, 32, 1, "resources/items/10mana_potions/big_mana_potion.png", "Big Mana Potion", 25, 6);
	public ItemType veryBigManaPotion = new ItemType(32, 32, 1, "resources/items/10mana_potions/very_big_mana_potion.png", "Very Big Mana Potion", 10, 6);
	
	public ItemType bread = new ItemType(32, 32, 1, "resources/items/11food/bread.png", "Bread", 20, 7);
	public ItemType carrot = new ItemType(32, 32, 1, "resources/items/11food/carrot.png", "Carrot", 6, 7);
	public ItemType rawChicken = new ItemType(32, 32, 1, "resources/items/11food/raw_chicken.png", "Raw Chicken", 33, 7);
	public ItemType cookedChicken = new ItemType(32, 32, 1, "resources/items/11food/cooked_chicken.png", "Cooked Chicken", 33, 7);
	public ItemType rawFish = new ItemType(32, 32, 1, "resources/items/11food/raw_fish.png", "Raw Fish", 26, 7);
	public ItemType cookedFish = new ItemType(32, 32, 1, "resources/items/11food/cooked_fish.png", "Cooked Fish", 26, 7);
	public ItemType rawMeat = new ItemType(32, 32, 1, "resources/items/11food/raw_meat.png", "Raw Meat", 38, 7);
	public ItemType cookedMeat = new ItemType(32, 32, 1, "resources/items/11food/cooked_meat.png", "Cooked Meat", 38, 7);
	public ItemType rawPork = new ItemType(32, 32, 1, "resources/items/11food/raw_pork.png", "Raw Pork", 29, 7);
	public ItemType cookedPork = new ItemType(32, 32, 1, "resources/items/11food/cooked_pork.png", "Cooked Pork", 29, 7);
	public ItemType wheat = new ItemType(32, 32, 1, "resources/items/11food/wheat.png", "Wheat", 4, 7);
	public ItemType apple = new ItemType(32, 32, 1, "resources/items/11food/apple.png", "Apple", 5, 7);
	
	public ItemType bone = new ItemType(32, 32, 1, "resources/items/12misc/bone.png", "Bone", 3, 8);
	public ItemType copperBar = new ItemType(32, 32, 1, "resources/items/12misc/copper_bar.png", "Copper Bar", 80, 8);
	public ItemType ironBar = new ItemType(32, 32, 1, "resources/items/12misc/iron_bar.png", "Iron Bar", 160, 8);
	public ItemType goldBar = new ItemType(32, 32, 1, "resources/items/12misc/gold_bar.png", "Gold Bar", 250, 8);
	public ItemType feather = new ItemType(32, 32, 1, "resources/items/12misc/feather.png", "Feather", 3, 8);
	public ItemType treasureChest = new ItemType(32, 32, 1, "resources/items/12misc/treasure_chest.png", "Treasure Chest", 320, 8);
	public ItemType goldCrown = new ItemType(32, 32, 1, "resources/items/12misc/gold_crown.png", "Gold Crown", 280, 8);
	public ItemType treasure = new ItemType(32, 32, 1, "resources/items/12misc/treasure.png", "Treasure", 280, 8);
	public ItemType multipleSticks = new ItemType(32, 32, 1, "resources/items/12misc/multiple_sticks.png", "Multiple Sticks", 5, 8);
	public ItemType trophy = new ItemType(32, 32, 1, "resources/items/12misc/trophy.png", "Trophy", 250, 8);
	
	public ItemType gold  = new ItemType(32, 32, 100, "resources/items/13gold/goldcoin.png", "Gold", 1, 9);
	
	public ItemTypeManager() throws SlickException {
		
	}
	
}
