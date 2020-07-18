import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * File System Tree class
 * @author Muharrem Ozan Yesiller 171044033
 */
public class FileSystemTree
{
    /**
     * root of file system tree
     */
    private FileNode root;

    /**
     * private static inner FileNode class for FileSystemTree hold together data
     */
    private static class FileNode
    {
        /**
         * name of current data
         */
        private String current_data;

        /**
         * path of current data
         */
        private String current_path;

        /**
         * children list node for directory
         */
        private List<FileNode> dir_children;

        /**
         * children list node for file
         */
        private List<FileNode> file_children;

        /**
         * two parameter constructor
         * @param arg name of data
         * @param path path of data
         */
        public FileNode(String arg, String path)
        {
            dir_children = new LinkedList<FileNode>();
            file_children = new LinkedList<FileNode>();

            current_data = arg;
            current_path = path;
        }

        /**
         * Helper method that do retrieve node is target that be parameter
         * @param target directory or file that users want
         * @return target directory or file
         */
        private FileNode getNewRoot(String target)
        {
            for (FileNode dir_child : dir_children) {
                if (dir_child.current_data.equals(target))
                    return dir_child;
            }

            return null;
        }

        /**
         * @Override equals method
         * @param obj to be compared object
         * @return true if obj equals this, otherwise false
         */
        @Override
        public boolean equals(Object obj)
        {
            if(obj instanceof FileNode)
            {
                FileNode temp = (FileNode) obj;

                return this.current_data.equals(temp.current_data);
            }

            return false;
        }

        /**
         * Override to String method
         * @return string of this object
         */
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();

            sb.append("Content of ");
            sb.append(current_data);
            sb.append(": \n");

            if(this.dir_children.size() > 0 || this.file_children.size() > 0) {

                for(FileNode o : dir_children)
                {
                    sb.append("dir - ");
                    sb.append(o.current_path);
                    sb.append("\n");
                }

                for (FileNode o : file_children)
                {
                    sb.append("file - ");
                    sb.append(o.current_path);
                    sb.append("\n");
                }

            } else sb.append("EMPTY\n");

