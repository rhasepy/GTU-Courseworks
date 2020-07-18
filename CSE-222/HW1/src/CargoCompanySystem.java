/**
* Main System class for cargo company
* Transactions made receive approval from this class according to the process.
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version Homework 1 part 2
*/

import java.util.ArrayList;
import java.util.Scanner;

public class CargoCompanySystem implements CompanySystem
{
	/**
	* System has general employee what is employee don't know (admin, branchemploye etc...)
	*/
	private ArrayList <Employee> company_employees;

	/**
	* System has branches
	*/
	private ArrayList <Branch> branches;

	/**
	* Cargo company has customers
	*/
	private ArrayList <Customer> customers;

	/**
	* System has current employee to **understand who is using the system**
	*/
	private Employee currentEmployee;

	/**
	* No parameter constructor
	* Initialize fields that be system has
	*/
	public CargoCompanySystem()
	{
		company_employees = new ArrayList<Employee>();
		branches = new ArrayList<Branch>();
		customers = new ArrayList<Customer>();
	}

	/**
	* Implements systemPanel from CompanySystem
	*/
	public void systemPanel()
	{
		String choice;
		int val = 0;

		do
		{
			System.out.println(
			"1) Create new user\n" +
			"2) Employee Login\n" +
			"3) Customer login\n" +
			"4) Exit\n"
			);

			Scanner in = new Scanner(System.in);
			choice = in.nextLine();

			try{

				val = Integer.parseInt(choice);

			}catch(Exception e){

				System.out.println("\nInvalid choice...");

			}

			switch(val)
			{
				case 1:
					createNewUser();
				break;

				case 2:
					login();
				break;

				case 3:
					customer_panel();
				break;

				case 4:
					System.out.println("The program turning off...");
				break;

				default:
					System.out.println("Please enter number between 1 and 3...");
				break;
			}

		}while(val != 4);
	}

	/**
	* Implements createNewUser from CompanySystem
	*/
	public void createNewUser()
	{
		Scanner in = new Scanner(System.in);

		String choice;
		int val = 0;

		System.out.println("Enter your name: ");
		String name = in.nextLine();

		System.out.println("Enter your id: ");
		String id = in.nextLine();

		System.out.println("Enter your password: ");
		String pw = in.nextLine();

		do // I ask what happened here for convenience.
		{
			System.out.println
			(
				"\nWhat is this account?\n" +
				"1) Admin\n" +
				"2) Branch Employee\n" +
				"3) Transportation Personnel\n"
			);

			Scanner java_in = new Scanner(System.in);
			choice = java_in.nextLine();

			try{

				val = Integer.parseInt(choice);

			}catch(Exception e){

				System.out.println("\nInvalid Choice...\n");

			}

			switch(val)
			{

				/*
					in addEmployeeSystem()
					Throws an exception so that there are no identical accounts in the list.
					if there is not same employee in arraylist, it will push employee to arraylist
				*/

				case 1:
					Administrator admin = new Administrator(name, id, pw); // generate admin

					try{

						addEmployeeSystem(admin);

					}catch(Exception e){
						System.out.println(e.getMessage());
					}

					val = 0;
				break;

				case 2:
					BranchEmployee branch_employee = new BranchEmployee(name, id, pw); // generate branch employee

					try{

						addEmployeeSystem(branch_employee);

					}catch(Exception e){
						System.out.println(e.getMessage());
					}

					val = 0;
				break;

				case 3:
					TransportationPersonnel tp = new TransportationPersonnel(name, id, pw); // generate transportation employee

					try{

						addEmployeeSystem(tp);

					}catch(Exception e){
						System.out.println(e.getMessage());
					}

					val = 0;
				break;

				default:
					System.out.println("Please Enter between 1 and 3...\n");
				break;
			}

		}while(val != 0);
	}

