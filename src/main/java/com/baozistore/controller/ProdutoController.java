package com.baozistore.controller;

import com.baozistore.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.baozistore.model.Produto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private final ProdutoRepository repository;

	public ProdutoController(ProdutoRepository repository) {
	    this.repository = repository;
	}
	
	@GetMapping
	public List<Produto> listarTodos() {
	    return repository.findAll();
	}
	
	@PostMapping
	public Produto criar(@RequestBody Produto produto) {
	    return repository.save(produto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
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
