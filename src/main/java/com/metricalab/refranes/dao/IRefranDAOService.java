package com.metricalab.refranes.dao;

import java.util.List;
import java.util.Optional;

import com.metricalab.refranes.entity.Refran;

public interface IRefranDAOService {

	public Optional<Refran> getBestRefran();

	public Optional<Refran> getRandomRefran();

	public Optional<List<Refran>> getRefranes(int numRefranes, final String order);

	public Long getNumRefranes();

	public Refran addRefran(final Refran refran);

	public Optional<List<Refran>> sortRefranes(final String order);

	public void deleteRefran(final Long id);

	public Optional<Refran> getRefranById(Long id);

	public Optional<List<Refran>> getRefranByUser(String user);

	public Optional<List<Refran>> getRefranesContiene(final String texto);

	public Optional<List<Refran>> getRefranesUsuarioContiene(final String usuario);

	public Optional<List<Refran>> getRefranesUsuarioContieneOrdenado(final String usuario, final String order);

	public Double getMediaCalidadRefranes();

}
