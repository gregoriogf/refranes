package com.metrica.formacion.bck.api.rest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.metrica.formacion.bck.api.rest.model.entity.Cliente;
import com.metrica.formacion.bck.api.rest.model.entity.Region;
import com.metrica.formacion.bck.api.rest.models.dao.IClienteService;
import com.metrica.formacion.bck.api.rest.services.IUploadFileService;

@CrossOrigin(origins = "${angular.dev.url}")
//@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	private static final int PAGE_SIZE = 5;
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadService;

	private static final Logger log = Logger.getLogger(ClienteRestController.class.getName());

	@Value("${file.path}")
	private String filePath;

	/**
	 * Muestra la lista de clientes existentes
	 *
	 * @return respuesta con la lista de clientes sin filtrar o un mensaje de error
	 *         en caso de fallo
	 */
	@GetMapping("/clientes")
	public ResponseEntity<?> index() {

		List<Cliente> listaClientes = null;
		final Map<String, Object> response = new HashMap<>();

		try {
			listaClientes = clienteService.findAll();
		} catch (final DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
	}

	/**
	 * Muestra la lista de clientes existentes
	 *
	 * @return respuesta con la lista de clientes sin filtrar o un mensaje de error
	 *         en caso de fallo
	 */
	@GetMapping("/clientes/pages/{page}")
	public ResponseEntity<?> index(@PathVariable final Integer page) {

		Page<Cliente> listaClientes = null;
		final Map<String, Object> response = new HashMap<>();

		try {
			listaClientes = clienteService.findAll(PageRequest.of(page, PAGE_SIZE));
		} catch (final DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(listaClientes, HttpStatus.OK);
	}

	/**
	 * Muestra el cliente con el id pasado por parametro
	 *
	 * @param id del cliente
	 * @return Respuesta con el cliente buscado o un mensaje de error en caso
	 *         contrario
	 */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable final Long id) {

		Cliente cliente = null;
		final Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (final DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("message", "El cliente id: ".concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	/**
	 * Guarda en base de datos el objeto cliente pasado por parametros
	 *
	 * @param cliente
	 * @return Respuesta con el nuevo cliente (con su id) o mensaje de error en caso
	 *         contrario
	 */
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody final Cliente cliente, final BindingResult result) {

		Cliente nuevoCliente = null;
		final Map<String, Object> response = new HashMap<>();

		final ResponseEntity<?> errorValidation = preValidateEntity(result);
		if (errorValidation != null) {
			return errorValidation;
		}

		try {
			nuevoCliente = clienteService.save(cliente);
		} catch (final DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "El cliente ha sido creado con exito");
		response.put("cliente", nuevoCliente);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Actualiza la entidad cliente
	 *
	 * @param cliente datos a modificar
	 * @param id      identificador del cliente
	 * @return Respuesta con el cliente actualizado o mensaje de error en caso
	 *         contrario
	 */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody final Cliente cliente, final BindingResult result,
			@PathVariable final Long id) {

		final Map<String, Object> response = new HashMap<>();
		Cliente clienteActualizado = null;
		Cliente clienteActual = null;

		final ResponseEntity<?> errorValidation = preValidateEntity(result);
		if (errorValidation != null) {
			return errorValidation;
		}

		try {
			clienteActual = clienteService.findById(id);
			if (clienteActual == null) {
				response.put("message", "Error no se pudo editar el cliente con id: "
						.concat(id.toString().concat(" No existe en la base de datos.")));
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}

			// La repuesta de PUT es ambigua.
			// Si no existe debería ser CREATED
			// Si existe OK ó NO CONTENT
			// https://developer.mozilla.org/es/docs/Web/HTTP/Methods/PUT
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreatedAt(cliente.getCreatedAt());
			clienteActual.setRegion(cliente.getRegion());
			clienteActualizado = clienteService.save(clienteActual);

		} catch (final DataAccessException e) {
			response.put("message", "Error al realizar el update en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "El cliente ha sido actualizado con exito");
		response.put("cliente", clienteActualizado);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	/**
	 * Borra un cliente con el id pasado por parametro
	 *
	 * @param id del cliente
	 * @return Mensaje informativo sonbre la petición
	 */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable final Long id) {
		final Map<String, Object> response = new HashMap<>();

		try {
			final Cliente cliente = clienteService.findById(id);
			final String nombreFotoAnterior = cliente.getFoto();
			uploadService.eliminar(nombreFotoAnterior);

			clienteService.delete(id);
		} catch (final DataAccessException e) {
			response.put("message", "Error al borrar un cliente de la base de datos.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "Cliente eliminado.");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Valida si los campos enviados son correctos
	 *
	 * @param result
	 * @return null si no hay errores, respuesta de error en caso contrario
	 */
	private ResponseEntity<?> preValidateEntity(final BindingResult result) {
		final Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
		}

		return !response.isEmpty() ? new ResponseEntity<>(response, HttpStatus.BAD_REQUEST) : null;
	}

	/**
	 *
	 * @param archivo
	 * @param id
	 * @return
	 */
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") long id) {
		final Map<String, Object> response = new HashMap<>();

		final Cliente cliente = clienteService.findById(id);
		log.info("upload: Comprobando el estado del archivo");

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (final IOException e) {
				response.put("message", "Error al subir la imagen: ");
				response.put("error", e.getCause().getMessage());
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			final String nombreFotoAnterior = cliente.getFoto();

			uploadService.eliminar(nombreFotoAnterior);

			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("message", "Ha subido correctamente la imagen: " + nombreArchivo);
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<?> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		}

		final HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}

	/**
	 * Muestra la lista de regiones existentes
	 *
	 * @return respuesta con la lista de regiones
	 */
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones() {

		return clienteService.findAllRegiones();
	}

}
