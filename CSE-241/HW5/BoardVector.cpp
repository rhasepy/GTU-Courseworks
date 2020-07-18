/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork 5
*/
#include "BoardVector.h"
#include "BoardArray2D.h"
#include "BoardArray1D.h"

using namespace std;

namespace NPuzzle
{
	/*
		The program fill with -1 board firstly
		and fill different random numbers
		and reset(to be solvable)
		and shuffle
	*/
	BoardVector::BoardVector()
						: AbstractBoard(3,3)
	{
		generateMap();
		shuffle(9);
	}

	BoardVector::BoardVector(int val_i)
						: AbstractBoard(val_i, 3)
	{
		generateMap();
		shuffle(val_i*3);
	}

	BoardVector::BoardVector(int val_i, int val_j)
						: AbstractBoard(val_i, val_j)
	{
		generateMap();
		shuffle(val_i*val_j);
	}

	BoardVector::BoardVector(const string& fileName)
						: AbstractBoard(fileName)
	{
		readFromFile(fileName);
	}

	BoardVector::~BoardVector() // destructor
	{
		gameBoard.clear();
	}

	const BoardVector& BoardVector::operator =(const AbstractBoard& rightSide)
	// assignment operator //
	{
		const BoardArray1D* test1D = dynamic_cast <const BoardArray1D*> (&rightSide);
		const BoardVector* testVector = dynamic_cast <const BoardVector*> (&rightSide);
		const BoardArray2D* test2D = dynamic_cast <const BoardArray2D*> (&rightSide);

		/*
			I used dynamic cast because user want to board vector assigning instead of board 1D
			For two of them are AbstarctBoard
			This assignment must work
			But for everyone different work
		*/

		if(this == &rightSide)
			return *this;

		else if(testVector != nullptr)
		{
			int val_i = testVector -> getSize_i();
			int val_j = testVector -> getSize_j();

			this -> gameBoard = testVector -> gameBoard;

			this -> setSize(val_i, val_j);
			this -> cell_i = testVector -> cell_i;
			this -> cell_j = testVector -> cell_j;
			this -> set_previous_move(testVector -> lastMove());
			this -> set_totalMoves(testVector -> numberOfMoves());

			return (*this);
		}

		else if(test1D != nullptr)
		{
			int val_i = test1D -> getSize_i();
			int val_j = test1D -> getSize_j();
			int value;

			this -> gameBoard.clear();

			for(int i = 0 ; i < val_i ; ++i)
			{
				vector <int> tempBoard;

				for(int j = 0 ; j < val_j ; ++j)
				{
					value = (*test1D)(i, j);
					tempBoard.push_back(value);
				}

				this -> gameBoard.push_back(tempBoard);
			}

			this -> setSize(val_i, val_j);
			this -> cell_i = test1D -> get_emptyCell_i();
			this -> cell_j = test1D -> get_emptyCell_j();
			this -> set_previous_move(test1D -> lastMove());
			this -> set_totalMoves(test1D -> numberOfMoves());

			return (*this);
		}

		else if(test2D != nullptr)
		{
			int val_i = test2D -> getSize_i();
			int val_j = test2D -> getSize_j();
			int value;

			this -> gameBoard.clear();

			for(int i = 0 ; i < val_i ; ++i)
			{
				vector <int> tempBoard;

				for(int j = 0 ; j < val_j ; ++j)
				{
					value = (*test2D)(i, j);
					tempBoard.push_back(value);
				}

				this -> gameBoard.push_back(tempBoard);
			}

			this -> setSize(val_i, val_j);
			this -> cell_i = test2D -> get_emptyCell_i();
			this -> cell_j = test2D -> get_emptyCell_j();
			this -> set_previous_move(test2D -> lastMove());
			this -> set_totalMoves(test2D -> numberOfMoves());

			return (*this);
		}
	}

	int BoardVector::get_emptyCell_i() const
	{
		return cell_i;
	}

	int BoardVector::get_emptyCell_j() const
	{
		return cell_j;
	}

