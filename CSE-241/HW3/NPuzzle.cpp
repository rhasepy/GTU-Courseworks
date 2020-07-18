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

NPuzzle::NPuzzle(string myFile) // my constructor for argument is given by user //
{
	readFromFile(myFile);
}

NPuzzle::NPuzzle(int x, int y) // my constructor for agrument is not given 
{
	setsize(x,y);
	myBoard.generateMap();
	shuffle(20);
	myBoard.init_TotalMoves();
}

NPuzzle::NPuzzle() // my default constructor 
{
	cout << "Something went wrong" << endl;
	cout << "Program is turning off..." << endl;
	return;
}

bool NPuzzle::isSolved() 
{
	if(myBoard.isSolved())
	{
		cout << "**********Final Board**********" << endl;
		print();
		cout << "*******************************" << endl;
		cout << "Problem Solved!" << endl;
		printReport();
		return true;
	}

	else
		return false;
}

int NPuzzle::get_size_i() // get column size of shape
{
	return myBoard.getSize_i();
}

int NPuzzle::get_size_j() // get row size of shape
{
	return myBoard.getSize_j();
}

void NPuzzle::print() const
{
	myBoard.print();
}

void NPuzzle::printReport()
{
	if(!myBoard.isSolved())
	{
		cout << "Map is not solved" << endl;
		cout << myBoard.get_TotalMoves() << " moves have been done..." << endl;
	}

	else
		cout << myBoard.get_TotalMoves() << " moves have been done..." << endl;
}

void NPuzzle::readFromFile(string myFile)
{
	myBoard.readFromFile(myFile);
}

void NPuzzle::writeToFile(string newFile)
{
	myBoard.writeToFile(newFile);
}

void NPuzzle::shuffle(int N) 
{
	int init_i = myBoard.get_emptyCell_i();
	int init_j = myBoard.get_emptyCell_j();

	myBoard.reset();
	
	for(int i = 0 ; i <= N ; ++i)
	{
		while(init_i == myBoard.get_emptyCell_i() && init_j == myBoard.get_emptyCell_j())
		{
			// has the empty cell moved or not //
			moveRandom();
		}

		init_i = myBoard.get_emptyCell_i();
		init_j = myBoard.get_emptyCell_j();
	}

	myBoard.set_prev_move('X'); // there should be no previous moves that will affect the Intelligent move after shuffle
}

void NPuzzle::reset()
{
	myBoard.reset();
}

void NPuzzle::setsize(int x, int y)
{
	myBoard.setSize(x,y);
}

char NPuzzle::moveRandom()
{
	// function produced random move, if the cell can //
	int random_move;
	int cell_i = myBoard.get_emptyCell_i();
	int cell_j = myBoard.get_emptyCell_j();
	int size_i = myBoard.getSize_i();
	int size_j = myBoard.getSize_j();

	while(true)
	{
		random_move = rand() % 4;

		if(random_move == 0 && cell_j != 0)
		{
			move('L');
			return 'L';
		}

		else if(random_move == 1 && cell_j < size_j-1)
		{
			move('R');
			return 'R';
		}
	
		else if(random_move == 2 && cell_i != 0)
		{
			move('U');
			return 'U';
		}

		else if(random_move == 3 && cell_i < (size_i-1))
		{
			move('D');
			return 'D';
		}
	}
}

char NPuzzle::moveIntelligent()
{
	int size_i = myBoard.getSize_i();
	int size_j = myBoard.getSize_j();
	int cell_i = myBoard.get_emptyCell_i();
	int cell_j = myBoard.get_emptyCell_j();
	int cost[4] = {99,99,99,99};
				// cost[0] -> move left cost //
				// cost[1] -> move right cost //
				// cost[2] -> mode up cost //
				// cost[3] -> move down cost //
	int min_cost;
	char prevMove = myBoard.get_prev_move();

	if( cell_j != 0 && prevMove != 'R' ) // if the empty cell can move left //
	{
		move('L');
		cost[0] = myBoard.heuristic_cost();
		move('R');	// After trying the left move back to the old position by making the reverse move. //
		myBoard.set_TotalMoves(-2);
	}

	if( cell_j < (size_j-1) && prevMove != 'L' ) // if the empty cell can move right //
	{
		move('R');
		cost[1] = myBoard.heuristic_cost();
		move('L');	// After trying the right move back to the old position by making the reverse move. //
		myBoard.set_TotalMoves(-2);
	}

	if( cell_i != 0 && prevMove != 'D' ) // if the empty cell can move up //
	{
		move('U');
		cost[2] = myBoard.heuristic_cost();
		move('D');	// After trying the up move back to the old position by making the reverse move. //
		myBoard.set_TotalMoves(-2);
	}

	if( cell_i < (size_i-1) && prevMove != 'U' ) // if the empty cell can mode down //
	{
		move('D');
		cost[3] = myBoard.heuristic_cost();
		move('U');	// After trying the down move back to the old position by making the reverse move. //
		myBoard.set_TotalMoves(-2);
	}
 
	min_cost = cost[0];

	for(int i = 1 ; i < 4 ; ++i)
	{
		if(min_cost >= cost[i])
			min_cost = cost[i];
		// find the minimum cost //
	}

	// this is the minimum cost of moving the array to which it equals. //

	if(min_cost == cost[0])
	{
		move('L');
		cout << "Intelligent move chooses L " << endl << endl;
		return 'L';
	}

	else if(min_cost == cost[1])
	{
		move('R');
		cout << "Intelligent move chooses R" << endl << endl;
		return 'R';
	}

	else if(min_cost == cost[2])
	{
		move('U');
		cout << "Intelligent move chooses U " << endl << endl;
		return 'U';
	}

	else if(min_cost == cost[3])
	{
		move('D');
		cout << "Intelligent move chooses D " << endl << endl;
		return 'D';
	}
}

