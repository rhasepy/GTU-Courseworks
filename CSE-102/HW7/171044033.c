#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef enum
{
	NOONE,CAP,CAR
}player_type;

typedef enum
{
	START,PROPERTY,TAX,PUNISH
}block_type;

typedef struct 
{
	player_type type; // Cap or Car //
	int current_block_id; //Holds player location as block id//
	int owned_block_ids[11]; //Holds property block ids that are owned by the user//
	int account; //Holds amount of the current money of the player//
	int turn_to_wait; //Holds number of turns to wait if the player got a punishment//
	char *name; //Holds the name of the player//
}player;

typedef struct 
{
	int block_id;//Holds id of the block//
	char *name;//Holds text of the block that is shown on the top of the block//
	int price;
	int rent;
	int rent_1;
	int rent_2;
	int rent_3;
	int house_price;
	int house_count;
	player owner;
	block_type type;
}block;

int menu_onGameplay();
int roll_dice();
int isCrash(player player_one, player player_two); // I checked bankruptcy //
void init_the_board(block board[]);
void show_board(block board[], player player_one, player player_two);
void show_properties(block board[]);
void buy_property(block *current_block, player *current_player);
void sell_property(block board[], player *current_player);
void gameplay(block board[], player player_one, player player_two);
void build_house(block *current_block, player *current_player); // for building house //

void main()
{
	srand(time(NULL));

	block board[20];
	player player_one,player_two;

	init_the_board(board);

	gameplay(board,player_one,player_two);
}

int isCrash(player player_one, player player_two)
{
	int isHave_playerone = 0;
	int isHave_playertwo = 0;
	int i;

	for(i = 0 ; i < 11 ; ++i)
	{
		if(player_one.owned_block_ids[i] != 0)
			isHave_playerone = 1; 
	}

	for(i = 0 ; i < 11 ; ++i)
	{
		if(player_two.owned_block_ids[i] != 0)
			isHave_playertwo = 1;
	}

	if(player_one.account > 0 && player_two.account > 0)
		return 1;

	if(player_one.account <= 0 && isHave_playerone == 0)
	{
		printf("...THE CAR WON...\n");
		return 0;
	}

	else if(player_two.account <= 0 && isHave_playertwo == 0)
	{
		printf("...THE CAP WON...\n");
		return 0;
	}
}

