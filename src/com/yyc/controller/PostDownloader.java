package com.yyc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yyc.model.Post;
import com.yyc.model.Type;

public class PostDownloader extends Downloader {
	@Override
	public List<Type> parse(List<Type> results, String result)
			throws JSONException {
		JSONObject root = new JSONObject(result);
		// System.out.println(root.toString());
		if (root.has("response")) {
			JSONArray objects = root.getJSONArray("response");
			for (int i = 0; i < objects.length(); i++) {
				JSONObject obj = (JSONObject) objects.getJSONObject(i);
				Post p = new Post();
				p.setId(obj.getString("id"));
				p.setType(obj.getString("type"));
				p.setContent(obj.getString("content"));
				p.setCreateTime(obj.getString("createTime"));
				p.setShareCount(obj.getInt("shareCount"));
				p.setAccessControl(obj.getString("accessControl"));
				p.setViewCount(obj.getInt("viewCount"));
				p.setCommentCount(obj.getInt("commentCount"));
				results.add(p);
			}
		}
		return results;
	}

	@Override
	public String getURL(String token, String userId, int pageNumber) {
		return "https://api.renren.com/v2/blog/list?access_token=" + token
				+ "&ownerId=" + userId + "&pageSize=" + pageSize
				+ "&pageNumber=" + pageNumber;
	}
}
