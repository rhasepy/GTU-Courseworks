#ifndef GTUVECTOR_H_
#define GTUVECTOR_H_

#include "GTUContainer.h"

namespace GTUSTL
{
	template<class T>
	class GTUVector : public GTUContainer<T>
	{
		public:
			GTUVector();
			GTUVector(const GTUVector& rightSide);
			GTUVector& operator =(const GTUVector& rightSide);
			bool empty() const; // Test whether vector is empty
			int size() const; // return vector size
			int max_size() const; // return vector capacity
			void insert(const T& Object); // insert element
			void erase(const GTUIterator<T>& erasing_iter); // erase iterator
			void erase(const GTUIterator<T>& start_iterator, const GTUIterator<T>& finish_iterator); // erase range of iterator
			void clear(); // erase all vector
			T operator [](const int& index) const; // return T value of index of vector
			void printVector() const; // prints the all vector
			GTUIterator<T> begin() const; // return start iterator
			GTUIterator<T> end() const; // return end iterator
			static void print_element(T element); // my helper function to test global functions //
			static bool isNegative(T element); // my helper function to test global function //
	};
}

#endif