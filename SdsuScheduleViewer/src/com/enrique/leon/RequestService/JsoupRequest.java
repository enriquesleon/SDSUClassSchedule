package com.enrique.leon.RequestService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.Document;

import com.enrique.leon.ClassModel.QueryPair;

public class JsoupRequest extends Request{
	private Connection connection;
	public JsoupRequest(String host) throws ScheduleRequestException{
		this(host,new ArrayList<QueryPair>());
	}
	public JsoupRequest(String host,List<QueryPair> params) throws ScheduleRequestException {
		super(host,params);
	}

	@Override
	protected Request buildRequest() throws ScheduleRequestException {
		String connectionUrl = host;
		if(path!=null||!path.equals("")){
			connectionUrl = host+path;
		}
		connection = Jsoup.connect(connectionUrl);

		if(this.params.size()!=0){
			for(QueryPair getQuery:params){
			this.connection.data(getQuery.getKey(), getQuery.getValue());
			}
		}
		return this;
	}

	
	@Override
	public RequestContent getRequestContent() throws ScheduleRequestException {
		try {
			Document doc = connection.get();
			String body = doc.body().outerHtml();
			String code = String.valueOf(connection.response().statusCode());
			String url  = doc.location();
			return new JsoupRequestContent(body, code, url, doc);
		} catch(MalformedURLException e){
			throw new ScheduleRequestException("Malformed Url",e);
		}catch (UnsupportedMimeTypeException e){
			throw new ScheduleRequestException("Mime Type Error",e);
		}catch (SocketTimeoutException e){
			//TODO
			throw new ScheduleRequestException("Socket Timeout",e);
		}catch (IOException e){
			throw new ScheduleRequestException("Fatal Connection Error",e);
		}

	}

}
