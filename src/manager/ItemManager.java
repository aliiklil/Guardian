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
			
		
		itemList.add(new Item(76, 21, itemTypeManager.stick));
		itemList.add(new Item(76, 21, itemTypeManager.stick));
						
		itemList.add(new Item(76, 21, itemTypeManager.dagger));
		itemList.add(new Item(76, 21, itemTypeManager.mace));
		itemList.add(new Item(76, 21, itemTypeManager.ironsword));
		itemList.add(new Item(76, 21, itemTypeManager.goldensword));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilsword));
		itemList.add(new Item(76, 21, itemTypeManager.blacksword));
		
		itemList.add(new Item(76, 21, itemTypeManager.sabre));
		itemList.add(new Item(76, 21, itemTypeManager.rapier));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilrapier));
		
		itemList.add(new Item(76, 21, itemTypeManager.shortspear));
		//itemList.add(new Item(76, 21, itemTypeManager.longspear));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilspear));
		itemList.add(new Item(76, 21, itemTypeManager.goldenspear));
		
		itemList.add(new Item(76, 21, itemTypeManager.bow));
		itemList.add(new Item(76, 21, itemTypeManager.longbow));
		itemList.add(new Item(76, 21, itemTypeManager.recurvedbow));
		
		
		
		
		itemList.add(new Item(76, 21, itemTypeManager.titanspearRune));
		itemList.add(new Item(76, 21, itemTypeManager.iceblockRune));
		itemList.add(new Item(76, 21, itemTypeManager.transformIntoWolfRune));
		itemList.add(new Item(76, 21, itemTypeManager.healLightWoundsRune));	
		
		itemList.add(new Item(76, 21, itemTypeManager.icelanceRune));
		itemList.add(new Item(76, 21, itemTypeManager.bloodtheftRune));
		itemList.add(new Item(76, 21, itemTypeManager.transformIntoSkeletonRune));
		itemList.add(new Item(76, 21, itemTypeManager.healMediumWoundsRune));
				
		itemList.add(new Item(76, 21, itemTypeManager.fireballRune));
		itemList.add(new Item(76, 21, itemTypeManager.firerainRune));
		itemList.add(new Item(76, 21, itemTypeManager.transformIntoOrcWarriorRune));
		itemList.add(new Item(76, 21, itemTypeManager.healHeavyWoundsRune));
		
		
		itemList.add(new Item(76, 21, itemTypeManager.healLightWoundsSpell));
		itemList.add(new Item(76, 21, itemTypeManager.titanspearSpell));
		itemList.add(new Item(76, 21, itemTypeManager.iceblockSpell));
		itemList.add(new Item(76, 21, itemTypeManager.transformIntoWolfSpell));
			
		itemList.add(new Item(76, 21, itemTypeManager.healMediumWoundsSpell));
		itemList.add(new Item(76, 21, itemTypeManager.icelanceSpell));
		itemList.add(new Item(76, 21, itemTypeManager.bloodtheftSpell));
		itemList.add(new Item(76, 21, itemTypeManager.transformIntoSkeletonSpell));
				
		itemList.add(new Item(76, 21, itemTypeManager.healHeavyWoundsSpell));
		itemList.add(new Item(76, 21, itemTypeManager.fireballSpell));
		itemList.add(new Item(76, 21, itemTypeManager.firerainSpell));
		itemList.add(new Item(76, 21, itemTypeManager.transformIntoOrcWarriorSpell));
		
		
		
		
		
		
		
		

		
		
		
		
		
		itemList.add(new Item(76, 21, itemTypeManager.lightImperialArmor));
		itemList.add(new Item(76, 21, itemTypeManager.mediumImperialArmor));
		itemList.add(new Item(76, 21, itemTypeManager.heavyImperialArmor));
		
		itemList.add(new Item(76, 21, itemTypeManager.lightRebelArmor));
		itemList.add(new Item(76, 21, itemTypeManager.mediumRebelArmor));
		itemList.add(new Item(76, 21, itemTypeManager.heavyRebelArmor));
		
		itemList.add(new Item(76, 21, itemTypeManager.noviceArmor));
		itemList.add(new Item(76, 21, itemTypeManager.mageArmor));
		itemList.add(new Item(76, 21, itemTypeManager.heavyMageArmor));
		
		itemList.add(new Item(76, 21, itemTypeManager.leatherArmor));
		//itemList.add(new Item(76, 21, itemTypeManager.ironArmor));
		itemList.add(new Item(76, 21, itemTypeManager.blackknightArmor));
		itemList.add(new Item(76, 21, itemTypeManager.goldenArmor));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilArmor));
		
		
		itemList.add(new Item(76, 21, itemTypeManager.gold));
		itemList.add(new Item(76, 21, itemTypeManager.gold));
		itemList.add(new Item(76, 21, itemTypeManager.gold));

		itemList.add(new Item(76, 21, itemTypeManager.apple));
		itemList.add(new Item(76, 21, itemTypeManager.apple));



		itemList.add(new Item(76, 21, itemTypeManager.arrow));
		itemList.add(new Item(76, 21, itemTypeManager.arrow));
		itemList.add(new Item(76, 21, itemTypeManager.arrow));
	
		itemList.add(new Item(76, 21, itemTypeManager.bone));
		itemList.add(new Item(76, 21, itemTypeManager.bone));
		itemList.add(new Item(76, 21, itemTypeManager.bone));
		itemList.add(new Item(76, 21, itemTypeManager.bone));
		itemList.add(new Item(76, 21, itemTypeManager.multipleSticks));
		itemList.add(new Item(76, 21, itemTypeManager.wheat));
		itemList.add(new Item(76, 21, itemTypeManager.trophy));
		
		itemList.add(new Item(76, 21, itemTypeManager.bread));
		itemList.add(new Item(76, 21, itemTypeManager.carrot));
		itemList.add(new Item(76, 21, itemTypeManager.rawChicken));
		itemList.add(new Item(76, 21, itemTypeManager.cookedChicken));
		itemList.add(new Item(76, 21, itemTypeManager.rawFish));
		itemList.add(new Item(76, 21, itemTypeManager.cookedFish));
		itemList.add(new Item(76, 21, itemTypeManager.rawMeat));
		itemList.add(new Item(76, 21, itemTypeManager.cookedMeat));
		itemList.add(new Item(76, 21, itemTypeManager.rawPork));
		itemList.add(new Item(76, 21, itemTypeManager.cookedPork));
		
		itemList.add(new Item(76, 21, itemTypeManager.ironBar));
		itemList.add(new Item(76, 21, itemTypeManager.ironBar));
		itemList.add(new Item(76, 21, itemTypeManager.ironBar));
		itemList.add(new Item(76, 21, itemTypeManager.ironBar));
		itemList.add(new Item(76, 21, itemTypeManager.ironBar));
		itemList.add(new Item(76, 21, itemTypeManager.ironBar));
		
		itemList.add(new Item(76, 21, itemTypeManager.goldenBar));
		itemList.add(new Item(76, 21, itemTypeManager.goldenBar));
		itemList.add(new Item(76, 21, itemTypeManager.goldenBar));
		itemList.add(new Item(76, 21, itemTypeManager.goldenBar));
		itemList.add(new Item(76, 21, itemTypeManager.goldenBar));
		itemList.add(new Item(76, 21, itemTypeManager.goldenBar));
		
		itemList.add(new Item(76, 21, itemTypeManager.mithrilBar));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilBar));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilBar));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilBar));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilBar));
		itemList.add(new Item(76, 21, itemTypeManager.mithrilBar));
		
		itemList.add(new Item(76, 21, itemTypeManager.feather));
		itemList.add(new Item(76, 21, itemTypeManager.treasureChest));
		itemList.add(new Item(76, 21, itemTypeManager.goldCrown));
		itemList.add(new Item(76, 21, itemTypeManager.treasure));
		
		itemList.add(new Item(76, 21, itemTypeManager.smallHpPotion));
		itemList.add(new Item(76, 21, itemTypeManager.mediumHpPotion));
		itemList.add(new Item(76, 21, itemTypeManager.bigHpPotion));
		
		itemList.add(new Item(76, 21, itemTypeManager.smallManaPotion));
		itemList.add(new Item(76, 21, itemTypeManager.mediumManaPotion));
		itemList.add(new Item(76, 21, itemTypeManager.bigManaPotion));
		
		itemList.add(new Item(76, 21, itemTypeManager.maxHpBonusPotion));
		itemList.add(new Item(76, 21, itemTypeManager.maxManaBonusPotion));
		itemList.add(new Item(76, 21, itemTypeManager.strengthPotion));
		itemList.add(new Item(76, 21, itemTypeManager.dexterityPotion));
		itemList.add(new Item(76, 21, itemTypeManager.speedPotion));
		
		
		itemList.add(new Item(76, 21, itemTypeManager.dragonroot));
		itemList.add(new Item(76, 21, itemTypeManager.goblinweed));
		itemList.add(new Item(76, 21, itemTypeManager.goldtruffle));
		itemList.add(new Item(76, 21, itemTypeManager.wolfnettel));
		
		itemList.add(new Item(76, 21, itemTypeManager.healberry));
		itemList.add(new Item(76, 21, itemTypeManager.healplant));
		itemList.add(new Item(76, 21, itemTypeManager.healroot));
		
		itemList.add(new Item(76, 21, itemTypeManager.manaberry));
		itemList.add(new Item(76, 21, itemTypeManager.manaplant));
		itemList.add(new Item(76, 21, itemTypeManager.manaroot));
		

		
		
		itemList.add(new Item(76, 21, itemTypeManager.icecube));
		itemList.add(new Item(76, 21, itemTypeManager.wolfHide));
		itemList.add(new Item(76, 21, itemTypeManager.smaragd));
		itemList.add(new Item(76, 21, itemTypeManager.aquamarine));
		itemList.add(new Item(76, 21, itemTypeManager.pitch));
		itemList.add(new Item(76, 21, itemTypeManager.orcTooth));
		itemList.add(new Item(76, 21, itemTypeManager.heart));
		itemList.add(new Item(76, 21, itemTypeManager.sulfur));
		
		

		
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