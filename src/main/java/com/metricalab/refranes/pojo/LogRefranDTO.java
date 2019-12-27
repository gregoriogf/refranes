package com.metricalab.refranes.pojo;

import java.util.Date;

public class LogRefranDTO {

	private long id;
	private String accion;
	private String verbo;
	private Date fecha;
	private String descripcion;

	public LogRefranDTO(long id, String accion, String verbo, Date fecha, String descripcion) {
		this.id = id;
		this.accion = accion;
		this.verbo = verbo;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getVerbo() {
		return verbo;
	}

	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
