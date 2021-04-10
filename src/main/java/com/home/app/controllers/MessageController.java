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

import com.home.app.model.dto.MessageDTO;
import com.home.app.services.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageDTO createMessage( @RequestBody @Valid  MessageDTO message) {
		return messageService.createMessage(message);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public MessageDTO getMessageById( @PathVariable Long id) {
		return messageService.getMessageById(id);
	}
	
	@PutMapping
	public MessageDTO updateMessage( @RequestBody @Valid MessageDTO message ) {
		return messageService.updateMessage(message);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMessage( @PathVariable Long id) {
		messageService.deleteMessage(id);
	}
	
	@GetMapping
	public List<MessageDTO> listAll() {
		return messageService.listAll();
	}

}
