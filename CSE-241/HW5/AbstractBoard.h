/*
	Muharrem Ozan Yesiller
		171044033
	CSE241 Homework 5
*/

#ifndef AbstractBoard_H_
#define AbstractBoard_H_

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <string>
#include <cstring>
#include <vector>

using namespace std;

namespace NPuzzle
{
	class AbstractBoard
	{
		private:
			static int objectNumber;
			int size_i;
			int size_j;
			int totalMoves;
			char previous_move;

		public:
			AbstractBoard(); // no parameter constructor will exit //
			AbstractBoard(int val_i); // one parameter constructor other is 3 default //
			AbstractBoard(int val_i, int val_j); // val_i = size_i, val_j = size_j//
			AbstractBoard(const string &fileName); // take file name and generate object //
			virtual ~AbstractBoard(); // destructor decrement number of objects //
			void print() const; // print the configuration of board //
			virtual void readFromFile(const string& myFile) = 0; // read file and generate new board/object //
			void writeToFile(const string& newFile) const; // write current configuration to text file //
			virtual void reset() = 0; // reset the board //
			void setSize(int val_i, int val_j); // set size_i and size_j //
			virtual char move(char choice) = 0; // move function //
			virtual bool isSolved() const = 0; // is Solved? //
			virtual int operator ()(int i, int j) const = 0; // (i, j) return value of object at the (i, j) //
			bool operator ==(const AbstractBoard& other) const; // 2 board is equal or not //
			int NumberOfBoards() const; // get number of boards //
			char lastMove() const; // get previous move //
			int numberOfMoves() const; // get number of total moves //
			void set_totalMoves(int x); // set total moves //
			void set_previous_move(char x); // set previous move //
		/*---------------------------------------------------------*/
			int getSize_i() const;
			int getSize_j() const;
			virtual void play() = 0; // playing loop //
	};
}

#endif