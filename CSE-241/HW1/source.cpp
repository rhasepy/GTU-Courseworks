/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork1
*/
#include <iostream>
#include <ctime>
#include "source.h"

using namespace std;

void run()
{
	int choice, game_map[9][9];
	int cell_i, cell_j;
	int flag = 1, inversion;

	choice = readUser_size();

	do
	{
		inversion = 0; 

		generate_gamemap(game_map, choice);
		findCellPosition(game_map, choice, &cell_i, &cell_j); // find the empty cell coordinate //

		inversion = isNot_Solvable((int *)game_map, choice);
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
		if(choice % 2 == 0)
		{
			if((choice - cell_i) % 2 == 0 && inversion % 2 == 1)
				flag = 0;
			else if((choice - cell_i) % 2 == 1 && inversion % 2 == 0)
				flag = 0;
		}
		/*
			If the board is odd number,
			If the inversion is odd, board is not solvable
			else the inversion is even, board is solvable
		*/
		else if(choice % 2 == 1)
		{
			if(inversion % 2 == 0)
				flag = 0;
		}

	}while(flag);

	cout << endl << "Your inital random board is" << endl;
	print_map(game_map, choice);

	playgame(game_map, choice);
}

void playgame(int game_map[9][9], int size)
{
	int cell_i, cell_j;
	int totalMoves = 0;
	int count_intellegent = 0;
	char choice, previous_move;

	findCellPosition(game_map, size, &cell_i, &cell_j); // find the empty cell coordinate //

	do
	{
		choice = readUser_move();

		switch(choice)
		{
			case 'L':
			case 'l':
				if(cell_j != 0) // condition to move left //
				{
					moveLeft(game_map, size, &cell_i, &cell_j);
					previous_move = 'L';
					count_intellegent = 0;
					++totalMoves;
				}
				else
					cout << endl << "Invalid move..." << endl << endl;
				break;

			case 'R':
			case 'r':
				if(cell_j < (size-1)) // condition to move right //
				{
					moveRight(game_map, size, &cell_i, &cell_j);
					previous_move = 'R';
					count_intellegent = 0;
					++totalMoves;
				}
				else
					cout << endl << "Invalid move..." << endl << endl; 
				break;

			case 'U':
			case 'u':
				if(cell_i != 0)  // condition to move up //
				{
					moveUp(game_map, size, &cell_i, &cell_j);
					previous_move = 'U';
					count_intellegent = 0;
					++totalMoves;
				}
				else
					cout << endl << "Invalid move..." << endl << endl;
				break;

			case 'D':
			case 'd':
				if(cell_i < (size-1)) // condition to move down //
				{
					moveDown(game_map, size, &cell_i, &cell_j);
					previous_move = 'D';
					count_intellegent = 0;
					++totalMoves;
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
					previous_move = intelligentMove(game_map, size, &cell_i, &cell_j);
					cout << "Intelligent move chooses " << previous_move << endl;
					++count_intellegent;
					++totalMoves;
				}

				else
				{
					previous_move = intelligentMove(game_map, size, &cell_i, &cell_j, &previous_move);
					cout << "Intelligent move chooses " << previous_move << endl;
					++totalMoves;
				}
				break;

			case 'S':
			case 's':
				cout << endl <<"The final board has shuffled..." << endl;
				shuffle(game_map, size, &cell_i, &cell_j);
				totalMoves = 0;
				break;

			case 'Q':
			case 'q':
				cout << "The program turning off..." << endl;
				return;
				break;

			default:
				cout << "You have chosen invalid choice..." << endl
					<< "Please choose one of them (L, R, U, D, I , S, Q)" << endl;
					break;
		}

		print_map(game_map, size);
 
		if(isFinish((int *)game_map, size)) // If the user solves puzzle, function return 1 //
		{
		/*
			I send the two-dimensional array as one-dimensional to this function.
			because controlling is easy for me
		*/
			cout << "Problem Solved!" << endl;
			cout << "Total number of moves " << totalMoves << endl;
			return;
		}

	}while(true);
}

