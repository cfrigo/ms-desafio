package com.anodos.mstarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anodos.mstarefas.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	 List<Tarefa> findByStatusAndUserId(String status, Long userId);
	 
	 List<Tarefa> findByUserId(Long userId);
	 
	 List<Tarefa> findByStatus(String status);
}
