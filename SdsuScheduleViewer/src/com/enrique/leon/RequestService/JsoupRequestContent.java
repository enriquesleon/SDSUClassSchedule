package com.enrique.leon.RequestService;

import org.jsoup.nodes.Document;

public class JsoupRequestContent extends RequestContent {
	private Document document;
	public JsoupRequestContent(String htmlBody, String requestUrl, String responseCode,Document doc) {
		super(htmlBody, requestUrl, responseCode);
		this.document = doc;
	}
	public Document getRequestDocument(){
		return this.document;
	}

}
