/*
	Muharrem Ozan Yeşiller
		171044011
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef enum
{
	NOONE,CAP,CAR
}player_type;

typedef enum
{
	START,PROPERTY,TAX,PUNISH,FORTUNE
}block_type;

typedef enum
{
	FREEHOUSE,TIMETRAVEL,GARNISHMENT,GENEROSITY,TREASUREHUNTER
}typeof_fortune_card;

typedef struct 
{
	typeof_fortune_card Type;
}fortune_card;

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

typedef struct node
{
	block data;
	struct node *next;
}node;

int menu_onGameplay();
int roll_dice();
int isCrash(player player_one, player player_two); // I checked bankruptcy //
void init_the_board(block board[]);
void show_board(node *head, player player_one, player player_two);
void show_properties(node *head);
void buy_property(node *current_block, player *current_player);
void sell_property(node *head, player *current_player);
void gameplay(node *head, player player_one, player player_two);
void build_house(node *current_block, player *current_player); // for building house //
node *init_theboard_linkedList(block board[], node *head); // for the return my linked listed //
node *get(node *head, int index); // for the access index. node of my linked list //

void main()
{
	srand(time(NULL));

	block board[24];
	player player_one,player_two;
	node *head = NULL;

	init_the_board(board);
	head = init_theboard_linkedList(board,head); //fill linked list with the aid of board array //

	gameplay(head,player_one,player_two);
}

int isCrash(player player_one, player player_two) //bankruptcy test//
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

void gameplay(node *head, player player_one, player player_two)
{
	int n,player_count = 1,i,flag = 0;
	int choice,house_count2,isPunish = 0;
	int rolled,ind = 0,choice_freehouse,sum = 0,property_count = 0,test_count = 0;
	block current_block;
	player current_player;
	fortune_card card;

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

	show_board(head,player_one,player_two);

	while(isCrash(player_one,player_two)) // Testing for the bankruptcy //
	{
		//show_board(head,player_one,player_two);

		do
		{
			if(player_count % 2 == 0)
			{
				printf("%s","\e[5;31;47m CAR GAMİNG... \e[0m\n");
				n = menu_onGameplay();
			}
			else if(player_count % 2 == 1)
			{
				printf("%s","\e[5;31;47m CAP GAMİNG... \e[0m\n");
				n = 1;
			}

			switch(n)
			{
				case 1:
					break;

				case 2:
					if(player_count % 2 == 0)
						printf("..........You have %d$..........\n",player_two.account);
					break;

				case 3:
					printf("\nYour properties are: \n");
					if(player_count % 2 == 0)
					{
						for(i = 0 ; i < 11 ; ++i)
							if(player_two.owned_block_ids[i] != 0)
							{
								printf("%d- %s\n",(i+1),get(head,player_two.owned_block_ids[i])->data.name);
								flag = 1;
							}

						if(flag == 0)
							printf("You do not have to any property\n\n");
					}
					break;

				case 4:
					show_properties(head);
					break;

				case 5:
					if(player_count % 2 == 0)
						buy_property(get(head,player_two.current_block_id), &player_two);
					break;

				case 6:
					if(player_count % 2 == 0)
						build_house(get(head,player_two.current_block_id), &player_two);
					break;

				case 7:
					if(player_count % 2 == 0)
						sell_property(head, &player_two);
					break;
				default:
					printf("INVALID CHOİCE...\n");
					break;
			}

		}while(n != 1);
		/*
			before the dice throw the menu and so on.
		*/
		
		// Start my algorithm after player roll the dice //
		if(player_count % 2 == 0)
		{
			if(player_two.turn_to_wait != 0) // if the user is penalized //
				player_two.turn_to_wait = player_two.turn_to_wait - 1;
			if(player_two.turn_to_wait == 0)  // if the user is not penalized //
			{
				rolled = roll_dice();
				player_two.current_block_id = player_two.current_block_id + rolled;
				printf("Player rolled the dice: %d\n",rolled);
			}

			if(player_two.current_block_id >= 24) // if the current position exceeds 24, this means that I have passed. //
			{
				player_two.current_block_id = player_two.current_block_id % 24;
				player_two.account = player_two.account + 10000;
			}

			show_board(head,player_one,player_two);

			if(get(head,player_two.current_block_id)->data.type == TAX) // If the player two at the tax block //
			{
				player_two.account = player_two.account - get(head,player_two.current_block_id)->data.rent;
				printf("You paid for the tax pay\n");
				printf("Your money -> %d\n",player_two.account); // reminding //
					
				if(player_two.account < 0)
				{
					printf("Your money has run out...\n");
					printf("You have to sell the property\n");

					for(i = 0 ; i < 11 ; ++i) // If it can not pay //
					{		
						sell_property(head, &player_two);
						if(player_two.account > 0)
							i = 12;
					}
				}
				++player_count;
			}

			else if(get(head,player_two.current_block_id)->data.type == PROPERTY)
			{
				if(get(head,player_two.current_block_id)->data.owner.type == NOONE)
				{
					printf("Would you like buy this property?\n");
					printf("1)Yes\n2)No\nYour choice: ");
					scanf("%d",&choice);
					if(choice == 1)
						buy_property(get(head,player_two.current_block_id), &player_two);
				}

				else if(get(head,player_two.current_block_id)->data.owner.type != player_two.type)// if player comes to a property that is not its //
				{
					house_count2 = get(head,player_two.current_block_id)->data.house_count;

					if(house_count2 == 0)
					{
						player_two.account = player_two.account - get(head,player_two.current_block_id)->data.rent;
						if(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_two);
								if(player_two.account > 0)
									i = 12;
							}
						}
					}

					else if(house_count2 == 1)
					{
						player_two.account = player_two.account - get(head,player_two.current_block_id)->data.rent_1;
						if(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_two);
								if(player_two.account > 0)
									i = 12;
							}
						}						
					}	

					else if(house_count2 == 2)
					{
						player_two.account = player_two.account - get(head,player_two.current_block_id)->data.rent_2;
						if(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_two);
								if(player_two.account > 0)
									i = 12;
							}
						}						
					}

					else if(house_count2 == 3)
					{
						player_two.account = player_two.account - get(head,player_two.current_block_id)->data.rent_3;
						if(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_two);
								if(player_two.account > 0)
									i = 12;
							}
						}						
					}
				}
				++player_count;
			}

			else if(get(head,player_two.current_block_id)->data.type == PUNISH)
			{
				if(player_two.turn_to_wait == 0)
				{
					if(player_two.current_block_id == 6)
						player_two.turn_to_wait = 3;
					else if(player_two.current_block_id == 18)
						player_two.turn_to_wait = 2;
				}

				++player_count;
			}

			else if(get(head,player_two.current_block_id)->data.type == FORTUNE)
			{
				card.Type = rand() % 5;

				if(card.Type == FREEHOUSE)
				{
					printf("Your owned properties: \n");
					for(i = 0 ; i < 11 ; ++i)
					{
						if(player_two.owned_block_ids[i] != 0)
							printf("%d -> %s\n",i,get(head,player_two.owned_block_ids[i])->data.name);
					}

					printf("Choose it: ");
					scanf("%d",&choice_freehouse);
					get(head,choice)->data.house_count += 1;
				}

				else if(card.Type == TIMETRAVEL)
				{
					rolled = 1 + rand() % 6;

					if(rolled >= 1 && rolled <= 3)
					{
						player_two.current_block_id = player_two.current_block_id + 2;
						player_two.turn_to_wait = 0;
						printf("\n\n!!!!You go along 2 blocks!!!!\n\n");
						//show_board(head,player_one,player_two);
					}

					else
					{
						player_two.current_block_id = player_two.current_block_id - 2;
						player_two.turn_to_wait = 0;
						printf("\n\n!!!!You go back 2 blocks!!!!\n\n");
						//show_board(head,player_one,player_two);
					}

					if(get(head,player_two.current_block_id)->data.type == PUNISH)
					{
						if(player_two.current_block_id == 6)
							player_two.turn_to_wait = 3;

						else if(player_two.current_block_id == 18)
							player_two.turn_to_wait = 2;
					}

					else if(get(head,player_two.current_block_id)->data.type == TAX)
					{
						player_two.account = player_two.account - get(head,player_two.current_block_id)->data.rent;
						printf("You paid for the tax pay\n");
						printf("Your money -> %d\n",player_two.account);
						if(player_two.account < 0)
						{
							printf("Your money has run out...\n");
							printf("You have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_two);
								if(player_two.account > 0)
									i = 12;
							}
						}
					}
				}

				else if(card.Type == GARNISHMENT)
				{
					printf("\n\nYou lost 5000$\n\n");
					player_two.account = player_two.account - 5000;
					if(player_two.account < 0)
					{
						printf("Your money has run out...\n");
						printf("You have to sell the property\n");

						for(i = 0 ; i < 11 ; ++i) // If it can not pay //
						{		
							sell_property(head, &player_two);
							if(player_two.account > 0)
								i = 12;
						}
					}
				}

				else if(card.Type == GENEROSITY)
				{
					printf("\n\n!!!Computer won 10 000$ from player!!!!\n\n");
					player_two.account = player_two.account - 10000;
					player_one.account = player_one.account + 10000;
					if(player_two.account < 0)
					{
						printf("Your money has run out...\n");
						printf("You have to sell the property\n");

						for(i = 0 ; i < 11 ; ++i) // If it can not pay //
						{		
							sell_property(head, &player_two);
							if(player_two.account > 0)
								i = 12;
						}
					}
				}

				else if(card.Type == TREASUREHUNTER)
				{
					printf("\n\n!!!!You won 20 000$!!!\n\n");
					player_two.account = player_two.account + 20000;
				}

				++player_count;
			} 
		}

		else // AI Algorithm //
		{
			if(player_one.turn_to_wait != 0)
				player_one.turn_to_wait = player_one.turn_to_wait - 1;
			if(player_one.turn_to_wait == 0)
			{
				rolled = roll_dice();
				player_one.current_block_id = player_one.current_block_id + rolled;
				printf("Computer rolled the dice: %d\n",rolled);
			}

			if(player_one.current_block_id >= 24)
			{
				player_one.current_block_id = player_one.current_block_id % 24;
				player_one.account = player_one.account + 10000;
			}

			show_board(head,player_one,player_two);

			if(get(head,player_one.current_block_id)->data.type == TAX)
			{
				player_one.account = player_one.account - get(head,player_one.current_block_id)->data.rent;
				printf("Computer paid for the tax pay\n");
				printf("Computer money -> %d\n",player_one.account);
					
				if(player_one.account < 0)
				{
					printf("Computer money has run out...\n");

					for(i = 0 ; i < 11 ; ++i) // If it can not pay //
					{		
						sell_property(head, &player_one);
						if(player_one.account > 0)
							i = 12;
					}
				}
				++player_count;
			}

			else if(get(head,player_one.current_block_id)->data.type == PROPERTY)
			{
				if(get(head,player_one.current_block_id)->data.owner.type == NOONE) // purchase algorithm of AI //
				{
					for(i = 0 ; i < 24 ; ++i)
					{
						if(get(head,i)->data.type == PROPERTY)
						{
							sum = sum + get(head,i)->data.price;
							++property_count;
						}
					}
					sum = sum / property_count;

					if(sum <= get(head,player_one.current_block_id)->data.price) // sum is avarage price all properties //
					{
						if(get(head,player_one.current_block_id)->data.price <= player_one.account)
						{
							get(head,player_one.current_block_id)->data.owner = player_one;
							
							i = 0;
							while(player_one.owned_block_ids[i] != 0)
								++i;

							player_one.owned_block_ids[i] = get(head,player_one.current_block_id)->data.block_id;
							player_one.account = player_one.account - get(head,player_one.current_block_id)->data.price;
							printf("Computer has bought %s\n",get(head,player_one.current_block_id)->data.name);
						}
					}

					else
					{
						rolled = 1 + rand() % 6;
						if(rolled >= 1 && rolled <= 3)
						{
							get(head,player_one.current_block_id)->data.owner = player_one;
							
							i = 0;
							while(player_one.owned_block_ids[i] != 0)
								++i;

							player_one.owned_block_ids[i] = get(head,player_one.current_block_id)->data.block_id;
							player_one.account = player_one.account - get(head,player_one.current_block_id)->data.price;
						}
					}
				}

				else if(get(head,player_one.current_block_id)->data.owner.type == player_one.type)
				{
					for(i = 0 ; i < 24 ; ++i)
						if(get(head,i)->data.type == PROPERTY)
							++property_count;

					for(i = 0 ; i < 11 ; ++i)
						if(player_one.owned_block_ids[i] != 0)
							++test_count;

					if(test_count >= (property_count/3))
					{
						rolled = 1 + rand() % 6;

						if(rolled >= 1 && rolled <= 3)
							get(head,player_one.current_block_id)->data.house_count += 1;
					}
				}

				else if(get(head,player_one.current_block_id)->data.owner.type == player_two.type)
				{
					house_count2 = get(head,player_one.current_block_id)->data.house_count;

					if(house_count2 == 0)
					{
						player_one.account = player_one.account - get(head,player_one.current_block_id)->data.rent;
						if(player_one.account < 0)
						{
							printf("Computer money has run out...\n");
							printf("Computer have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_one);
								if(player_one.account > 0)
									i = 12;
							}
						}
					}

					else if(house_count2 == 1)
					{
						player_one.account = player_one.account - get(head,player_one.current_block_id)->data.rent_1;
						if(player_one.account < 0)
						{
							printf("Computer money has run out...\n");
							printf("Computer have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_one);
								if(player_one.account > 0)
									i = 12;
							}
						}					
					}	

					else if(house_count2 == 2)
					{
						player_one.account = player_one.account - get(head,player_one.current_block_id)->data.rent_2;
						if(player_one.account < 0)
						{
							printf("Computer money has run out...\n");
							printf("Computer have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_one);
								if(player_one.account > 0)
									i = 12;
							}
						}						
					}

					else if(house_count2 == 3)
					{
						player_one.account = player_one.account - get(head,player_one.current_block_id)->data.rent_3;
						if(player_one.account < 0)
						{
							printf("Computer money has run out...\n");
							printf("Computer have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_one);
								if(player_one.account > 0)
									i = 12;
							}
						}						
					}
				}
				++player_count;
			}

			else if(get(head,player_one.current_block_id)->data.type == PUNISH)
			{
				if(player_one.turn_to_wait == 0)
				{
					if(player_one.current_block_id == 6)
						player_one.turn_to_wait = 3;
					else if(player_one.current_block_id == 18)
						player_one.turn_to_wait = 2;
				}

				++player_count;
			}

			else if(get(head,player_one.current_block_id)->data.type == FORTUNE)
			{
				card.Type = 1 + rand() % 5;

				if(card.Type == FREEHOUSE)
				{
					while(player_one.owned_block_ids[ind] != 0 && get(head,ind)->data.house_count != 3)
						++ind;

					get(head,ind)->data.house_count += 1;
				}

				else if(card.Type == TIMETRAVEL)
				{
					rolled = 1 + rand() % 6;

					if(rolled >= 1 && rolled <= 3)
					{
						player_two.current_block_id = player_two.current_block_id + 2;
						player_two.turn_to_wait = 0;
						printf("\n\n!!!! Computer go along 2 blocks !!!!\n\n");
					}
					else
					{
						player_two.current_block_id = player_two.current_block_id - 2;
						player_two.turn_to_wait = 0;
						printf("\n\n!!!! Computer go back 2 blocks !!!!\n\n");
					}

					if(get(head,player_one.current_block_id)->data.type == PUNISH)
					{
						if(player_one.current_block_id == 6)
							player_one.turn_to_wait = 3;
						else if(player_one.current_block_id == 18)
							player_one.turn_to_wait = 2;
					}

					else if(get(head,player_one.current_block_id)->data.type == TAX)
					{
						player_one.account = player_one.account - get(head,player_one.current_block_id)->data.rent;
						printf("Computer paid for the tax pay\n");
						printf("Computer money -> %d\n",player_one.account);
						if(player_one.account < 0)
						{
							printf("Computer money has run out...\n");
							printf("Computer have to sell the property\n");

							for(i = 0 ; i < 11 ; ++i) // If it can not pay //
							{		
								sell_property(head, &player_one);
								if(player_one.account > 0)
									i = 12;
							}
						}
					}
				}

				else if(card.Type == GARNISHMENT)
				{
					printf("\n\n!!!! Computer lost 5000$\n\n");
					player_one.account = player_one.account - 5000;
					if(player_one.account < 0)
					{
						printf("Computer money has run out...\n");
						printf("Computer have to sell the property\n");

						for(i = 0 ; i < 11 ; ++i) // If it can not pay //
						{		
							sell_property(head, &player_one);
							if(player_one.account > 0)
								i = 12;
						}
					}
				}

				else if(card.Type == GENEROSITY)
				{
					printf("\n\n!!!! Player earn 10 000$ from Computer !!!!\n\n");
					player_one.account = player_one.account - 10000;
					player_two.account = player_two.account + 10000;
					if(player_one.account < 0)
					{
						printf("Computer money has run out...\n");
						printf("Computer have to sell the property\n");

						for(i = 0 ; i < 11 ; ++i) // If it can not pay //
						{		
							sell_property(head, &player_one);
							if(player_one.account > 0)
								i = 12;
						}
					}
				}

				else if(card.Type == TREASUREHUNTER)
				{
					printf("\n\n!!!! Computer earn 20 000$ !!!!\n\n");
					player_one.account = player_one.account + 20000;
				}

				++player_count;
			}
		}
	}
}

