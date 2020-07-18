import java.util.LinkedList;
import java.util.List;

public class CrossWordPuzzle
{
    private List <WordLinkedList> list;

    public CrossWordPuzzle(List <WordLinkedList> param)
    {
        list = param;
    }

    public void addWord(String word)
    {
        int index;
        WordLinkedList newItem = new WordLinkedList(word);

        for (WordLinkedList wordLinkedList : list)
            if((index = wordLinkedList.getCross_index(word)) != -1)
            {
                list.add(newItem);
                wordLinkedList.addCross(newItem, index);
                return;
            }

        list.add(newItem);
    }

    /*public void removeWord(String word)
    {

    }*/

    public void print()
    {
        System.out.println("-----------------------");
        for (WordLinkedList wordLinkedList : list)
            wordLinkedList.printCross();
        System.out.println("-----------------------");
    }
}
