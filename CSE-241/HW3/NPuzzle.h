/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork3
*/
#ifndef NPuzzle_H_
#define NPuzzle_H_

using namespace std;

class NPuzzle
{
	private:

		class Board
		{
			public:
				void print() const; 
				void readFromFile(string myFile);
				void writeToFile(string newFile);
				void reset();
				void setSize(int x, int y); // set size i and size j //
				void move(char choice);
				bool isSolved();
			/*------------------------------------*/
				// My auxiliary methods //
				int getSize_i(); // get size of column //
				int getSize_j(); // get size of row //
				void set_emptyCellPosition(); // find the empty cell coordinate //
				int get_emptyCell_i(); // get empty cell column //
				int get_emptyCell_j(); // get empty cell row //
				void generateMap(); // generate map on starting if the file is not given //
				void init_TotalMoves(); // initiliaze total moves after start shuffling //
				void set_TotalMoves(int x); // if the cell move, increment total moves //
				int get_TotalMoves(); // get total moves //
				int heuristic_cost(); // calculate and return heuristic cost on board //
				void set_prev_move(char x); // set previous move //
				// I coding this method because prevent repeat on solving //
				char get_prev_move(); // get previous move //
				int findMax(int game_map[9][9], int size_i, int size_); // calculate and return max number of array //
				void fill_gamemap(); // fill board with -1 //
				bool testSame(int finder); // on the generating board, randoms is different each other //
				void char_to_int(char map_char[26], int i, int j); //reads the file as a string and converts it to a integer

			private:
				int size_i;
				int size_j;
				int cell_i;
				int cell_j;
				int totalMoves = 0;
				int gameBoard[9][9];
				char previous_move;
		};

		Board myBoard;

	public:
		void print() const;
		void printReport();
		void readFromFile(string myFile);
		void writeToFile(string newFile);
		void shuffle(int N);
		void reset();
		void setsize(int x, int y);
		char moveRandom();
		char moveIntelligent();
		void move(char choice);
		void solvePuzzle();
	/*----------------------------------*/
		// My auxiliary methods //
		NPuzzle(string myFile); // NPuzzle contructor when argument given by user 
		NPuzzle(int x, int y); // NPuzzle constructor when argument is not given 
		NPuzzle(); // Default contructor of NPuzzle 
		bool isSolved();
		int get_size_i(); // get size of column //
		int get_size_j(); // get size of row //
		bool isLastMovesSame(char LastMoves[], int index); // I coding this method because prevent repeat on solving //
};

#endif