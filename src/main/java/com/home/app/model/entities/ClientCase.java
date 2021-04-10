package com.home.app.model.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull(message = "Author is mandatory")
	private String name;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	
	@Column
	private String reference;
	
	@OneToMany(
			mappedBy = "clientCase",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<Message> messages = new HashSet<Message>();

	@Override
	public String toString() {
		return "ClientCase [id=" + id + ", name=" + name + ", creationDate=" + creationDate + ", reference=" + reference
				 + "]";
	}
	
	

}
