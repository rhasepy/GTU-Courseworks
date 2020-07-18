public interface SimpleTextEditor
{
    /**
     * Read method to read in a text file and construct the text.
     * @param filePath, the path of the file to be read
     */
    public void readFile(String filePath);

    /**
     * Add method that adds one or more characters (given as a string) at the specified
     * position (given as an integer index) in your text.
     * @param index, position
     * @param newItem, one or more characters
     */
    public void add(int index, String newItem);

    /**
     * Find method that returns the start index of the first occurrence of the searched
     * group of characters.
     * @param target, target element
     * @return first index of first occurrence characters of the searched target
     */
    public int find(String target);

    /**
     * Replace method that replaces all occurrences of a character with another character.
     * @param from, what will it replace
     * @param to, What will you replace?
     */
    public void replaceAll(char from, char to);

    /**
    * @return, numbers of characters
    */
    public int getSize();
}
