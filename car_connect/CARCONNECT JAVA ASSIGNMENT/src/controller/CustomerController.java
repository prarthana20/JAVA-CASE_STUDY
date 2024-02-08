package controller;

import dao.CustomerService;
import dao.ICustomerService;
import dao.IReservationService;
import dao.IVehicleService;
import dao.ReservationService;
import dao.VehicleService;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;
import model.Customer;
import model.Reservation;
import model.Vehicle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
	public void updateCustomer() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your details to update");
		System.out.println("please enter your first name:");
		String firstName=sc.next();
		System.out.println("please enter your last name:");
		String lastName=sc.next();
		System.out.println("Please enter your email:");
		String email=sc.next();
		System.out.println("Enter your phone number:");
		String phonenumber=sc.next();
		System.out.println("Please enter your address:");
		String address=sc.next();
		System.out.println("Enter your Username Remember!!! username should be unique and it will be used to login to your CarConnect account so please remember Username:");
		String username=sc.next();
		System.out.println("Enter your password:");
		String passwordnew=sc.next();
		System.out.println("Enter today date in yyyy-MM-dd format:");
		String date=sc.next();
		Customer customer=new Customer(firstName,lastName,email,phonenumber,address,username,passwordnew,date);
		try {
			ICustomerService ics=new CustomerService();
			ics.updateCustomer(customer);
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	public void getSelfDetails(String username) {
		try {
			ICustomerService ics=new CustomerService();
			Customer gotCustomer=ics.getCustomerByUsername(username);
			if(gotCustomer!=null) {
				System.out.println("Your Id : "+gotCustomer.getCustomerId());
				System.out.println("Your Name : "+gotCustomer.getFirstName()+" "+gotCustomer.getLastName());
				System.out.println("Your email: "+gotCustomer.getEmail());
				System.out.println("Your Phone number: "+gotCustomer.getPhoneNumber());
				System.out.println("Your Adress: "+gotCustomer.getAddress());
				System.out.println("Your Username: "+gotCustomer.getUserName());
				System.out.println("Your Password: "+gotCustomer.getPassword());
				System.out.println("Your Registration date: "+gotCustomer.getRegistrationDate());
			}
			else {
				System.out.println("No data for given username");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	public void deleteAccount(int customerId) {
		try {
			ICustomerService ics=new CustomerService();
			ics.deleteCustomer(customerId);
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public void getReservationsByCustomer(int customerId) {
		try {
			IReservationService irs=new ReservationService();
			List<Reservation> reservations=irs.getReservationsByCustomerId(customerId);
			if(reservations.isEmpty()) {
				System.out.println("There are no reservations");
			}
			else {
				for(Reservation res:reservations) {
					System.out.println("Reservation Id: "+res.getReservationId());
					System.out.println("Vehicle Id: "+res.getVehicleId());
					System.out.println("Start date: "+res.getStartDate());
					System.out.println("End date: "+res.getEndDate());
					System.out.println("Total cost: "+res.getTotalCost());
					System.out.println("Status: "+res.getStatus());
					
				}
			}
			
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
			
			
		
	}
	

}