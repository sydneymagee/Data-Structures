import java.util.Arrays;
/**
 * OutOfOrder.java
 * 
 */

 
/**
 * Counting out-of-order pairs in an array.
 * 
 * This class implements two different algorithms for
 * computing the number of out-of-order pairs in an int array.
 *
 * @author Sydney Magee
 * @version 1
 *
 */
    

public class OutOfOrder
{

    /**
     * Computes number out-of-order by checking every pair.
     * 
     * @param a    A list of integers
     * @return     The number of pairs out of order
     *             in the list 
     */
    public static int countOutOfOrder1(int [] a)
    {
        // put your code here
        int count = 0;
        for(int i = 0; i < a.length - 1; i++)
        {
            for(int j = i; j < a.length; j++)
             {
                 if(a[i] > a[j])
                 {
                    count++;   
                 }
             } 
        }
        return count;
    }

    /**
     * Computes number out-of-order while doing a mergesort.
     * 
     * @param a    A list of integers
     * @return     The number of pairs out of order
     *             in the list 
     */
    public static int countOutOfOrder2(int [] a)
    {
        int count = 0;
        if(a.length < 2)
        {
            return count;
        }
        else
        {
            int midpoint = ((a.length)/2);  
            count += mergeSortAndCount(a, 0, midpoint);
            count += mergeSortAndCount(a, midpoint+1, a.length -1);
            count += mergeAndCount(a, 0, midpoint, a.length-1);       
        return count;
        }
    }

/**
  * This is a merge method that I found on:
  * https://www.geeksforgeeks.org/counting-inversions/
  * 
*/
   private static int mergeAndCount(int[] arr, int l, int m, int r) 
    { 
  
        // Left subarray 
        int[] left = Arrays.copyOfRange(arr, l, m + 1); 
  
        // Right subarray 
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1); 
  
        int i = 0, j = 0, k = l, swaps = 0; 
  
        while (i < left.length && j < right.length) { 
            if (left[i] <= right[j]) 
                arr[k++] = left[i++]; 
            else { 
                arr[k++] = right[j++]; 
                swaps += (m + 1) - (l + i); 
            } 
        } 
  
        // Fill from the rest of the left subarray 
        while (i < left.length) 
            arr[k++] = left[i++]; 
  
        // Fill from the rest of the right subarray 
        while (j < right.length) 
            arr[k++] = right[j++]; 
  
        return swaps; 
    } 
    

/**
 * This is a mergeSort method I found on:
 * https://www.geeksforgeeks.org/counting-inversions/ 
 *
*/
private static int mergeSortAndCount(int[] arr, int l, int r) 
    { 
  
        // Keeps track of the inversion count at a 
        // particular node of the recursion tree 
        int count = 0; 
  
        if (l < r) { 
            int m = (l + r) / 2; 
  
            // Total inversion count = left subarray count 
            // + right subarray count + merge count 
  
            // Left subarray count 
            count += mergeSortAndCount(arr, l, m); 
  
            // Right subarray count 
            count += mergeSortAndCount(arr, m + 1, r); 
  
            // Merge count 
            count += mergeAndCount(arr, l, m, r); 
        } 
  
        return count; 
    } 
}


