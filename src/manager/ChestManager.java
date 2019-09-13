package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import models.Chest;
import models.Item;

public class ChestManager {

	private static ArrayList<Chest> chestList = new ArrayList<Chest>();
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public ChestManager() throws SlickException {
		
		Item boots = new Item(0, 0, itemTypeManager.bone);
		Item bone = new Item(0, 0, itemTypeManager.bone);
		Item rawFish = new Item(0, 0, itemTypeManager.rawFish);
		Item longspear = new Item(0, 0, itemTypeManager.longspear);
		
		Chest chest1 = new Chest(5, 2, boots, 0);
		Chest chest2 = new Chest(3, 5, bone, 1);
		Chest chest3 = new Chest(7, 8, rawFish, 2);
		Chest chest4 = new Chest(9, 10, longspear, 3);
		
		//chestList.add(chest1);
		//chestList.add(chest2);
		//chestList.add(chest3);
		//chestList.add(chest4);
	}
	
	public void update() throws SlickException {
		
		for(Chest chest : chestList)
			chest.update();
		
	}

	public void render(Graphics g) {
			
		for(Chest chest : chestList)
			chest.render(g);
		
	}
	
	public static ArrayList<Chest> getChestList() {
		return chestList;
	}

}

