#include <stdio.h>
#include <stdlib.h>

#define DICT_SIZE 15
#define WORD_LEN 10
#define LINE_LEN 18

int get_line_size(char *line) {
	char *ch_iter = line; // so as not to lose beginning of line
	int counter = 0;
	// go until you see new line or null char
	while(*ch_iter != '\n' && *ch_iter != '\0') {
		ch_iter++; // next char
		counter++; // increment counter
	}
	return counter;
}

void copy_string(char *source, char *destination) {
	// get iterators over original pointers
	char *src_iter = source;
	char *dst_iter = destination;
	// until null char
	while (*src_iter != '\0') {
		// copy pointers
		*dst_iter = *src_iter;
		// advance to next char
		src_iter++;
		dst_iter++;
   }
   // terminate string
   *dst_iter = '\0';
}

void remove_newline(char *line) {
	char *ch_iter = line;
	// go until you see new line
	while(*ch_iter != '\n') {
		ch_iter++; // next char
	}
	*ch_iter = '\0'; // overwrite new line
}

void print_dictionary(char *dict[]) {
	int i;
	for(i = 0 ; i < DICT_SIZE ; i++) {
		printf("%s\n", dict[i]);
	}
}

void print_coord(int coord[DICT_SIZE][4]) {
	int i, j;
	for(i = 0 ; i < DICT_SIZE ; i++) {
		for(j = 0 ; j < 4 ; j++) {
			printf("%d ", coord[i][j]);
		}
		printf("\n");
	}
}
/*
	//////////////My functions is below//////////////
*/

char produceRandcar() // This function produce random character //
{
	char randChar;
	randChar = 97 + rand() % 26;
	return randChar;
}

int sizeofDic(int index,char *dict[]) // This function calculate size of word //
{
	int count = 0,j = 0;

	while(dict[index][j] != '\0')
	{
		++count;
		++j;
	}
	return (count-1);
}

int whichDirection(int i,int j,int x,int y) // This function understand and return be which direction of word //
{
	if((i-x) == 0)
	{
		if((j-y) < 0)
			return 1; //-> south
		else
			return 2; //-> north
	}
	
	else if((j-y) == 0)
	{
		if((i-x) < 0)
			return 3; //-> east
		else
			return 4; //-> west
	}

	else if((i-x) != 0 && (j-y) != 0)
	{
		if((i-x) > 0 && (j-y) < 0)
			return 5; //-> south-east
		else if((i-x) < 0 && (j-y) > 0)
			return 6; //-> north-west
		else if((i-x) > 0 && (j-y) > 0)
			return 7; //-> north east
		else if((i-x) < 0 && (j-y) < 0)
			return 8; //-> south- east
	}
}

int controlFunc(char word[15], char myMap[15][15], int row, int column) // This function control that entered word is the same as word that be on the map//
{
	int i,j,countBig,size = 0;
	int countSize = 0;
	int tempColumn,tempRow;

	while(word[size] != '\0')
	{
		++size;
	}

	tempColumn = column;
	tempRow = row;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempColumn;
		}
	}
	if(size == countSize) //East Control//
		return 1;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempColumn;
		}
	}
	if(size == countSize) //West Control//
		return 2;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempRow;
		}
	}
	if(size == countSize) //South Control//
		return 3;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempRow;
		}
	}
	if(size == countSize) //North Control//
		return 4;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempColumn;
			++tempRow;
		}
	}
	if(size == countSize) //South east//
		return 5;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempColumn;
			++tempRow;
		}
	}
	if(size == countSize) //South west//
		return 6;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempColumn;
			--tempRow;
		}
	}
	if(size == countSize) //North east//
		return 7;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = 0 ; countBig < size ; ++countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempColumn;
			--tempRow;
		}
	}
	if(size == countSize) //north west//
		return 8;

	if(size != countSize)
		return 0;
}

