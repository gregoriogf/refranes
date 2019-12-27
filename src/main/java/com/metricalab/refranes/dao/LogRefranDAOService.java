package com.metricalab.refranes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricalab.refranes.entity.LogRefran;

@Service
public class LogRefranDAOService implements ILogRefranDAOService {

	@Autowired
	private ILogRefranDAO logRepository;

//	@Override
//	public LogRefranDTO addLogRefran(LogRefranDTO logRefran) {
//		return logRepository.save(logRefran);
//	}

	@Override
	public LogRefran addLogRefran(LogRefran logRefran) {
		return logRepository.save(logRefran);
	}

}
