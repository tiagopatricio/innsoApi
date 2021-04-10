package com.home.app.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.home.app.model.dto.MessageDTO;
import com.home.app.model.entities.ClientCase;
import com.home.app.model.entities.Message;
import com.home.app.model.repo.ClientCaseRepo;
import com.home.app.model.repo.MessageRepo;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepo messageRepo;
	
	@Autowired
	private ClientCaseRepo clientRepo;

	public MessageDTO createMessage( MessageDTO message ) {
		if (message.getCreationDate() == null) {
			message.setCreationDate(LocalDateTime.now());
		}
		ClientCase client = null;
		if (message.getClientCase() != null) {
			client = clientRepo.findById(message.getClientCase()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client case not found"));
		}
		Message msg = new Message(message.getId(), message.getCreationDate(), message.getAuthor(), message.getContent(), message.getChannel(), client);
		messageRepo.save(msg);
		message.setId(msg.getId());
		return message;
	}
	
	public MessageDTO getMessageById( Long id ) {
		Message msg = messageRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found"));
		return new MessageDTO(id, msg.getCreationDate(), msg.getAuthor(), msg.getContent(), msg.getChannel(), msg.getClientCase() != null ? msg.getClientCase().getId() : null);
	}
	
	public MessageDTO updateMessage( MessageDTO message ) {
		return messageRepo.findById(message.getId()).map( msg -> {
			msg.setAuthor(message.getAuthor());
			msg.setContent(message.getContent());
			msg.setChannel(message.getChannel());
			msg.setCreationDate(message.getCreationDate());
			ClientCase client = null;
			if (message.getClientCase() != null) {
				client = clientRepo.findById(message.getClientCase()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client case not found"));
			}
			msg.setClientCase(client);
			messageRepo.save(msg);
			return message;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found"));
	}
	
	public void deleteMessage( Long id) {
		messageRepo.findById(id).map( msg -> {messageRepo.delete(msg);return true;}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found"));
	}
	
	public List<MessageDTO> listAll() {
		List<Message> list = messageRepo.findAll();
		List<MessageDTO> finalList = new ArrayList<MessageDTO>();
		if (!list.isEmpty()) {
			for (Message msg :list) {
				finalList.add(new MessageDTO(msg.getId(), msg.getCreationDate(), msg.getAuthor(), msg.getContent(), msg.getChannel(), msg.getClientCase() != null ? msg.getClientCase().getId() : null));
			}
		}
		return finalList;
		
	}
}
