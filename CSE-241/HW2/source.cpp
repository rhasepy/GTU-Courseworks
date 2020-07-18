/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork1
*/
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <string>
#include <cstring>
#include "source.h"

using namespace std;

void run(int argc, char *argv[])
{
	int game_map[9][9];
	int cell_i, cell_j;
	int size_i, size_j;
	int flag = 1, inversion;
	char choice;
 
	if(argc == 1) // if the argument is not given it starts normally
	{
		do
		{
			cout << "Do you want to play with file(y/n): ";
			cin >> choice;
		}while(choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N');
	}

	else if(argc == 2) //if the argument is given it starts by reading the file
	{
		int i = 1, j = 0;
		string fileName = "";

		while(argv[i][j] != '\0')
		{
			fileName = fileName + argv[i][j];
			++j;
		}

		Loadin_File(game_map, &size_i, &size_j, fileName);
		findCellPosition(game_map, size_i, size_j, &cell_i, &cell_j);
		print_map(game_map, size_i, size_j);
		playgame(game_map, size_i, size_j);
	}


	if(choice == 'n' || choice == 'N')
	{
		cout << "Enter the size of height: ";
		size_i = readUser_size();
		cout << "Enter the size of width: ";
		size_j = readUser_size();
	
		do
		{
			inversion = 0; 

			generate_gamemap(game_map, size_i, size_j);
			findCellPosition(game_map, size_i, size_j, &cell_i, &cell_j); // find the empty cell coordinate //

			inversion = isNot_Solvable((int *)game_map, size_i, size_j);
			/*
			I send the two-dimensional array as one-dimensional to this function.
			because controlling is easy for me
			*/

			/*
				If the board is even number, 
				inversion and empty cell location for bottom are must reverse about even or odd

				for example:
					board -> 4
					inversion -> 9 
					empty cell location for bottom -> 8
					--------------------------------------
					Solvable map
			*/

			if(size_i == size_j)
			{
				if(size_i % 2 == 0)
				{
					if((size_i - cell_i) % 2 == 0 && inversion % 2 == 1)
						flag = 0;
					else if((size_i - cell_i) % 2 == 1 && inversion % 2 == 0)
						flag = 0;
				}
				/*
					If the board is odd number,
					If the inversion is odd, board is not solvable
					else the inversion is even, board is solvable
				*/
				else if(size_i % 2 == 1)
				{
					if(inversion % 2 == 0)
						flag = 0;
				}
			}

			else if(size_i != size_j)
			{
				flag = 0;
			}

		}while(flag);

		cout << endl << "Your inital random board is" << endl;
		print_map(game_map, size_i, size_j);

		playgame(game_map, size_i, size_j);
	}

	else if(choice == 'Y' || choice == 'y')
	{
		Loadin_File(game_map, &size_i, &size_j);
		findCellPosition(game_map, size_i, size_j, &cell_i, &cell_j);
		print_map(game_map, size_i, size_j);
		playgame(game_map, size_i, size_j);
	}
}

void playgame(int game_map[9][9], int size_i, int size_j)
{
	int cell_i, cell_j;
	int totalMoves = 0;
	int count_intellegent = 0;
	char choice, previous_move;

	findCellPosition(game_map, size_i, size_j, &cell_i, &cell_j); // find the empty cell coordinate //

	do
	{
		choice = readUser_move();

		switch(choice)
		{
			case 'L':
			case 'l':
				if(cell_j != 0) // condition to move left //
				{
					if(game_map[cell_i][cell_j-1] != -1)
					{
						moveLeft(game_map, size_i, size_j, &cell_i, &cell_j);
						previous_move = 'L';
						count_intellegent = 0;
						++totalMoves;
					}
				}
				else
					cout << endl << "Invalid move..." << endl << endl;
				break;

			case 'R':
			case 'r':
				if(cell_j < (size_j-1)) // condition to move right //
				{
					if(game_map[cell_i][cell_j+1] != -1)
					{
						moveRight(game_map, size_i, size_j, &cell_i, &cell_j);
						previous_move = 'R';
						count_intellegent = 0;
						++totalMoves;
					}
				}
				else
					cout << endl << "Invalid move..." << endl << endl; 
				break;

			case 'U':
			case 'u':
				if(cell_i != 0)  // condition to move up //
				{
					if(game_map[cell_i-1][cell_j] != -1)
					{
						moveUp(game_map, size_i, size_j, &cell_i, &cell_j);
						previous_move = 'U';
						count_intellegent = 0;
						++totalMoves;
					}
				}
				else
					cout << endl << "Invalid move..." << endl << endl;
				break;

			case 'D':
			case 'd':
				if(cell_i < (size_i-1)) // condition to move down //
				{
					if(game_map[cell_i+1][cell_j] != -1)
					{
						moveDown(game_map, size_i, size_j, &cell_i, &cell_j);
						previous_move = 'D';
						count_intellegent = 0;
						++totalMoves;
					}
				}
				else
					cout << endl << "Invalid move..." << endl << endl;
				break;

			case 'I':
			case 'i':
			/*
				If this is the first smart move, 
				this counter becomes 1 and the next smart move enters the other function.
				Because if the user constantly chooses the smart move, 
				the program can repeat itself with the previous move.
			*/
				if(count_intellegent == 0) 
				{
					previous_move = intelligentMove(game_map, size_i, size_j, &cell_i, &cell_j);
					cout << "Intelligent move chooses " << previous_move << endl;
					++count_intellegent;
					++totalMoves;
				}

				else
				{
					previous_move = intelligentMove(game_map, size_i, size_j, &cell_i, &cell_j, &previous_move);
					cout << "Intelligent move chooses " << previous_move << endl;
					++totalMoves;
				}
				break;

			case 'S':
			case 's':
				cout << endl <<"The final board has shuffled..." << endl;
				shuffle(game_map, size_i, size_j, &cell_i, &cell_j);
				totalMoves = 0;
				break;

			case 'Q':
			case 'q':
				cout << "The program turning off..." << endl;
				return;
				break;

			case 'Y':
			case 'y':
				cout << endl;
				Loadin_File(game_map, &size_i, &size_j);
				findCellPosition(game_map, size_i, size_j, &cell_i, &cell_j);			
				cout << endl;
				break;

			case 'E':
			case 'e':
				cout << endl;
				Loadto_File(game_map, size_i, size_j);
				cout << endl;
				break;

			case 'V':
			case 'v':
				solvePuzzle(game_map, size_i, size_j, &cell_i, &cell_j, &totalMoves);
				// Solves 3x3 array, %70 possibility
				// Solves 4x4 array, %30 possibility
				break;

			case 'T':
			case 't':
				if(!isFinish((int *)game_map, size_i, size_j))
				{
					cout << "Game not finish yet..." << endl;
					cout << "Total Moves up to present: " << totalMoves << endl;
				}
				break;

			default:
				cout << "You have chosen invalid choice..." << endl
					<< "Please choose one of them (L, R, U, D, I , S, Q, Y, E, V, T)" << endl;
					break;
		}
 
		if(isFinish((int *)game_map, size_i, size_j)) // If the user solves puzzle, function return 1 //
		{
			cout << "**********Final Board**********" << endl;
			print_map(game_map, size_i, size_j);
			cout << "*******************************" << endl;
		/*
			I send the two-dimensional array as one-dimensional to this function.
			because controlling is easy for me
		*/
			cout << "Problem Solved!" << endl;
			cout << "Total number of moves " << totalMoves << endl;
			return;
		}
		else
			print_map(game_map, size_i, size_j);

	}while(true);
}

void solvePuzzle(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j, int *totalMoves)
{
	int array_index = 2*((2*(size_i-1)) + (2*(size_j-1)));
	char LastMoves[array_index];
	char previous_move;
	int count_intellegent = 0;
	decltype(array_index) index = 0;

	for(int i = 0 ; i < array_index ; ++i)
		LastMoves[i] = 'X';

	while(!isFinish((int *)game_map, size_i, size_j))
	{
		if(count_intellegent == 0) 
		{
			previous_move = intelligentMove(game_map, size_i, size_j, cell_i, cell_j);
			cout << "Intelligent move chooses " << previous_move << endl;
			++(*totalMoves);
			print_map(game_map, size_i, size_j);
		}

		else
		{
			previous_move = intelligentMove(game_map, size_i, size_j, cell_i, cell_j, &previous_move);
			cout << "Intelligent move chooses " << previous_move << endl;
			++(*totalMoves);
			print_map(game_map, size_i, size_j);
		}

		LastMoves[ (count_intellegent % (array_index)) ] = previous_move;
		++count_intellegent;

		if(isLastMovesSame(LastMoves, array_index)) // if my array get stuck, move randomly algorithm
		{
			if(count_intellegent < 500)
			{
				for(int i = 0 ; i < 2 ; ++i)
				{
					cout << "Intelligent move chooses ";
					randomMove(game_map, cell_i, cell_j, size_i, size_j);
					print_map(game_map, size_i, size_j);
					++count_intellegent;
				}
			}

			else
			{
				cout << "Intelligent move chooses ";
				randomMove(game_map, cell_i, cell_j, size_i, size_j);
				print_map(game_map, size_i, size_j);
				++count_intellegent;
			}
		}

		else if(count_intellegent > 3000 && count_intellegent % 30 == 0)
		{
			cout << "Intelligent move chooses ";
			randomMove(game_map, cell_i, cell_j, size_i, size_j);
			print_map(game_map, size_i, size_j);
			++count_intellegent;
		}
	}
}

bool isLastMovesSame(char LastMoves[], int index)
{
	// I'm testing his last few moves to see if he's stuck.
	// few moves = 2*((2*(size_i-1)) + (2*(size_j-1)));
	// i declared in solvesPuzzle function
	int goalCount = 0;
	/*
		for example my array size 3x3 
		if array stuck, it starts to wander around
	*/
	for(int i = 0 ; i < (index/2) ; ++i)
		if(LastMoves[i] == LastMoves[i+(index/2)])
			++goalCount;

	if(goalCount == (index/2))
		return true;
	else
		return false;
}

void randomMove(int game_map[9][9], int *cell_i, int *cell_j, int size_i, int size_j)
{
	int random_move;
	// function produced random move, if the cell can //
	while(true)
	{
		random_move = rand() % 4;

		if(random_move == 0 && (*cell_j) != 0)
		{
			if(game_map[(*cell_i)][(*cell_j)-1] != -1)
			{
				cout << "L" << endl;
				moveLeft(game_map, size_i, size_j, cell_i, cell_j);
				return;
			}
		}

		else if(random_move == 1 && (*cell_j) < size_j-1)
		{
			if(game_map[(*cell_i)][(*cell_j)+1] != -1)
			{
				cout << "R" << endl;
				moveRight(game_map, size_i, size_j, cell_i, cell_j);
				return;
			}
		}

		else if(random_move == 2 && (*cell_i) != 0)
		{
			if(game_map[(*cell_i)-1][(*cell_j)] != -1)
			{
				cout << "U" << endl;
				moveUp(game_map, size_i, size_j, cell_i, cell_j);
				return;
			}
		}

		else if(random_move == 3 && (*cell_i) < (size_i-1))
		{
			if(game_map[(*cell_i)-1][(*cell_j)] != -1)
			{
				cout << "D" << endl;
				moveDown(game_map, size_i, size_j, cell_i, cell_j);
				return;
			}
		}
	}
}

void Loadto_File(int game_map[9][9], int size_i, int size_j)
{
	string fileName;

	cout << "Please enter the file name to load current board(with EXTENSION): ";
	cin >> fileName;

	ofstream myFile(fileName);

	if(myFile.is_open())
	{
		for(auto i = 0 ; i < size_i ; ++i)
		{
			for(auto j = 0 ; j < size_j ; ++j)
			{
				if(game_map[i][j] == 0)
					myFile << "bb";

				else if(game_map[i][j] == -1)
					myFile << "00";

				else
				{
					if(game_map[i][j] < 10 && game_map[i][j] != 0)
						myFile << "0" << game_map[i][j];

					else
						myFile << game_map[i][j];
				}

				if(j < (size_j-1))
					myFile << " ";
			}

			myFile << endl;
		}

		cout << "The current board load the " << fileName << endl;
	}

	else
		cout << "The current board did not load..." << endl;

	myFile.close();
}

void Loadin_File(int game_map[9][9], int *size_i, int *size_j)
{
	// my overloading function, that the argument is given
	int i = 0, j = 0, index = 0;
	int space = 0, count = 0;
	char map_char[26];
	string line = "";
	string myFile;

	fill_map_char(map_char);

	cout << "Enter the file name will loading(with EXTENSION): ";
	cin >> myFile;

	ifstream readFile(myFile);

	if(readFile.is_open())
	{
		fill_gamemap(game_map);

		while(getline(readFile, line))
		{
			j = 0;

			strcpy(map_char, line.c_str());

			char_to_int(game_map, map_char, i, j);

			++i;
		}
	}

	else
	{
		cout << "File don't found" << endl;
		cout << "You will continue old map..." << endl << endl;
	}

	while(map_char[count] != '\0')
	{
		if(map_char[count] == ' ')
			++space;

		++count;
	}

	(*size_i) = i;
	(*size_j) = space + 1;

	readFile.close();
}

void Loadin_File(int game_map[9][9], int *size_i, int *size_j, string fileName)
{
	// my overloading function, that the argument is not given
	int i = 0, j = 0;
	int space = 0, count = 0;
	char map_char[26];
	string line = "";

	fill_map_char(map_char);

	ifstream readFile(fileName);

	if(readFile.is_open())
	{
		fill_gamemap(game_map);

		while(getline(readFile, line))
		{
			j = 0;

			strcpy(map_char, line.c_str());

			char_to_int(game_map, map_char, i, j);

			++i;
		}
	}

	else
	{
		cout << "File don't found" << endl;
		exit(1);
	}

	while(map_char[count] != '\0')
	{
		if(map_char[count] == ' ')
			++space;

		++count;
	}

	(*size_i) = i;
	(*size_j) = space + 1;

	readFile.close();
}

void char_to_int(int map[9][9], char map_char[26], int i, int j)
{
	//reads the file as a string and converts it to a integer

	for(int index = 0 ; index < 26 ; ++index)
	{
		if(map_char[index] == ' ' && index < 25)
		{
			if(map_char[index-1] != 'b')
			{
				map[i][j] = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

				if(map[i][j] == 0)
					map[i][j] = -1;

				++j;
			}

			else
			{
				map[i][j] = 0;
				++j;
			}
		}

		else if(map_char[index] == '\0')
		{
			if(map_char[index-1] != 'b')
			{
				map[i][j] = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

				if(map[i][j] == 0)
					map[i][j] = -1;

				++j;
			}

			else
			{
				map[i][j] = 0;
				++j;
			}
			
			return;
		}

		else if(index == 25)
		{
			if(map_char[index-1] != 'b')
			{
				map[i][j] = static_cast<int> (10*(map_char[index-1] - 48)) + (map_char[index] - 48);

				if(map[i][j] == 0)
					map[i][j] = -1;
			}

			else
				map[i][j] = 0;
		}
	}
}

inline void fill_map_char(char map_char[26])
{
	for(int i = 0 ; i < 26 ; ++i)
		map_char[i] = '\0';
}

char intelligentMove(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int test_map[9][9];
	int testmap_Xi = (size_i-1), testmap_Xj = (size_j-1); 
	// Where is the "0" on test map // Example:
	// 123
	// 456
	// 780  0->(2,2)
	int cost[4] = {99,99,99,99}; // I have calculated cost and filled 4 directions //
	int min_cost, equal_cost;
				// cost[0] -> move left cost //
				// cost[1] -> move right cost //
				// cost[2] -> mode up cost //
				// cost[3] -> move down cost //

	swap_array(test_map, game_map, size_i, size_j);
	makeFinalSolution(test_map, size_i, size_j, &testmap_Xi, &testmap_Xj);

	if( (*cell_j) != 0 && game_map[(*cell_i)][(*cell_j)-1] != -1) // if the empty cell can move left //
	{
		moveLeft(game_map, size_i, size_j, cell_i, cell_j);
		cost[0] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveRight(game_map, size_i, size_j, cell_i, cell_j); // After trying the left move back to the old position by making the reverse move. //
	}

	if( (*cell_j) < (size_j-1) && game_map[(*cell_i)][(*cell_j+1)] != -1)// if the empty cell can move right //
	{
		moveRight(game_map, size_i, size_j, cell_i, cell_j);
		cost[1] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveLeft(game_map, size_i, size_j, cell_i, cell_j); // After trying the right move back to the old position by making the reverse move. //
	}

	if( (*cell_i) != 0 && game_map[(*cell_i)-1][(*cell_j)] != -1)
	{
		moveUp(game_map, size_i, size_j, cell_i, cell_j); // if the empty cell can move up //
		cost[2] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveDown(game_map, size_i, size_j, cell_i, cell_j); // After trying the up move back to the old position by making the reverse move. //
	}

	if( (*cell_i) < (size_i) && game_map[(*cell_i+1)][(*cell_j)] != -1) // if the empty cell can mode down //
	{
		moveDown(game_map, size_i, size_j, cell_i, cell_j); 
		cost[3] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveUp(game_map, size_i, size_j, cell_i, cell_j); // After trying the down move back to the old position by making the reverse move. //
	}

	min_cost = sort_cost(cost); // find the minimum cost and return //

	// this is the minimum cost of moving the array to which it equals. //

	if(min_cost == cost[0])
	{
		moveLeft(game_map, size_i, size_j, cell_i, cell_j);
		return 'L';
	}

	else if(min_cost == cost[1])
	{
		moveRight(game_map, size_i, size_j, cell_i, cell_j);
		return 'R';
	}

	else if(min_cost == cost[2])
	{
		moveUp(game_map, size_i, size_j, cell_i, cell_j);
		return 'U';
	}

	else if(min_cost == cost[3])
	{
		moveDown(game_map, size_i, size_j, cell_i, cell_j);
		return 'D';
	}
}

char intelligentMove(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j, char *previous_move)
{
	int test_map[9][9];
	int testmap_Xi = (size_i-1), testmap_Xj = (size_j-1);
	int randomCount;
	// Where is the "0" on test map // Example:
	// 123
	// 456
	// 780  0->(2,2)
	int cost[4] = {99,99,99,99}; // I have calculated cost and filled 4 directions //
	int min_cost, equal_cost;
				// cost[0] -> move left cost //
				// cost[1] -> move right cost //
				// cost[2] -> mode up cost //
				// cost[3] -> move down cost //

	swap_array(test_map, game_map, size_i, size_j);
	makeFinalSolution(test_map, size_i, size_j, &testmap_Xi, &testmap_Xj);

	if( (*cell_j) != 0 && (*previous_move) != 'R' && game_map[(*cell_i)][(*cell_j)-1] != -1) // if the empty cell can move left 
	{											// The second condition is for not repeating the previous move.
		moveLeft(game_map, size_i, size_j, cell_i, cell_j);
		cost[0] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveRight(game_map, size_i, size_j, cell_i, cell_j);	// After trying the left move back to the old position by making the reverse move. //
	}

	if( (*cell_j) < (size_j-1) && (*previous_move != 'L') && game_map[(*cell_i)][(*cell_j+1)] != -1) // if the empty cell can move right 
	{													// The second condition is for not repeating the previous move.
		moveRight(game_map, size_i, size_j, cell_i, cell_j);
		cost[1] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveLeft(game_map, size_i, size_j, cell_i, cell_j);	// After trying the right move back to the old position by making the reverse move. //
	}

	if( (*cell_i) != 0 && (*previous_move != 'D') && game_map[(*cell_i)-1][(*cell_j)] != -1) // if the empty cell can move up 
	{												// The second condition is for not repeating the previous move.
		moveUp(game_map, size_i, size_j, cell_i, cell_j);
		cost[2] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveDown(game_map, size_i, size_j, cell_i, cell_j);	// After trying the up move back to the old position by making the reverse move. //
	}

	if( (*cell_i) < (size_i-1) && (*previous_move != 'U') && game_map[(*cell_i+1)][(*cell_j)] != -1) // if the empty cell can move down 
	{													// The second condition is for not repeating the previous move.		
		moveDown(game_map, size_i, size_j, cell_i, cell_j);
		cost[3] = heuristic_cost(game_map, test_map, size_i, size_j);
		moveUp(game_map, size_i, size_j, cell_i, cell_j);	// After trying the down move back to the old position by making the reverse move. //
	}

	min_cost = sort_cost(cost); // find the minimum cost and return //

	if(min_cost == cost[0])
	{
		moveLeft(game_map, size_i, size_j, cell_i, cell_j);
		return 'L';
	}

	else if(min_cost == cost[1])
	{
		moveRight(game_map, size_i, size_j, cell_i, cell_j);
		return 'R';
	}

	else if(min_cost == cost[2])
	{
		moveUp(game_map, size_i, size_j, cell_i, cell_j);
		return 'U';
	}

	else if(min_cost == cost[3])
	{
		moveDown(game_map, size_i, size_j, cell_i, cell_j);
		return 'D';
	}
}

int sort_cost(int cost[4]) // find the minimum cost and return //
{
	int minimumCost = cost[0];

	for(int i = 1 ; i < 4 ; ++i)
	{
		if(minimumCost >= cost[i])
			minimumCost = cost[i];
	}

	return minimumCost;
}

int heuristic_cost(int game_map[9][9], int test_map[9][9], int size_i, int size_j)
{
	/*
		The count of numbers not on the map is cost for program.
	*/
	int cost = 0;

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(game_map[i][j] != test_map[i][j])
				++cost;

	return cost;
}

void shuffle(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int countFor_move = 0, random_move;
	int old_i, old_j;
	int foo = size_i*size_j;

	makeFinalSolution(game_map, size_i, size_j, cell_i, cell_j); // The board make final position //

	old_i = (*cell_i);
	old_j = (*cell_j);

	while(countFor_move != (size_i*size_j) && foo != 0)
	{
		random_move = rand() % 4;

		if(random_move == 0)
		{
			moveLeft(game_map, size_i, size_j, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j) // has the empty cell moved or not //
			{											// if the moved, coordinate was changed //
				++countFor_move; // if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}

		else if(random_move == 1)				// has the empty cell moved or not //
		{										// if the moved, coordinate was changed //
			moveRight(game_map, size_i, size_j, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j)
			{
				++countFor_move;			// if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}

		else if(random_move == 2)			// has the empty cell moved or not //
		{									// if the moved, coordinate was changed //
			moveUp(game_map, size_i, size_j, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j)
			{
				++countFor_move;			// if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}

		else if(random_move == 3)			// has the empty cell moved or not //
		{									// if the moved, coordinate was changed //
			moveDown(game_map, size_i, size_j, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j)
			{
				++countFor_move;			// if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}
	}
}

void makeFinalSolution(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int count = 1; // writing starts from 1, not from 0 because I designed 0 as a space.

	// the function generate map in-line on board(game_map) //

	int maxNumber = findMax(game_map, size_i, size_j);

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{	
			if(count <= maxNumber && game_map[i][j] != -1) // if the count is (size*size) //
			{							// this is zero according to my program //
				game_map[i][j] = count;
				++count;
			}

			else if(count == maxNumber+1) // if the count is (size*size) // this is zero according to my program //
			{
				game_map[i][j] = 0;
				(*cell_i) = i;
				(*cell_j) = j;
				return;
			}
		}
	}
}

int findMax(const int game_map[9][9], int size_i, int size_j)
{
	int max = 1;

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(game_map[i][j] > max)
				max = game_map[i][j];

	return max;
}

void moveLeft(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_j) != 0 && game_map[(*cell_i)][(*cell_j)-1] != -1) // for the cell can move left //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i][j-1];
		game_map[i][j-1] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_j = (*cell_j) - 1;
	}
}

void moveRight(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_j) < (size_j-1) && game_map[(*cell_i)][(*cell_j)+1] != -1) // for the cell can move right //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i][j+1];
		game_map[i][j+1] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_j = (*cell_j) + 1;
	}
}

void moveUp(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_i) != 0 && game_map[(*cell_i-1)][(*cell_j)] != -1) // for the cell can move up //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i-1][j];
		game_map[i-1][j] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_i = (*cell_i) - 1;
	}
}

