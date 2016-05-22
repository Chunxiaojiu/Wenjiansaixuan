package com.example.zywenjian;

public class WenjianChioseModel {
	private boolean single;
	private int number;
	private boolean color;
	private int oneforpaper;
	private String name;
	private String data;
	private String type;

	public WenjianChioseModel(String name, String data, String type,
			boolean single, int number, boolean color, int oneforpaper) {
		setName(name);
		setData(data);
		setType(type);
		setColor(color);
		setNumber(number);
		setOneforpaper(oneforpaper);
		setSingle(single);
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

	public boolean isSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public int getOneforpaper() {
		return oneforpaper;
	}

	public void setOneforpaper(int oneforpaper) {
		this.oneforpaper = oneforpaper;
	}

}