void gameplay(block board[20], player player_one, player player_two)
{
	int n,player_count = 1,i,flag = 0;
	int choice,house_count2,isPunish = 0;
	block current_block;
	player current_player;

	player_one.type = CAP;
	player_one.current_block_id = 0;
	player_one.account = 100000;
	player_one.turn_to_wait = 0;
	for(i = 0 ; i < 11 ; ++i)
		player_one.owned_block_ids[i] = 0;
	player_one.name = "Cap";

	player_two.type = CAR;
	player_two.current_block_id = 0;
	player_two.account = 100000;
	player_two.turn_to_wait = 0;
	for(i = 0 ; i < 11 ; ++i)
		player_two.owned_block_ids[i] = 0;
	player_two.name = "Car";

	show_board(board,player_one,player_two);

	while(isCrash(player_one,player_two)) // Testing for the bankruptcy //
	{
		//show_board(board,player_one,player_two);

		do
		{
			if(player_count % 2 == 0)
				printf("%s","\e[5;31;47m CAR GAMİNG... \e[0m\n");
			else if(player_count % 2 == 1)
				printf("%s","\e[5;31;47m CAP GAMİNG... \e[0m\n");

			n = menu_onGameplay();

			/*
				User allowed to use other options except for purchase and resale.
			*/

			if(n == 2)
			{
				if(player_count % 2 == 0)
					printf("..........You have %d$..........\n",player_two.account);
				else
					printf("..........You have %d$..........\n",player_one.account);
			}

			else if(n == 3)
			{
				printf("\nYour properties are: \n");
				if(player_count % 2 == 0)
				{
					for(i = 0 ; i < 11 ; ++i)
						if(player_two.owned_block_ids[i] != 0)
						{
							printf("%d- %s\n",(i+1),board[player_two.owned_block_ids[i]].name);
							flag = 1;
						}

					if(flag == 0)
						printf("You do not have to any property\n\n");
				}

				else
				{
					for(i = 0 ; i < 11 ; ++i)
						if(player_one.owned_block_ids[i] != 0)
						{
							printf("%d- %s\n",(i+1),board[player_one.owned_block_ids[i]].name);
							flag = 1;
						}

					if(flag == 0)
						printf("You do not have to any property\n\n");
				}
			}

			else if(n == 4)
				show_properties(board);

			else if(n == 6)
			{
				if(player_count % 2 == 0)
					build_house(&board[player_two.current_block_id], &player_two);
				else
					build_house(&board[player_two.current_block_id], &player_one);
			}

			if(n != 1)
				printf("\nFirstly, you have to roll the dice\nPlease try again...\n\n");

		}while(n != 1);

		

		if(player_count % 2 == 0)
		{
			if(player_two.turn_to_wait != 0) // if the user is penalized //
				player_two.turn_to_wait = player_two.turn_to_wait - 1;
			if(player_two.turn_to_wait == 0) // if the user is not penalized //
				player_two.current_block_id = player_two.current_block_id + roll_dice();

			if(player_two.current_block_id >= 20) // if the current position exceeds 20, this means that I have passed. //
			{
				player_two.current_block_id = player_two.current_block_id % 20;
				player_two.account = player_two.account + 10000;
			}

			show_board(board,player_one,player_two);

			if(board[player_two.current_block_id].type == TAX) // If the player two at the tax block //
			{
				player_two.account = player_two.account - board[player_two.current_block_id].rent;
				printf("You paid for the tax pay\n");
				printf("Your money -> %d\n",player_two.account); // reminding //
					
				while(player_two.account < 0) // If he can not pay //
				{
					printf("Your money has run out...\n");
					printf("You have to press 7 to sell the property\n");
					
					n = menu_onGameplay();
						
					if(n == 7) // sells land if the money is not enough
						sell_property(board, &player_two);
				}
				++player_count;
			}

			else if(board[player_two.current_block_id].type == PROPERTY)
			{
				if(board[player_two.current_block_id].owner.type == NOONE)
				{
					printf("Would you like buy this property\n");
					printf("(If your answer is 'yes' press 1, ıf your answer is 'no' press 0): ");
					scanf("%d",&choice);
					if(choice == 1)
						buy_property(&board[player_two.current_block_id], &player_two);
				}

				else
				{
					house_count2 = board[player_two.current_block_id].house_count;

					if(house_count2 == 0)
					{
						player_two.account = player_two.account - board[player_two.current_block_id].rent;
						while(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_two);
						}
					}

					else if(house_count2 == 1)
					{
						player_two.account = player_two.account - board[player_two.current_block_id].rent_1;
						while(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_two);
						}						
					}	

					else if(house_count2 == 2)
					{
						player_two.account = player_two.account - board[player_two.current_block_id].rent_2;
						while(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_two);
						}						
					}

					else if(house_count2 == 3)
					{
						player_two.account = player_two.account - board[player_two.current_block_id].rent_3;
						while(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_two);
						}						
					}
				}
				++player_count;
			}

			else if(board[player_two.current_block_id].type == PUNISH)
			{
				if(player_two.turn_to_wait == 0)
				{
					if(player_two.current_block_id == 5)
						player_two.turn_to_wait = 3;
					else if(player_two.current_block_id == 15)
						player_two.turn_to_wait = 2;
				}

				++player_count;
			} 
		}

		else
		{
			if(player_one.turn_to_wait != 0)
				player_one.turn_to_wait = player_one.turn_to_wait - 1;
			if(player_one.turn_to_wait == 0)
				player_one.current_block_id = player_one.current_block_id + roll_dice();

			if(player_one.current_block_id >= 20)
			{
				player_one.current_block_id = player_one.current_block_id % 20;
				player_one.account = player_one.account + 10000;
			}

			show_board(board,player_one,player_two);

			if(board[player_one.current_block_id].type == TAX) // Vergi blogundasın paran yoksa satmak zorundasın //
			{
				player_one.account = player_one.account - board[player_one.current_block_id].rent;
				printf("You paid for the tax pay\n");
				printf("Your money -> %d\n",player_one.account);
					
				while(player_one.account < 0)
				{
					printf("Your money has run out...\n");
					printf("You have to press 7 to sell the property\n");
					
					n = menu_onGameplay();
						
					if(n == 7) // sells land if the money is not enough
						sell_property(board, &player_one);
				}
				++player_count;
			}

			else if(board[player_one.current_block_id].type == PROPERTY)
			{
				if(board[player_one.current_block_id].owner.type == NOONE)
				{
					printf("Would you like buy this property\n");
					printf("(If your answer is 'yes' press 1, ıf your answer is 'no' press 0): ");
					scanf("%d",&choice);
					if(choice == 1)
						buy_property(&board[player_one.current_block_id], &player_one);
				}

				else
				{
					house_count2 = board[player_one.current_block_id].house_count;

					if(house_count2 == 0)
					{
						player_one.account = player_one.account - board[player_one.current_block_id].rent;
						while(player_one.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();

							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_one);
						}
					}

					else if(house_count2 == 1)
					{
						player_one.account = player_one.account - board[player_one.current_block_id].rent_1;
						while(player_one.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_one);
						}						
					}	

					else if(house_count2 == 2)
					{
						player_one.account = player_one.account - board[player_one.current_block_id].rent_2;
						while(player_one.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_one);
						}						
					}

					else if(house_count2 == 3)
					{
						player_one.account = player_one.account - board[player_one.current_block_id].rent_3;
						while(player_one.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to press 7 to sell the property\n");
							
							n = menu_onGameplay();
								
							if(n == 7) // sells land if the money is not enough
								sell_property(board, &player_one);
						}						
					}
				}
				++player_count;
			}

			else if(board[player_one.current_block_id].type == PUNISH)
			{
				if(player_one.turn_to_wait == 0)
				{
					if(player_one.current_block_id == 5)
						player_one.turn_to_wait = 3;
					else if(player_one.current_block_id == 15)
						player_one.turn_to_wait = 2;
				}

				++player_count;
			} 
		}
	}
}

