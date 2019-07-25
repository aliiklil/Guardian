package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Anvil;

public class AnvilManager {

	private static ArrayList<Anvil> anvilList = new ArrayList<Anvil>();
		
	public AnvilManager() throws SlickException {
		
		Anvil anvil1 = new Anvil(10, 2);
		
		anvilList.add(anvil1);
		
	}
	
	public void update() throws SlickException {
		
		for(Anvil anvil : anvilList)
			anvil.update();
		
	}

	public void render(Graphics g) {
			
		for(Anvil anvil : anvilList)
			anvil.render(g);
		
	}

	public static ArrayList<Anvil> getAnvilList() {
		return anvilList;
	}
	
}

