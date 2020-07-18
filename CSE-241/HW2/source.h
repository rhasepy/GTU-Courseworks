#ifndef source
#define source

using namespace std;

void run(int argc, char *argv[]);
inline void fill_gamemap(int game_map[9][9]); // fill array with -1 // 
inline void fill_map_char(char map_char[26]); // fill char array with NULL //
void swap_array(int test_map[9][9], int game_map[9][9], int size_i, int size_j);
void generate_gamemap(int game_map[9][9], int size_i, int size_j); // fill array random numbers that is different from each other //
void print_map(const int game_map[9][9], int size_i, int size_j); // print filled the map //
void playgame(int game_map[9][9], int size_i, int size_j); // start game //
void findCellPosition(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j); // find the empty cell coordinate //
void moveLeft(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j); 
void moveRight(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j);
void moveUp(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j);
void moveDown(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j);
void shuffle(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j); // firstly make final map and do random moving untill size*size //
void makeFinalSolution(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j);	// make final map //
void Loadin_File(int game_map[9][9], int *size_i, int *size_j); // if the argument is given, i called
void Loadin_File(int game_map[9][9], int *size_i, int *size_j, string fileName); // if the argument is not given, i called
void char_to_int(int map[9][9], char map_char[26], int i, int j);//reads the file as a string and converts it to a integer
void Loadto_File(int game_map[9][9], int size_i, int size_j); // reads the file and fill board
void solvePuzzle(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j, int *totalMoves);
void randomMove(int game_map[9][9], int *cell_i, int *cell_j, int size_i, int size_j); // generate rand moves
int sort_cost(int cost[4]);	// Program calculate cost of possibilities and find minimum cost and return //
int isFinish(int game_map[81], int size_i, int size_j); // Check the game is finish or not //
int isNot_Solvable(int game_map[81], int size_i, int size_j);	// Check the game is solvable or not //
int testSame(int game_map[9][9], int finder, int size_i, int size_j);	// Check is the same number on board // 
int readUser_size();									// read size from user //
int heuristic_cost(int game_map[9][9], int test_map[9][9], int size_i, int size_j); // calculate the cost of possibilities //
int findMax(const int game_map[9][9], int size_i, int size_j);// find max size of array
char readUser_move(); 												  // read move from user for moving //
char intelligentMove(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j);
// I'm checking the previous move in the smart move //
//but the user's first smart move will not be the previous step, so I used overloading. //
char intelligentMove(int game_map[9][9], int size_i, int size_j, int *cell_i, int *cell_j, char *previous_move);
bool isLastMovesSame(char LastMoves[], int index); // testing if get stuck 


#endif