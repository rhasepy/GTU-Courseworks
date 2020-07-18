/**
* This derived class from Employee
* Purpose of this class, differanciating authority
* And prevent code duplication
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW1 PART2
*/

public class Administrator extends Employee
{
	/**
	* Single constructor
	* @param name, will be assigned employee name
	* @param id, will be assigned employee id
	* @param pw, will be assigned employee pw
	*/
	public Administrator(String name, String id, String pw)
	{
		super(name, id, pw);
		super.setStatus("Administrator");
	}
}