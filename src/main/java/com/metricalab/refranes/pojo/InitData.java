package com.metricalab.refranes.pojo;

import java.util.List;

public class InitData {

	private List<Refran> refranes;
	private int maxRefranes = 10;

	public List<Refran> getRefranes() {
		return refranes;
	}

	public void setRefranes(List<Refran> refranes) {
		this.refranes = refranes;
	}

	public int getMaxRefranes() {
		return maxRefranes;
	}

	public void setMaxRefranes(int maxRefranes) {
		this.maxRefranes = maxRefranes;
	}

}
