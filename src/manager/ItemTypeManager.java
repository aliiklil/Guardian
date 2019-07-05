package manager;

import org.newdawn.slick.SlickException;

import models.ItemType;

public class ItemTypeManager {

	public ItemType stick = new ItemType(1, "resources/items/1melee/stick.png", "Stick", 20, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/stick.png", 10, 0, 0, 15, 0, 0);
	public ItemType dagger = new ItemType(1, "resources/items/1melee/dagger.png", "Dagger", 50, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/dagger.png", 20, 0, 0, 25, 0, 0);
	public ItemType mace = new ItemType(1, "resources/items/1melee/mace.png", "Mace", 100, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/mace.png", 30, 0, 0, 35, 0, 0);
	public ItemType longsword = new ItemType(1, "resources/items/1melee/longsword.png", "Longsword", 300, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/longsword.png", 40, 0, 0, 75, 0, 0);

	public ItemType sabre = new ItemType(1, "resources/items/1melee/sabre.png", "Sabre", 200, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/sabre.png", 0, 20, 0, 35, 0, 0);
	public ItemType rapier = new ItemType(1, "resources/items/1melee/rapier.png", "Rapier", 400, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/rapier.png", 0, 40, 0, 55, 0, 0);

	public ItemType shortspear = new ItemType(1, "resources/items/1melee/shortspear.png", "Short Spear", 50, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/shortspear.png", 10, 0, 0, 25, 0, 0);
	public ItemType longspear = new ItemType(1, "resources/items/1melee/longspear.png", "Longspear", 200, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/longspear.png", 20, 0, 0, 35, 0, 0);
	public ItemType dragonspear = new ItemType(1, "resources/items/1melee/dragonspear.png", "Dragonspear", 400, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/dragonspear.png", 30, 0, 0, 65, 0, 0);
	public ItemType goldspear = new ItemType(1, "resources/items/1melee/goldenspear.png", "Golden Spear", 550, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/goldspear.png", 40, 0, 0, 85, 0, 0);

	public ItemType bow = new ItemType(1, "resources/items/2bows/bow.png", "Bow", 50, 2, true, "bow", "resources/player_sprites/weapons/bows/bow.png", 0, 10, 0, 25, 0, 0);
	public ItemType longbow = new ItemType(1, "resources/items/2bows/longbow.png", "Long Bow", 200, 2, true, "bow", "resources/player_sprites/weapons/bows/longbow.png", 0, 20, 0, 35, 0, 0);
	public ItemType recurvedbow = new ItemType(1, "resources/items/2bows/recurvedbow.png", "Recurved Bow", 400, 2, true, "bow", "resources/player_sprites/weapons/bows/recurvedbow.png", 0, 30, 0, 65, 0, 0);

	public ItemType arrow = new ItemType(1, "resources/items/3arrows/arrow.png", "Arrow", 10, 3, false, "arrow", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);

	public ItemType firespell = new ItemType(1, "resources/items/4spells/firespell.png", "Fire Spell", 150, 4, true, "spell", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 10, 25, 0, 5);
	public ItemType icespell = new ItemType(1, "resources/items/4spells/icespell.png", "Ice Spell", 200, 4, true, "spell", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 20, 45, 0, 20);

	public ItemType clothhood = new ItemType(1, "resources/items/5helmets/clothhood.png", "Cloth Hood", 20, 5, true, "head", "resources/player_sprites/head/clothhood.png", 0, 0, 0, 0, 3, 0);
	public ItemType leathercap = new ItemType(1, "resources/items/5helmets/leathercap.png", "Leather Cap", 50, 5, true, "head", "resources/player_sprites/head/leathercap.png", 0, 0, 0, 0, 5, 0);
	public ItemType chainhat = new ItemType(1, "resources/items/5helmets/chainhat.png", "Chain Hat", 100, 5, true, "head", "resources/player_sprites/head/chainhat.png", 0, 0, 0, 0, 10, 0);
	public ItemType chainhelmet = new ItemType(1, "resources/items/5helmets/chainhelmet.png", "Chain Helmet", 150, 5, true, "head", "resources/player_sprites/head/chainhelmet.png", 0, 0, 0, 0, 15, 0);
	public ItemType metalhelmet = new ItemType(1, "resources/items/5helmets/metalhelmet.png", "Metal Helmet", 200, 5, true, "head", "resources/player_sprites/head/metalhelmet.png", 0, 0, 0, 0, 20, 0);
	public ItemType goldenhelmet = new ItemType(1, "resources/items/5helmets/goldenhelmet.png", "Gold Helmet", 300, 5, true, "head", "resources/player_sprites/head/goldenhelmet.png", 0, 0, 0, 0, 25, 0);

	public ItemType shirt = new ItemType(1, "resources/items/6torso/shirt.png", "Shirt", 10, 6, true, "chest", "resources/player_sprites/chest/shirt.png", 0, 0, 0, 0, 5, 0);
	public ItemType leatherchest = new ItemType(1, "resources/items/6torso/leatherchest.png", "Leather Chest", 300, 6, true, "chest", "resources/player_sprites/chest/leatherchest.png", 0, 0, 0, 0, 10, 0);
	public ItemType chainchest = new ItemType(1, "resources/items/6torso/chainchest.png", "Chain Chest", 500, 6, true, "chest", "resources/player_sprites/chest/chainchest.png", 0, 0, 0, 0, 15, 0);
	public ItemType metalchest = new ItemType(1, "resources/items/6torso/metalchest.png", "Metal Chest", 700, 6, true, "chest", "resources/player_sprites/chest/metalchest.png", 0, 0, 0, 0, 20, 0);
	public ItemType goldenchest = new ItemType(1, "resources/items/6torso/goldenchest.png", "Golden Chest", 1400, 6, true, "chest", "resources/player_sprites/chest/goldenchest.png", 0, 0, 0, 0, 25, 0);

	public ItemType metalgloves = new ItemType(1, "resources/items/7hands/metalgloves.png", "Metal Gloves", 600, 7, true, "hands", "resources/player_sprites/hands/metalgloves.png", 0, 0, 0, 0, 5, 0);
	public ItemType goldengloves = new ItemType(1, "resources/items/7hands/goldengloves.png", "Golden Gloves", 900, 7, true, "hands", "resources/player_sprites/hands/goldengloves.png", 0, 0, 0, 0, 10, 0);

	public ItemType skirt = new ItemType(1, "resources/items/8legarmor/skirt.png", "Skirt", 100, 8, true, "legs", "resources/player_sprites/legs/skirt.png", 0, 0, 0, 0, 5, 0);
	public ItemType metalgreaves = new ItemType(1, "resources/items/8legarmor/metallegarmor.png", "Metal Greaves", 300, 8, true, "legs", "resources/player_sprites/legs/metalgreaves.png", 0, 0, 0, 0, 10, 0);
	public ItemType goldengreaves = new ItemType(1, "resources/items/8legarmor/goldenlegarmor.png", "Golden Greaves", 1200, 8, true, "legs", "resources/player_sprites/legs/goldengreaves.png", 0, 0, 0, 0, 15, 0);

	public ItemType boots = new ItemType(1, "resources/items/9boots/boots.png", "Boots", 100, 9, true, "feet", "resources/player_sprites/feet/boots.png", 0, 0, 0, 0, 3, 0);
	public ItemType leatherboots = new ItemType(1, "resources/items/9boots/leatherboots.png", "Leather Boots", 200, 9, true, "feet", "resources/player_sprites/feet/leatherboots.png", 0, 0, 0, 0, 5, 0);
	public ItemType metalboots = new ItemType(1, "resources/items/9boots/metalboots.png", "Metal Boots", 500, 9, true, "feet", "resources/player_sprites/feet/metalboots.png", 0, 0, 0, 0, 10, 0);
	public ItemType goldenboots = new ItemType(1, "resources/items/9boots/goldenboots.png", "Golden Boots", 700, 9, true, "feet", "resources/player_sprites/feet/goldenboots.png", 0, 0, 0, 0, 15, 0);

	public ItemType verySmallHealingPotion = new ItemType(1, "resources/items/10healing_potions/very_small_healing_potion.png", "Very Small Healing Potion", 20, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType smallHealingPotion = new ItemType(1, "resources/items/10healing_potions/small_healing_potion.png", "Small Healing Potionn", 35, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType mediumHealingPotion = new ItemType(1, "resources/items/10healing_potions/medium_healing_potion.png", "Medium Healing Potionn", 35, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType bigHealingPotion = new ItemType(1, "resources/items/10healing_potions/big_healing_potion.png", "Big Healing Potion", 25, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType veryBigHealingPotion = new ItemType(1, "resources/items/10healing_potions/very_big_healing_potion.png", "Very Big Healing Potion", 10, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);

	public ItemType verySmallManaPotion = new ItemType(1, "resources/items/11mana_potions/very_small_mana_potion.png", "Very Small Mana Potion", 20, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType smallManaPotion = new ItemType(1, "resources/items/11mana_potions/small_mana_potion.png", "Small Mana Potionn", 35, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType mediumManaPotion = new ItemType(1, "resources/items/11mana_potions/medium_mana_potion.png", "Medium Mana Potionn", 35, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType bigManaPotion = new ItemType(1, "resources/items/11mana_potions/big_mana_potion.png", "Big Mana Potion", 25, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType veryBigManaPotion = new ItemType(1, "resources/items/11mana_potions/very_big_mana_potion.png", "Very Big Mana Potion", 10, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);

	public ItemType bread = new ItemType(1, "resources/items/12food/bread.png", "Bread", 20, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType carrot = new ItemType(1, "resources/items/12food/carrot.png", "Carrot", 6, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType rawChicken = new ItemType(1, "resources/items/12food/raw_chicken.png", "Raw Chicken", 33, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType cookedChicken = new ItemType(1, "resources/items/12food/cooked_chicken.png", "Cooked Chicken", 33, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType rawFish = new ItemType(1, "resources/items/12food/raw_fish.png", "Raw Fish", 26, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType cookedFish = new ItemType(1, "resources/items/12food/cooked_fish.png", "Cooked Fish", 26, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType rawMeat = new ItemType(1, "resources/items/12food/raw_meat.png", "Raw Meat", 38, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType cookedMeat = new ItemType(1, "resources/items/12food/cooked_meat.png", "Cooked Meat", 38, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType rawPork = new ItemType(1, "resources/items/12food/raw_pork.png", "Raw Pork", 29, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType cookedPork = new ItemType(1, "resources/items/12food/cooked_pork.png", "Cooked Pork", 29, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType wheat = new ItemType(1, "resources/items/12food/wheat.png", "Wheat", 4, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType apple = new ItemType(1, "resources/items/12food/apple.png", "Apple", 5, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);

	public ItemType bone = new ItemType(1, "resources/items/13misc/bone.png", "Bone", 3, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType copperBar = new ItemType(1, "resources/items/13misc/copper_bar.png", "Copper Bar", 80, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType ironBar = new ItemType(1, "resources/items/13misc/iron_bar.png", "Iron Bar", 160, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType goldBar = new ItemType(1, "resources/items/13misc/gold_bar.png", "Gold Bar", 250, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType feather = new ItemType(1, "resources/items/13misc/feather.png", "Feather", 3, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType treasureChest = new ItemType(1, "resources/items/13misc/treasure_chest.png", "Treasure Chest", 320, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType goldCrown = new ItemType(1, "resources/items/13misc/gold_crown.png", "Gold Crown", 280, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType treasure = new ItemType(1, "resources/items/13misc/treasure.png", "Treasure", 280, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType multipleSticks = new ItemType(1, "resources/items/13misc/multiple_sticks.png", "Multiple Sticks", 5, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);
	public ItemType trophy = new ItemType(1, "resources/items/13misc/trophy.png", "Trophy", 250, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);

	public ItemType gold = new ItemType(100, "resources/items/14gold/goldcoin.png", "Gold", 1, 14, false, "gold", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0);

	public ItemTypeManager() throws SlickException {

	}

}
