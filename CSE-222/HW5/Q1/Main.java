import java.io.File;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        FileSystemTree myFileSystem = new FileSystemTree("root");
        myFileSystem.addDir("root/first_directory");
        myFileSystem.addDir("root/second_directory");
        myFileSystem.addFile("root/first_directory/new_file.txt");
        myFileSystem.addDir("root/second_directory/new_directory");
        myFileSystem.addFile("root/second_directory/new_directory/new_file.doc");
        myFileSystem.printFileSystem();
        myFileSystem.search("new");
        myFileSystem.remove("root/first_directory");
        System.out.println(myFileSystem);
    }
}