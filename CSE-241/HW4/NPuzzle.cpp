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

NPuzzle::NPuzzle(string myFile) // my constructor for argument is given by user //
{
	readFromFile(myFile);
}

NPuzzle::NPuzzle(int val_i, int val_j) // my constructor for agrument is not given 
{
	myBoard.resize(1);

	val_i = input_size_i();	
	val_j = input_size_j();
	setsize(val_j,val_i);
	myBoard[0].generateMap();
	reset();
	shuffle(200);
	myBoard[0].init_TotalMoves();
}

NPuzzle::NPuzzle() // my default constructor 
{
	cout << "Something went wrong" << endl;
	cout << "Program is turning off..." << endl;
}

NPuzzle::Board::Board() // my constructor for if the object produced then object count increment
{
	++objectCount;
}

void NPuzzle::setTotalMoves(int x) // set total moves 
{
	myBoard[0].set_TotalMoves(x);
}

int NPuzzle::input_size_i()
{
	int val_i;
	char test_choice;

	cout << "Enter the size of heigh: ";

	do
	{
		cin >> test_choice;

		val_i = static_cast<int> (test_choice - 48);
		//To prevent the possibility of entering characters.//

		if(val_i > 10 || val_i < 3)
			cout << "Please choose integer between 3 and 9" << endl;

		}while(val_i > 10 || val_i < 3);

	return val_i;
}

int NPuzzle::input_size_j()
{
	int val_j;
	char test_choice;

	cout << "Enter the size of width: ";

	do
	{
		cin >> test_choice;

		val_j = static_cast<int> (test_choice - 48);
		//To prevent the possibility of entering characters.//

		if(val_j > 10 || val_j < 3)
			cout << "Please choose integer between 3 and 9" << endl;

		}while(val_j > 10 || val_j < 3);

	return val_j;
}

