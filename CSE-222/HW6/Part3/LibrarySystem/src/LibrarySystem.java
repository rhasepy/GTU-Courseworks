
import java.util.*;
/**
 * @author Muharrem Ozan Yesiller
 */
public class LibrarySystem {
  /**
   * 
   * Administrator object
   * 
   */
  private final Administrator admin;

  /**
   * admin login query
   * 
   */
  private boolean adminLogin =  false;

  /**
   * Map of Data
   * 
   */
  private final Map<String, Map<String, Set<String>>> lib_info;

  /**
   * No parameter constructor
   * Generete one admin automaticly whose password is "admin"
   */
  public LibrarySystem() {
        lib_info = new HashMap<String, Map<String, Set<String>>>();
        admin = new Administrator("admin", "admin", this);
  }

  /**
   * @return library data for administrator object
   */
  public Map<String, Map<String, Set<String>>> getLibrary() {
        if(adminLogin)
            return lib_info;
        else
            return null;
  }

  /**
   * If admin login success admin add book in system
   */
  public void addBook() {
        if(adminLogin)
            admin.addBook(this.input_book());
  }

  /**
   * Input from admin for book
   * @return book object, for encapsulating book data
   */
  private Book input_book() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the book name: ");
        String name = scan.nextLine();
        System.out.print("Enter the book author: ");
        String author = scan.nextLine();
        System.out.print("Enter the book location: ");
        String location = scan.nextLine();

        return new Book(author, name, location);
  }

  /**
   * Select book from library
   * @return book object, for encapsulating book data
   */
  private Book select_book() {
        Set<String> authorSet = lib_info.keySet();
        int count = 0;

        for(String s : authorSet) {
            Set<String> bookSet = lib_info.get(s).keySet();

            for(String s2 : bookSet) {
                System.out.println(++count + ") " + s2);
            }
        }

        if(count == 0) {
            System.out.println("There is not any book in library...");
            return null;
        }

        System.out.println("Select the book number: ");
        int input = inputNumerical(count);
        int book_index = 1;

        for(String s : authorSet) {
            Set<String> bookSet = lib_info.get(s).keySet();

            for(String s2 : bookSet) {
                if(book_index == input)
                    return new Book(s, s2, lib_info.get(s).get(s2).iterator().next());

                ++book_index;
            }
        }

        return null;
  }

  /**
   * Input location info from admin for updating book location
   * @return location string for updating book location
   */
  private String input_location() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input updated new location: ");
        return scan.nextLine();
  }

  /**
   * remove book method
   * if adminlogin is success then calls administrator's remove book method
   */
  public void removeBook() {
        if(adminLogin) {
            Book temp = this.select_book();

            if(temp != null)
                admin.removeBook(temp);
        }
  }

  /**
   * update book method
   * if adminlogin is success then calls administrator's update book method
   */
  public void updateBook() {
        if(adminLogin) {
            Book temp = select_book();

            if(temp != null)
                admin.updateBook(temp, input_location());
        }
  }

  /**
   * @param bookTitle searchin book name
   */
  public void search_with_title(String bookTitle) {
        Set<String> author_set = lib_info.keySet();

        for(String author : author_set) {
            Set<String> book_set = lib_info.get(author).keySet();

            if(lib_info.get(author).containsKey(bookTitle)) {

                Set<String> location_set = lib_info.get(author).get(bookTitle);
                System.out.println("\nAuthor: " + author);
                System.out.println("Book Name: " + bookTitle);

                int count = 1;
                for(String x : location_set) {
                    System.out.println(count + ")" + "Location: " + x);
                    ++count;
                }
                return;
            }
        }

        System.out.println("There is not book in library...");
  }

  /**
   * @param author search for author
   */
  public void search_with_author(String author) {
        Scanner scan = new Scanner(System.in);

        if(lib_info.containsKey(author))
        {
            System.out.println("\nThe book of " +  author);
            for(String book_name : lib_info.get(author).keySet()) {
                System.out.println(book_name);
            }

            System.out.print("\nInput: ");
            String book_name = scan.nextLine();

            if(!lib_info.get(author).containsKey(book_name)) {
                System.out.println("Invalid book...");
                return;
            }

            Set <String> locaSet = lib_info.get(author).get(book_name);

            System.out.println("The book location/s and status");
            for(String l : locaSet)
                System.out.println(l);
        }

        else
            System.out.println("There is not this author in system...");
  }

  /**
   * login system
   */
  public void login() {
        Scanner scan = new Scanner(System.in);

        do {

            System.out.println("Do you want login library system? [y/n]");
            if(scan.nextLine().equals("n")) {
                System.out.println("The program turning off...");
                return;
            }

            System.out.println("(Administrator password is admin)");
            System.out.println("(Else cases will be recognized normal user)");
            System.out.print("\nEnter Password: ");

            adminLogin = admin.getAdminPw().equals(scan.nextLine());

            operationPanel();

        }while(true);
  }

  /**
   * operation panel
   */
  public void operationPanel() {
        Scanner scan = new Scanner(System.in);
        int operation = 0;

        do {
            System.out.println("\n0) Exit");
            System.out.println("1) Search by book title");
            System.out.println("2) Search by author name");

            if(adminLogin)
            {
                System.out.println("3) Add book");
                System.out.println("4) Remove book");
                System.out.println("5) Update book");
                operation = inputNumerical(5);
            }
            else operation = inputNumerical(3);

            switch (operation)
            {
                case 1:
                    System.out.print("Book name: ");
                    search_with_title(scan.nextLine());
                    break;

                case 2:
                    System.out.println("Author name: ");
                    search_with_author(scan.nextLine());
                    break;

                case 3:
                    addBook();
                    break;

                case 4:
                    removeBook();
                    break;

                case 5:
                    updateBook();
                    break;

                case 0:
                    System.out.println("The operation panel turning off...");
                    break;

                default:
                    break;
            }
        }while (operation != 0);
  }

  /**
   * run program
   */
  public void run() {
 this.login();
  }

  /**
   * numerical input method
   * @param limit max limit of input
   * @return input numeric
   */
  private int inputNumerical(int limit) {
        Scanner scan = new Scanner(System.in);
        int input_number = 0;
        String input;

        do
        {
            System.out.print("Input: ");
            input = scan.nextLine();

            try {
                input_number = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid number...");
            }

            if (input_number > limit)
                System.out.println("Please enter the 0 <= input <= " + limit);

        }while (input_number > limit || input_number < 0);

        return input_number;
  }

}
