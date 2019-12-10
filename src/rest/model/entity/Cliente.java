package com.metrica.formacion.bck.api.rest.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Mapeo a base de datos de la clase cliente
 *
 * @author juan.pardo
 *
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	/**
	 * Identificador para serialización
	 */
	private static final long serialVersionUID = 1566393413681520103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 30)
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "no puede estar vacío")
	private String apellido;

	@NotEmpty(message = "no puede estar vacío")
	@Email
	@Column(nullable = false, unique = false)
	private String email;

	@NotNull(message = "no puede estar vacío")
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	private String foto;

	// Una region puede tener muchos clientes
	@NotNull(message = "la región no puede ser vacía")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Region region;

	// @PrePersist public void prePersist() { createdAt = new Date(); }
	// usar @PreUpdate para actualización

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
