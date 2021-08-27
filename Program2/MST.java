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
    List<Edge> edges;
    int numVertices;
    int numEdges;
    Edge e;

    public MST()
    {
        String fileName;
        Scanner fileIn;
        Scanner kb = new Scanner(System.in);
        System.out.println("What is the name of the input file: ");
        fileName = kb.next();
        try
        {
            fileIn = new Scanner(new FileReader(fileName));
            numVertices = fileIn.nextInt();
            numEdges = fileIn.nextInt();
            edges = new ArrayList<Edge>();
            while(fileIn.hasNext())
            {
                edges.add(new Edge(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt()));
            }
            fileIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Could not open the input file.");
            System.exit(0);
        } 
        
        kruskal(edges, numVertices); 
    }
    
    public List<Edge> kruskal(List<Edge> edges, int numVerticies)
    {
        DisjointSet ds = new DisjointSet(numVerticies);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
        List<Edge> mst = new ArrayList<Edge>();
        Edge e; 
        while(mst.size() != numVerticies -1)
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
        System.out.println("DisjointSets");
        ds.print();
        System.out.println("Edges");
        System.out.println(edges.toString());
        return mst;
    }
        
    public static void main(String[] args)
    {
        MST mst = new MST();
                 
    }
}
