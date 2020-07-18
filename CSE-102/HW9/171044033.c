#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ARRAY_SIZE 1000

typedef struct person
{
	char name[MAX_ARRAY_SIZE];
	char surname[MAX_ARRAY_SIZE];
	char musical_Work[MAX_ARRAY_SIZE];
	int age;
	struct person *next;
}*top;

top addNode(char name[], char surname [], char creation [], int age); // For the push stack //
top Sort_Alphabetically(top stack); // In this func, stack is compared alphabetically //
top Sort_Increasingly(top stack); // In this func, stack is compared increasingly(age) //
top convertToArray(top stack); // In this func, stack is converted to array // For the sorting //
void deleteNode(top *stack); // For the pop stack //
void print_stack(top stack); // For the print stack //
void menu(); // For the menu //
void swap(top array1, top array2); // I coded Swap function for swap on the sorting //

void main()
{
	menu();
}

void menu()
{
	int choice,age,isFirst = 0;
	char name[MAX_ARRAY_SIZE] ,surname[MAX_ARRAY_SIZE] , creation[MAX_ARRAY_SIZE];
	top stack = NULL;
	top temp = NULL;

	do
	{
		printf("%s","\e[5;31;47m ****MENU**** \e[0m\n");
		printf("    1- Add a Person to the Stack\n");
		printf("    2- Pop a Person to the Stack\n");
		printf("    3- Sort Alphabetical Order\n");
		printf("    4- Sort Age in Increasing Order\n");
		printf("    5- Exit the menu\n");
		printf("*************\n");

		printf("Select your Choice: ");
		scanf("%d",&choice);

		switch(choice)
		{
			case 1:
				if(isFirst == 0) // If the stack is empty //
				{
					stack = addNode(name,surname,creation,age); // addNode type is '*top' // So the function returns stack //
					++isFirst;
				}
				else // If the stack is not empty //
				{
					temp = addNode(name,surname,creation,age); // addNode type is '*top' // So the function returns stack //
					temp -> next = stack;
					stack = temp;
				}
				printf("-------------\n");
				print_stack(stack);
				break;

			case 2:
				deleteNode(&stack); // Pop // I using double pointer (1. from *top, 2. top *stack) // Therefore i called adress of stack //
				print_stack(stack);
				break;

			case 3:
				stack = Sort_Alphabetically(stack); // compare alphabetically //
				print_stack(stack); // Print the stack thah was compared alphabetically //
				break;

			case 4:
				stack = Sort_Increasingly(stack); // compare increasingly (age) //
				print_stack(stack); // Print the stack that was compared increasingly (age) //
				break;

			case 5:
				printf("The program has been turned off...\n");
				break;

			default:
				printf("Invalid choice please try again...\n");
				break;
		}
	}while(choice != 5);

	free(stack);
	free(temp);
}

top Sort_Alphabetically(const top stack)
{
	int i,j,sizeofStack = 0;
	top array = convertToArray(stack); // The stack is transformed array //
	top temp = stack;
	top stack2,x;

	while(temp != NULL) // I find size of stack //
	{
		temp = temp -> next;
		++sizeofStack;
	}

	// I will sort there // And then I will push array into stack and return sorted stack // alphabeticlly //

	for(i = 0 ; i < sizeofStack - 1 ; ++i)
		for(j = 0 ; j < sizeofStack - i - 1 ; ++j)
			if(strcmp(array[j].name,array[j+1].name) > 0) // Compare the name of element of stack //
				swap(&array[j+1],&array[j]); // swapping //

	stack2 = stack;
	x = stack2;

	for(i = 0 ; i < sizeofStack ; ++i) // I have turned to array from stack // And then I store to stack from array //
	{
		strcpy(stack2->name,array[i].name);
		strcpy(stack2->surname,array[i].surname);
		strcpy(stack2->musical_Work,array[i].musical_Work);
		stack2->age = array[i].age;
		stack2 = stack2 -> next;
	}

	return x;
}

