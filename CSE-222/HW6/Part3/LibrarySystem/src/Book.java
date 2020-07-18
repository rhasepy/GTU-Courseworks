
/**
 * I have wrote this class for encapsulating book information
 * @author Muharrem Ozan Yesiller
 */
public class Book {
  /**
   * author name
   * 
   */
  private final String author;

  /**
   * book name
   * 
   */
  private final String name;

  /**
   * book location
   * 
   */
  private String location;

  /**
   * book constructor
   * @param author author name
   * @param name book name
   * @param location location info
   */
  public Book(String author, String name, String location) {
        this.author = author;
        this.name = name;
        this.location = location;
  }

  /**
   * @return author name
   */
  public String getAuthor() {
 return author;
  }

  /**
   * @return book name
   */
  public String getName() {
 return name;
  }

  /**
   * @return book location
   */
  public String getLocation() {
 return location;
  }

  /**
   * @param new_loca set new location of book
   */
  public void setLocation(String new_loca) {
 this.location = new_loca;
  }

  /**
   * Overriding to string method
   * @return string of book info
   */
  @Override
  public String toString() {
        return "Book Name: " + name + "\n" +
                "Author: " + author + "\n" +
                "Location: " + location + "\n";
  }

  /**
   * Overriding to equals method
   * @param obj parameter for compare
   * @return if book author and name and location same is object and this book eqauls return true
   * @return otherwise return false
   */
  @Override
  public boolean equals(Object obj) {
        if(obj instanceof Book) {
            Book temp = (Book) obj;
            return temp.getAuthor().equals(this.author) &&
                    temp.getName().equals(this.name) &&
                    temp.getLocation().equals(this.location);
        }
        return false;
  }

}
