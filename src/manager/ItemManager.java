package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Item;

public class ItemManager {
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Item> removeList = new ArrayList<Item>();
	
	private Item potion = new Item(0, 0, "resources/potion.png");
	private Item bigPotion = new Item(320, 256, "resources/bigpotion.png");
	private Item helmet = new Item(288, 384, "resources/helmet.png");
	
	public ItemManager() throws SlickException {
		itemList.add(potion);
		itemList.add(bigPotion);
		itemList.add(helmet);
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