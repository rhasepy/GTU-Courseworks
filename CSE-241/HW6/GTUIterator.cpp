#include "GTUIterator.h"

namespace GTUSTL
{
	template <class T> // constructor take data and assing
	GTUIterator<T>::GTUIterator(T* traverser)
						: traverser(traverser)
	{

	}

	template <class T> // no parameter constructor data is nullptr
	GTUIterator<T>::GTUIterator()
						: traverser(nullptr)
	{

	}

	template <class T> // assingment operator
	GTUIterator<T>& GTUIterator<T>::operator =(const GTUIterator& rightSide) 
	{
		if(this == &rightSide)
			return (*this);

		this -> traverser = rightSide.traverser;

		return (*this);
	}

	template <class T> // return T reference
	T& GTUIterator<T>::operator *() const 
	{
		return *traverser;
	}

	template <class T> //return T* pointer
	T* GTUIterator<T>::operator -> () const 
	{
		return traverser;
	}

	template <class T>
	GTUIterator<T>& GTUIterator<T>::operator ++()  // adress increment prefix
	{
		++traverser;

		return (*this);
	}

	template <class T>
	GTUIterator<T>& GTUIterator<T>::operator ++(int ignored)  // adress increment postfix
	{
		GTUIterator temp = (*this);
		++traverser;

		return temp;
	}

	template <class T>
	GTUIterator<T>& GTUIterator<T>::operator --()  // adres decrement prefix
	{
		--traverser;

		return (*this);
	}

	template <class T>
	GTUIterator<T>& GTUIterator<T>::operator --(int ignored)  // adress decrement postfix
	{
		GTUIterator temp = (*this);
		--traverser;

		return temp;
	}

	template <class T>
	bool GTUIterator<T>::operator ==(const GTUIterator& rightSide) const 
	{
		return (this -> traverser == rightSide.traverser);
	}

	template <class T>
	bool GTUIterator<T>::operator !=(const GTUIterator& rightSide) const 
	{
		// my helper overload that i used to loop // e.g. while(iter1 != iter2)
		return !((*this) == rightSide);
	}

	template <class T> // return data of iterator
	T* GTUIterator<T>::get_traverser() const 
	{
		return traverser;
	}

	template <class T> // my helper operator
	GTUIterator<T>& GTUIterator<T>::operator +(int value)
	{
		traverser += value;

		return (*this);
	}
}