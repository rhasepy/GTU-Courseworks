/**
* GTUContainer abstract class
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW7
*/

import java.lang.reflect.Array;
import java.lang.IllegalArgumentException;

public abstract class GTUContainer <T>
{	
	/**
	* Capacity of set or vector
	*/
	protected int capacity_;

	/**
	* Size of set or vector
	*/
	protected int used_;

	/**
	* Generic type array to keep set or vector data
	*/
	protected T[] data_;

	/**
	* @return true, if the container is empty
	* @return false, if the container is not empty
	*/
	public abstract boolean empty();

	/**
	* @return number of size
	*/
	public abstract int size();

	/**
	* @return number of capacity
	*/
	public abstract int max_size();

	/**
	* @param T, object that will be inserted
	*/
	public abstract void insert(T object);

	/**
	* @param T, object that will be erased
	*/
	public abstract void erase(T object);

	/**
	* The method erases all content of containers
	*/
	public abstract void clear();

	/**
	* @return Iterator that be begin iterator
	*/
	public abstract GTUIterator<T> iterator();

	/**
	* @param o that will be tested is valid or not valid in container
	* @return true, the container has object
	* @return false, the container has not object
	*/
	public abstract boolean contains(Object o);
}