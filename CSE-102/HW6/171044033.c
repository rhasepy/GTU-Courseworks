#include <stdio.h>

int readDigit(int number, int count); // Read digit for part 4 //
int myPower(int number, int digit); // Calculate power //
int part4(int number, int digit); // for part 4 //
int part1(int number1, int number2, int mult); // for part 1 //
char part5(char *inputArr); // for part 5 //
void part3(int number); // for part 3 //
void part2(int arr[], int count, int count_limit); // for part 2.0 //
void merge_part2(int arr[], int left, int mid, int right); // for part 2.1 //
void menu();

int main()
{
	menu();
}

void menu()
{
	int choice,number,digit,result,mult = 2,number2;
	int arr[50],length_list,i;
	char inputArr[50],upperLetter;
	do
	{
		printf("1) GCD Finder\n");
		printf("2) Sort of Array\n");
		printf("3) Special Function\n");
		printf("4) Is Digit Power Equal?\n");
		printf("5) First Uppercase Finder\n");
		printf("6) Exit\n");
		scanf("%d",&choice);

		switch(choice)
		{
			case 1:
				printf("Input: ");
				scanf("%d",&number);
				scanf("%d",&number2);
				printf("\nFactors of %d and %d -> {",number,number2);
				result = part1(number,number2,mult); // Mult is 2 // multiplier is 2 //
				printf("}\n");
				printf("GCD: %d\n\n",result);
				break;
			case 2:
				printf("Enter the length of the list: ");
				scanf("%d",&length_list);
				
				printf("Enter the elements of list: \n");
				for(i = 0 ; i < length_list ; ++i)
					scanf("%d",&arr[i]);
				
				part2(arr,0,length_list-1);
				
				printf("\nSorted array is: ");
				
				for(i = 0 ; i < length_list ; ++i)
					printf("%d ",arr[i]);
				printf("\n");
				break;
			case 3:
				printf("Input: ");
				scanf("%d",&number);
				printf("Output: ");
				part3(number);
				printf("\n\n");
				break;
			case 4:
				printf("Input: ");
				scanf("%d",&number);
				printf("Output: ");
				result = part4(number,readDigit(number,0)); // starting count from 0 //
				if(number == result) printf("Equal\n\n");
				else printf("Not Equal\n\n");
				break;
			case 5:
				printf("Input: ");
				scanf("%s",inputArr);
				upperLetter = part5(inputArr);
				if(upperLetter >= 65 && upperLetter <= 90)
					printf("Output: %c\n\n",upperLetter);
				else
					printf("Output: There is not any upper letter\n\n");
				break;
			case 6:
				printf("The Program is turning off...\n");
				break;
			default:
				printf("İnvalid choice...\n");
				printf("Please try again...\n");
				break;
		}
	}while(choice != 6);
}

int readDigit(int number, int count) // To calculate digit number //
{
	if(number == 0 && count == 0) // If the number is '0' at the first time //
		return 1;

	else if(number > 0) // If the number is greater than 0, increment the count //
		return readDigit(number/10,count+1);
	
	else
		return count; // Return count //
}

int myPower(int number, int digit) // To calculate power of two numbers //
{
	if(digit > 0) // We can think that the digit number is count //
		return number*myPower(number,digit-1);
	else if(digit == 0)
		return 1;
}

int part1(int number1, int number2, int mult) // mult is multiplier // And starting 2 //
{
	if(mult <= number1 || mult <= number2) 
	{
		if(number1 % mult == 0 && number2 % mult == 0) // If the number1 and number2 can divided by mult, The mult is multyplier //
		{
			printf(" %d ",mult);
			return mult*part1(number1/mult,number2/mult,mult);
		}
		else
			return part1(number1,number2,mult+1);
	}

	else
		return 1;
}

void part2(int arr[], int count, int count_limit)
{
	int mid;

	if(count < count_limit)
	{
		mid = (count+count_limit) / 2; // Find middle index //
		part2(arr,count,mid); // Halve //
		part2(arr,mid+1,count_limit);
		merge_part2(arr,count,mid,count_limit);
	}
}

void merge_part2(int arr[], int left, int mid, int right)
{
	int i,j,k;
	int limit_left = mid - left + 1; // Temp left size //
	int limit_right = right - mid; // Temp right size //
	int Left[limit_left], Right[limit_right]; // Temp arrays for left halve and right halve //

	for(i = 0 ; i < limit_left ; ++i) // Fill Left halve arrays and right halve arrays //
		Left[i] = arr[left + i];

	for(j = 0 ; j < limit_right ; ++j)
		Right[j] = arr[mid + 1 + j];

	i = 0; // İnitial index of left array //
	j = 0; // İnitial index of right array //
	k = left;// İnitial index of merged array //

	while(i < limit_left && j < limit_right) // Compare Left arr and Right arr //
	{
		if(Left[i] <= Right[j])
		{
			arr[k] = Left[i];
			++i;
		}
		else
		{
			arr[k] = Right[j];
			++j;
		}
		++k;
	}

	while(i < limit_left)
	{
		arr[k] = Left[i];
		++i;
		++k;
	}

	while(j < limit_right)
	{
		arr[k] = Right[j];
		++j;
		++k;
	}
}

void part3(int number) // According to rule that given by pdf //
{	
	printf("%d ",number);
	if(number%2 == 0) // If number is even number //
		part3(number/2); // Call half of number //

	else if(number%2 == 1 && number != 1) // If number is odd number and number is not '1' //
		part3((3*number) + 1); // Call 3 multiple number and add 1 //
}

int part4(int number, int digit)
{
	if(number > 10)
	{
		return myPower(number%10,digit)+part4(number/10,digit);
	}
	else
		return myPower(number,digit);
}

char part5(char *inputArr)
{
	if(*inputArr > 96 && *inputArr < 123) // If the letter is lowercase //
		part5(++inputArr); // increment letter //
	else // If the letter is uppercase //
		return *inputArr; // return uppercase letter //
}