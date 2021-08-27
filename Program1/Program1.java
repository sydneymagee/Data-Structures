import java.util.*;
import java.io.*;
/**
 * This is Program 1
 *
 * @author Sydney Magee
 *
 */
public class Program1
{
    private static AdjacencyLists adjList;
    
    private Lab3 lab3;
    private LinkedList<Integer> queue;
    //private int vertices; 
    private static boolean directed;

    public Program1(String fileName)
    {
        lab3 = new Lab3();
        queue = new LinkedList<Integer>();
        adjList = lab3.makeList(fileName);
        directed = adjList.directed();
        lab3.printList(adjList);      
    }
              
    /**
     * This returns yes if the graph is connected
     * no if the graph is disconnected.
     */
    public boolean connected(int root)
    {
        if(adjList.directed())
        {
            return false;
        }
        boolean[] visited = new boolean[adjList.order()];
        queue.add(root);
        while(!queue.isEmpty())
        {
            root = queue.remove();
            Iterator<Integer>itr = adjList.neighborIterator(root);
            while(itr.hasNext())
            {
                int adjNode = itr.next();
                if(visited[adjNode] != true)
                {
                    queue.add(adjNode); 
                    visited[adjNode] = true;
                }
            }
        }
        if(queue.isEmpty())
        {
            for(int i = 0; i < adjList.order(); i++)
            {
                if(!visited[i])
                {
                    return false;
                }
            }
        }
        return true;
    }
   

    /**
     * This checks if the graph is two-colorable.
     */
    public boolean bipartite(AdjacencyLists adjList, boolean directed)
    {
        if(directed == true)
        {
            return false;
        }
        int twoCol[] = new int[adjList.order()];

        for(int i = 0; i < adjList.order(); i++)
        {
            twoCol[i] = 0;
        } 
        twoCol[1] = 1;
        queue.add(1);
        while(!queue.isEmpty())
        {
            int root = queue.remove();
            int n = twoCol[root];
            Iterator<Integer> itr = adjList.neighborIterator(root);
            while(itr.hasNext())
            {
                int adjNode = itr.next();
                if(twoCol[adjNode] == 0)
                {
                    if(n == 2)
                    {
                        twoCol[adjNode] = 1;
                        queue.add(adjNode);
                    }
                    if(n == 1)
                    {
                        twoCol[adjNode] = 2;
                        queue.add(adjNode);
                    }
                }
                
                if(n == twoCol[adjNode])
                {
                     
                    return false;
                }  
                
            } 
                       
        }
        System.out.println("Bipartite: " +  Arrays.toString(twoCol));    
        return true;   
    }

    /**
     * This determines whether or not the graph is cyclic or acyclic.
     *
     */
    public boolean isCyclic(int root, boolean visited[], int parent)
    {
        visited[root] = true;
        Iterator<Integer> itr = adjList.neighborIterator(root);
        while(itr.hasNext())
        {
            int adjNode = itr.next();
            if(!visited[adjNode])
            {
                if(isCyclic(adjNode, visited, root))
                {    
                    return true;
                }
            }
            else if(adjNode != parent)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This determines whether or not the graph is a tree.
     * A tree has the following properties:
     * 1. The number of verticies is one more than the number of edges.
     * 2. The graph is connected.
     * 3. The graph contains no cycles.
     */
    public boolean isTree()
    {
        boolean visited[] = new boolean[adjList.order()];
        for(int i = 0; i < adjList.order(); i++)
        {
            visited[i] = false;
        }
        if(isCyclic(0, visited, -1))
        {
            return false;
        }
        for(int adjNode = 0; adjNode < adjList.order(); adjNode++)
        {
            if(visited[adjNode] != true)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This prints out the parent, depth and child count of the root.
     */
    public void printInfo(int root)
    {
        //boolean visited[] = new boolean[adjList.order()];
        int parent[] = new int[adjList.order()];
        int depth[] = new int[adjList.order()];
        int childCount = 0;
        depth[root] = 0;
        queue.add(root);
        while(!queue.isEmpty())
        {
            Iterator<Integer> itr = adjList.neighborIterator(root);
            while(itr.hasNext())
            {
                int adjNode = itr.next();
                //if(!visited[adjNode])
                //{
                    childCount++;
                    //visited[adjNode] = true;
                    queue.add(adjNode);
                //}
                //else
                //{
                    parent[root] = adjNode;
                //}
            }
        depth[root] = 1 + depth[parent[root]];
        //visited[root] = true;
        }
        System.out.println("Parent: " + Arrays.toString(parent));
        System.out.println("Depth: " + Arrays.toString(depth));
        System.out.println("Children: " + childCount);
        //for(int i = 0; i < adjList.order(); i++)
        //{
        //    System.out.println("The parent is: " + parent[i]);
        //    System.out.println("The depth is: " + depth[i]);
        //    System.out.println("The number of children are: " + childCount);
        //}
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to my program, please read the readme file for help.");
        Program1 prog = new Program1(args[0]);
        boolean visited[] = new boolean[adjList.order()];
        Scanner kb = new Scanner(System.in);
        System.out.println("What is the root? ");
        int root = kb.nextInt();
        if(prog.connected(root))
        {
            System.out.println("The graph is connected");
        }
        else
        {
            System.out.println("The graph is not connected.");
        }
        if(prog.bipartite(adjList, directed))
        {
            System.out.println("The graph is two colorable");
        }
        else
        {
            System.out.println("The graph is not two colorable.");
        }
        if(prog.isTree())
        {
            System.out.println("The graph is a tree.");
            //prog.printInfo(root);
        }
        else
        {
            System.out.println("The graph is not a tree.");
        }
        if(prog.isCyclic(root, visited, -1))
        {
            System.out.println("The graph is cyclic.");
        }
        else
        {
            System.out.println("The graph is acyclic.");
        }
        
        
    }

}
