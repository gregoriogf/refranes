package com.metricalab.refranes.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "refranes")
public class Refran implements Serializable {

	private static final long serialVersionUID = -2955850841909139794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String texto;

	@Column(nullable = false)
	private Integer calidad;

	@Column(name = "ult_actualiz")
	@Temporal(TemporalType.DATE)
	private Date actualizado;

	@Column(nullable = false)
	private String usuario;

	public Refran() {

	}

	public Refran(String texto, int calidad, Date actualizado, String usuario) {
		this.texto = texto;
		this.calidad = calidad;
		this.actualizado = actualizado;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getCalidad() {
		return calidad;
	}

	public void setCalidad(Integer calidad) {
		this.calidad = calidad;
	}

	public Date getActualizado() {
		return actualizado;
	}

	public void setActualizado(Date actualizado) {
		this.actualizado = actualizado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
