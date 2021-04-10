package com.home.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.home.app.model.dto.ClientCaseDTO;
import com.home.app.services.ClientCaseService;

@RestController
@RequestMapping("/api/clients")
public class ClientCaseController {
	
	@Autowired
	private ClientCaseService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientCaseDTO createClientCase( @RequestBody @Valid ClientCaseDTO client) {
		return service.createClientCase(client);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ClientCaseDTO getClientCaseById( @PathVariable Long id) {
		return service.getClientCaseById(id);
	}
	
	@PutMapping
	public ClientCaseDTO updateClientCase( @RequestBody @Valid ClientCaseDTO client ) {
		return service.updateClientCase(client);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClientCase( @PathVariable Long id) {
		service.deleteClientCase(id);
	}
	
	@GetMapping
	public List<ClientCaseDTO> listAll() {
		return service.listAll();
	}
}
