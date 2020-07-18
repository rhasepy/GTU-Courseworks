#include "BoardArray1D.h"
#include "BoardVector.h"
#include "BoardArray2D.h"

using namespace std;

namespace NPuzzle
{
	/*
		The program fill with -1 board firstly
		and fill different random numbers
		and reset(to be solvable)
		and shuffle
	*/
	BoardArray1D::BoardArray1D()
								: AbstractBoard(3,3)
	{
		generateMap();
		shuffle(9);
	}

	BoardArray1D::BoardArray1D(int val_i)
								: AbstractBoard(val_i, 3)
	{
		generateMap();
		shuffle(val_i*3);
	}

	BoardArray1D::BoardArray1D(int val_i, int val_j)
						 		: AbstractBoard(val_i, val_j)
	{
		generateMap();
		shuffle(val_i*val_j);
	}

	BoardArray1D::BoardArray1D(const string& fileName) 
								: AbstractBoard(fileName)
	{
		setSize(0, 0);
		readFromFile(fileName);
	}

	BoardArray1D::BoardArray1D(const BoardArray1D& copyObject)
								: AbstractBoard(copyObject.getSize_i(), copyObject.getSize_j())
	// Copy constructor //
	{
		int val_i = copyObject.getSize_i();
		int val_j = copyObject.getSize_j();

		gameBoard = new int[val_i*val_j];
		cell_i = copyObject.cell_i;
		cell_j = copyObject.cell_j;
		set_totalMoves(copyObject.numberOfMoves());
		set_previous_move(copyObject.lastMove());

		for(int i = 0 ; i < val_i*val_j ; ++i)
			gameBoard[i] = copyObject.gameBoard[i];
	}

	BoardArray1D::~BoardArray1D() // destructor
	{
		delete[] gameBoard;
		gameBoard = NULL;
	}

	const BoardArray1D& BoardArray1D::operator =(const AbstractBoard& rightSide)
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

		else if(test1D != nullptr)
		{
			int val_i = test1D -> getSize_i();
			int val_j = test1D -> getSize_j();

			delete[] this -> gameBoard;

			this -> gameBoard = new int[val_i * val_j];

			for(int i = 0 ; i < val_i*val_j ; ++i)
				this -> gameBoard[i] = test1D -> gameBoard[i];

			this -> setSize(val_i, val_j);
			this -> cell_i = test1D -> cell_i;
			this -> cell_j = test1D -> cell_j;
			this -> set_previous_move(test1D -> lastMove());
			this -> set_totalMoves(test1D -> numberOfMoves());

			return (*this);
		}

		else if(testVector != nullptr)
		{
			int val_i = testVector -> getSize_i();
			int val_j = testVector -> getSize_j();

			delete[] this -> gameBoard;

			this -> gameBoard = new int[val_i * val_j];

			for(int i = 0 ; i < val_i ; ++i)
				for(int j = 0 ; j < val_j ; ++j)
					this -> gameBoard[(val_j*i) + j] = (*testVector)(i, j);

			this -> setSize(val_i, val_j);
			this -> cell_i = testVector -> get_emptyCell_i();
			this -> cell_j = testVector -> get_emptyCell_j();
			this -> set_previous_move(testVector -> lastMove());
			this -> set_totalMoves(testVector -> numberOfMoves());

			return (*this);
		}

