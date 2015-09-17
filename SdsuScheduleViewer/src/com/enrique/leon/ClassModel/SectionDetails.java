package com.enrique.leon.ClassModel;

import java.util.ArrayList;
import java.util.Collection;

public class SectionDetails {
	private String description;
	private String prerequisite;
	private ArrayList<String> footNoteCode;
	private ArrayList<String> footNote;
	public SectionDetails(){
		
	}
	public SectionDetails(String description, String prerequisite, Collection<String> footNoteCode,
			Collection<String> footNote) {
		super();
		this.description = description;
		this.prerequisite = prerequisite;
		this.footNoteCode = new ArrayList<String>(footNoteCode);
		this.footNote = new ArrayList<String>(footNote);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public ArrayList<String> getFootNoteCode() {
		return footNoteCode;
	}
	public void setFootNoteCode(ArrayList<String> footNoteCode) {
		this.footNoteCode = footNoteCode;
	}
	public ArrayList<String> getFootNote() {
		return footNote;
	}
	public void setFootNote(ArrayList<String> footNote) {
		this.footNote = footNote;
	}
	

}
