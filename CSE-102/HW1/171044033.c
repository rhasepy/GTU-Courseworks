#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define PI 3
#define Red 0
#define	Yellow 1
#define Blue 2
#define Black 3
#define White 4
#define Square 1
#define Rectangular 2
#define Circular 3
#define colorRed "\033[22;31m"
#define colorYellow "\033[01;33m"
#define colorBlue "\x1b[34m"
#define colorBlack "\x1b[35m"
#define colorReset "\x1b[0m"

double CreateBody(int);
int SetColor(int);
double LoadMoves(int,double);
int SetAttackPower(int,int);
void ShowPokemon(int,double,int,double,int);
void drawRectangular(double,int);
void drawCircular(double,int);
void drawSquare(double,int);

int main()
{
	srand(time(NULL)); // I added the code in main because rand executing compile time but srand executing run time //
	int shape, color, attack_power;
	double size_of_body, move_length;

	shape = 3; // 1-> Square // 2-> Rectangular // 3-> Circular //
	size_of_body = CreateBody(shape);

	color = 798; // Red -> 0 // Yellow -> 1 // Blue -> 2 // Black -> 3 // White -> 4 //
	color = SetColor(color);
	
	move_length = LoadMoves(shape, size_of_body);

	attack_power = SetAttackPower (0, 150);
	
	ShowPokemon(shape, size_of_body, color, move_length, attack_power);
}

double CreateBody(int shape)
{
	double edge,edge2,radius;

	if(shape == 1) // If the shape is a sqaure //
	{
		printf("Please, Enter the length of the edge of the square\n");	
		scanf("%lf",&edge);
		while(edge <= 0)
		{
			printf("Please Enter the positive number\n");
			scanf("%lf",&edge);
		}
		return (edge*edge);
	}

	if(shape == 2) // If the shape is a rectangular //
	{
		printf("Please, Enter the two edge length of the rectangular\n");
		scanf("%lf %lf",&edge,&edge2);
		while(edge <= 0 || edge2 <= 0)
		{
			printf("Please! Enter the positive numbers\n");
			scanf("%lf %lf",&edge,&edge2);
		}
		return (edge2*edge);
	}

	if(shape == 3) // If the shape is a circular //
	{
		printf("Please, Enter the length of the radius of the circular\n");
		scanf("%lf",&radius);
		while(edge <= 0)
		{
			printf("Please enter the positive number\n");
			scanf("%lf",&edge);
		}
		return (PI*radius*radius);
	}
}

int SetColor(int color)
{
	if(color <= 1000 || color >= 0)
		return (color%5);

	else
		return 1;
}

double LoadMoves(int shape, double size_of_body)
{
	double lengthValue;
	double tempValue;

	if(shape == 1)
	{
		lengthValue = sqrt(size_of_body);
		return (4*lengthValue);
	}

	if(shape == 2)
	{
		lengthValue = (size_of_body / 5);
		return((2*lengthValue) + 10);
	}

	if(shape == 3)
	{
		tempValue = size_of_body / PI;
		lengthValue = sqrt(tempValue);
		return (2*PI*lengthValue);
	}
}

int SetAttackPower(int lower_bound, int upper_bound) 
{
	int random;

	random = lower_bound + rand() % (upper_bound - lower_bound);

	return random;
}

