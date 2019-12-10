package com.metrica.formacion.bck.api.rest.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.metrica.formacion.bck.api.rest.model.entity.Cliente;
import com.metrica.formacion.bck.api.rest.model.entity.Region;

public interface IClienteService {

	/**
	 * Devolver todos los clientes
	 *
	 * @return Lista de clietes
	 */
	public List<Cliente> findAll();

	/**
	 * Devolver clientes paginados
	 *
	 * @return Lista de clietes paginados
	 */
	public Page<Cliente> findAll(Pageable pageable);

	/**
	 * Busqueda de cliente por id
	 *
	 * @param id
	 * @return cliente buscado
	 */
	public Cliente findById(Long id);

	/**
	 * Guarda el cliente pasado por parámetro
	 *
	 * @param cliente
	 * @return cliente guardado
	 */
	public Cliente save(Cliente cliente);

	/**
	 * Borra cliente por id
	 *
	 * @param cliente
	 */
	public void delete(Long id);

	public List<Region> findAllRegiones();

}
