import java.util.*;
import java.io.*;

/**
 * This is the Lab3 test file.
 * Contains main method.
 *
 * @author Sydney Magee
 * @version 9/10/2020
 */
public class Lab3
{
    /**
     * The main method reads in a file from the command line and
     * produces an Adjacency List.
     *
     */
    public Lab3()
    {}

    public AdjacencyLists makeList(String fileName) 
    { 
        Scanner fileIn = new Scanner(System.in);
        int vertices = 0;
        boolean directed = false;
        try
        {
            File file = new File(fileName);
            fileIn = new Scanner(file);
            vertices = fileIn.nextInt();
            int dir = fileIn.nextInt();
            if(dir == 1)
            {
                directed = true;
            }
            else if (dir == 0)
            {
                directed = false;
            }
        }
        catch(IOException ioe)
        {
            System.out.println("Could not open the input file.");
            System.exit(0);
        }

        AdjacencyLists adjList = new AdjacencyLists(vertices, directed);
        int x = 0;
        int y = 0;
        while(fileIn.hasNext())
        {
            x = fileIn.nextInt();
            y = fileIn.nextInt();
            if(x == -1 && y == -1)
            {
                break;
            }
            adjList.addEdge(x,y);
        }
        return adjList;
    }

    public void printList(AdjacencyLists adjList)
    {
        Iterator<Integer> itr;
        for(int i = 0; i < adjList.order(); i++)
        {
            itr = adjList.neighborIterator(i);
            System.out.print("\n" + i + ": ");
            while(itr.hasNext())
            {
                System.out.print(itr.next() + " ");
            }
        }
        System.out.println("\n" + "There are " + adjList.size() + " edges.");
    }

}

