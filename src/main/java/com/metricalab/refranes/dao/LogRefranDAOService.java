package com.metricalab.refranes.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricalab.refranes.entity.LogRefran;

@Service
public class LogRefranDAOService implements ILogRefranDAOService {

	@Autowired
	private ILogRefranDAO logRepository;

	@Override
	@Transactional
	public LogRefran addLogRefran(LogRefran logRefran) {
		return logRepository.save(logRefran);
	}

}
