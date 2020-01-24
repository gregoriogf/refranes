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
	public List<RefranDTO> getRefranes(final int numRefranes, final String order) {
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
	public void deleteRefran(final Long id) {
		log.log(Level.INFO, "Borrar refrán con id: {0} ", id);
		refranDAOService.deleteRefran(id);
	}

	@Override
	public RefranDTO getRefranById(final Long id) {
		log.log(Level.INFO, "Buscar refrán con id {0}", id);
		return refranConverter.convert(refranDAOService.getRefranById(id).orElse(null));
	}

	@Override
	public List<RefranDTO> getRefranByUser(final String user) {
		log.log(Level.INFO, "Buscar refrán por el username {0}", user);
		return refranConverter.convert(refranDAOService.getRefranByUser(user).orElse(null));
	}

	@Override
	public List<RefranDTO> getContainsRefran(final String texto) {
		log.log(Level.INFO, "Buscar Refrán", texto);
		return refranConverter.convert(refranDAOService.getRefranesContiene(texto).orElse(null));

	}

	@Override
	public List<RefranDTO> getContainsUsuario(final String usuario) {
		log.log(Level.INFO, "Buscar Usuario", usuario);
		return refranConverter.convert(refranDAOService.getRefranesUsuarioContiene(usuario).orElse(null));
	}

	@Override
	public List<RefranDTO> getContainsUsuarioOrder(final String usuario, final String order) {
		log.log(Level.INFO, "Buscar Usuario %s", usuario);
		log.log(Level.INFO, "Ordenado de manera %s ", order);
		return refranConverter
				.convert(refranDAOService.getRefranesUsuarioContieneOrdenado(usuario, order).orElse(null));

	}

	@Override
	public Double getMediaCalidadRefranes() {
		log.log(Level.INFO, "Obteniendo la media de la calidad de los refranes disponibles");
		return refranDAOService.getMediaCalidadRefranes();
	}

}
