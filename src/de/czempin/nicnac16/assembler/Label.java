package de.czempin.nicnac16.assembler;

public class Label {

	private String symbol;
	private Integer value;

	public Label(String symbol) {
		this.symbol = symbol;	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
