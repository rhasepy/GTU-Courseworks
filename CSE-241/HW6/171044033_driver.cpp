#include "GTUContainer.cpp"
#include "GTUVector.cpp"
#include "GTUSet.cpp"
#include "GTUIterator.cpp"
#include "GTUIteratorConst.cpp"

using namespace std;
using namespace GTUSTL;

namespace
{
	template <class GTUIterator, class T>
	GTUIterator find(GTUIterator begin, GTUIterator end, const T& GTUType);

	template <class GTUIterator, class Function>
	Function for_each(GTUIterator begin, GTUIterator end, Function func);

	template <class GTUIterator, class UnaryPredicate>
	GTUIterator find_if(GTUIterator begin, GTUIterator end, UnaryPredicate prediction);
}

int main()
{
	cout << "------------------------------TEST VECTOR----------------------------------" << endl;
	GTUVector<char> testVector;

	cout << "TRY ERASE ELEMENT EMPTY VECTOR" << endl;
	cout << "RESULT: ";
	testVector.erase(testVector.begin());
	cout << "-----------------------" << endl;
	testVector.insert('G');
	testVector.printVector();
	testVector.insert('T');
	testVector.printVector();
	testVector.insert('U');
	testVector.printVector();
	testVector.insert('V');
	testVector.printVector();
	testVector.insert('E');
	testVector.printVector();
	testVector.insert('C');
	testVector.printVector();
	testVector.insert('T');
	testVector.printVector();
	testVector.insert('O');
	testVector.printVector();
	testVector.insert('R');
	testVector.printVector();

	cout << "USED: " << testVector.size() << endl;

	if(testVector.empty())
		cout << "TEST UNSUCCESSED..." << endl;

	cout << "------------------------" << endl;
	cout << "Erasing '" << *(testVector.begin()) <<"'..." << endl;
	testVector.erase(testVector.begin());
	testVector.printVector();
	cout << "------------------------" << endl;

	cout << "Erasing '" << *(testVector.begin() + 4) <<"'..." << endl;
	testVector.erase(testVector.begin() + 4);
	testVector.printVector();
	cout << "------------------------" << endl;

	cout << "Erasing '" << *(testVector.begin() + 4) <<"'..." << endl;
	testVector.erase(testVector.begin() + 4);
	testVector.printVector();
	cout << "------------------------" << endl;


	testVector.clear();
	cout << "Cleared Vector..." << endl;
	testVector.printVector();
	cout << "------------------------" << endl;

	cout << "USED: " << testVector.size() << endl;

	if(testVector.empty())
		cout << "TEST SUCCESSED..." << endl;

	cout << "---------------------------------------------------------------------------" << endl;
	cout << endl;

	cout << "------------------------------TEST SET-------------------------------------" << endl;
	GTUSet<string> testSet;

	try
	{
		testSet.insert("Muharrem");
		testSet.printSet();
		testSet.insert("Ozan");
		testSet.printSet();
		testSet.insert("Yesiller");
		testSet.printSet();
		testSet.insert("171044033");
		testSet.printSet();
		testSet.insert("GTU");
		testSet.printSet();
		testSet.insert("171044033");
		testSet.printSet();
	}

	catch(const exception &set_exception)
	{
		cout << endl;
		cout << set_exception.what() << endl;
		cout << endl;
	}

	cout << "USED: " << testSet.size() << endl;

	if(testSet.empty())
		cout << "TEST UNSUCCESSED..." << endl;

	cout << "------------------------" << endl;
	testSet.erase("Muharrem");
	cout << "Erasing 'Muharrem'..." << endl;
	testSet.printSet();
	cout << "------------------------" << endl;

	testSet.erase("GTU");
	cout << "Erasing 'GTU'..." << endl;
	testSet.printSet();
	cout << "------------------------" << endl;

	cout << "Erasing '" << *(testSet.begin() + 1) << "'..." << endl;
	testSet.erase(testSet.begin() + 1);
	testSet.printSet();
	cout << "------------------------" << endl;

	testSet.clear();
	cout << "Cleared Set..." << endl;
	testSet.printSet();
	cout << "------------------------" << endl;

	cout << "USED: " << testSet.size() << endl;

	if(testSet.empty())
		cout << "TEST SUCCESSED..." << endl;

	cout << "---------------------------------------------------------------------------" << endl;
	cout << endl;
	cout << "--------------------------TEST GLOBAL FUNCTION-----------------------------" << endl;
	GTUVector <int> testVector2;

	testVector2.insert(2);
	testVector2.insert(3);
	testVector2.insert(100);
	testVector2.insert(23);
	testVector2.insert(456);
	testVector2.insert(34);
	testVector2.insert(9);
	testVector2.insert(0);
	testVector2.insert(-5);
	testVector2.insert(-765);
	testVector2.insert(43);
	testVector2.insert(82);
	testVector2.insert(-10);
	testVector2.insert(1);
	testVector2.insert(2);
	testVector2.insert(100);
	testVector2.insert(-765);

	cout << "-----------prinf of GTUVector----------" << endl;
	testVector2.printVector();
	cout << "---------------------------------------" << endl << endl;


	cout << "-----------print using for_each--------" << endl;
	for_each(testVector2.begin(), testVector2.end(), testVector2.print_element);
	/* i used for_each because,
	it calls the function that print one element as it travels over itself, and actually print all elements.*/
	cout << endl;
	cout << "---------------------------------------" << endl << endl;


	cout << "-----------print using find------------" << endl;

	GTUIterator<int> vectBegin(testVector2.begin());
	GTUIterator<int> vectEnd(testVector2.end());

	while(vectBegin != vectEnd)
	{
		cout << *find(vectBegin, vectEnd, *(vectBegin.get_traverser())) << " ";
		// If I ask it to return itself while traveling on it, I will actually return each element.
		// And i print this return //
		++vectBegin;
	}

	cout << endl;
	cout << "---------------------------------------" << endl << endl;

	cout << "-------isNegative using find_if--------" << endl;
	GTUIterator <int> vectBegin2(testVector2.begin());
	GTUIterator <int> vectEnd2(testVector2.end());
	GTUIterator <int> findNegativeIter = find_if(vectBegin2, vectEnd2, (testVector2.isNegative));
	// if it encounters a negative value while traveling on it, it returns that value.

	if(findNegativeIter != vectEnd2)
		cout << "First negatie value is " << *findNegativeIter << endl;
	else
		cout << "The Vector has not any negative number..." << endl;
	cout << "---------------------------------------" << endl << endl;


	cout << "---------------------------------------------------------------------------" << endl;
}

namespace
{
	template <class GTUIterator, class T>
	GTUIterator find(GTUIterator begin, GTUIterator end, const T& lookedFor)
	{
		while(begin != end) // traverse on each 
		{
			if((*begin) == lookedFor) // if the find lookedFor return this //
				return begin;

			++begin;
		}

		return end;
	}

	template <class GTUIterator, class Function>
	Function for_each(GTUIterator begin, GTUIterator end, Function func)
	{
		while(begin != end) // traverse on each
		{
			func((*begin)); // calls this function at every step

			++begin;
		}

		return func;
	}

	template <class GTUIterator, class UnaryPredicate>
	GTUIterator find_if(GTUIterator begin, GTUIterator end, UnaryPredicate prediction)
	{
		while(begin != end) // traverse on each
		{
			if(prediction(*begin)) // if the prediction is true //
				return begin;

			++begin;
		}

		return end; // if the prediction is false
	} 
}