package com.metricalab.refranes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metricalab.refranes.pojo.NumeroRefranes;
import com.metricalab.refranes.pojo.Refran;
import com.metricalab.refranes.pojo.RefranResponse;
import com.metricalab.refranes.service.RefranService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH })
@RestController
@RequestMapping("/metrica")
public class Refranes {

	@Autowired
	RefranService refranService;

	@GetMapping("/mejorrefran")
	public ResponseEntity<Refran> refran() {
		return new ResponseEntity<>(refranService.getBestRefran(), HttpStatus.OK);
	}

	@GetMapping("/refranes/{numero}")
	public ResponseEntity<List<Refran>> refranes(@PathVariable final Integer numero) {
		return new ResponseEntity<>(refranService.getRefranes(numero), HttpStatus.OK);
	}

	@GetMapping("/numerorefranes")
	public ResponseEntity<NumeroRefranes> numerorefranes() {
		return new ResponseEntity<>(new NumeroRefranes(refranService.getnumRefranes()), HttpStatus.OK);
	}

	@GetMapping("/refranes/random")
	public ResponseEntity<Refran> refranRandom() {
		return new ResponseEntity<>(refranService.getRefranRandom(), HttpStatus.OK);
	}

	@PostMapping("/refran/add")
	public ResponseEntity<RefranResponse> addRefran(@Valid @RequestBody final Refran refran) {
		final String respuesta = refranService.addRefran(refran) == 0 ? " Refran insertado " : " Error al insertar";
		return new ResponseEntity<>(new RefranResponse(respuesta), HttpStatus.OK);
	}

}
