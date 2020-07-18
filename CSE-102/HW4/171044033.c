/*
	HOCAM POKEMONLARI YERLEŞTİRİRKEN NUMARASINI SECİP ORNEK VERDİGİM KORDİNANTA GORE YERLESTIRIYORUZ 
	SONRA POKEMONUN NUMARASINI SECIP w a s d / W A S D HARFLERIYLE HAREKET ETTIRIYORUZ 
	BUNLARI BILGI OLARAK ILGILI FONKSIYONDA VERDIM
	YAPAY ZEKANIN POKEMONLARININ HAREKET OLASILIGI BAYAGI DUSTU ACIK VERMEMEK ICIN KURDUGUM ALGORITMAYA GORE
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef enum
{
	Charmander, Pikachu, Squirtle, Bulbasur, Pidgeotto, Ratata, Mug, Caterpie, Zubat, Krabby
}pokemon;

typedef enum
{
	Linear, Quadratic
}attack_type;

void pokedex(char Pokemon_name[10][11], int range[10], attack_type type[10], int attack_power[10], int stamina[10]);
void oaks_laboratory(char Pokemon_name[10][11], pokemon Pokemons[10], pokemon *my_Pokemons);
void show_Pokemons(char Pokemon_name[10][11], pokemon Pokemons[10], int pokemon_count);
void catch_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[10], pokemon *my_pocket);
void release_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[10], pokemon *my_pocket);
void battle(char Pokemon_name[10][11], int range[10], attack_type type[], int attack_power[10], int stamina[10], pokemon user_Pokemons[4]);
void show_area(char Pokemon_name[10][11], int area[8][8], int pokemon_staminas_view[8][8]);
void menu(pokemon my_Pokemons[4]);
void fillPokemon(pokemon Pokemons[10], int range[10], attack_type type[10], int attack_power[10], int stamina[10]);
void fillProgramPokemon(pokemon programPokemons[4]);
void sortPokemon(pokemon *my_pocket);
void movePokemonUser(pokemon my_Pokemons[4], int area[8][8], char Pokemon_name[10][11],int pokemon_staminas_view[8][8]);
void movePokemonProgram(pokemon my_Pokemons[4],int area[8][8],char Pokemon_name[10][11],int pokemon_staminas_view[8][8]);
void putPokemon(char Pokemon_name[10][11], pokemon user_Pokemons[4], int area[8][8]);
void putPokemonProgram(char Pokemon_name[10][11], pokemon programPokemons[4], int area[8][8]);
void putStamina(int stamina[10], int pokemon_staminas_view[8][8], int area[8][8]);
void attackPokemon(pokemon pokemons[4], int area[8][8], int range[10], attack_type type[10], int attack_power[10], int stamina[10]);
int howMany(int pokemon_count, char Pokemon_name[10][11]);
int haveYouTest(int Choice, pokemon my_pockett[4], int *test);
int isFill(pokemon my_Pokemons[4]);
int isExit(char word[11]);


int main()
{
	pokemon my_Pokemons[4] = {-1,-1,-1,-1} ;
	srand(time(NULL));
	menu(my_Pokemons);
	return 0;
}

void menu(pokemon my_Pokemons[4])
{
	int option;
	int range[10], attack_power[10], stamina[10];
	char Pokemon_name[10][11] = { {"Charmander"},{"Pikachu"},{"Squirtle"},{"Bulbasur"},{"Pidgeotto"},{"Ratata"},{"Mug"},{"Caterpie"},{"Zubat"},{"Krabby"} };
	attack_type type[10];

	pokemon Pokemons[10];

	fillPokemon(Pokemons,range,type,attack_power,stamina);

	do{
		printf("\nPlease select an option to continue\n");
		printf("1) Open Pokedex\n");
		printf("2) Go to Oak's Laboratory\n");
		printf("3) Enter the tournament\n");
		printf("Option: ");
		scanf("%d",&option);

		switch(option)
		{
			case 1:
				pokedex(Pokemon_name,range,type,attack_power,stamina);
				option = 0;
				break;
			case 2:
				oaks_laboratory(Pokemon_name,Pokemons,my_Pokemons);
				option = 0;
				break;
			case 3:
				if(isFill(my_Pokemons) == 1)
				{
					battle(Pokemon_name, range, type, attack_power, stamina, my_Pokemons);
				}
				else
				{
					printf("You must have 4 pokemons to enter the tournament\n");
					printf("(Hint: go to oaks lab and catch pokemons)\n");
					printf("Please try again...\n");
					option = 0;
				}
				break;
			default:
				printf("İnvalid option, please try again...\n\n");
				break;
		}
		
	}while(option < 1 || option > 3);
}

void fillPokemon(pokemon Pokemons[10], int range[10], attack_type type[10], int attack_power[10], int stamina[10])
{
	Pokemons[0] = Charmander;
	range[Charmander] = 1;
	type[Charmander] = 1;
	attack_power[Charmander] = 500;
	stamina[Charmander] = 2200;

	Pokemons[1] = Pikachu;
	range[Pikachu] = 3;
	type[Pikachu] = 0;
	attack_power[Pikachu] = 350;
	stamina[Pikachu] = 1500;

	Pokemons[2] = Squirtle;
	range[Squirtle] = 4;
	type[Squirtle] = 0;
	attack_power[Squirtle] = 300;
	stamina[Squirtle] = 1700;

	Pokemons[3] = Bulbasur;
	range[Bulbasur] = 3;
	type[Bulbasur] = 0;
	attack_power[Bulbasur] = 400;
	stamina[Bulbasur] = 2500;

	Pokemons[4] = Pidgeotto;
	range[Pidgeotto] = 2;
	type[Pidgeotto] = 1;
	attack_power[Pidgeotto] = 250;
	stamina[Pidgeotto] = 1900;

	Pokemons[5] = Ratata;
	range[Ratata] = 2;
	type[Ratata] = 0;
	attack_power[Ratata] = 250;
	stamina[Ratata] = 2500;

	Pokemons[6] = Mug;
	range[Mug] = 1;
	type[Mug] = 1;
	attack_power[Mug] = 350;
	stamina[Mug] = 3000;

	Pokemons[7] = Caterpie;
	range[Caterpie] = 2;
	type[Caterpie] = 1;
	attack_power[Caterpie] = 200;
	stamina[Caterpie] = 1200;

	Pokemons[8] = Zubat;
	range[Zubat] = 3;
	type[Zubat] = 0;
	attack_power[Zubat] = 350;
	stamina[Zubat] = 1250;

	Pokemons[9] = Krabby;
	range[Krabby] = 2;
	type[Krabby] = 0;
	attack_power[Krabby] = 300;
	stamina[Krabby] = 2600;
}

void pokedex(char Pokemon_name[10][11], int range[10], attack_type type[10], int attack_power[10], int stamina[10])
{
	char pokeName[11];
	int count,i,j,flag = 0;

	do{
		count = 0;
		i = 0;
		j = 0;
		printf("\nPlease type name of the Pokémon (type exit to close Pokedex)\n");
		scanf("%s",pokeName);

		if(isExit(pokeName) == 0)
		{
			while(pokeName[j] != '\0')
			{
				if(pokeName[j] == Pokemon_name[i][j])
				{
					++count;
					++j;
				}
				else
				{
					++i;
					count = 0;
				}
			}

			printf("\nName: ");
			for(j = 0 ; j < 11 ; ++j)
			{
				printf("%c",Pokemon_name[i][j]);
			}
			
			printf("\nA. Type: ");
			if(type[i] == 1)
				printf("Quadratic\n");
			else if(type[i] == 0)
				printf("Linear\n");

			printf("Range: %d block\n",range[i]);
			printf("A. Power : %d\n",attack_power[i]);
			printf("Stamina: %d\n\n",stamina[i]);
		}
		else
			flag = 1;
	}while(flag == 0);
}

int isExit(char word[11])
{
	if(word[0] == 'e' && word[1] == 'x' && word[2] == 'i' && word[3] == 't')
		return 1;
	else if(word[0] == 'E' && word[1] == 'x' && word[2] == 'i' && word[3] == 't')
		return 1;
	else
		return 0;
}

void oaks_laboratory(char Pokemon_name[10][11], pokemon Pokemons[10], pokemon *my_Pokemons)
{
	int option,x = 0;
	int pokemon_count,i;
	int pokeCount = 0;

	pokemon_count = howMany(pokemon_count,Pokemon_name);
	
	do{
		printf("\nWelcome to Laboratory of Professor Oak. How can I help you?\n");
		printf("1) Show Pokémons\n");
		printf("2) Catch a Pokémon\n");
		printf("3) Release a Pokémon\n");
		printf("4) Show my pocket\n");
		printf("5) Back\n");
		printf("Option: ");
		scanf("%d",&option);

		switch(option)
		{
			case 1:
				show_Pokemons(Pokemon_name,Pokemons,pokemon_count);
				break;
			case 2:
				if(pokeCount == 4)
				{
					printf("\n!!!You have four pokemons. You could not catch a pokemon.\n");
				}
				else
				{
					catch_a_pokemon(Pokemon_name,Pokemons,my_Pokemons);
						++pokeCount;	//We can think that we have 4 pokeballs//The count is counting this pokeball//
				}
				break;
			case 3:
				release_a_pokemon(Pokemon_name,Pokemons,my_Pokemons);
				--pokeCount;
				break;
			case 4:
				while(my_Pokemons[x] != -1 && x < 4)
				{
					++x;
				}
				show_Pokemons(Pokemon_name,my_Pokemons,x);
				break;
			case 5:
				break;
			default:
				printf("İnvalid option, please try again...\n\n");
				break;
		}
	}while(option != 5);
}

int howMany(int pokemon_count, char Pokemon_name[10][11]) //The function calculate that are there how many pokemons //
{
	int i = 0;
	
	while(Pokemon_name[i][0] != '\0')
	{
		++i;
	}
	return i;
}

void show_Pokemons(char Pokemon_name[10][11], pokemon Pokemons[10], int pokemon_count)
{
	int i,j;
	int myPoke;

	if(pokemon_count >= 0 && pokemon_count <= 4)
	{
		printf("\nYou have: \n");
		if(pokemon_count == 0)
		{
			printf("You do not have any pokemon\n");
		}

		else
		{
			for(i = 0 ; i < pokemon_count ; ++i)
			{
				myPoke = Pokemons[i];
				if(myPoke >= 0)
					printf("%d - ",myPoke);
				for(j = 0 ; j < 11 ; ++j)
				{
					printf("%c",Pokemon_name[myPoke][j]);
				}
				printf("\n");
			}
		}
	}
	
	else
	{
		printf("\n");
		for(i = 0 ; i < pokemon_count ; ++i)
		{
			printf("%d - ",i);
			for(j = 0 ; j < 11 ; ++j)
			{
				printf("%c",Pokemon_name[i][j]);
			}
			printf("\n");
		}
	}
}

void catch_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[10], pokemon *my_pocket)
{
	int i,j,flag = 0,x = 0;
	int choice;
	int test,testFuncValue;

	printf("\nPlease choose the pokemon that you want\n");
	printf("\nPokemonlist: \n");
	for(i = 0 ; i < 10 ; ++i)
	{
		printf("%d. ",i);
		for(j = 0 ; j < 11 ; ++j)
		{
			printf("%c",Pokemon_name[i][j]);
		}
		printf("\n");
	}

	printf("Your choice: ");
	do{
		scanf("%d",&choice);
		testFuncValue = haveYouTest(choice,my_pocket,&test);
		
		if(choice >= 0 && choice < 10 && testFuncValue == 0)
		{
			while(my_pocket[x] != -1)	//For -> Which element is free//
			{
				++x;
			}
			my_pocket[x] = Pokemons[choice];
			flag = 1;
		}
		else if(testFuncValue == 1)
			printf("Invalid choice or you have already had this pokemon please try again with different choice: ");
	
	}while(flag == 0);
}

void release_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[10], pokemon *my_pocket)
{
	int pokCount = 0, i = 0, j = 0;
	int relco = 0;
	int choice,test;
	printf("Your pokemon list: \n");
	while(my_pocket[i] != -1 && i < 4)	//For -> Which element is free//
	{
		++pokCount;
		++i;
	}
	show_Pokemons(Pokemon_name,my_pocket,pokCount);

	printf("Please, you choose pokemon that you want to release\n");
	scanf("%d",&choice);
	if(haveYouTest(choice,my_pocket,&test) == 1)
	{	
		while(my_pocket[j] != choice)	
		{
			++j;
			++relco;
		}
		my_pocket[relco] = -1;
		sortPokemon(my_pocket);
	}
	else
		printf("\nYou do not have this pokemon please try again...\n");
}

void sortPokemon(pokemon my_pockett[4]) //The arrays changed backhanded//
{
	int i;
	int j = 0;
	int temp[4] = { -1,-1,-1,-1 };

	for(i = 0 ; i < 4 ; ++i)
	{
		if(my_pockett[i] != -1)
		{
			temp[j] = my_pockett[i];
			++j;
		}
	}
	for(i = 0 ; i < 4 ; ++i)
		my_pockett[i] = temp[i];
}

int haveYouTest(int Choice, pokemon my_pockett[4], int *test)
{
	int i;

	for(i = 0 ; i < 4 ; ++i)
	{
		if(my_pockett[i] == Choice)
		{
			*test = i;
			return 1;
		}
	}
	return 0;
}

int isFill(pokemon my_Pokemons[4]) // The function say that which element of array is empty or full //
{
	int count = 0,i;
	for(i = 0 ; i < 4 ; ++i)
	{
		if(my_Pokemons[i] != -1)
			++count;
	}

	if(count == 4)
	{
		return 1;
	}
	else
		return 0;
}

void fillProgramPokemon(pokemon user_Pokemons[4])	//Fill the pokemons of program //
{
	int i = 0,j;
	int count,temp;
	
	for(i = 0 ; i < 4 ; ++i)
		user_Pokemons[i] = rand() % 10;

	while(user_Pokemons[0] == user_Pokemons[1] || user_Pokemons[0] == user_Pokemons[2] || user_Pokemons[0] == user_Pokemons[3] || user_Pokemons[1] == user_Pokemons[2] || user_Pokemons[1] == user_Pokemons[3] || user_Pokemons[2] == user_Pokemons[3])
	{
		for(i = 0 ; i < 4 ; ++i)
			user_Pokemons[i] = rand() % 10;
	}
}

void battle(char Pokemon_name[10][11], int range[10], attack_type type[2], int attack_power[10], int stamina[10], pokemon user_Pokemons[4])
{
	int area[8][8],i,j,coordi = 10;
	int pokemon_staminas[8][8];
	int haveProgram = 3,haveUser = 3;
	pokemon programPokemons[4];
	pokemon temp_programPokemons[4];
	
	for(i = 0 ; i < 8 ; ++i)
	{
		for(j = 0 ; j < 8 ; ++j)
		{
			pokemon_staminas[i][j] = -1;
			area[i][j] = -1;
		}
	}

	fillProgramPokemon(programPokemons);
	for(i = 0 ; i < 4 ; ++i)
	{
		temp_programPokemons[i] = programPokemons[i] + 10;
	}
	putPokemonProgram(Pokemon_name,temp_programPokemons,area);
	putPokemon(Pokemon_name,user_Pokemons,area);
	putStamina(stamina,pokemon_staminas,area);
	show_area(Pokemon_name,area,pokemon_staminas);
	
	while(haveProgram != 0 || haveUser != 0)
	{
		movePokemonUser(user_Pokemons,area,Pokemon_name,pokemon_staminas);
		show_area(Pokemon_name,area,pokemon_staminas);
		movePokemonUser(user_Pokemons,area,Pokemon_name,pokemon_staminas);
		show_area(Pokemon_name,area,pokemon_staminas);
		
		//attackPokemon(user_Pokemons, area, range, type, attack_power, stamina);	
		//show_area(Pokemon_name,area,pokemon_staminas);
		
		movePokemonProgram(temp_programPokemons,area,Pokemon_name,pokemon_staminas);
		show_area(Pokemon_name,area,pokemon_staminas);
		
		attackPokemon(programPokemons, area, range, type, attack_power, stamina);
		show_area(Pokemon_name,area,pokemon_staminas);
		
		for(i = 0 ; i < 4 ; ++i) // Testing whose pokemons are end //
		{
			if(user_Pokemons[i] == -1)
				--haveUser;
			if(programPokemons[i] == -1 || temp_programPokemons[i] == -1)
				--haveProgram;
		}
	}
}

void show_area(char Pokemon_name[10][11], int area[8][8], int pokemon_staminas_view[8][8])
{
	int i,j,count;
	
	printf("\n");
	for(i = 0 ; i < 8 ; ++i)
	{	
		for(j = 0 ; j < 8 ; ++j)
		{
			if(area[i][j] != -1)
			{
				count = area[i][j];
				
				if(count == 4 || count == 52)
					printf("|  Pd  ");
				else
				{
					if(count > 9)
					{
						count = count - 10 ;
						printf("|  %c%c  ",Pokemon_name[count][0],Pokemon_name[count][1]);
					}
					else
						printf("|  %c%c  ",Pokemon_name[count][0],Pokemon_name[count][1]);
				}
			}
			else
				printf("|      ");
		}
		printf("|");
		printf("\n");
		for(j = 0 ; j < 8 ; ++j)
		{
			if(pokemon_staminas_view[i][j] != -1)
			{
				printf("|(%04d)",pokemon_staminas_view[i][j]);
			}
			else
				printf("|      ");
		}
		printf("|");
		if(i < 7)
			printf("\n---------------------------------------------------------");
		printf("\n");
	}
	printf("\n");
}

void putPokemonProgram(char Pokemon_name[10][11], pokemon programPokemons[4], int area[8][8])
{
	int i,j,count = 0;

		i = rand() % 2;
		j = rand() % 8;
		area[i][j] = programPokemons[count];

		for(count = 1 ; count < 4 ; ++count)
		{
			i = rand() % 2;
			j = rand() % 8;
			while(area[i][j] != -1)
			{
				i = rand() % 2;
				j = rand() % 8;
			}
			area[i][j] = programPokemons[count];
		}
}

void putPokemon(char Pokemon_name[10][11], pokemon user_Pokemons[4], int area[8][8])
{
	int countBig,i,j,x,example = 10;
	int choice,coordinate,pokindex;
	int test;

	printf("For Example\n");
	for(i = 0 ; i < 2 ; ++i)
	{
		for(j = 0 ; j < 8 ; ++j)
		{	
			printf("|  %d  ",example);
			++example;
		}
		printf("|");
		printf("\n---------------------------------------------------------\n");
	}

	printf("Your pokemons: ");
	for(i = 0 ; i < 4 ; ++i)
	{
		printf("\n");
		x = user_Pokemons[i];
		printf("%d - ",user_Pokemons[i]);
		for(j = 0 ; j < 11 ; ++j)
			printf("%c",Pokemon_name[x][j]);
	}
		
	printf("\n");

	for(countBig = 0 ; countBig < 4 ; ++countBig)
	{
		printf("Please, enter the pokemon\n");
		scanf("%d",&choice);
		pokindex = 0;
		while(user_Pokemons[pokindex] != choice)
		{
			++pokindex;
		}
		printf("Please, enter the coordinate\n");
		scanf("%d",&coordinate);	
		if(coordinate == 10)
			area[6][0] = user_Pokemons[pokindex];
		else if(coordinate == 11)
			area[6][1] = user_Pokemons[pokindex];
		else if(coordinate == 12)
			area[6][2] = user_Pokemons[pokindex];
		else if(coordinate == 13)
			area[6][3] = user_Pokemons[pokindex];
		else if(coordinate == 14)
			area[6][4] = user_Pokemons[pokindex];
		else if(coordinate == 15)
			area[6][5] = user_Pokemons[pokindex];
		else if(coordinate == 16)
			area[6][6] = user_Pokemons[pokindex];
		else if(coordinate == 17)
			area[6][7] = user_Pokemons[pokindex];
		else if(coordinate == 18)
			area[7][0] = user_Pokemons[pokindex];
		else if(coordinate == 19)
			area[7][1] = user_Pokemons[pokindex];
		else if(coordinate == 20)
			area[7][2] = user_Pokemons[pokindex];
		else if(coordinate == 21)
			area[7][3] = user_Pokemons[pokindex];
		else if(coordinate == 22)
			area[7][4] = user_Pokemons[pokindex];
		else if(coordinate == 23)
			area[7][5] = user_Pokemons[pokindex];
		else if(coordinate == 24)
			area[7][6] = user_Pokemons[pokindex];
		else if(coordinate == 25)
			area[7][7] = user_Pokemons[pokindex];
	}
}

void putStamina(int stamina[10], int pokemon_staminas_view[8][8], int area[8][8])
{
	int i,j,index;

	for(i = 0 ; i < 8 ; ++i)
	{
		for(j = 0 ; j < 8 ; ++j)
		{
			if(area[i][j] != -1)
			{
				if(area[i][j] > 9)
				{
					index = area[i][j] - 10;
					pokemon_staminas_view[i][j] = stamina[index];
				}
				else
				{
					index = area[i][j];
					pokemon_staminas_view[i][j] = stamina[index];
				}
			}
		}
	}
}

void movePokemonUser(pokemon my_Pokemons[4],int area[8][8],char Pokemon_name[10][11],int pokemon_staminas_view[8][8])
{
	int choice,i = 0,x,j;
	int test;
	int understand_myPokemons[4];
	char joystick;

	printf("Your pokemons: ");
	for(i = 0 ; i < 4 ; ++i)
	{
		printf("\n");
		x = my_Pokemons[i];
		printf("%d - ",my_Pokemons[i]);
		for(j = 0 ; j < 11 ; ++j)
			printf("%c",Pokemon_name[x][j]);
	}

	do{
		printf("\nPlease, choose pokemon that you want to move and attack\n");
		scanf("%d",&choice);
	}while(haveYouTest(choice,my_Pokemons,&test) != 1);

	printf("Please, enter the direction that you want\n");
	printf("W - > Up\n");
	printf("S -> Down\n");
	printf("D - > Right\n");
	printf("A -> Left\n");
	printf("X -> Constant\n");
	scanf(" %c",&joystick);

	if(joystick == 'W' || joystick == 'w')
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if(i-1 >= 0)
					{
						if(area[i-1][j] == -1)
						{
							area[i-1][j] = area[i][j];
							pokemon_staminas_view[i-1][j] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}
	}
	else if(joystick == 'S' || joystick == 's')
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if(i+1 < 8)
					{
						if(area[i+1][j] == -1)
						{
							area[i+1][j] = area[i][j];
							pokemon_staminas_view[i+1][j] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}
	}
	else if(joystick == 'D' || joystick == 'd')
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if(j+1 < 8)
					{
						if(area[i][j+1] == -1)
						{
							area[i][j+1] = area[i][j];
							pokemon_staminas_view[i][j+1] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}
	}
	else if(joystick == 'A' || joystick == 'a')
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if(j-1 >= 0)
					{
						if(area[i][j-1] == -1)
						{
							area[i][j-1] = area[i][j];
							pokemon_staminas_view[i][j-1] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
						
						}	j = 8;
					}
				}
			}
		}
	}
}

void movePokemonProgram(pokemon my_Pokemons[4],int area[8][8],char Pokemon_name[10][11],int pokemon_staminas_view[8][8])
{
	int move,direction,choice;
	int i,j;

	move = 1 + rand() % 2;
	direction = 1 + rand() % 4;
	choice = rand() % 3;

	choice = my_Pokemons[choice];

	if(direction == 1)
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if((j-1) >= 0)
					{
						if(area[i][j-1] == -1)
						{
							area[i][j-1] = area[i][j];
							pokemon_staminas_view[i][j-1] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}
	}
	else if(direction == 2)
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if((j+1) < 8)
					{
						if(area[i][j+1] == -1)
						{
							area[i][j+1] = area[i][j];
							pokemon_staminas_view[i][j+1] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}
	}
	else if(direction == 3)
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if((i+1) < 8)
					{
						if(area[i+1][j] == -1)
						{
							area[i+1][j] = area[i][j];
							pokemon_staminas_view[i+1][j] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}		
	}
	else if(direction == 4)
	{
		for(i = 0 ; i < 8 ; ++i)
		{
			for(j = 0 ; j < 8 ; ++j)
			{
				if(area[i][j] == choice)
				{
					if((i+1) >= 0)
					{
						if(area[i-1][j] == -1)
						{
							area[i-1][j] = area[i][j];
							pokemon_staminas_view[i-1][j] = pokemon_staminas_view[i][j];
							area[i][j] = -1;
							pokemon_staminas_view[i][j] = -1;
							i = 8;
							j = 8;
						}
					}
				}
			}
		}
	}
}

void attackPokemon(pokemon pokemons[4], int area[8][8], int range[10], attack_type type[10], int attack_power[10], int stamina[10])
{

}