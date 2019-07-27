package manager;

import org.newdawn.slick.SlickException;

import models.ItemType;
import util.CollisionBox;

public class ItemTypeManager {

	public ItemType stick = new ItemType(1, "resources/items/1melee/stick.png", "Stick", 20, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/stick.png", 10, 0, 0, 15, 0, 0, 0, 0);	
	public ItemType dagger = new ItemType(1, "resources/items/1melee/dagger.png", "Dagger", 50, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/dagger.png", 20, 0, 0, 25, 0, 0, 0, 0);
	public ItemType mace = new ItemType(1, "resources/items/1melee/mace.png", "Mace", 100, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/mace.png", 30, 0, 0, 35, 0, 0, 0, 0);
	public ItemType ironsword = new ItemType(1, "resources/items/1melee/ironsword.png", "Iron Sword", 300, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/ironsword.png", 40, 0, 0, 75, 0, 0, 0, 0);
	public ItemType goldensword = new ItemType(1, "resources/items/1melee/goldensword.png", "Golden Sword", 400, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/goldensword.png", 50, 0, 0, 90, 0, 0, 0, 0);
	public ItemType diamondsword = new ItemType(1, "resources/items/1melee/diamondsword.png", "Diamond Sword", 400, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/diamondsword.png", 60, 0, 0, 110, 0, 0, 0, 0);
	public ItemType blacksword = new ItemType(1, "resources/items/1melee/blacksword.png", "Black Sword", 400, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/blacksword.png", 75, 0, 0, 140, 0, 0, 0, 0);

	public ItemType sabre = new ItemType(1, "resources/items/1melee/sabre.png", "Sabre", 200, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/sabre.png", 0, 20, 0, 35, 0, 0, 0, 0);
	public ItemType rapier = new ItemType(1, "resources/items/1melee/rapier.png", "Rapier", 400, 1, true, "melee_slay", "resources/player_sprites/weapons/melee_slay/rapier.png", 0, 40, 0, 55, 0, 0, 0, 0);

	public ItemType shortspear = new ItemType(1, "resources/items/1melee/shortspear.png", "Short Spear", 50, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/shortspear.png", 10, 0, 0, 25, 0, 0, 0, 0);
	public ItemType longspear = new ItemType(1, "resources/items/1melee/longspear.png", "Longspear", 200, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/longspear.png", 20, 0, 0, 35, 0, 0, 0, 0);
	public ItemType dragonspear = new ItemType(1, "resources/items/1melee/dragonspear.png", "Dragonspear", 400, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/dragonspear.png", 30, 0, 0, 65, 0, 0, 0, 0);
	public ItemType goldenspear = new ItemType(1, "resources/items/1melee/goldenspear.png", "Golden Spear", 550, 1, true, "melee_thrust", "resources/player_sprites/weapons/melee_thrust/goldenspear.png", 40, 0, 0, 85, 0, 0, 0, 0);

	public ItemType bow = new ItemType(1, "resources/items/2bows/bow.png", "Bow", 50, 2, true, "bow", "resources/player_sprites/weapons/bows/bow.png", 0, 10, 0, 25, 0, 0, 0, 0);
	public ItemType longbow = new ItemType(1, "resources/items/2bows/longbow.png", "Long Bow", 200, 2, true, "bow", "resources/player_sprites/weapons/bows/longbow.png", 0, 20, 0, 35, 0, 0, 0, 0);
	public ItemType recurvedbow = new ItemType(1, "resources/items/2bows/recurvedbow.png", "Recurved Bow", 400, 2, true, "bow", "resources/player_sprites/weapons/bows/recurvedbow.png", 0, 30, 0, 65, 0, 0, 0, 0);

	public ItemType arrow = new ItemType(1, "resources/items/3arrows/arrow.png", "Arrow", 10, 3, false, "arrow", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);

	public ItemType firespell = new ItemType(1, "resources/items/4spells/firespell.png", "Fire Spell", 150, 4, true, "spell", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 10, 25, 0, 5, 0, 0);
	public ItemType icespell = new ItemType(1, "resources/items/4spells/icespell.png", "Ice Spell", 200, 4, true, "spell", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 20, 45, 0, 20, 0, 0);

	public ItemType clothhood = new ItemType(1, "resources/items/5helmets/clothhood.png", "Cloth Hood", 20, 5, true, "head", "resources/player_sprites/head/clothhood.png", 0, 0, 0, 0, 2, 0, 0, 0);
	public ItemType leathercap = new ItemType(1, "resources/items/5helmets/leathercap.png", "Leather Cap", 50, 5, true, "head", "resources/player_sprites/head/leathercap.png", 0, 0, 0, 0, 4, 0, 0, 0);
	public ItemType chainhat = new ItemType(1, "resources/items/5helmets/chainhat.png", "Chain Hat", 100, 5, true, "head", "resources/player_sprites/head/chainhat.png", 0, 0, 0, 0, 6, 0, 0, 0);
	public ItemType chainhelmet = new ItemType(1, "resources/items/5helmets/chainhelmet.png", "Chain Helmet", 150, 5, true, "head", "resources/player_sprites/head/chainhelmet.png", 0, 0, 0, 0, 8, 0, 0, 0);
	public ItemType ironhelmet = new ItemType(1, "resources/items/5helmets/ironhelmet.png", "Iron Helmet", 200, 5, true, "head", "resources/player_sprites/head/ironhelmet.png", 0, 0, 0, 0, 10, 0, 0, 0);
	public ItemType goldenhelmet = new ItemType(1, "resources/items/5helmets/goldenhelmet.png", "Gold Helmet", 300, 5, true, "head", "resources/player_sprites/head/goldenhelmet.png", 0, 0, 0, 0, 12, 0, 0, 0);
	public ItemType diamondhelmet = new ItemType(1, "resources/items/5helmets/diamondhelmet.png", "Diamond Helmet", 500, 5, true, "head", "resources/player_sprites/head/diamondhelmet.png", 0, 0, 0, 0, 16, 0, 0, 0);

	public ItemType shirt = new ItemType(1, "resources/items/6torso/shirt.png", "Shirt", 10, 6, true, "chest", "resources/player_sprites/chest/shirt.png", 0, 0, 0, 0, 3, 0, 0, 0);
	public ItemType leatherchest = new ItemType(1, "resources/items/6torso/leatherchest.png", "Leather Chest", 300, 6, true, "chest", "resources/player_sprites/chest/leatherchest.png", 0, 0, 0, 0, 6, 0, 0, 0);
	public ItemType chainchest = new ItemType(1, "resources/items/6torso/chainchest.png", "Chain Chest", 500, 6, true, "chest", "resources/player_sprites/chest/chainchest.png", 0, 0, 0, 0, 9, 0, 0, 0);
	public ItemType ironchest = new ItemType(1, "resources/items/6torso/ironchest.png", "Iron Chest", 700, 6, true, "chest", "resources/player_sprites/chest/ironchest.png", 0, 0, 0, 0, 12, 0, 0, 0);
	public ItemType goldenchest = new ItemType(1, "resources/items/6torso/goldenchest.png", "Golden Chest", 1400, 6, true, "chest", "resources/player_sprites/chest/goldenchest.png", 0, 0, 0, 0, 15, 0, 0, 0);
	public ItemType diamondchest = new ItemType(1, "resources/items/6torso/diamondchest.png", "Diamond Chest", 2500, 6, true, "chest", "resources/player_sprites/chest/diamondchest.png", 0, 0, 0, 0, 21, 0, 0, 0);

	public ItemType irongloves = new ItemType(1, "resources/items/7hands/irongloves.png", "Iron Gloves", 600, 7, true, "hands", "resources/player_sprites/hands/irongloves.png", 0, 0, 0, 0, 2, 0, 0, 0);
	public ItemType goldengloves = new ItemType(1, "resources/items/7hands/goldengloves.png", "Golden Gloves", 900, 7, true, "hands", "resources/player_sprites/hands/goldengloves.png", 0, 0, 0, 0, 4, 0, 0, 0);
	public ItemType diamondgloves = new ItemType(1, "resources/items/7hands/diamondgloves.png", "Diamond Gloves", 1600, 7, true, "hands", "resources/player_sprites/hands/diamondgloves.png", 0, 0, 0, 0, 8, 0, 0, 0);

	public ItemType skirt = new ItemType(1, "resources/items/8legarmor/skirt.png", "Skirt", 100, 8, true, "legs", "resources/player_sprites/legs/skirt.png", 0, 0, 0, 0, 3, 0, 0, 0);
	public ItemType irongreaves = new ItemType(1, "resources/items/8legarmor/ironlegarmor.png", "Iron Greaves", 300, 8, true, "legs", "resources/player_sprites/legs/irongreaves.png", 0, 0, 0, 0, 6, 0, 0, 0);
	public ItemType goldengreaves = new ItemType(1, "resources/items/8legarmor/goldenlegarmor.png", "Golden Greaves", 1200, 8, true, "legs", "resources/player_sprites/legs/goldengreaves.png", 0, 0, 0, 0, 9, 0, 0, 0);
	public ItemType diamondgreaves = new ItemType(1, "resources/items/8legarmor/diamondlegarmor.png", "Diamond Greaves", 2200, 8, true, "legs", "resources/player_sprites/legs/diamondgreaves.png", 0, 0, 0, 0, 15, 0, 0, 0);

	public ItemType boots = new ItemType(1, "resources/items/9boots/boots.png", "Boots", 100, 9, true, "feet", "resources/player_sprites/feet/boots.png", 0, 0, 0, 0, 2, 0, 0, 0);
	public ItemType leatherboots = new ItemType(1, "resources/items/9boots/leatherboots.png", "Leather Boots", 200, 9, true, "feet", "resources/player_sprites/feet/leatherboots.png", 0, 0, 0, 0, 4, 0, 0, 0);
	public ItemType ironboots = new ItemType(1, "resources/items/9boots/ironboots.png", "Iron Boots", 500, 9, true, "feet", "resources/player_sprites/feet/ironboots.png", 0, 0, 0, 0, 6, 0, 0, 0);
	public ItemType goldenboots = new ItemType(1, "resources/items/9boots/goldenboots.png", "Golden Boots", 700, 9, true, "feet", "resources/player_sprites/feet/goldenboots.png", 0, 0, 0, 0, 8, 0, 0, 0);
	public ItemType diamondboots = new ItemType(1, "resources/items/9boots/diamondboots.png", "Diamond Boots", 1500, 9, true, "feet", "resources/player_sprites/feet/diamondboots.png", 0, 0, 0, 0, 12, 0, 0, 0);

	public ItemType verySmallHealingPotion = new ItemType(1, "resources/items/10healing_potions/very_small_healing_potion.png", "Very Small Healing Potion", 20, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 25, 0);
	public ItemType smallHealingPotion = new ItemType(1, "resources/items/10healing_potions/small_healing_potion.png", "Small Healing Potionn", 35, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 50, 0);
	public ItemType mediumHealingPotion = new ItemType(1, "resources/items/10healing_potions/medium_healing_potion.png", "Medium Healing Potionn", 35, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 100, 0);
	public ItemType bigHealingPotion = new ItemType(1, "resources/items/10healing_potions/big_healing_potion.png", "Big Healing Potion", 25, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 200, 0);
	public ItemType veryBigHealingPotion = new ItemType(1, "resources/items/10healing_potions/very_big_healing_potion.png", "Very Big Healing Potion", 10, 10, false, "healingpotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 400, 0, 0);

	public ItemType verySmallManaPotion = new ItemType(1, "resources/items/11mana_potions/very_small_mana_potion.png", "Very Small Mana Potion", 20, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 25);
	public ItemType smallManaPotion = new ItemType(1, "resources/items/11mana_potions/small_mana_potion.png", "Small Mana Potionn", 35, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 50);
	public ItemType mediumManaPotion = new ItemType(1, "resources/items/11mana_potions/medium_mana_potion.png", "Medium Mana Potionn", 35, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 100);
	public ItemType bigManaPotion = new ItemType(1, "resources/items/11mana_potions/big_mana_potion.png", "Big Mana Potion", 25, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 200);
	public ItemType veryBigManaPotion = new ItemType(1, "resources/items/11mana_potions/very_big_mana_potion.png", "Very Big Mana Potion", 10, 11, false, "manapotion", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 400);

	public ItemType bread = new ItemType(1, "resources/items/12food/bread.png", "Bread", 20, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 15, 0);
	public ItemType carrot = new ItemType(1, "resources/items/12food/carrot.png", "Carrot", 6, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 10, 0);
	public ItemType rawChicken = new ItemType(1, "resources/items/12food/raw_chicken.png", "Raw Chicken", 33, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 5, 0);
	public ItemType cookedChicken = new ItemType(1, "resources/items/12food/cooked_chicken.png", "Cooked Chicken", 33, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 25, 0);
	public ItemType rawFish = new ItemType(1, "resources/items/12food/raw_fish.png", "Raw Fish", 26, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 5, 0);
	public ItemType cookedFish = new ItemType(1, "resources/items/12food/cooked_fish.png", "Cooked Fish", 26, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 25, 0);
	public ItemType rawMeat = new ItemType(1, "resources/items/12food/raw_meat.png", "Raw Meat", 38, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 5, 0);
	public ItemType cookedMeat = new ItemType(1, "resources/items/12food/cooked_meat.png", "Cooked Meat", 38, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 25, 0);
	public ItemType rawPork = new ItemType(1, "resources/items/12food/raw_pork.png", "Raw Pork", 29, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 5, 0);
	public ItemType cookedPork = new ItemType(1, "resources/items/12food/cooked_pork.png", "Cooked Pork", 29, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 25, 0);
	public ItemType wheat = new ItemType(1, "resources/items/12food/wheat.png", "Wheat", 4, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 5, 0);
	public ItemType apple = new ItemType(1, "resources/items/12food/apple.png", "Apple", 5, 12, false, "food", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 15, 0);

	public ItemType bone = new ItemType(1, "resources/items/13misc/bone.png", "Bone", 3, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType copperBar = new ItemType(1, "resources/items/13misc/copper_bar.png", "Copper Bar", 80, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType ironBar = new ItemType(1, "resources/items/13misc/iron_bar.png", "Iron Bar", 160, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType goldenBar = new ItemType(1, "resources/items/13misc/gold_bar.png", "Golden Bar", 250, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType feather = new ItemType(1, "resources/items/13misc/feather.png", "Feather", 3, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType treasureChest = new ItemType(1, "resources/items/13misc/treasure_chest.png", "Treasure Chest", 320, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType goldCrown = new ItemType(1, "resources/items/13misc/gold_crown.png", "Gold Crown", 280, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType treasure = new ItemType(1, "resources/items/13misc/treasure.png", "Treasure", 280, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType multipleSticks = new ItemType(1, "resources/items/13misc/multiple_sticks.png", "Multiple Sticks", 5, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);
	public ItemType trophy = new ItemType(1, "resources/items/13misc/trophy.png", "Trophy", 250, 13, false, "misc", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);

	public ItemType gold = new ItemType(100, "resources/items/14gold/goldcoin.png", "Gold", 1, 14, false, "gold", "resources/player_sprites/empty_sprite_sheet.png", 0, 0, 0, 0, 0, 0, 0, 0);

	public ItemTypeManager() throws SlickException {
		
		stick.setAttackUpCollisionBox(new CollisionBox(0, 0, 80, 29));
		stick.setAttackDownCollisionBox(new CollisionBox(0, 0, 80, 29));
		stick.setAttackLeftCollisionBox(new CollisionBox(0, 0, 20, 36));
		stick.setAttackRightCollisionBox(new CollisionBox(0, 0, 20, 36));
		
		stick.setAttackUpOffsetX(-23);
		stick.setAttackUpOffsetY(-28);
		
		stick.setAttackDownOffsetX(-23);
		stick.setAttackDownOffsetY(+12);
		
		stick.setAttackLeftOffsetX(-19);
		stick.setAttackLeftOffsetY(-16);
		
		stick.setAttackRightOffsetX(+31);
		stick.setAttackRightOffsetY(-16);
		
		
		
		dagger.setAttackUpCollisionBox(new CollisionBox(0, 0, 46, 17));
		dagger.setAttackDownCollisionBox(new CollisionBox(0, 0, 46, 17));
		dagger.setAttackLeftCollisionBox(new CollisionBox(0, 0, 18, 36));
		dagger.setAttackRightCollisionBox(new CollisionBox(0, 0, 18, 36));
		
		dagger.setAttackUpOffsetX(-7);
		dagger.setAttackUpOffsetY(-16);
		
		dagger.setAttackDownOffsetX(-12);
		dagger.setAttackDownOffsetY(+6);
		
		dagger.setAttackLeftOffsetX(-17);
		dagger.setAttackLeftOffsetY(-16);
		
		dagger.setAttackRightOffsetX(+31);
		dagger.setAttackRightOffsetY(-16);
		
		
		
		mace.setAttackUpCollisionBox(new CollisionBox(0, 0, 80, 29));
		mace.setAttackDownCollisionBox(new CollisionBox(0, 0, 80, 29));
		mace.setAttackLeftCollisionBox(new CollisionBox(0, 0, 31, 36));
		mace.setAttackRightCollisionBox(new CollisionBox(0, 0, 31, 36));
		
		mace.setAttackUpOffsetX(-23);
		mace.setAttackUpOffsetY(-28);
		
		mace.setAttackDownOffsetX(-23);
		mace.setAttackDownOffsetY(+12);
		
		mace.setAttackLeftOffsetX(-30);
		mace.setAttackLeftOffsetY(-16);
		
		mace.setAttackRightOffsetX(+31);
		mace.setAttackRightOffsetY(-16);
		
		
		
		ironsword.setAttackUpCollisionBox(new CollisionBox(0, 0, 89, 45));
		ironsword.setAttackDownCollisionBox(new CollisionBox(0, 0, 89, 45));
		ironsword.setAttackLeftCollisionBox(new CollisionBox(0, 0, 68, 36));
		ironsword.setAttackRightCollisionBox(new CollisionBox(0, 0, 68, 36));

		ironsword.setAttackUpOffsetX(-28);
		ironsword.setAttackUpOffsetY(-37);
		
		ironsword.setAttackDownOffsetX(-28);
		ironsword.setAttackDownOffsetY(+12);
		
		ironsword.setAttackLeftOffsetX(-67);
		ironsword.setAttackLeftOffsetY(-16);
		
		ironsword.setAttackRightOffsetX(+31);
		ironsword.setAttackRightOffsetY(-16);

		
		
		goldensword.setAttackUpCollisionBox(new CollisionBox(0, 0, 89, 45));
		goldensword.setAttackDownCollisionBox(new CollisionBox(0, 0, 89, 45));
		goldensword.setAttackLeftCollisionBox(new CollisionBox(0, 0, 68, 36));
		goldensword.setAttackRightCollisionBox(new CollisionBox(0, 0, 68, 36));
		
		goldensword.setAttackUpOffsetX(-28);
		goldensword.setAttackUpOffsetY(-37);
		
		goldensword.setAttackDownOffsetX(-28);
		goldensword.setAttackDownOffsetY(+12);
		
		goldensword.setAttackLeftOffsetX(-67);
		goldensword.setAttackLeftOffsetY(-16);
		
		goldensword.setAttackRightOffsetX(+31);
		goldensword.setAttackRightOffsetY(-16);	
		
		
		
		
		diamondsword.setAttackUpCollisionBox(new CollisionBox(0, 0, 89, 45));
		diamondsword.setAttackDownCollisionBox(new CollisionBox(0, 0, 89, 45));
		diamondsword.setAttackLeftCollisionBox(new CollisionBox(0, 0, 68, 36));
		diamondsword.setAttackRightCollisionBox(new CollisionBox(0, 0, 68, 36));
		
		diamondsword.setAttackUpOffsetX(-28);
		diamondsword.setAttackUpOffsetY(-37);
		
		diamondsword.setAttackDownOffsetX(-28);
		diamondsword.setAttackDownOffsetY(+12);
		
		diamondsword.setAttackLeftOffsetX(-67);
		diamondsword.setAttackLeftOffsetY(-16);
		
		diamondsword.setAttackRightOffsetX(+31);
		diamondsword.setAttackRightOffsetY(-16);	
		
		
		
		
		blacksword.setAttackUpCollisionBox(new CollisionBox(0, 0, 89, 45));
		blacksword.setAttackDownCollisionBox(new CollisionBox(0, 0, 89, 45));
		blacksword.setAttackLeftCollisionBox(new CollisionBox(0, 0, 68, 36));
		blacksword.setAttackRightCollisionBox(new CollisionBox(0, 0, 68, 36));
		
		blacksword.setAttackUpOffsetX(-28);
		blacksword.setAttackUpOffsetY(-37);
		
		blacksword.setAttackDownOffsetX(-28);
		blacksword.setAttackDownOffsetY(+12);
		
		blacksword.setAttackLeftOffsetX(-67);
		blacksword.setAttackLeftOffsetY(-16);
		
		blacksword.setAttackRightOffsetX(+31);
		blacksword.setAttackRightOffsetY(-16);
		
		
		
		
		sabre.setAttackUpCollisionBox(new CollisionBox(0, 0, 85, 29));
		sabre.setAttackDownCollisionBox(new CollisionBox(0, 0, 85, 29));
		sabre.setAttackLeftCollisionBox(new CollisionBox(0, 0, 31, 36));
		sabre.setAttackRightCollisionBox(new CollisionBox(0, 0, 31, 36));
		
		sabre.setAttackUpOffsetX(-23);
		sabre.setAttackUpOffsetY(-32);
		
		sabre.setAttackDownOffsetX(-23);
		sabre.setAttackDownOffsetY(+12);
		
		sabre.setAttackLeftOffsetX(-30);
		sabre.setAttackLeftOffsetY(-16);
		
		sabre.setAttackRightOffsetX(+31);
		sabre.setAttackRightOffsetY(-16);
		
		
		
		
		rapier.setAttackUpCollisionBox(new CollisionBox(0, 0, 89, 45));
		rapier.setAttackDownCollisionBox(new CollisionBox(0, 0, 89, 45));
		rapier.setAttackLeftCollisionBox(new CollisionBox(0, 0, 68, 36));
		rapier.setAttackRightCollisionBox(new CollisionBox(0, 0, 68, 36));
		
		rapier.setAttackUpOffsetX(-28);
		rapier.setAttackUpOffsetY(-37);
		
		rapier.setAttackDownOffsetX(-28);
		rapier.setAttackDownOffsetY(+12);
		
		rapier.setAttackLeftOffsetX(-67);
		rapier.setAttackLeftOffsetY(-16);
		
		rapier.setAttackRightOffsetX(+31);
		rapier.setAttackRightOffsetY(-16);	

		
		
		shortspear.setAttackUpCollisionBox(new CollisionBox(0, 0, 5, 36));
		shortspear.setAttackDownCollisionBox(new CollisionBox(0, 0, 5, 36));
		shortspear.setAttackLeftCollisionBox(new CollisionBox(0, 0, 20, 5));
		shortspear.setAttackRightCollisionBox(new CollisionBox(0, 0, 20, 5));
		
		shortspear.setAttackUpOffsetX(+20);
		shortspear.setAttackUpOffsetY(-35);
		
		shortspear.setAttackDownOffsetX(+13);
		shortspear.setAttackDownOffsetY(+12);
		
		shortspear.setAttackLeftOffsetX(-19);
		shortspear.setAttackLeftOffsetY(+11);
		
		shortspear.setAttackRightOffsetX(+31);
		shortspear.setAttackRightOffsetY(+11);	
		
		
		
		longspear.setAttackUpCollisionBox(new CollisionBox(0, 0, 5, 73));
		longspear.setAttackDownCollisionBox(new CollisionBox(0, 0, 5, 73));
		longspear.setAttackLeftCollisionBox(new CollisionBox(0, 0, 59, 5));
		longspear.setAttackRightCollisionBox(new CollisionBox(0, 0, 59, 5));
		
		longspear.setAttackUpOffsetX(+22);
		longspear.setAttackUpOffsetY(-72);
		
		longspear.setAttackDownOffsetX(+14);
		longspear.setAttackDownOffsetY(+12);
		
		longspear.setAttackLeftOffsetX(-58);
		longspear.setAttackLeftOffsetY(+11);
		
		longspear.setAttackRightOffsetX(+31);
		longspear.setAttackRightOffsetY(+11);	
		
		
		
		dragonspear.setAttackUpCollisionBox(new CollisionBox(0, 0, 11, 78));
		dragonspear.setAttackDownCollisionBox(new CollisionBox(0, 0, 11, 78));
		dragonspear.setAttackLeftCollisionBox(new CollisionBox(0, 0, 65, 11));
		dragonspear.setAttackRightCollisionBox(new CollisionBox(0, 0, 65, 11));
		
		dragonspear.setAttackUpOffsetX(+20);
		dragonspear.setAttackUpOffsetY(-77);
		
		dragonspear.setAttackDownOffsetX(+11);
		dragonspear.setAttackDownOffsetY(+12);
		
		dragonspear.setAttackLeftOffsetX(-64);
		dragonspear.setAttackLeftOffsetY(+8);
		
		dragonspear.setAttackRightOffsetX(+31);
		dragonspear.setAttackRightOffsetY(+8);	
		
		
		
		goldenspear.setAttackUpCollisionBox(new CollisionBox(0, 0, 19, 78));
		goldenspear.setAttackDownCollisionBox(new CollisionBox(0, 0, 19, 78));
		goldenspear.setAttackLeftCollisionBox(new CollisionBox(0, 0, 65, 19));
		goldenspear.setAttackRightCollisionBox(new CollisionBox(0, 0, 65, 19));
		
		goldenspear.setAttackUpOffsetX(+20);
		goldenspear.setAttackUpOffsetY(-77);
		
		goldenspear.setAttackDownOffsetX(+11);
		goldenspear.setAttackDownOffsetY(+12);
		
		goldenspear.setAttackLeftOffsetX(-64);
		goldenspear.setAttackLeftOffsetY(+8);
		
		goldenspear.setAttackRightOffsetX(+31);
		goldenspear.setAttackRightOffsetY(+8);	
		

		
	}

}
