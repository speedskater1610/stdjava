import static printUtils.C.printf;      
import static printUtils.Cpp.cout;
/* this took me like 20 minutes ro figure out that I had to have printUtils/print.java  */
/* why cant java just use header files... I dont want to have to deal with this. */


public class 
Main
{
    public static void 
    main(String[] args)
    {
        String name = "russel";
        
        
        printf("hello, %s\n",  name);
        

        cout()
            .out("hello, ")
            .out(name)
            .endl();
    }
}
