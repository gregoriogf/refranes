package com.metricalab.refranes.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.metricalab.refranes.pojo.InitData;
import com.metricalab.refranes.pojo.Refran;

@Configuration
public class PersistentContext {

	@Bean()
	public InitData buildRefranes(final Environment env) {

		final List<Refran> listaRefranes = new ArrayList<>();
		listaRefranes.add(new Refran("A caballo regalado no le mires el diente", 3));
		listaRefranes.add(new Refran("A falta de pan, buenas son tortas", 4));
		listaRefranes.add(new Refran("A la arrogancia en el pedir, la virtud del no dar", 12));
		listaRefranes.add(new Refran("A la tercera va la vencida", 3));
		listaRefranes.add(new Refran("A palabras necias, oídos sordos", 5));
		listaRefranes.add(new Refran("Al pan, pan, y al vino, vino", 11));
		listaRefranes.add(new Refran("Al perro flaco, todo se le vuelven pulgas", 1));
		listaRefranes.add(new Refran("Al que Dios se la dé, San Pedro se la bendiga", 9));
		listaRefranes.add(new Refran("Antes se coge al mentiroso que al cojo", 8));
		listaRefranes.add(new Refran("Burro grande, ande o no ande", 2));

		final InitData initData = new InitData();
		initData.setRefranes(listaRefranes);

		return initData;
	}
}