bool NPuzzle::isSolved() const // is solved return true and give final note //
{
	if(myBoard[0].isSolved())
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

int NPuzzle::get_size_i() const// get column size of shape
{
	return myBoard[0].getSize_i();
}

int NPuzzle::get_size_j() const// get row size of shape
{
	return myBoard[0].getSize_j();
}

void NPuzzle::print() const
{
	myBoard[0].print();
}

void NPuzzle::printReport() const
{
	if(!myBoard[0].isSolved())
	{
		cout << "Map is not solved" << endl;
		cout << myBoard[0].numberOfMoves() << " moves have been done..." << endl;
	}

	else
		cout << myBoard[0].numberOfMoves() << " moves have been done..." << endl;
}

void NPuzzle::readFromFile(string myFile)
{
	myBoard.resize(1); // I have a board //
	myBoard[0].fill_and_init_gamemap(); // initialize board and fill -1 firstly //
	myBoard[0].readFromFile(myFile);
}

void NPuzzle::writeToFile(string newFile) const
{
	myBoard[0].writeToFile(newFile);
}

void NPuzzle::shuffle(int N) 
{
	myBoard.resize(1);
	int init_i = myBoard[0].get_emptyCell_i();
	int init_j = myBoard[0].get_emptyCell_j();
	
	for(int i = 0 ; i < N ; ++i)
	{
		while(init_i == myBoard[0].get_emptyCell_i() && init_j == myBoard[0].get_emptyCell_j())
		{
			// has the empty cell moved or not //
			moveRandom();
		}

		init_i = myBoard[0].get_emptyCell_i();
		init_j = myBoard[0].get_emptyCell_j();
	}

	myBoard[0].set_prev_move('S'); // there should be no previous moves that will affect the Intelligent move after shuffle
}

void NPuzzle::reset()
{
	myBoard[0].reset();
}

void NPuzzle::setsize(int x, int y)
{
	myBoard[0].setSize(x,y);
}

char NPuzzle::moveRandom()
{
	myBoard.resize(1);
	// function produced random move, if the cell can //
	int random_move;
	int cell_i = myBoard[0].get_emptyCell_i();
	int cell_j = myBoard[0].get_emptyCell_j();
	int size_i = myBoard[0].getSize_i();
	int size_j = myBoard[0].getSize_j();

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

char NPuzzle::move(char choice)
{
	char return_val;

	myBoard.resize(1);

	if(choice == 'L' || choice == 'l')
		 myBoard[0].set_prev_move('L');
	else if(choice == 'D' || choice == 'd')
		myBoard[0].set_prev_move('D');
	else if(choice == 'R' || choice == 'r')
		myBoard[0].set_prev_move('R');
	else if(choice == 'U' || choice == 'u')
		myBoard[0].set_prev_move('U');

	return_val = myBoard[0].move(choice);

	return return_val;
}

void NPuzzle::solvePuzzle()
{
	int index = 0;
	int count_iteration;
	bool loopCondition = true;
	char lastMove_myBoard;

	int emptyCell_i = myBoard[index].get_emptyCell_i();
	int emptyCell_j = myBoard[index].get_emptyCell_j();
	int mapSize_i = get_size_i();
	int mapSize_j = get_size_j();

	vector <Board> goalMap;  // the goalMap vector is the solution //
	goalMap.push_back(myBoard[0]);
	goalMap[0].reset();

	while(!isTheSameVector(goalMap[0])) // while the myBoard is not solution //
	{
		vector <Board> temp;
		count_iteration = 0;

		lastMove_myBoard = myBoard[index].lastMove();

		if(emptyCell_j < (mapSize_j-1)) // for right
		{
			if(myBoard[index](emptyCell_i, emptyCell_i+1) != -1)
			{
				myBoard[index].move('R');
				temp.push_back(myBoard[index]);
				myBoard[index].move('L');
				++count_iteration;
				myBoard[index].init_TotalMoves();
			}
		}

		if(emptyCell_j != 0) // for left
		{
			if(myBoard[index](emptyCell_i, emptyCell_j-1) != -1)
			{
				myBoard[index].move('L');
				temp.push_back(myBoard[index]);
				myBoard[index].move('R');
				++count_iteration;
				myBoard[index].init_TotalMoves();
			}
		}

		if(emptyCell_i != 0) // for up
		{
			if(myBoard[index](emptyCell_i-1, emptyCell_j) != -1)
			{
				myBoard[index].move('U');
				temp.push_back(myBoard[index]);
				myBoard[index].move('D');
				++count_iteration;
				myBoard[index].init_TotalMoves();
			}
		} 

		if(emptyCell_i < (mapSize_i-1)) // for down
		{
			if(myBoard[index](emptyCell_i+1, emptyCell_j))
			{
				myBoard[index].move('D');
				temp.push_back(myBoard[index]);
				myBoard[index].move('U');
				++count_iteration;
				myBoard[index].init_TotalMoves();
			}
		}

		myBoard[index].set_prev_move(lastMove_myBoard); // I'm turning my last move into normal.

		for(int j = 0 ; j < count_iteration ; ++j)
		{
			if(!isTheSameVector(temp[j])) // if the vector has not been to myBoard vector, then push back //
				myBoard.push_back(temp[j]);
		}

		++index;
		emptyCell_i = myBoard[index].get_emptyCell_i(); // update new empty cell i
		emptyCell_j = myBoard[index].get_emptyCell_j();	// update new empty cell j

		if(isTheSameVector(goalMap[0])) // if the any element of my vector reaches solution
		{	
			int indexOfLast = myBoard.size() - 1;
			reverse_and_print(indexOfLast);
		}
	}
}

void NPuzzle::reverse_and_print(int index)
{
	char last_move;
	int count = 0;
	vector <Board> reversedBoard;
	vector <char> findMove;  // save the my moves from solution to initial state //

	reversedBoard.push_back(myBoard[index]);

	while(!(myBoard[0] == reversedBoard[0]))
	{
		last_move = myBoard[index].lastMove(); // I save last reverse move to turn back //

		if(last_move == 'R')
		{
			reversedBoard[0].move('L');
			findMove.push_back('L');
		}

		else if(last_move == 'L')
		{
			reversedBoard[0].move('R');
			findMove.push_back('R');
		}

		else if(last_move == 'U')
		{
			reversedBoard[0].move('D');
			findMove.push_back('D');
		}

		else if(last_move == 'D')
		{
			reversedBoard[0].move('U');
			findMove.push_back('U');
		}

		else if(last_move == 'S')
			findMove.push_back('R');

		++count;

		index = whichVector(reversedBoard[0]); // return the vector which index of myBoard vector //
	}

	int reverse_move_index = findMove.size();

	for(int i = (reverse_move_index-1) ; i >= 0 ; --i) // reverse move to reach to initial state
	{
		if(findMove[i] == 'R')
		{
			myBoard[0].move('L');
			myBoard[0].print();
		}

		else if(findMove[i] == 'L')
		{
			myBoard[0].move('R');
			myBoard[0].print();
		}

		else if(findMove[i] == 'U')
		{
			myBoard[0].move('D');
			myBoard[0].print();
		}

		else if(findMove[i] == 'D')
		{
			myBoard[0].move('U');
			myBoard[0].print();
		}
	}
}

int NPuzzle::whichVector(const Board& object) // find and return object is which vector of myBoard
{
	for(int i = 0 ; i < myBoard.size() ; ++i)
		if(myBoard[i] == object)
			return i;
}

bool NPuzzle::isTheSameVector(const Board &object) // if the my board has object return true //
{
	for(int i = 0 ; i < myBoard.size() ; ++i)
	{
		if(myBoard[i] == object)
			return true;
	}

	return false;
}

int NPuzzle::Board::numberOfBoards() const
{
	return objectCount;
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
				gameBoard.at(i).at(j) = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

				if(gameBoard.at(i).at(j) == 0)
					gameBoard.at(i).at(j) = -1;

				++j;
			}

			else
			{
				gameBoard.at(i).at(j) = 0;
				++j;
			}
		}

		else if(map_char[index] == '\0')
		{
			if(map_char[index-1] != 'b')
			{
				gameBoard.at(i).at(j) = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

				if(gameBoard.at(i).at(j) == 0)
					gameBoard.at(i).at(j) = -1;

				++j;
			}

			else
			{
				gameBoard.at(i).at(j) = 0;
				++j;
			}
			
			index = 99; // I dont want to use break so, i used index asaignmet 99 to break for loop //
		}

		else if(index == 25)
		{
			if(map_char[index-1] != 'b')
			{
				gameBoard.at(i).at(j) = static_cast<int> (10*(map_char[index-1] - 48)) + (map_char[index] - 48);

				if(gameBoard.at(i).at(j) == 0)
					gameBoard.at(i).at(j) = -1;
			}

			else
				gameBoard.at(i).at(j) = 0;
		}
	}
}

