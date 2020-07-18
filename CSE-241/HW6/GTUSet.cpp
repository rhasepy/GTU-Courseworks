#include "GTUSet.h"

using namespace std;

namespace GTUSTL
{
	template <class T> // GTUVector no parameter constructor
	GTUSet<T>::GTUSet()
				: GTUContainer<T>::GTUContainer()
	{

	}

	template <class T> // GTUVector copy constructor
	GTUSet<T>::GTUSet(const GTUSet& rightSide)
						: GTUContainer<T>::GTUContainer(rightSide)
	{

	}

	template <class T>  // GTUSet = operator
	GTUSet<T>& GTUSet<T>::operator =(const GTUSet& rightSide)
	{
		GTUContainer<T>::operator=(rightSide);
	}

	template <class T> // If the GTUSet is empty ?? /
	bool GTUSet<T>::empty() const
	{
		if(this -> used_ == 0)
			return true;

		return false;
	}

	template <class T> // How much GTUSet has //
	int GTUSet<T>::size() const
	{
		return (this -> used_);
	}

	template <class T> // Can How much GTUVector has for now //
	int GTUSet<T>::max_size() const
	{
		return (this -> capacity_);
	}

	template <class T>
	void GTUSet<T>::insert(const T& Object)
	{
		bool isNotValid = true;
		int index = 0;

		for(GTUIterator<T> traverser (this -> begin()) ; traverser != this -> end() ; ++traverser)
			if((*traverser) == Object) // if the set has already have this object
			{
				throw invalid_argument("function std::GTUSTL::GTUSet<T>::insert(): You can't insert element that object has already have...");
				isNotValid = false;
			}

		if(((this -> size()) < (this -> max_size()))  && (isNotValid)) // if the set don't has object and it have capacity
		{
			*(this -> end()) = Object;
			this -> used_ += 1;
		}

		else if(((this -> size()) >= (this -> max_size()))  && (isNotValid)) // if the set don't have capacity
		{
			this -> capacity_ += 20;
			shared_ptr <T> temp(new T[this -> capacity_], default_delete <T[]>());
			GTUIterator<T> traverser(this -> begin());

			while(traverser != this -> end())
			{
				temp.get()[index] = *traverser;
				++traverser;
				++index;
			}

			this -> data_ = temp;
			*(this -> end()) = Object;
			this -> used_ += 1;
		}
	}

	template <class T>
	void GTUSet<T>::erase(const T& eraseObject)
	{
		bool isErased = false;
		bool isValid = false;
		int index = 0;

		GTUIterator <T> traverser_isValid(this -> begin());

		while(traverser_isValid != this -> end()) // Test the GTUSet has taken OBJECT //
		{
			if(*traverser_isValid == eraseObject)
				isValid = true;

			++traverser_isValid;
		}

		if(isValid) // if the GTUSet has taken OBJECT //
		{
			GTUIterator <T> traverser(this -> begin());

			while(traverser != this -> end())
			{
				if(*traverser != eraseObject) // this block ignore *traverser == eraseObject
				{
					(this -> data_).get()[index] = *traverser;
					++index;
					isErased = true;
				}

				++traverser;
			}
		}

		if(isErased) 
			this -> used_ -= 1;

		else // if the set don't has taken object
			cout << "The GTUSet has not element that you want to delete..." << endl;
	}

	template <class T>
	void GTUSet<T>::erase(const GTUIterator<T>& eraseIter)
	{
		bool isErased = false;
		bool isValid = false;
		int index = 0;

		GTUIterator <T> traverser_isValid(this -> begin());

		while(traverser_isValid != this -> end()) // Test the GTUSet has taken iterator //
		{
			if(traverser_isValid == eraseIter)
				isValid = true;

			++traverser_isValid;
		}

		if(isValid) // if the GTUSet has taken iterator //
		{
			GTUIterator <T> traverser(this -> begin());

			while(traverser != this -> end())
			{
				if(traverser != eraseIter)
				{
					(this -> data_).get()[index] = *traverser;
					++index;
				}

				else  // if the don't enter up if // so traverser == eraseIter //
					isErased = true;

				++traverser;
			}
		}

		if(isErased) 
			this -> used_ -= 1;

		else // if the set don't has taken object
			cout << "The GTUSet has not element that you want to delete..." << endl;
	}

	template <class T>
	void GTUSet<T>::erase(const GTUIterator<T>& start_iterator, const GTUIterator<T>& finish_iterator)
	{
		bool isErased = false;
		bool isValid = false;
		int index = 0;

		GTUIterator <T> traverser_isValid(this -> begin()); // Test the GTUSet has taken iterator //

		while(traverser_isValid != this -> end())
		{
			if(traverser_isValid == start_iterator)
				isValid = true;

			++traverser_isValid;
		}

		if(isValid)
		{
			GTUIterator <T> traverser(this -> begin());

			while(traverser != this -> end())
			{
				if(traverser != start_iterator)
				{
					(this -> data_).get()[index] = *traverser;
					++index;
					++traverser;
				}

				else // if the traverser has reached the beginning of the range to be deleted
				{
					while(traverser != finish_iterator) // the traverse untill end of range
						++traverser;

					isErased = true;
				}
			}
		}

		if(isErased) 
			this -> used_ -= 1;

		else // if the set don't has taken object
			cout << "The GTUSet has not element that you want to delete..." << endl;
	}

	template <class T>
	void GTUSet<T>::clear()
	{
		if(this -> used_ != 0)
		{
			this -> erase(this -> begin(), this -> end()); // erase from start to finish
			this -> used_ = 0;
		}
	}

	template <class T>
	void GTUSet<T>::printSet() const
	{
		GTUIterator<T> traverser(this -> begin());

		if(this -> empty())
		{
			cout << "This Set empty" << endl;
			return;
		}

		while(traverser != this -> end())
		{
			cout << (*traverser) << " ";
			++traverser;
		}

		cout << endl;
	}

	template <class T>
	GTUIterator<T> GTUSet<T>::begin() const // for return begin iterator
	{
		GTUIterator<T> returnIter((this -> data_).get());

		return returnIter;
	}

	template <class T>
	GTUIterator<T> GTUSet<T>::end() const // for return begin + used iterator //
	{
		GTUIterator<T> returnIter((this -> data_).get() + this -> used_);
		
		return returnIter;
	}

	template <class T>
	void GTUSet<T>::print_element(T element) // my helper function to test global function
	{
		cout << element << " ";
	}
}