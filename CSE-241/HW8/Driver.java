public class Driver
{
	public static void main(String[] args)
	{
		GTUVector<Character> testVector = new GTUVector<Character>();

		System.out.println("---------------------------TEST VECTOR(Char)-------------------------------");
		testVector.insert('G');
		System.out.println(testVector);
		testVector.insert('T');
		System.out.println(testVector);
		testVector.insert('U');
		System.out.println(testVector);
		testVector.insert('V');
		System.out.println(testVector);
		testVector.insert('E');
		System.out.println(testVector);
		testVector.insert('C');
		System.out.println(testVector);
		testVector.insert('T');
		System.out.println(testVector);
		testVector.insert('O');
		System.out.println(testVector);
		testVector.insert('R');
		System.out.println(testVector);

		System.out.println("USED: " + testVector.size());

		if(testVector.empty())
			System.out.println("TEST UNSUCCESSED...");

		System.out.println("------------------------");
		System.out.println("Erasing: G...");
		testVector.erase('G');
		System.out.println(testVector);
		System.out.println("------------------------");

		System.out.println("------------------------");
		System.out.println("Erasing: T(first T)...");
		testVector.erase('T');
		System.out.println(testVector);
		System.out.println("------------------------");

		System.out.println("------------------------");
		System.out.println("Erasing: R...");
		testVector.erase('R');
		System.out.println(testVector);
		System.out.println("------------------------");

		System.out.println("------------------------");
		testVector.clear();
		System.out.println("Cleared GTUVector...");
		System.out.println(testVector);
		System.out.println("USED: " + testVector.size());
		System.out.println("------------------------");

		if(testVector.empty())
			System.out.println("This Vector is empty...");

		System.out.println("---------------------------------------------------------------------------");

		System.out.println("--------------------------TEST SET(String)---------------------------------");
		GTUSet<String> testSet = new GTUSet<String>();

		try
		{
			testSet.insert("Muharrem");
			System.out.println(testSet);
			testSet.insert("Ozan");
			System.out.println(testSet);
			testSet.insert("Yesiller");
			System.out.println(testSet);
			testSet.insert("171044033");
			System.out.println(testSet);
			testSet.insert("GTU");
			System.out.println(testSet);
			testSet.insert("171044033");
			System.out.println(testSet);
		}catch(IllegalArgumentException e){
			e.getMessage();
		}

		System.out.println("USED: " + testSet.size());

		if(testSet.empty())
			System.out.println("TEST UNSUCCESSED...");

		System.out.println("------------------------");
		testSet.erase("Muharrem");
		System.out.println("Erasing 'Muharrem'...");
		System.out.println(testSet);
		System.out.println("------------------------");

		System.out.println("------------------------");
		testSet.erase("GTU");
		System.out.println("Erasing 'GTU'...");
		System.out.println(testSet);
		System.out.println("------------------------");

		System.out.println("------------------------");
		testSet.erase("171044033");
		System.out.println("Erasing '171044033'...");
		System.out.println(testSet);
		System.out.println("------------------------");

		if(testSet.contains("Ozan"))
			System.out.println("The Set has string of 'OZAN'");

		System.out.println("------------------------");
		testSet.clear();
		System.out.println("Cleared GTUSet...");
		System.out.println(testSet);
		System.out.println("USED: " + testSet.size());
		System.out.println("------------------------");

		if(testSet.empty())
			System.out.println("GTUSet is Empty...");

		if(testSet.contains("Ozan")) // must not print
			System.out.println("The Set has string of 'OZAN'");

		System.out.println("---------------------------------------------------------------------------");

		System.out.println("---------------------------TEST VECTOR(Int)--------------------------------");
		GTUVector<Integer> testVector2 = new GTUVector<Integer>();

		testVector2.clear(); // testing empty vector clear()

		testVector2.erase(2); // testing empty vector erase(..)
		System.out.println("Vector Capacity: " + testVector2.max_size());
		testVector2.insert(2);
		testVector2.erase(2);
		testVector2.insert(3);
		testVector2.insert(100);
		testVector2.insert(23);
		testVector2.insert(456);
		testVector2.insert(34);
		testVector2.insert(9);
		testVector2.insert(0);
		testVector2.insert(-5);
		testVector2.insert(-765);
		testVector2.insert(43);
		testVector2.insert(82);
		testVector2.insert(-10);
		testVector2.insert(1);
		testVector2.insert(2);
		testVector2.insert(100);
		testVector2.insert(-75);
		testVector2.insert(-765);
		testVector2.insert(-105);
		testVector2.insert(1235);
		testVector2.insert(5);
		testVector2.insert(4355);
		testVector2.insert(100000);
		testVector2.insert(42);


		System.out.println("Contens of Vector: ");
		System.out.println(testVector2);
		System.out.println("Vector Capacity: " + testVector2.max_size());
		System.out.println("Vector Size: " + testVector2.size());
		System.out.println("---------------------------------------\n");



		System.out.println("contains test(for -5 and -10): ");
		Integer test = -5;
		Integer test2 = -10;
		Integer mustNot = -50000;

		if(testVector2.contains(test) && testVector2.contains(-10))
			System.out.println("the vector has -10 and -5");

		if(testVector2.contains(mustNot))
			System.out.println("Test UNSUCCESSED...");
		
		System.out.println("---------------------------------------");
	}
}