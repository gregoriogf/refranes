package com.metricalab.refranes.service;

import java.util.List;

import com.metricalab.refranes.pojo.RefranDTO;

public interface IRefranService {

	public RefranDTO getBestRefran();

	public RefranDTO getRandomRefran();

	public List<RefranDTO> getRefranes(int numRefranes, String order);

	public Integer getNumRefranes();

	public RefranDTO addRefran(RefranDTO refran);

	public List<RefranDTO> sortRefranes(String order);

	public void deleteRefran(Long id);

	public RefranDTO getRefranById(Long id);

	public List<RefranDTO> getRefranByUser(String user);

	public List<RefranDTO> getContainsRefran(String texto);

	public List<RefranDTO> getContainsUsuario(String usuario);

	public List<RefranDTO> getContainsUsuarioOrder(String usuario, String order);

}