char intelligentMove(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int test_map[9][9];
	int testmap_Xi = (size-1), testmap_Xj = (size-1); 
	// Where is the "0" on test map // Example:
	// 123
	// 456
	// 780  0->(2,2)
	int cost[4] = {99,99,99,99}; // I have calculated cost and filled 4 directions //
	int min_cost;
				// cost[0] -> move left cost //
				// cost[1] -> move right cost //
				// cost[2] -> mode up cost //
				// cost[3] -> move down cost //

	fill_gamemap(test_map);
	makeFinalSolution(test_map, size, &testmap_Xi, &testmap_Xj);

	if( (*cell_j) != 0) // if the empty cell can move left //
	{
		moveLeft(game_map, size, cell_i, cell_j);
		cost[0] = heuristic_cost(game_map, test_map, size);
		moveRight(game_map, size, cell_i, cell_j); // After trying the left move back to the old position by making the reverse move. //
	}

	if( (*cell_j) < (size-1)) // if the empty cell can move right //
	{
		moveRight(game_map, size, cell_i, cell_j);
		cost[1] = heuristic_cost(game_map, test_map, size);
		moveLeft(game_map, size, cell_i, cell_j); // After trying the right move back to the old position by making the reverse move. //
	}

	if( (*cell_i) != 0)
	{
		moveUp(game_map, size, cell_i, cell_j); // if the empty cell can move up //
		cost[2] = heuristic_cost(game_map, test_map, size);
		moveDown(game_map, size, cell_i, cell_j); // After trying the up move back to the old position by making the reverse move. //
	}

	if( (*cell_i) < (size-1)) // if the empty cell can mode down //
	{
		moveDown(game_map, size, cell_i, cell_j); 
		cost[3] = heuristic_cost(game_map, test_map, size);
		moveUp(game_map, size, cell_i, cell_j); // After trying the down move back to the old position by making the reverse move. //
	}

	min_cost = sort_cost(cost); // find the minimum cost and return //


	// this is the minimum cost of moving the array to which it equals. //
	if(min_cost == cost[0])
	{
		moveLeft(game_map, size, cell_i, cell_j);
		return 'L';
	}

	else if(min_cost == cost[1])
	{
		moveRight(game_map, size, cell_i, cell_j);
		return 'R';
	}

	else if(min_cost == cost[2])
	{
		moveUp(game_map, size, cell_i, cell_j);
		return 'U';
	}

	else if(min_cost == cost[3])
	{
		moveDown(game_map, size, cell_i, cell_j);
		return 'D';
	}
}

char intelligentMove(int game_map[9][9], int size, int *cell_i, int *cell_j, char *previous_move)
{
	int test_map[9][9];
	int testmap_Xi = (size-1), testmap_Xj = (size-1); 
	// Where is the "0" on test map // Example:
	// 123
	// 456
	// 780  0->(2,2)
	int cost[4] = {99,99,99,99}; // I have calculated cost and filled 4 directions //
	int min_cost;
				// cost[0] -> move left cost //
				// cost[1] -> move right cost //
				// cost[2] -> mode up cost //
				// cost[3] -> move down cost //

	fill_gamemap(test_map);
	makeFinalSolution(test_map, size, &testmap_Xi, &testmap_Xj); 

	if( (*cell_j) != 0 && (*previous_move) != 'R') // if the empty cell can move left 
	{											// The second condition is for not repeating the previous move.
		moveLeft(game_map, size, cell_i, cell_j);
		cost[0] = heuristic_cost(game_map, test_map, size);
		moveRight(game_map, size, cell_i, cell_j);	// After trying the left move back to the old position by making the reverse move. //
	}

	if( (*cell_j) < (size-1) && (*previous_move != 'L')) // if the empty cell can move right 
	{													// The second condition is for not repeating the previous move.
		moveRight(game_map, size, cell_i, cell_j);
		cost[1] = heuristic_cost(game_map, test_map, size);
		moveLeft(game_map, size, cell_i, cell_j);	// After trying the right move back to the old position by making the reverse move. //
	}

	if( (*cell_i) != 0 && (*previous_move != 'D')) // if the empty cell can move up 
	{												// The second condition is for not repeating the previous move.
		moveUp(game_map, size, cell_i, cell_j);
		cost[2] = heuristic_cost(game_map, test_map, size);
		moveDown(game_map, size, cell_i, cell_j);	// After trying the up move back to the old position by making the reverse move. //
	}

	if( (*cell_i) < (size-1) && (*previous_move != 'U')) // if the empty cell can move down 
	{													// The second condition is for not repeating the previous move.		
		moveDown(game_map, size, cell_i, cell_j);
		cost[3] = heuristic_cost(game_map, test_map, size);
		moveUp(game_map, size, cell_i, cell_j);	// After trying the down move back to the old position by making the reverse move. //
	}

	min_cost = sort_cost(cost); // find the minimum cost and return //

	if(min_cost == cost[0])
	{
		moveLeft(game_map, size, cell_i, cell_j);
		return 'L';
	}

	else if(min_cost == cost[1])
	{
		moveRight(game_map, size, cell_i, cell_j);
		return 'R';
	}

	else if(min_cost == cost[2])
	{
		moveUp(game_map, size, cell_i, cell_j);
		return 'U';
	}

	else if(min_cost == cost[3])
	{
		moveDown(game_map, size, cell_i, cell_j);
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

int heuristic_cost(int game_map[9][9], int test_map[9][9], int size)
{
	/*
		The count of numbers not on the map is cost for program.
	*/
	int cost = 0;

	for(int i = 0 ; i < size ; ++i)
		for(int j = 0 ; j < size ; ++j)
			if(game_map[i][j] != test_map[i][j])
				++cost;

	return cost;
}

void shuffle(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int countFor_move = 0, random_move;
	int old_i, old_j;

	makeFinalSolution(game_map, size, cell_i, cell_j); // The board make final position //

	old_i = (*cell_i);
	old_j = (*cell_j);

	while(countFor_move != (size*size))
	{
		random_move = rand() % 4;

		if(random_move == 1)
		{
			moveLeft(game_map, size, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j) // has the empty cell moved or not //
			{											// if the moved, coordinate was changed //
				++countFor_move; // if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}

		else if(random_move == 2)				// has the empty cell moved or not //
		{										// if the moved, coordinate was changed //
			moveRight(game_map, size, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j)
			{
				++countFor_move;			// if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}

		else if(random_move == 3)			// has the empty cell moved or not //
		{									// if the moved, coordinate was changed //
			moveUp(game_map, size, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j)
			{
				++countFor_move;			// if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}

		else if(random_move == 4)			// has the empty cell moved or not //
		{									// if the moved, coordinate was changed //
			moveDown(game_map, size, cell_i, cell_j);

			if((*cell_i) != old_i || (*cell_j) != old_j)
			{
				++countFor_move;			// if the empty cell moved // increment the count for move //

				old_i = (*cell_i);
				old_j = (*cell_j);
			}
		}
	}

}

void makeFinalSolution(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int count = 1; // writing starts from 1, not from 0 because I designed 0 as a space.

	// the function generate map in-line on board(game_map) //

	for(int i = 0 ; i < size ; ++i)
	{
		for(int j = 0 ; j < size ; ++j)
		{
			if(count != (size*size)) // if the count is (size*size) //
			{							// this is zero according to my program //
				game_map[i][j] = count;
				++count;
			}
			else // if the count is (size*size) // this is zero according to my program //
			{
				game_map[i][j] = 0;
				(*cell_i) = i;
				(*cell_j) = j;
			}
		}
	}

}

void moveLeft(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_j) != 0) // for the cell can move left //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i][j-1];
		game_map[i][j-1] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_j = (*cell_j) - 1;
	}
}

void moveRight(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_j) < (size-1)) // for the cell can move right //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i][j+1];
		game_map[i][j+1] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_j = (*cell_j) + 1;
	}
}

void moveUp(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_i) != 0) // for the cell can move up //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i-1][j];
		game_map[i-1][j] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_i = (*cell_i) - 1;
	}
}

