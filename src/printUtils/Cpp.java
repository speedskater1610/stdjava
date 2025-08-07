package printUtils;

public class 
Cpp 
{
    /* wait so this when calling Cout makes a new Cout object then returns it so that I can infinatly nest .out or .endl */  
    public static Cpp 
    cout() 
    {
        return new Cpp();
    }

    /* this acts like the `<<` oppertor */
    public Cpp 
    out(Object obj) 
    {
        System.out.print(obj);
        return this;
    }

    /* same thing as std::endl just ends the current printing line */
    public Cpp 
    endl() 
    {
        System.out.println();
        return this;
    }
    
    
}
