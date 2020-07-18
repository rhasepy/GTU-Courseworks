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

int AbstractBoard::objectNumber = 0;

int main(int argc, char const *argv[])
{
	srand(time(NULL));

	if(argc == 2) // if the user give argument in command line //
	{
		int gameMode;

		cout << "1) BoardArray1D \n"
			 << "2) BoardArray2D \n"
			 << "3) BoardVector \n";
		cout << "Please choose the game mode: "; // The program have 3 derived class //
												// User choose one of them
		do
		{
			cout << "Plase choose the game mode: ";
			cin >> gameMode;
			if(gameMode > 3 || gameMode < 1)
				cout << "Please choose between 1 and 3" << endl;

		}while(gameMode > 3 || gameMode < 1); // check validity

		if(gameMode == 1)
		{
			BoardArray1D game(argv[1]);
			game.play();
		}

		else if(gameMode == 2)
		{
			BoardArray2D game(argv[1]);
			game.play();
		}

		else if(gameMode == 3)
		{
			BoardVector game(argv[1]);
			game.play();
		}
	}

	else if(argc == 1) // If the user not give any argument
	{
		int gameMode;
		int val_size;
		char test_choice;

		cout << "1) BoardArray1D \n"
			 << "2) BoardArray2D \n"
			 << "3) BoardVector \n"; // The program have 3 derived class //
			 						// User choose one of them
		do
		{
			cout << "Please choose the game mode: ";
			cin >> gameMode;
			if(gameMode > 3 || gameMode < 1)
				cout << "Please choose between 1 and 3" << endl;
			
		}while(gameMode > 3 || gameMode < 1); // check validity

		cout << "Enter the size: ";

		do
		{
			cin >> test_choice;

			val_size = static_cast<int> (test_choice - 48);
			//To prevent the possibility of entering characters.//

			if(val_size > 9 || val_size < 3)
				cout << "Please choose integer between 3 and 9" << endl;

		}while(val_size > 9 || val_size < 3);

		if(gameMode == 1)
		{
			BoardArray1D game(val_size, val_size);
			game.play();
		}

		else if(gameMode == 2)
		{
			BoardArray2D game(val_size, val_size);
			game.play();
		}

		else if(gameMode == 3)
		{
			BoardVector game(val_size, val_size);
			game.play();
		}
	}

	return 0;
}