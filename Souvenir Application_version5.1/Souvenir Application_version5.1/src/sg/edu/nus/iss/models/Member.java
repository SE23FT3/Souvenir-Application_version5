package sg.edu.nus.iss.models;

import java.io.Serializable;




public class Member extends Customer implements Serializable{

	/*
	 * created by Deepsha on 21stMarch 2015
	 * */
	private String memberId;
	private String loyaltyPoint;
	
	public Member()
	{
		
	}
	public Member(String customerName,String memberId,String loyaltyPoint)
	{
		super(customerName);
		this.memberId=memberId;
		this.loyaltyPoint=loyaltyPoint;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getLoyaltyPoint() {
		return loyaltyPoint;
	}
	public void setLoyaltyPoint(String loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}
	
	
	
}
