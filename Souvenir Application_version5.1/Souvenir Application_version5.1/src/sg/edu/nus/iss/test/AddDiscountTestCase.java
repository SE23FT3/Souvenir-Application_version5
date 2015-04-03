package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import sg.edu.nus.iss.models.Discount;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.DiscountManager;

public class AddDiscountTestCase {


	DiscountManager dm=new DiscountManager();
	   @Test
	   public void addNewDiscountDataTest() throws IOException {
	      System.out.println("Inside addNewDiscountDataTest()");
	      Discount discount=new Discount("NEW_YEAR_DISCOUNT","Lunar Festival Discount","2015-02-03","14", "10", "A");
	      assertTrue(dm.addNewDiscountData(discount));
	   }
	  

}