void NPuzzle::move(char choice)
{
	if(choice == 'L' || choice == 'l')
		 myBoard.set_prev_move('L');
	else if(choice == 'D' || choice == 'd')
		myBoard.set_prev_move('D');
	else if(choice == 'R' || choice == 'r')
		myBoard.set_prev_move('R');
	else if(choice == 'U' || choice == 'u')
		myBoard.set_prev_move('U');

	myBoard.move(choice);
}

void NPuzzle::solvePuzzle()
{
	int size_i = myBoard.getSize_i();
	int size_j = myBoard.getSize_j();
	int array_index = 2*((2*(size_i-1)) + (2*(size_j-1)));
	char LastMoves[array_index];
	int count_intellegent = 0;
	decltype(array_index) index = 0;

	for(int i = 0 ; i < array_index ; ++i)
		LastMoves[i] = 'X';

	while(!myBoard.isSolved())
	{
		moveIntelligent();
		print();

		LastMoves[ (count_intellegent % (array_index)) ] = myBoard.get_prev_move();
		++count_intellegent;

		if(isLastMovesSame(LastMoves, array_index)) // if my array get stuck, move randomly algorithm
		{
			if(count_intellegent < 500)
			{
				for(int i = 0 ; i < 2 ; ++i)
				{
					cout << "Intelligent move chooses " << moveRandom() << endl;
					print();
					++count_intellegent;
				}
			}

			else
			{
				cout << "Intelligent move chooses " << moveRandom() << endl;
				print();
				++count_intellegent;
			}
		}

		else if(count_intellegent > 3000 && count_intellegent % 30 == 0)
		{
			cout << "Intelligent move chooses " << moveRandom() << endl;
			print();
			++count_intellegent;
		}
	}
}

bool NPuzzle::isLastMovesSame(char LastMoves[], int index)
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
	{
		if(LastMoves[i] == LastMoves[i+(index/2)])
			++goalCount;

		if(LastMoves[i] == 'R' && LastMoves[i+1] == 'L')
			return true;
		else if(LastMoves[i] == 'L' && LastMoves[i+1] == 'R')
			return true;
		else if(LastMoves[i] == 'U' && LastMoves[i+1] == 'D')
			return true;
		else if(LastMoves[i] == 'D' && LastMoves[i+1] == 'U')
			return true;
	}

	if(goalCount == (index/2))
		return true;
	else
		return false;
}

void NPuzzle::Board::char_to_int(char map_char[26], int i, int j)
{
	// the function convert char to integer getted line from file //
	for(int index = 0 ; index < 26 ; ++index)
	{
		if(map_char[index] == ' ' && index < 25)
		{
			if(map_char[index-1] != 'b')
			{
				gameBoard[i][j] = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

				if(gameBoard[i][j] == 0)
					gameBoard[i][j] = -1;

				++j;
			}

			else
			{
				gameBoard[i][j] = 0;
				++j;
			}
		}

		else if(map_char[index] == '\0')
		{
			if(map_char[index-1] != 'b')
			{
				gameBoard[i][j] = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

				if(gameBoard[i][j] == 0)
					gameBoard[i][j] = -1;

				++j;
			}

			else
			{
				gameBoard[i][j] = 0;
				++j;
			}
			
			return;
		}

		else if(index == 25)
		{
			if(map_char[index-1] != 'b')
			{
				gameBoard[i][j] = static_cast<int> (10*(map_char[index-1] - 48)) + (map_char[index] - 48);

				if(gameBoard[i][j] == 0)
					gameBoard[i][j] = -1;
			}

			else
				gameBoard[i][j] = 0;
		}
	}
}

void NPuzzle::Board::set_prev_move(char x) // set previous move //
{
	previous_move = x;
}

