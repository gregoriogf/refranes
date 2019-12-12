package com.metricalab.refranes.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.metricalab.refranes.converter.RefranConverter;
import com.metricalab.refranes.pojo.InMemoryRefranes;
import com.metricalab.refranes.pojo.RefranDTO;
import com.metricalab.refranes.service.IRefranService;
import com.metricalab.refranes.service.RefranDataBaseServiceImpl;
import com.metricalab.refranes.service.RefranInMemoryServiceImpl;

@Configuration
public class PersistentContext {

	private static final String SPRING_BOOT_MEMORY = "sb_memory";

	@Bean
	public InMemoryRefranes buildRefranes() {

		final List<RefranDTO> listaRefranes = new ArrayList<>();
		listaRefranes
				.add(new RefranDTO(Long.valueOf(1), "A caballo regalado no le mires el diente", 3, SPRING_BOOT_MEMORY));
		listaRefranes.add(new RefranDTO(Long.valueOf(2), "A falta de pan, buenas son tortas", 4, SPRING_BOOT_MEMORY));
		listaRefranes.add(new RefranDTO(Long.valueOf(3), "A la arrogancia en el pedir, la virtud del no dar", 12,
				SPRING_BOOT_MEMORY));
		listaRefranes.add(new RefranDTO(Long.valueOf(4), "A la tercera va la vencida", 3, SPRING_BOOT_MEMORY));
		listaRefranes.add(new RefranDTO(Long.valueOf(5), "A palabras necias, oídos sordos", 5, SPRING_BOOT_MEMORY));
		listaRefranes.add(new RefranDTO(Long.valueOf(6), "Al pan, pan, y al vino, vino", 11, SPRING_BOOT_MEMORY));
		listaRefranes.add(
				new RefranDTO(Long.valueOf(7), "Al perro flaco, todo se le vuelven pulgas", 1, SPRING_BOOT_MEMORY));
		listaRefranes.add(
				new RefranDTO(Long.valueOf(8), "Al que Dios se la dé, San Pedro se la bendiga", 9, SPRING_BOOT_MEMORY));
		listaRefranes
				.add(new RefranDTO(Long.valueOf(9), "Antes se coge al mentiroso que al cojo", 8, SPRING_BOOT_MEMORY));
		listaRefranes.add(new RefranDTO(Long.valueOf(10), "Burro grande, ande o no ande", 2, SPRING_BOOT_MEMORY));

		final InMemoryRefranes inMemoryRefranes = new InMemoryRefranes();
		inMemoryRefranes.setRefranes(listaRefranes);

		return inMemoryRefranes;
	}

	@Primary
	@Bean
	public IRefranService getServiceImpl(final Environment env) {
		final String serviceType = env.getProperty("metrica.data.service");
		return "inMemory".equals(serviceType) ? new RefranInMemoryServiceImpl() : new RefranDataBaseServiceImpl();
	}

	@Bean
	public RefranConverter getConverter() {
		return new RefranConverter();
	}

}
