package com.maersk.poc.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value="maersk_booking")
public class Booking {
	/**
	 * @author rkumar
	 *
	 */
	
	@PrimaryKey
	private Long bookingRef;
	@Column(value="container_size")
	private Integer containersize;
	@Column(value = "container_type")
	public  ContainerType containertype;// can out side as: booking.setContainerSize(Booking.ContainerType.DRY);
	private String origin;
	private String destination;
	private Integer quantity;
	private String timestamp;
	
	
	
	public Booking() {}

  

	public Booking(Long bookingRef, Integer containersize, ContainerType containertype, String origin,
			String destination, Integer quantity, String timestamp) {
		super();
		this.bookingRef = bookingRef;
		this.containersize = containersize;
		this.containertype = containertype;
		this.origin = origin;
		this.destination = destination;
		this.quantity = quantity;
		this.timestamp = timestamp;
	}



	public Long getBookingRef() {
		return bookingRef;
	}



	public void setBookingRef(Long bookingRef) {
		this.bookingRef = bookingRef;
	}



	public Integer getContainersize() {
		return containersize;
	}



	public void setContainersize(Integer containersize) {
		this.containersize = containersize;
	}



	public ContainerType getContainertype() {
		return containertype;
	}



	public void setContainertype(ContainerType containertype) {
		this.containertype = containertype;
	}



	public String getOrigin() {
		return origin;
	}



	public void setOrigin(String origin) {
		this.origin = origin;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	

}