		else if(test2D != nullptr)
		{
			int val_i = test2D -> getSize_i();
			int val_j = test2D -> getSize_j();

			delete[] this -> gameBoard;

			this -> gameBoard = new int[val_i * val_j];

			for(int i = 0 ; i < val_i ; ++i)
				for(int j = 0 ; j < val_j ; ++j)
					this -> gameBoard[(val_j*i) + j] = (*test2D)(i, j);

			this -> setSize(val_i, val_j);
			this -> cell_i = test2D -> get_emptyCell_i();
			this -> cell_j = test2D -> get_emptyCell_j();
			this -> set_previous_move(test2D -> lastMove());
			this -> set_totalMoves(test2D -> numberOfMoves());

			return (*this);
		}
	}

	int BoardArray1D::get_emptyCell_i() const 
	{
		return cell_i;
	}

	int BoardArray1D::get_emptyCell_j() const
	{
		return cell_j;
	}

	void BoardArray1D::generateMap()
	{
		int val_i = getSize_i();
		int val_j = getSize_j();
		int index = 0;

		int number = rand() % (val_i*val_j);

		gameBoard = new int[val_i*val_j];

		fill_and_init_gamemap(); // fill the board with -1 //

		for(int i = 0 ; i < val_i ; ++i)
		{
			for(int j = 0 ; j < val_j ; ++j)
			{
				while(testSame(number)) // Prevent to be same numbers on board //
					number = rand() % (val_i*val_j);

				gameBoard[index] = number;
				++index;
			}
		}

		set_emptyCellPosition();
	}

	void BoardArray1D::fill_and_init_gamemap()
	{
		for(int i = 0 ; i < getSize_i() * getSize_j() ; ++i)
			gameBoard[i] = -1;
	}

	bool BoardArray1D::testSame(int finder) const
	{
		for(int i = 0 ; i < getSize_i() * getSize_j() ; ++i)
			if(finder == gameBoard[i])
				return true;

		return false;
	}

	void BoardArray1D::set_emptyCellPosition()
	{
		int count = 0;

		for(int i = 0 ; i < getSize_i() ; ++i)
		{
			for(int j = 0 ; j < getSize_j() ; ++j)
			{
				if(gameBoard[count] == 0)
				{
					cell_i = i;
					cell_j = j;
				}

				++count;
			}
		}
	}

	int BoardArray1D::findMax() const
	{
		int index = 0;
		int max = 0;

		for(int i = 0 ; i < getSize_i() * getSize_j() ; ++i, ++index)
			if(max < gameBoard[index])
				max = gameBoard[index];

		return max;
	}

	void BoardArray1D::shuffle(int N)
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

		set_totalMoves(numberOfMoves() - N);
	}

	void BoardArray1D::reset()
	{
		int count = 1;
		int index = 0;

		int maxNumber = findMax();

		for(int i = 0 ; i < getSize_i()*getSize_j() ; ++i, ++index)
		{
			if(count <= maxNumber)
			{
				gameBoard[index] = count;
				++count;
			}

			else if(count == maxNumber+1)
			{
				gameBoard[index] = 0;
				set_emptyCellPosition();
				set_totalMoves(0);
				set_previous_move('S');
				return;
			}
		}
	}

	char BoardArray1D::moveRandom()
	{
		int random_move;

		int val_i = getSize_i();
		int val_j = getSize_j();

		while(true)
		{
			random_move = rand() % 4;

			if(random_move == 0 && cell_j != 0) // condition left //
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

	char BoardArray1D::move(char choice)
	{
		if(choice == 'L' || choice == 'l') // conditions left
		{
			int temp, i, j;
			int size__j = getSize_j();

			if(cell_j != 0)
			{
				if( gameBoard[(size__j*cell_i) + (cell_j-1)] != -1 )
				{
					i = cell_i;
					j = cell_j;
					temp = gameBoard[(size__j*cell_i) + (cell_j-1)];
					gameBoard[(size__j*i)+(j-1)] = 0;
					gameBoard[(size__j*i) + j] = temp;

					set_emptyCellPosition();
					set_previous_move('L');
					set_totalMoves(numberOfMoves() + 1);
					return 'L';
				}
			}
		}

		else if(choice == 'R' || choice == 'r') // conditions right 
		{
			int temp, i, j;
			int size__j = getSize_j();

			if( cell_j < (getSize_j()-1) )
			{
				if(gameBoard[(size__j*cell_i) + (cell_j+1)] != -1)
				{
					i = cell_i;
					j = cell_j;
					temp = gameBoard[(size__j*i) + (j+1)];
					gameBoard[(size__j*i) + (j+1)] = 0;
					gameBoard[(size__j*i) + j] = temp;

					set_emptyCellPosition();
					set_previous_move('R');
					set_totalMoves(numberOfMoves() + 1);
					return 'R';
				}
			}
		}

		else if(choice == 'U' || choice == 'u') // conditions up
		{
			int temp, i, j;
			int size__j = getSize_j();

			if(cell_i != 0)
			{
				if(gameBoard[(size__j*cell_i) + (size__j-cell_j)] != -1)
				{
					i = cell_i;
					j = cell_j;
					temp = gameBoard[(size__j*i) + (j-size__j)];
					gameBoard[(size__j*i) + (j-size__j)] = 0;
					gameBoard[(size__j*i) + j] = temp;

					set_emptyCellPosition();
					set_previous_move('U');
					set_totalMoves(numberOfMoves() + 1);
					return 'U';
				}
			}
		}

		else if(choice == 'D' || choice == 'd') // conditions down
		{
			int temp, i ,j;
			int size__j = getSize_j();

			if(cell_i < getSize_i()-1)
			{
				if(gameBoard[(size__j*cell_i)+1 + (size__j*cell_j)] != -1)
				{
					i = cell_i;
					j = cell_j;
					temp = gameBoard[(size__j*i) + (j+size__j)];
					gameBoard[(size__j*i) + (j+size__j)] = 0;
					gameBoard[(size__j*i) + j] = temp;

					set_emptyCellPosition();
					set_previous_move('D');
					set_totalMoves(numberOfMoves() + 1);
					return 'D';
				}
			}
		}
	}

	bool BoardArray1D::isSolved() const
	{
		BoardArray1D finalObject(this -> getSize_i(), this -> getSize_j());
		finalObject.reset();

		return ((*this) == finalObject);	
	}

	int BoardArray1D::operator()(int i, int j) const
	{
		return gameBoard[(getSize_j()*i) + j];
	}

	void BoardArray1D::readFromFile(const string& myFile)
	{
		int i = 0, j = 0;
		int space = 0, count = 0;
		char map_char[26];
		string line = "";

		if(getSize_i() != 0 && getSize_j() != 0)
			delete[] gameBoard;

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
			gameBoard = new int[getSize_i() * getSize_j()];

			for(int i = 0 ; i < getSize_i() ; ++i)
				for(int j = 0 ; j < getSize_j() ; ++j)
						gameBoard[(i*getSize_j()) + j] = -1;

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

	void BoardArray1D::char_to_int(char *map_char, int i, int j)
	{
		i = i * getSize_j();

		for(int index = 0 ; index < 26 ; ++index)
		{
			if(map_char[index] == ' ' && index < 25)
			{
				if(map_char[index-1] != 'b')
				{
					gameBoard[i + j] = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

					if(gameBoard[i + j] == 0)
						gameBoard[i + j] = -1;

					++j;
				}

				else
				{
					gameBoard[i + j] = 0;
					++j;
				}
			}

			else if(map_char[index] == '\0')
			{
				if(map_char[index-1] != 'b')
				{
					gameBoard[i + j] = static_cast<int> (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

					if(gameBoard[i + j] == 0)
						gameBoard[i + j] = -1;

					++j;
				}

				else
				{
					gameBoard[i + j] = 0;
					++j;
				}
				
				index = 99; // I dont want to use break so, i used index asaignmet 99 to break for loop //
			}

			else if(index == 25)
			{
				if(map_char[index-1] != 'b')
				{
					gameBoard[i + j] = static_cast<int> (10*(map_char[index-1] - 48)) + (map_char[index] - 48);

					if(gameBoard[i + j] == 0)
						gameBoard[i + j] = -1;
				}

				else
					gameBoard[i + j] = 0;
			}
		}
	}

	void BoardArray1D::play()
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