#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct queue
{
	int data;
	struct queue *next;
}queue;

typedef struct stack
{
	int data;
 	struct stack *next;
}stack;

typedef struct bst
{
	int data;
 	struct bst *left, *right;
}bst;

void fill_structures(stack **stack_, queue **queue_, bst  **bst_, int data[20]);
void ordered_print(stack *stack_, queue *queue_, bst *bst_);
void search(stack *stack_, queue *queue_, bst *bst_, int val_to_search);
void special_traverse(stack *stack_, queue *queue_, bst *bst_);
void pop(stack **stack_);
void push(stack **stack_, int data);
void print_stack(stack *stack_);
void enqueue(queue **queue_, int data);
void dequeue(queue **queue_);
void print_queue(queue *queue_);
void insert(bst **bst_, int data);
void print_bst(bst *bst_);
int min_bst(bst *bst_);
int max_bst(bst *bst_);
bst* deleteBST(bst *bst_, int data);

int main()
{
 	int data[20] = {5, 2, 7, 8, 11, 3, 21, 7, 6, 15, 19, 35, 24, 1, 8, 12, 17, 8, 23, 4};

 	bst *bst_ = NULL;
 	queue *queue_ = NULL;
	stack *stack_ = NULL;

  	fill_structures(&stack_, &queue_, &bst_, data);

  	ordered_print(stack_, queue_, bst_);

  	search(stack_, queue_, bst_, 5);

  	special_traverse(stack_, queue_, bst_);

  	return 0;
}

void fill_structures(stack **stack_, queue **queue_, bst **bst_, int data[20])
{
	int i = 0, size = 0;
	clock_t start_s, finish_s, start_q, finish_q, start_b, finish_b;
	double total_s, total_q, total_b;

	start_s = clock();
	for(i = 0 ; i < 20 ; ++i)
		push(stack_, data[i]);
	finish_s = clock();
	total_s = (double)(finish_s - start_s) / 100;

	start_q = clock();
	for(i = 0 ; i < 20 ; ++i)
		enqueue(queue_, data[i]);
	finish_q = clock();
	total_q = (double)(finish_q - start_q) / 100;

	start_b = clock();
	for(i = 0 ; i < 20 ; ++i)
		insert(bst_, data[i]);
	finish_b = clock();
	total_b = (double)(finish_b - start_b) / 100;

	printf("The Structures are filled by program...\n");

	printf("\n***************************************\n");
	printf("*** Fill Structures Time ***\n");
	printf("%-13s  %-6s %-6s %-6s\n","Structures","Stack","Queue","BST");
	printf("%-13s  %.2f   %.2f   %.2f\n","Exec. Time",total_s,total_q,total_b);
	printf("***************************************\n\n");
}

void ordered_print(stack *stack_, queue *queue_, bst *bst_)
{
	clock_t start_s, start_q, start_b, finish_s, finish_q, finish_b;
	double total_s, total_b, total_q;
	stack *stack_i, *stack_j;
	queue *queue_i, *queue_j;
	int temp;


	start_s = clock();
	for(stack_i = stack_ ; stack_i != NULL ; stack_i = stack_i -> next)
	{
		for(stack_j = stack_ ; stack_j != NULL ; stack_j = stack_j -> next)
			if(stack_i -> data > stack_j -> data)
			{
				temp = stack_j -> data;
				stack_j -> data = stack_i -> data;
				stack_i -> data = temp;
			}
	}
	printf("Stack Content: ");
	print_stack(stack_);
	printf("\n\n");
	finish_s = clock();
	total_s = (double)(finish_s - start_s) / 100;

	start_b = clock();
	printf("Binary Tree Content: ");
	print_bst(bst_);
	printf("\n\n");
	finish_b = clock();
	total_b = (double)(finish_b - start_b) / 100;


	start_q = clock();
	for(queue_i = queue_ ; queue_i != NULL ; queue_i = queue_i -> next)
	{
		for(queue_j = queue_ ; queue_j != NULL ; queue_j = queue_j -> next)
			if(queue_i -> data < queue_j -> data)
			{
				temp = queue_j -> data;
				queue_j -> data = queue_i -> data;
				queue_i -> data = temp;
			}
	}
	printf("Queue Content: ");
	print_queue(queue_);
	printf("\n\n");
	finish_q = clock();
	total_q = (double)(finish_q - start_q) / 100;

	printf("The Structures are ordered by program...\n");

	printf("\n***************************************\n");
	printf("*** Ordered and Print Time ***\n");
	printf("%-13s  %-6s %-6s %-6s\n","Structures","Stack","Queue","BST");
	printf("%-13s  %.2f   %.2f   %.2f\n","Exec. Time",total_s,total_q,total_b);
	printf("***************************************\n\n");
}

