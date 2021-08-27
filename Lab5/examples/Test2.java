// More information can be found in the Pattern class

import java.util.Scanner;

public class Test2 
{
    public static void main(String[] args)
    {
         String input;
         Scanner scan = new Scanner(System.in);

         System.out.println ("Enter some text, `bye' to quit: ");
         input = scan.nextLine();
         while (!(input.toLowerCase().startsWith("bye"))) {
            if (input.matches("^ID: [0-1][5-9]-[0-9][0-9]$")) 
               System.out.println ("match");
            else
               System.out.println ("not a match");
            System.out.println ("\nMore text: ");
            input = scan.nextLine();
         } 
         System.out.println ("Good bye");
    }
}
