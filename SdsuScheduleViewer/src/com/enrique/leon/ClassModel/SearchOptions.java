package com.enrique.leon.ClassModel;
import java.util.Iterator;
import java.util.List;

import com.enrique.leon.Search.SdsuSearch;
public class SearchOptions implements Iterable<QueryPair>{
	private final List<QueryPair> queryParameters;
	private SearchOptions(SearchOptionsBuilder builder){
		this.queryParameters = builder.queryParameters;
	}
	public static class SearchOptionsBuilder{
		private SdsuSearch instance;
		private List<QueryPair> queryParameters;
		public SearchOptionsBuilder(SdsuSearch instance){
			this.instance = instance;
			instance.clearCurrentQuery();
			instance.addToCurrentQuery("mode", "search");
		}
		public SearchOptionsBuilder period(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("period");
			else
				instance.addToCurrentQuery("period", value);
			return this;
		}
		public SearchOptionsBuilder campus(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("admin_unit");
			else
				instance.addToCurrentQuery("admin_unit", value);
			return this;
		}
		public SearchOptionsBuilder courseSubject(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("abbrev");
			else
				instance.addToCurrentQuery("abbrev", value);
			return this;
		}
		public SearchOptionsBuilder courseNumber(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("number");
			else
				instance.addToCurrentQuery("number", value);
			return this;
		}
		public SearchOptionsBuilder suffix(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("suffix");
			else
				instance.addToCurrentQuery("suffix", value);
			return this;
		}
		public SearchOptionsBuilder courseTitle(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("courseTitle");
			else
				instance.addToCurrentQuery("courseTitle", value);
			return this;
		}
		public SearchOptionsBuilder scheduleNumber(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("scheduleNumber");
			else
				instance.addToCurrentQuery("scheduleNumber", value);
			return this;
		}
		public SearchOptionsBuilder units(String value){
			if (value.equals("")) instance.removeFromCurrentQuery("units");
			else
				instance.addToCurrentQuery("units", value);
			return this;
		}
		public SearchOptionsBuilder serviceLearning(boolean status){
			if (status) instance.addToCurrentQuery("serviceLearning","on");
			else instance.removeFromCurrentQuery("serviceLearning");
			return this;
		}
		public SearchOptionsBuilder specialTopics(boolean status){
			if (status) instance.addToCurrentQuery("specialTopics","on");
			else instance.removeFromCurrentQuery("specialTopics");
			return this;
		}
		public SearchOptionsBuilder honors(boolean status){
			if (status) instance.addToCurrentQuery("honors","on");
			else instance.removeFromCurrentQuery("honors");
			return this;
		}
		public SearchOptionsBuilder online(boolean status){
			if (status) instance.addToCurrentQuery("online","on");
			else instance.removeFromCurrentQuery("online");
			return this;
		}
		public SearchOptionsBuilder hybrid(boolean status){
			if (status) instance.addToCurrentQuery("hybrid","on");
			else instance.removeFromCurrentQuery("hybrid");
			return this;
		}
		public SearchOptionsBuilder evening(boolean status){
			if (status) instance.addToCurrentQuery("evening","on");
			else instance.removeFromCurrentQuery("evening");
			return this;
		}
		public SearchOptionsBuilder addQuery(String key, String value){
			instance.addToCurrentQuery(key, value);
			return this;
		}
		public SearchOptionsBuilder instructor(String value){
			if(value==""){
				instance.removeFromCurrentQuery("instructor");
			}
				instance.addToCurrentQuery("instructor", value);
			return this;
		}
		public SearchOptionsBuilder Room(String value){
			instance.addToCurrentQuery("facility", value);
			return this;
		}
		public SearchOptionsBuilder roomNumber(String value){
			instance.addToCurrentQuery("suffix", value);
			return this;
		}
		public SearchOptionsBuilder meetingType(String value){
			instance.addToCurrentQuery("meetingtype", value);
			return this;
		}
		public SearchOptionsBuilder monday(boolean status){
			if (status) instance.addToCurrentQuery("monday","on");
			else instance.removeFromCurrentQuery("monday");
			return this;
		}
		public SearchOptionsBuilder tuesday(boolean status){
			if (status) instance.addToCurrentQuery("tuesday","on");
			else instance.removeFromCurrentQuery("tuesday");
			return this;
		}
		public SearchOptionsBuilder wednesday(boolean status){
			if (status) instance.addToCurrentQuery("wednesday","on");
			else instance.removeFromCurrentQuery("wednesday");
			return this;
		}
		public SearchOptionsBuilder thursday(boolean status){
			if (status) instance.addToCurrentQuery("thursday","on");
			else instance.removeFromCurrentQuery("thursday");
			return this;
		}
		public SearchOptionsBuilder friday(boolean status){
			if (status) instance.addToCurrentQuery("friday","on");
			else instance.removeFromCurrentQuery("friday");
			return this;
		}
		public SearchOptionsBuilder saturday(boolean status){
			if (status) instance.addToCurrentQuery("saturday","on");
			else instance.removeFromCurrentQuery("saturday");
			return this;
		}
		public SearchOptionsBuilder startHours(String value){
			instance.addToCurrentQuery("startHours", value);
			return this;
		}
		public SearchOptionsBuilder startMins(String value){
			instance.addToCurrentQuery("startMins", value);
			return this;
		}
		public SearchOptionsBuilder endHours(String value){
			instance.addToCurrentQuery("endHours", value);
			return this;
		}
		public SearchOptionsBuilder endMins(String value){
			instance.addToCurrentQuery("endMins", value);
			return this;
		}
		public SearchOptionsBuilder remainingSeatsAtLeast(String value){
			instance.addToCurrentQuery("remaing_seats_at_least", value);
			return this;
		}
		public SearchOptionsBuilder genEd(String value){
			this.instance.addToCurrentQuery("ge", value);
			return this;
		}
		public SearchOptions build(){
			queryParameters = instance.getCurrentQueryAsList();
			return new SearchOptions(this);
		}

	}
	@Override
	public Iterator<QueryPair> iterator() {
		return SdsuSearch.instance.getCurrentQueryAsList().iterator();
	}

}
