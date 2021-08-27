import java.util.Scanner;
import java.util.Random;

public class Testing
{

    private Random rand;
    private int numTrials;
    private int minValues;
    private int range;
    private double inputValue;

    public Testing (int nt, int max, int min, double input)
    {
        
        rand = new Random();
        numTrials = nt;
        minValues = min;
        range = max - min + 1;
        inputValue = input;
    }

    public void runTest()
    {
       int count = 0, values;
       for (int i = 0; i < numTrials; i++) {
           if (range > 1)
              values = rand.nextInt(range) + minValues;
           else
              values = minValues;
           if (playGame(values))
              count++;
       } 
       int win = count;
       int loss = numTrials - count;
       System.out.println ("Win: " + win + " Loss " + loss + " " +
                  (100 * count /numTrials) + "%"); 
    
    }
    
public boolean playGame (int deckSize)
{
     int max = rand.nextInt();
     int nextNumber;
     int myGuess = max-1;
     boolean madeGuess = false;
     // int stopValue = (int) (deckSize / Math.E + 0.5);
     int stopValue = (int) (deckSize / inputValue + 0.5);
     for (int i = 1; i < deckSize; i++) { 
        nextNumber = rand.nextInt();
        if (!madeGuess && i >= stopValue && nextNumber > max) {
           myGuess = nextNumber;
           madeGuess = true;
        }
        if (nextNumber > max) {
           if (madeGuess && myGuess < nextNumber)
              return false;
           else
              max = nextNumber;
        }
     }
     return myGuess == max;
}

public static void main(String [] args)
{
     Scanner scan = new Scanner(System.in);
     System.out.println ("How many trials: ");
     int answer = scan.nextInt();
     System.out.println ("Min number of values: ");
     int min = scan.nextInt();
     System.out.println ("Max number of values: ");
     int max = scan.nextInt();
     System.out.println ("How far: 1/n: Enter n");
     double point = scan.nextDouble();
     Testing test = new Testing (answer, max, min, point); 
     test.runTest();
}
}

