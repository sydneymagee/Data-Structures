/**
 * This is the disjoint set Class.
 *
 * @version 11.20.20
 * @author Sydney Magee
 */
public class DisjointSet
{
    private int[] parent;

    public DisjointSet(int numElements)
    {
        parent = new int[numElements];
        for(int i = 0; i < numElements; i++)
        {
            parent[i] = -1;
        }
    }
    public void union(int root1, int root2)
    {
        if(parent[root1] < parent[root2])
        {
            parent[root2] = root1;
        }
        else if(parent[root1] > parent[root2])
        {
            parent[root1] = root2;
        }
        else
        {
            parent[root1] = root2;
            parent[root2]--;
        }
    }
    public int find(int el)
    {
        while(parent[el] >= 0)
        {
            el = parent[el];
        }
        return el;
    }
    public void print()
    {
        for(int i = 0; i < parent.length; i++)
        {
            System.out.println("(" + i + ", " + parent[i] + ")");
        }
    }
}
