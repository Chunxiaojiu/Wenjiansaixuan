package com.example.zywenjian;

public class wenjianmodel {
	private String name;
	private String data;
	private String type;

	public wenjianmodel(String name, String data, String type) {
		setData(data);
		setName(name);
		setType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
