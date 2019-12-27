package com.metricalab.refranes.service;

import java.util.Date;
import java.util.List;

import com.metricalab.refranes.entity.LogRefran;
import com.metricalab.refranes.pojo.LogRefranDTO;

public interface IRefranLogService {

	public List<LogRefran> findAll();

	public LogRefran findById(Long id);

	public void deleteById(Long id);

	public void DeleteAll();

	public List<LogRefran> findByAccion(String accion);

	public List<LogRefran> findByVerbo(String verbo);

	public List<LogRefran> findByFecha(Date fecha);

	public List<LogRefran> findByDescripcion(String descripcion);

	public LogRefranDTO addLog(LogRefran logRefran);

}
