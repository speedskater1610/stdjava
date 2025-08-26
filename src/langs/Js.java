package langs;


import org.graalvm.polyglot.*;  // as the interpriter / js engine 
import java.nio.file.*;         // for file acces to run js file at once

public class 
Js 
{

    // both true by defualt
    boolean detailed_errors = true; // if true then detailed errers will be included 
    boolean colored_errors = true; // to set colors through ansi codes for colored errors

    /*
    * @breif - sets the variable `detailed_errors` & `colored_errors` to be able to get diffrent errors and formats for those errors
    * @scope - public static
    * @param "boolean set_detailed_error" - if this is true then it will set the `detailed_errors` var to true otherwisee set to false
    * @param "boolean set_colored_error" - if this is true then it will set the `colored_errors` var to true otherwise set to false
    * @return - void 
    */
    public static void 
    set_error(boolean set_detailed_error, boolean set_colored_error) 
    {
        if (set_detailed_error)  detailed_errors = true; else detailed_errors  = false; 
        if (set_colored_error) colored_errors = true; else colored_errors = false; 
    }


    /*
    * @breif - a function that  will use the `org.graalvm.polyglot.*` JS engine to interprit js code at runtime
    * @scope - public static
    * @param "String code" - A String that is the js code that gets run; 
    * @return - void, so nothing.
    */
    // to run a js string through the engine 
    public static void 
    js(String code) 
    {

        String RED = "";
        String YELLOW = "";
        String GREEN = "";
        String END = "";
        /*
            RED - code failed and program and cant be fixed 
            YELLOW - try again mopst likely followed by bc in a loop or warning you of something
            GREEN - error passed and all good now
        */

        // assign if colored errors is turned on
        if (colored_errors) 
            {
                RED = "\u001B[31m";
                YELLOW = "\u001B[33m";
                GREEN = "\u001B[32m";
                END = "\u001B[0m";
            }
        // I am going to be completly honest when I say I have no idea what any of this does 
        // thank the lord for stack overflow and for me knowing some programming
        try 
        (
            Context context = Context.newBuilder("js")
                .allowAllAccess(true) // java interop
                .out(System.out)      // js console output to java console
                .build()
        ) 
        {

            // console input through the readLine(); function in js code
            // scanner -- console input
            Scanner sc = new Scanner(System.in);

            // expose a java function into js named "readLine"
            context.getBindings("js").putMember("readLine", (ProxyExecutable) arguments -> 
            {    
                // set up the scanner
                Scanner sc = new Scanner(System.in);
                
                String prompt = arguments.length > 0 ? arguments[0].asString() : "";
                String input = "";


                // for error ansi escape code formating


                // I could have simplified this so much if java just gave me `goto`
                while (input.isBlank()) 
                    { 
                        // loop until the iknput isnt blank meaning the user entered something other than ""
                        System.out.print(prompt);
                        input = sc.nextLine();
                        if (input.isBlank() && detailed_errors) 
                            {
                                System.out.println(YELLOW + "\nError reading the input, it was either blank or misread\t ?>" + END);    // ansi code for red meaning error
                            }
                        else 
                            {
                                System.out.println(YELLOW + "\n\t ?>" + END);
                            }
                    }

                
                return input;
            });

            // run the js code
            context.eval("js", code);
            
        }
        catch (PolyglotException e) 
            {
                if (detailed_errors)
                    {
                        // js errors
                        System.out.println(RED + "JavaScript Error: " + e.getMessage() + END);
                
                        if (e.isSyntaxError()) 
                            {
                                System.out.println(YELLOW + "ERROR - Syntax Error in js code" + END);
                            } 
                        else if (e.isInternalError()) 
                            {
                                System.out.println(YELLOW + "ERROR - Internal JS engine error." + END);
                            } 
                        else if (e.isGuestException()) 
                            {
                                System.out.println(YELLOW + "ERROR - Runtime error in JS: " + e.getMessage() + END);
                            }
                
                    } 
                else 
                    {
                        System.out.println(RED + "ERROR - js" + END);
                    }
            }
        catch (Exception e) 
            {
                // all othger java errors like file reading issues
                System.out.println(RED + "Java Error: " + e.getMessage() + END);
            }
        
    }

    
    /*
    * @breif - runs a enitre js file at once
    * @param "String path" - the path to thr js file that you want run
    * @scope - public static
    * @return - void
    */
    // to run a enter a js file at once
    public static void 
    jsFile(String path) throws Exception 
    {
        String code = Files.readString(Path.of(path));
        js(code);
    }

    
}
