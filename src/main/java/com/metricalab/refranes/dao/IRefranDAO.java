package com.metricalab.refranes.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.metricalab.refranes.entity.Refran;

public interface IRefranDAO extends JpaRepository<Refran, Long> {

	Optional<Refran> findFirstByOrderByCalidadDesc();

	@Query(value = "SELECT * FROM refranes ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Optional<Refran> findRandom();

	Optional<List<Refran>> findTop5ByOrderByCalidad();

	Optional<List<Refran>> findTop10ByOrderByCalidad();

	Optional<List<Refran>> findAllByOrderByCalidadDesc();

	Optional<List<Refran>> findAllByOrderByCalidadAsc();

	Optional<List<Refran>> findByUsuario(String user);

	Optional<List<Refran>> findByTextoContainingIgnoreCase(String texto);

	Optional<List<Refran>> findByUsuarioContainingIgnoreCase(String usuario);

	Optional<List<Refran>> findByUsuarioContainingIgnoreCaseOrderByUsuarioDesc(String usuario);

	Optional<List<Refran>> findByUsuarioContainingIgnoreCaseOrderByUsuarioAsc(String usuario);

	@Query(value = "SELECT avg(calidad) FROM refranes", nativeQuery = true)
	public Double avg();
}
