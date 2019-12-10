package com.metricalab.refranes.pojo;

public class Refran {

	private String texto;
	private int calidad;

	public Refran(String texto, int calidad) {
		this.texto = texto;
		this.calidad = calidad;
	}

	public int getCalidad() {
		return calidad;
	}

	public void setCalidad(int calidad) {
		this.calidad = calidad;
	}

	public Refran(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
