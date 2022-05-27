/**
 * Abstract Mail Component Class
 */
public abstract class MailComponent
{
	/**
	 * add element
	 * @param mailComponent mail element (composite or leaf)
	 */
	public void add(MailComponent mailComponent) { throw new UnsupportedOperationException(); }

	/**
	 * remove element
	 * @param mailComponent mail element (composite or leaf)
	 */
	public void remove(MailComponent mailComponent) { throw new UnsupportedOperationException(); }

	/**
	 * get element
	 * @param i th index
	 * @return child of index th in the structure
	 */
	public MailComponent getChild(int i) { throw new UnsupportedOperationException(); }

	/**
	 * print method
	 */
	public void print() { throw new UnsupportedOperationException(); }
}