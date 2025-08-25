package langs;


import org.graalvm.polyglot.*;

public class 
Js 
{
    public static void 
    js(String code) 
    {
        
        // I am going to be completly honest when I say I have no idea what any of this does 
        // thank the lord for stack overflow
        try 
        (
            Context context = Context.newBuilder("js")
                .allowAllAccess(true) // java interop
                .out(System.out)      // js console output to java console
                .build()
        ) 
        {
            context.eval("js", code);
        }
    }
}
