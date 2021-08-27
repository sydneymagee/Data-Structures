import java.util.*;
import java.io.*;
/**
 * This is the MST Class.
 * MST = Minimum Spanning Tree.
 *
 * @version 11.20.2020
 * @author Sydney Magee
 */
public class MST
{
    ArrayList<Edge> edges;
    int numVertices;
    int numEdges;
    Edge e;

    public MST(int verticies, int edge)
    {
        numVertices = verticies;
        edges = new ArrayList<Edge>();  
    }

    public void addEdge(int i, int j, int weight)
    {
        edges.add(new Edge(i, j, weight));
    }
    
    public ArrayList<Edge> kruskal()
    {
        DisjointSet ds = new DisjointSet(numVertices);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
        ArrayList<Edge> mst = new ArrayList<Edge>(); 
        while(mst.size() != numVertices -1)
        {
            e = pq.poll();
            int i = ds.find(e.getI());
            int j = ds.find(e.getJ());
            if(i != j)
            {
                mst.add(e);
                ds.union(i, j);
            }
        } 
        //System.out.println("DisjointSets");
        //ds.print();
        //System.out.println("Edges");
        //System.out.println(edges.toString());
        return mst;
    }
}
