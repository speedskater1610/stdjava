import static printUtils.C.printf;      // why cant java just use header files... I dont want to have to deal with this.
import static printUtils.Cpp.cout;      // this took me like 20 minutes ro figure out that I had to have printUtils/print.java 
import static langs.Bf.bf_sc;           //
// import static langs.bf.bf_sc;        //
//import static langs.Js.js;            // thank you codeHs
import static printUtils.Js.console;    //


public class 
Main
{
    public static void 
    main(String[] args)
    {

        // a random var to be used for the example
        String name = "bob";
        

        // how the printf function works
        printf("hello, %s\n",  name);
        

        // how to use cout
        cout()
            .out("hello, ")
            .out(name)
            .endl();


        // a use of the bf interpriter
        bf_sc("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");

        // use it for math
        Value result = js("1 + 2");
        System.out.println("JS result: " + result.asInt()); // prints 3


        js("""
            let x = 6;
            let y = 76;
            console.log("X: " + x + "\\nY: " + y);
        """);


        jsFile("test_files/javaScript.js");



        console.log("Hello, " + name + "!");
    }
}
