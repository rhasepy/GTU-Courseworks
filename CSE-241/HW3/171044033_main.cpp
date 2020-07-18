/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork3
*/
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <string>
#include <cstring>
#include "NPuzzle.h"

using namespace std;

void playgame(NPuzzle game);
int input_size();
string input_filename();
char input_move();

int main(int argc, char *argv[])
{
	srand(time(NULL));

	if(argc == 2)
	{
		NPuzzle game(argv[1]);
		playgame(game);
	}

	else if(argc == 1) // if the argument is not given it starts normally
	{
		char choice;

		do
		{
			cout << "Do you want to play with file(y/n): ";
			cin >> choice;
		}while(choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N');

		if(choice == 'n' || choice == 'N')
		{
			cout << "Enter the size of height: ";
			int val_x = input_size();

			cout << "Enter the size of width: ";
			int val_y = input_size();

			NPuzzle game(val_x, val_y);

			playgame(game);
		}

		else if(choice == 'Y' || choice == 'y')
		{
			string fileName;
			fileName = input_filename();
			NPuzzle game(fileName);

			playgame(game);
		}
	}

	else
	{
		cout << endl << "No such file or invalid file..." << endl;
		cout << "The Game is turning off..." << endl;
	}

	return 0;
}

void playgame(NPuzzle game)
{
	char choice;
	string fileName;

	do
	{
		game.print();
		choice = input_move();

		switch(choice)
		{
			case 'L':
			case 'l':
				game.move('L');
				break;

			case 'R':
			case 'r':
				game.move('R');
				break;

			case 'U':
			case 'u':
				game.move('U');
				break;

			case 'D':
			case 'd':
				game.move('D');
				break;

			case 'I':
			case 'i':
				game.moveIntelligent();
				break;

			case 'S':
			case 's':
				game.shuffle(game.get_size_i() * game.get_size_j());
				break;

			case 'Q':
			case 'q':
				cout << "The program turning off..." << endl;
				return;
				break;

			case 'V':
			case 'v':
				game.solvePuzzle();
				break;

			case 'T':
			case 't':
				game.printReport();
				break;

			case 'E':
			case 'e':
				fileName = input_filename();
				game.writeToFile(fileName);
				break;

			case 'O':
			case 'o':
				fileName = input_filename();
				game.readFromFile(fileName);
				break;

			default:
				cout << "You have chosen invalid choice..." << endl
					<< "Please choose one of them (V, T, E, O, L, R, U, D, I, S)" << endl;
					break;
		}
	}while(!game.isSolved());
}

string input_filename()
{
	string fileName;

	cout << "Please enter the file name to load current board(with EXTENSION): ";
	cin >> fileName;

	return fileName;
}

char input_move()
{
	char choice;

	cout << "Your move: ";
	cin >> choice;

	return choice;
}

int input_size()
{
	char test_choice;
	int choice;

	do
	{
		cin >> test_choice;

		choice = static_cast<int> (test_choice - 48);
		//To prevent the possibility of entering characters.//

		if(choice > 10 || choice < 3)
			cout << "Please choose integer between 3 and 9" << endl;

	}while(choice > 10 || choice < 3);

	return choice;
}