void build_house(block *current_block, player *current_player)
{
	int choice,i,isHave = 0;

		if(current_block->block_id == current_player->current_block_id)
			if(current_player->account >= current_block->house_price)
				isHave = 1;

	if(isHave == 1)
	{
		while(current_block->house_count != 3 && choice != 0)
		{
			printf("Would you like to build a house?: (Yes: 1, No: 0)");
			scanf("%d",&choice);

			if(choice != 0)
			{
				current_player->account = current_player->account - current_block->house_price;
				++current_block->house_count;
			}
		}
		if(current_block->house_count == 3)
			printf("The property has max capacity.\nYou can not do building...\n");
	}
	else
		printf("This property do not belong to you...\n");
}

void buy_property(block *current_block, player *current_player)
{
	int index = 0,choice,isHave = 0;

	if(current_block->type == PROPERTY)
	{
		if(current_block->owner.type == NOONE)
		{
			if(current_player->account > current_block->price)
			{
				while(current_player->owned_block_ids[index] != 0 && index <= 10)
				{
					++index;
				}
				current_player->owned_block_ids[index] = current_block->block_id;
				current_player->account = current_player->account - current_block->price;
				current_block->owner = *current_player;
				current_block->owner.type = current_player->type;
				printf("\nYou have bought %s\n",current_block->name);
				isHave = 1;
			}
			else
				printf("You can not afford to this property\n");
		}
		else
			printf("This block already have been bought\n");
	}
	else
		printf("This block is not property. You must not buy...\n");

	
	if(isHave == 1)
	{
		printf("\n\nWould you like building house?");
		printf("(If your answer is 'YES' press 1, If your answer is 'NO' press 0)\n\n");
		scanf("%d",&choice);

		if(choice == 1)
			build_house(current_block,current_player);
	}
}

void sell_property(block board[20], player *current_player)
{
	int index,choice,i,isHave = 0,flag = 0;

	for(i = 0 ; i < 11 ; ++i)
	{
		if(current_player->owned_block_ids[i] != 0)
		{
			index = current_player->owned_block_ids[i];

			printf("%d - %s\n",index,board[index].name);
			flag = 1;
		}
	}

	if(flag == 0)
	{
		printf("You do not have any property\n");
	}

	else if(flag == 1)
	{
		printf("\nYou have: \n");
		do
		{
			isHave = 0;
			index = -1;

			for(i = 0 ; i < 11 ; ++i)
				if(current_player->owned_block_ids[i] != 0)
					isHave = 1;

			if(isHave == 1)
			{
				printf("Please select property that you want to sell: (If you want to exit, you should press -1)\n");
				scanf("%d",&choice);

				if(choice != -1)
				{
					for(i = 0 ; i < 11 ; ++i)
						if(current_player->owned_block_ids[i] == board[choice].block_id)
							index = i;

					current_player->owned_block_ids[index] = 0;
					current_player->account = current_player->account + ( board[choice].price + (board[choice].house_count * board[choice].house_price) ) / 2;
					board[choice].owner.type = NOONE;
					board[choice].house_count = 0;
				}
				else
					isHave = 0;
			}

			else if(isHave == 0)
				printf("You do not have any property\n");

		}while(isHave == 1);
	}
}

