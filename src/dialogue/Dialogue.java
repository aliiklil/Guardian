package dialogue;

import java.util.ArrayList;

public class Dialogue {

	private ArrayList<Sentence> sentences = new ArrayList<Sentence>();
	private ArrayList<Dialogue> childDialogues = new ArrayList<Dialogue>(); //Dialogues, which can be selected after this dialogue is finished
	private ArrayList<Dialogue> newStartingDialogues = new ArrayList<Dialogue>(); //Dialogues, which can be selected in the starting selection, after this dialogue is finished
	
	private boolean isPermanent = false; //Dialogue can be spoken as often as the player wants
	private boolean isForLearning = false; //Dialogue is a learning dialogue
		
	public ArrayList<Sentence> getSentences() {
		return sentences;
	}
	
	public void addSentence(String sentence, String speakerName) {
		sentences.add(new Sentence(sentence, speakerName));
	}
	
	public ArrayList<Dialogue> getChildDialogues() {
		return childDialogues;
	}
	
	public void addChildDialogue(Dialogue childDialogue) {
		childDialogues.add(childDialogue);
	}
	
	public boolean hasChildDialogues() {
		return childDialogues.size() > 0;
	}
	
	public ArrayList<Dialogue> getNewStartingDialogues() {
		return newStartingDialogues;
	}
	
	public void addNewStartingDialogue(Dialogue newStartingDialogue) {
		newStartingDialogues.add(newStartingDialogue);
	}
	
	public boolean hasNewStartingDialogues() {
		return newStartingDialogues.size() > 0;
	}
	
	public boolean isPermanent() {
		return isPermanent;
	}

	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}

	public boolean isForLearning() {
		return isForLearning;
	}

	public void setForLearning(boolean isForLearning) {
		this.isForLearning = isForLearning;
	}
	
}
