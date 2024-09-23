package com.anodos.mstarefas.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "ms-usuarios", url ="localhost:8001" , path="/users")
public interface UsuariosFeignClient {
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Boolean> existsById(@PathVariable Long id);
}
