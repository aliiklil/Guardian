package dialogue;

public class Sentence {

	private String sentence;
	private String speakerName;
	
	public Sentence(String sentence, String speakerName) {
		this.sentence = sentence;
		this.speakerName = speakerName;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public String getSpeakerName() {
		return speakerName;
	}
	
}
