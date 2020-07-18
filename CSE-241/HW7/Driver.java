/**
* @author Muharrem Ozan Yesiller 171044033
* @since 2020
* @version HomeWork 7
*/

public class Driver
{
	public static boolean gtu_global_function(AbstractBoard[] object)
	{
		int size = object.length;
		int first = 0;
		int finaL = size - 1;
		boolean flag = false;

		for(int i = 0 ; i < size ; ++i)
			if(object[i].isSolved())
				flag = true;

		if(flag == false)
		{
			System.out.println("This array of AbstractBoard references not contain solution...");
			return false;
		}

		else
		{
			//if the array contains result
			//if the board reverse move from result, board must be first board
			for(int i = size - 1 ; i > 0 ; --i)
			{
				if(object[i].getSize_i() == object[i-1].getSize_i() && object[i].getSize_j() == object[i-1].getSize_j())
				{
					if(object[i].lastMove() == 'R')
						object[finaL].move('L');
					else if(object[i].lastMove() == 'L')
						object[finaL].move('R');
					else if(object[i].lastMove() == 'D')
						object[finaL].move('U');
					else if(object[i].lastMove() == 'U')
						object[finaL].move('D');
				}

				else
				{
					System.out.println("This array of AbstractBoard references contains solution but it is not sequence of moves for a solution...");
					return false;
				}
			}
		}

		if(object[finaL].Equals(object[first]))
		{
			//if the array contains result
			//if the board reverse move from result, board must be first board
			//If the result is solved by inverse movements is the final
			System.out.println("This array of AbstractBoard references contains sequence of moves for a solution...");
			return true;
		}

		if(flag == true)
			System.out.println("This array of AbstractBoard references contains solution but it is not sequence of moves for a solution...");

		return false;
	}

	public static void main(String[] args)
	{
/*------------------------------------1---------------------------------------------*/
		System.out.println("----------------1-----------------");
		BoardArray1D obj = new BoardArray1D("testfile1.txt");
		BoardArray1D obj1 = new BoardArray1D(obj);
		obj.move('R');
		BoardArray1D obj2 = new BoardArray1D(obj);
		obj.move('R');
		BoardArray1D obj3 = new BoardArray1D(obj);
		obj.move('D');
		BoardArray1D obj4 = new BoardArray1D(obj);

		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj3);
		System.out.println(obj4);
		

		AbstractBoard[] object = {obj1, obj2, obj3, obj4};

		System.out.println("For the my random array1(CONTAINS SINGLE TYPE OBJECT): ");
		gtu_global_function(object);
		System.out.println("----------------1-----------------");
		System.out.println("\n");
/*------------------------------------1---------------------------------------------*/

/*------------------------------------2---------------------------------------------*/
		System.out.println("----------------2-----------------");
		BoardArray2D testfile_1 = new BoardArray2D();

		testfile_1.readFromFile("testfile1.txt");
		System.out.println("The testfile_1 object of board: ");
		System.out.println(testfile_1);

		BoardArray2D test_1 = new BoardArray2D(testfile_1);
		testfile_1.move('R');
		BoardArray1D test_2 = new BoardArray1D(testfile_1);
		testfile_1.move('R');
		BoardArray1D test_3 = new BoardArray1D(testfile_1);
		testfile_1.move('D');
		BoardArray2D test_4 = new BoardArray2D(testfile_1);

		AbstractBoard[] object2 = {test_1, test_2, test_3, test_4};

		System.out.println("---------------------------");
		System.out.println("I pushed to array that moved R, R and D");
		System.out.println("---------------------------");
		System.out.println("For the my random array2(CONTAINS POLYMORPHIC TYPE OBJECT): ");
		gtu_global_function(object2);
		System.out.println("----------------2-----------------");
		System.out.println("\n");
/*------------------------------------2---------------------------------------------*/


/*------------------------------------3---------------------------------------------*/
		System.out.println("----------------3-----------------");
		BoardArray1D rand1 = new BoardArray1D(3,3);
		rand1.moveRandom();
		BoardArray2D rand2 = new BoardArray2D(3,4);
		rand2.moveRandom();
		BoardArray1D rand3 = new BoardArray1D(5,5);
		BoardArray1D rand4 = new BoardArray1D(7,7);
		BoardArray1D solution = new BoardArray1D();
		solution.reset();
		BoardArray2D rand5 = new BoardArray2D(solution);
		BoardArray2D rand6 = new BoardArray2D(5,7);

		AbstractBoard[] array3 = {rand1, rand2, rand3, rand4, rand5, rand6};
		System.out.println("For the my random array3: ");
		gtu_global_function(array3);

		System.out.println("------------");
		rand1.reset();
		System.out.println("solutionFile3x3: ");
		System.out.println(rand1);
		rand1.writeToFile("solutionFile3x3.txt");
		System.out.println("------------");
		System.out.println("solutionFile4x6: ");
		System.out.println(rand5);
		rand5.writeToFile("solutionFile4x6.txt");
		System.out.println("------------");
		System.out.println("----------------3-----------------");
		System.out.println("\n");
/*------------------------------------3---------------------------------------------*/


/*------------------------------------4---------------------------------------------*/
		System.out.println("----------------4-----------------");
		BoardArray2D random1 = new BoardArray2D();
		BoardArray1D random2 = new BoardArray1D(3);
		BoardArray1D random3 = new BoardArray1D(3,3);
		BoardArray2D random4 = new BoardArray2D(7,5);
		BoardArray2D random5 = new BoardArray2D(4,8);

		System.out.println(random1);
		System.out.println(random2);
		System.out.println(random3);
		System.out.println(random4);
		System.out.println(random5);

		AbstractBoard[] array4 = {random1, random2, random3, random4, random5};

		System.out.println("For the my random array4: ");
		gtu_global_function(array4);
		System.out.println("----------------4-----------------");
		System.out.println("\n");
/*------------------------------------4---------------------------------------------*/


/*------------------------------------5---------------------------------------------*/
		System.out.println("----------------5-----------------");
		BoardArray2D final_obj = new BoardArray2D("testfile2.txt");
		System.out.println("The Board: ");
		System.out.println(final_obj);
		System.out.println("Program moved D-L-L-D-R-R...");
		BoardArray1D _obj1 = new BoardArray1D(final_obj);
		final_obj.move('D');
		BoardArray1D _obj2 = new BoardArray1D(final_obj);
		final_obj.move('L');
		BoardArray2D _obj3 = new BoardArray2D(final_obj);
		final_obj.move('L');
		BoardArray1D _obj4 = new BoardArray1D(final_obj);
		final_obj.move('D');
		BoardArray1D _obj5 = new BoardArray1D(final_obj);
		final_obj.move('R');
		BoardArray2D _obj6 = new BoardArray2D(final_obj);
		final_obj.move('R');
		BoardArray1D _obj7 = new BoardArray1D(final_obj);


		AbstractBoard[] array5 = {_obj1, _obj2, _obj3, _obj4, _obj5, _obj6, _obj7};
		gtu_global_function(array5);
		System.out.println("----------------5-----------------");
		System.out.println("\n");
/*------------------------------------5---------------------------------------------*/
	}
}