void search(stack *stack_, queue *queue_, bst *bst_, int val_to_search)
{
	int step = 1, result = 0, flag = 0;
	clock_t start_s, finish_s, start_q, finish_q, start_b, finish_b;
	double total_s, total_q, total_b;

	start_s = clock();
	while(stack_ != NULL)
	{
		if(stack_ -> data == val_to_search)
		{
			++result;
			printf("%d. result founded on %d. step.[for stack]\n",result,step);
		}

		stack_ = stack_ -> next;
		++step;
	}
	if(result == 0)
		printf("Result not founded.[for stack]\n");
	finish_s = clock();
	total_s = (double)(finish_s - start_s) / 100;

	step = 1;
	result = 0;
	start_q = clock();
	while(queue_ != NULL)
	{
		if(queue_ -> data == val_to_search)
		{
			++result;
			printf("%d. result founded on %d. step.[for queue]\n",result,step);
		}

		queue_ = queue_ -> next;
		++step;
	}
	if(result == 0)
		printf("Result not founded.[for queue]\n");
	finish_q = clock();
	total_q = (double)(finish_q - start_q) / 100;

	step = 1;
	result = 0;
	start_b = clock();
	while(bst_ != NULL)
	{
		if(bst_ -> data > val_to_search)
			bst_ = bst_ -> left;

		else if(bst_ -> data < val_to_search)
			bst_ = bst_ -> right;

		if(bst_ -> data == val_to_search)
		{
			++result;
			printf("%d. result founded on %d. step.[binary search tree]\n",result,step);
			break;
		}

		++step;
	}
	if(result == 0)
		printf("Result not founded.[for bst]\n");
	finish_b = clock();
	total_b = (double)(finish_b - start_b) / 100;

	printf("\n***************************************\n");
	printf("*** Search Time ***\n");
	printf("%-13s  %-6s %-6s %-6s\n","Structures","Stack","Queue","BST");
	printf("%-13s  %.2f   %.2f   %.2f\n","Exec. Time",total_s,total_q,total_b);
	printf("***************************************\n\n");
}

void special_traverse(stack *stack_, queue *queue_, bst *bst_)
{
	int temp, i, j, size = 0;
	clock_t start_s, start_q, start_b, finish_s, finish_q, finish_b;
	double total_s, total_b, total_q;
	stack *stack_i, *stack_j, *reverse_s, *traverser_s;
	queue *queue_i, *queue_j, *reverse_q = NULL, *traverser_q;
	bst *traverser_b;

	start_s = clock();
	for(stack_i = stack_ ; stack_i != NULL ; stack_i = stack_i -> next)
	{
		for(stack_j = stack_ ; stack_j != NULL ; stack_j = stack_j -> next)
			if(stack_i -> data > stack_j -> data)
			{
				temp = stack_j -> data;
				stack_j -> data = stack_i -> data;
				stack_i -> data = temp;
			}
	}
	traverser_s = stack_;
	while(traverser_s != NULL)
	{
		++size;
		traverser_s = traverser_s -> next;
	}
	traverser_s = stack_;
	for(i = 0 ; i < size ; ++i) // for the producing increasing stack //
	{
		push(&reverse_s, traverser_s -> data);
		traverser_s = traverser_s -> next;
	}

	for(i = 0 ; i < (size / 2) ; ++i)
	{
		printf("%d %d ",stack_ -> data, reverse_s -> data);
		pop(&stack_);
		pop(&reverse_s);
	}
	if(size % 2 == 1)
		printf("%d ",stack_ -> data);

	printf("[special_traverse for stack]\n");
	finish_s = clock();
	total_s = (double)(finish_s - start_s) / 100;

	start_q = clock();
	for(queue_i = queue_ ; queue_i != NULL ; queue_i = queue_i -> next)
	{
		for(queue_j = queue_ ; queue_j != NULL ; queue_j = queue_j -> next)
			if(queue_i -> data > queue_j -> data)
			{
				temp = queue_j -> data;
				queue_j -> data = queue_i -> data;
				queue_i -> data = temp;
			}
	}

	traverser_q = queue_;
	size = 0;
	while(traverser_q != NULL)
	{
		++size;
		traverser_q = traverser_q -> next;
	}

	traverser_q = queue_;
	for(i = 0 ; i < (size /2) ; ++i) // for the producing decreasing queue //
	{
		enqueue(&reverse_q, traverser_q -> data);
		traverser_q = traverser_q -> next;
	}

	for(queue_i = queue_ ; queue_i != NULL ; queue_i = queue_i -> next)
	{
		for(queue_j = queue_ ; queue_j != NULL ; queue_j = queue_j -> next)
			if(queue_i -> data < queue_j -> data)
			{
				temp = queue_j -> data;
				queue_j -> data = queue_i -> data;
				queue_i -> data = temp;
			}
	}

	for(i = 0 ; i < (size / 2) ; ++i)
	{
		printf("%d %d ",reverse_q -> data, queue_ -> data);
		dequeue(&queue_);
		dequeue(&reverse_q);
	}
	if(size % 2 == 1)
		printf("%d ",queue_ -> data);

	printf("[special_traverse for queue]\n");
	finish_q = clock();
	total_q = (double)(finish_q - start_q) / 100;

	start_b = clock();
	traverser_b = bst_;

		printf("%d %d ",max_bst(bst_), min_bst(traverser_b));
		bst_ = deleteBST(bst_, max_bst(bst_));
		traverser_b = deleteBST(traverser_b, min_bst(traverser_b));
		printf("%d %d ",max_bst(bst_), min_bst(traverser_b));

	printf("[special_traverse for binary search tree]\n");
	finish_b = clock();
	total_b = (double)(finish_b - start_b) / 100;

	printf("\n***************************************\n");
	printf("*** Special Traverse Time ***\n");
	printf("%-13s  %-6s %-6s %-6s\n","Structures","Stack","Queue","BST");
	printf("%-13s  %.2f   %.2f   %.2f\n","Exec. Time",total_s,total_q,total_b);
	printf("***************************************\n\n");
}

