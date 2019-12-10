package com.metricalab.refranes.service;

import java.util.List;

import com.metricalab.refranes.pojo.Refran;

public interface RefranService {

	public Refran getBestRefran();

	public Refran getRefranRandom();

	public List<Refran> getRefranes(int numeroRefranes);

	public int getnumRefranes();

	public int addRefran(Refran refran);
}