void NPuzzle::Board::set_prev_move(char x) // set previous move //
{
	previous_move = x;
}

char NPuzzle::Board::lastMove() const// get previous move //
{
	return previous_move;
}

void NPuzzle::Board::init_TotalMoves() // initialize total moves after start shuffle //
{
	totalMoves = 0;
}

void NPuzzle::Board::set_TotalMoves(int x) // if the empty cell move, total moves increment //
{
	totalMoves = totalMoves + x;
}

int NPuzzle::Board::numberOfMoves() const
{
	return totalMoves;
}

int NPuzzle::Board::getSize_i() const
{
	return size_i;
}

int NPuzzle::Board::getSize_j() const
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

int NPuzzle::Board::get_emptyCell_i() const
{
	return cell_i;
}

int NPuzzle::Board::get_emptyCell_j() const
{
	return cell_j;
}

void NPuzzle::Board::generateMap() // the generate map
{
	int number = rand() % (size_i*size_j);

	fill_and_init_gamemap(); // initiliaze and fill with -1 map

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{
			while(testSame(number)) // Prevent to be same numbers on board //
				number = rand() % (size_i*size_j);

			gameBoard.at(i).at(j) = number;
		}
	}

	set_emptyCellPosition();
}

inline void NPuzzle::Board::fill_and_init_gamemap()
{
	for(int i = 0 ; i < 9 ; ++i)
	{
		vector <int> board_row;

		for(int j = 0 ; j < 9 ; ++j)
			board_row.push_back(-1);

		gameBoard.push_back(board_row);
	}
			
}

