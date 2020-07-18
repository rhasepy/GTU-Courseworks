#include "GTUIteratorConst.h"

namespace GTUSTL
{
	template<class T> // constructor taken data and assing
	GTUIteratorConst<T>::GTUIteratorConst(T* traverser)
					: traverser(traverser)
	{
				
	}

	template<class T> // no parameter constructor data is null ptr
	GTUIteratorConst<T>::GTUIteratorConst()
					: traverser(nullptr)
	{

	}

	template<class T> // assingment operator
	GTUIteratorConst<T>& GTUIteratorConst<T>::operator =(const GTUIteratorConst& rightSide) 
	{
		this -> traverser = rightSide.traverser;

		return (*this);
	}
 
	template<class T> // return T reference 
	const T& GTUIteratorConst<T>::operator * () const 
	{
		return *traverser;
	}

	template<class T> // return T pointer
	const T* GTUIteratorConst<T>::operator -> () const 
	{
		return traverser;
	}

	template<class T> // adress increment
	GTUIteratorConst<T>& GTUIteratorConst<T>::operator ++() 
	{
		traverser++;

		return (*this);
	}

	template<class T> // adress increment
	GTUIteratorConst<T>& GTUIteratorConst<T>::operator ++(int ignored) 
	{
		GTUIteratorConst temp = (*this);
		traverser++;

		return temp;
	}

	template<class T> // adress decrement
	GTUIteratorConst<T>& GTUIteratorConst<T>::operator --() 
	{
		traverser--;

		return (*this);
	}

	template<class T> // adress decremenet
	GTUIteratorConst<T>& GTUIteratorConst<T>::operator --(int ignored) 
	{
		GTUIteratorConst temp = (*this);
		traverser--;

		return temp;
	}

	template<class T> // is two iterator are same
	bool GTUIteratorConst<T>::operator ==(const GTUIteratorConst& rightSide) const 
	{
		return (this -> traverser == rightSide.traverser);
	}

	template<class T>
	bool GTUIteratorConst<T>::operator !=(const GTUIteratorConst& rightSide) const 
	{
		// my helper overload that i used to loop // e.g. while(iter1 != iter2)
		return !((*this) == rightSide);
	}

	template<class T> // return data of iterator
	T* GTUIteratorConst<T>::get_traverser() const 
	{
		return traverser;
	}

	template<class T> // my helper operator
	GTUIteratorConst<T>& GTUIteratorConst<T>::operator +(int value)
	{
		traverser += value;

		return (*this);
	}
}