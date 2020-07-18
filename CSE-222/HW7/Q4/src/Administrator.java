import java.util.*;

/**
 * Administrator class for precedende of add, remove, update software
 * @author Muharrem Ozan Yeşiller
 */
public class Administrator
{
    /**
     * Admin Id
     */
    private final String Id;

    /**
     * admin pw
     */
    private final String password;

    /**
     * store where administrator's work
     */
    private final SearchTree<Software> store;

    /**
     * @param Id admin id
     * @param password admin pw
     * @param store store object
     */
    public Administrator(String Id, String password, SearchTree<Software> store)
    {
        this.Id = Id;
        this.password = password;
        this.store = store;
    }

    /**
     * get administrator password
     * @return administrator's password
     */
    public String getAdminPw() {  return password; }

    public void addSoftware(Software item) {

        if(store.contains(item))
            store.find(item).incrementCount();
        else
            store.add(item);
    }

    public void removeSoftware(Software item) {

        if(store.contains(item)) {
            Software temp = store.find(item);

            if(temp.getQuantity() > 1) store.find(item).decrementCount();
            else store.remove(temp);
        }
    }

    public void updateSoftware(Software old, Software newItem) {

        Software temp = store.find(old);

        if(temp != null) {
            temp.setQuantity(newItem.getQuantity());
            temp.setName(newItem.getName());
            temp.setPrice(newItem.getPrice());
        }
    }
}
