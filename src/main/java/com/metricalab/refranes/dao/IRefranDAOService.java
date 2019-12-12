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
}
