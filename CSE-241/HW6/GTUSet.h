#ifndef GTUSET_H_
#define GTUSET_H_

#include "GTUContainer.h"

namespace GTUSTL
{
	template<class T>
	class GTUSet : public GTUContainer<T>
	{
		public:
			GTUSet();
			GTUSet(const GTUSet& rightSide);
			GTUSet& operator =(const GTUSet& rightSide);
			bool empty() const; // Test whether set is empty
			int size() const; // return set size
			int max_size() const;// return capacity
			void insert(const T& Object); // insert element // throws exception std::bad_pafram if there is a problem with insersion
			void erase(const T& eraseObject); // erase object
			void erase(const GTUIterator<T>& eraseIter); // erase iterator
			void erase(const GTUIterator<T>& start_iterator, const GTUIterator<T>& finish_iterator); // erase range of iterator
			void clear();// erase all set
			void printSet() const; // my helper function prints the set
			GTUIterator<T> begin() const; // return start iterator of set
			GTUIterator<T> end() const; // return end iterator of set
			static void print_element(T element); // my helper function to test global functions
	};
}

#endif