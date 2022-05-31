package com.bharath.flightreservation.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Reservation extends AbstractEntity {
	
	private Boolean checkedIn;
	private int NumberOfBags;
	@OneToOne
	private Passenger passenger;
	@OneToOne
	private Flight flight;
	
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public int getNumberOfBags() {
		return NumberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		NumberOfBags = numberOfBags;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
