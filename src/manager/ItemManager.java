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
			
		
		itemList.add(new Item(480, 416, itemTypeManager.stick));
		itemList.add(new Item(480, 416, itemTypeManager.stick));
						
		itemList.add(new Item(480, 416, itemTypeManager.dagger));
		itemList.add(new Item(480, 416, itemTypeManager.mace));
		itemList.add(new Item(480, 416, itemTypeManager.ironsword));
		itemList.add(new Item(480, 416, itemTypeManager.goldensword));
		itemList.add(new Item(480, 416, itemTypeManager.mithrilsword));
		itemList.add(new Item(480, 416, itemTypeManager.blacksword));
		
		itemList.add(new Item(480, 416, itemTypeManager.sabre));
		itemList.add(new Item(480, 416, itemTypeManager.rapier));
		itemList.add(new Item(480, 416, itemTypeManager.mithrilrapier));
		
		itemList.add(new Item(480, 416, itemTypeManager.shortspear));
		itemList.add(new Item(480, 416, itemTypeManager.longspear));
		itemList.add(new Item(480, 416, itemTypeManager.mithrilspear));
		itemList.add(new Item(480, 416, itemTypeManager.goldenspear));
		
		itemList.add(new Item(480, 416, itemTypeManager.bow));
		itemList.add(new Item(480, 416, itemTypeManager.longbow));
		itemList.add(new Item(480, 416, itemTypeManager.recurvedbow));
		
		
		
		
		itemList.add(new Item(480, 416, itemTypeManager.titanspearRune));
		itemList.add(new Item(480, 416, itemTypeManager.iceblockRune));
		itemList.add(new Item(480, 416, itemTypeManager.transformIntoWolfRune));
		itemList.add(new Item(480, 416, itemTypeManager.healLightWoundsRune));	
		
		itemList.add(new Item(480, 416, itemTypeManager.icelanceRune));
		itemList.add(new Item(480, 416, itemTypeManager.bloodtheftRune));
		itemList.add(new Item(480, 416, itemTypeManager.transformIntoSkeletonRune));
		itemList.add(new Item(480, 416, itemTypeManager.healMediumWoundsRune));
				
		itemList.add(new Item(480, 416, itemTypeManager.fireballRune));
		itemList.add(new Item(480, 416, itemTypeManager.firerainRune));
		itemList.add(new Item(480, 416, itemTypeManager.transformIntoOrcWarriorRune));
		itemList.add(new Item(480, 416, itemTypeManager.healHeavyWoundsRune));
		
		
		itemList.add(new Item(480, 416, itemTypeManager.healLightWoundsSpell));
		itemList.add(new Item(480, 416, itemTypeManager.titanspearSpell));
		itemList.add(new Item(480, 416, itemTypeManager.iceblockSpell));
		itemList.add(new Item(480, 416, itemTypeManager.transformIntoWolfSpell));
			
		itemList.add(new Item(480, 416, itemTypeManager.healMediumWoundsSpell));
		itemList.add(new Item(480, 416, itemTypeManager.icelanceSpell));
		itemList.add(new Item(480, 416, itemTypeManager.bloodtheftSpell));
		itemList.add(new Item(480, 416, itemTypeManager.transformIntoSkeletonSpell));
				
		itemList.add(new Item(480, 416, itemTypeManager.healHeavyWoundsSpell));
		itemList.add(new Item(480, 416, itemTypeManager.fireballSpell));
		itemList.add(new Item(480, 416, itemTypeManager.firerainSpell));
		itemList.add(new Item(480, 416, itemTypeManager.transformIntoOrcWarriorSpell));
		
		
		
		
		
		
		
		

		
		
		
		
		
		itemList.add(new Item(480, 416, itemTypeManager.lightImperialArmor));
		itemList.add(new Item(480, 416, itemTypeManager.mediumImperialArmor));
		itemList.add(new Item(480, 416, itemTypeManager.heavyImperialArmor));
		
		itemList.add(new Item(480, 416, itemTypeManager.lightRebelArmor));
		itemList.add(new Item(480, 416, itemTypeManager.mediumRebelArmor));
		itemList.add(new Item(480, 416, itemTypeManager.heavyRebelArmor));
		
		itemList.add(new Item(480, 416, itemTypeManager.noviceArmor));
		itemList.add(new Item(480, 416, itemTypeManager.mageArmor));
		itemList.add(new Item(480, 416, itemTypeManager.heavyMageArmor));
		
		itemList.add(new Item(480, 416, itemTypeManager.leatherArmor));
		itemList.add(new Item(480, 416, itemTypeManager.ironArmor));
		itemList.add(new Item(480, 416, itemTypeManager.blackknightArmor));
		itemList.add(new Item(480, 416, itemTypeManager.goldenArmor));
		itemList.add(new Item(480, 416, itemTypeManager.mithrilArmor));
		
		
		itemList.add(new Item(480, 416, itemTypeManager.gold));
		itemList.add(new Item(704, 416, itemTypeManager.gold));
		itemList.add(new Item(416, 480, itemTypeManager.gold));

		itemList.add(new Item(544, 544, itemTypeManager.apple));
		itemList.add(new Item(672, 544, itemTypeManager.apple));



		itemList.add(new Item(768, 480, itemTypeManager.arrow));
		itemList.add(new Item(768, 544, itemTypeManager.arrow));
		itemList.add(new Item(768, 608, itemTypeManager.arrow));
	
		itemList.add(new Item(800, 320, itemTypeManager.bone));
		itemList.add(new Item(832, 320, itemTypeManager.bone));
		itemList.add(new Item(864, 640, itemTypeManager.bone));
		itemList.add(new Item(896, 384, itemTypeManager.bone));
		itemList.add(new Item(256, 0, itemTypeManager.multipleSticks));
		itemList.add(new Item(320, 32, itemTypeManager.wheat));
		itemList.add(new Item(364, 32, itemTypeManager.trophy));
		
		itemList.add(new Item(864, 320, itemTypeManager.bread));
		itemList.add(new Item(736, 480, itemTypeManager.carrot));
		itemList.add(new Item(64, 1024, itemTypeManager.rawChicken));
		itemList.add(new Item(32, 1024, itemTypeManager.cookedChicken));
		itemList.add(new Item(1024, 64, itemTypeManager.rawFish));
		itemList.add(new Item(1024, 0, itemTypeManager.cookedFish));
		itemList.add(new Item(1024, 64, itemTypeManager.rawMeat));
		itemList.add(new Item(1024, 0, itemTypeManager.cookedMeat));
		itemList.add(new Item(256, 704, itemTypeManager.rawPork));
		itemList.add(new Item(128, 768, itemTypeManager.cookedPork));
		
		itemList.add(new Item(64, 128, itemTypeManager.ironBar));
		itemList.add(new Item(64, 128, itemTypeManager.ironBar));
		itemList.add(new Item(64, 128, itemTypeManager.ironBar));
		itemList.add(new Item(64, 128, itemTypeManager.ironBar));
		itemList.add(new Item(64, 128, itemTypeManager.ironBar));
		itemList.add(new Item(64, 128, itemTypeManager.ironBar));
		
		itemList.add(new Item(64, 192, itemTypeManager.goldenBar));
		itemList.add(new Item(64, 192, itemTypeManager.goldenBar));
		itemList.add(new Item(64, 192, itemTypeManager.goldenBar));
		itemList.add(new Item(64, 192, itemTypeManager.goldenBar));
		itemList.add(new Item(64, 192, itemTypeManager.goldenBar));
		itemList.add(new Item(64, 192, itemTypeManager.goldenBar));
		
		itemList.add(new Item(64, 192, itemTypeManager.mithrilBar));
		itemList.add(new Item(64, 192, itemTypeManager.mithrilBar));
		itemList.add(new Item(64, 192, itemTypeManager.mithrilBar));
		itemList.add(new Item(64, 192, itemTypeManager.mithrilBar));
		itemList.add(new Item(64, 192, itemTypeManager.mithrilBar));
		itemList.add(new Item(64, 192, itemTypeManager.mithrilBar));
		
		itemList.add(new Item(512, 0, itemTypeManager.feather));
		itemList.add(new Item(576, 0, itemTypeManager.treasureChest));
		itemList.add(new Item(512, 96, itemTypeManager.goldCrown));
		itemList.add(new Item(576, 64, itemTypeManager.treasure));
		
		itemList.add(new Item(512, 128, itemTypeManager.smallHpPotion));
		itemList.add(new Item(0, 96, itemTypeManager.mediumHpPotion));
		itemList.add(new Item(320, 32, itemTypeManager.bigHpPotion));
		
		itemList.add(new Item(128, 192, itemTypeManager.smallManaPotion));
		itemList.add(new Item(128, 128, itemTypeManager.mediumManaPotion));
		itemList.add(new Item(128, 96, itemTypeManager.bigManaPotion));
		
		itemList.add(new Item(128, 128, itemTypeManager.maxHpBonusPotion));
		itemList.add(new Item(128, 128, itemTypeManager.maxManaBonusPotion));
		itemList.add(new Item(128, 128, itemTypeManager.strengthPotion));
		itemList.add(new Item(128, 128, itemTypeManager.dexterityPotion));
		itemList.add(new Item(128, 128, itemTypeManager.speedPotion));
		
		
		itemList.add(new Item(0, 0, itemTypeManager.dragonroot));
		itemList.add(new Item(0, 32, itemTypeManager.goblinweed));
		itemList.add(new Item(64, 96, itemTypeManager.goldtruffle));
		itemList.add(new Item(96, 64, itemTypeManager.wolfnettel));
		
		itemList.add(new Item(32, 32, itemTypeManager.healberry));
		itemList.add(new Item(32, 64, itemTypeManager.healplant));
		itemList.add(new Item(32, 96, itemTypeManager.healroot));
		
		itemList.add(new Item(64, 0, itemTypeManager.manaberry));
		itemList.add(new Item(96, 96, itemTypeManager.manaplant));
		itemList.add(new Item(64, 64, itemTypeManager.manaroot));
		
		itemList.add(new Item(64, 0, itemTypeManager.smallBottle));
		itemList.add(new Item(96, 96, itemTypeManager.mediumBottle));
		itemList.add(new Item(64, 64, itemTypeManager.bigBottle));
		
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		itemList.add(new Item(1024, 64, itemTypeManager.emptyRune));
		
		
		itemList.add(new Item(1088, 64, itemTypeManager.chrystal));
		itemList.add(new Item(1024, 32, itemTypeManager.icecube));
		itemList.add(new Item(1152, 64, itemTypeManager.wolfHide));
		itemList.add(new Item(1216, 64, itemTypeManager.smaragd));
		itemList.add(new Item(1280, 64, itemTypeManager.aquamarine));
		itemList.add(new Item(1344, 64, itemTypeManager.pitch));
		itemList.add(new Item(1408, 64, itemTypeManager.sulfur));
		itemList.add(new Item(1408, 32, itemTypeManager.orcTooth));
		itemList.add(new Item(1408, 32, itemTypeManager.heart));
		
		
		itemList.add(new Item(1408, 192, itemTypeManager.letter));
		itemList.add(new Item(1408, 192, itemTypeManager.note));
		itemList.add(new Item(1408, 192, itemTypeManager.redbook));
		itemList.add(new Item(1408, 192, itemTypeManager.bluebook));
		itemList.add(new Item(1408, 192, itemTypeManager.blackbook));
		
		itemList.add(new Item(1408, 192, itemTypeManager.ogusChrystal));
		
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