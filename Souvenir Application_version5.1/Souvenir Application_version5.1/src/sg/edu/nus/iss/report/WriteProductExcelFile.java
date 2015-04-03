package sg.edu.nus.iss.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.ProductManager;

public class WriteProductExcelFile
{
	public void write()
	{
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("D://product1.xls");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

		// index from 0,0... cell A1 is cell(0,0)
		HSSFRow row1 = worksheet.createRow((short) 0);

		HSSFCell cellA1 = row1.createCell((short) 0);
		cellA1.setCellValue("Product Id");
		
		HSSFCell cellB1 = row1.createCell((short) 1);
		cellB1.setCellValue("Product Name");
		
		HSSFCell cellC1 = row1.createCell((short) 2);
		cellC1.setCellValue("Product Description");

		HSSFCell cellD1 = row1.createCell((short) 3);
		cellD1.setCellValue("Quantity");
		
		HSSFCell cellE1 = row1.createCell((short) 4);
		cellE1.setCellValue("Product Price");
		
		HSSFCell cellF1 = row1.createCell((short) 5);
		cellF1.setCellValue("Bar code");
		
		HSSFCell cellG1 = row1.createCell((short) 6);
		cellG1.setCellValue("Reorder Threshhold");
		
		HSSFCell cellH1 = row1.createCell((short) 7);
		cellH1.setCellValue("Order Quantity");
		
		Map<String,ArrayList> productdata=new HashMap<String,ArrayList>();
		ProductManager pm=new ProductManager();
		//pm.show();
		Product p=new Product();
		try 
		{
			ArrayList<Product> prod=pm.retrieveProductDataFromFile();
			productdata.put("Product", prod);
			int rownum=1;			 
			for (String key :productdata.keySet()) 
			{
	            List<Product> objList = productdata.get(key);//excelHolder is my map
	            for (Product obj : objList)
	            {
	            	int cellnum =0;
	            	Row row11 = worksheet.createRow(rownum++);
	            	Cell cell = row11.createCell(cellnum++);
	            	cell.setCellValue(obj.getProductId());
	            	Cell cell1 = row11.createCell(cellnum++);
	               	cell1.setCellValue(obj.getProductName());
	               	Cell cell2=row11.createCell(cellnum++);
	               	cell2.setCellValue(obj.getProductDescription());
	               	Cell cell3=row11.createCell(cellnum++);
	               	cell3.setCellValue(obj.getQuantityAvaliable());
	               	Cell cell4=row11.createCell(cellnum++);
	               	cell4.setCellValue(obj.getProductPrice());
	               	Cell cell5=row11.createCell(cellnum++);
	               	cell5.setCellValue(obj.getBarCode());
	               	Cell cell6=row11.createCell(cellnum++);
	               	cell6.setCellValue(obj.getReorderThreshold());
	               	Cell cell7=row11.createCell(cellnum++);
	               	cell7.setCellValue(obj.getOrderQuantity());	               	
	            }
			}
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	

