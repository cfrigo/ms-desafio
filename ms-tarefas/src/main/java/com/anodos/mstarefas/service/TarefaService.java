package com.anodos.mstarefas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anodos.mstarefas.entities.Tarefa;
import com.anodos.mstarefas.entities.dto.TarefaDTO;
import com.anodos.mstarefas.repository.TarefaRepository;

@Service
public class TarefaService {

	private static Logger logger = LoggerFactory.getLogger(TarefaService.class);

	@Autowired
	private TarefaRepository tarefaRepository;

	public Tarefa saveByTarefa(TarefaDTO dto) {

		Tarefa t = new Tarefa();
		try {
			t.setDescription(dto.getDescription());
			t.setStatus(dto.getStatus());
			t.setTitle(dto.getTitle());
			t.setUserId(dto.getUserId());			
			
			return tarefaRepository.save(t);
		} catch (Exception e) {
			logger.error("Erro ao efetuar o cadastro: " + e.getMessage());
			return new Tarefa();
		}
	}

	public Tarefa save(Tarefa tarefa) {
		try {
			return tarefaRepository.save(tarefa);
		} catch (Exception e) {
			logger.error("Erro ao efetuar o cadastro: " + e.getMessage());
			return new Tarefa();
		}
	}

	public Optional<Tarefa> findById(Long id) {
		return tarefaRepository.findById(id);
	}

	public List<Tarefa> findByStatusAndUserId(String status, Long userId) {
		List<Tarefa> lst = new ArrayList<>();
		try {
			lst = tarefaRepository.findByStatusAndUserId(status, userId);
		} catch (Exception e) {
			logger.error("Erro ao consultar tarefa: " + e.getMessage());
			return lst;
		}

		return lst;
	}

	public List<Tarefa> findByUserId(Long userId) {
		List<Tarefa> lst = new ArrayList<>();
		try {
			lst = tarefaRepository.findByUserId(userId);
		} catch (Exception e) {
			logger.error("Erro ao consultar tarefa: " + e.getMessage());
			return lst;
		}
		return lst;
	}

	public List<Tarefa> findByStatus(String status) {
		List<Tarefa> lst = new ArrayList<>();
		try {
			lst = tarefaRepository.findByStatus(status);
		} catch (Exception e) {
			logger.error("Erro ao consultar tarefa: " + e.getMessage());
			return lst;
		}
		return lst;
	}

	public Boolean existsById(Long id) {

		try {
			return tarefaRepository.existsById(id);
		} catch (Exception e) {
			logger.error("Erro ao consultar tarefa: " + e.getMessage());
			return false;
		}
	}
	
	public void deleteById(Long id) {

		try {
			tarefaRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Erro ao deletar tarefa: " + e.getMessage());			
		}
	}

}
