package com.example.demo.dto;

public class OdekakeDto {

	private int id;
	private String title;
	private String body;
	private String url1;
	private String url2;
	private String visited;
	private String season;
	private String remarks;
	private String createdBy;
	private String createdAt;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getVisited() {
		return visited;
	}

	public void setVisited(String visited) {
		this.visited = visited;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}