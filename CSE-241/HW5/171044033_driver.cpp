#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <string>
#include <cstring>
#include <vector>
#include "AbstractBoard.h"
#include "BoardArray1D.h"
#include "BoardArray2D.h"
#include "BoardVector.h"

using namespace std;
using namespace NPuzzle;

namespace
{
	template <int N> // i used template because i find size of dynamic array of pointers
	bool gtu_global_function(AbstractBoard* (&objects)[N])
	{
		int size = sizeof(objects) / sizeof(AbstractBoard*);
		int first = 0;
		int final = size - 1;
		bool flag = false;

		for(int i = 0 ; i < size ; ++i)
			if(objects[i] -> isSolved())
				flag = true;

		if(flag == false)
		{
			cout << "This array of pointers not contain solution..." << endl;
			return false;
		}

		else
		{
			for(int i = size-1 ; i > 0 ; --i)
			{
				//if the array contains result
				//if the board reverse move from result, board must be first board
				if(objects[i] -> getSize_i() == objects[i-1] -> getSize_i() && objects[i] -> getSize_j() == objects[i-1] -> getSize_j())
				{
					if(objects[i] -> lastMove() == 'R')
						objects[final] -> move('L');
					else if(objects[i] -> lastMove() == 'L')
						objects[final] -> move('R');
					else if(objects[i] -> lastMove() == 'U')
						objects[final] -> move('D');
					else if(objects[i] -> lastMove() == 'U')
						objects[final] -> move('U');
				}

				else
				{
					cout << "This array of pointers contains solution but it is not sequence of moves for a solution..." << endl;
					return false;
				}
			}
		}

		if((*objects[final]) == (*objects[first]))
		{
			//if the array contains result
			//if the board reverse move from result, board must be first board
			//If the result is solved by inverse movements is the final
			cout << "This array of pointers contains sequence of moves for a solution..." << endl;
			return true;
		}
	}
}

int AbstractBoard::objectNumber = 0;

int main()
{
/*-------------------------------------1--------------------------------------------*/
	cout << "----------------1-----------------" << endl;
	BoardVector* rand_1 = new BoardVector(3,3);
	BoardVector* rand_2 = new BoardVector(4,5);
	BoardArray1D* rand_3 = new BoardArray1D(6,6);
	BoardArray2D* rand_4 = new BoardArray2D();
	BoardVector* rand_5 = new BoardVector(9,9);
	BoardArray1D* rand_6 = new BoardArray1D("testfile1.txt");

	rand_5 -> reset();

	AbstractBoard* array[] = {rand_1, rand_2, rand_3, rand_4, rand_5, rand_6};

	cout << "For the my random array1: " << endl;
	gtu_global_function(array);
	cout << "----------------1-----------------" << endl << endl;
/*------------------------------------1---------------------------------------------*/



/*------------------------------------2---------------------------------------------*/
	cout << "----------------2-----------------" << endl;
	BoardArray1D* testfile_1 = new BoardArray1D();

	testfile_1 -> readFromFile("testfile1.txt");

	cout << "The testfile1 object of board: " << endl;
	testfile_1 -> print();

	BoardArray1D* test_1 = testfile_1;
	testfile_1 -> move('D');
	BoardArray1D* test_2 = testfile_1;
	testfile_1 -> move('D');
	BoardArray1D* test_3 = testfile_1;
	testfile_1 -> move('R');
	BoardArray1D* test_4 = testfile_1;
	testfile_1 -> move('R');
	BoardArray1D* test_5 = testfile_1;

	AbstractBoard* array2[] = {test_1, test_2, test_3, test_4, test_5};


	cout << "---------------------------" << endl;
	cout << "I pushed to array that moved D, D, R and R" << endl;
	cout << "---------------------------" << endl << endl;
	cout << "For the testfile1.txt: " << endl;
	gtu_global_function(array2); 
	cout << "----------------2-----------------" << endl << endl;
/*------------------------------------2---------------------------------------------*/



/*------------------------------------3---------------------------------------------*/
	cout << "----------------3-----------------" << endl;
	BoardArray2D* object_2D = new BoardArray2D(4,4);

	cout << "The object_2D of board: " << endl;
	object_2D -> print();
	object_2D -> writeToFile("proofFile2.txt");

	BoardArray2D* element_1 = object_2D;
	object_2D -> moveRandom();
	BoardArray2D* element_2 = object_2D;
	object_2D -> moveRandom();
	BoardArray2D* element_3 = object_2D;
	object_2D -> moveRandom();
	BoardArray2D* element_4 = object_2D;
	object_2D -> moveRandom();
	BoardArray2D* element_5 = object_2D;
	object_2D -> moveRandom();
	BoardArray2D* element_6 = object_2D;
		object_2D -> moveRandom();
	BoardArray2D* element_7 = object_2D;

	AbstractBoard* array3[] = {element_1, element_2, element_3, element_4, element_5, element_6, element_7};

	cout << "\nFor the object_2D: " << endl;
	gtu_global_function(array3);
	cout << "----------------3-----------------" << endl << endl;
/*------------------------------------3---------------------------------------------*/


/*------------------------------------4---------------------------------------------*/
	cout << "----------------4-----------------" << endl;
	BoardVector* _rand_1 = new BoardVector(3,3);
	BoardVector* _rand_2 = new BoardVector(4,5);
	BoardArray1D* _rand_3 = new BoardArray1D(6,6);
	BoardArray2D* _rand_4 = new BoardArray2D();
	BoardVector* _rand_5 = new BoardVector(9,9);
	BoardArray1D* _rand_6 = new BoardArray1D("testfile1.txt");
	BoardArray2D* _rand_7 = new BoardArray2D();
	BoardArray1D* _rand_8 = new BoardArray1D(7);

	AbstractBoard* array4[] = {_rand_1, _rand_2, _rand_3, _rand_4, _rand_5, _rand_6, _rand_7, _rand_8};

	cout << "For the my random array4: " << endl;
	gtu_global_function(array4);
	cout << "----------------4-----------------" << endl << endl;
/*------------------------------------4---------------------------------------------*/


/*------------------------------------5---------------------------------------------*/
	cout << "----------------5-----------------" << endl;
	BoardArray1D* random1 = new BoardArray1D();
	BoardArray2D* random2 = new BoardArray2D(3);
	BoardVector* random3 = new BoardVector(3,3);
	BoardVector* random4 = new BoardVector(7,5);
	BoardArray1D* random5 = new BoardArray1D(5,5);

	BoardArray1D* solution = new BoardArray1D(7,7);
	solution -> reset();
	*random3 = *solution; // working my assignment operator for BoardVector = BoardArray1D 

	AbstractBoard* array5[] = {random1, random2, random3, random4, random5};

	cout << "For the my random array5: " << endl;
	gtu_global_function(array5);
	cout << "----------------5-----------------" << endl << endl;
/*------------------------------------5---------------------------------------------*/

	return 0;
}