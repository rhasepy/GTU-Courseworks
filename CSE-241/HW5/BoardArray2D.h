/*
	Muharrem Ozan Yesiller
		171044033
	CSE241 Homework 5
*/

#ifndef BoardArray2D_H_
#define BoardArray2D_H_

#include "AbstractBoard.h"

using namespace std;

namespace NPuzzle
{
	class BoardArray2D : public AbstractBoard
	{
		private:
			int **gameBoard;
			int cell_i;
			int cell_j;
			void generateMap(); // generate map 
			void fill_and_init_gamemap(); // fill with -1 
			bool testSame(int finder) const; // testing if the board has same number
			void set_emptyCellPosition(); // setting empty cell position
			int findMax() const; // find and return max number of board
			void shuffle(int N); // make N random move on board
			void char_to_int(char *map_char, int i, int j); // char to integer for the read file 

		public:
			BoardArray2D(); // no parameter constructor otomaticly generate 3,3
			BoardArray2D(int val_i); // one int parameter constructor otomaticly generate val_i,3
			BoardArray2D(int val_i, int val_j);// two int parameter consturctor otomaticly generate val_i,val_j
			BoardArray2D(const string& fileName);// read the file and generate
			BoardArray2D(const BoardArray2D& copyObject); // copy constructor
			~BoardArray2D(); // destructor
			const BoardArray2D& operator =(const AbstractBoard& rightSide); // assingment operator
			virtual void readFromFile(const string& myFile) override; // read file and generate board
			virtual void reset() override; // set the board to solution
			virtual char move(char choice) override; // move
			virtual bool isSolved() const override; // is Solved
			virtual int operator()(int i, int j) const override; // return (i,j) cell
			int get_emptyCell_i() const; // get empty celli
			int get_emptyCell_j() const; // get empty cellj
			virtual void play() override; // playing loop
			char moveRandom(); // move random
	};
}

#endif