void pop(stack **stack_)
{
	if(*stack_ != NULL)
		(*stack_) = (*stack_) -> next;
}

void push(stack **stack_, int data)
{
	stack *new = (stack*)malloc(sizeof(stack));

	new -> data = data;
	new -> next = *stack_;

	*stack_ = new;
}

void print_stack(stack *stack_)
{
	stack *traverser;

	traverser = stack_;
	if(traverser == NULL)
		printf(" [EMPTY]\n");
	else
	{
		while(traverser != NULL)
		{
			printf("%d ",traverser -> data);
			traverser = traverser -> next;
		}
	}
}

void enqueue(queue **queue_, int data)
{
	queue *temp = NULL;
	queue *traverser = NULL;

	temp = (queue*)malloc(sizeof(queue));

	temp -> data = data;
	temp -> next = NULL;

	if((*queue_) == NULL)
		(*queue_) = temp;
	else
	{
		traverser = (*queue_);

		while(traverser -> next != NULL)
			traverser = traverser -> next;

		traverser -> next = temp;
	}
}

void dequeue(queue **queue_)
{
	if((*queue_) != NULL)
		(*queue_) = (*queue_) -> next;
}

void print_queue(queue *queue_)
{
	if(queue_ -> next != NULL)
		print_queue(queue_ -> next);

	if(queue_ -> next != NULL)
		printf("->%d",queue_ -> data);
	else
		printf("[tail]%d",queue_ -> data);
}

void insert(bst **bst_, int data)
{
	if((*bst_) == NULL)
	{
		bst *new = (bst*)malloc(sizeof(bst));

		new -> data = data;
		new -> right = NULL;
		new -> left = NULL;

		*bst_ = new;
	}

	if(data < (*bst_) -> data)
		insert(&(*bst_) -> left, data);
	else if(data > (*bst_) -> data)
		insert(&(*bst_) -> right, data);
}

void print_bst(bst *bst_)
{
    if(bst_ == NULL)
    	return;

    print_bst(bst_ -> right);
    printf("%d ",bst_ -> data);
    print_bst(bst_ -> left);
}

int max_bst(bst *bst_)
{
	while(bst_ -> right != NULL)
		bst_ = bst_ -> right;

	return bst_ -> data;
}

int min_bst(bst *bst_)
{
	while(bst_ -> left != NULL)
		bst_ = bst_ -> left;

	return bst_ -> data;
}

bst* deleteBST(bst *bst_, int data)
{
    if(bst_ == NULL)
        return NULL;

    if (data > bst_ -> data)
        bst_ -> right = deleteBST(bst_ -> right, data);

    else if(data < bst_ -> data)
        bst_->left = deleteBST(bst_ -> left, data);
    else
    {
        if(bst_ -> left == NULL && bst_ -> right == NULL)
        {
            free(bst_);
            return NULL;
        }
        else if(bst_ -> left == NULL || bst_ -> right == NULL)
        {
            bst *temp;
            if(bst_ -> left == NULL)
                temp = bst_ -> right;
            else
                temp = bst_ -> left;
            free(bst_);
            return temp;
        }
        else
        {
            bst *temp;
            temp -> data = min_bst(bst_->right);
            bst_ -> data = temp -> data;
            bst_ -> right = deleteBST(bst_ -> right, temp -> data);
        }
    }
    return bst_;
}