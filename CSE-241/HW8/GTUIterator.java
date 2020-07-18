/**
* GTUIterator interface.
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW7
*/

public interface GTUIterator<T>
{
    /**
    * Index-wise location of the iterator.
    */
	int index = 0;

	/**
	* Returns true if the iteration has more elements.
	* @return true if the iteration has more elements
	*/
	boolean hasNext();

	/**
	* Returns the next element in the iteration.
	* @return the next element in the iteration
	*/
	T next();

	/**
	* Returns the position of iterator.
	* @return the position of iterator.
	*/
	int getPosition();
}