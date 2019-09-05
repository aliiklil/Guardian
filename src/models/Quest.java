package models;

import java.util.ArrayList;

public class Quest {

	private String questTitle;
	
	private boolean inactive = true;
	private boolean active = false;
	private boolean finished = false;
	private boolean failed = false;
	
	private int experienceReward;
	private int goldReward;
	private Item itemReward;
	
	private ArrayList<String> notes = new ArrayList<String>();
	
	public Quest(String questTitle, int experienceReward) {
		this.questTitle = questTitle;
		this.experienceReward = experienceReward;
	}

	public String getQuestTitle() {
		return questTitle;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public int getExperienceReward() {
		return experienceReward;
	}
	
	public int getGoldReward() {
		return goldReward;
	}

	public void setGoldReward(int goldReward) {
		this.goldReward = goldReward;
	}

	public Item getItemReward() {
		return itemReward;
	}

	public void setItemReward(Item itemReward) {
		this.itemReward = itemReward;
	}

	public ArrayList<String> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}
		
}
