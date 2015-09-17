package com.enrique.leon.Parsing;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.enrique.leon.ClassModel.ClassSection;
import com.enrique.leon.ClassModel.ClassSection.ClassSectionBuilder;

import com.enrique.leon.RequestService.JsoupRequestContent;

import com.enrique.leon.RequestService.RequestContent;
public class ParseResults {
	private Elements classSections;
	private boolean hasElements;
	private Document resultsDocument;
	private List<ClassSection> foundClasses;
	public ParseResults(Document doc){
		this.resultsDocument = doc;
		this.hasElements = checkForResults();
		this.classSections = buildElementsList();
		this.foundClasses = new ArrayList<ClassSection>();
		if(this.hasElements){
			for(Element classSection:classSections){
				foundClasses.add(this.buildClassSection(classSection));
			}
		}
	}
	public ParseResults(String html){
		this(Jsoup.parse(html));
	}
	public ParseResults(RequestContent content){
		this(content.getHtmlBody());
	}
	public ParseResults(JsoupRequestContent content){
		this(content.getRequestDocument());
	}
	public List<ClassSection> getClassSection(){
		return this.foundClasses;
	}
	private boolean checkForResults(){
		boolean contains = false;
		Elements checkForResults = this.resultsDocument.select("#sectionTitleRecord");
		contains = checkForResults.size()>0;
		return contains;
	}
	private Elements buildElementsList(){
		Elements results = this.resultsDocument.select(".sectionRecordOdd,.sectionRecordEven");
		return results;
	}
	private ClassSection buildClassSection(Element classElement){
		ClassSection builtClass;
		ClassSectionBuilder classBuilder = new ClassSection.ClassSectionBuilder();
		Elements classRecordLevels = classElement.select(".sectionMeeting");
		for(Element record:classRecordLevels){
			buildCourseName(record,classBuilder);
			buildSectionNumber(record,classBuilder);
			buildScheduleNumber(record,classBuilder);
			buildCourseTitle(record,classBuilder);
			buildCourseUnits(record,classBuilder);
			buildCourseType(record,classBuilder);
			buildCourseTime(record,classBuilder);
			buildCourseDay(record,classBuilder);
			buildCourseLocation(record,classBuilder);
			buildCourseInstructor(record,classBuilder);
			buildCourseSeats(record,classBuilder);
		}
		builtClass = classBuilder.build();
		return builtClass;
	}
	private void buildCourseName(Element sectionMeeting,ClassSectionBuilder builder){
		Element fieldCourseColumn = sectionMeeting.select(".sectionFieldCourse").first();
		if(fieldCourseColumn!=null){
			Element courseNameElement = fieldCourseColumn.select("[href]").first();
			if(courseNameElement!=null&&courseNameElement.hasText()){
				String courseName = courseNameElement.text();
				String sectionDetailsUrl = courseNameElement.attr("href");
				builder.courseName(courseName);
				builder.sectionDetailsUrl(sectionDetailsUrl);
			}
		}
	}
	private void buildSectionNumber(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldSec = sectionMeeting.select(".sectionFieldSec").first();
		String sectionNumber = sectionFieldSec.text();
		if(!sectionNumber.equals("")){
				builder.sectionNumber(sectionNumber);
		}
	}
	private void buildScheduleNumber(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldSched = sectionMeeting.select(".sectionFieldSched").first();
		if(sectionFieldSched!=null&&sectionFieldSched.hasText()){
			builder.scheduleNumber(sectionFieldSched.text());
		}
	}
	private void buildCourseTitle(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldTitle = sectionMeeting.select(".sectionFieldTitle").first();
		if(sectionFieldTitle!=null&&sectionFieldTitle.hasText()){
			builder.courseTitle(sectionFieldTitle.text());
		}
	}
	private void buildCourseUnits(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldUnits = sectionMeeting.select(".sectionFieldUnits").first();
		if(sectionFieldUnits!=null&&sectionFieldUnits.hasText()){
			builder.units(sectionFieldUnits.text());
		}
	}
	private void buildCourseType(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldType = sectionMeeting.select(".sectionFieldType").first();
		if(sectionFieldType!=null&&sectionFieldType.hasText()){
			builder.formats(sectionFieldType.text());
		}
	}
	private void buildCourseTime(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldTime = sectionMeeting.select(".sectionFieldTime").first();
		if(sectionFieldTime!=null&&sectionFieldTime.hasText()){
			builder.meetingTime(sectionFieldTime.text());
		}
	}
	private void buildCourseDay(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldDay = sectionMeeting.select(".sectionFieldDay").first();
		if(sectionFieldDay!=null&&sectionFieldDay.hasText()){
			builder.days(sectionFieldDay.text());
		}
	}
	private void buildCourseLocation(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldLocation = sectionMeeting.select(".sectionFieldLocation").first();
		if(sectionFieldLocation!=null){
			Element location = sectionFieldLocation.select("a").first();
			if(location!=null&&location.hasText())
				builder.locations(location.text());
		}
	}
	private void buildCourseInstructor(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldInstructor = sectionMeeting.select(".sectionFieldInstructor").first();
		if(sectionFieldInstructor!=null){
			Element instructor = sectionFieldInstructor.select("a").first();
			if(instructor!=null&&instructor.hasText())
				builder.instructors(instructor.text());
		}
	}
	private void buildCourseSeats(Element sectionMeeting,ClassSectionBuilder builder){
		Element sectionFieldSeats = sectionMeeting.select(".sectionFieldSeats").first();
		if(sectionFieldSeats!=null&&sectionFieldSeats.hasText()){
			builder.seats(sectionFieldSeats.text());
		}
	}
}
