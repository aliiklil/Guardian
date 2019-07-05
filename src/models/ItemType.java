package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ItemType {

	private final int spriteWidth = 32;
	private final int spriteHeight = 32;
	
	private final Animation mapAnimation;
	private final Animation inventoryAnimation;
	private final Animation descriptionAnimation;
	
	private final String name;
	private final int value;
	
	private final int inventoryPriority;
	
	private final boolean equippable;
	
	private final String itemCategory;
	
	private SpriteSheet spriteSheet;
	
	private SpriteSheet overSizeSpriteSheet;
	
	private Animation lookUpAnimation;
	private Animation lookDownAnimation;
	private Animation lookLeftAnimation;
	private Animation lookRightAnimation;
	
	private Animation goUpAnimation;
	private Animation goDownAnimation;
	private Animation goLeftAnimation;
	private Animation goRightAnimation;
	
	private Animation slayUpAnimation;
	private Animation slayDownAnimation;
	private Animation slayLeftAnimation;
	private Animation slayRightAnimation;
	
	private Animation prepareSlayUpAnimation;
	private Animation prepareSlayDownAnimation;
	private Animation prepareSlayLeftAnimation;
	private Animation prepareSlayRightAnimation;
	
	private Animation thrustUpAnimation;
	private Animation thrustDownAnimation;
	private Animation thrustLeftAnimation;
	private Animation thrustRightAnimation;
	
	private Animation prepareThrustUpAnimation;
	private Animation prepareThrustDownAnimation;
	private Animation prepareThrustLeftAnimation;
	private Animation prepareThrustRightAnimation;
	
	private Animation shootUpAnimation;
	private Animation shootDownAnimation;
	private Animation shootLeftAnimation;
	private Animation shootRightAnimation;
	
	private Animation spellUpAnimation;
	private Animation spellDownAnimation;
	private Animation spellLeftAnimation;
	private Animation spellRightAnimation;
	
	private Animation dieAnimation;
	
	//Strength etc. needed to equip item, only used for weapons
	private int minStrength = 0;
	private int minDexterity = 0;
	private int minMagicKnowledge = 0;
	
	//Only needed for weapons
	private int damage = 0;
	
	//Only needed for armor
	private int protection = 0;
	
	//Only needed for spells
	private int manaCost = 0;
	
	//Only needed for food, potions etc.
	private int healthBoost = 0;
	private int manaBoost = 0;
	
	public ItemType(int duration, String itemImagePath, String name, int value, int inventoryPriority, boolean equippable, String itemCategory, String spriteSheetPath, int minStrength, int minDexterity, int minMagicKnowledge, int damage, int protection, int manaCost, int healthBoost, int manaBoost) throws SlickException {
		
		mapAnimation = new Animation(new SpriteSheet(itemImagePath, spriteWidth, spriteHeight), duration);
		inventoryAnimation = new Animation(new SpriteSheet(new Image(itemImagePath).getScaledCopy(2), spriteWidth * 2, spriteHeight * 2), duration);
		descriptionAnimation = new Animation(new SpriteSheet(new Image(itemImagePath).getScaledCopy(4), spriteWidth * 4, spriteHeight * 4), duration);
		
		this.name = name;
		this.value = value;
		this.inventoryPriority = inventoryPriority;
		this.equippable = equippable;
		this.itemCategory = itemCategory;
		
		this.spriteSheet = new SpriteSheet(spriteSheetPath, 64, 64);
		
		this.overSizeSpriteSheet = new SpriteSheet(spriteSheetPath, 192, 192);
		
		lookUpAnimation = new Animation(spriteSheet, 0, 8, 0, 8, true, 100, false);
		lookDownAnimation = new Animation(spriteSheet, 0, 10, 0, 10, true, 100, false);
		lookLeftAnimation = new Animation(spriteSheet, 0, 9, 0, 9, true, 100, false);
		lookRightAnimation = new Animation(spriteSheet, 0, 11, 0, 11, true, 100, false);
		
		goUpAnimation = new Animation(spriteSheet, 1, 8, 8, 8, true, 100, false);
		goDownAnimation = new Animation(spriteSheet, 1, 10, 8, 10, true, 100, false);
		goLeftAnimation = new Animation(spriteSheet, 1, 9, 8, 9, true, 100, false);
		goRightAnimation = new Animation(spriteSheet, 1, 11, 8, 11, true, 100, false);
			
		slayUpAnimation = new Animation(overSizeSpriteSheet, 2, 7, 5, 7, true, 100, false);
		slayDownAnimation = new Animation(overSizeSpriteSheet, 2, 9, 5, 9, true, 100, false);
		slayLeftAnimation = new Animation(overSizeSpriteSheet, 2, 8, 5, 8, true, 100, false);
		slayRightAnimation = new Animation(overSizeSpriteSheet, 2, 10, 5, 10, true, 100, false);
		
		prepareSlayUpAnimation = new Animation(overSizeSpriteSheet, 2, 7, 2, 7, true, 100, false);
		prepareSlayDownAnimation = new Animation(overSizeSpriteSheet, 2, 9, 2, 9, true, 100, false);
		prepareSlayLeftAnimation = new Animation(overSizeSpriteSheet, 2, 8, 2, 8, true, 100, false);
		prepareSlayRightAnimation = new Animation(overSizeSpriteSheet, 2, 10, 2, 10, true, 100, false);

		thrustUpAnimation = new Animation(overSizeSpriteSheet, 3, 11, 7, 11, true, 100, false);
		thrustDownAnimation = new Animation(overSizeSpriteSheet, 3, 13, 7, 13, true, 100, false);
		thrustLeftAnimation = new Animation(overSizeSpriteSheet, 3, 12, 7, 12, true, 100, false);
		thrustRightAnimation = new Animation(overSizeSpriteSheet, 3, 14, 7, 14, true, 100, false);
		
		prepareThrustUpAnimation = new Animation(overSizeSpriteSheet, 3, 11, 3, 11, true, 100, false);
		prepareThrustDownAnimation = new Animation(overSizeSpriteSheet, 3, 13, 3, 13, true, 100, false);
		prepareThrustLeftAnimation = new Animation(overSizeSpriteSheet, 3, 12, 3, 12, true, 100, false);
		prepareThrustRightAnimation = new Animation(overSizeSpriteSheet, 3, 14, 3, 14, true, 100, false);
		
		shootUpAnimation = new Animation(spriteSheet, 0, 16, 8, 16, true, 100, false);
		shootDownAnimation = new Animation(spriteSheet, 0, 18, 8, 18, true, 100, false);
		shootLeftAnimation = new Animation(spriteSheet, 0, 17, 8, 17, true, 100, false);
		shootRightAnimation = new Animation(spriteSheet, 0, 19, 8, 19, true, 100, false);
		
		spellUpAnimation = new Animation(spriteSheet, 0, 0, 6, 0, true, 100, false);
		spellDownAnimation = new Animation(spriteSheet, 0, 2, 6, 2, true, 100, false);
		spellLeftAnimation = new Animation(spriteSheet, 0, 1, 6, 1, true, 100, false);
		spellRightAnimation = new Animation(spriteSheet, 0, 3, 6, 3, true, 100, false);
		
		dieAnimation = new Animation(spriteSheet, 0, 20, 5, 20, true, 100, false);
		
		slayUpAnimation.setLooping(false);
		slayDownAnimation.setLooping(false);
		slayLeftAnimation.setLooping(false);
		slayRightAnimation.setLooping(false);
		
		thrustUpAnimation.setLooping(false);
		thrustDownAnimation.setLooping(false);
		thrustLeftAnimation.setLooping(false);
		thrustRightAnimation.setLooping(false);
		
		shootUpAnimation.setLooping(false);
		shootDownAnimation.setLooping(false);
		shootLeftAnimation.setLooping(false);
		shootRightAnimation.setLooping(false);
		
		spellUpAnimation.setLooping(false);
		spellDownAnimation.setLooping(false);
		spellLeftAnimation.setLooping(false);
		spellRightAnimation.setLooping(false);
		
		dieAnimation.setLooping(false);
		
		this.minStrength = minStrength;
		this.minDexterity = minDexterity;
		this.minMagicKnowledge = minMagicKnowledge;
	
		this.damage = damage;
		this.protection = protection;
		
		this.manaCost = manaCost;
		
		this.healthBoost = healthBoost;
		this.manaBoost = manaBoost;
		
	}
	
	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public Animation getMapAnimation() {
		return mapAnimation;
	}
	
	public Animation getInventoryAnimation() {
		return inventoryAnimation;
	}
	
	public Animation getDescriptionAnimation() {
		return descriptionAnimation;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getInventoryPriority() {
		return inventoryPriority;
	}
	
	public boolean isEquippable() {
		return equippable;
	}
	
	public String getItemCategory() {
		return itemCategory;
	}

	public Animation getLookUpAnimation() {
		return lookUpAnimation;
	}

	public Animation getLookDownAnimation() {
		return lookDownAnimation;
	}

	public Animation getLookLeftAnimation() {
		return lookLeftAnimation;
	}

	public Animation getLookRightAnimation() {
		return lookRightAnimation;
	}

	public Animation getGoUpAnimation() {
		return goUpAnimation;
	}

	public Animation getGoDownAnimation() {
		return goDownAnimation;
	}

	public Animation getGoLeftAnimation() {
		return goLeftAnimation;
	}

	public Animation getGoRightAnimation() {
		return goRightAnimation;
	}	

	public Animation getSlayUpAnimation() {
		return slayUpAnimation;
	}

	public Animation getSlayDownAnimation() {
		return slayDownAnimation;
	}

	public Animation getSlayLeftAnimation() {
		return slayLeftAnimation;
	}

	public Animation getSlayRightAnimation() {
		return slayRightAnimation;
	}

	public Animation getPrepareSlayUpAnimation() {
		return prepareSlayUpAnimation;
	}

	public Animation getPrepareSlayDownAnimation() {
		return prepareSlayDownAnimation;
	}

	public Animation getPrepareSlayLeftAnimation() {
		return prepareSlayLeftAnimation;
	}

	public Animation getPrepareSlayRightAnimation() {
		return prepareSlayRightAnimation;
	}

	public Animation getThrustUpAnimation() {
		return thrustUpAnimation;
	}

	public Animation getThrustDownAnimation() {
		return thrustDownAnimation;
	}

	public Animation getThrustLeftAnimation() {
		return thrustLeftAnimation;
	}

	public Animation getThrustRightAnimation() {
		return thrustRightAnimation;
	}

	public Animation getPrepareThrustUpAnimation() {
		return prepareThrustUpAnimation;
	}

	public Animation getPrepareThrustDownAnimation() {
		return prepareThrustDownAnimation;
	}

	public Animation getPrepareThrustLeftAnimation() {
		return prepareThrustLeftAnimation;
	}

	public Animation getPrepareThrustRightAnimation() {
		return prepareThrustRightAnimation;
	}

	public Animation getShootUpAnimation() {
		return shootUpAnimation;
	}

	public Animation getShootDownAnimation() {
		return shootDownAnimation;
	}

	public Animation getShootLeftAnimation() {
		return shootLeftAnimation;
	}

	public Animation getShootRightAnimation() {
		return shootRightAnimation;
	}

	public Animation getSpellUpAnimation() {
		return spellUpAnimation;
	}

	public Animation getSpellDownAnimation() {
		return spellDownAnimation;
	}

	public Animation getSpellLeftAnimation() {
		return spellLeftAnimation;
	}

	public Animation getSpellRightAnimation() {
		return spellRightAnimation;
	}

	public Animation getDieAnimation() {
		return dieAnimation;
	}

	public int getMinStrength() {
		return minStrength;
	}

	public int getMinDexterity() {
		return minDexterity;
	}

	public int getMinMagicKnowledge() {
		return minMagicKnowledge;
	}

	public int getDamage() {
		return damage;
	}

	public int getProtection() {
		return protection;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getHealthBoost() {
		return healthBoost;
	}

	public int getManaBoost() {
		return manaBoost;
	}
	
}
