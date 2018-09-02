package dialogue;

import java.util.ArrayList;

public class Dialogue {

	private ArrayList<Sentence> sentences = new ArrayList<Sentence>();
	private ArrayList<Dialogue> childDialogues = new ArrayList<Dialogue>();
	
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

}
