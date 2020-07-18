public class Customer
{
	/**
	* Name of customer
	*/
	private String name;

	/**
	* identity number of customer
	*/
	private String identityNumber;

	/**
	* Two parameter consturctor
	* @param name, Name of customer
	* @param identityNumber, identity number of customer 
	*/
	public Customer(String name, String identityNumber)
	{
		setName(name);
		setIdentity(identityNumber);
	}
	
	/**
	* setter function to set name
	* @param x, be assigned to name of customer
	*/
	public void setName(String x) { name = x; }

	/**
	* getter function to get name
	* @return customer name
	*/
	public String getName() { return name; }

	/**
	* setter function to set idendtity number
	* @param x, be assigned to number of identity of customer
	*/
	public void setIdentity(String x) { identityNumber = x; }

	/**
	* getter function to get identity number
	* @return identity number of customer
	*/
	public String getIdentitiy() { return identityNumber; }

	/**
	* Override equals method
	* If the two person has same identity number in system
	* Two person is same person
	* @param o, taken object
	* @return true, if the identity numbers are same
	* @return false, if the identity number are not same
	*/
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Customer))
			return false;

		Customer test = (Customer) o;

		return this.getIdentitiy().equals(test.getIdentitiy());
	}

	/**
	* Override toString method
	* @return information about customer
	*/
	@Override
	public String toString()
	{
		return "Name: " +
				getName() + 
				" -- Identity Number: " +
				getIdentitiy() +
				"\n";
	}
}