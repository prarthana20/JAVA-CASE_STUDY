package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import controller.AdminController;
import controller.CustomerController;
import controller.ReservationController;
import controller.VehicleController;
import dao.AdminService;
import dao.CustomerService;
import dao.IAdminService;
import dao.ICustomerService;
import dao.IReservationService;
import dao.IVehicleService;
import dao.ReportGenerator;
import dao.ReservationService;
import dao.VehicleService;
import exception.AuthenticationException;
import exception.InvalidInputException;
import exception.ReservationException;
import exception.VehicleNotFoundException;
import model.Admin;
import model.Customer;
import model.Reservation;
import model.Vehicle;

public class Main {
	
	public static void main(String args[])  {
		int choice;
		do {
			Scanner sc=new Scanner(System.in);
			int choiceAfterAuth;
			
			System.out.println("----------CarConnect----------");
			System.out.println("Enter 1 if you are a Customer");
			System.out.println("Enter 2 if you are an Admin");
			System.out.println("Enter 3 to exit the Application");
			
					
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				try {
				System.out.println("New Customer Enter 1");
				System.out.println("Existing Customer Enter 2");
				int neworold=sc.nextInt();
				switch(neworold) {
				case 1:
					System.out.println("Enter your details ");
					System.out.println("Enter First Name:");
					String firstName=sc.next();
					System.out.println("Enter Last Name:");
					String lastName=sc.next();
					System.out.println("Enter your E-mail:");
					String email=sc.next();
					System.out.println("Enter Phone Number:");
					String phonenumber=sc.next();
					System.out.println("Enter your Address:");
					String address=sc.next();
					System.out.println("Enter a unique Username:");
					String usernamenew=sc.next();
					System.out.println("Enter your password:");
					String passwordnew=sc.next();
					System.out.println("Enter Today's date in yyyy-MM-dd format:");
					String date=sc.next();
					Customer customernew=new Customer(firstName,lastName,email,phonenumber,address,usernamenew,passwordnew,date);
					ICustomerService ics=new CustomerService();
					ics.registerCustomer(customernew);
					break;
				case 2:
					System.out.println("Enter your username:");
					String username=sc.next();
					CustomerService cs=new CustomerService();
					Customer customer=cs.getCustomerByUsername(username);
					System.out.println("Enter your password:");
					String password=sc.next();
					if(customer.authenticate(password)) {
						System.out.println("Hi "+customer.getFirstName()+" "+customer.getLastName()+" welcome to CarConnect");
						System.out.println("Please tell us what would you like to do");
						do {
							System.out.println("Enter");
							System.out.println("1.Update");
							System.out.println("2.Get self details");
							System.out.println("3.delete your account");
							System.out.println("4.Check available vehicles to rent");
							System.out.println("5.Get your reservations");
							System.out.println("6.Reserve a vehicle");
							System.out.println("7.Update your Reservation");
							System.out.println("8.Cancel your Reservation");
							System.out.println("9.Logout");
							choiceAfterAuth=sc.nextInt();
							CustomerController customercontroller=new CustomerController();
							ReservationController reservationcontroller = new ReservationController();
							VehicleController vehiclecontroller = new VehicleController();
							switch(choiceAfterAuth){
							case 1:
								
								customercontroller.updateCustomer();
								break;
							case 2:
								
								customercontroller.getSelfDetails(customer.getUserName());
								break;
							case 3:
								customercontroller.deleteAccount(customer.getCustomerId());
								break;
							case 4:
								vehiclecontroller.availableVehicles();
								break;
							case 5:
								customercontroller.getReservationsByCustomer(customer.getCustomerId());
								break;
							case 6:
								System.out.println("Enter Vehicle Id:");
								int vehicleId=sc.nextInt();
								VehicleService vs=new VehicleService();
								Vehicle vehicle=vs.getVehicleById(vehicleId);
								System.out.println("Enter Start Date:");
								String startDate=sc.next();
								System.out.println("Enter End Date:");
								String endDate=sc.next();
								Reservation res=new Reservation(startDate,endDate);
								double totalCost=res.calculateTotalCost(vehicle.getDailyRate());
								System.out.println("Enter status whether Confirm,pending,completed:");
								String status=sc.next();
								Reservation reservation=new Reservation(customer.getCustomerId(),vehicleId,startDate,endDate,totalCost,status);
								reservationcontroller.reserveAVehicle(reservation);
								break;
							case 7:
								
								System.out.println("Enter reservation Id:");
								int reservationIdForUpdate =sc.nextInt();
								System.out.println("Enter Vehicle Id:");
								int vehicleIdForUpdate=sc.nextInt();
								VehicleService vs1=new VehicleService();
								Vehicle vehicleForUpdate=vs1.getVehicleById(vehicleIdForUpdate);
								System.out.println("Enter Start Date:");
								String startDateForUpdate=sc.next();
								System.out.println("Enter End Date:");
								String endDateForUpdate=sc.next();
								Reservation res1=new Reservation(startDateForUpdate,endDateForUpdate);
								double totalCostForUpdate=res1.calculateTotalCost(vehicleForUpdate.getDailyRate());
								System.out.println("Enter status whether Confirm,pending,completed:");
								String statusForUpdate=sc.next();
								Reservation reservationForUpdate=new Reservation();
								reservationForUpdate.setReservation1(reservationIdForUpdate, vehicleIdForUpdate, startDateForUpdate, endDateForUpdate, totalCostForUpdate, statusForUpdate);
								reservationcontroller.updateReservation(reservationForUpdate);
								break;
							case 8:
								System.out.println("Enter reservation Id:");
								int reservationIdForDelete =sc.nextInt();
								reservationcontroller.deleteReservation(reservationIdForDelete);
								break;
								
								
							case 9:
								System.out.println("You have successfully logged out!");
								break;
							default:
								System.out.println("Wrong input please enter a number in valid range of 1-9");
								break;
							}
						}while(choiceAfterAuth!=9);
					}
					else {
						System.out.println("Wrong password please try again!!!");
					}
					break;
				default:
					System.out.println("Wrong inut please give in given range of inputs!!!");
					break;
					
				}
				} catch (ClassNotFoundException | SQLException | IOException|InvalidInputException|AuthenticationException e) {
						System.out.println(e.getMessage());
					
				}
				break;
			
			case 2:
				try {
					System.out.println("Hi Admin ");
					System.out.println("Are you new or do you have Carconnect Admin account");
					System.out.println("If you are new then press 1 if you are already an admin then press 2");
					int neworold=sc.nextInt();
					switch(neworold) {
						case 1:
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
							Admin adminnew=new Admin(firstName,lastName,email,phonenumber,usernamenew,passwordnew,role,joinDate);
							IAdminService ias=new AdminService();
							ias.registerAdmin(adminnew);
							break;
						
						case 2:
							System.out.println("Please enter your username:");
							String username=sc.next();
							AdminService as=new AdminService();
							Admin admin=as.getAdminByUsername(username);
							System.out.println("Please enter your password:");
							String password=sc.next();
							if(admin.authenticate(password)) {
								System.out.println("Hi "+admin.getFirstName()+" "+admin.getLastName()+" welcome to CarConnect");
								System.out.println("Please tell us what would you like to do");
								do {
									System.out.println("Enter");
									System.out.println("1.Update");
									System.out.println("2.Get self details");
									System.out.println("3.delete your account");
									System.out.println("4.Check available vehicles ");
									System.out.println("5.Add Vehicle");
									System.out.println("6.Update vehicle");
									System.out.println("7.Remove vehicle");
									System.out.println("8.Generate reservation report");
									System.out.println("9.Generate vehicle report");
									System.out.println("10.Logout");
									choiceAfterAuth=sc.nextInt();
									VehicleController vehiclecontroller=new VehicleController();
									AdminController  admincontroller =new AdminController();
									switch(choiceAfterAuth) {
									case 1:
										admincontroller.updateAdmin();
										break;
									case 2:
										
										admincontroller.getSelfDetailsForAdmin(admin.getUsername());
										break;
									case 3:
										admincontroller.deleteAdminAccount(admin.getAdminId());
										break;
									case 4:
										vehiclecontroller.availableVehicles();
										break;
									case 5:
										System.out.println("Enter vehicle details");
										System.out.println("Enter Vehicle model");
										String model=sc.next();
										System.out.println("Enter Vehicle make");
										String make=sc.next();
										System.out.println("Enter Vehicle year");
										String year=sc.next();
										System.out.println("Enter Vehicle color");
										String color=sc.next();
										System.out.println("Enter Vehicle Registration number:");
										String regnumber=sc.next();
										System.out.println("Enter vehicle availablity:");
										boolean availability=sc.nextBoolean();
										System.out.println("Enter vehicle Daily Rate:");
										double dailyRate=sc.nextDouble();
										Vehicle vehicle=new Vehicle(model,make,year,color,regnumber,availability,dailyRate);
										
										vehiclecontroller.addVehicle(vehicle);
										break;
									case 6:
										System.out.println("Enter every detail to update vehicle");
										System.out.println("Enter vehicle Id:");
										int vehicleid=sc.nextInt();
										System.out.println("Enter Vehicle model");
										String modelup=sc.next();
										System.out.println("Enter Vehicle make");
										String makeup=sc.next();
										System.out.println("Enter Vehicle year");
										String yearup=sc.next();
										System.out.println("Enter Vehicle color");
										String colorup=sc.next();
										System.out.println("Enter vehicle availablity:");
										boolean availabilityup=sc.nextBoolean();
										System.out.println("Enter vehicle Daily Rate:");
										double dailyRateup=sc.nextDouble();
										Vehicle vehicleup=new Vehicle(vehicleid,modelup,makeup,yearup,colorup,availabilityup,dailyRateup);
										vehiclecontroller.updatevehicle(vehicleup);
										break;
									case 7:
										System.out.println("Enter Vehicle Id:");
										int vehicleIdForDelete =sc.nextInt();
										vehiclecontroller.deleteVehicle(vehicleIdForDelete);
										break;
									case 8:
										ReportGenerator rg=new ReportGenerator();
										rg.generateReservationReport();
										break;
									case 9:
										ReportGenerator rgv=new ReportGenerator();
										rgv.generateVehicleReport();
										break;
									case 10:
										System.out.println("Logging out....");
										System.out.println("Successfully logged out!");
										break;
									default:
										System.out.println("Wrong input please enter in given range!!!");
										break;
									}
									
								}while(choiceAfterAuth!=10);
							}
							else {
								System.out.println("Wrong password please try again!!!");
							}
							break;
			
						default:
							System.out.println("Wrong input please enter in given range!!!");
							break;
			}
				
				}catch (ClassNotFoundException | SQLException | IOException|InvalidInputException|AuthenticationException e) {
					System.out.println(e.getMessage());
					
				}
				
				break;	
				
			case 3:
				System.out.println("Exiting for application....");
				System.out.println("You have succesfully exited from application");
				break;
			default:
				System.out.println("Wrong inut please give in given range of inputs!!!");
				break;
			
		}
			
			}while(choice!=3);
		
		}
		
}