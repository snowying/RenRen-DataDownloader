package com.yyc.model;

public class Post extends Type {
	String id;
	String type;
	String content;
	String createTime;
	int shareCount;
	String title;
	String accessControl;
	int viewCount;
	int commentCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String string) {
		this.createTime = string;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAccessControl() {
		return accessControl;
	}

	public void setAccessControl(String accessControl) {
		this.accessControl = accessControl;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String toString() {
		return "{\"id\":" + getId() + ",\"type\":\"" + getType()
				+ "\",\"content\":\"" + getContent() + "\",\"createTime\":\""
				+ getCreateTime() + "\",\"shareCount\":" + getShareCount()
				+ ",\"title\":\"" + getTitle() + "\",\"accessControl\":\""
				+ getAccessControl() + "\",\"viewCount\":" + getViewCount()
				+ ",\"commentCount\":" + getCommentCount() + "}";
	}
}