void moveDown(int game_map[9][9], int size, int *cell_i, int *cell_j)
{
	int temp, i, j;

	if((*cell_i) < (size-1)) // for the cell can move up //
	{
		i = *cell_i;
		j = *cell_j;
		temp = game_map[i+1][j];
		game_map[i+1][j] = 0; // zero is empty cell for my program //
		game_map[i][j] = temp;
		*cell_i = (*cell_i) + 1;
	}
}

void findCellPosition(int game_map[9][9], int size, int *cell_i, int *cell_j)
{

	// find the empty cell position coordinate //

	for(int i = 0 ; i < size ; ++i)
		for(int j = 0 ; j < size ; ++j)
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

int isFinish(int game_map[81], int size)
{

	/*
		I send the two-dimensional array as one-dimensional to this function.
		because controlling is easy for me
	*/

	int count = 0;
	int n = (size*size) - 2; 
	int goalCount = (n*(n+1)) / 2; 

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
		7 == (size*size) - 2    ----> int n;

		SUM OF THEESE:  7 + 6 + 5 ... + 1
		so -> (n*(n+1))/2         ----> goal count;

		if the map is in order, this math/algorithm is consistent
	*/

	if(game_map[10*(size-1)] == 0)
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

	cout << "Please enter the problem size" << endl;
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

void fill_gamemap(int game_map[9][9])
{
	for(int i = 0 ; i < 9 ; ++i)
		for(int j = 0 ; j < 9 ; ++j)
			game_map[i][j] = -1;
}

void generate_gamemap(int game_map[9][9], int size)
{
	int number = rand() % (size*size);

	fill_gamemap(game_map);

	for(int i = 0 ; i < size ; ++i)
	{
		for(int j = 0 ; j < size ; ++j)
		{
			while(testSame(game_map, number, size)) // Prevent to be same numbers on board //
				number = rand() % (size*size);

			game_map[i][j] = number;
		}
	}
}

int testSame(int game_map[9][9], int finder, int size) // Prevent to be same numbers on board //
{
	for(int i = 0 ; i < size ; ++i)
		for(int j = 0 ; j < size ; ++j)	
			if(finder == game_map[i][j])
				return 1;

	return 0;
}

int isNot_Solvable(int game_map[81], int N)
{
	int count = 0;

	/*
		I send the two-dimensional array as one-dimensional to this function.
		because controlling is easy for me
	*/

	for(int i = 0 ; i < (9*9)-1 ; ++i)
		for(int j = i+1 ; j < (9*9) ; ++j)
			if(game_map[i] != -1 && game_map[j] != -1 && game_map[i] && game_map[j] && game_map[i] > game_map[j])
				++count;

	// my integer variable "count" is inversion count //
	return count;
}

void print_map(int game_map[9][9], int size)
{
	for(int i = 0 ; i < size ; ++i)
	{
		for(int j = 0 ; j < size ; ++j)
		{
			if(game_map[i][j] == -1 || game_map[i][j] == 0)
				cout << "    ";
			else if(game_map[i][j] > 9)
				cout << game_map[i][j] << "  ";
			else
				cout << game_map[i][j] << "   ";
		}
		cout << endl;
	}
}