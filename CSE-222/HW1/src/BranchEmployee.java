/**
* This derived class from Employee
* Purpose of this class, differanciating authority
* And prevent code duplication
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW1 part 2
*/

public class BranchEmployee extends Employee
{
	/**
	* Name of Branch
	*/
	private String branchName;

	/**
	* Single constructor
	* @param name, will be assigned employee name
	* @param id, will be assigned employee id
	* @param pw, will be assigned employee pw
	*/
	public BranchEmployee(String name, String id, String pw)
	{
		super(name, id, pw);
		super.setStatus("Branch Employee");
		setNameOfBranch("NONE BRANCH");
	}

	/**
	* setter method of branch name
	* @param x, assigned to branch name
	*/
	public void setNameOfBranch(String x){ this.branchName = x; }

	/**
	* getter method of branch name
	* @return branchname
	*/
	public String getNameOfBranch(){ return this.branchName; }

	/**
	* Overriding toString method
	* In addition to status information and branch name from employee
	* @return, İnformation of branch employee
	*/
	@Override
	public String toString()
	{
		return "\n---------------------\n" +
				"Name: " + super.getName() + " - " +
				"ID: " + super.getUserID() + " - " +
				"Status: " + super.getStatus() + " - " +
				"Branch: " + this.getNameOfBranch() + "\n" + 
				"---------------------\n";
	}
}