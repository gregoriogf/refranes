package com.metricalab.refranes.pojo;

import java.util.List;

public class InMemoryRefranes {

	private List<RefranDTO> refranes;
	private int maxRefranes = 10;

	public List<RefranDTO> getRefranes() {
		return refranes;
	}

	public void setRefranes(List<RefranDTO> refranes) {
		this.refranes = refranes;
	}

	public int getMaxRefranes() {
		return maxRefranes;
	}

	public void setMaxRefranes(int maxRefranes) {
		this.maxRefranes = maxRefranes;
	}

}