char NPuzzle::Board::get_prev_move() // get previous move //
{
	return previous_move;
}

int NPuzzle::Board::heuristic_cost() // calculate heuristic cost // 
{
	int cost = 0;
	int test_map[9][9];

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			test_map[i][j] = gameBoard[i][j];

	// test map is the solution //

/*********************fill test map to solution***************************/
	int count = 1;

	int maxNumber = findMax(gameBoard, size_i, size_j);

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{	
			if(count <= maxNumber && test_map[i][j] != -1)
			{							
				test_map[i][j] = count;
				++count;
			}

			else if(count == maxNumber+1) 
				test_map[i][j] = 0;
		}
	}
/*********************fill test map to solution***************************/

	// The function compare test map and game board // 

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(gameBoard[i][j] != test_map[i][j])
				++cost;

	return cost;
}

void NPuzzle::Board::init_TotalMoves() // initialize total moves after start shuffle //
{
	totalMoves = 0;
}

void NPuzzle::Board::set_TotalMoves(int x) // if the empty cell move, total moves increment //
{
	totalMoves = totalMoves + x;
}

int NPuzzle::Board::get_TotalMoves()
{
	return totalMoves;
}

int NPuzzle::Board::getSize_i()
{
	return size_i;
}

int NPuzzle::Board::getSize_j()
{
	return size_j;
}

void NPuzzle::Board::set_emptyCellPosition() // find the empty cell coordinate //
{
	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(gameBoard[i][j] == 0)
			{
				cell_i = i;
				cell_j = j;
			}
}

int NPuzzle::Board::get_emptyCell_i()
{
	return cell_i;
}

int NPuzzle::Board::get_emptyCell_j()
{
	return cell_j;
}

void NPuzzle::Board::generateMap()
{
	int number = rand() % (size_i*size_j);

	fill_gamemap();

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{
			while(testSame(number)) // Prevent to be same numbers on board //
				number = rand() % (size_i*size_j);

			gameBoard[i][j] = number;
		}
	}

	set_emptyCellPosition();
}

void NPuzzle::Board::fill_gamemap()
{
	for(int i = 0 ; i < 9 ; ++i)
		for(int j = 0 ; j < 9 ; ++j)
			gameBoard[i][j] = -1;
}

bool NPuzzle::Board::testSame(int finder)
{
	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)	
			if(finder == gameBoard[i][j])
				return true;

	return false;
}

void NPuzzle::Board::print() const
{
	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{
			if(gameBoard[i][j] == -1)
			{
				cout << "00 ";
			}

			else if(gameBoard[i][j] == 0)
			{
				cout << "bb" << " ";
			}

			else
			{
				if(gameBoard[i][j] < 10 && gameBoard[i][j] != 0)
				{
					cout << "0" << gameBoard[i][j] << " ";
				}

				else
				{
					cout << gameBoard[i][j] << " ";
				}
			}
		}

		cout << endl;
	}

	cout << endl;
}

void NPuzzle::Board::readFromFile(string myFile)
{
	int i = 0, j = 0, index = 0;
	int space = 0, count = 0;
	char map_char[26];
	string line = "";

	for(int i = 0 ; i < 26 ; ++i)
		map_char[i] = '\0';

	ifstream readFile(myFile);

	if(readFile.is_open())
	{
		for(int i = 0 ; i < 9 ; ++i)
			for(int j = 0 ; j < 9 ; ++j)
				gameBoard[i][j] = -1;

		while(getline(readFile, line))
		{
			j = 0;

			strcpy(map_char, line.c_str());

			char_to_int(map_char, i, j);

			++i;
		}
	}

	while(map_char[count] != '\0')
	{
		if(map_char[count] == ' ')
			++space;

		++count;
	}

	setSize(space+1, i);

	set_emptyCellPosition();

	readFile.close();
}

void NPuzzle::Board::writeToFile(string newFile)
{
	ofstream myFile(newFile);

	if(myFile.is_open())
	{
		for(auto i = 0 ; i < size_i ; ++i)
		{
			for(auto j = 0 ; j < size_j ; ++j)
			{
				if(gameBoard[i][j] == 0)
					myFile << "bb";

				else if(gameBoard[i][j] == -1)
					myFile << "00";

				else
				{
					if(gameBoard[i][j] < 10 && gameBoard[i][j] != 0)
						myFile << "0" << gameBoard[i][j];

					else
						myFile << gameBoard[i][j];
				}

				if(j < (size_j-1))
					myFile << " ";
			}

			myFile << endl;
		}

		cout << "The current board load the " << newFile << endl;
	}

	else
		cout << "The current board did not load..." << endl;

	myFile.close();
}