top Sort_Increasingly(const top stack)
{
	int i,j,sizeofStack = 0;
	top array = convertToArray(stack); // The stack is transformed array //
	top temp = stack;
	top stack2,x;

	while(temp != NULL)
	{
		temp = temp -> next;
		++sizeofStack;
	}

	// I will sort there // And then I will push array into stack and return the sorted stack // numeratilly //

	for(i = 0 ; i < sizeofStack-1 ; ++i)
		for(j = 0 ; j < sizeofStack - i - 1 ; ++j)
			if(array[j].age > array[j+1].age) // Compare the age of element of stack //
				swap(&array[j+1],&array[j]); // swapping //

	stack2 = stack;
	x = stack2;

	for(i = 0 ; i < sizeofStack ; ++i) // I have turned to array from stack // And then I store to stack from array //
	{
		strcpy(stack2->name,array[i].name);
		strcpy(stack2->surname,array[i].surname);
		strcpy(stack2->musical_Work,array[i].musical_Work);
		stack2->age = array[i].age;
		stack2 = stack2 -> next;
	}

	return x;
}

void swap(top array1, top array2) // Swapping // I need to this function on the sorting //
{
	char temp_name[MAX_ARRAY_SIZE];
	char temp_surname[MAX_ARRAY_SIZE];
	char temp_musical_Work[MAX_ARRAY_SIZE];
	int temp_age;

	strcpy(temp_name,array1->name);
	strcpy(temp_surname,array1->surname);
	strcpy(temp_musical_Work,array1->musical_Work);
	temp_age = array1->age;

	strcpy(array1->name,array2->name);
	strcpy(array1->surname,array2->surname);
	strcpy(array1->musical_Work,array2->musical_Work);
	array1->age = array2->age;

	strcpy(array2->name,temp_name);
	strcpy(array2->surname,temp_surname);
	strcpy(array2->musical_Work,temp_musical_Work);
	array2->age = temp_age;
}

top convertToArray(const top stack) // The stack is converted the array by this function // For the easy compare //
{
	int sizeofStack = 0,i;
	top temp,array,traverser;

	temp = stack;
	traverser = stack;

	while(temp != NULL)
	{
		temp = temp -> next;
		++sizeofStack;
	}

	array = (top)malloc(sizeof(top)*sizeofStack);

	for(i = 0 ; i < sizeofStack ; ++i)
	{
		strcpy(array[i].name,traverser->name);
		strcpy(array[i].surname,traverser->surname);
		strcpy(array[i].musical_Work,traverser->musical_Work);
		array[i].age = traverser -> age;

		traverser = traverser -> next;
	}

	return array;
}

top addNode(char name[MAX_ARRAY_SIZE], char surname [MAX_ARRAY_SIZE], char creation [MAX_ARRAY_SIZE], int age)
{
	int i;
	int index;
	char temp_name[MAX_ARRAY_SIZE],test;
	top new;

	new = (top)malloc(sizeof(top));

	printf("Name: ");
	scanf("%s",name);
	scanf("%c",&test); // If the input has two name or one name?? //

	if(test == ' ')
	{
		index = 0;
		while(name[index] != '\0')
			++index;

		name[index] = ' ';
		scanf("%s",&name[index+1]);
	}

	printf("Surname: ");
	scanf("%s",surname);

	printf("Creation: ");
	scanf("%s",creation);
	scanf("%c",&test); // If the creation of input has two name or one name?? //

	if(test == ' ')
	{
		index = 0;
		while(creation[index] != '\0')
			++index;

		creation[index] = ' ';
		scanf("%s",&creation[index+1]);
	}

	printf("Age: ");
	scanf("%d",&age);

	i = 0;
	while(name[i] != '\0') // I assign to stack from array // I will return stack below //
	{
		new -> name[i] = name[i];
		++i;
	}

	i = 0;
	while(surname[i] != '\0') // I assign to stack from array // I will return stack below //
	{
		new -> surname[i] = surname[i];
		++i;
	}

	i = 0;
	while(creation[i] != '\0') // I assign to stack from array // I will return stack below //
	{
		new -> musical_Work[i] = creation[i];
		++i;
	}

	new -> age = age;

	return new; // I return filled stack //
}

void print_stack(const top stack)
{
	int i = 1;
	top traverser;

	traverser = stack;

	if(traverser == NULL) // If the stack is empty //
		printf("(Stack Content: [EMPTY])\n");
	else
	{
		while(traverser != NULL) // if the stack is not empty //
		{
			printf("%d) ",i);
			printf("%s\n",traverser->name);
			printf("%s\n",traverser->surname);
			printf("%s\n",traverser->musical_Work);
			printf("%d\n",traverser->age);
			traverser = traverser -> next;
			++i;
		}
	}
}

void deleteNode(top *stack) // Pop // Last in first out //
{
	if(*stack != NULL)
		(*stack) = (*stack) -> next;
}