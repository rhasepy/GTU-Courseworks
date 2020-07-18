/**
* GTUSet generic class extended GTUContainer Abstract class
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HW7
*/

import java.lang.reflect.Array;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

public class GTUSet<T> extends GTUContainer<T>
{
	/**
	* This define is capacity to increase
	*/
	private static final int GROW_SIZE = 20;

	/**
	* The method extend T[], if the capacity is full
	*/
	private void grow()
	{
		this.data_ = Arrays.copyOf(this.data_, capacity_+GROW_SIZE);
		capacity_ += GROW_SIZE;
	}

	/**
	* No parameter constructor, capacity assign 10 default
	*/
	@SuppressWarnings("unchecked")  // This statement added for Casting with generics arrays issues.
	public GTUSet()
	{
		this(10); // Delegation
	}

	/**
	* Constructor that accepts size value. User of this class, can construct with GtuSet(size) with initial size value.
	* @param size capacity value that will be initialized.
	* @param virtualParam is not acceptable parameter. Do not provide a value for that param.
	* @throws IllegalArgumentException If second paramater provided to this constructor.
	*/
	@SuppressWarnings("unchecked")  // This statement added for Casting with generics arrays issues.
	public GTUSet(int size, T... virtualParam)
	{
		if(virtualParam.length > 0)
			throw new IllegalArgumentException("Do not provide value for virtualParam. It is not acceptable!");

		capacity_ = size;

		Class<?> type = virtualParam.getClass().getComponentType();
		data_ = (T [])(Array.newInstance(type, capacity_));
		used_ = 0;
	}
	
	private class GTUSetIterator implements GTUIterator<T>
	{
		private int index = 0;

		@Override
		public boolean hasNext()
		{
			if(index < used_)
				return true;

			return false;
		}

		@Override
		public T next()
		{
			return data_[index++];
		}

		@Override
		public int getPosition()
		{
			return index;
		}
	}

	/**
	* @return true, if the container is empty
	* @return false, if the container is not empty
	*/
	@Override
	public boolean empty()
	{
		if(used_ == 0)
			return true;

		return false;
	}

	/**
	* @return number of size
	*/
	@Override
	public int size()
	{
		return used_;
	}

	/**
	* @return number of capacity
	*/
	@Override
	public int max_size()
	{
		return capacity_;
	}

	/**
	* @param T, object that will be inserted
	*/
	@Override
	@SuppressWarnings("unchecked")  // This statement added for Casting with generics arrays issues.
	public void insert(T object)
	{
		if(!(this.contains(object)))
		{
			if(this.size() + 1 == this.max_size())
				this.grow();

			this.data_[used_] = object;
			++used_;
		}

		else
			throw new IllegalArgumentException("You can't insert element that object has already have...");
	}

	/**
	* @param T, object that will be erased
	*/
	@Override
	public void erase(T object)
	{
		if(this.used_ > 0)
		{
			boolean isValid = false;
			GTUIterator<T> traverser = this.iterator();

			while(traverser.hasNext())
			{
				T value = traverser.next();
				if(value == object)
				{
					isValid = true;
					break;
				}
			}

			if(isValid)
			{
				for(int i = traverser.getPosition() ; i < this.size() ; ++i)
					data_[i-1] = data_[i];

				--used_;
			}
		}
	}

	/**
	* The method erases all content of containers
	*/
	@Override
	public void clear()
	{
		this.used_ = 0;
	}

	/**
	* @return Iterator that be begin iterator
	*/
	@Override
	public GTUIterator<T> iterator()
	{
		return new GTUSetIterator();
	}

	/**
	* @param o that will be tested is valid or not valid in container
	* @return true, the container has object
	* @return false, the container has not object
	*/
	@Override
	public boolean contains(Object object)
	{
		GTUIterator<T> traverser = this.iterator();

		while(traverser.hasNext())
		{
			T value = traverser.next();

			if(value == object)
				return true;
		}

		return false;
	}

	/**
	* Overrided toString method
	* @return string of contents of set
	*/
	@Override
	public String toString()
	{
		StringBuilder temp = new StringBuilder();

		GTUIterator<T> traverser = this.iterator();

		while(traverser.hasNext())
		{
			T val = traverser.next();
			temp.append(val);
			temp.append(" ");
		}

		String returnString = temp.toString();

		return returnString;
	}
}