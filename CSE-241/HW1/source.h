#ifndef source
#define source

void run();
void fill_gamemap(int game_map[9][9]); // fill array with -1 // 
void generate_gamemap(int game_map[9][9], int size); // fill array random numbers that is different from each other //
void print_map(int game_map[9][9], int size); // print filled the map //
void playgame(int game_map[9][9], int size); // start game //
void findCellPosition(int game_map[9][9], int size, int *cell_i, int *cell_j); // find the empty cell coordinate //
void moveLeft(int game_map[9][9], int size, int *cell_i, int *cell_j); 
void moveRight(int game_map[9][9], int size, int *cell_i, int *cell_j);
void moveUp(int game_map[9][9], int size, int *cell_i, int *cell_j);
void moveDown(int game_map[9][9], int size, int *cell_i, int *cell_j);
void shuffle(int game_map[9][9], int size, int *cell_i, int *cell_j); // firstly make final map and do random moving untill size*size //
void makeFinalSolution(int game_map[9][9], int size, int *cell_i, int *cell_j);	// make final map //
char intelligentMove(int game_map[9][9], int size, int *cell_i, int *cell_j);
int sort_cost(int cost[4]);	// Program calculate cost of possibilities and find minimum cost and return //
int isFinish(int game_map[81], int size); // Check the game is finish or not //
int isNot_Solvable(int game_map[81], int N);	// Check the game is solvable or not //
int testSame(int game_map[9][9], int finder, int size);	// Check is the same number on board // 
int readUser_size();									// read size from user //
int heuristic_cost(int game_map[9][9], int test_map[9][9], int size); // calculate the cost of possibilities //
char readUser_move(); 												  // read move from user for moving //
char intelligentMove(int game_map[9][9], int size, int *cell_i, int *cell_j);
// I'm checking the previous move in the smart move //
//but the user's first smart move will not be the previous step, so I used overloading. //
char intelligentMove(int game_map[9][9], int size, int *cell_i, int *cell_j, char *previous_move);

#endif