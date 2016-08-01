package com.iqwareinc.platform.core.model.entity;

public class Property extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
