     /*
      *********************************************************************
      *  Program Name:   Three Algorithms for Creating a Random Permutation   
      *                      problem 2.7 Weiss text
      *  Authors:        Alice McRae,     
      *  Date:           February 3, 1999    
      *  Assignment:     Lab #3
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

#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

const int NUM1 = 16807;
const int MODULUS = 2147483647;
const int QUANT = 127773;
const int REMAIN = 2836;

const int CLOCK_TICK = 16666; 


void alg1 (int a[],  int n);
void alg2 (int a[],  int n);
void alg3 (int a[],  int n);

int menu();
int *allocate_memory (int *a, int & num_elements);
int choose_between (int left, int right);
void swap (int & a, int  & b);
int generator_seed;   /* seed for random number generator */

using namespace std;
int main(void)
{

   int *a = NULL;
   int choice;
   int num_elements;
   long starttime, finishtime;

    /* initialize random number generator - done once */

    generator_seed = time(0) / 1000;


    /* build the array */

    a = allocate_memory(a, num_elements); 

    /* run the algorithms */

    choice = menu(); 
    while (choice != 5) 
    {
       if (choice >= 1 && choice <= 3)
           starttime = clock();
       switch (choice)
       {
          case 1: alg1(a, num_elements);
                  break;
          case 2: alg2(a, num_elements);
                  break;
          case 3: alg3(a, num_elements);
                  break;
          case 4: a = allocate_memory(a, num_elements); 
                  break;
          default: cout << "Invalid choice: Select again\n";
      } 
      if (choice >= 1 && choice <= 3)
      {
         finishtime = clock();
         cout << (finishtime - starttime) / CLOCK_TICK << endl;
      }
      choice = menu(); 
   }
}

     /*
      *********************************************************************
      *+         Algorithm 1 for Random Array 
      *+            from exercise 2.7 of Weiss' text 
      *  Author: Mark Weiss pseudocode (typed in by Alice McRae)    
      *  Date:   Sept. 7, 1996    
      *  Params: a - the permutation to create 
      *          n - the number of elements in the list    
      *  Method: one by one, fill the list in this way:
      *          choose a random number between 1 and n-1 and
      *          look through the list to make sure it doesn't already
      *          appear in the list.  If it is in the list, choose again.
      *********************************************************************
      */


void alg1 (int a[], int n)
{
    int i, j, duplicate, random_number;

    for (i=0; i < n; i++)
    {
       do 
       {
         duplicate = 0;
         random_number = choose_between(0,n-1);
         for (j=0; j <i && !duplicate; j++)
           if (a[j] == random_number)
              duplicate = 1;
       } while (duplicate);
       a[i] = random_number;
   }
}


     /*
      *********************************************************************
      *+         Algorithm 2 for Random Array 
      *+            from exercise 2.7 of Weiss' text 
      *  Author: Mark Weiss pseudocode (typed in by Alice McRae)    
      *  Date:   Sept. 7, 1996    
      *  Params: a - the permutation to create 
      *          n - the number of elements in the list    
      *  Method: one by one, fill the list in this way:
      *          choose a random number between 1 and n-1 and
      *          check a used array to make sure it doesn't already
      *          appear in the list.  If it is in the list, choose again.
      *********************************************************************
      */


void alg2 (int a[], int n)
{
    int i, random_number;
    int *used;

    used = new int[n]; 
    for (i=0; i < n; i++)
       used[i] = 0;
      
    for (i=0; i < n; i++)
    {
       do 
         random_number = choose_between(0,n-1);
       while (used[random_number]);
       a[i] = random_number;
       used[random_number] = 1;
    }
    delete [] used;
}



     /*
      *********************************************************************
      *+         Algorithm 3 for Random Array
      *+            from exercise 2.7 of Weiss' text
      *  Author: Mark Weiss pseudocode (typed in by Alice McRae)
      *  Date:   Sept. 7, 1996
      *  Params: a - the permutation to create
      *          n - the number of elements in the list
      *  Method: first fill the array with numbers 0 to n-1 
      *          then shuffle  by picking an array element to go first,  
      *          swapping that with the first element, then picking an 
      *          element to go second and swapping that one, etc. 
      *********************************************************************
      */

void alg3 (int a[], int n)
{

   int i, random_number;

   for (i=0; i < n; i++)
      a[i] = i;

   for (i=0; i < n-1; i++)
   {
      random_number = choose_between (i, n-1);
      swap (a[i], a[random_number]);
   }
}
      


    /*
      *********************************************************************
      *+        Swap 
      *  Params:  the addresses of the elements to be swapped
      *********************************************************************
      */

void swap ( int& a, int & b)
{
   int temp;

   temp = a;
   a = b;
   b = temp;
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
    int choice, result;

    cout << "**************************************************\n";
    cout << "           ARRAY FILL ALGORITHMS  \n";
    cout << "**************************************************\n";
    cout << "       1.  Algorithm #1 \n";
    cout << "       2.  Algorithm #2 \n";
    cout << "       3.  Algorithm #3 \n";
    cout << "       4.  Change the size of the array \n";
    cout << "       5.  Quit  \n";
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
      *+                       choose_between  
      *  Author:  Alice McRae and article I can't find again.
      *  Date:   Sept. 7, 1996
      *  Params: left - the lower bound on the integer to choose 
      *          right - the higher bound on the integer to choose 
      *  Return: a random integer between left and right, inclusive
      *********************************************************************
    */

int choose_between (int left, int right)
{
   double temp;
   int lo, hi, test;

   hi = generator_seed / QUANT;
   lo = generator_seed % QUANT;
   test = NUM1 * lo - REMAIN * hi;
   if (test > 0)
      generator_seed = test;
   else
      generator_seed = test + MODULUS;
   return (int) ((1.0 * generator_seed ) / MODULUS * (right - left + 1)) + left;
}

    /*
      *********************************************************************
      *+        Allocate memory
      *+ Allocates memory for a list and fills it
      *********************************************************************
   */

int * allocate_memory (int *a, int & num_elements)
{

   if (a != NULL)
      delete [] a;

   cout << "How many elements: ";
   cin >> num_elements; 

   a = new int [num_elements];
   return a;
}
