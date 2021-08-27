/**
 * This is the edge class.
 * 
 * @version 11.20.20
 * @author Sydney Magee
 */
public class Edge implements Comparable<Edge>
{
    int i;
    int j;
    int weight;

    public Edge(int i, int j, int weight)
    {
        this.i = i;
        this.j = j;
        this.weight = weight;
    }
    public int getI()
    {
        return i;
    }
    public int getJ()
    {
        return j;
    }
    public int getWeight()
    {
        return weight;
    }
    public int compareTo(Edge edge)
    {
        return weight - edge.getWeight();
    }
    public String toString()
    {
        return ("I: " + this.i + "    J: " + this.j + 
                    "    Weight: " + this.weight + "\n");
    }
}
