package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import sg.edu.nus.iss.models.Category;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.service.CustomerManager;

public class AddCategoryTestCase {

	CategoryManager cm=new CategoryManager();
	   @Test
	   public void addNewCategoryDataTest() throws IOException {
	      System.out.println("Inside addNewCategoryDataTest()");
	     Category category=new Category("BOO","BOOKS");
	      //assertTrue(cm.addCategory(category));
	   }

}
