/**
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HomeWork 7
*/
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		if(args.length == 1) // if the given arguman commend line
		{
			int gameMode;
			Scanner cin = new Scanner(System.in);

			System.out.println("1)BoardArray1D");
			System.out.println("2)BoardArray2D");
			System.out.print("Please chooce the game mode: ");

			do
			{
				System.out.print("Please choose the game mode: ");
				gameMode = cin.nextInt();

				if(gameMode > 2 || gameMode < 1)
					System.out.println("Please choose between 1 or 2");

			}while(gameMode > 2 || gameMode < 1);

			if(gameMode == 1)
			{
				BoardArray1D game = new BoardArray1D(args[0]);
				game.play();
			}

			else if(gameMode == 2)
			{
				BoardArray2D game = new BoardArray2D(args[0]);
				game.play();
			}
		}

		else if(args.length == 0) // if the not given arguman commend line
		{
			int gameMode;
			int val_size;
			char test_choice;
			Scanner cin = new Scanner(System.in);

			System.out.println("1)BoardArray1D");
			System.out.println("2)BoardArray2D");
			System.out.print("Please chooce the game mode: ");

			do
			{
				System.out.print("Please choose the game mode: ");
				gameMode = cin.nextInt();

				if(gameMode > 2 || gameMode < 1)
					System.out.println("Please choose between 1 or 2");

			}while(gameMode > 2 || gameMode < 1);

			System.out.println("Enter the size: ");

			do
			{
				test_choice = cin.next().charAt(0);

				val_size = (int) test_choice - 48;

				if(val_size > 9 || val_size < 3)
					System.out.println("Please choose integer between 3 and 9");

			}while(val_size > 9 || val_size < 3);

			if(gameMode == 1)
			{
				BoardArray1D game = new BoardArray1D(val_size, val_size);
				game.play();
			}

			else if(gameMode == 2)
			{
				BoardArray2D game = new BoardArray2D(val_size, val_size);
				game.play();
			}
		}
	}
}