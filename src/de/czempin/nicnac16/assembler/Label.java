package de.czempin.nicnac16.assembler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Label {

	private String symbol;
	private Integer value;
	private Set<Integer> references = new HashSet<Integer>();

	public Label(String symbol) {
		this.symbol = symbol;	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void addReference(int currentAddress) {
		this.references.add(currentAddress);
	}

	public Collection<Integer> getReferences() {
		return Collections.unmodifiableCollection(this.references);
	}

}
