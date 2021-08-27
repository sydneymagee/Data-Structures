import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Set;
import java.util.Map;
/**
 * This is the Lab5 class.
 * @author Sydney Magee
 * @version 10/27/2020
 */
public class Lab5
{
    private FileReader fr;
    private BufferedReader br;
    private StateMachine sm;
    private HashSet<String> keywords;
    private TreeMap<String, LinkedList<Integer>> identifiers;
    
    /**
     * The constructor for Lab5 
     * Asks the user for a file name
     * Reads the file 
     * Stores keywords in a HashSet 
     * Calls method to find identifiers and stores in TreeMap
     * TreeMap stores non-keyword identifiers and their line numbers
     * Prints identifiers 
     * @throws IOException if there is an error with the file
     */
    public Lab5()
    {
        try
        {
            keywords = new HashSet<String>();
            identifiers = new TreeMap<String, LinkedList<Integer>>();
            Scanner kb = new Scanner(System.in);
            System.out.println("Please enter the file: ");
            String fileName = kb.nextLine();
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            sm = new StateMachine(br);
                       
            keywords();
            //System.out.println(keywords);
            findIdentifiers();
        }
        catch(IOException e)
        {
            System.out.println("File not found.");
        }
        
    }
    
    /**
     * This method finds the keywords in keywords.dat 
     * then stores the keywords in the HashSet, keywords.
     */
    public void keywords()
    {
        try
        {
            Scanner fileIn = new Scanner(new FileReader("keywords.dat"));
            while(fileIn.hasNext())
            {
                keywords.add(fileIn.nextLine());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This method finds non-keyword identifiers 
     * then stores them in the TreeMap, identifiers, 
     * along with the line number.
     */
    public void findIdentifiers()
    {
        try
        {
            while(sm.hasToken())
            {
                String token = sm.getToken();
                if(!keywords.contains(token)) //make sure it is not a keyword
                {
                    //if the word has not been seen, add word and line number.
                    if(!identifiers.containsKey(token)) 
                    {
                        identifiers.put(token, new LinkedList<Integer>());
                        identifiers.get(token).add(sm.getLineNumber());
                     
                    }
                    //if the word has been seen, only add line number.
                    else 
                    {
                        identifiers.get(token).add(sm.getLineNumber());
                    }

                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This method prints the TreeMap of identifiers.
     */
    public void printIdentifiers()
    {
        Set<Map.Entry<String, LinkedList<Integer>>> entries = identifiers.entrySet();
        for(Map.Entry<String, LinkedList<Integer>> entry : entries)
        {
            System.out.println(entry.getKey() + " =>" + entry.getValue());
        }
    }


    public static void main(String[] args) throws IOException
    {
        Lab5 lab = new Lab5();
        lab.printIdentifiers();
    }
}