	/**
	* Implements login panel from CompanySystem
	*/
	public void login()
	{
		Scanner in = new Scanner(System.in);

		System.out.println("\nEnter your username: ");
		String id = in.nextLine();

		System.out.println("\nEnter your password: ");
		String pw = in.nextLine();

		Employee finder_account = new Administrator("xxx", id, pw);

		if(company_employees.contains(finder_account)) //if such membership exists
		{
			if(company_employees.get(company_employees.indexOf(finder_account)).getPassword().equals(pw))
			{
				currentEmployee = company_employees.get(company_employees.indexOf(finder_account));
				//The type of user who uses the system is important to their authority.
				operationPanel();
				//is a panel that asks for the actions to be taken. 
				//According to the authority, he can either do the transactions or not.
			}

			else // if such membership does not exists
			{
				System.out.println("\nInvalid account...\n");
				currentEmployee = null;
			}
		}

		else // if such membership does not exists
		{
			System.out.println("\nInvalid account...\n");
			currentEmployee = null;
		}
	}

	/**
	* Implements operation panel from CompanySystem
	*/
	public void operationPanel()
	{
		Scanner in = new Scanner(System.in);

		String choice;
		int val = 0;

		do
		{
			System.out.println
			(
				"\nPlease Choose Panel that you will do operations...\n" +
				"1) Add Remove Panel (Employee, Branch, Customer, Shipment etc...)\n" +
				"2) Customer Panel with tracking number...(for both customer and employee)\n" +
				"3) Cargo Panel...\n" +
				"4) Exit...\n"
			);

			choice = in.nextLine();

			try{

				val = Integer.parseInt(choice);

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

			switch(val)
			{
				case 1: // only administrators or branch employee
					if(currentEmployee instanceof Administrator || currentEmployee instanceof BranchEmployee) // authority check for person who using system
						system_add_remove_panel();
					else
						System.out.println("\nYou are not Administrator or Branch Employee\n");
				break;

				case 2: // all people
					customer_panel();
				break;

				case 3: // only transportation personnel or branch employee
					if(currentEmployee instanceof TransportationPersonnel || currentEmployee instanceof BranchEmployee) // authority check for person who using system
						cargo_panel();
					else
						System.out.println("\nYou are not Transportation Personnel or Branch Employee\n");
				break;

				case 4:
					System.out.println("\nRequest panel turned off...\n");
				break;

				default:
					System.out.println("\n Please you choose between 1 and 4\n");
				break;
			}
		}while(val != 4);
	}

	/**
	* It is the panel where add and remove are made in the system.
	* But cannot operate outside of unauthorized authority.
	*/
	private void system_add_remove_panel()
	{
		Scanner in = new Scanner(System.in);

		String choice;
		int val = 0;

		do
		{
			System.out.println
			(
				"\nWelcome to Add/Remove Panel\n\n" +
				"1) Add Branch\n" +
				"2) Add Branch Employee\n" +
				"3) Add Transportation Personnel\n" +
				"4) Add Customer\n" +
				"5) Add Cargo\n" +
				"6) Remove Branch\n" +
				"7) Remove Branch Employee\n" +
				"8) Remove Transportation Personnel\n" +
				"9) Remove Customer\n" +
				"10) Remove Cargo\n" +
				"11) Exit...\n"
			);

			choice = in.nextLine();

			try{

				val = Integer.parseInt(choice);

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

			switch(val)
			{
				case 1:
					if(currentEmployee instanceof Administrator) // authority check for person who using system
					{
						try{

							addBranch(takeBranch());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not administrator...\n");
				break;

				case 2:
					if(currentEmployee instanceof Administrator) // authority check for person who using system
					{
						try{

							addBranchEmployee(whichBranch(), takeBranchEmployee());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not administrator...\n");
				break;

				case 3:
					if(currentEmployee instanceof Administrator) // authority check for person who using system
					{
						try{

							addTransportationPersonnel(whichBranch(), takeTransporter());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not administrator...\n");
				break;

				case 4:
					if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
					{
						try{

							addCustomer(takeCustomer());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not Branch Employee...\n");
				break;

				case 5:
					if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
					{
						try{

							addCargo(whichBranch(), takeCargo());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not Branch Employee...\n");
				break;

				case 6:
					if(currentEmployee instanceof Administrator) // authority check for person who using system
					{
						try{

							removeBranch(whichBranch());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not Administrator...\n");
				break;

				case 7:
					if(currentEmployee instanceof Administrator) // authority check for person who using system
					{
						try{

							Branch whichBranch = whichBranch();

							removeBranchEmployee(whichBranch, whichBranch.whichEmployee());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not Administrator...\n");
				break;

				case 8:
					if(currentEmployee instanceof Administrator) // authority check for person who using system
					{
						try{

							Branch whichBranch = whichBranch();

							removeTransportationPersonnel(whichBranch, whichBranch.whichTransporter());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not administrator...\n");
				break;

				case 9:
					if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
					{
						try{

							removeCustomer(whichCustomer());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYoy are not Branch Employee...\n");
				break;

				case 10:
					if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
					{
						try{

							Branch whichBranch = whichBranch();

							removeCargo(whichBranch, whichBranch.whichCargo());

						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}

					else
						System.out.println("\nYou are not Branch Employee...\n");
				break;

				case 11:
					System.out.println("\nAdd Remove Pannel turning off...\n");
				break;

				default:
					System.out.println("Please enter number between 1 and 9...");
				break;
			}
		}while(val != 11);
	}	

	/**
	* Is a panel that everyone will use. that is, it is not a customer without a tracking number and cannot use it :)
	*/
	private void customer_panel()
	{
		String choice;
		Scanner in = new Scanner(System.in);

		System.out.println("\nPlease enter the tracking number: ");

		choice = in.nextLine();

		isValidCargo(choice);
	}

	/**
	* the method search cargo with tracking number
	* @param trackingNumber, will be find cargo tracking number
	*/
	public void isValidCargo(String trackingNumber)
	{
		for(int i = 0 ; i < branches.size() ; ++i)
			if(branches.get(i).containsCargo(trackingNumber))
			{
				branches.get(i).showCargo(trackingNumber);
				return;
			}

		System.out.println("\nInvalid tracking number...\n");
	}

	/**
	* In this panel, the units related to cargo delivery can make transactions.
	*/
	private void cargo_panel()
	{
		Scanner in = new Scanner(System.in);

		String choice;
		int val = 0;

		do
		{
			System.out.println
			(
				"1) The Delivered Cargo was delivered by Transportation Personnel \n" +
				"2) Arrived Cargo in branch\n" +
				"3) Leave Cargo from branch\n" +
				"4) Exit... \n"
			);

			choice = in.nextLine();

			try{

				val = Integer.parseInt(choice);

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

			switch(val)
			{
				case 1:
					if(currentEmployee instanceof TransportationPersonnel) // authority check for person who using system
					{
						if(branches.size() > 0)
						{
							try{
								Branch operation_branch = whichBranch();

								thereis_deliveredCargo(operation_branch, operation_branch.whichCargo());

							}catch(Exception e){
								System.out.println(e.getMessage());
							}
						}

						else
							System.out.println("\nThere is no branch...\n");
					}

					else
						System.out.println("\nYou are not Transportation Employee...\n");
				break;

				case 2:
					if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
					{
						int index = employeeHasBranch();

						if(index != -1)
						{
							BranchEmployee current = (BranchEmployee) currentEmployee;

							arrivedCargo(branches.get(index), takeCargo());
						}

						else
							System.out.println("\nCurrent Employee does not work at any branch\n");
					}
					else
						System.out.println("\nYou are not branch employee...\n");

				break;

				case 3:
					if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
					{
						int index = employeeHasBranch();

						if(index != -1)
						{
							BranchEmployee current = (BranchEmployee) currentEmployee;

							leaveCargo(branches.get(index), branches.get(index).whichCargo());
						}

						else
							System.out.println("\nCurrent Employee does not work at any branch...\n");
					}

					else
						System.out.println("\nYou are not Branch Employee...\n");
					
				break;

				case 4:
					System.out.println("\nCargo panel turned off...\n");
				break;

				default:
					System.out.println("\nPlease choose between 1 and 4\n");
				break;
			}
		}while(val != 4);
	}

	/**
	* if the cargo arrived, branch employee can change arrived cargo status
	*/
	public void arrivedCargo(Branch branch, Shipment arrived)
	{
		if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
			branch.arrivedCargo(arrived);

		else
			System.out.println("\nThe Person that using system is not Branch Emoloyee...\n");
	}

	/**
	* if the cargo leaved, branch employee can change leaved cargo status
	*/
	public void leaveCargo(Branch branch, Shipment arrived)
	{
		if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
			branch.leaveCargo(arrived);

		else
			System.out.println("\nThe Person that using system is not Branch Emoloyee...\n");
	}

	/**
	* which indexed branch employee belongs to
	*/
	private int employeeHasBranch()
	{
		if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
		{
			BranchEmployee current = (BranchEmployee) currentEmployee;

			for(int i = 0 ; i < branches.size() ; ++i)
				if(branches.get(i).getBranchName().equals(current.getNameOfBranch()))
					return i;
		}

		else
			System.out.println("\nYou are not branch Emplpyee...\n");

		return -1;
	}

	/**
	* If the cargo out from a branch, the branch of the cargo remains uncertain. 
	* But if another branch says that this cargo belongs to us, the branch of the cargo will be determined.
	* @param whichBranch, which branch 
	* @param whichCargo, which cargo
	*/
	public void thereis_deliveredCargo(Branch whichBranch, Shipment whichCargo)
	{
		if(currentEmployee instanceof TransportationPersonnel) // authority check for person who using system
		{
			if(branches.size() > 0)
				branches.get(branches.indexOf(whichBranch)).setCargoStatus("DELIVERED CARGO", whichCargo);

			else
				System.out.println("\nThere is not branch please create branch before this organization...\n");
		}

		else
			System.out.println("\nYou are not Transportation Employee...\n");
	}

	/**
	* @param o, taken employee
	* The method push taken employee to all employees arraylist
	* Throws an exception so that there are no identical accounts in the arraylist.
	*/
	public void addEmployeeSystem(Employee o)
	{
		if(company_employees.contains(o))
			throw new IllegalArgumentException("\nThis account is visible in system please try again...");

		else
			company_employees.add(o);
	}

	/**
	* @param o, taken employee
	* The method remove taken employee from all employees arraylist
	* Throws and exception so that there are identical accounts int the arraylist
	*/
	public void removeEmployeeSystem(Employee o)
	{
		if(!(company_employees.contains(o)))
			throw new IllegalArgumentException("\nThere is not this employee in the system...\n");
		else
			company_employees.remove(o);
	}

	/**
	* Add branch to the system
	*/
	public void addBranch(Branch willAdd)
	{
		if(currentEmployee instanceof Administrator) // authority check for person who using system
		{
			if(branches.contains(willAdd))
				throw new IllegalArgumentException("This branches is visible in system please try again...");
			
			else
				branches.add(willAdd);
		}

		else
			System.out.println("\nYou are not Administrator...\n");
	}

	/**
	* Adds branch employee to the system
	* @param willAdd, object to add.
	* @param whichBranch, which branch will be added 
	*/
	public void addBranchEmployee(Branch whichBranch, BranchEmployee willAdd)
	{
		if(currentEmployee instanceof Administrator) // authority check for person who using system
		{
			try{

				if(branches.size() == 0)
					throw new IllegalArgumentException("\nThere is not this branch avaible in system...\n");

				if(!(branches.contains(whichBranch)))
					throw new IllegalArgumentException("\nThere is not this branch avaible in system...\n");

				for(int i = 0 ; i < branches.size() ; ++i)
					if(branches.get(i).containsEmployee(willAdd))
						throw new IllegalArgumentException("This branch has already have this branch employee...\n");
				
				branches.get(branches.indexOf(whichBranch)).insertBranchEmployee(willAdd);
				addEmployeeSystem(willAdd);

			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		else
			System.out.println("\nYou are not Administrator...\n");
	}

	/**
	* Adds transportation personnel to the system
	* @param willAdd, object to add.
	* @param whichBranch, which branch will be added 
	*/
	public void addTransportationPersonnel(Branch whichBranch, TransportationPersonnel willAdd)
	{
		if(currentEmployee instanceof Administrator) // authority check for person who using system
		{
			try{

				if(branches.size() == 0)
					throw new IllegalArgumentException("\nThere is not this branch avaible in system...\n");

				if(!(branches.contains(whichBranch)))
					throw new IllegalArgumentException("\nThere is not this branch avaible in system...\n");

				for(int i = 0 ; i < branches.size() ; ++i)
					if(branches.get(i).containsTransporter(willAdd))
						throw new IllegalArgumentException("This branch has already have this transporter...\n");

				branches.get(branches.indexOf(whichBranch)).insertTransporter(willAdd);
				addEmployeeSystem(willAdd);
			
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		else
			System.out.println("\nYou are not administrator...\n");
	}

	/**
	* Adds customer to the system
	* @param willAdd, object to add.
	*/
	public void addCustomer(Customer willAdd)
	{
		if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
		{
			if(customers.contains(willAdd))
				throw new IllegalArgumentException("This customer is visible in system please try again...");

			else
				customers.add(willAdd);
		}

		else
			System.out.println("\nYou are not Branch Employee...\n");
	}

	/**
	* Adds cargo to the system
	* @param shipment, object to add.
	* @param whichBranch, which branch will be added
	*/
	public void addCargo(Branch whichBranch, Shipment shipment)
	{
		if(currentEmployee instanceof BranchEmployee) // authority check for person who using system
		{
			try{
				if(branches.size() < 1)
					throw new IllegalArgumentException("\nThere is not this branch avaible in system...\n");

				if(!(branches.contains(whichBranch)))
					throw new IllegalArgumentException("\nThere is not this branch avaible in system...\n");

				branches.get(branches.indexOf(whichBranch)).insertCargo(shipment);

			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		else
			System.out.println("\nYou are not Branch Employee...\n");
	}

	/**
	* remove branch from the system
	* @param whichBranch, which branch will be removed
	*/
	public void removeBranch(Branch whichBranch)
	{
		if(this.currentEmployee instanceof Administrator) // authority check for person who using system
		{
			if(branches.size() > 0)
				if(branches.contains(whichBranch))
					branches.remove(whichBranch);
			else
				System.out.println("\nThere is not branch please create branch before this organization...\n");
		}
		else
			System.out.println("\nYou are not Administrator...\n");
	}

	/**
	* remove branch employee from the system
	* @param whichBranch, at which branch will be removed
	* @param whichEmployee which branch employee will be removed
	*/
	public void removeBranchEmployee(Branch whichBranch, BranchEmployee whichEmployee)
	{
		if(currentEmployee instanceof Administrator) // authority check for person who using system
		{
			try{

				if(branches.size() > 0)
				{
					branches.get(branches.indexOf(whichBranch)).removeBranchEmployee(whichEmployee);
					removeEmployeeSystem(whichEmployee);
				}

				else
					System.out.println("\nThere is not branch please create branch before this organization...\n");

			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		else
			System.out.println("\nYou are not administrator...\n");
	}

	/**
	* remove transportation personnel from the system
	* @param whichBranch, which branch will be removed
	* @param whichTransporter which transporter will be removed
	*/
	public void removeTransportationPersonnel(Branch whichBranch, TransportationPersonnel whichTransporter)
	{
		if(currentEmployee instanceof Administrator) // authority check for person who using system
		{
			try{

				if(branches.size() > 0)
				{
					branches.get(branches.indexOf(whichBranch)).removeTransporter(whichTransporter);
					removeEmployeeSystem(whichTransporter);
				}

				else
					System.out.println("\nThere is not branch please create branch before this organization...\n");
			
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		else
			System.out.println("\nYou are not administrator...\n");
	}

	/**
	* remove customer from the system
	* @param whichCustomer, which customer will be removed
	*/
	public void removeCustomer(Customer whichCustomer)
	{
		if(this.currentEmployee instanceof BranchEmployee) // authority check for person who using system
		{
			if(customers.size() > 0)
			{
				if(customers.contains(whichCustomer))
					customers.remove(whichCustomer);

				else
					System.out.println("\nThere is not this customer in system...\n");
			}

			else
				System.out.println("\nThere is not customer please create customer account before this organization...\n");
		}
		else
			System.out.println("\nYou are not Branch Employee...\n");
	}

	/**
	* remove cargo from the system
	* @param whichBranch at which branch will be removed
	* @param whichCargo, which cargo will be removed
	*/
	public void removeCargo(Branch whichBranch, Shipment whichCargo)
	{
		if(this.currentEmployee instanceof BranchEmployee) // authority check for person who using system
		{
			try{

				if(branches.size() > 0)
					branches.get(branches.indexOf(whichBranch)).removeCargo(whichCargo);

				else
					System.out.println("\nThere is not branch please create branch before this organization...\n");

			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		else
			System.out.println("\nYou are not Branch Employee...\n");
	}

	/**
	* generate and return customer
	* @return Customer object that is generated
	*/
	private Customer takeCustomer()
	{
		String name;
		String identity;

		Scanner in = new Scanner(System.in);

		System.out.println("\nPlease Enter the name of customer: \n");
		name = in.nextLine();

		System.out.println("\nPlease Enter the identity number of customer: \n");
		identity = in.nextLine();

		return new Customer(name, identity);
	}

	/**
	* generate and return cargo
	* @return cargo object that is generated
	*/
	private Shipment takeCargo()
	{
		String senderName;
		String receiverName;
		String trackingNumber;

		Scanner in = new Scanner(System.in);

		System.out.println("\nPlease Enter the name of cargo's sender: ");
		senderName = in.nextLine();

		System.out.println("\nPlease Enter the name of cargo's receiver: ");
		receiverName = in.nextLine();

		System.out.println("\nPlease Enter the name of cargo's tracking number: ");
		trackingNumber = in.nextLine();

		return new Shipment(senderName, receiverName, trackingNumber, "in Branch");
	}

	/**
	* generate and return transportation personnel object
	* @return transportation personnel that is generated
	*/
	private TransportationPersonnel takeTransporter()
	{
		String name;
		String userID;
		String password;

		Scanner in = new Scanner(System.in);

		System.out.println("\nPlease Enter the name of Transportation Employee: ");
		name = in.nextLine();

		System.out.println("\nPlease Enter the username for Transportation employee: ");
		userID = in.nextLine();

		System.out.println("\nPlease Enter the password for Transportation employee: ");
		password = in.nextLine();

		return new TransportationPersonnel(name , userID, password);
	}

	/**
	* generate and return branch employee object
	* @return branch employee that is generated
	*/
	private BranchEmployee takeBranchEmployee()
	{
		String name;
		String userID;
		String password;

		Scanner in = new Scanner(System.in);

		System.out.println("\nPlease Enter the name of Branch Employee: ");
		name = in.nextLine();

		System.out.println("\nPlease Enter the username for branch employee: ");
		userID = in.nextLine();

		System.out.println("\nPlease Enter the password for branch employee: ");
		password = in.nextLine();

		return new BranchEmployee(name, userID, password);
	}

	/**
	* generate and return branch object
	* @return branch that is generated
	*/
	private Branch takeBranch()
	{
		String name;

		Scanner in = new Scanner(System.in);

		System.out.println("\nPlease Enter the name of Branch: ");
		name = in.nextLine();

		return new Branch(name);
	}

	/**
	* choose and return branch that is which branch in system
	* @return branch be choosed from user
	*/
	private Branch whichBranch()
	{
		int return_val = 0;
		String choice;
		Scanner in = new Scanner(System.in);

		if(branches.size() == 0)
			throw new IllegalArgumentException("\nThere is not branch...\n");

		for(int i = 1 ; i <= branches.size() ; ++i)
			System.out.println(i + ") " + branches.get(i-1).getBranchName() + "\n");

		do
		{
			System.out.println("\nPlease enter the branch NUMBER that will be operation: ");
			
			choice = in.nextLine();

			try{

				return_val = Integer.parseInt(choice);

				if(return_val <= 0 || return_val > branches.size())
					System.out.println("\nInvalid Branch...\n");

			}catch(Exception e){
				System.out.println("Invalid choice...");
			}

		}while(return_val <= 0 || return_val > branches.size());

		return branches.get(return_val-1);
	}


	/**
	* choose and return customer that is which customer in system
	* @return customer be choosed from user
	*/
	private Customer whichCustomer()
	{
		int return_val = 0;
		String choice;

		Scanner in = new Scanner(System.in);

		if(customers.size() == 0)
			throw new IllegalArgumentException("\nThere is not customer...\n");

		for(int i = 1 ; i <= customers.size() ; ++i)
			System.out.println(i + ") " + customers.get(i-1).getName() + "\n");

		do
		{
			System.out.println("\nPlease enter the customer that will be operation: ");

			choice = in.nextLine();

			try{

				return_val = Integer.parseInt(choice);

				if(return_val <= 0 || return_val > customers.size())
					System.out.println("\nInvalid Branch...\n");

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

		}while(return_val <= 0 || return_val > customers.size());

		return customers.get(return_val-1);
	}

	/**
	* Show all branches (No need for now, but I did it in terms of readability)
	*/
	public void showBranches()
	{
		if(branches.size() > 0)
		{
			int index = 0;

			System.out.println("ALL BRANCHES: ");
			for(int i = 0 ; i < branches.size() ; ++i)
			{
				index = i + 1;
				System.out.println(index + ") " + branches.get(i));
			}
			System.out.println("--------------\n");
		}

		else
			System.out.println("There is not any branches...\n");
	}

	/**
	* Show all admins (No need for now, but I did it in terms of readability)
	*/
	public void showAdmins()
	{
		boolean isValid = false;

		System.out.println("Show All Admins: ");
		for(int i = 0 ; i < company_employees.size() ; ++i)
			if(company_employees.get(i) instanceof Administrator)
			{
				System.out.println(company_employees.get(i));
				isValid = true;
			}

		if(!isValid)
			System.out.println("\nThere is not any admins...\n");
	}

	/**
	* Show all branch employees (No need for now, but I did it in terms of readability)
	*/
	public void showBranchEmployees()
	{
		if(branches.size() > 0)
		{
			System.out.print("SHOW ALL EMPLOYEES: ");
			for(int i = 0 ; i < company_employees.size() ; ++i)
				if(company_employees.get(i) instanceof BranchEmployee)
					System.out.print(company_employees.get(i));
		}

		else
			System.out.println("\nThere is not any branch...\n");
	}

	/**
	* Show all transportation personnel (No need for now, but I did it in terms of readability)
	*/
	public void showTransporters()
	{
		if(branches.size() > 0)
		{
			System.out.print("SHOW ALL TRANSPORTERS: ");
			for(int i = 0 ; i < company_employees.size() ; ++i)
				if(company_employees.get(i) instanceof TransportationPersonnel)
					System.out.print(company_employees.get(i));
		}

		else
			System.out.println("\nThere is not any branch...\n");
	}

	/**
	* Show all cargos (No need for now, but I did it in terms of readability)
	*/
	public void showCargos()
	{
		if(branches.size() > 0)
		{
			System.out.println("SHOW ALL CARGOS: ");
			for(int i = 0 ; i < branches.size() ; ++i)
			{
				System.out.print("\n-----" + branches.get(i).getBranchName() + "-----");
				branches.get(i).showCargos();
			}

		}

		else
			System.out.println("\nThere is not any branch...\n");
	}

	/**
	* Show all customers (No need for now, but I did it in terms of readability)
	*/
	public void showCustomers()
	{
		if(customers.size() > 0)
		{
			System.out.println("ALL CUSTOMERS: ");
			for(int i = 0 ; i < customers.size() ; ++i)
			System.out.print(customers.get(i));
			System.out.println("---------------\n");
		}

		else
			System.out.println("There is not any customer...\n");
	}

	/**
	* setter method current employee
	* @param o, be assigned to current employee
	* To be able to test in the main method
	*/
	public void setCurrentEmployee(Employee o) { currentEmployee = o; }

	/**
	* getter method current employee
	* @return current employee
	* To be able to test in the main method
	*/
	public Employee getCurrentEmployee() { return currentEmployee; }
}