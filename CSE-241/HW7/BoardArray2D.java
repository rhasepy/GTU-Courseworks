/**
* <h1> BoardArray2D subclass for N- Puzzle Problem </h1>
* @author Muharrem Ozan Yesiller
* @since 2020
* @version HomeWork 7
*/
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class BoardArray2D extends AbstractBoard
{
	/*
	The program fill with -1 board firstly
	and fill different random numbers
	and reset(to be solvable)
	and shuffle
	*/

	/**
	* Private integer reference array for hiding and keep together board configuration
	*/
	private int[][] gameBoard;

	/**
	* No parameter contructor
	* row and column assign 3 default
	*/
	public BoardArray2D()
	{
		super(3,3);
		generateMap();
		shuffle(50);
	}

	/**
	* One parameter constructor
	* @param val_i assign column
	* row assign 3 default
	*/
	public BoardArray2D(int val_i)
	{
		super(val_i, 3);
		generateMap();
		shuffle(50);
	}

	/**
	* Two parameter constructor
	* @param val_i assign column
	* @param val_j assign row
	*/
	public BoardArray2D(int val_i, int val_j)
	{
		super(val_i, val_j);
		generateMap();
		shuffle(50);
	}

	/**
	* Constructor that accepst string variabla
	* @param fileName is name of shape file
	* The program read shape file and generate object
	*/
	public BoardArray2D(String fileName)
	{
		super(fileName);
		setSize(0, 0);
		readFromFile(fileName);
	}

	/**
	* Copy constructor for generate object with object
	* @param AbstractBoard object for copy to generate object with object
	*/
	BoardArray2D(AbstractBoard copyObject)
	{
		super(copyObject.getSize_i(), copyObject.getSize_j());

		int val_i = copyObject.getSize_i();
		int val_j = copyObject.getSize_j();

		this.gameBoard = new int[val_i][val_j];

		this.set_emptycell(copyObject.get_emptyCell_i(), copyObject.get_emptyCell_j());
		this.set_totalMoves(copyObject.numberOfMoves());
		this.set_previous_move(copyObject.lastMove());

		for(int i = 0 ; i < val_i ; ++i)
			for(int j = 0 ; j < val_j ; ++j)
			this.gameBoard[i][j] = copyObject.cell(i, j);
	}

	/**
	* generate map with random numbers
	*/
	private void generateMap()
	{
		int val_i = this.getSize_i();
		int val_j = this.getSize_j();
		Random rand = new Random();

		int number = rand.nextInt(val_i * val_j);

		this.gameBoard = new int[val_i][val_j];

		fill_and_init_gamemap(); // fill the board with -1 //

		for(int i = 0 ; i < val_i ; ++i)
		{
			for(int j = 0 ; j < val_j ; ++j)
			{
				while(testSame(number)) // Prevent to be same numbers on board //
					number = rand.nextInt(val_i * val_j);

				gameBoard[i][j] = number;
			}
		}

		set_emptyCellPosition();
	}

	/**
	* fill with -1 total array
	*/
	private void fill_and_init_gamemap()
	{
		for(int i = 0 ; i < this.getSize_i() ; ++i)
			for(int j = 0 ; j < this.getSize_j() ; ++j)
				this.gameBoard[i][j] = -1;
	}

	/**
	* @param finder, value to be found
	* @return true, the array contains finder
	* @return false, the array not contains finder
	*/
	private boolean testSame(int finder)
	{
		for(int i = 0 ; i < this.getSize_i() ; ++i)
			for(int j = 0 ; j < this.getSize_j() ; ++j)
				if(finder == this.gameBoard[i][j])
					return true;

		return false;
	}

	/**
	* set the empty cell coordinate
	*/
	private void set_emptyCellPosition()
	{
		for(int i = 0 ; i < this.getSize_i() ; ++i)
			for(int j = 0 ; j < this.getSize_j() ; ++j)
				if(this.gameBoard[i][j] == 0)
				{
					this.set_emptycell(i, j);
					return;
				}
	}

	/**
	* @return value, max of numbers on board 
	*/
	private int findMax()
	{
		int max = 0;

		for(int i = 0 ; i < this.getSize_i() ; ++i)
			for(int j = 0 ; j < this.getSize_j() ; ++j)
				if(max < this.gameBoard[i][j])
					max = this.gameBoard[i][j];

		return max;
	}

	/**
	* Takes N and random move up to N
	* @param N is number of random move
	*/
	private void shuffle(int n)
	{
		this.reset();

		int prev_cell_i = this.get_emptyCell_i();
		int prev_cell_j = this.get_emptyCell_j();

		for(int i = 0 ; i < n ; ++i)
		{
			while(prev_cell_i == this.get_emptyCell_i() && prev_cell_j == this.get_emptyCell_j())
				this.moveRandom();

			prev_cell_i = this.get_emptyCell_i();
			prev_cell_j = this.get_emptyCell_j();
		}

		this.set_previous_move('S');
		this.set_totalMoves(this.numberOfMoves() - n);
	}

	/**
	* This method reset to solution current board
	*/
	public void reset()
	{
		int count = 1;

		int maxNumber = this.findMax();

		for(int i = 0 ; i < this.getSize_i() ; ++i)
		{
			for(int j = 0 ; j < this.getSize_j() ; ++j)
			{
				if(count <= maxNumber)
				{
					this.gameBoard[i][j] = count;
					++count;
				}

				else if(count == maxNumber + 1)
				{
					this.gameBoard[i][j] = 0;
					this.set_emptyCellPosition();
					this.set_totalMoves(0);
					this.set_previous_move('S');
					return;
				}
			}
		}
	}

	/**
	* Random move, random direction
	*/
	public char moveRandom()
	{
		int random_move;
		Random rand = new Random();

		int val_i = this.getSize_i();
		int val_j = this.getSize_j();

		while(true)
		{
			random_move = rand.nextInt(4);

			if(random_move == 0 && this.get_emptyCell_j() != 0) // condition left
			{
				this.move('L');
				return 'L';
			}

			else if(random_move == 1 && this.get_emptyCell_j() < val_j-1) // condition right
			{
				this.move('R');
				return 'R';
			}
		
			else if(random_move == 2 && this.get_emptyCell_i() != 0) // condition up
			{
				this.move('U');
				return 'U';
			}

			else if(random_move == 3 && this.get_emptyCell_i() < (val_i-1)) // condition down
			{
				this.move('D');
				return 'D';
			}
		}
	}

	/**
	* This method execute moving for cell
	* @param choice parameter is which direction empty cell move
	*/
	public char move(char choice)
	{
		if(choice == 'L' || choice == 'l')
		{
			int temp, i, j;
			int cell__i = this.get_emptyCell_i();
			int cell__j = this.get_emptyCell_j();

			if(cell__j != 0) // condition left
			{
				i = cell__i;
				j = cell__j;
				temp = this.gameBoard[i][j-1];
				this.gameBoard[i][j-1] = 0;
				this.gameBoard[i][j] = temp;

				this.set_emptyCellPosition();
				this.set_previous_move('L');
				this.set_totalMoves(this.numberOfMoves() + 1);
				return 'L';
			}
		}

		else if(choice == 'R' || choice == 'r')
		{
			int temp, i, j;
			int cell__i = this.get_emptyCell_i();
			int cell__j = this.get_emptyCell_j();

			if(cell__j < (this.getSize_j() - 1)) // condition right
			{
				i = cell__i;
				j = cell__j;
				temp = this.gameBoard[i][j+1];
				this.gameBoard[i][j+1] = 0;
				this.gameBoard[i][j] = temp;

				this.set_emptyCellPosition();
				this.set_previous_move('R');
				this.set_totalMoves(this.numberOfMoves() + 1);
				return 'R';
			}
		}

		else if(choice == 'U' || choice == 'u')
		{
			int temp, i, j;
			int cell__i = this.get_emptyCell_i();
			int cell__j = this.get_emptyCell_j();

			if(cell__i != 0) // condition up
			{
				i = cell__i;
				j = cell__j;
				temp = this.gameBoard[i-1][j];
				this.gameBoard[i-1][j] = 0;
				 this.gameBoard[i][j] = temp;

				this.set_emptyCellPosition();
				this.set_previous_move('U');
				this.set_totalMoves(this.numberOfMoves() + 1);
				return 'U';
			}
		}

		else if(choice == 'D' || choice == 'd')
		{
			int temp, i, j;
			int cell__i = this.get_emptyCell_i();
			int cell__j = this.get_emptyCell_j();

			if(cell__i < (this.getSize_i() - 1)) // condition down
			{
				i = cell__i;
				j = cell__j;
				temp = this.gameBoard[i+1][j];
				this.gameBoard[i+1][j] = 0;
				this.gameBoard[i][j] = temp;

				this.set_emptyCellPosition();
				this.set_previous_move('D');
				this.set_totalMoves(this.numberOfMoves() + 1);
				return 'D';
			}
		}

		return 'X';
	}

	/**
	* Takes two indexes and returns the corresponding cell content.
	* Terminates program if the indexes are not valid.
	* @param index_i is cell column
	* @param index_j is cell row
	*/
	public int cell(int index_i, int index_j)
	{
		if((index_i >= this.getSize_i() || index_i < 0) || (index_j >= this.getSize_j() || index_j < 0))
			System.exit(0);

		return this.gameBoard[index_i][index_j];
	}

	/**
	* @param fileName that is filename which will read shape file
	*/
	public void readFromFile(String myFile)
	{
		int i = 0, j = 0;
		int space = 0, count = 0;
		char[] map_char = new char[26];
		String line = "";

		for(int index = 0 ; index < 26 ; ++index)
			map_char[index] = '\0';

		/********* FOR THE SETTING SIZE *********/
		try
		{
			File f = new File(myFile);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine()) != null)
			{
				for(int index = 0 ; index < line.length() ; ++index)
					map_char[index] = line.charAt(index);
				++i;
			}

			while(map_char[count] != '\0')
			{
				if(map_char[count] == ' ')
					++space;

				++count;
			}

			this.setSize(i, space+1);
			fr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		/********* FOR THE SETTING SIZE *********/

		/********* FOR THE FILLING MAP  *********/
		try
		{
			for(int index = 0 ; i < 26 ; ++i)
				map_char[i] = '\0';

			line = "";
			i = 0;
			j = 0;

			File f = new File(myFile);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			this.gameBoard = new int[this.getSize_i()][this.getSize_j()];

			for(int ii = 0 ; ii < this.getSize_i() ; ++ii)
				for(int jj = 0 ; jj < this.getSize_j() ; ++jj)
					this.gameBoard[ii][jj] = -1;

			while((line = br.readLine()) != null)
			{
				j = 0;

				for(int index = 0 ; index < line.length() ; ++index)
					map_char[index] = line.charAt(index);

				this.char_to_int(map_char, i, j);

				++i;
			}

			this.set_totalMoves(0);
			this.set_emptyCellPosition();
			this.set_previous_move('S');
			fr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		/********* FOR THE FILLING MAP  *********/
	}

	/**
	* The method converts a string is that was read shape file to a number
	* @param map_char is that was read from shape file
	* @param i column
	* @param j row
	*/
	private void char_to_int(char[] map_char, int i, int j)
	{
		for(int index = 0 ; index < 26 ; ++index)
		{
			if(map_char[index] == ' ' && index < 25)
			{
				if(map_char[index-1] != 'b')
				{
					this.gameBoard[i][j] = (int) (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

					if(this.gameBoard[i][j] == 0)
						this.gameBoard[i][j] = -1;

					++j;
				}

				else
				{
					this.gameBoard[i][j] = 0;
					++j;
				}
			}

			else if(map_char[index] == '\0')
			{
				if(map_char[index-1] != 'b')
				{
					this.gameBoard[i][j] = (int) (10*(map_char[index-2] - 48)) + (map_char[index-1] - 48);

					if(this.gameBoard[i][j] == 0)
						this.gameBoard[i][j] = -1;

					++j;
				}

				else
				{
					this.gameBoard[i][j] = 0;
					++j;
				}

				index = 99; // I dont want to use break so, i used index asaignmet 99 to break for loop //
			}

			else if(index == 25)
			{
				if(map_char[index-1] != 'b')
				{
					this.gameBoard[i][j] = (int) (10*(map_char[index-1] - 48)) + (map_char[index] - 48);

					if(this.gameBoard[i][j] == 0)
						this.gameBoard[i][j] = -1;
				}

				else
					this.gameBoard[i][j] = 0;
			}
		}
	}

	/**
	* The method serve gameing to you
	*/
	public void play()
	{
		char choice;
		String fileName;
		Scanner choice_obj = new Scanner(System.in);
		Scanner choice_fileName = new Scanner(System.in);

		do
		{
			System.out.print(this);

			System.out.printf("Your choice: ");
			choice = choice_obj.next().charAt(0);


			switch(choice)
			{
				case 'L':
				case 'l':
					this.move('L');
					break;

				case 'R':
				case 'r':
					this.move('R');
					break;

				case 'U':
				case 'u':
					this.move('U');
					break;

				case 'D':
				case 'd':
					this.move('D');
					break;

				case 'Q':
				case 'q':
					System.out.println("The program turning off...");
					System.exit(0);
					break;

				case 'E':
				case 'e':
					System.out.print("Please enter the file name to load current board(with EXTENSION): ");
					fileName = choice_fileName.nextLine();
					this.writeToFile(fileName);
					break;

				case 'O':
				case 'o':
					System.out.print("Please enter the file name to load current board(with EXTENSION): ");
					fileName = choice_fileName.nextLine();
					readFromFile(fileName);
					break;

				default:
					System.out.println("You have chosen invalid choice...");
					System.out.println("Please choose one of them (E, O, L, R, U, D)");
					break;
			}
		}while(!(this.isSolved()));

		if(isSolved())
		{
			System.out.println("**********Final Board**********");
			System.out.print(this);
			System.out.println("*******************************");
			System.out.println("Problem Solved!");
			System.out.printf("Total Moves: %d\n", this.numberOfMoves());
		}
	}
}
