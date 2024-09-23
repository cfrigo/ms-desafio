package com.anodos.mstarefas.entities.dto;

public class TarefaDTO {
	
	private String title;

	private String description;
	
	private String status;
	
	private Long userId;
	
	public TarefaDTO() {}
	
	public TarefaDTO(String title, String description, String status, Long userId) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
