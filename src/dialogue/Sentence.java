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
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public String getSpeakerName() {
		return speakerName;
	}
	
}
