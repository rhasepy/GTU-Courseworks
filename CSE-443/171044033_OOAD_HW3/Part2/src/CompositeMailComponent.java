import java.util.Iterator;
import java.util.LinkedList;

/**
 * Composite Mail Component Class
 */
public class CompositeMailComponent extends MailComponent {

	/**
	 * list of leaf mail adresses
	 */
	 private LinkedList<MailComponent> compositeMailAdresses = new LinkedList<MailComponent>();

	/**
	 * composite mail adress
	 */
	private String mailAdresses;

	/**
	 * Consturctor
	 * @param mailAdresses, composite mail adress
	 */
	public CompositeMailComponent(String mailAdresses) { this.mailAdresses = mailAdresses; }

	/**
	 * get composite mail adress
	 * @return composite name of mail adress
	 */
	public String getMail() { return mailAdresses; }

	/**
	 * i th child
	 * @param i, index
	 * @return, Mail Component
	 */
	public MailComponent getChild(int i) { return this.compositeMailAdresses.get(i); }

	/**
	 * remove method
	 * @param compositeMailAdresses remove element in composite
	 */
	public void remove(MailComponent compositeMailAdresses) { this.compositeMailAdresses.remove(compositeMailAdresses); }

	/**
	 * add method
	 * @param compositeMailAdresses add element in composite
	 */
	public void add(MailComponent compositeMailAdresses) { this.compositeMailAdresses.add(compositeMailAdresses); }

	/**
	 * recursive print method composite by composite
	 */
	 public void print() {
		 System.out.println("All " + getMail() + ":");
		 Iterator<MailComponent> iterator = this.compositeMailAdresses.iterator();

		 while (iterator.hasNext()) { iterator.next().print(); }
		 System.out.println("**************************************** End " + getMail());
	 }
}
