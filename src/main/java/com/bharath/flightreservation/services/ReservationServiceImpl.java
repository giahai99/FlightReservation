package com.bharath.flightreservation.services;

import com.bharath.flightreservation.util.EmailUtil;
import com.bharath.flightreservation.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Passenger;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.repos.PassengerRepository;
import com.bharath.flightreservation.repos.ReservationRepository;

@Service
public class  ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		// Make Payment
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = "reservations/reservation_"+savedReservation.getId()+".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);

		emailUtil.sendItinerary(passenger.getEmail(), filePath);


		return savedReservation;
	}

}













