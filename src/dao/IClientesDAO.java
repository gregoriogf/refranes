package com.metrica.formacion.bck.api.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.metrica.formacion.bck.api.rest.model.entity.Cliente;
import com.metrica.formacion.bck.api.rest.model.entity.Region;

public interface IClientesDAO extends JpaRepository<Cliente, Long> {

	// Biblio: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

	@Query("from Region")
	public List<Region> findAllRegiones();
}
