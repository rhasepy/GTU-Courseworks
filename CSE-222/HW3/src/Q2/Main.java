import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Main 
{
	public static void main(String[] args) 
	{
		try {

			Logger logger = Logger.getLogger("HW3_Q2LOG");
			FileHandler fh;

			fh = new FileHandler("HW3_Q2.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

	        /************WITH ITERATOR TEST***********************/
	        ///////////////////////////////////////////////////////////////////////////READ TEST ///////////////////////////////////
	       logger.info("all tests are applied to editor with iterator for now");
	       logger.info("READ TEST");

	        Path current_path = Paths.get("res/one.txt");
	        String fpath = current_path.toAbsolutePath().toString();
	        SimpleTextEditor book_iter_ll = new SimpleTextEditorWithIterator(new LinkedList<>(), fpath);

	        Random rand = new Random();
	        int rand_integer1 = rand.nextInt(book_iter_ll.getSize());
	        int rand_integer2 = rand.nextInt(book_iter_ll.getSize());

	        logger.info("This text has " + book_iter_ll.getSize() + " characters.");
	        logger.info("\n----PARAGRAPH1----");
	        logger.info(book_iter_ll.toString());
	        logger.info("----PARAGRAPH1----");

	        logger.info("READ TEST SUCCESS\n");
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        ///////////////////////////////////////////// ADD TEST ///////////////////////////////////////////////////////////////////
	        logger.info("ADD TEST"); 

	        logger.info("Add 'Ozan' to 0th index");
	        logger.info("Add 'Muharrem ' to 0th index");
	        logger.info("Add ' Yesiller,' to last index");
	        logger.info("Add ' GTU.' to last index");
	        logger.info("Add '99999999999' to random index to between characters");
	        logger.info("Add 'ASDASDADA' to random index to between characters");
	        logger.info("Add 15. index '!'");
	        logger.info("\n...THE PROOF TEXT...\n");

	        book_iter_ll.add(0, "Ozan");
	        book_iter_ll.add(0, "Muharrem ");
	        book_iter_ll.add(book_iter_ll.getSize()-1, " Yesiller,");
	        book_iter_ll.add(book_iter_ll.getSize()-1, " GTU.");
	        book_iter_ll.add(rand_integer1, "99999999999");
	        book_iter_ll.add(rand_integer2, "ASDASDADA");
	        book_iter_ll.add(15, "!");

	        logger.info(book_iter_ll.toString());

	        logger.info("ADD TEST SUCCESS\n");
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        ///////////////////////////////////////////// FIND TEST ///////////////////////////////////////////////////////////////////
	        logger.info("FIND TEST");

	        logger.info("Program find 'Ozan'... -> first index of 'Ozan' is " + book_iter_ll.find("Ozan"));
	        logger.info("Program find 'GTU'... -> first index of 'GTU' is " + book_iter_ll.find("GTU"));
	        logger.info("Program find '.'... -> first index of '.' is " + book_iter_ll.find("."));
	        logger.info("Program find '999'... -> first index of '999' is " + book_iter_ll.find("999"));

	        logger.info("FIND TEST SUCCESS\n");
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			//////////////////////////////////////////////////REPLACE TEST/////////////////////////////////////////////////////////////
	        logger.info("REPLACE TEST");

	        logger.info("Program replace character from 'a' to 'A'...");
	        book_iter_ll.replaceAll('a', 'A');
	        logger.info("Program replace character from 'Y' to 'y'...");
	        book_iter_ll.replaceAll('Y', 'y');
	        logger.info("Program replace character from '.' to '-'...");
	        book_iter_ll.replaceAll('.', '-');

	        logger.info("\n" + book_iter_ll);

	        logger.info("REPLACE TEST SUCCESS\n");
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        /************************************************/


			/************WITHOUT LOOP TEST********************/
			logger.info("\n\nall tests are applied to editor with loop for now");
	        logger.info("READ TEST");
	        SimpleTextEditor book_loop_ll = new SimpleTextEditorWithIterator(new LinkedList<>(), fpath);

	        int rand_integer1_2 = rand.nextInt(book_loop_ll.getSize());
	        int rand_integer2_2 = rand.nextInt(book_loop_ll.getSize());

	        logger.info("This text has " + book_loop_ll.getSize() + " characters.");
	        logger.info("\n----PARAGRAPH1----");
	        logger.info(book_loop_ll.toString());
	        logger.info("----PARAGRAPH1----");

	        logger.info("READ TEST SUCCESS\n");
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        ///////////////////////////////////////////// ADD TEST ///////////////////////////////////////////////////////////////////
	        logger.info("ADD TEST"); 

	        logger.info("Add 'Ozan' to 0th index");
	        logger.info("Add 'Muharrem ' to 0th index");
	        logger.info("Add ' Yesiller,' to last index");
	        logger.info("Add ' GTU.' to last index");
	        logger.info("Add '99999999999' to random index to between characters");
	        logger.info("Add 'ASDASDADA' to random index to between characters");
	        logger.info("Add 15. index '!'");
	        logger.info("\n...THE PROOF TEXT...\n");

	        book_loop_ll.add(0, "Ozan");
	        book_loop_ll.add(0, "Muharrem ");
	        book_loop_ll.add(book_loop_ll.getSize()-1, " Yesiller,");
	        book_loop_ll.add(book_loop_ll.getSize()-1, " GTU.");
	        book_loop_ll.add(rand_integer1_2, "99999999999");
	        book_loop_ll.add(rand_integer2_2, "ASDASDADA");
	        book_loop_ll.add(15, "!");

	        logger.info(book_loop_ll.toString());

	        logger.info("ADD TEST SUCCESS\n");
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        ///////////////////////////////////////////// FIND TEST ///////////////////////////////////////////////////////////////////
	        logger.info("FIND TEST");

	        logger.info("Program find 'Ozan'... -> first index of 'Ozan' is " + book_loop_ll.find("Ozan"));
	        logger.info("Program find 'GTU'... -> first index of 'GTU' is " + book_loop_ll.find("GTU"));
	        logger.info("Program find '.'... -> first index of '.' is " + book_loop_ll.find("."));
	        logger.info("Program find '999'... -> first index of '999' is " + book_loop_ll.find("999"));

	        logger.info("FIND TEST SUCCESS\n");
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			//////////////////////////////////////////////////REPLACE TEST/////////////////////////////////////////////////////////////
	        logger.info("REPLACE TEST");

	        logger.info("Program replace character from 'a' to 'A'...");
	        book_loop_ll.replaceAll('a', 'A');
	        logger.info("Program replace character from 'Y' to 'y'...");
	        book_loop_ll.replaceAll('Y', 'y');
	        logger.info("Program replace character from '.' to '-'...");
	        book_loop_ll.replaceAll('.', '-');

	        logger.info("\n" + book_loop_ll.toString());

	        logger.info("REPLACE TEST SUCCESS\n");
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        Path second_file_path = Paths.get("res/two.txt");
	        String fpath2 = second_file_path.toAbsolutePath().toString();
	        int file_size_twotxt;

	        long[][] times = new long[4][4];// for row (Linkedlist loop and iterator) and (Arraylist loop and iterator)
	        				// for column READ, ADD, FIND, REPLACE
	        long[][] times_start = new long[4][4]; 
	        long[][] times_last = new long[4][4];
	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start[0][0] = System.nanoTime();
	        SimpleTextEditor test_linkedlist_loop =  new SimpleTextEditorWithLoop(new LinkedList<>(), fpath2);
	        file_size_twotxt = test_linkedlist_loop.getSize();
	        times_last[0][0] = System.nanoTime();

	        times_start[0][1] = System.nanoTime();
	        test_linkedlist_loop.add(0, "asd");
	        test_linkedlist_loop.add(test_linkedlist_loop.getSize(), "sfsdfsd");
	        test_linkedlist_loop.add(test_linkedlist_loop.getSize() / 2, "1232312");
	        test_linkedlist_loop.add(test_linkedlist_loop.getSize(), "33aaaa" );
	        test_linkedlist_loop.add(test_linkedlist_loop.getSize() - 15, "3refc33");
	        times_last[0][1] = System.nanoTime();

	        times_start[0][2] = System.nanoTime();
	        test_linkedlist_loop.find("asd");
	        test_linkedlist_loop.find("sfs");
	        test_linkedlist_loop.find("are");
	        test_linkedlist_loop.find("2");
	        test_linkedlist_loop.find("co");
	        times_last[0][2] = System.nanoTime();

	        times_start[0][3] = System.nanoTime();
	        test_linkedlist_loop.replaceAll('a', 'A');
	        test_linkedlist_loop.replaceAll('b', 'B');
	        test_linkedlist_loop.replaceAll('n', 'N');
	        test_linkedlist_loop.replaceAll('b', 'p');
	        test_linkedlist_loop.replaceAll('H', 'h');
	        times_last[0][3] = System.nanoTime();

	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start[1][0] = System.nanoTime();
	        SimpleTextEditor test_linkedlist_iter =  new SimpleTextEditorWithIterator(new LinkedList<>(), fpath2);
	        times_last[1][0] = System.nanoTime();

	        times_start[1][1] = System.nanoTime();
	        test_linkedlist_iter.add(0, "asd");
	        test_linkedlist_iter.add(test_linkedlist_iter.getSize(), "sfsdfsd");
	        test_linkedlist_iter.add(test_linkedlist_iter.getSize()/2, "1232312");
	        test_linkedlist_iter.add(test_linkedlist_iter.getSize(), "33aaaa" );
	        test_linkedlist_iter.add(test_linkedlist_iter.getSize() - 15, "3refc33");
	        times_last[1][1] = System.nanoTime();

	        times_start[1][2] = System.nanoTime();
	        test_linkedlist_iter.find("asd");
	        test_linkedlist_iter.find("sfs");
	        test_linkedlist_iter.find("are");
	        test_linkedlist_iter.find("2");
	        test_linkedlist_iter.find("co");
	        times_last[1][2] = System.nanoTime();

	        times_start[1][3] = System.nanoTime();
	        test_linkedlist_iter.replaceAll('a', 'A');
	        test_linkedlist_iter.replaceAll('b', 'B');
	        test_linkedlist_iter.replaceAll('n', 'N');
	        test_linkedlist_iter.replaceAll('b', 'p');
	        test_linkedlist_iter.replaceAll('H', 'h');
	        times_last[1][3] = System.nanoTime();

	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start[2][0] = System.nanoTime();
	        SimpleTextEditor test_arraylist_loop =  new SimpleTextEditorWithLoop(new ArrayList<>(), fpath2);
	        times_last[2][0] = System.nanoTime();

	        times_start[2][1] = System.nanoTime();
	        test_arraylist_loop.add(0, "asd");
	        test_arraylist_loop.add(test_arraylist_loop.getSize(), "sfsdfsd");
	        test_arraylist_loop.add(test_arraylist_loop.getSize()/2, "1232312");
	        test_arraylist_loop.add(test_arraylist_loop.getSize(), "33aaaa" );
	        test_arraylist_loop.add(test_arraylist_loop.getSize() - 15, "3refc33");
	        times_last[2][1] = System.nanoTime();

	        times_start[2][2] = System.nanoTime();
	        test_arraylist_loop.find("asd");
	        test_arraylist_loop.find("sfs");
	        test_arraylist_loop.find("are");
	        test_arraylist_loop.find("2");
	        test_arraylist_loop.find("co");
	        times_last[2][2] = System.nanoTime();

	        times_start[2][3] = System.nanoTime();
	        test_arraylist_loop.replaceAll('a', 'A');
	        test_arraylist_loop.replaceAll('b', 'B');
	        test_arraylist_loop.replaceAll('n', 'N');
	        test_arraylist_loop.replaceAll('b', 'p');
	        test_arraylist_loop.replaceAll('H', 'h');
	        times_last[2][3] = System.nanoTime();
	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start[3][0] = System.nanoTime();
	        SimpleTextEditor test_arraylist_iter =  new SimpleTextEditorWithIterator(new ArrayList<>(), fpath2);
	        times_last[3][0] = System.nanoTime();

	        times_start[3][1] = System.nanoTime();
	        test_arraylist_iter.add(0, "asd");
	        test_arraylist_iter.add(test_arraylist_iter.getSize(), "sfsdfsd");
	        test_arraylist_iter.add(test_arraylist_iter.getSize()/2, "1232312");
	        test_arraylist_iter.add(test_arraylist_iter.getSize(), "33aaaa" );
	        test_arraylist_iter.add(test_arraylist_iter.getSize() - 15, "3refc33");
	        times_last[3][1] = System.nanoTime();

	        times_start[3][2] = System.nanoTime();
	        test_arraylist_iter.find("asd");
	        test_arraylist_iter.find("sfs");
	        test_arraylist_iter.find("are");
	        test_arraylist_iter.find("2");
	        test_arraylist_iter.find("co");
	        times_last[3][2] = System.nanoTime();

	        times_start[3][3] = System.nanoTime();
	        test_arraylist_iter.replaceAll('a', 'A');
	        test_arraylist_iter.replaceAll('b', 'B');
	        test_arraylist_iter.replaceAll('n', 'N');
	        test_arraylist_iter.replaceAll('b', 'p');
	        test_arraylist_iter.replaceAll('H', 'h');
	        times_last[3][3] = System.nanoTime();

	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        Path third_file_path = Paths.get("res/three.txt");
	        String fpath3 = third_file_path.toAbsolutePath().toString();
	        int file_size_threetxt;

	        long[][] times_ = new long[4][4];// for row (Linkedlist loop and iterator) and (Arraylist loop and iterator)
	        				// for column READ, ADD, FIND, REPLACE
	        long[][] times_start_ = new long[4][4]; // 0->READ, 1->ADD, 2->FIND, 3->REPLACE
	        long[][] times_last_ = new long[4][4]; // 0->READ, 1->ADD, 2->FIND, 3->REPLACE

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start_[0][0] = System.nanoTime();
	        SimpleTextEditor test_linkedlist_loop_2 =  new SimpleTextEditorWithLoop(new LinkedList<>(), fpath3);
	        file_size_threetxt = test_linkedlist_loop_2.getSize();
	        times_last_[0][0] = System.nanoTime();

	        times_start_[0][1] = System.nanoTime();
	        test_linkedlist_loop_2.add(0, "asd");
	        test_linkedlist_loop_2.add(test_linkedlist_loop_2.getSize(), "sfsdfsd");
	        test_linkedlist_loop_2.add(test_linkedlist_loop_2.getSize()/2, "1232312");
	        test_linkedlist_loop_2.add(test_linkedlist_loop_2.getSize(), "33aaaa" );
	        test_linkedlist_loop_2.add(test_linkedlist_loop_2.getSize() - 15, "3refc33");
	        times_last_[0][1] = System.nanoTime();

	        times_start_[0][2] = System.nanoTime();
	        test_linkedlist_loop_2.find("asd");
	        test_linkedlist_loop_2.find("sfs");
	        test_linkedlist_loop_2.find("are");
	        test_linkedlist_loop_2.find("2");
	        test_linkedlist_loop_2.find("co");
	        times_last_[0][2] = System.nanoTime();

	        times_start_[0][3] = System.nanoTime();
	        test_linkedlist_loop_2.replaceAll('a', 'A');
	        test_linkedlist_loop_2.replaceAll('b', 'B');
	        test_linkedlist_loop_2.replaceAll('n', 'N');
	        test_linkedlist_loop_2.replaceAll('b', 'p');
	        test_linkedlist_loop_2.replaceAll('H', 'h');
	        times_last_[0][3] = System.nanoTime();
	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start_[1][0] = System.nanoTime();
	        SimpleTextEditor test_linkedlist_iter_2 =  new SimpleTextEditorWithIterator(new LinkedList<>(), fpath3);
	        times_last_[1][0] = System.nanoTime();

	        times_start_[1][1] = System.nanoTime();
	        test_linkedlist_iter_2.add(0, "asd");
	        test_linkedlist_iter_2.add(test_linkedlist_iter_2.getSize(), "sfsdfsd");
	        test_linkedlist_iter_2.add(test_linkedlist_iter_2.getSize()/2, "1232312");
	        test_linkedlist_iter_2.add(test_linkedlist_iter_2.getSize(), "33aaaa" );
	        test_linkedlist_iter_2.add(test_linkedlist_iter_2.getSize() - 15, "3refc33");
	        times_last_[1][1] = System.nanoTime();

	        times_start_[1][2] = System.nanoTime();
	        test_linkedlist_iter_2.find("asd");
	        test_linkedlist_iter_2.find("sfs");
	        test_linkedlist_iter_2.find("are");
	        test_linkedlist_iter_2.find("2");
	        test_linkedlist_iter_2.find("co");
	        times_last_[1][2] = System.nanoTime();

	        times_start_[1][3] = System.nanoTime();
	        test_linkedlist_iter_2.replaceAll('a', 'A');
	        test_linkedlist_iter_2.replaceAll('b', 'B');
	        test_linkedlist_iter_2.replaceAll('n', 'N');
	        test_linkedlist_iter_2.replaceAll('b', 'p');
	        test_linkedlist_iter_2.replaceAll('H', 'h');
	        times_last_[1][3] = System.nanoTime();
	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start_[2][0] = System.nanoTime();
	        SimpleTextEditor test_arraylist_loop_2 =  new SimpleTextEditorWithLoop(new ArrayList<>(), fpath3);
	        times_last_[2][0] = System.nanoTime();

	        times_start_[2][1] = System.nanoTime();
	        test_arraylist_loop_2.add(0, "asd");
	        test_arraylist_loop_2.add(test_arraylist_loop_2.getSize(), "sfsdfsd");
	        test_arraylist_loop_2.add(test_arraylist_loop_2.getSize()/2, "1232312");
	        test_arraylist_loop_2.add(test_arraylist_loop_2.getSize(), "33aaaa" );
	        test_arraylist_loop_2.add(test_arraylist_loop_2.getSize() - 15, "3refc33");
	        times_last_[2][1] = System.nanoTime();

	        times_start_[2][2] = System.nanoTime();
	        test_arraylist_loop_2.find("asd");
	        test_arraylist_loop_2.find("sfs");
	        test_arraylist_loop_2.find("are");
	        test_arraylist_loop_2.find("2");
	        test_arraylist_loop_2.find("co");
	        times_last_[2][2] = System.nanoTime();

	        times_start_[2][3] = System.nanoTime();
	        test_arraylist_loop_2.replaceAll('a', 'A');
	        test_arraylist_loop_2.replaceAll('b', 'B');
	        test_arraylist_loop_2.replaceAll('n', 'N');
	        test_arraylist_loop_2.replaceAll('b', 'p');
	        test_arraylist_loop_2.replaceAll('H', 'h');
	        times_last_[2][3] = System.nanoTime();
	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        //////////////////////////////////////////////////////////////////////////////////////////////////////
	        times_start_[3][0] = System.nanoTime();
	        SimpleTextEditor test_arraylist_iter_2 =  new SimpleTextEditorWithIterator(new ArrayList<>(), fpath3);
	        times_last_[3][0] = System.nanoTime();

	        times_start_[3][1] = System.nanoTime();
	        test_arraylist_iter_2.add(0, "asd");
	        test_arraylist_iter_2.add(test_arraylist_iter_2.getSize(), "sfsdfsd");
	        test_arraylist_iter_2.add(test_arraylist_iter_2.getSize()/2, "1232312");
	        test_arraylist_iter_2.add(test_arraylist_iter_2.getSize(), "33aaaa" );
	        test_arraylist_iter_2.add(test_arraylist_iter_2.getSize() - 15, "3refc33");
	        times_last_[3][1] = System.nanoTime();

	        times_start_[3][2] = System.nanoTime();
	        test_arraylist_iter_2.find("asd");
	        test_arraylist_iter_2.find("sfs");
	        test_arraylist_iter_2.find("are");
	        test_arraylist_iter_2.find("2");
	        test_arraylist_iter_2.find("co");
	        times_last_[3][2] = System.nanoTime();

	        times_start_[3][3] = System.nanoTime();
	        test_arraylist_iter_2.replaceAll('a', 'A');
	        test_arraylist_iter_2.replaceAll('b', 'B');
	        test_arraylist_iter_2.replaceAll('n', 'N');
	        test_arraylist_iter_2.replaceAll('b', 'p');
	        test_arraylist_iter_2.replaceAll('H', 'h');
	        times_last_[3][3] = System.nanoTime();
	        //////////////////////////////////////////////////////////////////////////////////////////////////////

	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	        for(int i = 0 ; i < 4 ; ++i)
	        	for(int j = 0 ; j < 4 ; ++j)
	        	{
	        		times[i][j] = (times_last[i][j] - times_start[i][j]);
	        		times_[i][j] = (times_last_[i][j] - times_start_[i][j]);
	        	}

	        logger.info("-------------------------------------------------------------------------");
	        logger.info("\n\nTIMES FOR two.txt. two.txt has " + file_size_twotxt + " characters.\n");
	        logger.info("-------------------------------------------------------------------------");
	        logger.info("The Linked list with loop: ");
			logger.info(print(times, 0));

	        logger.info("The Linked list with iterator: ");
	        logger.info(print(times, 1));

	        logger.info("The Array list with loop: ");
			logger.info(print(times, 2));

	        logger.info("The Array list with iterator: ");
			logger.info(print(times, 3));	

	        logger.info("-------------------------------------------------------------------------");
	        logger.info("\n\nTIMES FOR three.txt. three.txt has " + file_size_threetxt + " characters.\n");
	        logger.info("-------------------------------------------------------------------------");
	        logger.info("The Linked list with loop: ");
			logger.info(print(times_, 0));

	        logger.info("The Linked list with iterator: ");
	        logger.info(print(times_, 1));

	        logger.info("The Array list with loop: ");
	        logger.info(print(times_, 2));

	        logger.info("The Array list with iterator: ");
	        logger.info(print(times_, 3));

	    }catch(Exception e) { 

	    	e.printStackTrace();

	    }
	}

	public static String print(long[][] times, int which)
	{
		StringBuilder sb = new StringBuilder();

		sb.append("\n-------------------\n");
	 	for(int i = 0 ; i < 4 ; ++i)
	 	{
	 		switch(i)
	 		{
	 			case 0:
	 			sb.append("READ: ");
	 			break;

	 			case 1:
	 			sb.append("ADD: ");
	 			break;

	 			case 2:
	 			sb.append("FIND: ");
	 			break;

	 			case 3:
	 			sb.append("REPLACE: ");
	 			break;
	 		}
	 		sb.append(times[which][i] + " ns\n");
	 	}
	 	sb.append("-------------------\n");

	 	return sb.toString();
	} 
}		