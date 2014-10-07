package com.yyc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yyc.model.Photo;
import com.yyc.model.Type;

public class PhotoDownloader extends Downloader {
	int pageSize = 100;
	int pageNumberMax = 5;
	String albumId = null;

	public PhotoDownloader(String albumId){
		this.albumId = albumId;
	}
	
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

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	
	@Override
	public List<Type> parse(List<Type> results, String result)
			throws JSONException {
		JSONObject root = new JSONObject(result);
		// System.out.println(root.toString());
		if (root.has("response")) {
			JSONArray objects = root.getJSONArray("response");
			for (int i = 0; i < objects.length(); i++) {
				JSONObject obj = (JSONObject) objects.getJSONObject(i);
				Photo p = new Photo();
				p.setId(obj.getString("id"));
				p.setDescription(obj.getString("description"));
				JSONArray images = obj.getJSONArray("images");
				p.setUrl(images.getJSONObject(0).getString("url"));
				p.setCreateTime(obj.getString("createTime"));
				p.setAlbumId(obj.getString("albumId"));
				p.setViewCount(obj.getInt("viewCount"));
				p.setCommentCount(obj.getInt("commentCount"));
				p.setOwnerId(obj.getString("ownerId"));
				results.add(p);
			}
		}
		return results;
	}

	@Override
	public String getURL(String token, String userId, int pageNumber) {
		return "https://api.renren.com/v2/photo/list?access_token=" + token
				+ "&ownerId=" + userId + "&pageSize=" + pageSize
				+ "&pageNumber=" + pageNumber + "&albumId=" + albumId;
	}
}
