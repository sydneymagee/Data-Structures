     /*
      *********************************************************************
      *  Program Name:   Four Algorithms for Max Subsequence Sum       
      *  Authors:        Alice McRae,     
      *                  Mark Weiss - From pages 24-29 of text.
      *  Assignment:     Hw #3
      *  Purpose:        To determine experimentally the run time complexity 
      *                     
      *  Input:          The number of elements in the list
      *  Output:         The time taken by the algorithm   
      *               
      *  Functions:      For cs, the time units are 1/60 second, I think.
      *  History:        
      *********************************************************************
      */

import java.util.Scanner;
import java.util.Random;

public class TestHw3 
{

    private final static int RANGE = 500;  
    private Random rand;
    private Scanner cin;
    private int [] a;


    public TestHw3()
    {
        int numElements;
        rand = new Random();
        cin = new Scanner(System.in);
        System.out.print("How many elements: ");
        numElements = cin.nextInt();
        a = new int[numElements];
        fillArray();   
        test();
    }
        

    public void test()
    {
       int choice, numElements;
       int sum = 0;
       long starttime = 0,  finishtime;

       choice = menu(); 
       while (choice != 7) 
       {
           if (choice < 5)
           {
              System.gc();
              starttime = System.currentTimeMillis();
           }
           switch (choice)
           {
               case 1: sum = Hw3.alg1(a);
                      break;
               case 2: sum = Hw3.alg2(a);
                      break;
               case 3: sum = Hw3.alg3(a);
                      break;
               case 4: sum = Hw3.alg4(a);
                      break;
               case 5: fillArray(); 
                     break;
               case 6: 
                   System.out.print("How many elements: ");
                   numElements = cin.nextInt();
                   a = new int[numElements];
                   fillArray ();   
                   break;
               default: System.out.println
                          ("Invalid choice: Select again\n");
           } 

           if (choice >= 1 && choice <= 4)
           {
               finishtime = System.currentTimeMillis();
               System.out.println("The sum is " + sum); 
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

public int menu()
{
    int choice=0, result;

    System.out.println("**************************************************");
    System.out.println("           MAX SUBSEQUENCE ALGORITHMS  ");
    System.out.println("**************************************************");
    System.out.println("       1.  Algorithm #1 ");
    System.out.println("       2.  Algorithm #2 ");
    System.out.println("       3.  Algorithm #3 ");
    System.out.println("       4.  Algorithm #4 ");
    System.out.println("       5.  Change values in the array ");
    System.out.println("       6.  Change the size of the array ");
    System.out.println("       7.  Quit  ");
    System.out.println("**************************************************");

    System.out.print("\n\nEnter your choice: ");
    choice = cin.nextInt();

   if (choice < 1 || choice > 7)
      choice = 7;

   return choice;
}


/*
 *********************************************************************
 *+                       fillArray  
 *+ Fills the list with values between -RANGE/2 and RANGE/2
 *********************************************************************
 */

public void fillArray ()
{
   //     random number chosen from -RANGE/2 to RANGE/2     

   for (int i = 0; i < a.length; i++)
      a[i] = (int) (Math.random() * RANGE - RANGE / 2);

}

    public static void main(String[] args)
    {
        new TestHw3();
    }


}
