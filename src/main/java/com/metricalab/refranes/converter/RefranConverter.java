package com.metricalab.refranes.converter;

import java.util.Date;

import com.metricalab.refranes.entity.Refran;
import com.metricalab.refranes.pojo.RefranDTO;

public class RefranConverter implements GenericConverter<Refran, RefranDTO> {

	@Override
	public RefranDTO apply(final Refran input) {
		return new RefranDTO(input.getId(), input.getTexto(), input.getCalidad(), input.getUsuario());
	}

	public Refran apply(final RefranDTO output) {
		return new Refran(output.getTexto(), output.getCalidad(), new Date(), output.getUsuario());
	}

}
