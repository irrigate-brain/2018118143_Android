package com.zrp.latte.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.zrp.latte.delegates.web.WebDelegate;

public abstract class Event implements IEvent {

	private Context mContent = null;
	private String mAction = null;
	private WebDelegate mWebDelegate = null;
	private String mUrl = null;

	public Context getContent() {
		return mContent;
	}

	public void setContent(Context mContent) {
		this.mContent = mContent;
	}

	public String getAction() {
		return mAction;
	}

	public void setAction(String mAction) {
		this.mAction = mAction;
	}

	public WebDelegate getWebDelegate() {
		return mWebDelegate;
	}

	public void setWebDelegate(WebDelegate mWebDelegate) {
		this.mWebDelegate = mWebDelegate;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public WebView getWebView() {
		return mWebDelegate.getWebView();
	}

	public void setWebView(WebView mWebView) {
		WebView mWebView1 = mWebView;
	}
}
