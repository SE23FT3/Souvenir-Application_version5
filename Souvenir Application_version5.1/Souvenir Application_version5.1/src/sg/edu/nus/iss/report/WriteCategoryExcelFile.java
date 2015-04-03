package sg.edu.nus.iss.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import sg.edu.nus.iss.models.Category;
import sg.edu.nus.iss.service.CategoryManager;


public class WriteCategoryExcelFile
{
	public void write()
	{
		FileOutputStream fileOut = null;
		try
		{
			fileOut = new FileOutputStream("E://category1.xls");
					} 
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

		// index from 0,0... cell A1 is cell(0,0)
		HSSFRow row1 = worksheet.createRow((short) 0);

		HSSFCell cellA1 = row1.createCell((short) 0);
		cellA1.setCellValue("Category code");
		
		HSSFCell cellB1 = row1.createCell((short) 1);
		cellB1.setCellValue("Category name");
	
		Map<String,ArrayList> categorydata=new HashMap<String,ArrayList>();
		CategoryManager cm=new CategoryManager();
		Category c=new Category();
		
		try 
		{
			ArrayList<Category> categ=cm.retrieveCategoryDataFromFile();
			categorydata.put("Category", categ);
			int rownum=1;			 
			for (String key :categorydata.keySet()) 
			{
	            List<Category> objList = categorydata.get(key);//excelHolder is my map
	            for (Category obj : objList)
	            {
	            	int cellnum =0;
	            	Row row11 = worksheet.createRow(rownum++);
	            	Cell cell = row11.createCell(cellnum++);
	            	cell.setCellValue(obj.getCategoryCode());
	            	Cell cell1 = row11.createCell(cellnum++);
	               	cell1.setCellValue(obj.getCategoryName());
	        
	            }
		} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		try {
			workbook.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
