import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public abstract class AbstractGraph implements Graph
{
    /**
     * directed or not
     */
    private boolean directed;

    /**
     * vertex number
     */
    private int numv;

    /**
     * contructor
     * @param numV vertex number
     * @param directed directed info
     */
    public AbstractGraph(int numV, boolean directed) {
        this.numv = numV;
        this.directed = directed;
    }

    /**
     * insert edge
     * @param edge to be insert
     */
    public abstract void insert(Edge edge);

    /**
     * is edge or not
     * @param source source
     * @param dest dest
     * @return is edge or not
     */
    public abstract boolean isEdge(int source, int dest);

    /**
     * get edge method
     * @param source source
     * @param dest destination
     * @return edge of source -> dest
     */
    public abstract Edge getEdge(int source, int dest);

    /**
     * get Iterator
     * @param source source
     * @return iterator of source
     */
    public abstract Iterator<Edge> edgeIterator(int source);

    /**
     * get num vertex
     * @return vertex number
     */
    @Override
    public int getNumV() { return numv; }

    /**
     * is directed or not
     * @return directed info
     */
    @Override
    public boolean isDirected() { return directed; }

    /**
     * load from file
     * @param scan scanner object
     */
    public void loadEdgesFromFile(Scanner scan) {

        String line = "";
        int source = -1;
        int dest;

        while(line != null) {

            try { line = scan.nextLine(); }
            catch (Exception e) { return; }


            if(line != null)
            {
                ++source;

                try {
                    for(int i = 0 ; i < line.length() ; ++i){

                        int w = Integer.parseInt(String.valueOf(line.charAt(i)));

                        if(w == 0)
                            insert(new Edge(source, i));
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        }
    }

    /**
     * create graph method
     * @param scan scanner object
     * @param isDirected is directed or not
     * @param type matrix or list graph
     * @return graph
     * @throws IOException for file stream exception
     */
    public static Graph createGraph(Scanner scan, boolean isDirected, String type)
    throws IOException {

        int numV = scan.nextInt();
        scan.nextLine();

        AbstractGraph returnValue = null;

        if(type.equalsIgnoreCase("Matrix")) returnValue = new MatrixGraph(numV, isDirected);
        else if(type.equalsIgnoreCase("List")) returnValue = new ListGraph(numV, isDirected);
        else throw new IllegalArgumentException();

        returnValue.loadEdgesFromFile(scan);

        return (Graph) returnValue;
    }
}
