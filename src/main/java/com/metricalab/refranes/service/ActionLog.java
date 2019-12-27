package com.metricalab.refranes.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.metricalab.refranes.converter.LogRefranConverter;
import com.metricalab.refranes.dao.ILogRefranDAOService;
import com.metricalab.refranes.entity.LogRefran;
import com.metricalab.refranes.pojo.LogRefranDTO;

@Service
@Qualifier("dataBase")
public class ActionLog implements IRefranLogService {

	private static final Logger log = Logger.getLogger(ActionLog.class.getName());

	@Autowired
	ILogRefranDAOService logRefranDaoService;

	@Autowired
	IRefranLogRepository repository;

	@Autowired
	private LogRefranConverter logRefranConverter;

	@Override
	public List<LogRefran> findAll() {
		log.log(Level.INFO, "Listado de todos los logs");
		return repository.findAll();
	}

	@Override
	public LogRefran findById(Long id) {
		log.log(Level.INFO, "Buscando el log con id:{0}", id);
		return repository.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		final Optional<LogRefran> opt = repository.findById(id);
		if (opt.isPresent()) {
			log.log(Level.WARNING, "Borrando el log con id: {0}", id);
		} else {
			log.log(Level.WARNING, "El log con id: {0} no existe", id);
		}
	}

	@Override
	public void DeleteAll() {
		log.log(Level.WARNING, "Borrando todos los logs:");
	}

	// Buscar por accion
	@Override
	public List<LogRefran> findByAccion(String accion) {
		log.log(Level.WARNING, "Buscando los logs de accion: {0}", accion);
		return repository.findByAccion(accion);
	}

	// Buscar por verbo
	@Override
	public List<LogRefran> findByVerbo(String verbo) {
		log.log(Level.WARNING, "Buscando los logs de verbo: {0}", verbo);
		return repository.findByVerbo(verbo);
	}

	// Buscar por fecha
	@Override
	public List<LogRefran> findByFecha(Date fecha) {
		log.log(Level.WARNING, "Buscando los logs de fecha: {0}", fecha);
		return repository.findByFecha(fecha);
	}
	// Buscar por descripcion

	@Override
	public List<LogRefran> findByDescripcion(String descripcion) {
		log.log(Level.WARNING, "Buscando los logs de descripcion: {0}", descripcion);
		return repository.findByDescripcion(descripcion);
	}

	@Override
	public LogRefranDTO addLog(final LogRefranDTO logRefranDTO) {
		log.log(Level.INFO, "Informacion log: {0}", logRefranDTO.getDescripcion());
		final LogRefran logrefran = logRefranDaoService.addLogRefran(logRefranConverter.apply(logRefranDTO));
		// TODO Auto-generated method stub
		return logRefranConverter.apply(logrefran);
	}

	@Override
	public LogRefranDTO addLog(LogRefran logRefran) {
		// TODO Auto-generated method stub
		return null;
	}

}