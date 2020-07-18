import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MatrixGraph extends AbstractGraph
{
    /**
     * edges storage
     */
    private double[][] edges;

    /**
     * search for dfs
     */
    private DepthFirstSearch search;

    /**
     * contructor
     * @param numV vertex number
     * @param isDirected directed or not
     */
    public MatrixGraph(int numV, boolean isDirected) {
        super(numV, isDirected);

        edges = new double[numV][numV];

        for(int i = 0 ; i < numV ; ++i)
            for(int j = 0 ; j < numV ; ++j)
                edges[i][j] = 0.0;
    }

    /**
     * @param source source of edge
     * @param dest dest of edge
     * @return edge of source -> dest
     */
    @Override
    public Edge getEdge(int source, int dest) {
        if(edges[source][dest] != 0.0)
            return new Edge(source, dest, edges[source][dest]);

        return null;
    }

    /**
     * @param e tobe insert
     */
    @Override
    public void insert(Edge e) {

        if(edges[e.getSource()][e.getDest()] == 0.0)
            edges[e.getSource()][e.getDest()] = e.getWeight();

        if(!isDirected()) edges[e.getDest()][e.getSource()] = e.getWeight();
    }

    /**
     * @param source source of edge
     * @param dest dest of edge
     * @return true if is source -> edge, otherwise false
     */
    @Override
    public boolean isEdge(int source, int dest) { return edges[source][dest] != 0.0; }

    /**
     * @param source source of edge
     * @param dest destination of edge
     * @return if removing success return true, otherwise false
     */
    @Override
    public boolean remove(int source, int dest) {

        if(edges[source][dest] == 0.0) return false;

        edges[source][dest] = 0.0;
        if(isDirected()) edges[dest][source] = 0.0;

        return true;
    }

    /**
     * @param source source of list
     * @return source th edge
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) { return new MatrixGraphIterator(source); }

    /**
     * private inner iterator class
     */
    private class MatrixGraphIterator implements Iterator<Edge>
    {
        /**
         * source
         */
        private int source;

        /**
         * destination
         */
        private int dest;

        /**
         * weighted
         */
        private double w;


        /**
         * contructor
         * @param source source
         */
        public MatrixGraphIterator(int source) {
            this.source = source;
            this.dest = 0;
            this.w = edges[source][dest];
        }

        /**
         * @param source source
         * @return source th iterator
         */
        public MatrixGraphIterator iterator(int source) {

            if((source+1) == edges.length)
                return null;

            return new MatrixGraphIterator(source);
        }

        /**
         * has next method
         * @return if iterator has next true, otherwise return false
         */
        @Override
        public boolean hasNext() {

            int i = dest;
            while(!(edges[source].length <= i+1)) {
                ++i;
                if(isEdge(source, i))
                    return true;
            }

            return false;
        }

        /**
         * next method
         * @return next object of iterator
         */
        @Override
        public Edge next() {

            if(!hasNext())
                return null;

            w = edges[source][++dest];

            while(!isEdge(source, dest)) {

                if(!hasNext())
                    return null;

                ++dest;
                w = edges[source][dest];
            }

            return new Edge(source, dest, w);
        }
    }

    /**
     * BFS class
     * @param start_vertex
     * @return bfs path array
     */
    public int[] BFS(int start_vertex) {

        Queue<Integer> queue = new LinkedList<>();

        int[] parent = new int[this.getNumV()];

        for(int i = 0 ; i < this.getNumV() ; ++i) parent[i] = -1;

        boolean[] identified = new boolean[this.getNumV()];

        identified[start_vertex] = true;
        queue.offer(start_vertex);

        while(!queue.isEmpty()) {

            int current = queue.remove();
            Iterator<Edge> itr = this.edgeIterator(current);

            while(itr.hasNext()) {

                int neighbor = itr.next().getDest();

                if(!identified[neighbor]) {
                    identified[neighbor] = true;
                    queue.offer(neighbor);
                    parent[neighbor] = current;
                }
            }
        }

        return parent;
    }

    /**
     * show specific path
     * @param from from
     * @param to to
     */
    public void showBFS(int from, int to) {

        int[] result = BFS(from);

        if(result[to] == -1)
            System.out.println("There is not any path...");

        else
        {
            Stack<String> messages = new Stack<String>();

            messages.push(String.valueOf(to));
            messages.push(String.valueOf(result[to]));
            int index = result[to];

            while(index != from && index != -1) {
                messages.push(String.valueOf(result[index]));
                index = result[index];
            }

            while(!messages.isEmpty()) {

                if(messages.size() == 1)
                    System.out.println(messages.pop());
                else
                    System.out.print(messages.pop() + " -> ");
            }
        }
    }

    /**
     * show dfs discovery and visit order
     * @param current current
     */
    public void showDFS(int current) {

        search = new DepthFirstSearch(this);

        int[] dOrder = search.getDiscoveryOrder();
        int[] fOrder = search.getFinishOrder();
        int[] parent = search.getParent();

        System.out.println("Discovery and finish order");

        for (int i = 0 ; i < this.getNumV() ; i++)
            if(dOrder[i] != fOrder[i])
                System.out.println(dOrder[i] + " " + fOrder[i]);

        System.out.println("Parent: ");
        for(int i = 0 ; i < parent.length ; ++i)
            System.out.println("[" + i + "]: " + parent[i]);
    }

    /**
     * override to string method
     * @return string of object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (double[] edge : edges) {
            for (double v : edge) {
                sb.append(v);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