            return sb.toString();
        }
    }

    /**
     * One parameter constructor
     * @param arg path of root directoy or file
     */
    public FileSystemTree(String arg) { root = new FileNode(arg, arg); }

    /**
     * Adding directory in file system tree
     * If there is no file it proceeds by creating
     * @param path of directory
     */
    public void addDir(String path)
    {
        String[] directories = path.split("/");
        StringBuilder current_path = new StringBuilder();
        FileNode localRoot = root;

        if(directories[0].equals(root.current_data))
        {
            current_path.append(directories[0]);
            current_path.append("/");

            for(int i = 1 ; i < directories.length ; ++i)
            {
                /*
                Because can not add directory in file
                 */
                if(isFile(directories[i]))
                    throw new IllegalArgumentException("Can not generate directory in file...");

                current_path.append(directories[i]);
                current_path.append("/");

                assert localRoot != null;
                if(localRoot.dir_children.contains(new FileNode(directories[i], current_path.toString())))
                    localRoot = localRoot.getNewRoot(directories[i]);

                else
                {
                    localRoot.dir_children.add(new FileNode(directories[i], current_path.toString()));
                    localRoot = localRoot.getNewRoot(directories[i]);
                }
            }
        } else throw new NullPointerException("Invalid root directory in system...");
    }

    /**
     * Adding file in file system tree
     * If there is no file it proceeds by creating
     * @param path of file
     */
    public void addFile(String path)
    {
        String[] directories = path.split("/");
        StringBuilder current_path = new StringBuilder();
        FileNode localRoot = root;
        boolean fileFlag = false;

        if(directories[0].equals(root.current_data))
        {
            current_path.append(directories[0]);
            current_path.append("/");

            for(int i = 1 ; i < directories.length ; ++i)
            {
                /*
                user cannot nest two files
                 */
                if(fileFlag) throw new IllegalArgumentException("Can not generate directory in file...");

                if(isFile(directories[i]))
                {
                    current_path.append(directories[i]);
                    current_path.append("/");

                    assert localRoot != null;
                    localRoot.file_children.add(new FileNode(directories[i], current_path.toString()));
                    fileFlag = true;
                }
                else
                {
                    current_path.append(directories[i]);
                    current_path.append("/");
                    this.addDir(current_path.toString());

                    assert localRoot != null;
                    localRoot = localRoot.getNewRoot(directories[i]);
                }
            }
        } else throw new NullPointerException("Invalid root directory in system...");
    }

    /**
     * helper method
     * Testing item is file or not basicly
     * @param item test item
     * @return item is file true, otherwise false
     */
    private boolean isFile(String item)
    {
        for(int i = 0 ; i < item.length() ; ++i)
            if(item.charAt(i) == '.')
                return true;

        return false;
    }

    /**
     * remove file or directory
     * @param path file or directory path
     */
    public void remove(String path)
    {
        String[] directories = path.split("/");
        String willBe_remove = directories[directories.length - 1];
        StringBuilder current_path = new StringBuilder();
        FileNode localRoot = root;

        if(directories[0].equals(root.current_data))
        {
            current_path.append(directories[0]);
            current_path.append("/");

            for(int i = 1 ; i < directories.length-1 ; ++i)
            {
                if(isFile(directories[i]))
                    throw new IllegalArgumentException("Can not enter in file...");

                current_path.append(directories[i]);
                current_path.append("/");

                assert localRoot != null;
                if(localRoot.dir_children.contains(new FileNode(directories[i], current_path.toString())))
                    localRoot = localRoot.getNewRoot(directories[i]);

                else
                {
                    localRoot.dir_children.add(new FileNode(directories[i], current_path.toString()));
                    localRoot = localRoot.getNewRoot(directories[i]);
                }
            }
        } else throw new NullPointerException("Invalid root directory in system...");

        assert localRoot != null;
        if(warning_input(path)) {
            localRoot.file_children.remove(new FileNode(willBe_remove, current_path.toString()));
            localRoot.dir_children.remove(new FileNode(willBe_remove, current_path.toString()));
        }
    }

    /**
     * queries the security of the desired file or directory.
     * @param path of to be want to remove file or directory
     * @return if the user enter y/Y true, otherwise false
     */
    private boolean warning_input(String path)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nAre you sure you want to delete the " +
               path + "? [y/n]");
        String input = scan.nextLine();

        if(input.length() == 1)
            return input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N");

        return false;
    }

    /**
     * Search method
     * @param target name of directory or file
     */
    public void search(String target)
    {
        if(root == null)
            throw new NullPointerException("There is no file or directory in system");

        Queue<FileNode> localRoot_queue = new LinkedList<>();
        localRoot_queue.add(root);

        while(!localRoot_queue.isEmpty())
        {
            for(int i = localRoot_queue.size() ; i > 0 ; --i)
            {
                FileNode peek_node = localRoot_queue.peek();
                localRoot_queue.remove();

                assert peek_node != null;
                if(isContain(peek_node.current_path, target))
                {
                    if(isFile(peek_node.current_path))
                        System.out.println("file - " + peek_node.current_path);
                    else
                        System.out.println("dir - " + peek_node.current_path);
                }

                localRoot_queue.addAll(peek_node.dir_children);
                localRoot_queue.addAll(peek_node.file_children);
            }
        }
    }

    /**
     * helper compare method path and target
     * @param path path
     * @param target target
     * @return path include target return true otherwise return false
     */
    private boolean isContain(String path, String target) { return path.contains(target); }

    /**
     * Printt all nodes of file system tree
     * Works to string method
     */
    public void printFileSystem() { System.out.println(this); }

    /**
     * Convert to String from this object
     * @return String of this object
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if (root == null)
            return "null";

        Queue<FileNode> localRoot_queue = new LinkedList<>();
        localRoot_queue.add(root);

        while (!localRoot_queue.isEmpty())
        {
            for(int i = localRoot_queue.size() ; i > 0 ; --i)
            {
                FileNode peek_node = localRoot_queue.peek();
                localRoot_queue.remove();
                assert peek_node != null;
                sb.append(peek_node.toString());
                sb.append("\n");

                localRoot_queue.addAll(peek_node.dir_children);
            }
        }
        return sb.toString();
    }
}
