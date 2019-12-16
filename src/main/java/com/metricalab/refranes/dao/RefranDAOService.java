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

import com.metricalab.refranes.entity.Refran;
import com.metricalab.refranes.exception.DataBaseException;
import com.metricalab.refranes.utils.ConstantsData;

@Service
public class RefranDAOService implements IRefranDAOService {

	@Autowired
	private IRefranDAO repository;

	@Override
	public Optional<Refran> getBestRefran() {
		return repository.findFirstByOrderByCalidadDesc();
	}

	@Override
	public Optional<Refran> getRandomRefran() {
		return repository.findRandom();
	}

	@Override
	public Optional<List<Refran>> getRefranes(final int numRefranes, final String order) {
		// Es un ejemplo de diferentes llamadas al repositorio
		// dependiendo del numero de refranes a buscar
		Optional<List<Refran>> refranes = null;
		if (numRefranes == 5) {
			refranes = repository.findTop5ByOrderByCalidad();
		} else if (numRefranes == 10) {
			refranes = repository.findTop10ByOrderByCalidad();
		} else {
			final Page<Refran> page = repository.findAll(PageRequest.of(0, numRefranes,
					Sort.by("ASC".equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC, "calidad")));
			refranes = Optional.of(page.get().collect(Collectors.toList()));
		}
		return refranes;
	}

	@Override
	public Long getNumRefranes() {
		return repository.count();
	}

	@Override
	public Refran addRefran(final Refran refran) {
		return repository.save(refran);
	}

	@Override
	public Optional<List<Refran>> sortRefranes(final String order) {
		return "ASC".equalsIgnoreCase(order) ? repository.findAllByOrderByCalidadAsc()
				: repository.findAllByOrderByCalidadDesc();
	}

	@Override
	public void deleteRefran(final Long id) {
		final Refran ref = repository.findById(id)
				.orElseThrow(() -> new DataBaseException(ConstantsData.CODE_ERR_SEARCH_REFRAN,
						ConstantsData.MESS_ERR_SEARCH_REFRAN, HttpStatus.NOT_FOUND));
		repository.delete(ref);
	}

	@Override
	public Optional<Refran> getRefranById(final Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<List<Refran>> getRefranByUser(final String user) {
		return repository.findByUsuario(user);
	}

}
