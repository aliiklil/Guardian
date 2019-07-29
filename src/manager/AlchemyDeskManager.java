package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.AlchemyDesk;
import models.Anvil;

public class AlchemyDeskManager {

	private static ArrayList<AlchemyDesk> alchemyDeskList = new ArrayList<AlchemyDesk>();
		
	public AlchemyDeskManager() throws SlickException {
		
		AlchemyDesk alchemyDesk1 = new AlchemyDesk(15, 6, DialogueManager.alchemyDeskDialogues);
		
		alchemyDeskList.add(alchemyDesk1);
		
	}
	
	public void update() throws SlickException {
		
		for(AlchemyDesk alchemyDesk : alchemyDeskList)
			alchemyDesk.update();
		
	}

	public void render(Graphics g) {
			
		for(AlchemyDesk alchemyDesk : alchemyDeskList)
			alchemyDesk.render(g);
		
	}

	public static ArrayList<AlchemyDesk> getAlchemyDeskList() {
		return alchemyDeskList;
	}
	
}

