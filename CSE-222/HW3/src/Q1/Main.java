public class Main
{
	public static void main(String[] args)
	{
		LinkedArrayList<Integer> list = new LinkedArrayList<Integer>();

		System.out.println("The program adding numbers to the data structure...");
		System.out.println("Calls add(E e) method(the push within structre)");
		list.add(Integer.valueOf(2));
		list.add(Integer.valueOf(3));
		list.add(Integer.valueOf(4));
		list.add(Integer.valueOf(6));
		list.add(Integer.valueOf(7));
		list.add(Integer.valueOf(10));
		list.add(Integer.valueOf(1000));
		list.add(Integer.valueOf(-12));
		System.out.println(list);

		System.out.println("Adding 0, 0th index");
		list.add(0, Integer.valueOf(0));
        System.out.println(list);
        System.out.println("Adding 1, 1th index");
        list.add(1, Integer.valueOf(1));
        System.out.println(list);
        System.out.println("Adding 5, 5th index");
        list.add(5, Integer.valueOf(5));
        System.out.println(list);
        System.out.println("Adding 8, 8th index");
        list.add(8, Integer.valueOf(8));
        System.out.println(list);
        System.out.println("Adding 9, 9th index");
        list.add(9, Integer.valueOf(9));
        System.out.println(list);

        System.out.println("Removing 12th index");
        list.remove(12);
        System.out.println(list);
        System.out.println("Removing 11th index");
        list.remove(11);
        System.out.println(list);
        System.out.println("Removing 10th index");
        list.remove(10);
        System.out.println(list);
        System.out.println("Removing 0th index");
        list.remove(0);
        System.out.println(list);
        System.out.println("Removing value of '9' (calls remove(Object e)");
        list.remove(Integer.valueOf(9));
        System.out.println(list);
        System.out.println("Removing value of '6' (calls remove(Object e)");
        list.remove(Integer.valueOf(6));
        System.out.println(list);
        System.out.println("Adding 6, 5th index");
        list.add(5, Integer.valueOf(6));
        System.out.println(list);
        System.out.println("Adding value of '9' (push so add last element)");
        list.add(Integer.valueOf(9));
        System.out.println(list);
        System.out.println("Adding value of '10'");
        list.add(Integer.valueOf(10));
        System.out.println(list);
        System.out.println("Removing 2th index");
        list.remove(2);
        System.out.println(list);
        System.out.println("Removing 4th index");
        list.remove(4);
        System.out.println(list);
	}
}