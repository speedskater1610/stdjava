package langs;


import org.graalvm.polyglot.*;  // as the interpriter / js engine 
import java.nio.file.*;         // for file acces to run js file at once

public class 
Js 
{
    // to run a js string through the engine 
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
    
    // to run a enitr js file at once
    public static void 
    jsFile(String path) throws Exception 
    {
        String code = Files.readString(Path.of(path));
        js(code);
    }   
}
