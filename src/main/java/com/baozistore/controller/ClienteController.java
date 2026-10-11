package com.baozistore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baozistore.repository.ClienteRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.baozistore.model.Cliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteRepository repository;
	 public ClienteController(ClienteRepository repository) {
	        this.repository = repository;
	    }
	 
	 @GetMapping
	 public List<Cliente> listarTodos() {
	     return repository.findAll();
	 }
	 
	 @PostMapping
	 public Cliente criar(@RequestBody Cliente cliente) {
	     return repository.save(cliente);
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
	     return repository.findById(id)
	             .map(ResponseEntity::ok)
	             .orElse(ResponseEntity.notFound().build());
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> excluir(@PathVariable Long id) {
	     if (!repository.existsById(id)) {
	         return ResponseEntity.notFound().build();
	     }

	     repository.deleteById(id);
	     return ResponseEntity.noContent().build();
	 }

}