int menu_onGameplay()
{
	int choice;

	printf("1- Roll the dice\n");
	printf("2- Show my account\n");
	printf("3- Show my property\n");
	printf("4- Show property deeds\n");
	printf("5- Buy property\n");
	printf("6- Buy House\n");
	printf("7- Sell property\n");
	printf("Please select an option to continue: ");
	scanf("%d",&choice);

	return choice;
}

int roll_dice()
{
	return (1 + rand() % 6);
}

void show_properties(block board[])
{
	int choice;
	int countBig,i,j=0;

	printf("Please select a property to see details: \n");
	printf("1- Esenyurt\n");
	printf("3- Tuzla\n");
	printf("4- Arnavutkoy\n");
	printf("6- Catalca\n");
	printf("7- Beykoz\n");
	printf("9- Maltepe\n");
	printf("11- Sisli\n");
	printf("13- Atasehir\n");
	printf("14- Sariyer\n");
	printf("16- Kadikoy\n");
	printf("17- Besiktas\n");
	printf("19- Bebek\n");
	printf("0- Exit\n");
	scanf("%d",&choice);

	if(choice != 0)
	{
		for(countBig = 0 ; countBig < 3 ; ++countBig)
		{
			if(countBig == 0)
			{
				printf("----------------------------------\n");
				printf("|");
				for(i = 0 ; i < 11 ; ++i)
					printf(" ");
				printf("%-11s",board[choice].name);
				for(i = 0 ; i < 10 ; ++i)
					printf(" ");
				printf("|\n----------------------------------\n");
			}
			else if(countBig == 2)
			{
				printf("----------------------------------\n|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("House Price");
				for(i = 0 ; i < 4 ; ++i)
					printf(" ");
				printf("%5d$    |\n",board[choice].house_price);
				printf("----------------------------------\n");
			}
			else
			{
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent%15d$     |\n",board[choice].rent);
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent 1 H%11d$     |\n",board[choice].rent_1);
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent 2 H%11d$     |\n",board[choice].rent_2);
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent 3 H%11d$     |\n",board[choice].rent_3);
			}

		}

	}
}

void show_board(block board[20], player player_one, player player_two) //21 bosluk
{
	int countBig,count,i,j,index = 0,flag = 0,temp_index;

	for(countBig = 0 ; countBig < 24 ; ++countBig)
	{
		if(countBig == 0)
		{
			for(i = 0 ; i < (22*6)+1 ; ++i)
					printf("-");
			printf("\n");
		}

		temp_index = index;

		if(countBig % 4 == 0)
		{
			for(count = 0 ; count < 6 ; ++count)
			{
				printf("|");
				for(i = 0 ; i < 6; ++i)
				{
					printf(" ");
				}

				if(index >= 6 && index <= 9)
				{
					printf("%-11s",board[25-index].name);

					for(j = 0 ; j < 4 ; ++j)
						printf(" ");

					printf("|");

					for(j = 0 ; j < (21*4)+(21/2) ; ++j)
					{
						if(j == (21*4)+3)
							printf("|");
						else
							printf(" ");
					}

					printf("%-11s",board[index].name);

					for(j = 0 ; j < 4 ; ++j)
						printf(" ");

					count = 6;
					flag = 1;
				}

				else if(index >= 10)
				{
					printf("%-11s",board[temp_index+5].name);
					for(j = 0 ; j < 4 ; ++j)
						printf(" ");
					--temp_index;
				}

				else
				{
					printf("%-11s",board[index].name);
					for(j = 0 ; j < 4 ; ++j)
						printf(" ");
				}
				++index;
			}
			printf("|\n");
			temp_index = index;
		}

		else if(countBig % 4 == 1)
		{
			if(flag == 0)
				index = index - 6;
			else if(flag == 1)
			{
				index = index - 1;
				flag = 0;
			}

			for(count = 0 ; count < 6 ; ++count)
			{
				printf("|");

				for(i = 0 ; i < 6 ; ++i)
					printf(" ");

				if(index >= 6 && index <= 9)
				{
					if(board[25-index].price != 0)
						printf("%5d$",board[25-index].price);
					else if(board[25-index].price == 0 && board[25-index].rent != 0 && index != 5 && index != 15)
						printf("%5d$",board[25-index].rent);
					else
						printf("      ");

					for(i = 0 ; i < 9 ; ++i)
						printf(" ");
					printf("|");

					for(i = 0 ; i < (21*4)+3 ; ++i)
						printf(" ");
					printf("|");

					for(i = 0 ; i < 6 ; ++i)
						printf(" ");

					if(board[index].price != 0)
						printf("%5d$",board[index].price);
					else if(board[index].price == 0 && board[index].rent != 0 && index != 5 && index != 15)
						printf("%5d$",board[index].rent);
					else
						printf("      ");

					++index;
					count = 6;
				}

				else if(index >= 10)
				{
					if(board[temp_index-1].price != 0)
						printf("%5d$",board[temp_index-1].price);
					else if(board[temp_index-1].price == 0 && board[temp_index-1].rent != 0 && (temp_index-1) != 5 && (temp_index-1) != 15)
						printf("%5d$",board[temp_index-1].rent);
					else
						printf("      ");
					--temp_index;
				}

				else
				{
					if(board[index].price != 0)
						printf("%5d$",board[index].price);
					else if(board[index].price == 0 && board[index].rent != 0 && index != 5 && index != 15)
						printf("%5d$",board[index].rent);
					else
						printf("      ");
					++index;
				}

				for(i = 0 ; i < 9 ; ++i)
					printf(" ");
			}
			printf("|\n");
		}

		else if(countBig % 4 == 2) // Algorithm for the cap and car //
		{
			if(countBig == 2)
				index = index - 6;
			else if(countBig != 22)
				index = index - 1;

			if(index >= 6 && index <= 9)
			{
				printf("|");
				for(i = 0 ; i < 6 ; ++i)
					printf(" ");
					
				if(25-index == player_one.current_block_id && 25-index == player_two.current_block_id)
					printf("%-5s%-10s",player_one.name,player_two.name);
				
				else if(25-index == player_one.current_block_id && 25-index != player_two.current_block_id)
					printf("%-15s",player_one.name);

				else if(25-index != player_one.current_block_id && 25-index == player_two.current_block_id)
					printf("%-15s",player_two.name);

				else if(25-index != player_one.current_block_id && 25-index != player_two.current_block_id)
					for(i = 0 ; i < 15 ; ++i)
						printf(" ");

				printf("|");
				for(i = 0 ; i < (22*4)-1 ; ++i)
					printf(" ");

				printf("|");
				for(i = 0 ; i < 6 ; ++i)
					printf(" ");

				if(index == player_one.current_block_id && index == player_two.current_block_id)
					printf("%-5s%-10s",player_one.name,player_two.name);
				
				else if(index == player_one.current_block_id && index != player_two.current_block_id)
					printf("%-15s",player_one.name);

				else if(index != player_one.current_block_id && index == player_two.current_block_id)
					printf("%-15s",player_two.name);

				else if(index != player_one.current_block_id && index != player_two.current_block_id)
					for(i = 0 ; i < 15 ; ++i)
						printf(" ");
				++index;
			}

			else if(index >= 10)
			{
				temp_index = index + 5;

				for(count = 0 ; count < 6 ; ++count)
				{
					printf("|");
					for(i = 0 ; i < 6 ; ++i)
						printf(" ");
					
					if(temp_index == player_one.current_block_id && temp_index == player_two.current_block_id)
						printf("%-5s%-10s",player_one.name,player_two.name);
				
					else if(temp_index == player_one.current_block_id && temp_index != player_two.current_block_id)
						printf("%-15s",player_one.name);

					else if(temp_index != player_one.current_block_id && temp_index == player_two.current_block_id)
						printf("%-15s",player_two.name);

					else if(temp_index != player_one.current_block_id && temp_index != player_two.current_block_id)
						for(i = 0 ; i < 15 ; ++i)
							printf(" ");
					--temp_index;
				}
			}

			else
			{
				for(count = 0 ; count < 6 ; ++count)
				{
					printf("|");
					for(i = 0 ; i < 6 ; ++i)
						printf(" ");

					if(index == player_one.current_block_id && index == player_two.current_block_id)
						printf("%-5s%-10s",player_one.name,player_two.name);
				
					else if(index == player_one.current_block_id && index != player_two.current_block_id)
						printf("%-15s",player_one.name);

					else if(index != player_one.current_block_id && index == player_two.current_block_id)
						printf("%-15s",player_two.name);

					else if(index != player_one.current_block_id && index != player_two.current_block_id)
						for(i = 0 ; i < 15 ; ++i)
							printf(" ");
					++index;
				}
			}
			printf("|\n");
		}

		else if(countBig % 4 == 3)
		{
			if(countBig == 3 || countBig == 19 || countBig == 23)
			{
				for(i = 0 ; i < (22*6)+1 ; ++i)
					printf("-");
			}

			else
			{
				for(i = 0 ; i < 23 ; ++i)
					printf("-");
				for(i = 0 ; i < (22*4)-1 ; ++i)
					printf(" ");
				for(i = 0 ; i < 23 ; ++i)
					printf("-");
			}
			printf("\n");
		}
	}
}

void init_the_board(block board[20])
{
	int i;

	board[0].block_id = 0;
	board[0].name = "Start";
	board[0].price = 0;
	board[0].rent = 0;
	board[0].rent_1 = 0;
	board[0].rent_2 = 0;
	board[0].rent_3 = 0;
	board[0].house_price = 0;
	board[0].house_count = 0;
	board[0].owner.type = NOONE;
	board[0].type = START;
	board[1].block_id = 1;
	board[1].name = "Esenyurt";
	board[1].price = 16000;
	board[1].rent = 800;
	board[1].rent_1 = 4000;
	board[1].rent_2 = 9000;
	board[1].rent_3 = 25000;
	board[1].house_price = 10000;
	board[1].house_count = 0;
	board[1].owner.type = NOONE;
	board[1].type = PROPERTY;
	board[2].block_id = 2;
	board[2].name = "Car Park";
	board[2].price = 0;
	board[2].rent = 1500;
	board[2].rent_1 = 0;
	board[2].rent_2 = 0;
	board[2].rent_3 = 0;
	board[2].house_price = 0;
	board[2].house_count = 0;
	board[2].owner.type = NOONE;
	board[2].type = TAX;
	board[3].block_id = 3;
	board[3].name = "Tuzla";
	board[3].price = 16500;
	board[3].rent = 850;
	board[3].rent_1 = 4250;
	board[3].rent_2 = 9500;
	board[3].rent_3 = 26000;
	board[3].house_price = 12000;
	board[3].house_count = 0;
	board[3].owner.type = NOONE;
	board[3].type = PROPERTY;
	board[4].block_id = 4;
	board[4].name = "Arnavutkoy";
	board[4].price = 17000;
	board[4].rent = 875;
	board[4].rent_1 = 4500;
	board[4].rent_2 = 10000;
	board[4].rent_3 = 28000;
	board[4].house_price = 12000;
	board[4].house_count = 0;
	board[4].owner.type = NOONE;
	board[4].type = PROPERTY;
	board[5].block_id = 5;
	board[5].name = "Wait 2 Turn";
	board[5].price = 0;
	board[5].rent = 2;
	board[5].rent_1 = 0;
	board[5].rent_2 = 0;
	board[5].rent_3 = 0;
	board[5].house_price = 0;
	board[5].house_count = 0;
	board[5].owner.type = NOONE;
	board[5].type = PUNISH;
	board[6].block_id = 6;
	board[6].name = "Catalca";
	board[6].price = 20000;
	board[6].rent = 1000;
	board[6].rent_1 = 5000;
	board[6].rent_2 = 12000;
	board[6].rent_3 = 30000;
	board[6].house_price = 13000;
	board[6].house_count = 0;
	board[6].owner.type = NOONE;
	board[6].type = PROPERTY;
	board[7].block_id = 7;
	board[7].name = "Beykoz";
	board[7].price = 23000;
	board[7].rent = 1100;
	board[7].rent_1 = 5500;
	board[7].rent_2 = 12500;
	board[7].rent_3 = 33000;
	board[7].house_price = 13000;
	board[7].house_count = 0;
	board[7].owner.type = NOONE;
	board[7].type = PROPERTY;
	board[8].block_id = 8;
	board[8].name = "Car Fix";
	board[8].price = 0;
	board[8].rent = 1750;
	board[8].rent_1 = 0;
	board[8].rent_2 = 0;
	board[8].rent_3 = 0;
	board[8].house_price = 0;
	board[8].house_count = 0;
	board[8].owner.type = NOONE;
	board[8].type = TAX;
	board[9].block_id = 9;
	board[9].name = "Maltepe";
	board[9].price = 30000;
	board[9].rent = 1350;
	board[9].rent_1 = 7000;
	board[9].rent_2 = 15000;
	board[9].rent_3 = 40000;
	board[9].house_price = 15000;
	board[9].house_count = 0;
	board[9].owner.type = NOONE;
	board[9].type = PROPERTY;
	board[10].block_id = 10;
	board[10].name = "Bills";
	board[10].price = 0;
	board[10].rent = 2000;
	board[10].rent_1 = 0;
	board[10].rent_2 = 0;
	board[10].rent_3 = 0;
	board[10].house_price = 0;
	board[10].house_count = 0;
	board[10].owner.type = NOONE;
	board[10].type = TAX;
	board[11].block_id = 11;
	board[11].name = "Sisli";
	board[11].price = 33000;
	board[11].rent = 1500;
	board[11].rent_1 = 8000;
	board[11].rent_2 = 16000;
	board[11].rent_3 = 42000;
	board[11].house_price = 16000;
	board[11].house_count = 0;
	board[11].owner.type = NOONE;
	board[11].type = PROPERTY;
	board[12].block_id = 12;
	board[12].name = "Oil";
	board[12].price = 0;
	board[12].rent = 2250;
	board[12].rent_1 = 0;
	board[12].rent_2 = 0;
	board[12].rent_3 = 0;
	board[12].house_price = 0;
	board[12].house_count = 0;
	board[12].owner.type = NOONE;
	board[12].type = TAX;
	board[13].block_id = 13;
	board[13].name = "Atasehir";
	board[13].price = 35000;
	board[13].rent = 1600;
	board[13].rent_1 = 8500;
	board[13].rent_2 = 17000;
	board[13].rent_3 = 45000;
	board[13].house_price = 17000;
	board[13].house_count = 0;
	board[13].owner.type = NOONE;
	board[13].type = PROPERTY;
	board[14].block_id = 14;
	board[14].name = "Sariyer";
	board[14].price = 40000;
	board[14].rent = 1750;
	board[14].rent_1 = 9500;
	board[14].rent_2 = 19000;
	board[14].rent_3 = 48000;
	board[14].house_price = 19000;
	board[14].house_count = 0;
	board[14].owner.type = NOONE;
	board[14].type = PROPERTY;
	board[15].block_id = 15;
	board[15].name = "Wait 1 Turn";
	board[15].price = 0;
	board[15].rent = 1;
	board[15].rent_1 = 0;
	board[15].rent_2 = 0;
	board[15].rent_3 = 0;
	board[15].house_price = 0;
	board[15].house_count = 0;
	board[15].owner.type = NOONE;
	board[15].type = PUNISH;
	board[16].block_id = 16;
	board[16].name = "Kadikoy";
	board[16].price = 43000;
	board[16].rent = 1900;
	board[16].rent_1 = 11000;
	board[16].rent_2 = 21500;
	board[16].rent_3 = 55000;
	board[16].house_price = 23000;
	board[16].house_count = 0;
	board[16].owner.type = NOONE;
	board[16].type = PROPERTY;
	board[17].block_id = 17;
	board[17].name = "Besiktas";
	board[17].price = 60000;
	board[17].rent = 2500;
	board[17].rent_1 = 15000;
	board[17].rent_2 = 28000;
	board[17].rent_3 = 60000;
	board[17].house_price = 30000;
	board[17].house_count = 0;
	board[17].owner.type = NOONE;
	board[17].type = PROPERTY;
	board[18].block_id = 18;
	board[18].name = "Vocation";
	board[18].price = 0;
	board[18].rent = 5000;
	board[18].rent_1 = 0;
	board[18].rent_2 = 0;
	board[18].rent_3 = 0;
	board[18].house_price = 0;
	board[18].house_count = 0;
	board[18].owner.type = NOONE;
	board[18].type = TAX;
	board[19].block_id = 19;
	board[19].name = "Bebek";
	board[19].price = 70000;
	board[19].rent = 3500;
	board[19].rent_1 = 20000;
	board[19].rent_2 = 35500;
	board[19].rent_3 = 65000;
	board[19].house_price = 35000;
	board[19].house_count = 0;
	board[19].owner.type = NOONE;
	board[19].type = PROPERTY;
}