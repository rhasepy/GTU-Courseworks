import java.util.*;

public class SoftwareStore
{
    private SearchTree<Software> store;
    private HashSet<String> software_name;
    private Administrator admin;
    private boolean adminLogin = false;

    public SoftwareStore(SearchTree<Software> store) {
        this.store = store;
        this.software_name = new HashSet<>();
        this.admin = new Administrator("admin", "admin", store);
        defaultPackage();
    }

    public SearchTree<Software> getStore() {  return store; }

    public Software search_byName(String name) { return store.find(new Software(name, 0)); }

    public void search_byPrice(double price) {

        try{
            for (String s : software_name) {
                Software temp = store.find(new Software(s, 0));

                if(temp.getPrice() == price) System.out.println(temp);
            }
        }catch (NullPointerException e) { System.out.println("invalid product"); }
    }

    public void search_byQuantity(int quant) {

        try{
            for(String s : software_name) {
                Software temp = store.find(new Software(s, 0));

                if(temp.getQuantity() == quant) System.out.println(temp);
            }
        }catch (NullPointerException e) { System.out.println("invalid product"); }
    }

    private void addSoftware(Software item) {

        if(adminLogin && (!store.contains(item))) {
            admin.addSoftware(item);
            software_name.add(item.getName());
        }
    }

    private void removeSoftware(Software item) {

        if(adminLogin && store.contains(item)) {
            if(store.find(item).getQuantity() == 1)
                software_name.remove(item.getName());
            admin.removeSoftware(item);
        }
    }

    private void updateSoftware(Software old, Software newItem) {

        if(adminLogin && store.contains(old)) {
            admin.updateSoftware(old, newItem);
            software_name.remove(old.getName());
            software_name.add(newItem.getName());
        }
    }

    private void defaultPackage() {
        store.add(new Software("Adobe Photoshop 6.0", 9.99));
        store.add(new Software("Adobe Photoshop 6.2", 11.99));
        store.add(new Software("Norton 4.5", 17.99));
        store.add(new Software("Norton 5.5", 22.99));
        store.add(new Software("Adobe Flash 3.3", 5.99));
        store.add(new Software("Adobe Flash 4.0", 8.99));

        software_name.add("Adobe Photoshop 6.0");
        software_name.add("Adobe Photoshop 6.2");
        software_name.add("Norton 4.5");
        software_name.add("Norton 5.5");
        software_name.add("Adobe Flash 3.3");
        software_name.add("Adobe Flash 4.0");
    }

    @Override
    public String toString() { return store.toString(); }

    public void run()
    {
        String input;
        while(true)
        {
            do {
                System.out.println("login or exit [y/n]");
                input = inputString();

                if(!(input.equals("n") || input.equals("y") || input.equals("Y") || input.equals("N")))
                    System.out.println("Invalid choice please try again...");

            } while (!(input.equals("n") || input.equals("y") || input.equals("Y") || input.equals("N")));

            if(input.equals("n") || input.equals("N"))
                System.exit(1);

            adminLogin = false;
            this.login();
            this.operationPanel();
        }

    }

    private void operationPanel() {

        while(true)
        {
            if(!adminLogin) {

                System.out.println("0) Exit...");
                System.out.println("1) Search by name");
                System.out.println("2) Search by price");
                System.out.println("3) Search by quantity");
                System.out.println("4) Buy software");

                switch (inputNumerical())
                {
                    case 0:
                        System.out.println("The operation panel turning off...");
                        return;

                    case 1:
                        System.out.println(this.search_byName(inputString()));
                        break;

                    case 2:
                        this.search_byPrice(inputDouble());
                        break;

                    case 3:
                        this.search_byQuantity(inputNumerical());
                        break;

                    case 4:
                        admin.removeSoftware(inputSoftware());
                        break;

                    default:
                        System.out.println("Invalid choice...");
                        break;
                }

            } else {

                System.out.println("0) Exit...");
                System.out.println("1) Add Software");
                System.out.println("2) Remove Software");
                System.out.println("3) Update Software");
                System.out.println("4) Software package arrived at the store");

                switch (inputNumerical())
                {
                    case 0:
                        System.out.println("The operation panel turning off...");
                        return;

                    case 1:
                        addSoftware(inputSoftware());
                        break;

                    case 2:
                        removeSoftware(new Software(inputString(), 0));
                        break;

                    case 3:
                        updateSoftware(new Software(inputString(), 0), inputSoftware());
                        break;

                    case 4:
                        Software temp = inputSoftware();
                        if(!store.contains(temp)) addSoftware(temp);
                        else System.out.println("The store has already have this package");
                        break;

                    default:
                        System.out.println("Invalid choice...");
                        break;
                }
            }
        }
    }

    private Software inputSoftware() {

        System.out.print("Enter the software name: ");
        String name = inputString();
        System.out.print("Enter the software price: ");
        double price = inputDouble();

        return new Software(name, price);
    }

    private String inputString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private int inputNumerical() {

        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print("Please enter integer: ");
            try { return Integer.parseInt(scan.nextLine()); }
            catch (Exception e) { System.out.println("\nPlease enter just integer...\n"); }
        }
    }

    private double inputDouble() {
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print("Please enter integer: ");
            try { return Double.parseDouble(scan.nextLine()); }
            catch (Exception e) { System.out.println("\nPlease enter just integer...\n"); }
        }
    }

    private void login() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Administrator pw: admin");
        System.out.print("Please enter Password: ");

        if(admin.getAdminPw().equals(scan.nextLine())) adminLogin = true;
    }
}