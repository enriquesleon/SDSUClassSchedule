package com.enrique.leon.Parsing;
import java.util.List;

import com.enrique.leon.ClassModel.ClassSection;
import com.enrique.leon.ClassModel.QueryPair;
import com.enrique.leon.ClassModel.SearchOptions;
import com.enrique.leon.ClassModel.SearchOptions.SearchOptionsBuilder;
import com.enrique.leon.Parsing.*;
import com.enrique.leon.RequestService.*;
import com.enrique.leon.Search.SdsuSearch;
public class SearchResultsTest {

	public static void main(String[] args) throws ScheduleRequestException {
		
		SdsuSearch instance = SdsuSearch.getInstance("https://sunspot.sdsu.edu/schedule/search");
		SearchOptionsBuilder builder = new SearchOptions.SearchOptionsBuilder(instance);
		SearchOptions options = builder.campus("R").courseSubject("CS").evening(true).build();
		List<QueryPair> query = instance.getCurrentQueryAsList();
		RequestContent contentOptions = new JsoupRequest(instance.getHost(), query).getRequestContent();

	}

}
