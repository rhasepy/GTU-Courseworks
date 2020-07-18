import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class SimpleTextEditorWithLoop implements SimpleTextEditor
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
    public SimpleTextEditorWithLoop(List<Character> init, String filePath)
    {
        book = init;
        readFile(filePath);
    }

    /**
     * Read method to read in a text file and construct the text.
     * with using loop
     * @param filePath, the path of the file to be read
     */
    public void readFile(String filePath)
    {
        String line;

        try {

            File f = new File(filePath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while( (line = br.readLine()) != null) { parse_assign(line); }

            fr.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Editor is empty...");

        }
    }

    /**
     * my helper method
     * adds and adds the received line to characters.
     * with loop
     * @param line, line from file
     */
    private void parse_assign(String line)
    {
        for(int i = 0 ; i < line.length() ; ++i)
            book.add(line.charAt(i));

        book.add('\n');
    }

    /**
     * newItem parses and adds the string from the start index.
     * with loop
     * @param index, position
     * @param newItem, one or more characters
     */
    public void add(int index, String newItem)
    {
        for(int i = 0 ; i < newItem.length() ; ++i, ++index)
            book.add(index, newItem.charAt(i));
    }

    /**
     * Find method that returns the start index of the first occurrence of the searched
     * group of characters.
     * with loop
     * @param target, target element
     * @return first index of first occurrence characters of the searched target
     */
    public int find(String target)
    {
        int newLine_count = 0;

        for(int i = 0 ; i < book.size() ; ++i)
        {
            if(book.get(i) == '\n') {
                ++newLine_count;
            }

            if(target.charAt(0) == book.get(i))
            {
                if(compare(i, target))
                    return i - newLine_count;
            }
        }

        return -1;
    }

    /**
     * It compares the word with high probability and returns true if this is the word we are looking for.
     * with loop
     * @param index, beginning index of possible word to search
     * @param newItem, the real word to look for
     * @return true if this is the word we are looking for
     * @return false if this is not the word we are looking for
     */
    private boolean compare(int index, String newItem)
    {
        int target_index = 0;

        for(int i = 0 ; i < newItem.length() ; ++i)
        {
            if(book.get(index) != newItem.charAt(target_index))
                return false;

            ++target_index;
            ++index;
        }

        return true;
    }

    /**
     * Replace method that replaces all occurrences of a character with another character.
     * with loop
     * @param from, what will it replace
     * @param to, What will you replace?
     */
    public void replaceAll(char from, char to)
    {
        for(int i = 0 ; i < book.size() ; ++i)
            if(book.get(i) == from)
                book.set(i, to);
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
