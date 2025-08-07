package printUtils; 


/* I think this is O(n) because each thing get looped through once. */
/* now I am confused does concatination cause this to be O(n^2) bc of the `+=` if `result` was not a `String` then it would be O(n) */
public class 
C 
{
    /**
     * @define the C printf, written in Java
     * @param String format - the main body of what is being printed
     * @param Object... args - argString to be the %_ formats
     * @return Void, but prints instead
     */
    public static void 
    printf(String format, Object... args) 
    {
        /* Pretty much I just have a next and I assign it to the %_ based of types then inc then repeat sort of like a repl but more a rel...p */
        String result = "";
        int argIndex = 0;

        for (int i = 0; i < format.length(); i++) 
        {
            char c = format.charAt(i);
            
            /* yes there is better way to do this with ot heavily nested ifs but I am too lazy and it is */
            if (c == '%' && i + 1 < format.length()) 
            {
                char next = format.charAt(i + 1);
                i++; /* skip the fmt char */
                    
                if (next == 's' || next == 'd' || next == 'f') 
                {
                    if (argIndex < args.length) 
                    {
                        result += args[argIndex].toString();
                        argIndex++;
                    } 
                    else 
                    {
                        result += "%" + next;
                    }
                } 
                else if (next == '%') 
                {
                    result += "%";
                } 
                else 
                {
                    result += "%" + next; /* unknown format so just assign the next thing */
                }
            } 
            else 
            {
                result += c;
            }
        }
        /* Yes I get the irony */
        System.out.printf("%s", result);
    }
};
