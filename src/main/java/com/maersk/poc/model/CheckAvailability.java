/**
 * 
 */
package com.maersk.poc.model;

/**
 * @author rkumar
 *
 */
public class CheckAvailability {
	private Integer availableSpace;
	private Boolean available;
	
	
	
	public CheckAvailability() {}
	
	public CheckAvailability(Integer availableSpace, Boolean available) {
		super();
		this.availableSpace = availableSpace;
		this.available = available;
	}
	public Integer getAvailableSpace() {
		return availableSpace;
	}
	public void setAvailableSpace(Integer availableSpace) {
		this.availableSpace = availableSpace;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	
	
}
