#include "GTUVector.h"

using namespace std;

namespace GTUSTL
{
	template<class T> // GTUVector no parameter constructor
	GTUVector<T>::GTUVector()
					: GTUContainer<T>::GTUContainer()
	{

	}

	template <class T> // GTUVector copy constructor
	GTUVector<T>::GTUVector(const GTUVector& rightSide)
						: GTUContainer<T>::GTUContainer(rightSide)
	{

	}

	template <class T> // GTUVector = operator
	GTUVector<T>& GTUVector<T>::operator =(const GTUVector& rightSide)
	{
		GTUContainer<T>::operator=(rightSide);
	}

	template <class T> // If the GTUVector is empty ?? /
	bool GTUVector<T>::empty() const
	{
		if(this -> used_ == 0)
			return true;

		else
			return false;
	}

	template <class T> // How much GTUVector has //
	int GTUVector<T>::size() const
	{
		return (this -> used_);
	}

	template <class T> // Can How much GTUVector has for now //
	int GTUVector<T>::max_size() const
	{
		return (this -> capacity_);
	}

	template <class T> // insert element within GTUVector //
	void GTUVector<T>::insert(const T& Object)
	{
		int index = 0;

		if(this -> used_  >=  this -> capacity_) // if the vector capacity is full //
		{
			this -> capacity_ += 20; // The program extend capacity of GTUVector //
			shared_ptr <T> temp(new T[this -> capacity_]);
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

		else if(this -> used_ < this -> capacity_) // If GTUVector have enoguh capacity //
		{
			*(this -> end()) = Object;
			this -> used_ += 1;
		}
	}

	template <class T>
	void GTUVector<T>::erase(const GTUIterator<T>& erasing_iter)
	{
		bool isValid = false;
		bool isErased = false;
		int erase_count = 0;
		int index = 0;

		GTUIterator <T> traverser_isValid(this -> begin());

		while(traverser_isValid != this -> end()) // Test the GTUVector has taken iterator //
		{
			if(traverser_isValid == erasing_iter)
				isValid = true;

			++traverser_isValid;
		}

		if(isValid) // if the GTUVector has taken iterator //
		{
			GTUIterator <T> traverser(this -> begin());
			shared_ptr <T> temp(new T[this -> capacity_]);

			while(traverser != this -> end())
			{
				if(traverser != erasing_iter)
				{
					temp.get()[index] = *traverser;
					++index;
				}

				else if(!isErased) // if the don't enter up if // so traverser == erasing_iter
				{
					isErased = true;
				}

				else if(traverser == erasing_iter && (isErased == true))
				// The block prevent erase the same elements //
				{
					temp.get()[index] = *traverser;
					++index;
				}

				++traverser;
			}

			GTUIterator <T> fill_back_iterator(this -> begin());
			index = 0;

			while(fill_back_iterator != this -> end()) // fill temp to this //
			{
				(this -> data_).get()[index] = temp.get()[index];
				++index;
				++fill_back_iterator;
			}
		}

		if(isErased)
			this -> used_ -= 1;

		else // if the vector don't has taken object //
			cout << "The GTUVector has not element that you want to delete..." << endl;
	}

	template <class T>
	void GTUVector<T>::erase(const GTUIterator<T>& start_iterator, const GTUIterator<T>& finish_iterator)
	{
		bool isValid = false;
		bool isErased = false;
		int index = 0;

		GTUIterator <T> traverser_isValid(this -> begin());

		while(traverser_isValid != this -> end()) // Test the GTUVector has taken iterator //
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

				else  // if the traverser has reached the beginning of the range to be deleted
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
	void GTUVector<T>::clear()
	{
		if(this -> used_ != 0)
		{
			this -> erase(this -> begin(), this-> end()); // erase from start to finish
			this -> used_ = 0;
		}
	}

	template <class T>
	T GTUVector<T>::operator [](const int& index) const // [] overload for GTUVector 
	{
		GTUIterator <T> traverser = this -> begin();

		for(int i = 0 ; i < index ; ++i) //return object of index.
			++traverser;

		return *(traverser.get_traverser());
	}

	template <class T>
	void GTUVector<T>::printVector() const
	{
		if(this -> empty())
		{
			cout << "This Vector empty" << endl;
			return;
		}

		for(int i = 0 ; i < this -> used_ ; ++i)
			cout << (*this)[i] << " "; // I used [] operator //

		cout << endl;
	}

	template <class T>
	GTUIterator<T> GTUVector<T>::begin() const
	{
		GTUIterator<T> returnIter((this -> data_).get()); // for return begin iterator

		return returnIter;
	}

	template <class T>
	GTUIterator<T> GTUVector<T>::end() const
	{
		GTUIterator<T> returnIter((this -> data_).get() + this -> used_); // for return begin + used iterator //

		return returnIter;
	}

	template <class T>
	void GTUVector<T>::print_element(T element) // my helper function to test global function
	{
		cout << element << " ";
	}

	template <class T>
	bool GTUVector<T>::isNegative(T element) // my helper function to test global function
	{
		if(element < 0)
			return true;

		return false;
	}
}