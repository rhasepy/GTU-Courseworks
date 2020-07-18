/**
* This derived class from Employee
* Purpose of this class, differanciating authority
* And prevent code duplicaton
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW1 part 2
*/
public class TransportationPersonnel extends Employee
{
	/**
	* Single constructor
	* @param name, will be assigned employee name
	* @param id, will be assigned employee id
	* @param pw, will be assigned employee pw
	*/
	public TransportationPersonnel(String name, String id, String pw)
	{
		super(name, id, pw);
		super.setStatus("Transportation Personnel");
	}
}