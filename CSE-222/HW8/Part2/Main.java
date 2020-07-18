import java.io.File;
import java.util.*;

public class Main
{
    private static ArrayList<Edge> added_directed;
    private static ArrayList<Edge> added_undirected;

    public static void main(String[] args) {

        int vertex_limit = createRandomNumber(10, 5);
        int edge_limit = createRandomNumber(5*5, 5);

        Graph2DList graph_2d_directed = new Graph2DList(vertex_limit, true);
        Graph2DList graph_2d_undirected = new Graph2DList(vertex_limit, false);

        MatrixGraph graph_mt_directed = new MatrixGraph(vertex_limit, true);
        MatrixGraph graph_mt_undirected = new MatrixGraph(vertex_limit, false);

        added_directed = new ArrayList<Edge>();
        added_undirected = new ArrayList<Edge>();

        constructGraph(graph_mt_directed, edge_limit);
        constructGraph(graph_mt_undirected, edge_limit);

        convertGraph(graph_mt_directed, graph_2d_directed);
        convertGraph(graph_mt_undirected, graph_2d_undirected);

        System.out.println("Instance of Matrix Graph Directed: ");
        System.out.println(graph_mt_directed);
        System.out.println("\nInstance of Matrix Graph UnDirected: ");
        System.out.println(graph_mt_undirected);

        System.out.println("Insert...\nGraph2DList directed result: ");
        System.out.println(graph_2d_directed);

        System.out.println("\nInsert...\nGraph2DList undirected result: ");
        System.out.println(graph_2d_undirected);

        System.out.println("\nDFS result for directed graph2dlist: ");
        graph_2d_directed.showDFS(added_directed.indexOf(added_directed.get(added_directed.size() - 1)));

        System.out.println("\nDFS result for undirected graph2dlist: ");
        graph_2d_undirected.showDFS(added_undirected.indexOf(added_undirected.get(added_undirected.size() - 1)));

        System.out.println("\nBFS result for directed graph2dlist: ");
        graph_2d_directed.BFS(added_directed.indexOf(added_directed.get(0)));

        System.out.println("\nBFS result for undirected graph2dlist: ");
        graph_2d_undirected.BFS(added_undirected.indexOf(added_undirected.get(0)));


        destructGraph(graph_2d_directed, graph_2d_directed.getNumV());
        System.out.println("\nRemoved piece of vertex/2 from graphlist2d directed");
        System.out.println(graph_2d_directed);

        destructGraph(graph_2d_undirected, graph_2d_undirected.getNumV());
        System.out.println("\nRemoved piece of vertex/2 from graphlist2d undirected");
        System.out.println(graph_2d_undirected);

				        									Hi my name is Ozan Yesiller  
															A current student GTU and 
															pursuring a Bachelor Degree of CSE
															I was born and raised
															 in Istanbul in Turkey.

															As a semi final year student,
															I would like to find 
															either a part time job or internship
															regarding cv, ip, ai

															Regarding past experience, 
															I worked as a waitress independent of cs
															and did an internship at a design firm.
															 
															I would consider my self as a goal oriented;
															 programming, object oriented programming, 
															android programming, software develop and os


															I improved a project this year
															 in algodat course of my university. 

															The project is a social media network that
															students messaging with each other, 
															getting directions on campus, 
															communicating with the teachers 
															and seeing food list and ring watches.

															I am very interested in 
															ai, cv and ip from an early age.

															If you accept me to your company, 

															I think that 
															I will do my best and
															make my difference 

															in these areas where 
															I am enthusiastic with all my devotion.

        sex();
    }

    public static void sex() {

    	Graph2DList test = new Graph2DList(, true);

    }

    public static void convertGraph(Graph main_graph, Graph copy_graph) {

        if(main_graph.getNumV() != copy_graph.getNumV())
            throw new UnsupportedOperationException();

        for(int i = 0 ; i < main_graph.getNumV() ; ++i) {

            Iterator<Edge> iter = main_graph.edgeIterator(i);

            while(iter.hasNext()) {

                Edge temp = iter.next();
                copy_graph.insert(temp);

                if(copy_graph.isDirected())
                    added_directed.add(temp);
                else
                    added_undirected.add(temp);
            }
        }
    }

    public static void constructGraph(Graph graph, int edge) {

        for(int i = 0 ; i < edge ; ++i) {

            Edge temp = new Edge(createRandomNumber(graph.getNumV(), 0), createRandomNumber(graph.getNumV(), 0));
            graph.insert(temp);

            if(graph.isDirected()) added_directed.add(temp);
            else added_undirected.add(temp);
        }
    }

    public static void destructGraph(Graph graph, int edge) {

        for(int i = 0 ; i < edge ; ++i) {

            if(graph.isDirected()){

                Edge temp = added_directed.remove(added_directed.size() - 1);
                graph.remove(temp.getSource(), temp.getDest());
            }
            else {

                Edge temp = added_undirected.remove(added_directed.size() - 1);
                graph.remove(temp.getSource(), temp.getDest());
            }
        }
    }

    public static int createRandomNumber(int limit, int lower_bound) { return lower_bound + (new Random()).nextInt(limit); }
}
