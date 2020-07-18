#ifndef GTUITERATORCONST_H_
#define GTUITERATORCONST_H_

#include <memory>

using namespace std;

namespace GTUSTL
{
	template<class T>
	class GTUIteratorConst
	{
		public:
			GTUIteratorConst(T* traverser);
			GTUIteratorConst();
			GTUIteratorConst& operator =(const GTUIteratorConst& rightSide);
			const T& operator * () const;
			const T* operator -> () const;
			GTUIteratorConst& operator ++();// increment adress
			GTUIteratorConst& operator ++(int ignored); // increment adress postfix
			GTUIteratorConst& operator +(int value); // my helper operator
			GTUIteratorConst& operator --(); // decrement adress 
			GTUIteratorConst& operator --(int ignored); // decrement adress postfix
			bool operator ==(const GTUIteratorConst& rightSide) const; // if the two iterator are same
			bool operator !=(const GTUIteratorConst& rightSide) const; // my helper function (return !empty)
			T* get_traverser() const; // get iterator data
			
		private:
			T* traverser;
	};
}

#endif