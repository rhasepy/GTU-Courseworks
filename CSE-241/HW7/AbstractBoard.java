/**
* <h1> Abstract Board Class for N- Puzzle Problem </h1>
* This class is written for Abstraction and prevent code duplication 
* @author Muharrem Ozan Yesiller
* @since 2020
* @version HomeWork 7
*/

/**
* Equals, toString, isSolved ve writeToFile methods implemented in this source file.
*/
import java.io.*;

public abstract class AbstractBoard
{
	private static int objectNumber = 0;
	private int size_i;
	private int size_j;
	private int totalMoves;
	private char previous_move;
	private int cell_i;
	private int cell_j;

	/**
	* No parameter constructor
	*/
	public AbstractBoard()
	{
		System.out.println("You don't generate no parameter object...");
		System.exit(0);
	}

	/**
	* Constructor that accepts one parameter
	* @param val_i, val_i assign column, row assign 3 default.
	*/
	public AbstractBoard(int val_i)
	{
		if(val_i < 3)
		{
			System.out.println("This object was not generated...");
			System.exit(0);
		}

		this.size_i = val_i;
		this.size_j = 3;
		this.totalMoves = 0;
		this.previous_move = 'S';
		++objectNumber;
	}

	/**
	* Constructor that accepts two parameter
	* @param val_i, assign column
	* @param val_j, assign row
	*/
	public AbstractBoard(int val_i, int val_j)
	{
		if(val_i < 3 || val_j < 3)
		{
			System.out.println("This object was not generated...");
			System.exit(0);
		}

		this.size_i = val_i;
		this.size_j = val_j;
		this.totalMoves = 0;
		this.previous_move = 'S';
		++objectNumber;
	}

	/**
	* Constructor that accepst string variabla
	* @param fileName is name of shape file
	* The program read shape file and generate object
	*/
	public AbstractBoard(String fileName)
	{
		this.totalMoves = 0;
		this.previous_move = 'S';
		++objectNumber;
	}

	/**
	* Retrieves the val total move numbers
	* @return total number of moves
	*/
	public void set_totalMoves(int val)
	{
		this.totalMoves = val;
	}

	/**
	* Retrieves previous move
	* @return previous move
	*/
	public void set_previous_move(char val)
	{
		this.previous_move = val;
	}

	/**
	* Sets the size of row and column
	* @param val_i assign column
	* @param val_j assign row
	*/
	public void setSize(int val_i, int val_j)
	{
		this.size_i = val_i;
		this.size_j = val_j;
	}

	/**
	* Retrieves living object number
	* @return living object number
	*/
	public int NumberOfBoards()
	{
		return objectNumber;
	}

	/**
	* Retrieves previous move
	* @return previous move
	*/
	public char lastMove()
	{
		return previous_move;
	}

	/**
	* Retrieves number of total moves
	* @return number of total moves
	*/
	public int numberOfMoves()
	{
		return totalMoves;
	}

	/**
	* Retrieve column value
	* @return column size
	*/
	public int getSize_i()
	{
		return size_i;
	}

	/**
	* Retrieve row value
	* @return row size
	*/
	public int getSize_j()
	{
		return size_j;
	}

	/**
	* Sets the empty cell coordinate
	* @param val_i assign empty cell y
	* @param val_j assign empty cell x
	*/
	public void set_emptycell(int val_i, int val_j)
	{
		this.cell_i = val_i;
		this.cell_j = val_j;
	}

	/**
	* Retrieve empty cell coordinate y
	* @return empty cell coordinate y
	*/
	public int get_emptyCell_i()
	{
		return cell_i;
	}

	/**
	* Retrieve empty cell coordinate x
	* @return empty cell coordinate x
	*/
	public int get_emptyCell_j()
	{
		return cell_j;
	}

	/**
	* Equals method
	* @param AbstractBoard, does not matter be any extends subclasses
	* @return true, if the board array equals this board array
	* @return false, if the board array not equal this board array
	*/
	public boolean Equals(AbstractBoard other)
	{
		if(this.getSize_i() == other.getSize_i() && this.getSize_j() == other.getSize_j())
		{
			for(int i = 0 ; i < this.getSize_i() ; ++i)
				for(int j = 0 ; j < this.getSize_j() ; ++j)
					if(this.cell(i,j) != other.cell(i,j))
						return false;

			return true;
		}

		return false;
	}

	/**
	* @return true if the board is solution
	* @return false if the board is not solution
	*/
	public boolean isSolved()
	{
		int size = this.getSize_i() * this.getSize_j();
		int[] finalBoard = new int[size];
		int index = 0;
		int count = 1;

		for(int i = 0 ; i < this.getSize_i() ; ++i)
			for(int j = 0 ; j < this.getSize_j() ; ++j)
			{
				finalBoard[index] = count;
				++count;
				++index;
			}

		finalBoard[size-1] = 0;
		index = 0;

		for(int i = 0 ; i < this.getSize_i() ; ++i)
			for(int j = 0 ; j < this.getSize_j() ; ++j)
			{
				if(this.cell(i, j) != finalBoard[index])
					return false;

				++index;
			}

		return true;
	}

	/**
	* @return string that producesthe board as string
	*/
	@Override
	public String toString()
	{
		String returnStr = "";

		for(int i = 0 ; i < this.getSize_i() ; ++i)
		{
			for(int j = 0 ; j < this.getSize_j() ; ++j)
			{
				if(this.cell(i, j) == -1)
					returnStr += "00 ";

				else if(this.cell(i, j) == 0)
					returnStr += "bb ";

				else
				{
					if(this.cell(i, j) < 10 && this.cell(i, j) != 0)
					{
						returnStr += "0";
						returnStr += Integer.toString(this.cell(i, j));
						returnStr += " ";
					}

					else
					{
						returnStr += Integer.toString(this.cell(i, j));
						returnStr += " ";
					}
				}
			}

			returnStr += "\n";
		}

		returnStr += "\n";

		return returnStr;
	}
	
	/**
	* This function write text file current board
	* @param file name that will write to textfile
	*/
	public void writeToFile(String fileName)
	{
		String map = "";
		try
		{
			File f = new File(fileName);
			FileWriter fw = new FileWriter(f, false);
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0 ; i < this.getSize_i() ; ++i)
			{
				for(int j = 0 ; j < this.getSize_j() ; ++j)
				{
					if(this.cell(i, j) == 0)
						map += "bb";

					else if(this.cell(i, j) == -1)
						map += "00";

					else
					{
						if(this.cell(i, j) < 10 && this.cell(i, j) != 0)
						{
							map += "0";
							map += Integer.toString(this.cell(i, j));
						}

						else
							map += Integer.toString(this.cell(i, j));
					}

					if(j < (this.getSize_j()-1))
						map += " ";

				}

				map += "\n";
			}

			System.out.print("The current board load the ");
			System.out.println(fileName);

			bw.write(map);
			bw.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	* @param fileName that is filename which will read shape file
	*/
	public abstract void readFromFile(String fileName);

	/**
	* This method reset to solution current board
	*/
	public abstract void reset();

	/**
	* This method execute moving for cell
	* @param choice parameter is which direction empty cell move
	*/
	public abstract char move(char choice);

	/**
	* Takes two indexes and returns the corresponding cell content.
	* Terminates program if the indexes are not valid.
	* @param index_i is cell column
	* @param index_j is cell row
	*/
	public abstract int cell(int index_i, int index_j);
}