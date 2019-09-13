package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.RuneTable;

public class RuneTableManager {

	private static ArrayList<RuneTable> runeTableList = new ArrayList<RuneTable>();
		
	public RuneTableManager() throws SlickException {
		
		RuneTable runeTable1 = new RuneTable(19, 10, DialogueManager.runeForgingDialogues);
		
		//runeTableList.add(runeTable1);
		
	}
	
	public void update() throws SlickException {
		
		for(RuneTable runeTable : runeTableList)
			runeTable.update();
		
	}

	public void renderTop(Graphics g) {
			
		for(RuneTable runeTable : runeTableList)
			runeTable.renderTopAnimation(g);
		
	}
	
	public void renderBottom(Graphics g) {
		
		for(RuneTable runeTable : runeTableList)
			runeTable.renderBottomAnimation(g);
		
	}


	public static ArrayList<RuneTable> getRuneTableList() {
		return runeTableList;
	}
	
}

