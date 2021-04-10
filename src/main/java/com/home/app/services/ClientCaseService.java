package com.home.app.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.home.app.model.dto.ClientCaseDTO;
import com.home.app.model.entities.ClientCase;
import com.home.app.model.entities.Message;
import com.home.app.model.repo.ClientCaseRepo;
import com.home.app.model.repo.MessageRepo;

@Service
public class ClientCaseService {
	
	@Autowired
	private ClientCaseRepo clientRepo;
	
	@Autowired
	private MessageRepo messageRepo;
	
	public ClientCaseDTO createClientCase( ClientCaseDTO client) {
		if (client.getCreationDate() == null) {
			client.setCreationDate(LocalDateTime.now());
		}
		Set<Message> msgList = new HashSet<Message>();
		if (client.getMessages() != null && !client.getMessages().isEmpty()) {
			client.getMessages().forEach(id -> {
				msgList.add(messageRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Messages not found")));
			});
		}
		
		ClientCase cli = clientRepo.save(new ClientCase(client.getId(), client.getName(), client.getCreationDate(), client.getReference(), msgList));
		client.setId(cli.getId());
		return client;
	}
	
	public ClientCaseDTO getClientCaseById( Long id) {
		
		ClientCase client = clientRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client case not found"));
		Set<Long> messages = new HashSet<Long>();
		if (client.getMessages() != null && !client.getMessages().isEmpty()) {
			client.getMessages().forEach(msg -> {messages.add(msg.getId());});
		}
		return new ClientCaseDTO(id, client.getName(), client.getCreationDate(), client.getReference(), messages);
	}
	
	public ClientCaseDTO updateClientCase( ClientCaseDTO client ) {
		
		 clientRepo.findById(client.getId()).map( cc -> {
			 cc.setCreationDate(client.getCreationDate());
			 cc.setName(client.getName());
			 cc.setReference(client.getReference());
			 if (client.getMessages() != null && !client.getMessages().isEmpty()) {
				 client.getMessages().forEach(id -> {
					 messageRepo.findById(id).map(msg -> {
						 msg.setClientCase(cc);
						 return true;
					 })
					 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
				 });
			 }
			 clientRepo.save(cc);
			return true;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client case not found"));
		 return client;
	}
	
	public void deleteClientCase( Long id) {
		clientRepo.findById(id).map( cc -> {clientRepo.delete(cc);return true;}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client case not found"));
	}
	
	public List<ClientCaseDTO> listAll() {
		List<ClientCase> list = clientRepo.findAll();
		List<ClientCaseDTO> finalList = new ArrayList<ClientCaseDTO>();
		if (!list.isEmpty()) {
			list.forEach(client -> {
				Set<Long> messages = new HashSet<Long>();
				if (client.getMessages() != null && !client.getMessages().isEmpty()) {
					client.getMessages().forEach(msg -> {messages.add(msg.getId());});
				}
				finalList.add(new ClientCaseDTO(client.getId(), client.getName(), client.getCreationDate(), client.getReference(), messages));
				});
		}
		return finalList;
	}

}
