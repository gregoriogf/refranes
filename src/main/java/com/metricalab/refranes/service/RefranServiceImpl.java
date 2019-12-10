package com.metricalab.refranes.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricalab.refranes.pojo.InitData;
import com.metricalab.refranes.pojo.Refran;

@Service
public class RefranServiceImpl implements RefranService {

	@Autowired
	InitData initData;

	@Override
	public Refran getBestRefran() {
		return Collections.max(initData.getRefranes(), Comparator.comparing(c -> c.getCalidad()));
	}

	@Override
	public Refran getRefranRandom() {
		return initData.getRefranes().get(new Random().nextInt(initData.getMaxRefranes() - 1));
	}

	@Override
	public List<Refran> getRefranes(int numeroRefranes) {
		final Stream<Refran> str = initData.getRefranes().stream();
		return str.limit(numeroRefranes).collect(Collectors.toList());
	}

	@Override
	public int getnumRefranes() {
		return initData.getRefranes().size();
	}

	@Override
	public int addRefran(Refran refran) {
		initData.getRefranes().add(refran);
		return 0;
	}
}
