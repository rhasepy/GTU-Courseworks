import java.sql.SQLOutput;

public class WordLinkedList
{
    private Node head = null;
    private Node tail = null;
    private Character cross_char = null;
    private String cross_word;
    private int cross_index = -1;
    private int size = 0;


    private static class Node
    {
        private char data;
        private Node next = null;
        private Node prev = null;
        private Node cross = null;

        public Node(char e)
        {
            data = e;
        }
    }


    public WordLinkedList(String init)
    {
        parse_and_assign(init);
        cross_word = "NONE";
    }

    private void parse_and_assign(String init)
    {
        for(int i = 0 ; i < init.length() ; ++i)
            add(init.charAt(i));
    }

    private void add(char item)
    {
        if(head == null){

            head = new Node(item);
            tail = head;
            ++size;

        } else {

            Node traverse = head;

            while(traverse != null) { traverse = traverse.next; }

            Node newNode = new Node(item);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;

        }

        ++size;
    }

    public boolean addCross(WordLinkedList word, int index)
    {
        Node traverse = head;
        String word_str = word.toString();

        for(int i = 0 ; i < index ; ++i) { traverse = traverse.next; }

        for(int i = 0 ; i < word_str.length() ; ++i)
        {
            if(word_str.charAt(i) == traverse.data && traverse.cross == null)
            {
                this.cross_char = word_str.charAt(i);
                this.cross_word = word_str;
                this.cross_index = index;
                traverse.cross = new Node(word_str.charAt(i));


                Node traverse2 = word.head;
                for(int x = 0 ; x < i ; ++x)
                    traverse2 = traverse2 .next;

                word.cross_char = word_str.charAt(i);
                word.cross_word = this.toString();
                word.cross_index = i;

                traverse2.cross = new Node(this.toString().charAt(index));

                return true;
            }
        }

        return false;
    }

    public int getCross_index(String item)
    {
        int index;

        for(int i = 0 ; i < item.length() ; ++i)
        {
            index = 0;

            for (Node traverse = head; traverse != null; traverse = traverse.next, ++index)
                if (traverse.data == item.charAt(i) && traverse.cross == null)
                    return index;
        }

        return -1;
    }

    /*public void removeCross(int index)
    {

    }*/

    public void printCross()
    {
        System.out.println("\n" + this);
        System.out.println("Cross character: " + this.cross_char);
        System.out.println("Cross word: " + this.cross_word);

        if(cross_index != -1)
            System.out.println("Cross index: " + this.cross_index);
        else
            System.out.println("Cross index: NONE CROSS");
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        Node traverse = head;

        while(traverse != null)
        {
            sb.append(traverse.data);
            traverse = traverse.next;
        }

        return sb.toString();
    }
}
