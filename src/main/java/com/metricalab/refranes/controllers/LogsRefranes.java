package com.metricalab.refranes.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metricalab.refranes.entity.LogRefran;
import com.metricalab.refranes.pojo.LogRefranDTO;
import com.metricalab.refranes.service.ActionLog;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
@RestController
@RequestMapping("/apiLogs")
public class LogsRefranes {

	@Autowired
	private ActionLog repository;

	// FIND ALL
	@GetMapping("/logs")
	public List<LogRefran> findAll() {
		return repository.findAll();
	}

	// FIND BY ID
	@GetMapping("/logs/{id}")
	public LogRefran findById(@PathVariable("id") final Long id) {
		return repository.findById(id);
	}

	// DELETE BY ID
	@DeleteMapping("/logs/{id}")
	public void deleteById(@PathVariable("id") final Long id) {
		repository.deleteById(id);
	}

	// DELETE ALL
	@DeleteMapping("/logs")
	public void DeleteAll() {
		repository.DeleteAll();
	}

	// FIND BY ACCION
	@GetMapping("/logs/accion/{accion}")
	public List<LogRefran> findByAccion(@PathVariable("accion") final String accion) {
		return repository.findByAccion(accion);
	}

	// FIND BY VERBO
	@GetMapping("/logs/verbo/{verbo}")
	public List<LogRefran> findByVerbo(@PathVariable("verbo") final String verbo) {
		return repository.findByVerbo(verbo);
	}

	// FIND BY FECHA
	@GetMapping("/logs/fecha/{fecha}")
	public List<LogRefran> findByFecha(@PathVariable("fecha") final Date fecha) {
		return repository.findByFecha(fecha);
	}

	// FIND BY DESCRIPCION
	@GetMapping("/logs/descripcion/{descripcion}")
	public List<LogRefran> findByDescripcion(@PathVariable("descripcion") final String descripcion) {
		return repository.findByDescripcion(descripcion);
	}

	// CREATE LOG
	@PostMapping("/logs")
	public LogRefranDTO addLog(@PathVariable("logRefran") final LogRefranDTO logRefran) {
		final ActionLog actionlog = new ActionLog();
		final String respuesta = actionlog.addLog(logRefran) != null ? " Refran insertado " : " Error al insertar ";
		return repository.addLog(logRefran);
	}

}
