package com.metricalab.refranes.converter;

import com.metricalab.refranes.entity.LogRefran;
import com.metricalab.refranes.pojo.LogRefranDTO;

public class LogRefranConverter implements GenericConverter<LogRefran, LogRefranDTO> {

	@Override
	public LogRefranDTO apply(final LogRefran input) {
		return new LogRefranDTO(input.getId(), input.getAccion(), input.getVerbo(), input.getFecha(),
				input.getDescripcion());
	}

	public LogRefran apply(final LogRefranDTO output) {
		return new LogRefran(output.getAccion(), output.getVerbo(), output.getFecha(), output.getDescripcion());
	}

}
