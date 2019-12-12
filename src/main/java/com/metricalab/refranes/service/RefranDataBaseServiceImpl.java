package com.metricalab.refranes.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.metricalab.refranes.converter.RefranConverter;
import com.metricalab.refranes.dao.IRefranDAOService;
import com.metricalab.refranes.entity.Refran;
import com.metricalab.refranes.pojo.RefranDTO;

@Service
@Qualifier("dataBase")
public class RefranDataBaseServiceImpl implements IRefranService {

	private static final Logger log = Logger.getLogger(RefranDataBaseServiceImpl.class.getName());

	@Autowired
	private IRefranDAOService refranDAOService;

	@Autowired
	private RefranConverter refranConverter;

	@Override
	public RefranDTO getBestRefran() {
		log.log(Level.INFO, "Obteniendo el mejor refrán");
		return refranConverter.convert(refranDAOService.getBestRefran().orElse(null));
	}

	@Override
	public RefranDTO getRandomRefran() {
		log.log(Level.INFO, "Obteniendo un refrán aleatorio");
		return refranConverter.convert(refranDAOService.getRandomRefran().orElse(null));
	}

	@Override
	public List<RefranDTO> getRefranes(int numRefranes, final String order) {
		log.log(Level.INFO, "Obteniendo {0} refranes pedidos.", numRefranes);
		return refranConverter.convert(refranDAOService.getRefranes(numRefranes, order).orElse(null));
	}

	@Override
	public Integer getNumRefranes() {
		log.log(Level.INFO, "Obteniendo el número de refranes disponibles");
		return Math.toIntExact(refranDAOService.getNumRefranes());
	}

	@Override
	public RefranDTO addRefran(final RefranDTO refranDTO) {
		log.log(Level.INFO, "Insertando un nuevo refrán: {0}", refranDTO.getTexto());
		final Refran refran = refranDAOService.addRefran(refranConverter.apply(refranDTO));
		return refranConverter.apply(refran);
	}

	@Override
	public List<RefranDTO> sortRefranes(final String order) {
		log.log(Level.INFO, "Ordenando refranes: {0} ", order);
		return refranConverter.convert(refranDAOService.sortRefranes(order).orElse(null));
	}

	@Override
	public void deleteRefran(Long id) {
		log.log(Level.INFO, "Borrar refrán con id: {0} ", id);
		refranDAOService.deleteRefran(id);
	}

}