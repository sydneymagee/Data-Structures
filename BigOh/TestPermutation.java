import java.util.Scanner;

     /*
      *********************************************************************
      *  Program Name:   Three Algorithms for Creating a Random Permutation   
      *                      problem 2.7 Weiss text
      *  Authors:        Alice McRae,     
      *  Date:           Aug. 2020, from 2013    
      *  Assignment:     In class experiment 
      *  Purpose:        To determine experimentally the run time complexity 
      *                  of some algorithms given in our text.
      *                     
      *  Input:          The number of elements in the list
      *  Output:         The time taken by the algorithm   
      *               
      *  Functions:  
      *  History:        
      *********************************************************************
      */


public class TestPermutation 
{
        private static final int NUM_PER_LINE = 10;
        private int [] a;
        private Scanner cin;
        private PermutationGenerator pg;
        

        public TestPermutation()
        {
            int numElements;

            cin = new Scanner(System.in);
            pg = new PermutationGenerator();
            System.out.println("How many elements: ");
            numElements = cin.nextInt();
            a = new int[numElements];
            test();
        }
   

        private void test()
        {
           int  choice = menu();
           int numElements;
           long starttime = 0, finishtime = 0;

           while (choice != 6) 
           {
      
              if (choice >= 1 && choice <= 3)
                 starttime = System.currentTimeMillis();
              switch (choice)
              {
                 case 1: pg.alg1(a);
                         break;
                 case 2: pg.alg2(a);
                         break;
                 case 3: pg.alg3(a);
                         break;
                 case 4: 
                        System.out.println("How many elements: ");
                        numElements = cin.nextInt();
                        a = new int[numElements];
                        break;
                case 5: printPermutation();
                        break;
                default: 
                      System.out.println( "Invalid choice: Select again\n");
              } 
              if (choice >= 1 && choice < 4)
              {
                  finishtime = System.currentTimeMillis();
                  System.out.println("The time to complete is " +
                 (finishtime - starttime) + " time units.\n\n\n\n");
              }
              choice = menu(); 
           }
      }


    /*
      *********************************************************************
      *+                     menu 
      *  Returns: the choice of which algorithm to run or a choice 
                  to change the data in the list. 
      *********************************************************************
      */

    private int menu()
    {
        int choice;

        System.out.println
              ("**************************************************");
        System.out.println("           ARRAY FILL ALGORITHMS  ");
        System.out.println
            ("**************************************************");
        System.out.println("       1.  Algorithm #1 ");
        System.out.println("       2.  Algorithm #2 ");
        System.out.println("       3.  Algorithm #3 ");
        System.out.println("       4.  Change the size of the array");
        System.out.println("       5.  Print the array");
        System.out.println("       6.  Quit  ");
        System.out.println
            ("**************************************************");
        System.out.println("\n\nEnter your choice: ");
        choice = cin.nextInt();
        return choice;
    }

    /*
      *********************************************************************
      *+                    printPermutation 
      *  Returns: the choice of which algorithm to run or a choice 
                  to change the data in the list. 
      *********************************************************************
      */
     public void printPermutation()
    {
        System.out.println();
        for (int i = 0; i < a.length; i++)
        {
            System.out.printf("%6d\t", a[i]);
            if (i % NUM_PER_LINE == NUM_PER_LINE - 1)
            {
                System.out.println();
            }
        }
        if (a.length % NUM_PER_LINE != 0)
        {
            System.out.println();
        }
        System.out.println("\n\n");


    }

    public static void main(String [] args)
    {
        new TestPermutation();
    }
}
