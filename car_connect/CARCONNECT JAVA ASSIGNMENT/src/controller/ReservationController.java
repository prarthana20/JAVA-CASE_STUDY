package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.IReservationService;
import dao.ReservationService;
import exception.InvalidInputException;
import exception.ReservationException;
import model.Reservation;

public class ReservationController {
	public void reserveAVehicle(Reservation reservation) {
		
		try {
			IReservationService irs=new ReservationService();
			irs.createReservation(reservation);
		} catch (ClassNotFoundException | SQLException | IOException|ReservationException|InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
	
}
public void updateReservation(Reservation reservation) {
	
	try {
		IReservationService irs=new ReservationService();
		irs.updateReservation(reservation);
	} catch (ClassNotFoundException | SQLException | IOException|ReservationException|InvalidInputException e) {
		System.out.println(e.getMessage());
	}

}
public void deleteReservation(int reservationId) {
	try {
		IReservationService irs=new ReservationService();
		irs.cancelReservation(reservationId);
	} catch (ClassNotFoundException | SQLException | IOException|ReservationException|InvalidInputException e) {
		System.out.println(e.getMessage());
	}
}
}