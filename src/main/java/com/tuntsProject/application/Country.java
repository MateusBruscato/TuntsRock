package com.tuntsProject.application;

public class Country {

	private String name;
	private String capital;
	private String area;
	private String currencies;
	
	public Country(String name, String capital, String area, String currencies) {
		super();
		this.name = name;
		this.capital = capital;
		this.area = area;
		this.currencies = currencies;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCurrencies() {
		return currencies;
	}
	public void setCurrencies(String currencies) {
		this.currencies = currencies;
	}

	@Override
	public String toString() {
		return "Country name=" + name + ", capital=" + capital + ", area=" + area + ", currencies=" + currencies + "]";
	}
	
	
	
}