void moveDown(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_i) < (size_i-1) && game_map[(*cell_i)+1][(*cell_j)] != -1) // for the cell can move up //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i+1][j];
		game_map[i+1][j] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_i = (*cell_i) + 1;
	}
}

void findCellPosition(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j)
{

	// find the empty cell position coordinate //

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(game_map[i][j] == 0)
			{
				(*cell_i) = i;
				(*cell_j) = j;
			}
}

char readUser_move()
{
	char choice;

	cout << "Your move: ";
	cin >> choice;

	return choice;
}

int isFinish(int game_map[81], int size_i, int size_j)
{
	/*
		I send the two-dimensional array as one-dimensional to this function.
		because controlling is easy for me
	*/

	int count = 0, index = 0, index_of_bb = 0;
	int temp_map[9][9];

	for(int i = 0 ; i < 9 ; ++i)
		for(int j = 0 ; j < 9 ; ++j, ++index)
			temp_map[i][j] = game_map[index];

	int max = findMax(temp_map, size_i, size_j) - 1;
	int maxNumber = findMax(temp_map, size_i, size_j);
	int goalCount = (max*(max+1)) / 2;

	/*
		on the map there is a minus of the number that is less than a number.
		EXCLUDİNG ZERO

		for example:
		we assume that board is : 123
								  456	
								  780
		for the 8:
				(7) 6 5 4 3 2 1
		for the 7:
				(6) 5 4 3 2 1
				.
				.
				.
		7 -> max number(8) -1 

		SUM OF THEESE:  7 + 6 + 5 ... + 1
		so -> (n*(n+1))/2         ----> goal count;

		if the map is in order, this math/algorithm is consistent
	*/

	for(int index_of_bb = 0 ; index_of_bb < 81 ; ++index_of_bb)
		if(game_map[index_of_bb] == 0)
			index = index_of_bb;
	
	if(size_i != size_j && game_map[index-1] == maxNumber)
	{
		for (int i = 0 ; i < 81 ; i++)
	        for (int j = i+1 ; j < 81 ; j++)	
	        	if(game_map[i] && game_map[j] && game_map[i] != -1 && game_map[j] != -1 && game_map[i] < game_map[j])
	        	{	
	        		// when I convert a two-dimensional array to a one-dimensional array,
	        		// intervene '-1'
	        		// this if condition prevents this situation
	        		++count;
	        	}
	}

	else if(size_i == size_j && game_map[index-1] == maxNumber)
	{
		for (int i = 0 ; i < 81 ; i++)
			for (int j = i+1 ; j < 81 ; j++)
		       	if(game_map[i] && game_map[j] && game_map[i] != -1 && game_map[j] != -1 && game_map[i] < game_map[j])
		       	{	
		       		// when I convert a two-dimensional array to a one-dimensional array,
		       		// intervene '-1'
		       		// this if condition prevents this situation
		       		++count;
				}
	}
	
	if(count == goalCount)
		return 1;
	
	else
		return 0;
}

