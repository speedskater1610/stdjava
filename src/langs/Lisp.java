package langs;


public class
lisp
{

    /*
    * @breif - tries to find the amount of, either a char or a string inside of a a larger string
    * @param "String what_to_search" - the string or char that we are trying to find the amount of times it aprears
    * @param "String where_to_search" - the larger string that gets looked at t find the amount of time what_to_search apears
    * @scope - private static, gets used as a helper inside of the lisp() function. 
    * @return - a int the count of how many times that `what_to_search` is found
    */
    private static int
    search_for_string_in_string (String what_to_search, String where_to_search)
    {
        int count = 0;

        for (int i = where_to_search.length(); i != 0;  --i) 
            {
                if (where_to_search[i] == what_to_search) ++count; 
            }
        
        return count;
    }


    /*
    * @breif - returns a number based  off off hat ssi calced from a lisp machine
    * @param "String problem"
    * @format - must  have everything in (...) so no "+ 6 (- 6 2)" it must be "(+ 6 (- 6 2))"; the opperationss that can curretly be used are +, -, *, /
    * @scope - public static, gets imported into langs
    * @return - a int that represents the final solution the to  provided problem
    */
    public static int
    lisp (String problem)
    {
        // TODO EVERYTHING!
        // serachs for all of the ( in the probelm to giv us the total number of operations
        int total_problems = search_for_string_in_string("(", problem);

        if (total_problems != search_for_string_in_string(")", problem)) 
            {    
                System.out.println("ERROR - lisp machine cant match all of the ( to all of the )");
                return; 
            }
    }
    
}
