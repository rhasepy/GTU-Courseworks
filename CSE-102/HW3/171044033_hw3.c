/*
	Hocam part 1 i pdfteki output gibi boşluklu yaptım, enter enter yapmadım
	Muharrem Ozan Yeşiller 
		   171044033
*/
#include <stdio.h>

double calculate_homework(int[]);
double calculate_lab(int[]);
double calculate_all(int,int[],int);
void drawQuatrangle();
void menu();
void calculatorMenu();				
void gradesCalculator();
int doit(int operatorFunc(int, int), int first_number, int second_number);	
int take_grades(int[]); 			
int take_exam_grades(int[]);		
int add(int,int);
int sub(int,int);
int mult(int,int);
int div(int,int);
int power(int,int);
int mod(int,int);

int main()
{
	menu();
}

void menu() // General menu function //
{
	int choice,flag = 0;
	do
	{
		if(flag == 0)
		{
			printf("\n\e[5;31;47m ###MENU### \e[0m\n");
			printf("1. Calculator\n");
			printf("2. Calculate Grades\n");
			printf("3. Draw Quatrangle\n");
			printf("4. Exit\n");
			printf("Choice: ");
			scanf("%d",&choice);
		}

		switch(choice)
		{
			case 1:
				calculatorMenu();
				flag = 0;
				break;

			case 2:
				gradesCalculator();
				flag = 0;
				break;

			case 3:
				drawQuatrangle();
				flag = 0;
				break;

			case 4:
				flag = 0;
				printf("The program is shutting down...\n");
				break;

			default:							// If the user exclude enter number that is between [1-4] //
				while(choice > 4 || choice < 1)
				{
					printf("This is an invalid choice. Try again!\n");
					scanf("%d",&choice);
				}
				flag = 1;
				break;			
		}
	}while(choice != 4); // If the user enter '4', Program will be turn off //
}

void calculatorMenu() // My auxiliary function for calculator menu //
{
	int value1,value2,result = 0,flag = 0;
	char op,test,test2;

	printf("\nWelcome to my calculator\n");
	printf("If you want to turn off calcultor, you should press 'p' or 'P'\n"); // If the user enter 'p' or 'P', calculator will turn off //

	while(flag == 0)
	{
		scanf(" %c%c",&op,&test); //test-> Program testing test is whitespace or * // If the test is '*' , program will execute power function//
		if(op == 'p' || op == 'P')
		{
			flag = 1;
			test2 = 0; // Because test2 include 'space' and enter loop at 103.line //
		}
		if(flag == 0)
		{
			scanf("%d",&value1); // Program understand first number//
			scanf("%c",&test2); // If the test2 is whitespace, so there is a second number //
		}

		if(test2 == 32)
		{
			scanf("%d",&value2); // If the test2 is whitespace, value2 scanning second number //
			if(test == '*') // Opetor power //
			{
				result = doit(power,value1,value2); //At the 90. line// test2 include '*' operator include '*' // ** -> Power //
			}
			else
			{
				if(op == '+')	// If the operator '+', calculator will execute adding //
				{
					result = doit(add,value1,value2);
				}
				else if(op == '-')	// If the operator '+', calculator will execute subtraction //
				{
					result = doit(sub,value1,value2);	
				}
				else if(op == '*')	// If the operator '+', calculator will execute multiply //
				{
					result = doit(mult,value1,value2);
				}
				else if(op == '/')	// If the operator '+', calculator will execute division //
				{
					result = doit(div,value1,value2);
				}
				else if(op == '%')	// If the operator '+', calculator will execute mod //
				{
					result = doit(mod,value1,value2);
				}
			}
		}

		else
		{
			if(test == '*') // Opetor power //
			{
				result = doit(power,result,value1);
			}
			else if(test == 32)
			{
				if(op == '+')	// If the operator '+', calculator will execute adding //
				{
					result = doit(add,result,value1);
				}
				else if(op == '-')	// If the operator '+', calculator will execute subtraction //
				{
					result = doit(sub,result,value1);
				}
				else if(op == '*')	// If the operator '+', calculator will execute multiply //
				{
					result = doit(mult,result,value1);
				}
				else if(op == '/')	// If the operator '+', calculator will execute division //
				{
					result = doit(div,result,value1);
				}
				else if(op == '%')	// If the operator '+', calculator will execute mod //
				{
					result = doit(mod,result,value1);
				}
			}
		}
	}
}

int doit(int operatorFunc(int, int), int first_number, int second_number) // operatorFunc understand "which operator I need using" //
{
	int resultValue;
	resultValue = operatorFunc(first_number,second_number);
	printf("%d\n",resultValue);
	return resultValue;
}

