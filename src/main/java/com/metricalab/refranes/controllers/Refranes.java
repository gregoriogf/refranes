package com.metricalab.refranes.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metricalab.refranes.pojo.NumRefranes;
import com.metricalab.refranes.pojo.RefranDTO;
import com.metricalab.refranes.pojo.RefranResponse;
import com.metricalab.refranes.service.IRefranService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH })
@RestController
@RequestMapping("/api")
public class Refranes {

	private static final Logger log = Logger.getLogger(Refranes.class.getName());

	// Si no se configura al iniciar la aplicación se puede
	// seleccionar la implementación a utilizar mediante un Qualifier.
	// @Qualifier("inMemory")
	// @Qualifier("dataBase")
	@Autowired
	IRefranService refranService;

	@GetMapping("/refranes/mejor")
	public ResponseEntity<RefranDTO> mejorRefran() {
		log.log(Level.INFO, "Llamada al endpoint /mejorrefran ");
		return new ResponseEntity<>(refranService.getBestRefran(), HttpStatus.OK);
	}

	@GetMapping("/refranes/{numero}/{order}")
	public ResponseEntity<List<RefranDTO>> refranes(@PathVariable final Integer numero,
			@PathVariable final String order) {
		// Logger con expresion lambda
		log.log(Level.INFO, () -> String.format(
				"Llamada al endpoint /refranes/{numero}/{order}. Se quieren recuperar %s refranes con ordenación %s .",
				numero, order));
		return new ResponseEntity<>(refranService.getRefranes(numero, order), HttpStatus.OK);
	}

	@GetMapping("/refranes/numeroTotal")
	public ResponseEntity<NumRefranes> numerorefranes() {
		log.log(Level.INFO, "Llamada al endpoint /numerorefranes");

		return new ResponseEntity<>(new NumRefranes(refranService.getNumRefranes()), HttpStatus.OK);
	}

	@GetMapping("/refranes/aleatorio")
	public ResponseEntity<RefranDTO> refranRandom() {
		log.log(Level.INFO, "Llamada al endpoint /refranes/aleatorio");
		return new ResponseEntity<>(refranService.getRandomRefran(), HttpStatus.OK);
	}

	@GetMapping("/refranes/ordenar/{order}")
	public ResponseEntity<List<RefranDTO>> ordenarRefranes(@PathVariable final String order) {
		log.log(Level.INFO, "Llamada al endpoint /ordenar/refranes");
		return new ResponseEntity<>(refranService.sortRefranes(order), HttpStatus.OK);
	}

	@PostMapping("/refranes")
	public ResponseEntity<RefranResponse> addRefran(@Valid @RequestBody final RefranDTO refran) {
		log.log(Level.INFO, "Llamada al endpoint /refranes (POST)");
		final String respuesta = refranService.addRefran(refran) != null ? " Refran insertado " : " Error al insertar ";
		return new ResponseEntity<>(new RefranResponse(respuesta), HttpStatus.OK);
	}

	@DeleteMapping("/refranes/{id}")
	public ResponseEntity<RefranResponse> deleteRefran(@PathVariable final Long id) {
		log.log(Level.INFO, "Llamada al endpoint /refranes/{id} (DELETE)");
		refranService.deleteRefran(id);
		return new ResponseEntity<>(new RefranResponse("Refran con id: " + id + " borrado"), HttpStatus.OK);
	}

	@GetMapping("/refranes/encontrarRefran/{texto}")
	public ResponseEntity<List<RefranDTO>> buscarRefran(@PathVariable final String texto) {
		log.log(Level.INFO, "Llamada al endpoint /refranes/encontrarRefran/{texto}");
		return new ResponseEntity<>(refranService.getContainsRefran(texto), HttpStatus.OK);

	}

	@GetMapping("/refranes/encontrarUsuario/{usuario}")
	public ResponseEntity<List<RefranDTO>> encontrarUsuario(@PathVariable final String usuario) {
		log.log(Level.INFO, "Llamada al endpoint /refranes/encontrarUsuario/{usuario}");
		return new ResponseEntity<>(refranService.getContainsUsuario(usuario), HttpStatus.OK);
	}

	@GetMapping("/refranes/encontrarUsuarioOrder/{usuario}/{order}")
	public ResponseEntity<List<RefranDTO>> encontrarUsuarioOrder(@PathVariable final String usuario,
			@PathVariable final String order) {
		log.log(Level.INFO, () -> String.format(
				"Llamada al endpoint /refranes/{usuario}/{order}. Se quieren recuperar %s refranes con ordenación %s .",
				usuario, order));
		return new ResponseEntity<>(refranService.getContainsUsuarioOrder(usuario, order), HttpStatus.OK);
	}

}
