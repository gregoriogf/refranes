package com.metrica.formacion.bck.api.rest.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metrica.formacion.bck.api.rest.model.entity.Cliente;
import com.metrica.formacion.bck.api.rest.model.entity.Region;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClientesDAO clienteDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(final Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(final Cliente cliente) {
		return clienteDAO.save(cliente);
	}

	@Override
	@Transactional
	public void delete(final Long id) {
		clienteDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(final Pageable pageable) {
		return clienteDAO.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDAO.findAllRegiones();
	}

}
