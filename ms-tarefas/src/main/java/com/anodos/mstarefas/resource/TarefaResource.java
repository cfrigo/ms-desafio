package com.anodos.mstarefas.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anodos.mstarefas.entities.Tarefa;
import com.anodos.mstarefas.entities.dto.TarefaDTO;
import com.anodos.mstarefas.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaResource {

	@Autowired
	private TarefaService tarefaService;

	@PostMapping
	public ResponseEntity<Tarefa> createTarefa(@RequestBody TarefaDTO Tarefa) {
		Tarefa savedTarefa = tarefaService.saveByTarefa(Tarefa);
		return ResponseEntity.status(201).body(savedTarefa);
	}

	@GetMapping
	public ResponseEntity<?> listTarefas(@RequestParam(required = false) String status,
			@RequestParam(required = false) Long userId) {

		if (status != null && userId != null) {
			return ResponseEntity.status(200).body(tarefaService.findByStatusAndUserId(status, userId));
		} else if (userId != null) {
			return ResponseEntity.status(200).body(tarefaService.findByUserId(userId));
		} else if (status != null) {
			return ResponseEntity.status(200).body(tarefaService.findByStatus(status));
		}else {
			return ResponseEntity.status(400).body("Verifique os parametros de entrada!");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDetails) {
			
		 Optional<Tarefa> optionalTask = tarefaService.findById(id);
	        return optionalTask.map(tarefa -> {
	        	
	        	 if (tarefaDetails.getDescription() != null) {
	        		 tarefa.setDescription(tarefaDetails.getDescription());
	             }
	             if (tarefaDetails.getStatus() != null) {
	            	 tarefa.setStatus(tarefaDetails.getStatus());
	             }
	        	
	            Tarefa updatedTask = tarefaService.save(tarefa);
	            return ResponseEntity.ok(updatedTask);
	        }).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
		if (tarefaService.existsById(id)) {
			tarefaService.deleteById(id);				
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