void build_house(node *current_block, player *current_player)
{
	int choice,i,isHave = 0;

		if(current_block->data.block_id == current_player->current_block_id)
			if(current_player->account >= current_block->data.house_price)
				isHave = 1;

	if(isHave == 1)
	{
		while(current_block->data.house_count != 3 && choice != 0)
		{
			printf("Would you like to build a house?: (Yes: 1, No: 0)");
			scanf("%d",&choice);

			if(choice != 0)
			{
				current_player->account = current_player->account - current_block->data.house_price;
				++current_block->data.house_count;
			}
		}
		if(current_block->data.house_count == 3)
			printf("The property has max capacity.\nYou can not do building...\n");
	}
	else
		printf("This property do not belong to you...\n");
}

void buy_property(node *current_block, player *current_player)
{
	int index = 0,choice,isHave = 0;

	if(current_block->data.type == PROPERTY)
	{
		if(current_block->data.owner.type == NOONE)
		{
			if(current_player->account > current_block->data.price)
			{
				while(current_player->owned_block_ids[index] != 0 && index <= 10)
				{
					++index;
				}
				current_player->owned_block_ids[index] = current_block->data.block_id;
				current_player->account = current_player->account - current_block->data.price;
				current_block->data.owner = *current_player;
				current_block->data.owner.type = current_player->type;
				printf("\nYou have bought %s\n",current_block->data.name);
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

void sell_property(node *head, player *current_player)
{
	int index,choice,i,isHave = 0,flag = 0;
	int max_prop;

	printf("\nYou have: \n");
	for(i = 0 ; i < 11 ; ++i)
	{
		if(current_player->owned_block_ids[i] != 0)
		{
			index = current_player->owned_block_ids[i];

			printf("%d - %s\n",index,get(head,index)->data.name);
			flag = 1;
		}
	}

	if(flag == 0)
	{
		printf("You do not have any property\n");
	}

	else if(flag == 1)
	{
		if(current_player->type == CAR) // for the player //
		{
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
						{
							if(current_player->owned_block_ids[i] == get(head,choice)->data.block_id)
							{
								index = i;
								i = 12;
							}
						}

						current_player->owned_block_ids[index] = 0;
						current_player->account = current_player->account + ( get(head,choice)->data.price + (get(head,choice)->data.house_count * get(head,choice)->data.house_price) ) / 2;
						get(head,choice)->data.owner.type = NOONE;
						get(head,choice)->data.house_count = 0;
						//board[choice].owner.type = NOONE;
						//board[choice].house_count = 0;
						printf("You have sold %s\n",get(head,choice)->data.name);
						flag = 0;
					}
					else
					{
						flag = 0;
						isHave = 0;
					}
				}

				else if(isHave == 0)
					printf("You do not have any property\n");

			}while(isHave == 1 && flag == 1);
		}

		else if(current_player->type == CAP) // for the AI //
		{
			max_prop = current_player->owned_block_ids[0];

			for(i = 0 ; i < 11 ; ++i)
			{
				if(max_prop < current_player->owned_block_ids[i])
					max_prop = current_player->owned_block_ids[i];
			}
			printf("Computer has sold %s\n",get(head,max_prop)->data.name);
			current_player->owned_block_ids[max_prop] = 0;
			current_player->account = current_player->account + ( get(head,max_prop)->data.price + (get(head,max_prop)->data.house_count * get(head,max_prop)->data.house_price) ) / 2;
			get(head,max_prop)->data.owner.type = NOONE;
			get(head,max_prop)->data.house_count = 0;
		}
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

void show_properties(node *head)
{
	int choice;
	int countBig,i,j=0;

	printf("Please select a property to see details: \n");
	printf("1- Esenyurt\n");
	printf("4- Tuzla\n");
	printf("5- Arnavutkoy\n");
	printf("7- Catalca\n");
	printf("8- Beykoz\n");
	printf("11- Maltepe\n");
	printf("13- Sisli\n");
	printf("16- Atasehir\n");
	printf("17- Sariyer\n");
	printf("19- Kadikoy\n");
	printf("20- Besiktas\n");
	printf("23- Bebek\n");
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
				printf("%-11s",get(head,choice)->data.name);
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
				printf("%5d$    |\n",get(head,choice)->data.house_price);
				printf("----------------------------------\n");
			}
			else
			{
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent%15d$     |\n",get(head,choice)->data.rent);
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent 1 H%11d$     |\n",get(head,choice)->data.rent_1);
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent 2 H%11d$     |\n",get(head,choice)->data.rent_2);
				printf("|");
				for(i = 0 ; i < 7 ; ++i)
					printf(" ");
				printf("Rent 3 H%11d$     |\n",get(head,choice)->data.rent_3);
			}

		}

	}
}

