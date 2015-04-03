package sg.edu.nus.iss.models;




public class NonMember extends Customer
{
	/*
	 * created by Deepsha on 21stMarch 2015
	 * */
	
	private String annualSalesOffer;
	
	public NonMember (String customerName,String annualSalesOffer)
	{
		super(customerName);
		this.annualSalesOffer=annualSalesOffer;
		
	}

	public String getAnnualSalesOffer() {
		return annualSalesOffer;
	}

	public void setAnnualSalesOffer(String annualSalesOffer) {
		this.annualSalesOffer = annualSalesOffer;
	}
	
}
