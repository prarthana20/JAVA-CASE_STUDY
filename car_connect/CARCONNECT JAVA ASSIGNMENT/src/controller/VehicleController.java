package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.IVehicleService;
import dao.VehicleService;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;
import model.Vehicle;

public class VehicleController {
	public void availableVehicles() {
		try {
			IVehicleService ivs=new VehicleService();
			List<Vehicle> availableVehicles=ivs.getAvailableVehicles();
			for(Vehicle vehi:availableVehicles) {
				System.out.println("Vehicle Id: "+vehi.getVehicleId());
				System.out.println("Vehicle Model: "+vehi.getModel());
				System.out.println("Vehicle Company: "+vehi.getMake());
				System.out.println("Vehicle Color: "+vehi.getColor());
				System.out.println("Vehicle Daily Rate: "+vehi.getDailyRate());
				
			}
		} catch (ClassNotFoundException | SQLException | IOException|VehicleNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void addVehicle(Vehicle vehicle) {
		try {
			IVehicleService ivs=new VehicleService();
			ivs.addVehicle(vehicle);
		}catch(SQLException| IOException|ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updatevehicle(Vehicle vehicle) {
		try {
			IVehicleService ivs=new VehicleService();
			ivs.updateVehicle(vehicle);
		}catch(SQLException| IOException|ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteVehicle(int vehicleId) {
		try {
			IVehicleService ivs=new VehicleService();
			ivs.removeVehicle(vehicleId);
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
	}
}