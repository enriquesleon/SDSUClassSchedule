package com.enrique.leon.Search;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.enrique.leon.ClassModel.QueryPair;
import com.enrique.leon.Parsing.ParseMainPage;
import com.enrique.leon.RequestService.JsoupRequest;

import com.enrique.leon.RequestService.RequestContent;
import com.enrique.leon.RequestService.ScheduleRequestException;
public class SdsuSearch {
	public static SdsuSearch instance;
	private List<QueryPair> currentQuery;
	private Map<String,String> getRequestParams;
	
	private ArrayList<String> periodsOptions;
	private ArrayList<String> courseSubjectsOptions;
	private ArrayList<String> courseSuffixOptions;
	private ArrayList<String> roomOptions;
	private ArrayList<String> delieveryOptions;
	private ArrayList<String> generalEducationOptions;
	private boolean queryOptionsLoaded = false;
	private String host;
	private String path;
	private SdsuSearch(String host) throws ScheduleRequestException{
		this.host = host;
		currentQuery = new  ArrayList<QueryPair>();
		getRequestParams = new Hashtable<String,String>();
		this.loadGetRequestParams();
	}
	public void addToCurrentQuery(String key,String value){
		currentQuery.add(new QueryPair(key,value));
	}
	public void removeFromCurrentQuery(String key){
		currentQuery.remove(key);
	}
	public static SdsuSearch getInstance(String host) throws ScheduleRequestException{
		if(instance == null){
			return new SdsuSearch(host);
		}
		return instance;
	}
	private void loadGetRequestParams() throws ScheduleRequestException{
		if(!this.queryOptionsLoaded){
			RequestContent mainSearchRequest = new JsoupRequest(this.host).getRequestContent();
			ParseMainPage queries = new ParseMainPage(mainSearchRequest);
			periodsOptions = queries.getPeriodsOptions();
			courseSubjectsOptions = queries.getCourseSubjectsOptions();
			courseSuffixOptions = queries.getCourseSuffixOptions();
			roomOptions = queries.getRoomOptions();
			delieveryOptions = queries.getDelieveryOptions();
			generalEducationOptions = queries.getGeneralEducationOptions();
			getRequestParams = queries.getAvailableOptions();
			this.queryOptionsLoaded = true;
		}
	}

	public String getHost(){
		return host;
	}
	public String getPath(){
		return path;
	}
	public List<QueryPair> getCurrentQueryAsList() {
		return currentQuery;
	}
	public void clearCurrentQuery(){
		this.currentQuery.clear();
	}
	public Map<String,String> getLoadedOptions() {
		return getRequestParams;
	}
	public ArrayList<String> getPeriodsOptions() {
		return periodsOptions;
	}
	public ArrayList<String> getCourseSubjectOptions() {
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
	public boolean isQueryOptionsLoaded() {
		return queryOptionsLoaded;
	}
	

}
