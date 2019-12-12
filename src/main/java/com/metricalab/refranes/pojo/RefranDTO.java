package com.metricalab.refranes.pojo;

public class RefranDTO {

	private Long id;
	private String texto;
	private int calidad;
	private String usuario;

	public RefranDTO(Long id, String texto, Integer calidad, String usuario) {
		this.texto = texto;
		this.calidad = calidad;
		this.usuario = usuario;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCalidad() {
		return calidad;
	}

	public void setCalidad(Integer calidad) {
		this.calidad = calidad;
	}

	public RefranDTO(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
