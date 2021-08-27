import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester
{
  public static void main(String [] args)
  {
       BufferedReader fileIn = null;
       StateMachine reader;
       if (args.length < 1) {
          System.out.println ("Usage: java Tester <filename>");
          System.exit(0);
       }

       try{
          fileIn = new BufferedReader (new FileReader(args[0]));
          reader = new StateMachine(fileIn);

          // test the FiniteStateMachine
          // by printing all the words in a Java file

          while (reader.hasToken())
             System.out.println (reader.getToken() + " "  
                   + reader.getLineNumber());
          fileIn.close();

      } catch (IOException ioe) {
            System.out.println ("File not found.  Exiting");
      }
  }
}

