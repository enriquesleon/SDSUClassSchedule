package com.enrique.leon.RequestService;

import java.util.List;
import com.enrique.leon.RequestService.RequestContent;
import com.enrique.leon.ClassModel.QueryPair;
import com.enrique.leon.RequestService.ScheduleRequestException;
public abstract class Request{
	protected List<QueryPair> params;
	protected String host;
	protected String path;
	protected boolean hasContent;
	public Request(String host,List<QueryPair> params) throws ScheduleRequestException {
		super();
		this.params = params;
		this.host = host;
		try {
			this.buildRequest();
			this.hasContent = true;
		} catch (ScheduleRequestException e) {
			this.hasContent = false;
			throw new ScheduleRequestException(e);
		}
	}

	public List<QueryPair> getParams() {return params;}
	public String getHost()                 {return host;}
	public String getPath()           		{return path;}
	protected abstract Request buildRequest() throws ScheduleRequestException;
	abstract RequestContent getRequestContent() throws ScheduleRequestException;
}