void ShowPokemon(int shape, double body_size, int color, double move_length, int attack_power)
{
	if(shape == 1) // Square //
	{
		drawSquare(body_size,color);
		printf("\n\n");
		printf("Name: Sqaure Pokémon\n");
		printf("Size: %.2lf\n",body_size);
		if(color == 0)
		{
			printf("Color: Red\n");
		}

		if(color == 1)
		{
			printf("Color: Yellow\n");
		}

		if(color == 2)
		{
			printf("Color: Blue\n");
		}

		if(color == 3)
		{
			printf("Color: Black\n");
		}

		if(color == 4)
		{
			printf("Color: White\n");
		}
		printf("Move: %.2lf\n",move_length);
		printf("Attack Power: %d\n",attack_power);

	}

	if(shape == 2)	// Rectangular //
	{
		drawRectangular(body_size,color);
		printf("\n\n");
		printf("Name: Rectangular Pokémon\n");
		printf("Size: %.2lf\n",body_size);
		if(color == 0)
		{
			printf("Color: Red\n");
		}

		if(color == 1)
		{
			printf("Color: Yellow\n");
		}

		if(color == 2)
		{
			printf("Color: Blue\n");
		}

		if(color == 3)
		{
			printf("Color: Black\n");
		}

		if(color == 4)
		{
			printf("Color: White\n");
		}
		printf("Move: %.2lf\n",move_length);
		printf("Attack Power: %d\n",attack_power);
	}

	if(shape == 3) // Circular //
	{
		drawCircular(body_size,color);
		printf("\n\n");
		printf("Name: Circular Pokémon\n");
		printf("Size: %.2lf\n",body_size);
		if(color == 0)
		{
			printf("Color: Red\n");
		}

		if(color == 1)
		{
			printf("Color: Yellow\n");
		}

		if(color == 2)
		{
			printf("Color: Blue\n");
		}

		if(color == 3)
		{
			printf("Color: Black\n");
		}

		if(color == 4)
		{
			printf("Color: White\n");
		}
		printf("Move: %.2lf\n",move_length);
		printf("Attack Power: %d\n",attack_power);
	}
}

void drawSquare(double size_of_body, int color)		// Why second parameter is color? Because ı understand pokemon's color //
{																	// I drew colored pokemon with color code //
	int edgeOfsquare,count,count2;

	edgeOfsquare = sqrt(size_of_body);

	if(color == 0) // For the red square pokemon //
	{	
		for(count=0;count<edgeOfsquare;++count)
		{
			for(count2=0;count2<edgeOfsquare;++count2)
			{
				printf(colorRed "X " colorReset);
			}
			printf("\n");
		}
	}

	if(color == 1) // For the yellow square pokemon //
	{
		for(count=0;count<edgeOfsquare;++count)
		{
			for(count2=0;count2<edgeOfsquare;++count2)
			{
				printf(colorYellow "X " colorReset);
			}
			printf("\n");
		}		
	}

	if (color == 2) // For the blue square pokemon //
	{
		for(count=0;count<edgeOfsquare;++count)
		{
			for(count2=0;count2<edgeOfsquare;++count2)
			{
				printf(colorBlue "X " colorReset);
			}
			printf("\n");
		}
	}

	if(color == 3) // For the black square pokemon //
	{
		for(count=0;count<edgeOfsquare;++count)
		{
			for(count2=0;count2<edgeOfsquare;++count2)
			{
				printf(colorBlack "X " colorReset);
			}
			printf("\n");
		}		
	}

	if(color == 4) // For the white square pokemon //
	{
		for(count=0;count<edgeOfsquare;++count)
		{
			for(count2=0;count2<edgeOfsquare;++count2)
			{
				printf("X ");
			}
			printf("\n");
		}		
	}
}

void drawRectangular(double size_of_body, int color) // Why second parameter is color? Because ı understand pokemon's color // 
{																	// I drew colored pokemon with color code //							
	int LongerEdge, count1, count2;

	LongerEdge = (size_of_body / 5); // Why "/5"? Because, We assume that one of rectangular's edges is five //

	if(color == 0) // For the red rectangular pokemon //
	{
		for(count1=0;count1<LongerEdge;++count1)
		{
			for(count2=0;count2<5;++count2)
			{
				printf(colorRed "X " colorReset);
			}
			printf("\n");
		}
	}

	if(color == 1) // For the yellow rectangular pokemon //
	{
		for(count1=0;count1<LongerEdge;++count1)
		{
			for(count2=0;count2<5;++count2)
			{
				printf(colorYellow "X " colorReset);
			}
			printf("\n");
		}
	}

	if(color == 2) // For the blue rectangular pokemon //
	{
		for(count1=0;count1<LongerEdge;++count1)
		{
			for(count2=0;count2<5;++count2)
			{
				printf(colorBlue "X " colorReset);
			}
			printf("\n");
		}
	}

	if(color == 3) // For the black rectangular pokemon //
	{
		for(count1=0;count1<LongerEdge;++count1)
		{
			for(count2=0;count2<5;++count2)
			{
				printf(colorBlack "X " colorReset);
			}
			printf("\n");
		}		
	}

	if(color == 4) // For the white rectangular pokemon //
	{
		for(count1=0;count1<LongerEdge;++count1)
		{
			for(count2=0;count2<5;++count2)
			{
				printf("X ");
			}
			printf("\n");
		}		
	}
}

