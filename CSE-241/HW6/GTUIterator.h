#ifndef GTUITERATOR_H_
#define GTUITERATOR_H_

#include <memory>

using namespace std;

namespace GTUSTL
{
	template<class T>
	class GTUIterator
	{
		public:
			GTUIterator(T* traverser);
			GTUIterator();
			GTUIterator& operator =(const GTUIterator& rightSide);
			T& operator *() const;
			T* operator -> () const;
			GTUIterator& operator ++(); // adress increment prefix
			GTUIterator& operator ++(int ignored); // adress increment postfix
			GTUIterator& operator +(int value);
			GTUIterator& operator --() ; // adres decrement prefix
			GTUIterator& operator --(int ignored); // adress decrement postfix
			bool operator ==(const GTUIterator& rightSide) const;
			bool operator !=(const GTUIterator& rightSide) const;
			T* get_traverser() const;

		private:
			T* traverser;
	};
}

#endif