int isExit(char isExit[]) // if the user entered 'exit' or not //
{
	int i,count = 0;
	char exit[9] = {'e','x','i','t',' ','g','a','m','e'};

	for(i = 0 ; i < 9 ; ++i)
	{
		if(isExit[i] == exit[i])
			++count;
	}

	if(count == 4)
		return 1;
	else
		return 0;
}

void letterUp(char myMap[15][15], int row, int column, int direction, int size) // This function subserves that lowercase to be uppercase //
{
	int i;
	if(direction == 1)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
				myMap[row][column] = myMap[row][column] - 32;
			}
			++column;
		}
	}
	else if(direction == 2)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			 }
			 	--column;
		}
	}
	else if(direction == 3)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			}
			 	++row;
		}
	}
	else if(direction == 4)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			}
			 	--row;
		}
	}
	else if(direction == 5)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			}
			 	++column;
			 	++row;
		}
	}
	else if(direction == 6)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			}
			 	--column;
			 	++row;
		}
	}
	else if(direction == 7)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			}
			 	++column;
			 	--row;
		}
	}
	else if(direction == 8)
	{
		for(i = 0 ; i < size ; ++i)
		{
			if(myMap[row][column] < 65 || myMap[row][column] > 90)
			{
			 	myMap[row][column] = myMap[row][column] - 32;
			}
			 	--column;
			 	--row;
		}
	}
}

int reverse_controlFunc(char word[15], char myMap[15][15], int row, int column) // This function control that entered word is the same as REVERSE word that be on the map //
{
	int i,j,countBig,size = 0;
	int countSize = 0;
	int tempColumn,tempRow;

	while(word[size] != '\0')
	{
		++size;
	}

	tempColumn = column;
	tempRow = row;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempColumn;
		}
	}
	if(size == countSize) //East//
		return 1;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempColumn;
		}
	}
	if(size == countSize) //West//
		return 2;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempRow;
		}
	}
	if(size == countSize) //South//
		return 3;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempRow;
		}
	}
	if(size == countSize) //North//
		return 4;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempColumn;
			++tempRow;
		}
	}
	if(size == countSize) //South East//
		return 5;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempColumn;
			++tempRow;
		}
	}
	if(size == countSize) //South West//
		return 6;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			++tempColumn;
			--tempRow;
		}
	}
	if(size == countSize) //North West//
		return 7;

	tempColumn = column;
	tempRow = row;
	countSize = 0;
	for(countBig = size-1 ; countBig >= 0 ; --countBig)
	{
		if(tempRow < 15 && tempRow >= 0 && tempColumn < 15 && tempColumn >= 0) // To do not be overflow //
		{
			if(word[countBig] == myMap[tempRow][tempColumn] || word[countBig] == myMap[tempRow][tempColumn] + 32) //Second condition is for conflicting words//
			{
				++countSize;
			}
			--tempColumn;
			--tempRow;
		}
	}
	if(size == countSize) //North East//
		return 8;

	if(size != countSize)
		return 0;
}
 
