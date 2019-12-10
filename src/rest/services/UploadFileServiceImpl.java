package com.metrica.formacion.bck.api.rest.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private static final Logger log = Logger.getLogger(UploadFileServiceImpl.class.getName());

	@Value("${file.path}")
	private String filePath;

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = null;

		log.log(Level.INFO, "Fichero: {0} ", rutaArchivo);
		recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("notUser.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
			log.log(Level.SEVERE, null, "No se pudo cargar la imagen:" + nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		final String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename().replace(" ", "");
		log.info(" Nombre de archivo a copiar:" + nombreArchivo);
		final Path rutaArchivo = getPath(nombreArchivo);
		log.info(" Ruta del archivo:" + rutaArchivo.toUri());
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			final Path rutaFotoAnterior = Paths.get(filePath).resolve(nombreFoto).toAbsolutePath();
			final File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(filePath).resolve(nombreFoto).toAbsolutePath();
	}

}
