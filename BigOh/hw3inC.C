     /*
      *********************************************************************
      *  Program Name:   Four Algorithms for Max Subsequence Sum       
      *  Authors:        Alice McRae,     
      *                  Mark Weiss - From pages 24-29 of text.
      *  Assignment:     Lab #2
      *  Purpose:        To determine experimentally the run time complexity 
      *                  of some algorithms given in our text.
      *                     
      *  Input:          The number of elements in the list
      *  Output:         The time taken by the algorithm   
      *               
      *  Functions:      For cs, the time units are 1/60 second.
      *  History:        
      *********************************************************************
      */

#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

const int RANGE = 50;
const int CLOCK_PRECISION = 16666;   

int alg1 (const int a[], const unsigned int n);
int alg2 (const int a[], const unsigned int n);
int alg3 (const int a[], const unsigned int n);
int alg3_recurses (const int a[], const int left, const int right);
int alg4 (const int a[], const unsigned int n);

int menu();
void fill (int *a, const int num_elements);
int *allocate_memory (int *a, int& num_elements);
int max3 (const int i, const int j, const int k);


int main()
{

   int *a = NULL;
   int choice;
   int num_elements;
   int sum;
   long int starttime, finishtime;


    /* initialize random number generator - done once */

    srandom(time(0));


    /* build the array */

    a = allocate_memory(a, num_elements); 

    /* run the algorithms */

    choice = menu(); 
    while (choice != 7) 
    {
       if (choice < 5)
           starttime = (long int) clock();
       switch (choice)
       {
          case 1: sum = alg1(a, num_elements);
                  break;
          case 2: sum = alg2(a, num_elements);
                  break;
          case 3: sum = alg3(a, num_elements);
                  break;
          case 4: sum = alg4(a, num_elements);
                  break;
          case 5: fill(a, num_elements); 
                  break;
          case 6: a = allocate_memory(a, num_elements); 
                  break;
          default: cout << "Invalid choice: Select again\n";
      } 

      if (choice >= 1 && choice <= 4)
      {
         finishtime = (long int) clock();
         cout << "The time to run is ";
         cout << (finishtime - starttime)/CLOCK_PRECISION <<
                    " time units.\n"; 
         cout << "The sum is " << sum << "\n\n\n\n" << endl;
      }
      choice = menu(); 
   }
}

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


int alg1 (const int a[], const unsigned int n)
{
    int this_sum = 0,  max_sum = 0;

    for (int i=0; i<n; i++)
       for (int j=i; j<n; j++)
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

int alg2 (const int a[], unsigned int n)  
{
    int this_sum = 0, max_sum = 0; 

    max_sum = 0; 
    for (int i=0; i<n; i++)
    {
        this_sum = 0;
        for (int j=i; j<n; j++)
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

      int alg3 (const int a[], const unsigned int n)
      {
         return alg3_recurses (a, 0, n - 1);
      }

     /*
      *  Params: a - the list to find the maxsequence sum in
                 left - the left index  
                 right - the right index 
      */
      
int alg3_recurses (const int a[], const int left, const int right)
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

int alg4 (const int a[], const unsigned int n)

{
    int this_sum, max_sum, j;

   for (int i = 0; i < 100; i++)
   {
    this_sum = max_sum = 0; 
    for (j=0; j<n; j++)
    {
        this_sum += a[j];
        if (this_sum > max_sum)   /* update max_sum  */
             max_sum = this_sum;
        else if (this_sum < 0)
          this_sum = 0;
   }
  }
   return max_sum;
}


    /*
      *********************************************************************
      *+                     menu 
      *  Returns: the choice of which algorithm to run or a choice 
                  to change the data in the list. 
      *********************************************************************
      */

int menu()
{
    int choice=0, result;

    cout << "**************************************************\n";
    cout << "           MAX SUBSEQUENCE ALGORITHMS  \n";
    cout << "**************************************************\n";
    cout << "       1.  Algorithm #1 \n";
    cout << "       2.  Algorithm #2 \n";
    cout << "       3.  Algorithm #3 \n";
    cout << "       4.  Algorithm #4 \n";
    cout << "       5.  Change values in the array \n";
    cout << "       6.  Change the size of the array \n";
    cout << "       7.  Quit  \n";
    cout << "**************************************************\n";
    cout << "\n\nEnter your choice: ";
    cin >> choice;
    if (!cin) {
       cin.clear();
       cin.ignore(100,'\n');
       return 0;
    }
    else 
       return choice;
}


    /*
      *********************************************************************
      *+                       Fill  
      *+ Fills the list with values between -RANGE/2 and RANGE/2
      *********************************************************************
    */

void fill (int *a, const int num_elements)
{
    int i;
   
    /*     random number chosen from -RANGE/2 to RANGE/2     */
 
    for (i = 0; i < num_elements; i++)
        a[i] = random() / 16 % RANGE - RANGE / 2;

}

    /*
      *********************************************************************
      *+        Allocate memory 
      *+ Allocates memory for a list and fills it 
      *********************************************************************
   */

int *allocate_memory (int *a, int &num_elements)
{

   if (a != NULL)                     // deallocate the old array
      delete [] a;
 
   cout << "How many elements: ";
   cin >> num_elements;
   if (!cin) {
      cin.clear();
      cin.ignore(100,'\n');
      cout << "Invalid choice.  Default value: 1000 elements\n";
      num_elements = 1000;
   }
   a = new int[num_elements];
   fill (a, num_elements);   
   return a;
}


    /*
      *********************************************************************
      *+        max3 
      *  Inputs: 3 integers 
      *  Return value:  the largest of the integers 
      *********************************************************************
   */

int max3 (const int i, const int j, const int k)
{
   if (i >= j && i >= k)
     return i;
   else if ( j >= k)
     return j;
   else
     return k;
}
