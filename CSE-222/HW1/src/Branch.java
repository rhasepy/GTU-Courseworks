/**
* Branch class
* Branch class is the similar to CargoCompanySystem class
* The class is little system class 
* The system class has little systems as Branch class
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW1 part 2
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Branch
{
	/**
	* Name of the branch
	*/
	private String branch_name;

	/**
	* The branch has employees for branch this is branch employees
	*/
	private ArrayList <BranchEmployee> branch_employees;

	/**
	* The branch has employees for transportation this is transportation personnel
	*/
	private ArrayList <TransportationPersonnel> transporters;

	/**
	* The branch has shipments from giving or taking customer
	*/
	private ArrayList <Shipment> cargos;

	/**
	* One parameter constructor
	* The constructor initiliaze ArrayList fiedls
	* @param name, will be assigned branch_name
	*/
	public Branch(String name)
	{
		branch_name = name;
		branch_employees = new ArrayList<BranchEmployee>();
		transporters = new ArrayList<TransportationPersonnel>();
		cargos = new ArrayList<Shipment>();
	}

	/**
	* setter functions for branch name
	* @param x, assigned branch_name
	*/
	public void setBranchName(String x)
	{
		this.branch_name = x;
	}

	/**
	* getter functions for branch name
	* @return branch_name
	*/
	public String getBranchName()
	{
		return this.branch_name;
	}

	/**
	* the method take shipment and change information of status
	* @param arrivig, arrived object
	*/
	public void arrivedCargo(Shipment arriving)
	{
		if(!(cargos.contains(arriving))) // if the cargo came here, it shouldn't be here
		{
			cargos.add(arriving);
			arriving.setStatus("ARRIVED IN " + getBranchName());
		}
		
		else
			System.out.println("\nThe cargo has already  arrived branch...\n");
	}

	/**
	* the method take shipment and change informatin of status 
	* @param leaving, leaving object
	*/
	public void leaveCargo(Shipment leaving)
	{
		if(cargos.contains(leaving)) //The cargo must be here to get out of here
			leaving.setStatus("LEAVED from " + getBranchName());

		else
			System.out.println("\nThis cargo is not at the branch\n");
	}

	/**
	* the method take branch employee and push branch employee arraylist
	* @param o, taken BranchEmployee object that will be inserted arraylist
	*/
	public void insertBranchEmployee(BranchEmployee o)
	{
		if(branch_employees.contains(o))
			throw new IllegalArgumentException("\nThis branch has already have this employee...\n");

		else
		{
			o.setNameOfBranch(this.getBranchName());
			branch_employees.add(o);
		}
	}

	/**
	* the method take transportation employee and push transportation employee arraylist
	* @param o, taken TransportationPersonnel object that will be inserted arraylist
	*/
	public void insertTransporter(TransportationPersonnel o)
	{
		if(transporters.contains(o))
			throw new IllegalArgumentException("This branch has already have this transporter...\n");

		else
			transporters.add(o);
	}

	/**
	* the method take cargo and push cargo arraylist
	* @param o, taken Shipment object that will be inserted arraylist
	*/
	public void insertCargo(Shipment o)
	{
		if(cargos.contains(o))
			throw new IllegalArgumentException("\nThis branch has already have this cargo...\n");

		else
		{
			o.setStatus(getBranchName());
			cargos.add(o);
		}
	}

	/**
	* the method take branch employee and remove if the object is in branch employee arraylist
	* @param whichEmployee, taken object that will be removed element of branch employee arraylist
	*/
	public void removeBranchEmployee(BranchEmployee whichEmployee)
	{
		if(branch_employees.size() > 0)
		{
			if(branch_employees.contains(whichEmployee))
				branch_employees.remove(whichEmployee);

			else
				throw new IllegalArgumentException("\nInvalid removing...\n");
		}

		else
			throw new IllegalArgumentException("\nThere is not any branch employee...\nYou can't remove...\n");
	}

	/**
	* the method take transportation personnel and remove if the object is in transportation personnel arraylist
	* @param whichTransporter, that will be removed element of transportation personnel arraylist
	*/
	public void removeTransporter(TransportationPersonnel whichTransporter)
	{
		if(transporters.size() > 0)
		{
			if(transporters.contains(whichTransporter))
				transporters.remove(whichTransporter);

			else
				throw new IllegalArgumentException("\nInvalid removing...\n");
		}

		else
			throw new IllegalArgumentException("\nThere is not any transporters...\nYou can't remove...\n");
	}

	/**
	* the method take cargo and remove if the object is in cargo arraylist
	* @param whichShipment,that will be removed element of cargo arraylist
	*/
	public void removeCargo(Shipment whichShipment)
	{
		if(cargos.size() > 0)
		{
			if(cargos.contains(whichShipment))
				cargos.remove(whichShipment);

			else
				throw new IllegalArgumentException("\nInvalid removing...\n");
		}

		else
			throw new IllegalArgumentException("\nThere is not any cargo...\nYou can't remove...\n");
	}

	/**
	* @return that branch employee in the branch choice of authority employee
	*/
	public BranchEmployee whichEmployee()
	{
		int return_val = 0;
		String choice;

		Scanner in = new Scanner(System.in);

		if(branch_employees.size() <  1 )
			throw new IllegalArgumentException("\nThere is not branch employee...\n");

		for(int i = 1 ; i <= branch_employees.size() ; ++i)
			System.out.println(i + ") " + branch_employees.get(i-1) + "\n");

		do
		{
			System.out.println("\nPlease enter the Branch Employee that will be operation: ");

			choice = in.nextLine();

			try{

				return_val = Integer.parseInt(choice);

				if(return_val <= 0 || return_val > branch_employees.size())
					System.out.println("\nInvalid Branch...\n");

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

		}while(return_val <= 0 || return_val > branch_employees.size());

		return branch_employees.get(return_val-1);
	}

	/**
	* @return that transportation personnel in the branch choice of authority employee
	*/
	public TransportationPersonnel whichTransporter()
	{
		int return_val = 0;
		String choice;

		Scanner in = new Scanner(System.in);

		if(transporters.size() == 0)
			throw new IllegalArgumentException("\nThere is not transportation personnel...\n");

		for(int i = 1 ; i <= transporters.size() ; ++i)
			System.out.println(i + ") " + transporters.get(i-1).toString() + "\n");

		do
		{
			System.out.println("\nPlease enter the Transporter that will be operation: ");

			choice = in.nextLine();

			try{

				return_val = Integer.parseInt(choice);

				if(return_val <= 0 || return_val > transporters.size())
					System.out.println("\nInvalid Transporter...\n");

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

		}while(return_val <= 0 || return_val > transporters.size());

		return transporters.get(return_val-1);
	}

	/**
	* @return  that cargo choice of authority employee
	*/
	public Shipment whichCargo()
	{
		int return_val = 0;
		String choice;
		Scanner in = new Scanner(System.in);

		if(cargos.size() == 0)
			throw new IllegalArgumentException("\nThere is not cargo...\n");

		for(int i = 1 ; i <= cargos.size() ; ++i)
			System.out.println(i + ") " + cargos.get(i-1).toString() + "\n");

		do
		{
			System.out.println("\nPlease enter the Cargo that will be operation: ");

			choice = in.nextLine();
			
			try{

				return_val = Integer.parseInt(choice);

			if(return_val <= 0 || return_val > cargos.size())
				System.out.println("\nInvalid Cargo...\n");

			}catch(Exception e){
				System.out.println("\nInvalid choice...\n");
			}

		}while(return_val <= 0 || return_val > cargos.size());

		return cargos.get(return_val-1);
	}

	/**
	* setter functions for cargo status in the this branch
	* @param status, is the status of cargo
	* @param cargo, which cargo status will be changed
	*/
	public void setCargoStatus(String status, Shipment cargo)
	{
		cargos.get(cargos.indexOf(cargo)).setStatus(status);
	}

	/**
	* the method take one string because,
	* what the user needs to know for the shipping condition is the tracking number, not the cargo itself.
	* @param tracking, tracking number of the cargo
	* @return true, if the taken cargo is in here
	* @return false, if the taken cargo is in not here
	*/
	public boolean containsCargo(String tracking)
	{
		for(int i = 0 ; i < cargos.size() ; ++i)
			if(cargos.get(i).getTracking().equals(tracking))
				return true;

		return false;
	}

	/**
	* @param o, taken branch employee
	* @return true, if the taken branch employee is in here
	* @return false, if the taken branch employee is in not here
	*/
	public boolean containsEmployee(BranchEmployee o)
	{
		return branch_employees.contains(o);
	}

	/**
	* @param o, taken transportation personnel
	* @return true, if the taken transportation personnel is in here
	* @return false, if the taken transportation personnel is in not here
	*/
	public boolean containsTransporter(TransportationPersonnel o)
	{
		return transporters.contains(o);
	}

	/**
	* @param tracking, is the tracking number of cargo
	* show the cargos in the branch
	*/
	public void showCargo(String tracking)
	{
		if(containsCargo(tracking))
		{
			for(int i = 0 ; i < cargos.size() ; ++i)
				if(cargos.get(i).getTracking().equals(tracking))
				{
					System.out.println(cargos.get(i));
					return;
				}
		}

		else
			System.out.println("\nThere is not any cargos in the branch...\n");
	}

	/**
	* getter function for branch employeearraylist size
	* @return branch employee
	*/
	public int getBranchEmployee_size()
	{
		return branch_employees.size();
	}

	/**
	* getter function for branch transporterarraylist size
	* @return transporter size
	*/
	public int getTransporter_size()
	{
		return transporters.size();
	}

	/**
	* getter function for cargosarraylist size
	* @return cargoSize
	*/
	public int getCargo_size()
	{
		return cargos.size();
	}

	/**
	* Overriding method 
	* @param o, taken object should be Branch
	* @return true, taken branch object and this brunch is the same (according to branch name)
	* @return false, taken branch object and this brunch is the not same (according to branch name)
	*/
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Branch))
			return false;

		Branch test = (Branch) o;

		return (test.getBranchName().equals(this.getBranchName()));
	}


	/**
	* Overriding method
	* @return string of branch information
	*/
	@Override
	public String toString()
	{
		return "Name of Branch: " +
				getBranchName();
	}

	/**
	* print all cargos in this branch
	*/
	public void showCargos()
	{
		for(int i = 0 ; i < cargos.size() ; ++i)
			System.out.print(cargos.get(i));
	}
}