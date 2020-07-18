public class Main
{
    public static void main(String[] args)
    {
        MyDeque <String> test = new MyDeque<String>();

        System.out.println("Add First Test");
        System.out.println("Adding some words");
        test.addFirst("Ozan");
        test.addFirst("171044033");
        test.add("Yesiller");
        System.out.print(test);
        System.out.println("Add first test success");

        System.out.println("\nAdd Last Test");
        System.out.println("Adding some words");
        test.addLast("Gebze");
        test.addLast("Technical");
        test.addLast("University");
        System.out.print(test);
        System.out.println("Add Last test success");

        System.out.println("\nOffer first Test");
        System.out.println("Adding some words");
        test.offerFirst("Basak");
        test.offerFirst("Karakas");
        test.offerFirst("GTU");
        System.out.print(test);
        System.out.println("Offer first test success");

        System.out.println("\nOffer last Test");
        System.out.println("Adding some words");
        test.offerLast("Fatih");
        test.offerLast("Erdogan");
        test.offerLast("Sevilgen");
        System.out.print(test);
        System.out.println("Offer last test success");

        System.out.println("\nRemove first test");
        test.removeFirst();
        System.out.println(test);
        test.removeFirst();
        System.out.println(test);
        test.removeFirst();
        System.out.println(test);
        System.out.println("Remove first test success");

        System.out.println("\nRemove last test");
        test.removeLast();
        System.out.println(test);
        test.removeLast();
        System.out.println(test);
        test.removeLast();
        System.out.println(test);
        System.out.println("Remove last test success");

        System.out.println("\nPoll first test");
        test.pollFirst();
        System.out.println(test);
        test.pollFirst();
        System.out.println(test);
        test.pollFirst();
        System.out.println(test);
        System.out.println("Poll first test success");

        System.out.println("\nPoll last test");
        test.pollLast();
        System.out.println(test);
        test.pollLast();
        System.out.println(test);
        test.pollLast();
        System.out.println(test);
        System.out.println("Poll last test success");

        System.out.println("\nadd(E e) and garbage collecting test");
        test.add("GTU");
        test.add("CSE222");
        test.add("ALGO&DAT");
        System.out.println(test);

        System.out.println("Contains Test");
        System.out.println("Searching 'CSE222'");
        if(test.contains("CSE222"))
            System.out.println("Found");
        else
            System.out.println("Not found");

        System.out.println("Searching 'Ozan'");
        if(test.contains("Ozan"))
            System.out.println("Found");
        else
            System.out.println("Not found");


        System.out.println("\nPush(E e) test");
        test.push("Nur");
        System.out.println(test);
        test.push("Banu");
        System.out.println(test);
        test.push("Albayrak");
        System.out.println(test);
        System.out.println("Test success");


        System.out.println("\nPop test");
        test.pop();
        System.out.println(test);
        test.pop();
        System.out.println(test);
        test.pop();
        System.out.println(test);
        System.out.println("Test success");

        System.out.println("\nGet test");
        System.out.println("The getFirst() is -> " + test.getFirst());
        System.out.println("The getLast() is ->  " + test.getLast());
        System.out.println("The element() is -> " + test.element());
        System.out.println("Test success");

        System.out.println("\nPeek Test");
        System.out.println("The peekFirst() is -> " + test.peekFirst());
        System.out.println("The peekLast() is -> " + test.peekLast());
        System.out.println("The peek() is -> " + test.peek());
        System.out.println("Test success");

        System.out.println("\nOffer() test");
        test.offer("GTU");
        test.offer("Faculty of");
        test.offer("Computer Engineering");
        System.out.println(test);
        System.out.println("Test success");

        System.out.println("\nRemove First Occurence of GTU");
        test.removeFirstOccurrence("GTU");
        System.out.println(test);
        System.out.println("Test success");

        System.out.println("\nRemove Last Occurence of GTU");
        test.removeLastOccurrence("GTU");
        System.out.println(test);
        System.out.println("Test success");

        System.out.println("\npoll() test(two times)");
        test.poll();
        test.poll();
        System.out.println(test);
        System.out.println("Test success");

        System.out.println("\nremove() test(two times)");
        test.remove();
        test.remove();
        System.out.println(test);
        System.out.println("Test Success");
    }
}
