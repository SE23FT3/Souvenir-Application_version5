package sg.edu.nus.iss.models;



import java.io.Serializable;
import java.util.Date;

public class Discount implements Serializable{

	/*
	 * created by Bani on 21st March 2015*
	 */
	private String discountCode;
	private String discountDescription;
	private String startDate;
	private String durationOfDiscount;
	private String discountPercentage;
	private String applicability;
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Discount(String discountCode, String discountDescription,
			String startDate, String durationOfDiscount,
			String discountPercentage, String applicability) {
		super();
		this.discountCode = discountCode;
		this.discountDescription = discountDescription;
		this.startDate = startDate;
		this.durationOfDiscount = durationOfDiscount;
		this.discountPercentage = discountPercentage;
		this.applicability = applicability;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getDiscountDescription() {
		return discountDescription;
	}
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
	
	public String getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getApplicability() {
		return applicability;
	}
	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDurationOfDiscount() {
		return durationOfDiscount;
	}
	public void setDurationOfDiscount(String durationOfDiscount) {
		this.durationOfDiscount = durationOfDiscount;
	}
	
	
	
}
