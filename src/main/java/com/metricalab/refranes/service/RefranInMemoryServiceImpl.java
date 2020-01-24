package com.metricalab.refranes.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.metricalab.refranes.pojo.InMemoryRefranes;
import com.metricalab.refranes.pojo.RefranDTO;
import com.metricalab.refranes.utils.ConstantsData;

@Service
@Qualifier("inMemory")
public class RefranInMemoryServiceImpl implements IRefranService {

	private static final Logger log = Logger.getLogger(RefranInMemoryServiceImpl.class.getName());

	@Autowired
	private InMemoryRefranes inMemoryRefranes;

	@Override
	public RefranDTO getBestRefran() {
		log.log(Level.INFO, "Obteniendo el mejor refrán");
		return Collections.max(inMemoryRefranes.getRefranes(), Comparator.comparing(c -> c.getCalidad()));
	}

	@Override
	public RefranDTO getRandomRefran() {
		log.log(Level.INFO, "Obteniendo un refrán aleatorio");
		return inMemoryRefranes.getRefranes().get(new Random().nextInt(inMemoryRefranes.getMaxRefranes() - 1));
	}

	@Override
	public List<RefranDTO> getRefranes(final int numeroRefranes, final String order) {
		log.log(Level.INFO, () -> String.format("Obteniendo %s refranes pedidos. Criterio de ordenación: %s. ",
				numeroRefranes, order));
		final Stream<RefranDTO> str = sortRefranesList(order, inMemoryRefranes.getRefranes()).stream();
		return str.limit(numeroRefranes).collect(Collectors.toList());
	}

	@Override
	public Integer getNumRefranes() {
		log.log(Level.INFO, "Obteniendo el número de refranes disponibles");
		return inMemoryRefranes.getRefranes().size();
	}

	@Override
	public RefranDTO addRefran(final RefranDTO refran) {
		log.log(Level.INFO, "Insertando un nuevo refrán: {0}", refran.getTexto());
		final Long maxValue = inMemoryRefranes.getRefranes().stream().mapToLong(x -> x.getId()).max().getAsLong();
		refran.setId(maxValue + 1);
		inMemoryRefranes.getRefranes().add(refran);
		return refran;
	}

	@Override
	public List<RefranDTO> sortRefranes(final String order) {
		log.log(Level.INFO, "Ordenando refranes: {0} ", order);
		final List<RefranDTO> refranes = inMemoryRefranes.getRefranes();
		sortRefranesList(order, refranes);
		return refranes;
	}

	@Override
	public void deleteRefran(final Long id) {
		final List<RefranDTO> refranes = inMemoryRefranes.getRefranes();
		refranes.removeIf(x -> x.getId().equals(id));
	}

	/**
	 * Ordena una lista de refranes
	 *
	 * @param order    tipo de ordenación
	 * @param refranes Lista de refranes
	 * @return Refranes ordenados
	 */
	private List<RefranDTO> sortRefranesList(final String order, final List<RefranDTO> refranes) {

		if (ConstantsData.ASCENDENTE.equalsIgnoreCase(order)) {
			Collections.sort(refranes,
					Comparator.comparing(p -> p.getCalidad(), Comparator.nullsLast(Comparator.naturalOrder())));
		} else {
			Collections.sort(refranes,
					Comparator.comparing(p -> p.getCalidad(), Comparator.nullsLast(Comparator.reverseOrder())));
		}
		return refranes;
	}

	@Override

	public RefranDTO getRefranById(final Long id) {
		log.log(Level.INFO, "Buscar refrán con id {0}", id);
		final List<RefranDTO> refranes = inMemoryRefranes.getRefranes();
		final RefranDTO result = refranes.stream().filter(refran -> id.equals(refran.getId())).findAny().orElse(null);
		return result;
	}

	@Override
	public List<RefranDTO> getRefranByUser(final String user) {
		log.log(Level.INFO, "Buscar refrán por el username {0}", user);
		final List<RefranDTO> refranes = inMemoryRefranes.getRefranes();
		final List<RefranDTO> result = refranes.stream().filter(refran -> refran.getUsuario().equals(user))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<RefranDTO> getContainsRefran(String texto) {
		final List<RefranDTO> refranes = inMemoryRefranes.getRefranes();
		return refranes.stream().filter(refran -> refran.getTexto().toLowerCase().contains(texto.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<RefranDTO> getContainsUsuario(String usuario) {
		log.log(Level.INFO, () -> String.format("Obteniendo usuario %s ", usuario));
		final List<RefranDTO> refranes = inMemoryRefranes.getRefranes();
		return refranes.stream().filter(refran -> refran.getUsuario().toLowerCase().contains(usuario.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<RefranDTO> getContainsUsuarioOrder(String usuario, String order) {
		log.log(Level.INFO,
				() -> String.format("Obteniendo usuario %s . Criterio de ordenación: %s. ", usuario, order));
		final List<RefranDTO> busqueda = getContainsUsuario(usuario);
		return sortRefranesList(order, busqueda);
	}

	@Override
	public Double getMediaCalidadRefranes() {
		log.log(Level.INFO, "Obteniendo el número de refranes disponibles");
		Double sum = new Double(0);
		Double media = new Double(0);
		List<RefranDTO> listaRefranes = inMemoryRefranes.getRefranes();
		for (RefranDTO refranDTO : listaRefranes) {
			sum += refranDTO.getCalidad();
		}
		media = Double.valueOf(sum/inMemoryRefranes.getRefranes().size());
		
		return media;
	}

}
