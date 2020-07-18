/**
* Company System interface for another companies
* But I will use only cargo companies
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version Homework1 Part2
*/

interface CompanySystem
{
	/**
	* Firstly, the system panel opens...
	*/
	void systemPanel();

	/**
	* Membership is created
	*/
	void createNewUser();

	/**
	* Login panel
	*/
	void login();

	/**
	* Is a panel that asks for the actions to be taken. 
	* According to the authority, he can either do the transactions or not.
	*/
	void operationPanel();

	/**
	* @param o, taken employee
	* The method push taken employee to all employees datas
	*/
	void addEmployeeSystem(Employee o);

	/**
	* @param o, taken employee
	* The method remove taken employee from all employees datas
	*/
	void removeEmployeeSystem(Employee o);

	/**
	* remove customer from the system
	* @param whichCustomer, which customer will be removed
	*/
	void removeCustomer(Customer whichCustomer);

	/**
	* Adds customer to the system
	* @param willAdd, object to add.
	*/
	void addCustomer(Customer willAdd);
}