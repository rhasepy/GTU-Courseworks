import java.util.*;

public class Graph2DList extends AbstractGraph
{
    /**
     * 2d linked list stroing
     */
    private List<List<Edge>> edges;

    /**
     * for the depth first search
     */
    private DepthFirstSearch search;

    /**
     * constructor
     * @param numV vertex number
     * @param isDirected is directed or not
     */
    public Graph2DList(int numV, boolean isDirected) {
        super(numV, isDirected);
        edges = new LinkedList<>();

        for(int i = 0 ; i < numV ; ++i) edges.add(new LinkedList<>());
    }

    /**
     * insert method
     * @param edge to be add object
     */
    @Override
    public void insert(Edge edge) {

        if(edge.getSource() > getNumV())
            throw new ArrayIndexOutOfBoundsException("source of edge: " + edge.getSource() + ", NumV" + super.getNumV());

        if(!edges.get(edge.getSource()).contains(edge)) {
            edges.get(edge.getSource()).add(edge);
        }

        if(!isDirected())
            if(!edges.get(edge.getSource()).contains(edge))
                edges.get(edge.getDest()).add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
    }

    /*
        As mentioned in pdf:
      Note that if you can delete a node, node IDs cannot be from 0...(n-1) anymore.
      This condition is provided by the controls included in the insert method.
     */
    /**
     * remove method
     * @param source to be source of remove element
     * @param dest to be destination of remove element
     * @return if the remove success true, otherwise false
     */
    @Override
    public boolean remove(int source, int dest) {

        if(edges.size() <= source) return false;
        if(edges.get(source).size() <= dest) return false;
        if(edges.get(source).get(dest) == null) return false;

        edges.get(source).remove(new Edge(source, dest));
        if(isDirected()) edges.get(dest).remove(new Edge(dest, source));

        return true;
    }

    /**
     * is edge or not method
     * @param source source of edge
     * @param dest destination of edge
     * @return true is edge otherwise not
     */
    @Override
    public boolean isEdge(int source, int dest) { return edges.get(source).contains(new Edge(source, dest)); }

    /**
     * get method
     * @param source source of edge
     * @param dest dest of edge
     * @return source -> dest edge
     */
    @Override
    public Edge getEdge(int source, int dest) {

        for(Edge edge : edges.get(source))
            if(edge.equals(new Edge(source, dest))) return edge;

        return null;
    }

    /**
     * Iterator method
     * @param source iterator of source th list
     * @return iterator of source th list
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) { return edges.get(source).iterator(); }

    /**
     * override to string method
     * @return string of this class
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (List<Edge> edgeList : edges)
            for (Edge edge : edgeList) sb.append(edge.toString());

        return sb.toString();
    }

    /**
     * BFS function
     * @param start_vertex start from vertex
     * @return path start from vertex to all vertex
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

        for(int i = 0 ; i < parent.length ; ++i)
            System.out.println("[" + i + "]: " + parent[i]);

        return parent;
    }

    /**
     * show the path
     * @param from from vertex
     * @param to to vertex
     */
    public void showBFS(int from, int to) {

        int[] result = BFS(from);
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

    /**
     * show dfs discovery and visit order
     * @param current start from
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
}
