package manager;

import java.util.ArrayList;

import dialogue.Dialogue;

public class DialogueManager {
	
	public static ArrayList<Dialogue> anvilDialogues = new ArrayList<Dialogue>();
	public static ArrayList<Dialogue> alchemyDeskDialogues = new ArrayList<Dialogue>();
	public static ArrayList<Dialogue> runeForgingDialogues = new ArrayList<Dialogue>();
	
	public static ArrayList<Dialogue> ogusDialogues = new ArrayList<Dialogue>();
	public static ArrayList<Dialogue> halrokDialogues = new ArrayList<Dialogue>();
	
	public static ArrayList<Dialogue> jorgenDialogues = new ArrayList<Dialogue>();
	
	public DialogueManager() {
				
		ogusDialogues.add(new Dialogue());
		ogusDialogues.get(0).addSentence("What are you doing?", "Hero");
		ogusDialogues.get(0).addSentence("My name is Ogus and I'm a guard of this city.", "Ogus");
		ogusDialogues.get(0).addSentence("Do you need something?", "Ogus");

		ogusDialogues.get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("Where does the road south of here go?", "Hero");
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("It leads to an old farm.", "Ogus");
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("If you go there, make sure to stay on the roads. The forest can be very dangerous.", "Ogus");
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("I will take care.", "Hero");
		
		ogusDialogues.get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(0).getChildDialogues().get(1).addSentence("Nothing, thanks.", "Hero");
		ogusDialogues.get(0).getChildDialogues().get(1).addSentence("If you need something, you can always ask us.", "Ogus");
		
		
					
		ogusDialogues.add(new Dialogue());
		ogusDialogues.get(1).addSentence("Can you teach me something?", "Hero");
		ogusDialogues.get(1).addSentence("Sure, I can help you to get stronger and increase your dexterity.", "Ogus");
		
		ogusDialogues.get(1).addNewStartingDialogue(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).setPermanent(true);
		ogusDialogues.get(1).getNewStartingDialogues().get(0).setForLearning(true);
		ogusDialogues.get(1).getNewStartingDialogues().get(0).addSentence("Teach me something.", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).addSentence("Sure.", "Ogus");
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(0).addSentence("Strength + 5 (Costs 5LP)", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(0).addSentence("You are a fast learner.", "Ogus");
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(1).addSentence("Dexterity + 5 (Costs 5LP)", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(1).addSentence("You are a fast learner.", "Ogus");
		
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(2).addSentence("Health Points + 5 (Costs 5LP)", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(2).addSentence("You are a fast learner.", "Ogus");
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(3).addSentence("Mana + 5 (Costs 5LP)", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(3).addSentence("You are a fast learner.", "Ogus");
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(4).addSentence("Melee Skill + 5 (Costs 5LP)", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(4).addSentence("You are a fast learner.", "Ogus");
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(5).addSentence("Bow Skill + 5 (Costs 5LP)", "Hero");
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(5).addSentence("You are a fast learner.", "Ogus");
		
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		ogusDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(6).addSentence("Back", "Hero");
		
		
		
		jorgenDialogues.add(new Dialogue());
		jorgenDialogues.get(0).addSentence("What do you sell?", "Hero");
		jorgenDialogues.get(0).addSentence("I sell everything from weapons and armor to potions.", "Jorgen");
		jorgenDialogues.get(0).addSentence("Take a look at my goods if you want.", "Jorgen");
		
		jorgenDialogues.add(new Dialogue());
		jorgenDialogues.get(1).setPermanent(true);
		jorgenDialogues.get(1).addSentence("Show me your goods.", "Hero");
		jorgenDialogues.get(1).addSentence("Sure.", "Jorgen");
		
		
		

		
		halrokDialogues.add(new Dialogue());
		halrokDialogues.get(0).addSentence("Why do you look so scared?", "Hero");
		halrokDialogues.get(0).addSentence("Didn't you see the wolf east of here?", "Halrok");
		halrokDialogues.get(0).addSentence("It is coming too close to the city. If you kill it, I will give you 50 gold.", "Halrok");
		halrokDialogues.get(0).addSentence("Sure, I can kill it.", "Hero");
		halrokDialogues.get(0).addSentence("Thanks. Come back to me after you have killed it.", "Halrok");
		halrokDialogues.get(0).setQuestTitle("Wolf Hunt");
		
		halrokDialogues.add(new Dialogue());
		halrokDialogues.get(1).addSentence("Can you teach me something?", "Hero");
		halrokDialogues.get(1).addSentence("Sure, I can teach you a lot of skills.", "Halrok");
		
		halrokDialogues.get(1).addNewStartingDialogue(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).setPermanent(true);
		halrokDialogues.get(1).getNewStartingDialogues().get(0).setForLearning(true);
		halrokDialogues.get(1).getNewStartingDialogues().get(0).addSentence("Teach me something.", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).addSentence("Sure.", "Halrok");

		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(0).addSentence("Lockpicking + 1 (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(0).addSentence("You are a fast learner.", "Halrok");
		
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(1).addSentence("Alchemy + 1 (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(1).addSentence("You are a fast learner.", "Halrok");
	
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(2).addSentence("Blacksmithing + 1 (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(2).addSentence("You are a fast learner.", "Halrok");
		
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(3).addSentence("Runeforging + 1 (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(3).addSentence("You are a fast learner.", "Ogus");
		
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(4).addSentence("Take Furs (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(4).addSentence("You are a fast learner.", "Halrok");
		
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(5).addSentence("Take Trophies (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(5).addSentence("You are a fast learner.", "Halrok");
		
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(6).addSentence("HP Regeneration (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(6).addSentence("You are a fast learner.", "Halrok");
	
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(7).addSentence("Mana Regeneration (Costs 10LP)", "Hero");
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(7).addSentence("You are a fast learner.", "Halrok");
		
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().add(new Dialogue());
		halrokDialogues.get(1).getNewStartingDialogues().get(0).getChildDialogues().get(8).addSentence("Back", "Hero");
		
				
	}
	
}
