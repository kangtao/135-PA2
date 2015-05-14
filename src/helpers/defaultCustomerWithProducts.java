package helpers;

public class defaultCustomerWithProducts 
{
	private String cname;
	
	private String pname;
	
	private int totalMoney;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param count
	 */
	public defaultCustomerWithProducts(String cname, String pname, int totalMoney) 
	{
		this.cname = cname;
		this.pname = pname;
		this.totalMoney = totalMoney;
	}


	/**
	 * @return the cname
	 */
	public String getCname() 
	{
		return cname;
	}
	
	/**
	 * @return the name
	 */
	public String getPname() {
		return pname;
	}


	/**
	 * @return total money
	 */
	public int getTotalMoney() {
		return totalMoney;
	}
}


