package com.metricalab.refranes.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalab.refranes.entity.Refran;
import com.metricalab.refranes.exception.DataBaseException;
import com.metricalab.refranes.utils.ConstantsData;

@Service
public class RefranDAOService implements IRefranDAOService {

	@Autowired
	private IRefranDAO repository;

	@Override
	@Transactional(readOnly = true)
	public Optional<Refran> getBestRefran() {
		return repository.findFirstByOrderByCalidadDesc();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Refran> getRandomRefran() {
		return repository.findRandom();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<Refran>> getRefranes(int numRefranes, String order) {
		// Es un ejemplo de diferentes llamadas al repositorio
		// dependiendo del numero de refranes a buscar
		Optional<List<Refran>> refranes = null;
		if (numRefranes == 5) {
			refranes = repository.findTop5ByOrderByCalidad();
		} else if (numRefranes == 10) {
			refranes = repository.findTop10ByOrderByCalidad();
		} else {
			final Page<Refran> page = repository.findAll(PageRequest.of(0, numRefranes,
					Sort.by(ConstantsData.ASCENDENTE.equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC,
							"calidad")));
			refranes = Optional.of(page.get().collect(Collectors.toList()));
		}
		return refranes;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumRefranes() {
		return repository.count();
	}

	@Override
	@Transactional
	public Refran addRefran(Refran refran) {
		return repository.save(refran);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<Refran>> sortRefranes(String order) {
		return ConstantsData.ASCENDENTE.equalsIgnoreCase(order) ? repository.findAllByOrderByCalidadAsc()
				: repository.findAllByOrderByCalidadDesc();
	}

	@Override
	@Transactional
	public void deleteRefran(Long id) {
		final Refran ref = repository.findById(id)
				.orElseThrow(() -> new DataBaseException(ConstantsData.CODE_ERR_SEARCH_REFRAN,
						ConstantsData.MESS_ERR_SEARCH_REFRAN, HttpStatus.NOT_FOUND));
		repository.delete(ref);
	}

	@Override
	public Optional<List<Refran>> getRefranesContiene(String texto) {
		return repository.findByTextoContainingIgnoreCase(texto);

	}

	@Override
	public Optional<List<Refran>> getRefranesUsuarioContiene(String usuario) {
		return repository.findByUsuarioContainingIgnoreCase(usuario);

	}

	@Override
	public Optional<List<Refran>> getRefranesUsuarioContieneOrdenado(String usuario, String order) {

		Optional<List<Refran>> ord = null;

		if (ConstantsData.ASCENDENTE.equalsIgnoreCase(order)) {
			ord = repository.findByUsuarioContainingIgnoreCaseOrderByUsuarioAsc(usuario);
		} else if (ConstantsData.DESCENDENTE.equalsIgnoreCase(order)) {
			ord = repository.findByUsuarioContainingIgnoreCaseOrderByUsuarioDesc(usuario);

		}

		return ord;

	}

}