void show_board(node *head, player player_one, player player_two)
{
	int countBig,count,i,j,index = 0,flag = 0,temp_index;

	for(countBig = 0 ; countBig < 28 ; ++countBig) // 28 kere
	{
		if(countBig == 0)
		{
			for(i = 0 ; i < (23*7)+1 ; ++i)
				printf("-");

			printf("\n");
		}

		temp_index = index;

		if(countBig % 4 == 0) // Algorithm for the name //
		{
			for(count = 0 ; count < 7 ; ++count)
			{
				printf("|");
				for(i = 0 ; i < 6 ; ++i)
					printf(" ");

				if(index >= 7 && index <= 11)
				{
					printf("%-12s",get(head,30-index)->data.name);

					for(i = 0 ; i < 4 ; ++i)
						printf(" ");
					printf("|");

					for(j = 0 ; j < (21*5)+(21/2) ; ++j)
					{
						if(j == (22*5)+4)
							printf("|");
						else
							printf(" ");
					}

					printf("      %-12s",get(head,index)->data.name);

					for(i = 0 ; i < 4 ; ++i)
						printf(" ");

					count = 10; // For loop turn 1 times //
					flag = 1; // At the printing price/rent, for the fixing to index //
				}

				else if(index >= 12)
				{
					printf("%-12s",get(head,temp_index+6)->data.name);
					for(i = 0 ; i < 4 ; ++i)
						printf(" ");
					--temp_index;
				}

				else
				{
					printf("%-12s",get(head,index)->data.name);
					for(i = 0 ; i < 4 ; ++i)
						printf(" ");
				}
				++index;
			}
			printf("|\n");
			temp_index = index;
		}

		else if(countBig % 4 == 1) // Algorithm for the price/rent //
		{
			if(flag == 0)
				index = index - 7;
			else if(flag == 1)
			{
				index = index - 1;
				flag = 0;
			}

			for(count = 0 ; count < 7 ; ++count)
			{
				printf("|");

				for(i = 0 ; i < 6 ; ++i)
					printf(" ");

				if(index >= 7 && index <= 11)
				{
					if(get(head,30-index)->data.price != 0)
						printf("%5d$",get(head,30-index)->data.price);
					else if(get(head,30-index)->data.price == 0 && get(head,30-index)->data.rent != 0 && index != 6 && index != 18)
						printf("%5d$",get(head,30-index)->data.rent);
					else
						printf("      ");

					for(i = 0 ; i < 10 ; ++i)
						printf(" ");
					printf("|");

					for(i = 0 ; i < (23*5)-1 ; ++i)
						printf(" ");
					printf("|");

					for(i = 0 ; i < 6 ; ++i)
						printf(" ");

					if(get(head,index)->data.price != 0)
						printf(" %5d$",get(head,index)->data.price);
					else if(get(head,index)->data.price == 0 && get(head,index)->data.rent != 0 && index != 6 && index != 18)
						printf(" %5d$",get(head,index)->data.rent);
					else
						printf("       ");

					++index;
					count = 10; // For loop turn 1 times //
				}

				else if(index >= 12)
				{
					if(get(head,temp_index-1)->data.price != 0)
						printf(" %5d$",get(head,temp_index-1)->data.price);
					else if(get(head,temp_index-1)->data.price == 0 && get(head,temp_index-1)->data.rent != 0 && (temp_index-1) != 6 && (temp_index-1) != 18)
						printf(" %5d$",get(head,temp_index-1)->data.rent);
					else
						printf("       ");
					--temp_index;
				}

				else
				{
					if(get(head,index)->data.price != 0)
						printf(" %5d$",get(head,index)->data.price);
					else if(get(head,index)->data.price == 0 && get(head,index)->data.rent != 0 && index != 6 && index != 18)
						printf(" %5d$",get(head,index)->data.rent);
					else
						printf("       ");
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
				index = index - 7;
			else if(countBig != 26)
				index = index - 1;

			if(index >= 7 && index <= 11)
			{
				printf("|");
				for(i = 0 ; i < 6 ; ++i)
					printf(" ");
					
				if(30-index == player_one.current_block_id && 30-index == player_two.current_block_id)
					printf("%-6s%-10s",player_one.name,player_two.name);
				
				else if(30-index == player_one.current_block_id && 30-index != player_two.current_block_id)
					printf("%-16s",player_one.name);

				else if(30-index != player_one.current_block_id && 30-index == player_two.current_block_id)
					printf("%-16s",player_two.name);

				else if(30-index != player_one.current_block_id && 30-index != player_two.current_block_id)
					for(i = 0 ; i < 16 ; ++i)
						printf(" ");

				printf("|");
				for(i = 0 ; i < (23*5)-1 ; ++i)
					printf(" ");

				printf("|");
				for(i = 0 ; i < 6 ; ++i)
					printf(" ");

				if(index == player_one.current_block_id && index == player_two.current_block_id)
					printf("%-6s%-10s",player_one.name,player_two.name);
				
				else if(index == player_one.current_block_id && index != player_two.current_block_id)
					printf("%-16s",player_one.name);

				else if(index != player_one.current_block_id && index == player_two.current_block_id)
					printf("%-16s",player_two.name);

				else if(index != player_one.current_block_id && index != player_two.current_block_id)
					for(i = 0 ; i < 16 ; ++i)
						printf(" ");
				++index;
			}

			else if(index >= 12)
			{
				temp_index = index + 6;

				for(count = 0 ; count < 7 ; ++count)
				{
					printf("|");
					for(i = 0 ; i < 6 ; ++i)
						printf(" ");
					
					if(temp_index == player_one.current_block_id && temp_index == player_two.current_block_id)
						printf("%-6s%-10s",player_one.name,player_two.name);
				
					else if(temp_index == player_one.current_block_id && temp_index != player_two.current_block_id)
						printf("%-16s",player_one.name);

					else if(temp_index != player_one.current_block_id && temp_index == player_two.current_block_id)
						printf("%-16s",player_two.name);

					else if(temp_index != player_one.current_block_id && temp_index != player_two.current_block_id)
						for(i = 0 ; i < 16 ; ++i)
							printf(" ");
					--temp_index;
				}
			}

			else
			{
				for(count = 0 ; count < 7 ; ++count)
				{
					printf("|");
					for(i = 0 ; i < 6 ; ++i)
						printf(" ");

					if(index == player_one.current_block_id && index == player_two.current_block_id)
						printf("%-6s%-10s",player_one.name,player_two.name);
				
					else if(index == player_one.current_block_id && index != player_two.current_block_id)
						printf("%-16s",player_one.name);

					else if(index != player_one.current_block_id && index == player_two.current_block_id)
						printf("%-16s",player_two.name);

					else if(index != player_one.current_block_id && index != player_two.current_block_id)
						for(i = 0 ; i < 16 ; ++i)
							printf(" ");
					++index;
				}
			}
			printf("|\n");
		}

		else if(countBig % 4 == 3)
		{
			if(countBig == 3 || countBig == 23 || countBig == 27)
			{
				for(i = 0 ; i < (23*7)+1 ; ++i)
					printf("-");
			}

			else
			{
				for(i = 0 ; i < 24 ; ++i)
					printf("-");
				for(i = 0 ; i < (23*5)-1 ; ++i)
					printf(" ");
				for(i = 0 ; i < 24 ; ++i)
					printf("-");
			}
			printf("\n");
		}
	}
}

void init_the_board(block board[24])
{
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
	board[3].name = "Fortune Card";
	board[3].price = 0;
	board[3].rent = 0;
	board[3].rent_1 = 0;
	board[3].rent_2 = 0;
	board[3].rent_3 = 0;
	board[3].house_price = 0;
	board[3].house_count = 0;
	board[3].owner.type = NOONE;
	board[3].type = FORTUNE;

	board[4].block_id = 4;
	board[4].name = "Tuzla";
	board[4].price = 16500;
	board[4].rent = 850;
	board[4].rent_1 = 4250;
	board[4].rent_2 = 9500;
	board[4].rent_3 = 26000;
	board[4].house_price = 12000;
	board[4].house_count = 0;
	board[4].owner.type = NOONE;
	board[4].type = PROPERTY;

	board[5].block_id = 5;
	board[5].name = "Arnavutkoy";
	board[5].price = 17000;
	board[5].rent = 875;
	board[5].rent_1 = 4500;
	board[5].rent_2 = 10000;
	board[5].rent_3 = 28000;
	board[5].house_price = 12000;
	board[5].house_count = 0;
	board[5].owner.type = NOONE;
	board[5].type = PROPERTY;

	board[6].block_id = 6;
	board[6].name = "Wait 2 Turn";
	board[6].price = 0;
	board[6].rent = 2;
	board[6].rent_1 = 0;
	board[6].rent_2 = 0;
	board[6].rent_3 = 0;
	board[6].house_price = 0;
	board[6].house_count = 0;
	board[6].owner.type = NOONE;
	board[6].type = PUNISH;

	board[7].block_id = 7;
	board[7].name = "Catalca";
	board[7].price = 20000;
	board[7].rent = 1000;
	board[7].rent_1 = 5000;
	board[7].rent_2 = 12000;
	board[7].rent_3 = 30000;
	board[7].house_price = 13000;
	board[7].house_count = 0;
	board[7].owner.type = NOONE;
	board[7].type = PROPERTY;

	board[8].block_id = 8;
	board[8].name = "Beykoz";
	board[8].price = 23000;
	board[8].rent = 1100;
	board[8].rent_1 = 5500;
	board[8].rent_2 = 12500;
	board[8].rent_3 = 33000;
	board[8].house_price = 13000;
	board[8].house_count = 0;
	board[8].owner.type = NOONE;
	board[8].type = PROPERTY;

	board[9].block_id = 9;
	board[9].name = "Fortune Card";
	board[9].price = 0;
	board[9].rent = 0;
	board[9].rent_1 = 0;
	board[9].rent_2 = 0;
	board[9].rent_3 = 0;
	board[9].house_price = 0;
	board[9].house_count = 0;
	board[9].owner.type = NOONE;
	board[9].type = FORTUNE;

	board[10].block_id = 10;
	board[10].name = "Car Fix";
	board[10].price = 0;
	board[10].rent = 1750;
	board[10].rent_1 = 0;
	board[10].rent_2 = 0;
	board[10].rent_3 = 0;
	board[10].house_price = 0;
	board[10].house_count = 0;
	board[10].owner.type = NOONE;
	board[10].type = TAX;

	board[11].block_id = 11;
	board[11].name = "Maltepe";
	board[11].price = 30000;
	board[11].rent = 1350;
	board[11].rent_1 = 7000;
	board[11].rent_2 = 15000;
	board[11].rent_3 = 40000;
	board[11].house_price = 15000;
	board[11].house_count = 0;
	board[11].owner.type = NOONE;
	board[11].type = PROPERTY;

	board[12].block_id = 12;
	board[12].name = "Bills";
	board[12].price = 0;
	board[12].rent = 2000;
	board[12].rent_1 = 0;
	board[12].rent_2 = 0;
	board[12].rent_3 = 0;
	board[12].house_price = 0;
	board[12].house_count = 0;
	board[12].owner.type = NOONE;
	board[12].type = TAX;

	board[13].block_id = 13;
	board[13].name = "Sisli";
	board[13].price = 33000;
	board[13].rent = 1500;
	board[13].rent_1 = 8000;
	board[13].rent_2 = 16000;
	board[13].rent_3 = 42000;
	board[13].house_price = 16000;
	board[13].house_count = 0;
	board[13].owner.type = NOONE;
	board[13].type = PROPERTY;

	board[14].block_id = 14;
	board[14].name = "Oil";
	board[14].price = 0;
	board[14].rent = 2250;
	board[14].rent_1 = 0;
	board[14].rent_2 = 0;
	board[14].rent_3 = 0;
	board[14].house_price = 0;
	board[14].house_count = 0;
	board[14].owner.type = NOONE;
	board[14].type = TAX;

	board[15].block_id = 15;
	board[15].name = "Fortune Card";
	board[15].price = 0;
	board[15].rent = 0;
	board[15].rent_1 = 0;
	board[15].rent_2 = 0;
	board[15].rent_3 = 0;
	board[15].house_price = 0;
	board[15].house_count = 0;
	board[15].owner.type = NOONE;
	board[15].type = FORTUNE;

	board[16].block_id = 16;
	board[16].name = "Atasehir";
	board[16].price = 35000;
	board[16].rent = 1600;
	board[16].rent_1 = 8500;
	board[16].rent_2 = 17000;
	board[16].rent_3 = 45000;
	board[16].house_price = 17000;
	board[16].house_count = 0;
	board[16].owner.type = NOONE;
	board[16].type = PROPERTY;

	board[17].block_id = 17;
	board[17].name = "Sariyer";
	board[17].price = 40000;
	board[17].rent = 1750;
	board[17].rent_1 = 9500;
	board[17].rent_2 = 19000;
	board[17].rent_3 = 48000;
	board[17].house_price = 19000;
	board[17].house_count = 0;
	board[17].owner.type = NOONE;
	board[17].type = PROPERTY;

	board[18].block_id = 18;
	board[18].name = "Wait 1 Turn";
	board[18].price = 0;
	board[18].rent = 1;
	board[18].rent_1 = 0;
	board[18].rent_2 = 0;
	board[18].rent_3 = 0;
	board[18].house_price = 0;
	board[18].house_count = 0;
	board[18].owner.type = NOONE;
	board[18].type = PUNISH;

	board[19].block_id = 19;
	board[19].name = "Kadikoy";
	board[19].price = 43000;
	board[19].rent = 1900;
	board[19].rent_1 = 11000;
	board[19].rent_2 = 21500;
	board[19].rent_3 = 55000;
	board[19].house_price = 23000;
	board[19].house_count = 0;
	board[19].owner.type = NOONE;
	board[19].type = PROPERTY;

	board[20].block_id = 20;
	board[20].name = "Besiktas";
	board[20].price = 60000;
	board[20].rent = 2500;
	board[20].rent_1 = 15000;
	board[20].rent_2 = 28000;
	board[20].rent_3 = 60000;
	board[20].house_price = 30000;
	board[20].house_count = 0;
	board[20].owner.type = NOONE;
	board[20].type = PROPERTY;

	board[21].block_id = 21;
	board[21].name = "Fortune Card";
	board[21].price = 0;
	board[21].rent = 0;
	board[21].rent_1 = 0;
	board[21].rent_2 = 0;
	board[21].rent_3 = 0;
	board[21].house_price = 0;
	board[21].house_count = 0;
	board[21].owner.type = NOONE;
	board[21].type = FORTUNE;

	board[22].block_id = 22;
	board[22].name = "Vocation";
	board[22].price = 0;
	board[22].rent = 5000;
	board[22].rent_1 = 0;
	board[22].rent_2 = 0;
	board[22].rent_3 = 0;
	board[22].house_price = 0;
	board[22].house_count = 0;
	board[22].owner.type = NOONE;
	board[22].type = TAX;

	board[23].block_id = 23;
	board[23].name = "Bebek";
	board[23].price = 70000;
	board[23].rent = 3500;
	board[23].rent_1 = 20000;
	board[23].rent_2 = 35500;
	board[23].rent_3 = 65000;
	board[23].house_price = 35000;
	board[23].house_count = 0;
	board[23].owner.type = NOONE;
	board[23].type = PROPERTY;
}

node *init_theboard_linkedList(block board[], node *head) // initialize and fill the my linked list with aid of board //
{
	int i;
	node *temp;
	node *traverser;

	for(i = 0 ; i < 24 ; ++i)
	{
		temp = (node*)malloc(sizeof(node));

		temp -> data = board[i];
		temp -> next = NULL;

		if(head == NULL)
			head = temp;

		else
		{
			traverser = head;
			while(traverser -> next != NULL)
				traverser = traverser -> next;

			traverser -> next = temp;
		}
	}

	return head; // return filled linked list //
}

node *get(node *head, int index) // For access the index. node //
{
	int i;
	node *traverser;

	traverser = head;

	for(i = 0 ; i < index ; ++i)
		traverser = traverser -> next;

	return traverser; // return index. node //
}