	void BoardVector::generateMap()
	{
		int val_i = getSize_i();
		int val_j = getSize_j();

		int number = rand() % (val_i*val_j);

		fill_and_init_gamemap(); // fill the board with -1 //

		for(int i = 0 ; i < val_i ; ++i)
		{
			for(int j = 0 ; j < val_j ; ++j)
			{
				while(testSame(number)) // Prevent to be same numbers on board //
					number = rand() % (val_i*val_j);

				gameBoard.at(i).at(j) = number;
			}
		}

		set_emptyCellPosition();
	}

	void BoardVector::fill_and_init_gamemap()
	{
		for(int i = 0 ; i < getSize_i() ; ++i)
		{
			vector <int> board_row;

			for(int j = 0 ; j < getSize_j() ; ++j)
				board_row.push_back(-1);

			gameBoard.push_back(board_row);
		}
	}

	bool BoardVector::testSame(int finder) const
	{
		for(int i = 0 ; i < getSize_i() ; ++i)
			for(int j = 0 ; j < getSize_j() ; ++j)	
				if(finder == gameBoard[i][j])
					return true;

		return false;	
	}

	void BoardVector::set_emptyCellPosition()
	{
		for(int i = 0 ; i < getSize_i() ; ++i)
			for(int j = 0 ; j < getSize_j() ; ++j)
				if(gameBoard[i][j] == 0)
				{
					cell_i = i;
					cell_j = j;
				}
	}

	int BoardVector::findMax() const
	{
		int max = 1;

		for(int i = 0 ; i < getSize_i() ; ++i)
			for(int j = 0 ; j < getSize_j() ; ++j)
				if(gameBoard.at(i).at(j) > max)
					max = gameBoard.at(i).at(j);

		return max;
	}

	void BoardVector::shuffle(int N)
	{
		reset();

		int prev_cell_i = cell_i;
		int prev_cell_j = cell_j;

		for(int i = 0 ; i < N ; ++i)
		{
			while(prev_cell_i == cell_i && prev_cell_j == cell_j)
				moveRandom();

			prev_cell_i = cell_i;
			prev_cell_j = cell_j;
		}

		set_previous_move('S');
		set_totalMoves(0);
	}

	void BoardVector::reset()
	{
		int count = 1;

		int maxNumber = findMax();

		for(int i = 0 ; i < getSize_i() ; ++i)
		{
			for(int j = 0 ; j  < getSize_j() ; ++j)
			{
				if(count <= maxNumber)
				{
					gameBoard.at(i).at(j) = count;
					++count;
				}

				else if(count == maxNumber + 1)
				{
					gameBoard.at(i).at(j) = 0;
					set_emptyCellPosition();
					set_totalMoves(0);
					set_previous_move('S');
					return;
				}
			}
		}
	}

	char BoardVector::moveRandom()
	{
		int random_move;

		int val_i = getSize_i();
		int val_j = getSize_j();

		while(true)
		{
			random_move = rand() % 4;

			if(random_move == 0 && cell_j != 0) // condition left
			{
				move('L');
				return 'L';
			}

			else if(random_move == 1 && cell_j < val_j-1) // condition right
			{
				move('R');
				return 'R';
			}
		
			else if(random_move == 2 && cell_i != 0) // condition up
			{
				move('U');
				return 'U';
			}

			else if(random_move == 3 && cell_i < (val_i-1)) // condition down
			{
				move('D');
				return 'D';
			}
		}
	}

