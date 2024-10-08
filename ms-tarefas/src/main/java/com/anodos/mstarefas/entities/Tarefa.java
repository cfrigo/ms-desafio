package com.anodos.mstarefas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id; 	

	private String title;

	private String description;
	
	private String status;
	
	private Long userId;
	
	public Tarefa() {}

	public Tarefa(Long id, String title, String description, String status, Long userId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
