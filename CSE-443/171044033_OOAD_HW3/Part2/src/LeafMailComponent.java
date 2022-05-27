/**
 * Leaf component class
 */
public class LeafMailComponent extends MailComponent
{
	/**
	 * name of student
	 */
	 private String name;

	/**
	 * mail of student
	 */
	private String mail;

	/**
	 * consturctor
	 * @param name, name of student
	 * @param mail, mail of student
	 */
	public LeafMailComponent(String name, String mail){
		 this.name = name;
		 this.mail = mail;
	 }

	/**
	 * getter
	 * @return name
	 */
	public String getName() { return name; }

	/**
	 * getter
	 * @return email
	 */
	public String getMail() { return mail; }

	/**
	 * print method
	 */
	public void print() { System.out.println(this); }

	/**
	 * override to string method
	 * @return string of object
	 */
	@Override
	public String toString() {
		return "-> " + this.getName() + ", " + this.getMail();
	}
}