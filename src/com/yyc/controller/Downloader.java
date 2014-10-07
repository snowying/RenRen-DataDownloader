package com.yyc.controller;

import java.util.List;

import org.json.JSONException;

import com.yyc.model.Type;

public abstract class Downloader {
	int pageSize = 20;
	int pageNumberMax = 20;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumberMax() {
		return pageNumberMax;
	}

	public void setPageNumberMax(int pageNumberMax) {
		this.pageNumberMax = pageNumberMax;
	}

	public abstract List<Type> parse(List<Type> results, String result)
			throws JSONException;

	public abstract String getURL(String token, String userId, int pageNumber);
}