int readUser_size()
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

inline void fill_gamemap(int game_map[9][9])
{
	for(int i = 0 ; i < 9 ; ++i)
		for(int j = 0 ; j < 9 ; ++j)
			game_map[i][j] = -1;
}

void swap_array(int test_map[9][9], int game_map[9][9], int size_i, int size_j)
{
	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			test_map[i][j] = game_map[i][j];
}

void generate_gamemap(int game_map[9][9], int size_i, int size_j)
{
	int number = rand() % (size_i*size_j);

	fill_gamemap(game_map);

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{
			while(testSame(game_map, number, size_i, size_j)) // Prevent to be same numbers on board //
				number = rand() % (size_i*size_j);

			game_map[i][j] = number;
		}
	}
}

int testSame(int game_map[9][9], int finder, int size_i, int size_j) // Prevent to be same numbers on board //
{
	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)	
			if(finder == game_map[i][j])
				return 1;

	return 0;
}

int isNot_Solvable(int game_map[81], int size_i, int size_j)
{
	int count = 0;

	/*
		I send the two-dimensional array as one-dimensional to this function.
		because controlling is easy for me
	*/

	for(int i = 0 ; i < (size_i*size_j)-1 ; ++i)
		for(int j = i+1 ; j < (size_i*size_j) ; ++j)
			if(game_map[i] != -1 && game_map[j] != -1 && game_map[i] && game_map[j] && game_map[i] > game_map[j])
				++count;

	// my integer variable "count" is inversion count //
	return count;
}

void print_map(const int game_map[9][9], int size_i, int size_j)
{
	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{
			if(game_map[i][j] == -1)
			{
				cout << "00 ";
			}

			else if(game_map[i][j] == 0)
			{
				cout << "bb" << " ";
			}

			else
			{
				if(game_map[i][j] < 10 && game_map[i][j] != 0)
				{
					cout << "0" << game_map[i][j] << " ";
				}

				else
				{
					cout << game_map[i][j] << " ";
				}
			}
		}

		cout << endl;
	}
}