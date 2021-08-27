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

public class Hw3 
{

     /*
      *********************************************************************
      *+         Algorithm 1 for Subsequence Sum 
      *+            from page 39 of Weiss' text 
      *  Author: Mark Weiss (typed in by Alice McRae)    
      *  Date:   January 20,1999    
      *  Params: a - the list to find the maxsequence sum in
                 n - the number of elements in the list    
      *  Returns: the maxsequence sum    
      *********************************************************************
      */


public static int alg1 (int[] a)
{
    int this_sum = 0,  max_sum = 0;

    for (int i=0; i<a.length; i++)
       for (int j=i; j<a.length; j++)
       {
           this_sum = 0;
           for (int k=i; k<=j; k++)
               this_sum += a[k];

           if (this_sum > max_sum)   
               max_sum = this_sum;
        }
    return max_sum;
}

     /*
      *********************************************************************
      *+         Algorithm 2 for Subsequence Sum
      *+            from page 40 of Weiss' text
      *  Author: Mark Weiss (typed in by Alice McRae)
      *  Date:   January 19,1999 
      *  Params: a - the list to find the maxsequence sum in
                 n - the number of elements in the list
      *  Returns: the maxsequence sum
      *********************************************************************
      */

public static int alg2 (int[] a)  
{
    int this_sum = 0, max_sum = 0; 

    max_sum = 0; 
    for (int i=0; i<a.length; i++)
    {
        this_sum = 0;
        for (int j=i; j<a.length; j++)
        {
           this_sum += a[j];
           if (this_sum > max_sum)   
               max_sum = this_sum;
        }
     }
     return max_sum;
}



     /*
      *********************************************************************
      *+         Algorithm 3 for Subsequence Sum
      *+            from page 42 of Weiss' text
      *+          (note this has 2 functions)
      *  Author: Mark Weiss (typed in by Alice McRae)
      *  Date:   January 19, 1999 
      *  Params: a - the list to find the maxsequence sum in
                 n - the number of elements in the list
      *  Returns: the maxsequence sum
      *********************************************************************
      */

      public static int alg3 (int[] a)
      {
         return alg3_recurses (a, 0, a.length - 1);
      }

     /*
      *  Params: a - the list to find the maxsequence sum in
                 left - the left index  
                 right - the right index 
      */
      
public static int alg3_recurses (int[] a,  int left,  int right)
{
     int max_left_sum, max_right_sum;
     int max_left_border_sum, max_right_border_sum;
     int left_border_sum, right_border_sum;
     int center, i;

     if (left == right)    /* basis case */
        if (a[left] > 0)
           return a[left];
        else
           return 0;

     center = (left + right)/2;
     max_left_sum = alg3_recurses (a, left, center);
     max_right_sum = alg3_recurses (a, center+1, right); 

     max_left_border_sum = 0; left_border_sum = 0;
     for (i=center; i>= left; i--) 
     {
        left_border_sum += a[i];
        if (left_border_sum  > max_left_border_sum)
           max_left_border_sum = left_border_sum;
     }

     max_right_border_sum = 0; right_border_sum = 0;
     for (i=center+1; i<= right; i++) 
     {
        right_border_sum += a[i];
        if (right_border_sum  > max_right_border_sum)
           max_right_border_sum = right_border_sum;
     }
    
     return max3(max_left_sum, max_right_sum,
                  max_left_border_sum + max_right_border_sum);

}


    /*
      *********************************************************************
      *+         Algorithm 4 for Subsequence Sum
      *+            from page 43 of Weiss' text
      *  Author: Mark Weiss (typed in by Alice McRae)
      *  Date:   January 20, 1999
      *  Params: a - the list to find the maxsequence sum in
                 n - the number of elements in the list
      *  Returns: the maxsequence sum
      *********************************************************************
      */

public static int alg4 (int[] a)

{
    int this_sum, max_sum, j;

    this_sum = max_sum = 0; 
    for (j=0; j<a.length; j++)
    {
        this_sum += a[j];
        if (this_sum > max_sum)   /* update max_sum  */
             max_sum = this_sum;
        else if (this_sum < 0)
          this_sum = 0;
   }
   return max_sum;
}

    /*
      *********************************************************************
      *+        max3 
      *  Inputs: 3 integers 
      *  Return value:  the largest of the integers 
      *********************************************************************
   */

public static int max3 (int i, int j, int k)
{
   if (i >= j && i >= k)
     return i;
   else if ( j >= k)
     return j;
   else
     return k;
}
}
