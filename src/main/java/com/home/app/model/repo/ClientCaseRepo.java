package com.home.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.app.model.entities.ClientCase;

@Repository
public interface ClientCaseRepo extends JpaRepository<ClientCase, Long> {

}
