/*
	Muharrem Ozan Yesiller
		171044033
	CSE241 Homework 5
*/

#ifndef BoardArray1D_H_
#define BoardArray1D_H_

#include "AbstractBoard.h"

using namespace std;

namespace NPuzzle
{
	class BoardArray1D : public AbstractBoard
	{
		private:
			int *gameBoard;
			int cell_i;
			int cell_j;
			void generateMap(); // generate map 
			void fill_and_init_gamemap();   // fill with -1 
			bool testSame(int finder) const; // testing if the board has same number
			void set_emptyCellPosition(); // setting empty cell position
			int findMax() const; // find and return max number of board
			void shuffle(int N); // make N random move on board
			void char_to_int(char *map_char, int i, int j); // char to integer for the read file 

		public:
			BoardArray1D(); // Default generate 3 x 3 map //
			BoardArray1D(int val_i); // Default generate (val_i) x 3 map //
			BoardArray1D(int val_i, int val_j); // Generate (val_i) x (val_j) mal //
			BoardArray1D(const string& fileName);// read the file and generate
			BoardArray1D(const BoardArray1D& copyObject);// copy constructor
			~BoardArray1D();// destructor
			const BoardArray1D& operator =(const AbstractBoard& rightSide);// assingment operator
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