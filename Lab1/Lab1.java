import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;

public class Lab1
{
    private Scanner kb;
    private int[] gems;
    private Random rand;
    private int numGems;
    private ArrayList<Integer> pile1;
    private ArrayList<Integer> pile2;
    private int leftSum;
    private int rightSum;


    public Lab1()
    {
        kb = new Scanner(System.in);
        rand = new Random();
        numGems = 0;
        gems = new int[numGems];
        pile1 = new ArrayList<Integer>();
        pile2 = new ArrayList<Integer>();
           
        System.out.println("Please type 1 for Read From File or 2 for Random generation.");
        int ans = kb.nextInt();
            
        if (ans == 1)
        {
            readFromFile();
            splitGems();
            printGems();
        }
        else if (ans == 2)
        {
            fillGems();
            splitGems();
            printGems();
        }
        else
        {
            System.out.println("Error please enter your choice again.");
        }
    }
    
    public void fillGems()
    {
        System.out.println("How many gems are in the bag? ");
        int numGems = kb.nextInt();
        gems = new int[numGems];
        for(int i = 0; i < numGems; i++)
        { 
            int random = rand.nextInt(numGems); 
            gems[i] = random; 
        }
    }
    
    public void readFromFile()
    {
        String fileName;
        Scanner fileIn = null;

        System.out.println("Please enter the name of the file containing gems here: ");
        fileName = kb.next();
        try
        {
            fileIn = new Scanner(new FileReader(fileName));
        }
        catch(IOException ioe)
        {
            System.out.println("Error with file, please try again.");
            System.exit(0);
        }
        int numGems = fileIn.nextInt();
        gems = new int[numGems];

        for(int i = 0; i < gems.length; i++)
        {
            gems[i] = fileIn.nextInt();
        }
        fileIn.close();
    } 

    public void printGems()
    {
        System.out.println("Gem Values: "); 
        for(int i = 0 ; i < gems.length; i ++)
        {
            System.out.print(gems[i]);
        }
        System.out.println("\n\nPile 1: ");
        for(int i = 0; i < pile1.size(); i ++)
        {
            System.out.print(pile1.get(i));
        }
        System.out.println("\nSum of Pile1: " + leftSum);
        System.out.println("\nPile2: ");
        for(int i = 0; i < pile2.size(); i ++)
        {
            System.out.print(pile2.get(i));
        }    
         System.out.println("\nSum of Pile2: " + rightSum);
    }

    public void splitGems()
    {
        for(int i = 0; i < gems.length; i++)
        {
            if(leftSum <= rightSum)
            {
                leftSum = leftSum + gems[i];
                pile1.add(gems[i]);
            }
            else if (leftSum >= rightSum)
            {
                rightSum += gems[i];
                pile2.add(gems[i]);
            }
            else if (leftSum == rightSum)
            {
                System.out.println(pile1);
                System.out.println("Pile 1 Sum: " + leftSum);
                System.out.println(pile2);
                System.out.println("Pile 2 Sum: " + rightSum);
            }
            else
            {
                System.out.println("Uneven value amount of gems in the bag.");  
                
            }
        }
        
    }

public static void main(String[] args)
    { 
        Lab1 solution = new Lab1();
        System.out.println(solution);       
   }
}







