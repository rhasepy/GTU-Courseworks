/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork4
*/
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <string>
#include <cstring>
#include <vector>
#include "NPuzzle.h"

using namespace std;

int NPuzzle::Board::objectCount = 0;

int main(int argc, char *argv[])
{
	srand(time(NULL));

	if(argc == 2)
	{
		char choice;
		string fileName;
		NPuzzle game(argv[1]);

		do
		{
			cout << game << endl;

			cout << "Your move: ";
			cin >> choice;

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

				case 'S':
				case 's':
					game.shuffle(game.get_size_i() * game.get_size_j());
					break;

				case 'Q':
				case 'q':
					cout << "The program turning off..." << endl;
					return 0;
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
					cout << "Please enter the file name to load current board(with EXTENSION): ";
					cin >> fileName;
					game.writeToFile(fileName);
					break;

				case 'O':
				case 'o':
					cin >> game;
					break;

				default:
					cout << "You have chosen invalid choice..." << endl
						<< "Please choose one of them (V, T, E, O, L, R, U, D, I, S)" << endl;
						break;
			}
		}while(!game.isSolved());
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
			int val_i;
			int val_j;
			string fileName;

			NPuzzle game(val_i, val_j);

			do
			{
				cout << game << endl;

				cout << "Your move: ";
				cin >> choice;

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

					case 'S':
					case 's':
						game.shuffle(game.get_size_i() * game.get_size_j());
						break;

					case 'Q':
					case 'q':
						cout << "The program turning off..." << endl;
						return 0;
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
						cout << "Please enter the file name to load current board(with EXTENSION): ";
						cin >> fileName;
						game.writeToFile(fileName);
						break;

					case 'O':
					case 'o':
						cin >> game;
						break;

					default:
						cout << "You have chosen invalid choice..." << endl
							<< "Please choose one of them (V, T, E, O, L, R, U, D, I, S)" << endl;
							break;
				}
			}while(!game.isSolved());
		}

		else if(choice == 'Y' || choice == 'y')
		{
			string fileName;
			cout << "Please enter the file name to load current board(with EXTENSION): ";
			cin >> fileName;
			NPuzzle game(fileName);

			do
			{
				cout << game << endl;

				cout << "Your move: ";
				cin >> choice;

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

					case 'S':
					case 's':
						game.shuffle(game.get_size_i() * game.get_size_j());
						break;

					case 'Q':
					case 'q':
						cout << "The program turning off..." << endl;
						return 0;
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
						cout << "Please enter the file name to load current board(with EXTENSION): ";
						cin >> fileName;
						game.writeToFile(fileName);
						break;

					case 'O':
					case 'o':
						cin >> game;
						break;

					default:
						cout << "You have chosen invalid choice..." << endl
							<< "Please choose one of them (V, T, E, O, L, R, U, D, I, S)" << endl;
							break;
				}
			}while(!game.isSolved());
		}
	}

	else
	{
		cout << endl << "No such file or invalid file..." << endl;
		cout << "The Game is turning off..." << endl;
		return 0;
	}

	return 0;
}