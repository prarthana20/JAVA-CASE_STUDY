
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import dao.AdminService;
import dao.IAdminService;
import dao.IVehicleService;
import dao.VehicleService;
import exception.InvalidInputException;
import model.Admin;
import model.Vehicle;

public class AdminController {
	
	public void updateAdmin() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter all your details ");
		System.out.println("please enter your first name:");
		String firstName=sc.next();
		System.out.println("please enter your last name:");
		String lastName=sc.next();
		System.out.println("Please enter your email:");
		String email=sc.next();
		System.out.println("Enter your phone number:");
		String phonenumber=sc.next();
		
		System.out.println("Enter your Username Remember!!! username should be unique and it will be used to login to your CarConnect account so please remember Username:");
		String usernamenew=sc.next();
		System.out.println("Enter your password:");
		String passwordnew=sc.next();
		System.out.println("Please enter your Role:");
		String role=sc.next();
		System.out.println("Enter your join date in yyyy-MM-dd format:");
		String joinDate=sc.next();
		Admin admin=new Admin(firstName,lastName,email,phonenumber,usernamenew,passwordnew,role,joinDate);
		try {
			IAdminService ics=new AdminService();
			ics.updateAdmin(admin);
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	public void getSelfDetailsForAdmin(String username) {
		try {
			IAdminService ias=new AdminService();
			Admin gotAdmin=ias.getAdminByUsername(username);
			if(gotAdmin!=null) {
				System.out.println("Your Id : "+gotAdmin.getAdminId());
				System.out.println("Your Name : "+gotAdmin.getFirstName()+" "+gotAdmin.getLastName());
				System.out.println("Your email: "+gotAdmin.getEmail());
				System.out.println("Your Phone number: "+gotAdmin.getPhoneNumber());
				System.out.println("Your Role: "+gotAdmin.getRole());
				System.out.println("Your Username: "+gotAdmin.getUsername());
				System.out.println("Your Password: "+gotAdmin.getPassword());
				System.out.println("Your Joining date: "+gotAdmin.getJoinDate());
			}
			else {
				System.out.println("No data for given username");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	public void deleteAdminAccount(int adminId) {
		try {
			IAdminService ias=new AdminService();
			ias.deleteAdmin(adminId);
		} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
	}
	


}