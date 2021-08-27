import java.util.Random;

     /*
      *********************************************************************
      *  Program Name:   Three Algorithms for Creating a Random Permutation   
      *                      problem 2.8 Weiss text
      *  Authors:        Alice McRae,     
      *  Date:           from Feb. 1995 in C, modified many times    
      *  Assignment:     In class exercise 
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


public class PermutationGenerator 
{
     private static Random randomGenerator;

     public PermutationGenerator()
     {
         randomGenerator = new Random();
     }  

     /*
      *********************************************************************
      *+         Algorithm 1 for Random Array 
      *+            from exercise 2.7 of Weiss' text 
      *  Author: Mark Weiss pseudocode (typed in by Alice McRae)    
      *  Date:   Sept. 7, 1996, modified 2013   
      *  Params: a - the permutation to create 
      *          n - the number of elements in the list    
      *  Method: one by one, fill the list in this way:
      *          choose a random number between 1 and n-1 and
      *          look through the list to make sure it doesn't already
      *          appear in the list.  If it is in the list, choose again.
      *********************************************************************
      */


    public void alg1 (int [] a)
    {
         int i, j, random_number;
         boolean duplicate;
         int n = a.length;

         for (i=0; i < n; i++)
         {
             do 
             {
                 duplicate = false;
                 random_number = randomGenerator.nextInt(n);
                 for (j=0; j <i && !duplicate; j++)
                     if (a[j] == random_number)
                        duplicate = true;
             } while (duplicate);
             a[i] = random_number;
         }
     }


     /*
      *********************************************************************
      *+         Algorithm 2 for Random Array 
      *+            from exercise 2.7 of Weiss' text 
      *  Author: Mark Weiss pseudocode (typed in by Alice McRae)    
      *  Date:   Sept. 7, 1996, modified 2013    
      *  Params: a - the permutation to create 
      *          n - the number of elements in the list    
      *  Method: one by one, fill the list in this way:
      *          choose a random number between 1 and n-1 and
      *          check a used array to make sure it doesn't already
      *          appear in the list.  If it is in the list, choose again.
      *********************************************************************
      */


    public void alg2 (int [] a)
    {
        int i, random_number;
        int n = a.length;
        boolean [] used = new boolean[n]; 

        for (i=0; i < n; i++)
        used[i] = false;
      
        for (i=0; i < n; i++)
        {
           do 
             random_number = randomGenerator.nextInt(n);
           while (used[random_number]);
           a[i] = random_number;
           used[random_number] = true;
        }
    }



     /*
      *********************************************************************
      *+         Algorithm 3 for Random Array
      *+            from exercise 2.7 of Weiss' text
      *  Author: Mark Weiss pseudocode (typed in by Alice McRae)
      *  Date:   Sept. 7, 1995,modified 2013
      *  Params: a - the permutation to create
      *          n - the number of elements in the list
      *  Method: first fill the array with numbers 0 to n-1 
      *          then shuffle  by picking an array element to go first,  
      *          swapping that with the first element, then picking an 
      *          element to go second and swapping that one, etc. 
      *********************************************************************
      */

    public void alg3 (int [] a)
    {

       int i, random_number;
       int temp;

       int n = a.length;
       for (i=0; i < n; i++)
          a[i] = i;

       for (i=0; i < n-1; i++)
       {
          random_number = randomGenerator.nextInt(n-i)+ i;
          temp = a[i];
          a[i] = a[random_number];
          a[random_number] = temp;
       }
    }
      
}
