package com.metricalab.refranes.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.metricalab.refranes.entity.LogRefran;
import com.metricalab.refranes.pojo.LogRefranDTO;

@Repository
public interface IRefranLogRepository extends JpaRepository<LogRefran, Long>, JpaSpecificationExecutor<LogRefran> {

	public List<LogRefran> findByAccion(String accion);

	public List<LogRefran> findByVerbo(String verbo);

	public List<LogRefran> findByFecha(Date fecha);

	public List<LogRefran> findByDescripcion(String descripcion);

	public LogRefranDTO addLog(LogRefranDTO logRefran);

}
