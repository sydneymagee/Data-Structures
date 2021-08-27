
public class TryThis2
{

  public static class Item {
    private static int uniqueId = 1;
    private int itemId;
    private String itemName;
    public Item(String name) {
        itemName = name;
        itemId = uniqueId;
        uniqueId++;
    }
    public int getId() {
        return itemId;
    }
    public String getName() {
        return itemName;
    }
}


   public static void main(String[] args)

   {

       Item [] array = new Item[3];
       array[0] = new Item("wrench");
       array[1] = new Item("pliars");
       array[2] = new Item("hammer");

       for (Item i : array) 
            System.out.print(i.getId() + "  ");
    }
}

