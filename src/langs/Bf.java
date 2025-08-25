package langs;



public class 
Bf 
{
    // bf all time save what has happneed from before to keep adding onto it
    public static void 
    bf_at (String code) 
    {
        
    }
    
    // bf single compute starts fromt he begiing as a clean slate every time
    public static int 
    bf_sc (String code) 
    {
        int[] tape = new int[30000];    // please java why are ints a class
        int ptr = 0;
        
        char[] prog = code.toCharArray();   // ts feels like ts 

        for (int i = 0; i < prog.length; i++) 
            {
                switch (prog[i]) 
                    {
                        // moving the `cursor`
                        case '>':
                            ptr++;
                            
                            if (ptr >= tape.length) 
                            ptr = 0; // wrap around if going to far 
                            
                            break;
                            
                        case '<':
                            ptr--;
                            
                            if (ptr < 0) 
                            ptr = tape.length - 1; // ptr cant go into negatives
                            
                            break;
                    
                        // add
                        case '+':
                            tape[ptr]++;
                            break;
                            
                        // sub
                        case '-':
                            tape[ptr]--;
                            break;
                            
                        // print a char
                        case '.':
                            System.out.print((char) (tape[ptr]));
                            break;
                            
                        case '#': // return the current ptr value and end 
                            return ((int) (tape[ptr]));
                            
                        // take a char in
                        case ',':
                            try 
                                {
                                    tape[ptr] = System.in.read(); // a single byte I think this is the same as stdin
                                } 
                            catch (Exception e) 
                                {
                                    e.printStackTrace();
                                }
                                
                            break;

                            
                        // loops 
                        case '[':
                            if (tape[ptr] == 0) 
                                {
                                    int loop = 1;
                                    while (loop > 0) 
                                        {
                                            i++;
                                            
                                            if (prog[i] == '[') 
                                            loop++;
                                            
                                            else if (prog[i] == ']') 
                                            loop--;
                                        }
                                }
                            break;
                            
                        case ']':
                            if (tape[ptr] != 0) 
                                {
                                    int loop = 1;
                                    
                                    while (loop > 0) 
                                        {
                                            i--;
                                            if (prog[i] == '[') loop--;
                                            else if (prog[i] == ']') loop++;
                                        }
                                
                                    
                                }
                            break;
                    }
            }
        return 0; //defualt return if there is no specified '#' returned value
    }
}
