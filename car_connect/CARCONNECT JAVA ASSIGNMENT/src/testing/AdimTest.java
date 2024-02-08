//package testing;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import controller.AdminController;
//
//
//public class AdminTest {
//	AdminController adminController;
//	@Before
//	public void setUp() {
//		 adminController=new AdminController();
//	}
//	
//	@Test
//	public void testCustomerById() {
//		assertEquals(2, adminController.getIdFromAdmin());
//	}
//	
//	
//	
//	@After
//	public void tearDown() {
//		adminController=null;
//	}
//}