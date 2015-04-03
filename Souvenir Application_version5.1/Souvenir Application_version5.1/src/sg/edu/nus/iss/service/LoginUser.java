package sg.edu.nus.iss.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sg.edu.nus.iss.gui.LoginPanel;
import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.models.StoreKeeper;
import sg.edu.nus.iss.util.Constants;

public class LoginUser 
{

	BufferedWriter bw=null;
	ArrayList<String> a=new ArrayList<String>();
	ArrayList<String> list=new ArrayList<String>();
	private JTextField usernamefield;
	private JTextField passwordfield;
	private String line;
	private String replacement;
	private String part2;
	private StoreManager manager;
	private MainMenu mainMenu;
	private int j;
	private ArrayList<StoreKeeper> storeKeeperList;
	private LoginPanel loginPanel;
	
	public LoginUser(StoreManager manager) throws IOException{
		this.manager = manager;
		storeKeeperList=retrieveStoreKeeperDataFromFile();
		System.out.print(retrieveStoreKeeperDataFromFile());
	}
	public ArrayList<StoreKeeper> retrieveStoreKeeperDataFromFile() throws IOException{
		String dataofFile=null;
		StoreKeeper storeKeeper=null;
		ArrayList storeKeeperList=new ArrayList<StoreKeeper>();;
		FileReader r=null;
		BufferedReader br=null;
		try {
			r=new FileReader(Constants.STOREKEEPERSFILE);
			br=new BufferedReader(r);
			while((dataofFile=br.readLine())!=null){
				System.out.println(dataofFile);
				
				List<String> storeKeeperString = Arrays.asList(dataofFile.split(","));
				for(int z=0;z<=storeKeeperString.size();z++)
				{
					
					storeKeeper=new StoreKeeper();
					storeKeeper.setUsername(storeKeeperString.get(0));
					storeKeeper.setPassword(storeKeeperString.get(1));
					storeKeeperList.add(storeKeeper);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch b

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			br.close();
		}
	return storeKeeperList;
	}
	
	public boolean validatelogin(String uname,String pwd) throws IOException
	{
		boolean result=false;
			j =0;
	        Iterator<StoreKeeper> i = storeKeeperList.iterator();
	        while (i.hasNext()) 
			{	
	        	StoreKeeper dis = i.next();	
	    	  if((uname.equalsIgnoreCase(dis.getUsername().toString()))&&(pwd.equalsIgnoreCase(dis.getPassword().toString())))
				{	
	    		  result = true;
				}
	        	j++;
	        	break;
	    	  }
		return result;
      }

	public boolean changePassword(String uname,String oldpwd, String newpwd)
	{
		boolean result=false;
		try
		{
			FileReader fr=new FileReader(Constants.STOREKEEPERSFILE);
			BufferedReader br = new BufferedReader(fr);
			while((line=br.readLine())!=null)
			{
				System.out.println(line);
				String parts[]=line.split(",");
				String part1=parts[0];
				String part2=parts[1];
				if(part1.equalsIgnoreCase(uname))
					{
						if(part2.equals(oldpwd)){
							if(newpwd.length()!=0){
							FileWriter fw=new FileWriter("./data/Storekeepers.dat");
							fw.write(line.replace(part2, newpwd));
							fw.close();
							JOptionPane.showMessageDialog(null, "Change password successfully!");
							result = true;
							break;}
							else{
								JOptionPane.showMessageDialog(null, "New password can not be empty!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Old password is wrong!");
						}			
					}
				else{
					JOptionPane.showMessageDialog(null, "Cannot find the user!");
				}

			}
			fr.close();

		} 
		catch (FileNotFoundException e)
		{
		  	e.printStackTrace();
		}			

		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return result;
		
	}
	public void addUser(String uname, String rand) 
	{
		FileWriter fw = null;
		try
		{
			String data=uname+","+rand;
			fw=new FileWriter("./data/Storekeepers.dat",true);
			bw=new BufferedWriter(fw);
			bw.newLine();
			bw.write(data);
			bw.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				fw.close();
			}
			catch (IOException e)
			{			
				e.printStackTrace();
			}
		}
	}

}