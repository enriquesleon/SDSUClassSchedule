package com.enrique.leon.RequestService;

public class RequestContent {
	private String htmlBody;
	private String requestUrl;
	private String responseCode;
	private boolean hasContent = true;
	
	public RequestContent(String htmlBody, String requestUrl, String responseCode) {
		super();
		this.htmlBody = htmlBody;
		this.requestUrl = requestUrl;
		this.responseCode = responseCode;
		if(this.htmlBody==null||this.htmlBody.equals("")){
			this.hasContent = false;
		}
	}
	public String getHtmlBody() {
		return htmlBody;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public boolean isHasContent() {
		return hasContent;
	}
	
}
