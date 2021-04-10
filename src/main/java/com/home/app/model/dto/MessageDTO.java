package com.home.app.model.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.home.app.model.types.Channels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

	private Long id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	
	@NotNull(message = "Author is mandatory")
	private String author;
	
	@Column
	@NotNull(message = "Content is mandatory")
	private String content;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Channel is mandatory")
	private Channels channel;
	
	private Long clientCase;
}
