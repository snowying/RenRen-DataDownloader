package com.yyc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yyc.model.Album;
import com.yyc.model.Type;

public class AlbumDownloader extends Downloader {
	int pageSize = 100;
	int pageNumberMax = 5;

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

	@Override
	public List<Type> parse(List<Type> results, String result)
			throws JSONException {
		JSONObject root = new JSONObject(result);
		// System.out.println(root.toString());
		if (root.has("response")) {
			JSONArray objects = root.getJSONArray("response");
			for (int i = 0; i < objects.length(); i++) {
				JSONObject obj = (JSONObject) objects.getJSONObject(i);
				Album p = new Album();
				p.setName(obj.getString("name"));
				p.setLocation(obj.getString("location"));
				p.setId(obj.getString("id"));
				p.setType(obj.getString("type"));
				p.setDescription(obj.getString("description"));
				p.setCreateTime(obj.getString("createTime"));
				p.setPhotoCount(obj.getInt("photoCount"));
				p.setAccessControl(obj.getString("accessControl"));
				JSONArray cover = obj.getJSONArray("cover");
				p.setCover(cover.getJSONObject(0).getString("url"));
				p.setLastModifyTime(obj.getString("lastModifyTime"));
				results.add(p);
			}
		}
		return results;
	}

	@Override
	public String getURL(String token, String userId, int pageNumber) {
		return "https://api.renren.com/v2/album/list?access_token=" + token
				+ "&ownerId=" + userId + "&pageSize=" + pageSize
				+ "&pageNumber=" + pageNumber;
	}

}