int add(int number1,int number2)
{
	return (number1+number2);
}

int sub(int number1, int number2)
{
	return (number1-number2);
}

int mult(int number1, int number2)
{
	return (number1*number2);
}

int div(int number1, int number2)
{
	while(number2 == 0) // If the user enter 0 for divider //
	{
		printf("İnvalid number. Divider don't have to be zero.\n");
		printf("Please you should change divider: ");
		scanf("%d",&number2);
	}
	return (number1/number2);
}

int power(int number1, int number2)
{
	int i,result = 1;
	
	if(number2 >= 0) // If the force is positive //
	{
		for(i = 0 ; i < number2 ; ++i)
			result = result * number1;
	}
	else	// If the force is negative //
	{
		for(i = 0 ; i > number2 ; --i)
			result = result / number1;
	}
	return result;
}

int mod(int number1, int number2) // Number 1 is divider //
{
	while(number2 == 0) // If the user enter 0 for mod divider //
	{
		printf("İnvalid number. Divider don't have to be zero.\n");
		printf("Please you should change divider: ");
		scanf("%d",&number2);
	}
	return (number1%number2);
}

void gradesCalculator() // My auxiliary function for calculating grades //
{
	int homeWork[10],Lab[10],exam[2];
	double lab,homework;
	double calculatedAll;

	printf("Please Enter the 10 numbers for homeworks\n"); 
	take_grades(homeWork);									// Taking 10 homework grades //
	printf("Please Enter the 10 number for lab grades\n");
	take_grades(Lab);										// Taking 10 lab grades //
	printf("Please Enter the midterm and final exams\n");
	take_exam_grades(exam);									// Taking midterm and final exam grades //

	homework = calculate_homework(homeWork);	// For calculating homework average //
	lab = calculate_lab(Lab);					// For calculating lab average //

	calculatedAll = calculate_all(homework, exam, lab);	// For calculating weighted average //

	printf("\nWeighted Average: %.2lf\n",calculatedAll);
}

double calculate_all(int HomeWork,int Exam[],int Lab)
{
	double result;

	Exam[0] = (Exam[0]*30)/100; // %30 of midterm's grade //
	Exam[1] = (Exam[1]*40)/100;	// %40 of final's grade //

	result = ((HomeWork*10)/100) + ((Lab*20)/100) + Exam[0] + Exam[1]; // Adding %30->midtermgrades, %40->finalgrades, labaverages and hw averages //

	return result;  
}

int take_grades(int a[])
{
	int i,value;
	
	for(i = 0 ; i < 10 ; ++i)
	{
		printf("%d: ",(i+1));
		scanf("%d",&value);
		a[i] = value;
	}
}

int take_exam_grades(int a[])
{
	printf("Midterm: ");
	scanf("%d",&a[0]);
	
	printf("Final: ");
	scanf("%d",&a[1]);
}

double calculate_lab(int a[])
{
	double result = 0;
	int i;

	for(i = 0 ; i < 10 ; ++i)
		result = result + a[i];

	return (result/10); 
}

double calculate_homework(int a[])
{
	double result = 0;
	int i;

	for(i = 0 ; i < 10 ; ++i)
		result = result + a[i];

	return (result/10); 
}

void drawQuatrangle()
{
	int heightNumber,whiteSpace,totalStar = 0;
	int countBig,i,j;
	char myBackslah = '\\'; // using for putting backslah('\') //

	printf("Please, Enter the number\n");
	scanf("%d",&heightNumber);

	whiteSpace = heightNumber - 1;
	for(countBig = 0 ; countBig < heightNumber ; ++countBig)
	{
		for(i = 0 ; i < whiteSpace ; ++i) // First of all, put the space till (heightnumber-1) //
		{
			printf(" ");
		}
		printf("/");	// And I put '/' //
		for(j = 0 ; j < totalStar ; ++j)
		{
			printf("*");		// And than put the star till ((step-1)*2) //
		}
		printf("%c\n",myBackslah); //Last of all, put the '\' //

		totalStar += 2;
		whiteSpace -= 1;
	}

	totalStar -= 2;
	whiteSpace += 1;
	for(countBig = 0 ; countBig < heightNumber ; ++countBig) // The reverse of the above //
	{
		for(i = 0 ; i < whiteSpace ; ++i)
		{
			printf(" ");
		}
		printf("%c",myBackslah);
		for(j = 0 ; j < totalStar ; ++j)
		{
			printf("*");
		}
		printf("/\n");

		totalStar -= 2;
		whiteSpace += 1;
	}
}