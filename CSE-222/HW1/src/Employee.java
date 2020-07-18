/**
* Employee my abstract class
* This clas has not contain abstract method because
* there is no membership only employee, classes derived from the employee have memberships.
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW1 part 2
*/
public abstract class Employee
{
	/**
	* Employee status
	*/
	private String status;

	/**
	* Employee name
	*/
	private String name;

	/**
	* Employee id
	*/
	private String user_id;

	/**
	* Employee password
	*/
	private String password;

	/**
	* Three parameter constructor
	* @param name, Employee name
	* @param id, Employee id
	* @param password, Employee password
	*/
	public Employee(String name, String id, String password)
	{
		setName(name);
		setUserID(id);
		setPassword(password);
		setStatus("NONE STATUS");
	}

	/**
	* @param name, be assigned employee name
	*/
	public void setName(String name) { this.name = name; }

	/**
	* @param id, be assigned employee id
	*/
	public void setUserID(String id) { this.user_id = id; }

	/**
	* @param pw, be assigned employee password
	*/
	public void setPassword(String pw) { this.password = pw; }

	/**
	* @param x, be assigned to status
	*/
	public void setStatus(String x) { this.status = x; }

	/**
	* @return employee password
	*/
	public String getPassword() { return this.password; }

	/**
	* @return employee id
	*/
	public String getUserID() { return this.user_id; }

	/**
	* @return employee name
	*/
	public String getName() { return this.name; }

	/**
	* @return status of employee
	*/
	public String getStatus() { return this.status; }

	/**
	* @return string of employee information without password 
	*/
	@Override
	public String toString()
	{
		return "\n---------------------\n" +
				"Name: " + getName() + " - " +
				"ID: " + getUserID() + " - " +
				"Status: " + getStatus() + "\n" +
				"---------------------\n";
	}

	/**
	* @param o, will be compared object
	* @return true, o and this the same object (according to id)
	* @return false, o and this the not same object (according to id)
	*/
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Employee))
			return false;

		Employee test = (Employee) o;

		return (this.getUserID().equals(test.getUserID()));
	}
}
