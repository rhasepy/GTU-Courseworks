
import java.util.*;
/**
 * Administrator class for precedende of add, removei update book
 * @author Muharrem Ozan Yeşiller
 */
public class Administrator {
  /**
   * Admin Id
   * 
   */
  private final String Id;

  /**
   * admin pw
   * 
   */
  private final String password;

  /**
   * 
   * library where administrator's work
   * 
   */
  private final LibrarySystem library;

  /**
   * @param Id admin id
   * @param password admin pw
   * @param lib library object
   */
  public Administrator(String Id, String password, LibrarySystem lib) {
        this.Id = Id;
        this.password = password;
        this.library = lib;
  }

  /**
   * add book method
   * @param book to be add book
   */
  public void addBook(Book book) {
        if(!library.getLibrary().containsKey(book.getAuthor()))
            library.getLibrary().put(book.getAuthor(), new HashMap<>());
        else
            library.getLibrary().put(book.getAuthor(), library.getLibrary().get(book.getAuthor()));

        if(!library.getLibrary().get(book.getAuthor()).containsKey(book.getName())) {
            library.getLibrary().get(book.getAuthor()).put(book.getName(), new HashSet<>());
        }

        library.getLibrary().get(book.getAuthor()).get(book.getName()).
                add(book.getLocation());
  }

  /**
   * remove book method
   * @param book to be remove book
   */
  public void removeBook(Book book) {
        if(library.getLibrary().get(book.getAuthor()).get(book.getName()).size() == 1) {
            library.getLibrary().get(book.getAuthor()).remove(book.getName());

            if(library.getLibrary().get(book.getAuthor()).size() == 0) {
                library.getLibrary().remove(book.getAuthor());
            }
        }
        else
            library.getLibrary().get(book.getAuthor()).get(book.getName()).remove(book.getLocation());
  }

  /**
   * update book method
   * @param book to be update book
   * @param location to be update location
   */
  public void updateBook(Book book, String location) {
        library.getLibrary().get(book.getAuthor()).get(book.getName()).remove(book.getLocation());
        library.getLibrary().get(book.getAuthor()).get(book.getName()).add(location);
  }

  /**
   * get administrator password
   * @return administrator's password
   */
  public String getAdminPw() {
  return password;
  }

}