	char BoardVector::move(char choice)
	{
		if(choice == 'L' || choice == 'l') // conditions left
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

					set_totalMoves(numberOfMoves() + 1);
					set_emptyCellPosition();
					set_previous_move('L');
					return 'L';
				}
			}
		}

		else if(choice == 'R' || choice == 'r')  // conditions right
		{
			int temp, i, j;

			if(cell_j < (getSize_j()-1))
			{
				if(gameBoard[cell_i][cell_j+1] != -1)
				{
					i = cell_i;
					j = cell_j;
					temp = gameBoard.at(i).at(j+1);
					gameBoard.at(i).at(j+1) = 0;
					gameBoard.at(i).at(j) = temp;

					set_totalMoves(numberOfMoves() + 1);
					set_emptyCellPosition();
					set_previous_move('R');
					return 'R';
				}
			}
		}

		else if(choice == 'U' || choice == 'u')  // conditions up
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
					
					set_totalMoves(numberOfMoves() + 1);
					set_emptyCellPosition();
					set_previous_move('U');
					return 'U';
				}
			}
		}

		else if(choice == 'D' || choice == 'd')  // conditions down
		{
			int temp, i, j;

			if(cell_i < (getSize_i()-1)) 
			{
				if(gameBoard[cell_i+1][cell_j] != -1)
				{
					i = cell_i;
					j = cell_j;
					temp = gameBoard.at(i+1).at(j);
					gameBoard.at(i+1).at(j) = 0; 
					gameBoard.at(i).at(j) = temp;
					
					set_totalMoves(numberOfMoves() + 1);
					set_emptyCellPosition();
					set_previous_move('D');
					return 'D';
				}
			}
		}
	}

	bool BoardVector::isSolved() const
	{
		BoardVector finalObject(this -> getSize_i(), this -> getSize_j());
		finalObject.reset();

		return ((*this) == finalObject);
	}

	int BoardVector::operator()(int i, int j) const
	{
		return gameBoard[i][j];
	}

	void BoardVector::readFromFile(const string& myFile)
	{
		int i = 0, j = 0;
		int space = 0, count = 0;
		char map_char[26];
		string line = "";

		gameBoard.clear();

		/********* FOR THE SETTING SIZE *********/
		ifstream readFile(myFile);

		if(readFile.is_open())
		{
			while(getline(readFile, line))
			{
				strcpy(map_char, line.c_str());
				++i;
			}

			while(map_char[count] != '\0')
			{
				if(map_char[count] == ' ')
					++space;

				++count;
			}
		}

		setSize(i, space+1);
		readFile.close();
		/********* FOR THE SETTING SIZE *********/

		/********* FOR THE FILLING MAP  *********/
		readFile.clear();
		readFile.open(myFile);

		for(int i = 0 ; i < 26 ; ++i)
			map_char[i] = '\0';

		line = "";
		i = 0;
		j = 0;

		if(readFile.is_open())
		{
			for(int i = 0 ; i < getSize_i() ; ++i)
			{
				vector <int> tempBoard;

				for(int j = 0 ; j < getSize_j() ; ++j)
					tempBoard.push_back(-1);

				gameBoard.push_back(tempBoard);
			}

			while(getline(readFile, line))
			{
				j = 0;

				strcpy(map_char, line.c_str());

				char_to_int(map_char, i , j);

				++i;
			}

		}

		set_totalMoves(0);

		set_emptyCellPosition();

		set_previous_move('S');

		readFile.close();
		/********* FOR THE FILLING MAP  *********/
	}

	void BoardVector::char_to_int(char *map_char, int i, int j)
	{
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

	void BoardVector::play()
	{
		char choice;
		string fileName;

		do
		{	
			print();

			cout << "Your choice: ";
			cin >> choice;

			switch(choice)
			{
				case 'L':
				case 'l':
					move('L');
					break;

				case 'R':
				case 'r':
					move('R');
					break;

				case 'U':
				case 'u':
					move('U');
					break;

				case 'D':
				case 'd':
					move('D');
					break;

				case 'Q':
				case 'q':
					cout << "The program turning off..." << endl;
					exit(1);
					break;

				case 'E':
				case 'e':
					cout << "Please enter the file name to load current board(with EXTENSION): ";
					cin >> fileName;
					writeToFile(fileName);
					break;

					case 'O':
					case 'o':
						cout << "Please enter the file name to load current board(with EXTENSION): ";
						cin >> fileName;
						readFromFile(fileName);
						break;

					default:
						cout << "You have chosen invalid choice..." << endl
							<< "Please choose one of them (E, O, L, R, U, D)" << endl;
							break;
			}
		}while(!(isSolved()));

		if(isSolved())
		{
			cout << "**********Final Board**********" << endl;
			print();
			cout << "*******************************" << endl;
			cout << "Problem Solved!" << endl;
			cout << "Total Moves: " << numberOfMoves() << endl;
		}
	}
}