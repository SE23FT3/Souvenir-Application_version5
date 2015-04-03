package sg.edu.nus.iss.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.MemberPanel;
import sg.edu.nus.iss.models.Discount;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.util.Constants;

public class CustomerManager implements Constants {

	/*  
	 *Created by Deepsha on 21St March2015 *
	 */

	
	
	private MainMenu mainMenu;
	private MemberPanel memberPanel;
	
	public CustomerManager(){}
	
	public CustomerManager(MainMenu mainMenu) throws IOException{
		this.mainMenu = mainMenu;
		memberPanel = new MemberPanel(this);

	}
	

	public ArrayList<Member> retrieveMemberDataFromFile() throws IOException{
		String dataofFile=null;
		Member member=null;
		ArrayList<Member> memberList=new ArrayList<Member>();;
		FileReader r=null;
		BufferedReader br=null;
		try {
			r=new FileReader(Constants.MEMBERSFILE);
			br=new BufferedReader(r);
			while((dataofFile=br.readLine())!=null){
				System.out.println(dataofFile);
				
				List<String> memberString = Arrays.asList(dataofFile.split(","));
				for(int z=0;z<=memberString.size();z++)
				{
					
					member=new Member();
					member.setCustomerName(memberString.get(0));
					member.setMemberId(memberString.get(1));
					member.setLoyaltyPoint(memberString.get(2));
					memberList.add(member);
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
	
		return memberList;
	}
	
	public void displayMemberFile(ArrayList memberList)
	{
		Iterator<Member> iterator = memberList.iterator();
		while (iterator.hasNext()) {
			Member m=iterator.next();
			System.out.println("Member Name:"+m.getCustomerName());
			System.out.println("Member ID:"+m.getMemberId());
			System.out.println("Loyalty Points:"+m.getLoyaltyPoint());
			
		}
	}
		
public boolean addNewMemberData(Member member) throws IOException {
		
		if(member!=null)
		{
			System.out.println("addNewMemberData");
			String lp="" + member.getLoyaltyPoint();
			String content=member.getCustomerName()+","+member.getMemberId()+","+ lp;
			System.out.println(content);
		
	        BufferedWriter bout = new BufferedWriter(new FileWriter(Constants.MEMBERSFILE,true));
	          
	         bout.append(content);
	         bout.newLine();
	         bout.close();						
		}
		return true;
	}
public ArrayList<Member> deleteMemberData(Member member) throws IOException
{
	ArrayList<Member> memberList=null;
	if(member!=null)
	{
	 memberList=retrieveMemberDataFromFile();
	
  
             
      //  displayMemberFile(memberList);
        BufferedWriter bout = new BufferedWriter(new FileWriter(Constants.MEMBERSFILE));
        Iterator<Member> iterator = memberList.iterator();
		while (iterator.hasNext()) {
			
			
			Member mem=iterator.next();
			if(mem.getMemberId().equalsIgnoreCase(member.getMemberId()))
			{
				System.out.println("Member has been removed successfully!!");
				memberList.remove(mem);
				break;
			}
			else
			{
				System.out.println("Data not found");
				
				
			}
			String line=mem.getCustomerName()+","+mem.getMemberId()+","+mem.getLoyaltyPoint();
			System.out.println(line);
			bout.write(line);
			bout.newLine();
			
		}    
		 System.out.println("After deletion:"+memberList.size());
		bout.close();						
	}
	return memberList;
}

public ArrayList<Member> searchDataAndDisplay(String data, String value) {
	// TODO Auto-generated method stub
	System.out.print("searchDataAndDisplay");

	ArrayList<Member> searchedlist=new ArrayList<Member>();
	ArrayList<Member> memberList = null;
	Member searchedMember;
	System.out.println("searchDataAndDisplay"+value+data);
	try {
		memberList = retrieveMemberDataFromFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      Iterator<Member> iterator = memberList.iterator();
			while (iterator.hasNext()) {							
				Member dis=iterator.next();
				if(data.equalsIgnoreCase("Member Name"))
				{
					
					if(value.equalsIgnoreCase(dis.getCustomerName())){
						searchedMember=new Member();
						searchedMember=dis;
						searchedlist.add(searchedMember);	
				
					}
				}
				else if(data.equalsIgnoreCase("Member ID"))
				{
					if(value.equalsIgnoreCase(dis.getMemberId())){
					
						searchedMember=new Member();
						searchedMember=dis;
					searchedlist.add(searchedMember);	
					}
				}
				else if(data.equalsIgnoreCase("Loyaly Points"))
				{
					
					if(value.equalsIgnoreCase(dis.getLoyaltyPoint())){
						searchedMember=new Member();
						searchedMember=dis;
						searchedlist.add(searchedMember);	
					System.out.println("matched lp");}
				}
				
			}
			
	return  searchedlist;

}

public MemberPanel getMemberPanel(){
	return memberPanel;
}

public void refresh() throws IOException {
	// TODO Auto-generated method stub
	mainMenu.refreshCustomerPanel();
	mainMenu.displayCustomerPanel();
}

public String getLoyaltyPointForMember(String memberID) {
	// TODO Auto-generated method stub
	String loyaltyPoint=null;
	try {
		ArrayList memberList = retrieveMemberDataFromFile();
		 Iterator<Member> iterator = memberList.iterator();
			while (iterator.hasNext()) {							
				Member mem=iterator.next();
				if(mem.getMemberId().equalsIgnoreCase(memberID)){
				loyaltyPoint=mem.getLoyaltyPoint();
				}
			}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return loyaltyPoint;
}


public void updateLoyaltyPoint(String memberID,String loyaltyPoints, int points) throws IOException
{
	Member member=null;
	int loyaltyPointsRedeemed;
	ArrayList<Member> memberList=null;
	if(memberID!=null)
	{
		 
		
		System.out.println("updateLoyaltyPoint"+loyaltyPoints);
		int lp=Integer.parseInt(loyaltyPoints);
		if(lp== -1)
		{
			System.out.println("Nem member");
			 loyaltyPointsRedeemed=0;
			loyaltyPointsRedeemed=points;
		}
		else
		{
			System.out.println("old member");
			 loyaltyPointsRedeemed=Integer.parseInt(loyaltyPoints);
			loyaltyPointsRedeemed=loyaltyPointsRedeemed+points;
		}
		try {
			memberList=retrieveMemberDataFromFile();
			System.out.println("memberList:size:"+memberList.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(memberList!=null)
		{
			BufferedWriter bufferedout = new BufferedWriter(new FileWriter(Constants.MEMBERSFILE));
		 Iterator<Member> iterator = memberList.iterator();
			while (iterator.hasNext()) {							
				Member mem=iterator.next();
				if(mem.getMemberId().equalsIgnoreCase(memberID))
				{
					System.out.println("matched new loyaltyPointsRedeemed:"+loyaltyPointsRedeemed);
					mem.setLoyaltyPoint(String.valueOf(loyaltyPointsRedeemed));
				}
				String line=mem.getCustomerName()+","+mem.getMemberId()+","+mem.getLoyaltyPoint();
				System.out.println(line);
				bufferedout.write(line);
				bufferedout.newLine();
				
			}
			bufferedout.close();
	}
		
	}
}

}

