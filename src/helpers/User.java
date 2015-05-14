package helpers;

public class User 
{

	private int id;
	
	private String name;
	
	private String state;
	
	private int age;
	
	
	public User(int id, String name, String state,int age) 
	{
		this.id = id;
		this.name = name;
		this.state = state;
		this.age = age;
	}

	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * @return the state id
	 */
	public String getState() 
	{
		return state;
	}

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}


	/**
	 * @return the price
	 */
	public int getAge() 
	{
		return age;
	}
	

}
