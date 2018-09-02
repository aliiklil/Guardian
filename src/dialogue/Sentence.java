package dialogue;

public class Sentence {

	private String text;
	private String speakerName;
	
	public Sentence(String text, String speakerName) {
		this.text = text;
		this.speakerName = speakerName;
	}
	
	public String getText() {
		return text;
	}
	
	public String getSpeakerName() {
		return speakerName;
	}
	
}
