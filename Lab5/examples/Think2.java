public class Think2
{

   public static void main(String [] args)
   {

       Think2 grr = new Think2();
       System.out.print ("Make a String with holiday : ");
       System.out.println (grr.makeAString("holiday", 0, 6)); 
       System.out.print ("Test gcd of 30, 144: ");
       System.out.println (grr.mystery(144,30)); 
       System.out.println ("Testing think");
       grr.think(new double [] {1,3,4,5,6, 10, 12}, 0,7); 
       System.out.print ("Testing longGoodbye(5) ");
       grr.longGoodbye(5); 
   }

   public String makeAString(String str, int left, int right) {
    if (left > right) return "";
    else if (left == right) return "" + str.charAt(left);
    else {
        String str2 = makeAString(str, left+1, right-1);
        return str2 + str.charAt(left) + str.charAt(right);
    }
   }

    public int mystery(int num1, int num2) {
    if (num1 < num2)
        return mystery(num2, num1);
    else if (num2 == 0)
        return num1;
    else
        return mystery(num2, num1%num2);
    }

   public void longGoodbye( int n ) {
    if (n <= 1)
        System.out.println("Goodbye");
    else {
        longGoodbye(n-1);
        longGoodbye(n-2);
    }
    }

    public void think ( double [] arr, int left, int n ) {
    int middle = left + n/2;
    if (n > 1) {
        for (int i = 0; i < n; i++)
            arr[i+left] += n * Math.random();
        think(arr, left, n/2);
        think(arr, middle, n-n/2);
    }
    }

   
}



