#ifndef GTUCONTAINER_H
#define GTUCONTAINER_H

#include <iostream>
#include <exception>
#include <memory>
#include "GTUIterator.h"
#include "GTUIteratorConst.h"

using namespace std;

namespace GTUSTL
{
	template<class T>
	class GTUContainer
	{
		public:
			GTUContainer();
			GTUContainer(const GTUContainer& copyObject);
			virtual ~GTUContainer();
			GTUContainer& operator =(const GTUContainer& rightSide);
			virtual bool empty() const = 0; // Test whether container is empty
			virtual int size() const = 0; // return container size
			virtual int max_size() const = 0; // return max size
			virtual void insert(const T& object) = 0; // insert element
			// throws exception std::bad_pafram if there is a problem with insersion
			virtual void erase(const GTUIterator<T>& object) = 0; // erase element
			virtual void clear() = 0; // clear container
			virtual GTUIterator<T> begin() const = 0; // return iterator to begining
			virtual GTUIterator<T> end() const = 0; // return iterator to end

		protected:
			int capacity_; // total capacity
			int used_;	// used piece of capacity
			shared_ptr<T> data_; // data in container
	};
}

#endif