/**
* Main class
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW1
*/

import java.util.Scanner;

public class Mainclass
{
	/**
	* System object
	*/
	private static final CargoCompanySystem main_system_object = new CargoCompanySystem();

	public static void main(String[] args)
	{
		panelForLecturer();
	}

	public static void panelForLecturer()
	{
		int val = 0;
		String choice;
		Scanner in = new Scanner(System.in);

		System.out.println("\nWelcome the cargo company system program for CSE222 HW1...\n");

		do
		{
			System.out.println
			(
				"1) Test the homework...\n" +
				"2) Run Program...\n" +
				"3) Exit Program..."
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
					test();
				break;

				case 2:
					main_system_object.systemPanel();
				break;

				case 3:
					System.out.println("\nThe program turned off...");
				break;

				default:
					System.out.println("\nPlease choose between 1 and 3...\n");
				break;
			}

		}while(val < 1 || val > 3);
	}

	public static void test()
	{
		/**********************************Administrator TEST**************************************/
		Employee employe_admin = new Administrator("Ozan", "Admin", "Admin");

		main_system_object.setCurrentEmployee(employe_admin);
		System.out.println("\nThe user that be using system is Administrator...\n");
		System.out.print("The information of administrator: " + employe_admin);

		System.out.println("\n----------------------------------------------------------------");
		System.out.println("The Current user(Administrator) try some add/remove operation...");
		System.out.println("----------------------------------------------------------------\n");

		System.out.println("Try Adding Branch: ");
		Branch istanbul = new Branch("istanbul");
		System.out.println("Added branch name is " + istanbul.getBranchName());
		main_system_object.addBranch(istanbul);
		main_system_object.showBranches();

		System.out.println("Try Adding Another Branch: ");
		Branch mersin = new Branch("mersin");
		System.out.println("Added branch name is " + mersin.getBranchName());
		main_system_object.addBranch(mersin);
		main_system_object.showBranches();

		System.out.println("Try Adding Another Branch: ");
		Branch ankara = new Branch("ankara");
		System.out.println("Added branch name is " + ankara.getBranchName());
		main_system_object.addBranch(ankara);
		main_system_object.showBranches();

		try{
			System.out.println("------------------------------------------------");
			System.out.println("Try Adding Branch that system has already have: ");
			System.out.println("The Branch name is " + ankara.getBranchName());
			main_system_object.addBranch(ankara);
		}catch(Exception e){
			System.out.print("ERROR -> ");
			System.out.println(e.getMessage());
			System.out.println("------------------------------------------------\n");
		}

		System.out.println("Try Adding Another Branch: ");
		Branch adana = new Branch("adana");
		System.out.println("Added branch name is " + adana.getBranchName());
		main_system_object.addBranch(adana);
		main_system_object.showBranches();

		System.out.println("Try Remove Branch: ");
		System.out.println("Removed branch name is " + mersin.getBranchName());
		main_system_object.removeBranch(mersin);
		main_system_object.showBranches();

		System.out.println("Try Remove Another Branch: ");
		System.out.println("Removed branch name is " + adana.getBranchName());
		main_system_object.removeBranch(adana);
		main_system_object.showBranches();

		System.out.println("Try Add Branch Employee in " + istanbul.getBranchName() + ": ");
		BranchEmployee ozan = new BranchEmployee("ozan", "ozannyesiller", "branchemployee1");
		main_system_object.addBranchEmployee(istanbul, ozan);
		System.out.println("Added Branch Employee is: " + ozan);

		System.out.println("Try Add Same Branch Employee in " + ankara.getBranchName() + ": ");
		System.out.print("ERROR -> ");
		main_system_object.addBranchEmployee(ankara, ozan);

		System.out.println("Try Add Branch Employee in " + ankara.getBranchName() + ": ");
		BranchEmployee berke = new BranchEmployee("berke", "berkesnmzz", "branchemployee1");
		main_system_object.addBranchEmployee(ankara, berke);
		System.out.println("Added Branch Employee is: " + berke);

		System.out.println("Try Add Branch Employee in " + istanbul.getBranchName() + ": ");
		BranchEmployee mehmetalp = new BranchEmployee("mehmetalp", "mehmetalpylmz", "branchemployee1");
		main_system_object.addBranchEmployee(istanbul, mehmetalp);
		System.out.println("Added Branch Employee is: " + mehmetalp);

		System.out.println("Try Add Branch Employee in " + ankara.getBranchName() + ": ");
		BranchEmployee goktug = new BranchEmployee("goktug", "gok2a", "branchemployee1");
		main_system_object.addBranchEmployee(ankara, goktug);
		System.out.println("Added Branch Employee is: " + goktug);

		System.out.println("Try remove branch employee is " + goktug.getName());
		main_system_object.removeBranchEmployee(ankara, goktug);
		main_system_object.showBranchEmployees();

		System.out.println("\nTry Add Transportation Personnel in " + ankara.getBranchName());
		TransportationPersonnel hasan = new TransportationPersonnel("hasan", "hsnn", "transporter1");
		main_system_object.addTransportationPersonnel(ankara, hasan);
		System.out.println("Added Transportation Personnel is: " + hasan);

		System.out.println("\nTry Add Another Transportation Personnel in " + istanbul.getBranchName());
		TransportationPersonnel huseyin = new TransportationPersonnel("huseyin", "hsynn27", "transporter2");
		main_system_object.addTransportationPersonnel(istanbul, huseyin);
		System.out.println("Added Transportation Personnel is: " + huseyin);

		System.out.println("\nTry Add Another Transportation Personnel in " + ankara.getBranchName());
		TransportationPersonnel sami = new TransportationPersonnel("sami", "sami27", "transporter3");
		main_system_object.addTransportationPersonnel(ankara, sami);
		System.out.println("Added Transportation Personnel is: " + sami);

		System.out.println("\nTry Add same transportation personnel but different branch: \n");
		System.out.print("ERROR -> ");
		main_system_object.addTransportationPersonnel(ankara, huseyin);
		main_system_object.showTransporters();

		System.out.println("\nTry Remove Transportation Personel is " + sami.getName() + " in " + ankara.getBranchName() + "\n");
		main_system_object.removeTransportationPersonnel(ankara, sami);
		main_system_object.showTransporters();
		System.out.println("");

		System.out.println("Try adding customer: ");
		Customer test = new Customer("Selami", "2993278665");
		main_system_object.addCustomer(test);

		System.out.println("\nTry adding cargo: ");
		Shipment test2 = new Shipment("ozan", "melisa", "90a", "istanbul");
		main_system_object.addCargo(istanbul, test2);
		System.out.println("*********************END OF ADMINISTRATOR TEST**************************\n");
		/**********************************Administrator TEST**************************************/

		/**********************************Branch Employee TEST**************************************/
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		BranchEmployee employe_branch = ozan;
		main_system_object.setCurrentEmployee(employe_branch);
		System.out.println("\nThe user that be using system is Branch Employee...\n");
		System.out.print("The information of branch employee: " + employe_branch);

		System.out.println("\n----------------------------------------------------------------");
		System.out.println("The Current user(branch employee) try some add/remove operation...");
		System.out.println("----------------------------------------------------------------\n");


		System.out.println("\nTry adding branch: ");
		Branch test_1 = new Branch("adana");
		main_system_object.addBranch(test_1);

		System.out.println("\nTry removing branch: ");
		main_system_object.removeBranch(istanbul);

		System.out.println("\nTry adding branch employee: ");
		BranchEmployee test_2 = new BranchEmployee("nurefsan", "yilmaz", "employee1");
		main_system_object.addBranchEmployee(istanbul, test_2);

		System.out.println("\nTry removing branch employee: ");
		main_system_object.removeBranchEmployee(istanbul, ozan);

		System.out.println("\nTry adding transportation personnel: ");
		TransportationPersonnel test_3 = new TransportationPersonnel("kadir", "guntepe", "employee1__");
		main_system_object.addTransportationPersonnel(istanbul, test_3);

		System.out.println("\nTry removing transportation personnel: ");
		main_system_object.removeTransportationPersonnel(istanbul, hasan);

		System.out.println("\nTry adding customer in system: ");
		Customer ozan_c = new Customer("ozan", "2993254");
		System.out.println("Added customer name is " + ozan_c.getName());
		main_system_object.addCustomer(ozan_c);

		System.out.println("\nTry adding customer in system: ");
		Customer melisa_c = new Customer("melisa", "3224590");
		System.out.println("Added customer name is " + melisa_c.getName());
		main_system_object.addCustomer(melisa_c);
		System.out.println("");
		main_system_object.showCustomers();

		System.out.println("\nTry adding customer in system: ");
		Customer ahmet_c = new Customer("ahmet", "6205714");
		System.out.println("Added customer name is " + ahmet_c.getName());
		main_system_object.addCustomer(ahmet_c);
		System.out.println("");
		main_system_object.showCustomers();

		System.out.println("\nTry removing customer in system: ");
		main_system_object.removeCustomer(melisa_c);
		System.out.println("Removed customer name is " + melisa_c.getName() + "\n");
		main_system_object.showCustomers();

		System.out.println("\nTry adding cargo in system: \n");
		Shipment cargo = new Shipment(ozan_c.getName(), ahmet_c.getName(), "962a", istanbul.getBranchName());
		main_system_object.addCargo(istanbul ,cargo);
		System.out.print("The cargo informations: ");
		System.out.println(cargo);

		System.out.println("\nTry adding another cargo in system: \n");
		Shipment cargo2 = new Shipment(ahmet_c.getName(), ozan_c.getName(), "932c", ankara.getBranchName());
		main_system_object.addCargo(ankara, cargo2);
		System.out.print("The cargo informations: ");
		System.out.println(cargo2);

		System.out.println("\nTry removing cargo in " + ankara.getBranchName() + ": \n");
		main_system_object.removeCargo(ankara, cargo2);
		System.out.println("Removed cargo is " + cargo2 + "\n");
		main_system_object.showCargos();

		System.out.println("\n\nArrived cargo in branch of " + ankara.getBranchName() + ": \n");
		Shipment cargo3 = new Shipment(ahmet_c.getName(), ozan_c.getName(), "152t", ankara.getBranchName());
		main_system_object.arrivedCargo(ankara ,cargo3);
		System.out.print("The cargo informations: ");
		System.out.println(cargo3);
		main_system_object.showCargos();

		System.out.println("\nLeaved Cargo from " + istanbul.getBranchName());
		main_system_object.leaveCargo(istanbul, cargo);
		System.out.println("\nThe cargo is: " + cargo + "\n");
		main_system_object.showCargos();
		System.out.println("*********************END OF BRANCH EMPLOYEE TEST**************************\n");
		/**********************************Branch Employee TEST**************************************/

		/**********************************Transporter TEST**************************************/

		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		TransportationPersonnel employe_tp = hasan;
		main_system_object.setCurrentEmployee(employe_tp);
		System.out.println("\nThe user that be using system is transportation personnel...\n");
		System.out.print("The information of transportation personnel: " + employe_tp);

		System.out.println("\n----------------------------------------------------------------");
		System.out.println("The Current user(transportation personnel) try some add/remove operation...");
		System.out.println("----------------------------------------------------------------\n");

		System.out.println("\nTry adding branch: ");
		Branch test__1 = new Branch("adana");
		main_system_object.addBranch(test__1);

		System.out.println("\nTry removing branch: ");
		main_system_object.removeBranch(istanbul);		

		System.out.println("\nTry adding branch employee: ");
		BranchEmployee test__2 = new BranchEmployee("be1", "be1", "be1");
		main_system_object.addBranchEmployee(istanbul, test__2);

		System.out.println("\nTry removing branch employee: ");
		main_system_object.removeBranchEmployee(istanbul, ozan);

		System.out.println("\nTry adding transportation personnel: ");
		TransportationPersonnel test__3 = new TransportationPersonnel("tp1", "tp1", "tp1");
		main_system_object.addTransportationPersonnel(istanbul, test__3);

		System.out.println("\nTry removing transportation personnel: ");
		main_system_object.removeTransportationPersonnel(istanbul, hasan);

		System.out.println("\nTry adding customer: ");
		Customer test__4 = new Customer("yusuf", "123765");
		main_system_object.addCustomer(test__4);

		System.out.println("\nTry removing customer: ");
		main_system_object.removeCustomer(ahmet_c);

		System.out.println("\nTry adding cargo: ");
		Shipment cargo_4 = new Shipment(ahmet_c.getName(), ozan_c.getName(), "sad22", ankara.getBranchName());
		main_system_object.addCargo(ankara, cargo_4);

		System.out.println("\nTry removing cargo: ");
		main_system_object.removeCargo(ankara, cargo3);

		System.out.println("\nBEFORE DELIVERED CARGO TEST: \n");
		main_system_object.showCargos();

		System.out.println("\nTry delivered cargo status: \n");
		System.out.println("\nDelivered cargo: " + cargo);
		main_system_object.thereis_deliveredCargo(istanbul, cargo);

		main_system_object.showCargos();
		System.out.println("*********************END OF TRANSPORTATON PERSONNEL TEST**************************\n");
		/**********************************Transporter TEST**************************************/

		/**********************************CUSTOMER TEST**************************************/
		System.out.println("\nTry search cargo with tracking number for customer: ");
		System.out.println("\nThe cargo that is tracking number 189t is: ");
		main_system_object.isValidCargo("189t");
		System.out.println("\nYes, there is not this cargo in system...\n");

		System.out.println("\nTry search cargo that is tracking number 152t is: \n");
		main_system_object.isValidCargo("152t");
		System.out.println("*********************END OF CUSTOMER TEST**************************\n");
		/**********************************CUSTOMER TEST**************************************/
	}
}