bool NPuzzle::Board::testSame(int finder) const
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
				if(gameBoard[i][j] != -1)
					gameBoard.at(i).at(j) = -1;

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

	init_TotalMoves();

	set_prev_move('S');

	readFile.close();
}

void NPuzzle::Board::writeToFile(string newFile) const
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
			if(count <= maxNumber)
			{			
				gameBoard.at(i).at(j) = count;
				++count;
			}

			else if(count == maxNumber+1) 
			{
				gameBoard.at(i).at(j) = 0;
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

char NPuzzle::Board::move(char choice)
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
				temp = gameBoard.at(i).at(j-1);
				gameBoard.at(i).at(j-1) = 0;
				gameBoard.at(i).at(j) = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
				set_prev_move('L');
				return 'L';
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
				temp = gameBoard.at(i).at(j+1);
				gameBoard.at(i).at(j+1) = 0;
				gameBoard.at(i).at(j) = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
				set_prev_move('R');
				return 'R';
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
				temp = gameBoard.at(i-1).at(j);
				gameBoard.at(i-1).at(j) = 0;
				gameBoard.at(i).at(j) = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
				set_prev_move('U');
				return 'U';
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
				temp = gameBoard.at(i+1).at(j);
				gameBoard.at(i+1).at(j) = 0; 
				gameBoard.at(i).at(j) = temp;
				set_emptyCellPosition();
				set_TotalMoves(1);
				set_prev_move('D');
				return 'D';
			}

			else
				cout << "Invalid move..." << endl << endl;
		}

		else
			cout << "Invalid move..." << endl << endl;
	}
}

bool NPuzzle::Board::isSolved() const
{
	int count = 0, index = 0, index_of_bb = 0;
	vector < vector <int> > temp_map;
	int game_map[81];

	for(int i = 0 ; i < 9 ; ++i)
	{
		vector <int> temp_map_row;

		for(int j = 0 ; j < 9 ; ++j, ++index)
		{
			game_map[index] = gameBoard.at(i).at(j);
			temp_map_row.push_back(game_map[index]);
		}

		temp_map.push_back(temp_map_row);
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

int NPuzzle::Board::findMax(const vector< vector <int> > game_map, int size_i, int size_j) const
{
	int max = 1;

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
			if(game_map.at(i).at(j) > max)
				max = game_map.at(i).at(j);

	return max;
}

bool NPuzzle::Board::operator ==(const Board& object1)
{
	int size_i = object1.getSize_i();
	int size_j = object1.getSize_j();
	int count = 0;

	for(int i = 0 ; i < size_i ; ++i)
		for(int j = 0 ; j < size_j ; ++j)
		{
			if(object1(i,j) == gameBoard[i][j])
				++count;
		}

	if(count == (size_i*size_j))
		return true;
	else
		return false;
}

int NPuzzle::Board::operator()(int i, int j) const
{
	return gameBoard[i][j];
}

ostream& operator <<(ostream& outputStream, const NPuzzle& object)
{
	int size_i = object.get_size_i();
	int size_j = object.get_size_j();

	for(int i = 0 ; i < size_i ; ++i)
	{
		for(int j = 0 ; j < size_j ; ++j)
		{
			if(object.myBoard[0](i,j) == -1)
			{
				outputStream << "00 ";
			}

			else if(object.myBoard[0](i,j) == 0)
			{
				outputStream << "bb" << " ";
			}

			else
			{
				if(object.myBoard[0](i,j) < 10 && object.myBoard[0](i,j) != 0)
				{
					outputStream << "0" << object.myBoard[0](i,j) << " ";
				}

				else
				{
					outputStream << object.myBoard[0](i,j) << " ";
				}
			}
		}

		outputStream << endl;
	}

	return outputStream;
}

istream& operator >>(istream &inputStream, NPuzzle& object)
{
	string fileName;

	cout << "Please enter the file name to load current board(with EXTENSION): ";
	inputStream >> fileName;

	object.readFromFile(fileName);

	return inputStream;
}