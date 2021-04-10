package com.home.app.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.app.model.types.Channels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	
	@Column
	@NotNull(message = "Author is mandatory")
	private String author;
	
	@Column
	@NotNull(message = "Content is mandatory")
	private String content;
	
	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Channel is mandatory")
	private Channels channel;
	
	@JsonIgnore
	@ManyToOne
	private ClientCase clientCase;

	@Override
	public String toString() {
		return "Message [id=" + id + ", creationDate=" + creationDate + ", author=" + author + ", content=" + content
				+ ", channel=" + channel + "]";
	}

}