void drawCircular(double size_of_body, int color)	// Why second parameter is color? Because ı understand pokemon's color // 
{																	// I drew colored pokemon with color code //
	int radius;
	radius = sqrt(size_of_body / PI);
	int i,j;
	int countBig;
	int tabSize,tabSize2;
	int column=radius/2;

	tabSize = (radius-2)/2;

	if(radius%2==0 && radius!=0) // If the radius is even number //
	{
		tabSize2 = 1;
		if(color == 0) // Start for the red circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)	// The first loop puts "X" middle according to radius //
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorRed "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)	// The second loop puts tab and X and again tab and X //
			{
				for(i=0;i<tabSize;++i)					// Put tab //
				{
					printf("\t");
				}
					printf(colorRed "X" colorReset);	// Put X //
				for(j=0;j<tabSize2;++j)					// Put again tab //
				{
					printf("\t");
				}
				printf(colorRed "X" colorReset);		// Put again X //
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			} 
				tabSize += 2;
				tabSize2 -= 4;
			for(countBig=0;countBig<column-1;++countBig) // The third loop executing reverse second loop //
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorRed "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorRed "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}

			for(countBig=0;countBig<1;++countBig) // The last loop is the same first loop //
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorRed "X\n" colorReset);
			}
		}													// Finish for the red circular pokemon //

		if(color == 1) // Start for the yellow circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)	
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorYellow "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)		
			{
				for(i=0;i<tabSize;++i)						
				{
					printf("\t");
				}
					printf(colorYellow "X" colorReset);		
				for(j=0;j<tabSize2;++j)						
				{
					printf("\t");
				}
				printf(colorYellow "X" colorReset);			
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			} 
				tabSize += 2;
				tabSize2 -= 4;
			for(countBig=0;countBig<column-1;++countBig)				
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorYellow "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorYellow "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}

			for(countBig=0;countBig<1;++countBig)		 // The last loop is the same first loop //
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorYellow "X\n" colorReset);
			}
		}													// Finish for the yellow circular pokemon //

		if(color == 2) // Start for the blue circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)			
			{
				for(i=1;i<radius/2;++i)	
				{
				printf("\t");				
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorBlue "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)		
			{
				for(i=0;i<tabSize;++i)						
				{
				printf("\t");
				}
				printf(colorBlue "X" colorReset);			
				for(j=0;j<tabSize2;++j)						
				{
					printf("\t");
				}
				printf(colorBlue "X" colorReset);			
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			} 
				tabSize += 2;
				tabSize2 -= 4;
			for(countBig=0;countBig<column-1;++countBig)	
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlue "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlue "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}

			for(countBig=0;countBig<1;++countBig)		
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorBlue "X\n" colorReset);
			}
		}													// Finish for the blue circular pokemon //

		if(color == 3) // Start for the black circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorBlack "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlack "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlack "X" colorReset);
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			} 
				tabSize += 2;
				tabSize2 -= 4;
			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlack "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlack "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}

			for(countBig=0;countBig<1;++countBig)	
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf(colorBlack "X\n" colorReset);
			}
		}												// Finish for the black circular pokemon //

		if(color == 4) // For the white circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=1;i<radius/2;++i)
				{
				printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf("X\n");
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
				printf("\t");
				}
				printf("X");
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf("X");
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			} 
				tabSize += 2;
				tabSize2 -= 4;
			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf("X");
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf("X");
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}

			for(countBig=0;countBig<1;++countBig)	
			{
				for(i=1;i<radius/2;++i)
				{
					printf("\t");
				}
				for(j=0;j<4;++j)
				{
					printf(" ");
				}
				printf("X\n");
			}
		}													// Finish for the white circular pokemon //
	}														
	
	else	// If the radius is odd number //
	{
		tabSize2=2;
		tabSize = (radius-2)/2;
		column=radius/2;


		if(color == 0)  // Start for the red circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=0;i<radius/2;++i)
				{
					printf("\t");
				}
				printf(colorRed "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorRed "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorRed "X" colorReset);
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			}	 

			tabSize += 2;
			tabSize2 -= 4;

			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorRed "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorRed "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}	 
			if(radius != 1){
				for(countBig=0;countBig<1;++countBig)
				{
					for(i=0;i<radius/2;++i)
					{
						printf("\t");
					}
					printf(colorRed "X\n" colorReset);
				}
			}
		}											// Finish for the red circular pokemon //

		if(color == 1) // Start for the yellow circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=0;i<radius/2;++i)
				{
					printf("\t");
				}
				printf(colorYellow "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorYellow "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorYellow "X" colorReset);
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			}	 

			tabSize += 2;
			tabSize2 -= 4;

			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorYellow "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorYellow "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}	 
			if(radius != 1)
			{
				for(countBig=0;countBig<1;++countBig)
				{
					for(i=0;i<radius/2;++i)
					{
						printf("\t");
					}
					printf(colorYellow "X\n" colorReset);
				}
			}
		}												// Finish for the yellow circular pokemon //

		if(color == 2) // Start for the blue circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=0;i<radius/2;++i)
				{
					printf("\t");
				}
				printf(colorBlue "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlue "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlue "X" colorReset);
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			}	 

			tabSize += 2;
			tabSize2 -= 4;

			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlue "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlue "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}	 
			if(radius != 1){
				for(countBig=0;countBig<1;++countBig)
				{
					for(i=0;i<radius/2;++i)
					{
						printf("\t");
					}
					printf(colorBlue "X\n" colorReset);
				}
			}
		}												// Finish for the blue circular pokemon //

		if(color == 3) // Start for the black circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=0;i<radius/2;++i)
				{
					printf("\t");
				}
				printf(colorBlack "X\n" colorReset);
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlack "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlack "X" colorReset);
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			}	 

			tabSize += 2;
			tabSize2 -= 4;

			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf(colorBlack "X" colorReset);
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf(colorBlack "X" colorReset);
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}	 
			if(radius != 1)
			{
				for(countBig=0;countBig<1;++countBig)
				{
					for(i=0;i<radius/2;++i)
					{
						printf("\t");
					}
					printf(colorBlack "X\n" colorReset);
				}
			}
		}												// Finish for the black circular pokemon //

		if(color == 4) // Start for the white circular pokemon //
		{
			for(countBig=0;countBig<1;++countBig)
			{
				for(i=0;i<radius/2;++i)
				{
					printf("\t");
				}
				printf("X\n");
			}

			for(countBig=0;countBig<column;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf("X");
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf("X");
				printf("\n");
				tabSize2 += 2; 
				tabSize -= 1;
			}	 

			tabSize += 2;
			tabSize2 -= 4;

			for(countBig=0;countBig<column-1;++countBig)
			{
				for(i=0;i<tabSize;++i)
				{
					printf("\t");
				}
					printf("X");
				for(j=0;j<tabSize2;++j)
				{
					printf("\t");
				}
				printf("X");
				printf("\n");
				tabSize2 -= 2; 
				tabSize += 1;
			}	 
			if(radius != 1)
			{
				for(countBig=0;countBig<1;++countBig)
				{
					for(i=0;i<radius/2;++i)
					{
						printf("\t");
					}
					printf("X\n");
				}
			}
		}
	}								// Finish for the white circular pokemon //
}