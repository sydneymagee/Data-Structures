public class Pet
{
    private String name;
    private int age;
    private double weight;
    

    public Pet ( String initialName , int initialAge ,  double initialWeight )                  
    {
        name = initialName;
        age = initialAge;
        weight = initialWeight;
    }


    public void setPet ( String newName , int newAge , double newWeight )
    {
        name = newName;
        age = newAge;
        weight = newWeight;
    }

    public void setName ( String newName )
    {
        name = newName; 
    }
    
    public void setAge ( int newAge )
    {
            age = newAge;
    }

    public void setWeight ( double newWeight )
    {
            weight = newWeight;
    }

    public String getName ( )
    {
        return name;
    }


    public int getAge ( )
    {
        return age;
    }

   
    public double getWeight ( )
    {
        return weight;
    }

}