void NPuzzle::Board::reset()
{
	int count = 1;

	int maxNumber = findMax(gameBoard, size_i, size_j);

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{	
			if(count <= maxNumber && gameBoard[i][j] != -1)
			{			
				gameBoard[i][j] = count;
				++count;
			}

			else if(count == maxNumber+1) 
			{
				gameBoard[i][j] = 0;
				set_emptyCellPosition();
				return;
			}
		}
	}
}

void NPuzzle::Board::setSize(int x, int y)
{
	if(x >= 3 && y >= 3 && x <= 9 && y <= 9)
	{
		size_i = y;
		size_j = x;
	}
	else
	{
		cout << "Setting unsuccessful" << endl;
		cout << "The program is turning off..." << endl;
		exit(1);
	}
}

void NPuzzle::Board::move(char choice)
{
	// if the gameBoard[] == -1, there is wall (00) in there //
	// cell can't move //
	if(choice == 'L' || choice == 'l')
	{
		int temp, i, j;

		if(cell_j != 0)
		{
			if(gameBoard[cell_i][cell_j-1] != -1)
			{
				i = cell_i;
				j = cell_j;
				temp = gameBoard[i][j-1];
				gameBoard[i][j-1] = 0;
				gameBoard[i][j] = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
			}

			else
				cout << "Invalid move..." << endl << endl;
		}

		else
			cout << "Invalid move..." << endl << endl;
	}

	else if(choice == 'R' || choice == 'r')
	{
		int temp, i, j;

		if(cell_j < (size_j-1))
		{
			if(gameBoard[cell_i][cell_j+1] != -1)
			{
				i = cell_i;
				j = cell_j;
				temp = gameBoard[i][j+1];
				gameBoard[i][j+1] = 0;
				gameBoard[i][j] = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
			}

			else
				cout << "Invalid move..." << endl << endl;
		}

		else
			cout << "Invalid move..." << endl << endl;
	}

	else if(choice == 'U' || choice == 'u')
	{
		int temp, i, j;

		if(cell_i != 0)
		{
			if(gameBoard[cell_i-1][cell_j] != -1)
			{
				i = cell_i;
				j = cell_j;
				temp = gameBoard[i-1][j];
				gameBoard[i-1][j] = 0;
				gameBoard[i][j] = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
			}

			else
				cout << "Invalid move..." << endl << endl;
		}

		else
			cout << "Invalid move..." << endl << endl;
	}

	else if(choice == 'D' || choice == 'd')
	{
		int temp, i, j;

		if(cell_i < (size_i-1)) 
		{
			if(gameBoard[cell_i+1][cell_j] != -1)
			{
				i = cell_i;
				j = cell_j;
				temp = gameBoard[i+1][j];
				gameBoard[i+1][j] = 0; 
				gameBoard[i][j] = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
			}

			else
				cout << "Invalid move..." << endl << endl;
		}

		else
			cout << "Invalid move..." << endl << endl;
	}
}

bool NPuzzle::Board::isSolved()
{
	int count = 0, index = 0, index_of_bb = 0;
	int temp_map[9][9];
	int game_map[81];

	for(int i = 0 ; i < 9 ; ++i)
		for(int j = 0 ; j < 9 ; ++j, ++index)
		{
			game_map[index] = gameBoard[i][j];
			temp_map[i][j] = game_map[index];
		}

	/*
		I convert the two-dimensional array as one-dimensional in this function.
			because controlling is easy for me
	*/

	int max = findMax(temp_map, size_i, size_j) - 1;
	int maxNumber = findMax(temp_map, size_i, size_j);
	int goalCount = (max*(max+1)) / 2;

	for(int index_of_bb = 0 ; index_of_bb < 81 ; ++index_of_bb)
		if(game_map[index_of_bb] == 0)
			index = index_of_bb;
	
	if(size_i != size_j && game_map[index-1] == maxNumber)
	{
		for (int i = 0 ; i < 81 ; i++)
	        for (int j = i+1 ; j < 81 ; j++)	
	        	if(game_map[i] && game_map[j] && game_map[i] != -1 && game_map[j] != -1 && game_map[i] < game_map[j])
	        	{	
	        		++count;
	        	}
	}

	else if(size_i == size_j && game_map[index-1] == maxNumber)
	{
		for (int i = 0 ; i < 81 ; i++)
			for (int j = i+1 ; j < 81 ; j++)
		       	if(game_map[i] && game_map[j] && game_map[i] != -1 && game_map[j] != -1 && game_map[i] < game_map[j])
		       	{	
		       		++count;
				}
	}
	
	if(count == goalCount)
	{
		return true;
	}
	
	else
		return false;
}

int NPuzzle::Board::findMax(int game_map[9][9], int size_i, int size_j)
{
	int max = 1;

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(game_map[i][j] > max)
				max = game_map[i][j];

	return max;
}