void gameplay(char myMap[15][15])
{
	char word[15],isExitt[9];
	int row,column,i,j;
	int direction,reverse_direction;
	int size = 0;

	for(i = 0 ; i < 15 ; ++i)
	{
		word[i] = '\0';
	}

	do
	{
		for(i = 0 ; i < 15 ; ++i)
		{
			for(j = 0 ; j < 15 ; ++j)
			{
				printf("%c ",myMap[i][j]);
			}
			printf("\n");
		}

		printf("\nPlease enter the coordinate\n");
		
		printf("Row: ");
		scanf("%d",&row);
		
		printf("Column: ");
		scanf("%d",&column);
		
		printf("Word: ");
		scanf("%s",word);

		direction = controlFunc(word,myMap,row,column);
		reverse_direction = reverse_controlFunc(word,myMap,row,column);
		size = 0;

		if(direction == 0)
		{

			//Is the word reverse or not reverse//

			if(reverse_direction == 0)
			{
				printf("\nInvalid word\n");
				printf("Try again...\n");
			}
			else if(reverse_direction == 1)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);				
			}
			else if(reverse_direction == 2)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);
			}
			else if(reverse_direction == 3)
			{
    			while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);				
			}
			else if(reverse_direction == 4)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);
			}
			else if(reverse_direction == 5)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);
			}
			else if(reverse_direction == 6)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);
			}
			else if(reverse_direction == 7)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);
			}
			else if(reverse_direction == 8)
			{
				while(word[size] != '\0')
				{
					++size;
				}
				letterUp(myMap,row,column,reverse_direction,size);
			}
		}
			//If the not enter none of if statement, the entered word do not be reverse //

		else if(direction == 1)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 2)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 3)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 4)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 5)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 6)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 7)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		else if(direction == 8)
		{
			while(word[size] != '\0')
			{
				++size;
			}
			letterUp(myMap,row,column,direction,size);
		}
		printf("If you want to turn off program, you must typing 'exit'\n");
		printf("If you want continue, you must press any key...\n");
		scanf("%s",isExitt);
	}while(isExit(isExitt) == 0);
}

void fillMap(char *dict[], int coord[DICT_SIZE][4]) // This function fill the map with *dict[] and their coordinates//
{
	char myMap[15][15];
	int a,b,i,j,x,y;
	int direction,count;
	int index = 0;

	for(a = 0 ; a < DICT_SIZE ; ++a) // To fill the map //
	{
		for(b = 0 ; b < DICT_SIZE ; ++b)
		{
			myMap[a][b] = '\0';
		}
	}

	for(a = 0 ; a < DICT_SIZE ; ++a)
	{
		i = coord[a][0];
		j = coord[a][1];
		x = coord[a][2];
		y = coord[a][3];
		direction = whichDirection(i,j,x,y);

		if(direction == 1)
		{
			count = sizeofDic(index,dict);
			
			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				++j;
			}
		}
		else if(direction == 2)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				--j;
			}
		}
		else if(direction == 3)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				++i;
			}
		}
		else if(direction == 4)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				--i;
			}
		}
		else if(direction == 5)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				++j;
				--i;
			}
		}
		else if(direction == 6)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				--j;
				++i;
			}
		}
		else if(direction == 7)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				--i;
				--j;
			}
		}
		else if(direction == 8)
		{
			count = sizeofDic(index,dict);

			for(b = 0 ; b < count ; ++b)
			{
				myMap[i][j] = dict[a][b];
				++i;
				++j;
			}
		}
		++index;
	}
	
	for(i = 0 ; i < 15 ; ++i)
	{
		for(j = 0 ; j < 15 ; ++j)
		{
			if(myMap[i][j] == '\0')
			{
				myMap[i][j] = produceRandcar();
			}
		}
	}

	gameplay(myMap);
}

int main(){
	char *dict[DICT_SIZE];
    int coord[DICT_SIZE][4];    
    char line[LINE_LEN];
	FILE *fp = fopen("word_hunter.dat", "r");
	
	int line_counter = 0;
	int dict_counter = 0;
	while(fgets(line, LINE_LEN, fp) != NULL) {
		if(line_counter%5 == 0) {
			dict[dict_counter] = (char*) malloc(sizeof(char) * get_line_size(line));
			remove_newline(line);
			copy_string(line, dict[dict_counter]);
		} else if (line_counter%5 == 1){
			coord[dict_counter][0] = atoi(line);
		} else if (line_counter%5 == 2){			
			coord[dict_counter][1] = atoi(line);		
		} else if (line_counter%5 == 3){
			coord[dict_counter][2] = atoi(line);
		} else if (line_counter%5 == 4){
			coord[dict_counter][3] = atoi(line);
			dict_counter++;
		}
		line_counter++;
	}
	
	fclose(fp);
	fillMap(dict,coord);
	//print_dictionary(dict);
	//print_coord(coord);
	
	// WRITE HERE...
		
	return 0;
}
