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
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.service.CustomerManager;

public class WriteMemberExcelFile
{
	public void write()
	{
		FileOutputStream fileOut = null;
		try
		{
			fileOut = new FileOutputStream("E://member.xls");
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
		cellA1.setCellValue("Customer Name");
		
		HSSFCell cellB1 = row1.createCell((short) 1);
		cellB1.setCellValue("Member Id");
		
		HSSFCell cellC1 = row1.createCell((short) 2);
		cellC1.setCellValue("Loyalty Points");
	
		Map<String,ArrayList> memberdata=new HashMap<String,ArrayList>();
		CustomerManager cm=new CustomerManager();
		Member m=new Member();
		
		try 
		{
			ArrayList<Member> mem=cm.retrieveMemberDataFromFile();
			memberdata.put("Member", mem);
			int rownum=1;			 
			for (String key :memberdata.keySet()) 
			{
	            List<Member> objList = memberdata.get(key);//excelHolder is my map
	            for (Member obj : objList)
	            {
	            	int cellnum =0;
	            	Row row11 = worksheet.createRow(rownum++);
	            	Cell cell = row11.createCell(cellnum++);
	            	cell.setCellValue(obj.getCustomerName());
	            	
	            	Cell cell1 = row11.createCell(cellnum++);
	            	cell1.setCellValue(obj.getMemberId());
	               
	               	Cell cell2= row11.createCell(cellnum++);
	            	cell2.setCellValue(obj.getLoyaltyPoint());
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
