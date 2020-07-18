import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.ListIterator;

public class SimpleTextEditorWithIterator implements SimpleTextEditor
{
    /**
     * list of characters
     */
    private List<Character> book;

    /**
     * two parameter constructor
     * @param init, The character string held by the list can be one of many list structures.
     * @param filePath, the path of the file to be read
     */
    public SimpleTextEditorWithIterator(List<Character> init, String filePath)
    {
        book = init;
        readFile(filePath);
    }

    /**
     * Read method to read in a text file and construct the text.
     * with using list iterator
     * @param filePath, the path of the file to be read
     */
    public void readFile(String filePath)
    {
        try{

            File f = new File(filePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int ascii_char = 0;

            ListIterator<Character> iter = book.listIterator();

            while((ascii_char = br.read()) != -1) { iter.add( (char) ascii_char); }

            fr.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Editor is empty...");
        }
    }

    /**
     * newItem parses and adds the string from the start index.
     * with list iterator
     * @param index, position
     * @param newItem, one or more characters
     */
    public void add(int index, String newItem)
    {
        ListIterator <Character> iter = book.listIterator(index);

        for(int i = 0 ; i < newItem.length(); ++i)
            iter.add(newItem.charAt(i));
    }

    /**
     * Find method that returns the start index of the first occurrence of the searched
     * group of characters.
     * with list iterator
     * @param target, target element
     * @return first index of first occurrence characters of the searched target
     */
    public int find(String target)
    {
        ListIterator <Character> iter = book.listIterator();
        int newLine_count = 0;

        while(iter.hasNext())
        {
            int index = iter.nextIndex();
            char element = iter.next();

            if(element == '\n')
                ++newLine_count;

            if(target.charAt(0) == element)
                if(compare(index, target))
                    return index - newLine_count;
        }

        return -1;
    }

    /**
     * It compares the word with high probability and returns true if this is the word we are looking for.
     * with list iterator
     * @param index, beginning index of possible word to search
     * @param newItem, the real word to look for
     * @return true if this is the word we are looking for
     * @return false if this is not the word we are looking for
     */
    private boolean compare(int index, String newItem)
    {
        ListIterator <Character> iter = book.listIterator(index);

        for(int i = 0 ; i < newItem.length() ; ++i)
        {
            if(iter.next() != newItem.charAt(i))
                return false;
        }

        return true;
    }

    /**
     * Replace method that replaces all occurrences of a character with another character.
     * with list iterator
     * @param from, what will it replace
     * @param to, What will you replace?
     */
    public void replaceAll(char from, char to)
    {
        ListIterator <Character> iter = book.listIterator();

        while(iter.hasNext())
        {
            if (iter.next().equals(from))
                iter.set(to);
        }
    }

    /**
    * @return, numbers of characters
    */
    public int getSize() { return book.size(); }

    /**
     * overriding toString method
     * @return string of character list
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        for(int i = 0 ; i < book.size() ; ++i)
            str.append(book.get(i).toString());

        return str.toString();
    }
}
