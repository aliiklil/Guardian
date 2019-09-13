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
			
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.stick));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.stick));
						
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.dagger));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mace));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironsword));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldensword));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilsword));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.blacksword));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.sabre));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.rapier));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilrapier));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.shortspear));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.longspear));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilspear));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenspear));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bow));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.longbow));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.recurvedbow));
		
		
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.titanspearRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.iceblockRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.transformIntoWolfRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healLightWoundsRune));	
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.icelanceRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bloodtheftRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.transformIntoSkeletonRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healMediumWoundsRune));
				
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.fireballRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.firerainRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.transformIntoOrcWarriorRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healHeavyWoundsRune));
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healLightWoundsSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.titanspearSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.iceblockSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.transformIntoWolfSpell));
			
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healMediumWoundsSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.icelanceSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bloodtheftSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.transformIntoSkeletonSpell));
				
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healHeavyWoundsSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.fireballSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.firerainSpell));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.transformIntoOrcWarriorSpell));
		
		
		
		
		
		
		
		

		
		
		
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.lightImperialArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mediumImperialArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.heavyImperialArmor));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.lightRebelArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mediumRebelArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.heavyRebelArmor));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.noviceArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mageArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.heavyMageArmor));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.leatherArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.blackknightArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenArmor));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilArmor));
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.gold));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.gold));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.gold));

		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.apple));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.apple));



		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.arrow));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.arrow));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.arrow));
	
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bone));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bone));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bone));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bone));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.multipleSticks));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.wheat));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.trophy));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bread));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.carrot));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.rawChicken));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.cookedChicken));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.rawFish));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.cookedFish));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.rawMeat));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.cookedMeat));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.rawPork));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.cookedPork));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ironBar));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldenBar));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilBar));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mithrilBar));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.feather));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.treasureChest));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldCrown));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.treasure));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.smallHpPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mediumHpPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bigHpPotion));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.smallManaPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mediumManaPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bigManaPotion));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.maxHpBonusPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.maxManaBonusPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.strengthPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.dexterityPotion));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.speedPotion));
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.dragonroot));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goblinweed));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.goldtruffle));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.wolfnettel));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healberry));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healplant));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.healroot));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.manaberry));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.manaplant));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.manaroot));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.smallBottle));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.mediumBottle));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bigBottle));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.emptyRune));
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.chrystal));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.icecube));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.wolfHide));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.smaragd));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.aquamarine));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.pitch));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.sulfur));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.orcTooth));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.heart));
		
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.letter));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.note));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.redbook));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.bluebook));
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.blackbook));
		
		itemList.add(new Item(MobManager.getPlayer().getStartCenterXTile(), MobManager.getPlayer().getStartCenterYTile(), itemTypeManager.ogusChrystal));
		
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