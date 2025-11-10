package com.example.demo.dto;

public class OdekakeSummaryDto {

	private String id;
	private String title;
	private String visited;
	private String url1;
	private String createdBy;
	private String category;

	// コンストラクタ（空のものと全フィールド付き）
	public OdekakeSummaryDto() {
	}

	public OdekakeSummaryDto(String id, String title, String visited, String url1, String createdBy) {
		this.id = id;
		this.title = title;
		this.visited = visited;
		this.url1 = url1;
		this.createdBy = createdBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVisited() {
		return visited;
	}

	public void setVisited(String visited) {
		this.visited = visited;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}