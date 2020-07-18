/*
	Muharrem Ozan Yeşiller
		  171044033
		  					*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void menu();
void draw_hourglass(int);
void draw_mountain_road(int,int);
void show_scores(int,int);
int playLuckyNumber();
int make_a_guess(int,int,int);
int readFromUser_mountain(int);
int readFromUser_hourglass();

int main()
{
	srand(time(NULL));
	menu();
}

void menu()
{
	int Choice,flag = 0;
	int score_human = 0,score_program = 0,isWin;

	do{
		if(flag == 0)
		{
			printf("\n");
			printf("%s","\e[5;31;47m ***** MENU ***** \e[0m\n");
			printf("1. Play Lucky Number\n");
			printf("2. Draw Hourglass\n");
			printf("3. Draw Mountain Road\n");
			printf("4. Exit\n");
			printf("Choice: ");

			scanf("%d",&Choice);
		}

		switch(Choice)
		{
			case 1:
				isWin = playLuckyNumber();
				if(isWin == 1)				// I store score data in menu //
					++score_human;
				else
					++score_program;
				
				show_scores(score_human,score_program); // Print the scores //
				flag = 0;
				break;

			case 2:			// If the choice is 2 program will execute draw hourglass function //
				draw_hourglass(readFromUser_hourglass());
				flag = 0;
				break;

			case 3:
				draw_mountain_road(readFromUser_mountain('N'),readFromUser_mountain('R')); // N -> for length // R -> for radius //
				flag = 0;
				break;

			case 4:												// If the choice is 4, Program will close //
				printf("The program is shutting down...\n");	
				flag = 0;
				break;

			default:									// Entered number is not between [1-4] //
				while(Choice > 4 || Choice < 1)
				{
					printf("This is an invalid choice. Try again!\n");
					scanf("%d",&Choice);
				}
				flag = 1;	// I don't print the menu again at the user is entered number between [1-4] after user entered wrong number at first //
				break;
		}
	}while(Choice != 4); // Loop returns untill user enter '4' //
}

int make_a_guess(int trial, int min, int max) // User guesses for the lucky number game //
{
	int guessValue;

	printf("(Trial: %d) Make a guess between %d and %d: ",trial,min,max);
	scanf("%d",&guessValue);
	return guessValue;
}

void show_scores(int score_human, int score_program) // Show scores program and user //
{
	printf("Your Score: %d Program Score: %d\n",score_human,score_program);
}

int playLuckyNumber() // My auxiliary function for playing lucky number //
{
	int trial,showTrial,min,max,i;
	int luckyNumber,distance,guessNumber;

	luckyNumber = 1 + (rand() % 1024);
	trial = 10;
	min = 1;
	max = 1024;
	showTrial = 1;

	printf("You have %d trial in total\n",trial);
	
	for(i = 0 ; i < trial ; ++i)
	{
		guessNumber = make_a_guess(showTrial,min,max);
		distance = abs(luckyNumber - guessNumber);  // I used to understand absolute value //
		++showTrial;

		if(distance >= 512)
			printf("Distance is 10\n\n");
		else if(distance >= 256 && distance < 512)
			printf("Distance is 9\n\n");
		else if(distance >= 128 && distance < 256)
			printf("Distance is 8\n\n");
		else if(distance >= 64 && distance < 128)
			printf("Distance is 7\n\n");
		else if(distance >= 32 && distance < 64)
			printf("Distance is 6\n\n");
		else if(distance >= 16 && distance < 32)
			printf("Distance is 5\n\n");
		else if(distance >= 8 && distance < 16)
			printf("Distance is 4\n\n");
		else if(distance >= 4 && distance < 8)
			printf("Distance is 3\n\n");
		else if(distance >= 2 && distance < 4)
			printf("Distance is 2\n\n");
		else if(distance == 1)
			printf("Distance is 1\n\n");
		else if(distance == 0)
		{
			printf("Distance: 0. You win!\n");
			i = trial; 	// For the break loop //
		}
		
		if(guessNumber > luckyNumber)
			max = guessNumber;
		else if(guessNumber < luckyNumber)
			min = guessNumber;

		if(guessNumber < 1 || guessNumber > 1024) // If the user enter the number out of interval //
		{
			max = 1024;
			min = 1;
		}

		if(i == (trial-1))
			printf("Run out the your trial...\nProgram win!\n"); // If the user don't have trial //
	}

	if(distance == 0)
		return 1; // If the human win, Program will increment human's score in menu //
	else 
		return 0; // If the program win, Program will increment score's score in menu //
}

void draw_hourglass(int height)
{
	int tempHeight;
	int i,j,countBig,whiteSpace = 1;

	tempHeight = height;

	for(countBig = 0 ; countBig < (tempHeight/2) ; ++countBig)
	{
		for(i = 0 ; i < height ; ++i)
		{
			printf("*");
		}
		
		printf("\n");

		for(j = 0 ; j < whiteSpace ; ++j)
		{
			printf(" ");
		}
		++whiteSpace;
		height -= 2;
	}
		whiteSpace -= 2;

	for(countBig = 0 ; countBig < (tempHeight/2)+1 ; ++countBig)
	{
		for(i = 0 ; i < height ; ++i)
		{
			printf("*");
		}

		printf("\n");

		for(j = 0 ; j < whiteSpace ; ++j)
		{
			printf(" ");
		}

		height += 2;
		--whiteSpace;
	}
}

int readFromUser_hourglass() // My auxuiliary function for read number that user will enter //
{
	int heightValue,flag = 0;

	do
	{
		printf("Please, Enter the height\n");
		printf("You have to use positive odd number\n");
		printf("Height: ");

		scanf("%d",&heightValue);

		if(heightValue % 2 != 0 && heightValue > 0)
		{
			flag = 1;
		}

	}while(flag == 0);

	printf("\n");

	return heightValue;
}

void draw_mountain_road(int length, int max_radius)
{
	int countBig,i,j;
	int randomRadius,spaceSize,isLengthOdd;
	char myBackslash = '\\';

	spaceSize = max_radius;
	isLengthOdd = length;

	if(length % 2 == 0) // For the length is even number //
	{
		for(countBig = 0 ; countBig < length/2 ; ++ countBig)
		{
			randomRadius = rand() % max_radius + 1;
			for(i = 0 ; i < randomRadius ; ++i)
			{
				for(j = 0 ; j < spaceSize ; ++j)	// Put white space as much as max radius for NOT BE OVERFLOW //
				{
					printf(" ");
				}
				printf("/\n");
				--spaceSize;
			}

			for(i = 0 ; i < spaceSize ; ++i)
			{
				printf(" ");
			}
			printf("|\n");
			++spaceSize;

			for(i = 0 ; i < randomRadius ; ++i)
			{
				for(j = 0 ; j < spaceSize ; ++j)
				{
					printf(" ");
				}
				printf("%c\n",myBackslash);
				++spaceSize;
			}

			randomRadius = rand() % max_radius + 1;
			for(i = 0 ; i < randomRadius ; ++i)
			{
				for(j = 0 ; j < spaceSize ; ++j)
				{
					printf(" ");
				}
				printf("%c\n",myBackslash);
				++spaceSize;
			}

			for(i = 0 ; i < spaceSize ; ++i)
			{
				printf(" ");
			}
			printf("|\n");
			--spaceSize;

			for(i = 0 ; i < randomRadius ; ++i)
			{
				for(j = 0 ; j < spaceSize ; ++j)
				{
					printf(" ");
				}
				printf("/\n");
				--spaceSize;
			}
		}
	}
	
	if(length % 2 != 0) // For the length is odd number //
	{
		for(countBig = 0 ; countBig < (length/2)+1 ; ++ countBig)
		{
			randomRadius = rand() % max_radius + 1;
			for(i = 0 ; i < randomRadius ; ++i)
			{
				for(j = 0 ; j < spaceSize ; ++j)	// Put white space as much as max radius for NOT BE OVERFLOW //
				{
					printf(" ");
				}
				printf("/\n");
				--spaceSize;
			}

			for(i = 0 ; i < spaceSize ; ++i)
			{
				printf(" ");
			}
			printf("|\n");
			++spaceSize;

			for(i = 0 ; i < randomRadius ; ++i)
			{
				for(j = 0 ; j < spaceSize ; ++j)
				{
					printf(" ");
				}
				printf("%c\n",myBackslash);
				++spaceSize;
			}
			
			if((isLengthOdd-1) % 2 == 0 && isLengthOdd > 1) // Print the second mountain hill, if the surviving length is even number //
			{	
				isLengthOdd -= 2;
				randomRadius = rand() % max_radius + 1;
				for(i = 0 ; i < randomRadius ; ++i)
				{
					for(j = 0 ; j < spaceSize ; ++j)
					{
						printf(" ");
					}
					printf("%c\n",myBackslash);
					++spaceSize;
				}

				for(i = 0 ; i < spaceSize ; ++i)
				{
					printf(" ");
				}
				printf("|\n");
				--spaceSize;

				for(i = 0 ; i < randomRadius ; ++i)
				{
					for(j = 0 ; j < spaceSize ; ++j)
					{
						printf(" ");
					}
					printf("/\n");
					--spaceSize;
				}
			}
		}
	}
}

int readFromUser_mountain(int n) // My auxuiliary function for the read from user length and radius //
{
	int flag = 0,result;

	while(flag == 0)
	{
		switch(n)
		{
			case 'N':
				printf("Please, Enter the length of mountain\n");
				scanf("%d",&result);
				break;

			case 'R':
				printf("Please, Enter the max radius of mountain\n");
				scanf("%d",&result);
				break;
		}

		if(result < 0)
		{
			printf("This is an invalid choice. Try again!\n");
		}

		else
		{
			flag = 1;
		}
	}

	return result;
}