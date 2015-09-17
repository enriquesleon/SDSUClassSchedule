package com.enrique.leon.ClassModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.enrique.leon.ClassModel.SectionDetails;
public class ClassSection {
	
	private final String courseName;
	private final String courseTitle;
	private final String units;
	private final String seats;
	private final String maxSeats;
	private final String openSeats;
	private final String sectionNumber;
	private final String scheduleNumber;
	private final boolean isOpen;
	private final String sectionDetailsUrl;
	private SectionDetails sectionDetails;

	private final List<String> days;
	private final List<String> startTime;
	private final List<String> endTime;
	private final List<String> meetingTime;
	private final List<String> locations;
	private final List<String> instructors;
	private final List<String> formats;
	public static class ClassSectionBuilder{
		
		private String courseName;
		private String courseTitle;
		private String units;
		private String seats;
		private String openSeats;
		private String maxSeats;
		private String sectionNumber;
		private String scheduleNumber;
		private String sectionDetailsUrl;
		private boolean isOpen;
		
		private List<String> startTime = new ArrayList<String>(5);
		private List<String> endTime = new ArrayList<String>(5);
		private List<String> meetingTime = new ArrayList<String>(5);
		private List<String> days = new ArrayList<String>(5);
		private List<String> locations = new ArrayList<String>(5);
		private List<String> instructors = new ArrayList<String>(5);
		private List<String> formats = new ArrayList<String>(5);
		public ClassSectionBuilder courseName(String val){
			courseName = val;
			return this;
		}
		public ClassSectionBuilder courseTitle(String val){
			courseTitle = val;
			return this;
		}
		public ClassSectionBuilder units(String val){
			units = val;
			return this;
		}
		public ClassSectionBuilder seats(String val){
			seats = val;
			maxSeats = this.getMaxSeatFromString(seats);
			openSeats = this.getOpenSeatFromString(seats);
			isOpen = this.seatAvailability(openSeats);
			return this;
		}
		public ClassSectionBuilder sectionNumber(String val){
			sectionNumber = val;
			return this;
		}
		public ClassSectionBuilder scheduleNumber(String val){
			scheduleNumber = val;
			return this;
		}
		public ClassSectionBuilder meetingTime(String val){
			meetingTime.add(val);
			startTime.add(this.getStartTimeFromString(val));
			endTime.add(this.getEndTimeFromString(val));
			return this;
		}
		public ClassSectionBuilder days(String val){
			days.add(val);
			return this;
		}
		public ClassSectionBuilder locations(String val){
			locations.add(val);
			return this;
		}
		public ClassSectionBuilder instructors(String val){
			instructors.add(val);
			return this;
		}
		public ClassSectionBuilder sectionDetailsUrl(String val){
			sectionDetailsUrl = val;
			return this;
		}
		public ClassSectionBuilder formats(String val){
			formats.add(val);
			return this;
		}
		public ClassSection build(){
			return new ClassSection(this);
		}

