package manager;

import java.util.ArrayList;

import dialogue.Dialogue;

public class DialogueManager {
	
	public static ArrayList<Dialogue> ogusDialogues = new ArrayList<Dialogue>();
	public static ArrayList<Dialogue> halrokDialogues = new ArrayList<Dialogue>();
	
	public DialogueManager() {

		ogusDialogues.add(new Dialogue());
		ogusDialogues.get(0).addSentence("Who are you?", "Hero");
		ogusDialogues.get(0).addSentence("My name is Ogus. Im trying to find my shield.", "Ogus");
		ogusDialogues.get(0).addSentence("I lost it when I got attacked by bandits.", "Ogus");
		
		ogusDialogues.get(0).addChildDialogue(new Dialogue());
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("I can help you. Where did the ambush take place?", "Hero");
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("It was just south from here.", "Ogus");
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("If you bring it to me, I can give you 300 gold.", "Ogus");
		ogusDialogues.get(0).getChildDialogues().get(0).addSentence("I hope, you can bring it to me undamaged.", "Ogus");
		
		ogusDialogues.get(0).addChildDialogue(new Dialogue());
		ogusDialogues.get(0).getChildDialogues().get(1).addSentence("I'm sorry, I can't help you.", "Hero");
		ogusDialogues.get(0).getChildDialogues().get(1).addSentence("No problem, I will find the shield myself.", "Ogus");
		
		
		ogusDialogues.add(new Dialogue());
		ogusDialogues.get(1).addSentence("Where can I find the city?", "Hero");
		ogusDialogues.get(1).addSentence("Just go north east and stay on the road. But take care, there are a lot of bandits robbing people.", "Ogus");
		ogusDialogues.get(1).addSentence("Isn't the city guard patroling around these city?", "Hero");
		ogusDialogues.get(1).addSentence("No, they have a lot of stuff to do inside the city. They don't take a step outside the city anymore. Too dangerous.", "Ogus");
		 
		
		ogusDialogues.add(new Dialogue());
		ogusDialogues.get(2).addSentence("Where can I sell my loot?", "Hero");
		ogusDialogues.get(2).addSentence("There is a merchant close to here in the north.", "Ogus");
		ogusDialogues.get(2).addSentence("You can also go to the city.", "Ogus");
		ogusDialogues.get(2).addSentence("Does the city let unknown people in easily?", "Hero");
		ogusDialogues.get(2).addSentence("No. You must look like someone who has money. They don't need more beggars in the city. They have enough already.", "Ogus");
		
		
		ogusDialogues.add(new Dialogue());
		ogusDialogues.get(3).addSentence("Who is the leader of the city guards?", "Hero");
		ogusDialogues.get(3).addSentence("Currently Ragnar is leading them.", "Ogus");
		ogusDialogues.get(3).addSentence("But if you are not a city guard yourself, you will not be able to meet him.", "Ogus");
		ogusDialogues.get(3).addSentence("Why is that?", "Hero");
		ogusDialogues.get(3).addSentence("I think, they fear that someone might try to assassinate him.", "Ogus");
		ogusDialogues.get(3).addSentence("But why would you want to meet him?", "Ogus");
					
		ogusDialogues.get(3).addChildDialogue(new Dialogue());
		ogusDialogues.get(3).getChildDialogues().get(0).addSentence("I need to give him an important message.", "Hero");
		ogusDialogues.get(3).getChildDialogues().get(0).addSentence("What is the message about?", "Ogus");
		ogusDialogues.get(3).getChildDialogues().get(0).addChildDialogue(new Dialogue());
		ogusDialogues.get(3).getChildDialogues().get(0).getChildDialogues().get(0).addSentence("The kings army needs to move to the capital to defend it.", "Hero");
		ogusDialogues.get(3).getChildDialogues().get(0).getChildDialogues().get(0).addSentence("Why? Who is going to attack?", "Ogus");
		ogusDialogues.get(3).getChildDialogues().get(0).getChildDialogues().get(0).addSentence("There is a army of skeletons coming from the north killing    everything they encounter.", "Hero");
		ogusDialogues.get(3).getChildDialogues().get(0).getChildDialogues().get(0).addSentence("If that is true, I hope your message reaches the king as fast as possible.", "Ogus");
		
		ogusDialogues.get(3).getChildDialogues().get(0).addChildDialogue(new Dialogue());
		ogusDialogues.get(3).getChildDialogues().get(0).getChildDialogues().get(1).addSentence("The message is secret and can only be told him.", "Hero");
		ogusDialogues.get(3).getChildDialogues().get(0).getChildDialogues().get(1).addSentence("Thats unfortunetaly, but I understand.", "Ogus");
		
		ogusDialogues.get(3).addChildDialogue(new Dialogue());
		ogusDialogues.get(3).getChildDialogues().get(1).addSentence("I want to join the city guards myself.", "Hero");
		ogusDialogues.get(3).getChildDialogues().get(1).addSentence("Do as you wish. But from what I've heard they don't pay good.", "Ogus");
		ogusDialogues.get(3).getChildDialogues().get(1).addSentence("And as a new recruit, you will get to do all the dirty work.", "Ogus");
		
		ogusDialogues.get(3).addChildDialogue(new Dialogue());
		ogusDialogues.get(3).getChildDialogues().get(2).addSentence("Thats none of your business.", "Hero");
		ogusDialogues.get(3).getChildDialogues().get(2).addSentence("I'm sorry for asking.", "Ogus");
		
	
		halrokDialogues.add(new Dialogue());
		halrokDialogues.get(0).addSentence("Who are you?", "Hero");
		halrokDialogues.get(0).addSentence("I was awakened by a necromancer. He said my name in my past life was Halrok.", "Halrok");
		halrokDialogues.get(0).addSentence("Why did he do that?", "Hero");
		halrokDialogues.get(0).addSentence("I don't know. He said, he will let us know when the time comes.", "Halrok");	
		
		halrokDialogues.add(new Dialogue());
		halrokDialogues.get(1).addSentence("Where can I find weapons?", "Hero");
		halrokDialogues.get(1).addSentence("I don't know. You can try to go into caves and see if you want something in there.", "Halrok");
		halrokDialogues.get(1).addSentence("Also, I saw an orc just north from here. You can ask him, if you want.", "Halrok");
		halrokDialogues.get(1).addSentence("Thanks, I will do that.", "Hero");
		
	}
	
}
