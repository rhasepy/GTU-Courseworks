import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

       /* int[][] ar = new int[0][0];
        MatrixGraph graph = null;

        try {
            ar = readFromFile(new Scanner((new File(args[0]))));
        } catch (Exception e) {
            System.err.println("IO Exception while reading graph");
            System.err.println(e.toString());
            System.exit(1);
        }

        graph = new MatrixGraph(ar.length, false);

        for (int[] ints : ar) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        initMaze(ar);
        downConvertor(ar, 0, 3);
        System.out.println("\nGraph: ");
        for (int[] ints : ar) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }*/

//////////////////////////////////////////////////////////////////////////////////////////////////

        int  numV = 0;
        Graph theMaze = null;

        try{
            Scanner scan = new Scanner(new File(args[0]));
            theMaze = AbstractGraph.createGraph(scan, false, "List");
            numV = theMaze.getNumV();
        } catch (Exception e) {
            System.err.println("IO Exception while reading graph");
            System.err.println(e.toString());
            System.exit(1);
        }


        //System.out.println(theMaze);
        int[] parent = GraphSearch.BreadthFirstSearch(theMaze, 0);

        for(int i = 0 ; i < parent.length ; ++i)
            System.out.println("[" + i + "]: " + parent[i]);


        Deque<Integer> thePath = new ArrayDeque<>();
        int v = numV - 1;

        while(parent[v] != -1) {
            thePath.push(v);
            v = parent[v];
        }

        System.out.println("The shortest path is: ");
        while(!thePath.isEmpty()) {
            System.out.print(thePath.pop());
            System.out.println(" ");
        }
    }

    public static void initMaze(int[][] maze, MatrixGraph graph_maze) {

        int curr_source = 0;
        int curr_dest = 1;

        System.out.println("The junction: ");
        for(int i = 0 ; i < maze.length ; ++i) {
            for(int j = 0 ; j < maze[i].length ; ++j) {

                if(isJunction(maze, i, j)) {
                    System.out.println("i: " + i + " j: " + j);
                }
            }
        }
    }

    public static boolean isJunction(int[][] maze, int i, int j) {

        if(maze[i][j] == 1) return false;

        if((i+1) != maze.length) return maze[i+1][j] == 0;

        if((i-1) > 0) return maze[i - 1][j] == 0;

        return false;
    }

    public static void downConvertor(int[][] maze, int i, int j) {

        int w = 0;

        while((i+1) != maze.length) {

            if(maze[i+1][j] == 1) break;

            maze[i++][j] = 1;
            ++w;
        }

        maze[i][j] = w;
    }

    public static void upConvertor(int[][] maze, int i, int j) {

    }

    public static int[][] readFromFile(Scanner scan) {

        int numV = scan.nextInt();
        scan.nextLine();

        int[][] maze = new int[numV][numV];

        String line = "";
        int i = -1;

        while(scan.hasNext()) {

            line = scan.nextLine();
            ++i;

            if(line != null) {

                try {
                    for(int j = 0 ; j < line.length() ; ++j)
                        maze[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        }

        return maze;
    }
}
