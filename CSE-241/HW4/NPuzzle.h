/*
	Muharrem Ozan Yeşiller
		171044033 
	  CSE241 HomeWork4
*/
#ifndef NPuzzle_H_
#define NPuzzle_H_

using namespace std;

class Board;

class NPuzzle
{
	private:

		class Board
		{
			public:
				void print() const; 
				void readFromFile(string myFile);
				void writeToFile(string newFile) const;
				void reset();
				void setSize(int x, int y); // set size i and size j //
				char move(char choice);
				bool isSolved() const;
				int operator ()(int i, int j) const;
				bool operator ==(const Board& object1);
				int numberOfBoards() const;
				int numberOfMoves() const; // get total moves //
				char lastMove() const; // get previous move //
				Board();
			/*------------------------------------*/
				// My auxiliary methods//
				int getSize_i() const; // get size of column //
				int getSize_j() const; // get size of row //
				void set_emptyCellPosition(); // find the empty cell coordinate //
				int get_emptyCell_i() const; // get empty cell column //
				int get_emptyCell_j() const; // get empty cell row //
				void generateMap(); // generate map on starting if the file is not given //
				void init_TotalMoves(); // initiliaze total moves after start shuffling //
				void set_TotalMoves(int x); // if the cell move, increment total moves //
				void set_prev_move(char x); // set previous move //
				// I coding this method because prevent repeat on solving //
				inline void fill_and_init_gamemap(); // fill board with -1 //
				void char_to_int(char map_char[26], int i, int j); //reads the file as a string and converts it to a integer
				int findMax(const vector< vector <int> > game_map, int size_i, int size_) const; // calculate and return max number of array //
				bool testSame(int finder)const; // on the generating board, randoms is different each other //

			private:
				static int objectCount;
				int size_i;
				int size_j;
				int cell_i;
				int cell_j;
				int totalMoves;
				char previous_move;
				vector < vector<int> > gameBoard;
		};

		vector <Board> myBoard;

	public:
		void print() const;
		void printReport() const;
		void readFromFile(string myFile);
		void writeToFile(string newFile) const;
		void shuffle(int N);
		void reset();
		void setsize(int x, int y);
		char moveRandom();
		char move(char choice);
		void solvePuzzle();
		friend ostream& operator <<(ostream &outputStream, const NPuzzle& object);
		friend istream& operator >>(istream &inputStream, NPuzzle& object);
	/*----------------------------------*/
		// My auxiliary methods (externally accessible)//
		NPuzzle(string myFile); // NPuzzle contructor when argument given by user 
		NPuzzle(int val_i, int val_j); // NPuzzle constructor when argument is not given 
		NPuzzle(); // Default contructor of NPuzzle 
		bool isSolved() const;
		int get_size_i() const; // get size of column //
		int get_size_j() const; // get size of row //
		int input_size_i();
		int input_size_j();
		void setTotalMoves(int x);
		bool isTheSameVector(const Board& object);
		void reverse_and_print(int index);
		int whichVector(const Board& object);
};

#endif