import java.util.*;
import java.io.*;
/**
 * This is the Two Traveling Salespeople Problem.
 * @author Sydney Magee
 * @version 12.4.20
 */
public class Final
{
    int numCities;
    double[][] coordinates;
    double[][] distances;
    MST mst;
    AdjacencyLists adjList;
    ArrayList<Edge> minimumTree;
    ArrayList<Integer> dfs;

    public Final(String fileName)
    {
        try
        {   
            Scanner fileIn = new Scanner(new FileReader(fileName));
            numCities = fileIn.nextInt();
            adjList = new AdjacencyLists(numCities);
            mst = new MST(numCities, ((numCities * numCities)-numCities));
            distances = new double[numCities][numCities];
            coordinates = new double[numCities][2];
            minimumTree = new ArrayList<Edge>();
            makeCoords(fileIn);
            calcDistance();
            minimumTree = mst.kruskal(); 
            makeAdjList(minimumTree);           
            System.out.println("\nTour 1: \n");
            dfs = DFS(adjList, 0, numCities);                               
            goOnTour(dfs);
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\nTour 2: \n");
            dfs = DFS(adjList, numCities - 1, numCities);
            goOnTour(dfs);       
        }
        catch(IOException ioe)
        {
            System.out.println("File could not be opened.");
            System.exit(0);
        }             
    }

    /**
     * This populates the coordinates.
     */
    public void makeCoords(Scanner fileIn)
    {
        int i = 0;
        while(fileIn.hasNext())
        {
            if(fileIn.hasNextInt())
            {
                coordinates[i][0] = fileIn.nextDouble();
                coordinates[i][1] = fileIn.nextDouble();
                //System.out.println(i + "    " + coordinates[i][0] + "     " + coordinates[i][1]); 
                i++;       
            }
            else
            {
                fileIn.nextLine();
            }
        } 
    }

    /**
     * This calculates the distances between coordinates.
     */
    public void calcDistance()
    {
        for(int i = 0; i < coordinates.length; i++)
        {
            double xi = coordinates[i][0];
            double yi = coordinates[i][1];
            for(int j = 0; j < coordinates.length; j++)
            {
                double xj = coordinates[j][0];
                double yj = coordinates[j][1];

                distances[i][j] = Math.sqrt((xi-xj) * (xi-xj) + (yi-yj) * (yi-yj));
                if(i != j)
                {
                    mst.addEdge(i, j, (int)distances[i][j]);
                }
                //System.out.println(i + "    " + j + "   " + distances[i][j]);
            }
        }
    }
    
    /**
     * This populates the adjList
     */
    public void makeAdjList(ArrayList<Edge> mst)
    {
        for(int i = 0; i < mst.size(); i++)
        {
            adjList.addEdge(mst.get(i).getI(), mst.get(i).getJ());
        }
    }
    
    /**
     * This is the Depth First Search.
     * I sourced some implementation inspiration from:
     * https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
     */
    public ArrayList<Integer> DFSUtil(int root, boolean visited[])
    {
        visited[root] = true;
        dfs.add(root);
        Iterator<Integer> itr = adjList.neighborIterator(root);
        while(itr.hasNext())
        {
            int n = itr.next();
            if(!visited[n])
            {
                DFSUtil(n, visited);
            }
        }   
        return dfs;
    }
    public ArrayList<Integer> DFS(AdjacencyLists adjList, int root, int x)
    {
        dfs = new ArrayList<Integer>(x);
        boolean visited[] = new boolean[x];
        return DFSUtil(root, visited);
    }
    /**
     * This takes the result of the depth first search and outputs it.
     * This is a tool for finding the shortest path for the salepeople
     * to take.
     */ 
    public void goOnTour(ArrayList<Integer> dfs)
    {
        double total = 0;
        for(int i = 0; i < dfs.size(); i++)
        {
            if(i + 1 < dfs.size())
            {
                int x = dfs.get(i);
                int y = dfs.get(i + 1);

                total += distances[x][y];
                System.out.println(x + "  travels to  " + y);
                System.out.println("With a Weight of: " + distances[x][y]);
                
            }
            else
            {
                int x = dfs.get(i);
                int y = dfs.get(0);
                
                total += distances[x][y];
                System.out.println(x + "  travels to  " + y);
                System.out.println("With a Weight of: " + distances[x][y]);
            }
        }
        System.out.println("Total: " + total);
        
    }
    

    /**
     * This is the main method.
     * 
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to my final program!");
        System.out.println("An attempt at the traveling salespeople problem");
        Final f = new Final(args[0]);
    }
}
