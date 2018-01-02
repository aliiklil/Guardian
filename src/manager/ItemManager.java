package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Item;

public class ItemManager {
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Item> removeList = new ArrayList<Item>();
	
	private Item potion = new Item(128, 256, 32, 32, 100, "resources/items/potion.png", "Healing Potion", 10);
	private Item bigPotion = new Item(320, 256, 32, 32, 100, "resources/items/bigpotion.png", "Big Healing Potion", 25);
	private Item helmet = new Item(288, 384, 32, 32, 100, "resources/items/helmet.png", "Helmet", 250);
	
	private Item potion1 = new Item(384, 288, 32, 32, 100, "resources/items/potion.png", "Healing Potion", 10);
	private Item bigPotion1 = new Item(480, 256, 32, 32, 100, "resources/items/bigpotion.png", "Big Healing Potion", 25);
	private Item helmet1 = new Item(288, 480, 32, 32, 100, "resources/items/helmet.png", "Helmet", 250);
	
	private Item potion2 = new Item(480, 320, 32, 32, 100, "resources/items/potion.png", "Healing Potion", 10);
	private Item bigPotion2 = new Item(320, 640, 32, 32, 100, "resources/items/bigpotion.png", "Big Healing Potion", 25);
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

	
	public ItemManager() throws SlickException {
		itemList.add(potion);
		itemList.add(bigPotion);
		itemList.add(helmet);
		
		itemList.add(potion1);
		itemList.add(bigPotion1);
		itemList.add(helmet1);
		
		itemList.add(potion2);
		itemList.add(bigPotion2);
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