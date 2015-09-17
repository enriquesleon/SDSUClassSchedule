package com.enrique.leon.Parsing;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.enrique.leon.RequestService.JsoupRequestContent;

import com.enrique.leon.RequestService.RequestContent;

public class ParseMainPage {
	Document doc;

	private Map<String, String> availableOptions;
	private ArrayList<String> periodsOptions = new ArrayList<>();
	private ArrayList<String> courseSubjectsOptions = new ArrayList<>();
	private ArrayList<String> courseSuffixOptions = new ArrayList<>();
	private ArrayList<String> roomOptions = new ArrayList<>();
	private ArrayList<String> delieveryOptions = new ArrayList<>();
	private ArrayList<String> generalEducationOptions = new ArrayList<>();
	
	public ParseMainPage(Document doc) {
		availableOptions = new Hashtable<String, String>();
		
		this.doc=doc;
		this.parasePeriods();
		this.parseCourseSubjects();
		this.parseSuffix();
		this.parseRooms();
		this.parseGenEd();
		this.parseDelivery();
	}

	public ParseMainPage(RequestContent content) {
		this(Jsoup.parse(content.getHtmlBody()));
	}

	public ParseMainPage(JsoupRequestContent content) {
		this(content.getRequestDocument());
	}
	public ParseMainPage(String html){
		this(Jsoup.parse(html));
	}

	private void parasePeriods() {
		Elements links = doc.select("select#period").select("option");
		for (Element e : links) {
			if (!(e.text().equals(""))) {
				periodsOptions.add(e.text());
				availableOptions.put(e.text(), e.attr("value"));
			}
		}
	}

	private void parseCourseSubjects() {
		Elements links = doc.select("select#abbrev").select("option[value]");
		for (Element e : links) {
			if (!(e.text().equals(""))) {
				courseSubjectsOptions.add(e.text());
				availableOptions.put(e.text(), e.attr("value"));
			}
		}
	}

	private void parseSuffix() {
		Elements links = doc.select("select#suffix").select("option[value]");
		for (Element e : links) {
			if (!(e.text().equals(""))) {
				courseSuffixOptions.add(e.text());
				availableOptions.put(e.text(), e.attr("value"));
			}
		}
	}

	private void parseRooms() {
		Elements links = doc.select("select#facility").select("option[value]");
		for (Element e : links) {
			String text = e.text().replaceAll("\u00a0", " ");
			if (!(text.equals(""))) {
				roomOptions.add(text);
				availableOptions.put(text, e.attr("value"));
			}
		}
	}

	private void parseGenEd() {
		Elements links = doc.select("select#ge").select("option[value]");
		for (Element e : links) {
			String text = e.text().replaceAll("\u00a0", " ").trim();
			String attr = e.attr("value");
			if (!(text.equals(""))) {
				text = e.text().replaceAll("\u00a0", " ").trim().split(" ", 2)[1];
				generalEducationOptions.add(text + " - " + attr);
				availableOptions.put(text + " - " + attr, attr);
			}
		}
	}
	private void parseDelivery() {
		Elements links = doc.select("select#meetingType").select("option[value]");
		for (Element e : links) {
			String text = e.text().replaceAll("\u00a0", " ");
			if (!(text.equals(""))) {
				delieveryOptions.add(text);
				availableOptions.put(text, e.attr("value"));
			}
		}
	}

	public Map<String, String> getAvailableOptions() {
		return availableOptions;
	}

	public ArrayList<String> getPeriodsOptions() {
		return periodsOptions;
	}

	public ArrayList<String> getCourseSubjectsOptions() {
		return courseSubjectsOptions;
	}

	public ArrayList<String> getCourseSuffixOptions() {
		return courseSuffixOptions;
	}

	public ArrayList<String> getRoomOptions() {
		return roomOptions;
	}

	public ArrayList<String> getDelieveryOptions() {
		return delieveryOptions;
	}

	public ArrayList<String> getGeneralEducationOptions() {
		return generalEducationOptions;
	}
	
	
}

