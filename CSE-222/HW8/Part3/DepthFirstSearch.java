import java.util.Iterator;

public class DepthFirstSearch
{
    /**
     * discovery index
     */
    private int discoverIndex;

    /**
     * discovery order
     */
    private int[] discoveryOrder;

    /**
     * finish index
     */
    private int finishIndex;

    /**
     * finish order
     */
    private int[] finishOrder;

    /**
     * current graph
     */
    private Graph graph;

    /**
     * parent
     */
    private int[] parent;

    /**
     * visited
     */
    private boolean[] visited;

    /**
     * constructor
     * @param graph current graph
     */
    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
        int n = graph.getNumV();

        parent = new int[n];
        visited = new boolean[n];
        discoveryOrder = new int[n];
        finishOrder = new int[n];

        for(int i = 0 ; i < n ; ++i)
            parent[i] = -1;

        for(int i = 0 ; i < n ; ++i)
            if(!visited[i])
                depthFirstSearch(i);

    }

    //Recursive search algorithm
    public void depthFirstSearch(int current) {
        visited[current] = true;
        discoveryOrder[discoverIndex++] = current;

        Iterator<Edge> itr = graph.edgeIterator(current);

        while(itr.hasNext()) {

            int neighbor = itr.next().getDest();

            if(!visited[neighbor]) {
                parent[neighbor] = current;
                depthFirstSearch(neighbor);
            }
        }
        finishOrder[finishIndex++] = current;
    }

    /**
     * get discovery order
     * @return discovery order
     */
    public int[] getDiscoveryOrder() { return discoveryOrder; }

    /**
     * get finish order
     * @return finish order
     */
    public int[] getFinishOrder() { return finishOrder; }

    /**
     * get parent
     * @return parent
     */
    public int[] getParent() { return parent; }
}