		private String getStartTimeFromString(String meetingTime){
			String startTime;
			String[] meetingParts = meetingTime.split("-");
			if (meetingParts[0].equals(meetingTime)){
				startTime = "0000";
			}
			else startTime = meetingParts[0];
			return startTime;
		}
		private String getEndTimeFromString(String meetingTime){
			String endTime;
			String meetingParts[] = meetingTime.split("-");
			if (meetingParts[0].equals(meetingTime)){
				endTime = "0000";
			}
			else endTime = meetingParts[0];
			return endTime;
		}
		private String getMaxSeatFromString(String seats){
			String maxSeat;
			String[] parts = seats.split("/");
			if(parts[0].equals(seats)){
				maxSeat = "NA";
			}
			else maxSeat = parts[1];
			return maxSeat;
		}
		private String getOpenSeatFromString(String seats){
			String openSeat;
			String[] parts = seats.split("/");
			if(parts[0].equals(seats)){
				openSeat = "NA";
			}
			else openSeat = parts[0];
			return openSeat;
		}
		private boolean seatAvailability(String open){
			int openSeats;			
			try {
				openSeats = Integer.parseInt(open);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
			return (openSeats > 0)? true: false;
		}
	}
	private ClassSection(ClassSectionBuilder b){
		
		this.courseName = b.courseName;
		this.courseTitle= b.courseTitle;
		this.units = b.units;
		this.seats = b.seats;
		this.maxSeats = b.maxSeats;
		this.openSeats = b.openSeats;
		this.days = b.days;
		this.locations = b.locations;
		this.sectionNumber = b.sectionNumber;
		this.scheduleNumber = b.scheduleNumber;
		this.startTime = b.startTime;
		this.endTime = b.endTime;
		this.meetingTime = b.meetingTime;
		this.instructors = b.instructors;
		this.isOpen = b.isOpen;
		this.sectionDetailsUrl = b.sectionDetailsUrl;
		this.formats = b.formats;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public String getUnits() {
		return units;
	}
	public String getSeats() {
		return seats;
	}
	public String getDays(int index){
		try {
			return days.get(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	public String getStartTime(int index){
		try {
			return startTime.get(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	public String getEndTime(int index){
		try {
			return endTime.get(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	public List<String> getLocations(){
		return this.locations;
	}
	public String getLocation(int index){
		try {
			return this.locations.get(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	public String getMaxSeats() {
		return maxSeats;
	}
	public String getOpenSeats() {
		return openSeats;
	}
	public List<String> getMeetingTimes(){
		return this.meetingTime;
	}
	public String getMeetingTime(int index){
		try {
			return this.meetingTime.get(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	public String getSectionNumber() {
		return sectionNumber;
	}
	public String getScheduleNumber() {
		return scheduleNumber;
	}
	public List<String> getInstructors(){
		return instructors;
	}
	public String getInstructor(int index){
		try {
			return instructors.get(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	public List<String> getFormats(){
		return this.formats;
	}
	
	public String getInstructor(){
		return getInstructor(0);
	}
	public boolean isOpen(){
		return isOpen;
	}
	public String getSectionDetailsUrl(){
		return sectionDetailsUrl;
	}
	public SectionDetails sectiondetails(String description, String prerequisite, Collection<String> footNoteCode,
			Collection<String> footNote){
		if(this.sectionDetails==null)
			this.sectionDetails = new SectionDetails(description, prerequisite, footNoteCode, footNote);
		return this.sectionDetails;
	}
	public static Comparator<ClassSection> courseNameComparator(){
		return new Comparator<ClassSection>(){

			
			public int compare(ClassSection arg0, ClassSection arg1) {
				return arg0.getCourseName().compareTo(arg1.getCourseName());
			}
			
		};

	}
	public static Comparator<ClassSection> courseTitleComparator(){
		return new Comparator<ClassSection>(){

			
			public int compare(ClassSection arg0, ClassSection arg1) {
				return arg0.getCourseTitle().compareTo(arg1.getCourseTitle());
			}
			
		};

	}
	public static Comparator<ClassSection> startTimeComparator(){
		return new Comparator<ClassSection>(){

			
			public int compare(ClassSection arg0, ClassSection arg1) {
				return arg0.getStartTime(0).compareTo(arg1.getStartTime(0));
			}
			
		};

	}
	public static Comparator<ClassSection> endTimeComparator(){
		return new Comparator<ClassSection>(){

			
			public int compare(ClassSection arg0, ClassSection arg1) {
				return arg0.getEndTime(0).compareTo(arg1.getEndTime(0));
			}
			
		};

	}
	public static Comparator<ClassSection> scheduleNumberComparator(){
		return new Comparator<ClassSection>(){

			
			public int compare(ClassSection arg0, ClassSection arg1) {
				return arg0.getScheduleNumber().compareTo(arg1.getScheduleNumber());
			}
			
		};

	}
	public static Comparator<ClassSection> instructorComparator(){
		return new Comparator<ClassSection>(){

			
			public int compare(ClassSection arg0, ClassSection arg1) {
				return arg0.getInstructors().get(0).compareTo(arg1.getInstructors().get(0));
			}
			
		};

	}
	public static enum SortBy{
		SORT_NAME,
		SORT_TITLE,
		SORT_START,
		SORT_END,
		SORT_SCHED,
		SORT_INSTRUCT
		
	}
	public static void sort(List<ClassSection> classes,SortBy option){
		switch(option){
		case SORT_END:
			classes.sort(ClassSection.endTimeComparator());
			break;
		case SORT_INSTRUCT:
			classes.sort(ClassSection.instructorComparator());
			break;
		case SORT_NAME:
			classes.sort(ClassSection.courseNameComparator());
			break;
		case SORT_SCHED:
			classes.sort(ClassSection.scheduleNumberComparator());
			break;
		case SORT_START:
			classes.sort(ClassSection.startTimeComparator());
			break;
		case SORT_TITLE:
			classes.sort(ClassSection.courseTitleComparator());
			break;
		default:
			break;
		
		}
	}
	public void printClass(){
		System.out.print("Course Name: "+this.courseName);
		System.out.println("Course Title: "+this.courseTitle);
		System.out.println("units: "+this.units);
		System.out.println("seats: "+this.seats);
		System.out.println("sectionNumber: "+this.sectionNumber);
		for(String s:this.instructors){
			System.out.print("Instructors: ");
			System.out.print(s+",");
		}
		for(String s:this.days){
			System.out.print("Meeting Days: ");
			System.out.print(s +",");
		}
		
	}
}
