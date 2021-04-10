package com.home.app.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCaseDTO {
	
	private Long id;
	
	@NotNull(message = "Author is mandatory")
	private String name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	
	private String reference;
	
	private Set<Long> messages;

}
