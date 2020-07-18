/*
	Muharrem Ozan Yesiller
		171044033
		Homework 5
*/
#include "AbstractBoard.h"

using namespace std;

namespace NPuzzle
{
	AbstractBoard::AbstractBoard()
	{
		cout << "You don't generate no parameter object" << endl;
		exit(1);
	}

	AbstractBoard::AbstractBoard(int val_i) 
					: size_i(val_i), size_j(3), totalMoves(0), previous_move('S')
	{
		if(val_i < 3)
		{
			cout << "This object invalid..." << endl;
			exit(1);
		}

		++objectNumber;
	}

	AbstractBoard::AbstractBoard(int val_i, int val_j)
					: size_i(val_i), size_j(val_j), totalMoves(0), previous_move('S')
	{
		if(val_i < 3 || val_j < 3)
		{
			cout << "This object invalid..." << endl;
			exit(1);
		}

		++objectNumber;
	}

	AbstractBoard::AbstractBoard(const string& fileName)
					: totalMoves(0), previous_move('S')
	{
		++objectNumber;
	}

	AbstractBoard::~AbstractBoard()
	{
		--objectNumber;
	}

	void AbstractBoard::set_totalMoves(int x)
	{
		totalMoves = x;
	}

	void AbstractBoard::set_previous_move(char x)
	{
		previous_move = x;
	}

	void AbstractBoard::setSize(int val_i, int val_j)
	{
		size_i = val_i;
		size_j = val_j;
	}

	int AbstractBoard::NumberOfBoards() const
	{
		return objectNumber;
	}

	char AbstractBoard::lastMove() const
	{
		return previous_move;
	}

	int AbstractBoard::numberOfMoves() const
	{
		return totalMoves;
	}

	int AbstractBoard::getSize_i() const
	{
		return size_i;
	}

	int AbstractBoard::getSize_j() const
	{
		return size_j;
	}

	void AbstractBoard::print() const
	{
		/*
			I used the () operator of inherited class
			Because the function is the same  all of them, if using () operator
		*/
		for(int i = 0 ; i < getSize_i() ; ++i)
		{
			for(int j = 0 ; j < getSize_j() ; ++j)
			{
				if((*this)(i, j) == -1)
				{
					cout << "00 ";
				}

				else if((*this)(i, j) == 0)
				{
					cout << "bb" << " ";
				}

				else
				{
					if((*this)(i, j) < 10 && (*this)(i, j) != 0)
					{
						cout << "0" << (*this)(i, j) << " ";
					}

					else
					{
						cout << (*this)(i, j) << " ";
					}
				}
			}

			cout << endl;
		}

		cout << endl;
	}

	void AbstractBoard::writeToFile(const string& newFile) const
	{
		/*
			I used the () operator of inherited class
			Because the function is the same  all of them, if using () operator
		*/
		ofstream myFile(newFile);

		if(myFile.is_open())
		{
			for(int i = 0 ; i < getSize_i() ; ++i)
			{
				for(int j = 0 ; j < getSize_j() ; ++j)
				{
					if((*this)(i, j) == 0)
						myFile << "bb";

					else if((*this)(i, j) == -1)
						myFile << "00";

					else
					{
						if((*this)(i, j) < 10 && (*this)(i, j) != 0)
							myFile << "0" << (*this)(i, j);

						else
							myFile << (*this)(i, j);
					}

					if(j < (getSize_j()-1))
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

	bool AbstractBoard::operator ==(const AbstractBoard& other) const
	{
		/*
			I used the () operator of inherited class
			Because the function is the same  all of them, if using () operator
		*/
		int size__i = other.getSize_i();
		int size__j = other.getSize_j();
		int count = 0;

		if(getSize_i() == other.getSize_i() && getSize_j() == other.getSize_j())
		{
			// if the sizes are equal //
			for(int i = 0 ; i < size__i ; ++i)
				for(int j = 0 ; j < size__j ; ++j)
					if(other(i,j) == (*this)(i, j))
						++count;
		}

		if(count == (size__i * size__j))
			return true;
		else 
			return false;
	}
}