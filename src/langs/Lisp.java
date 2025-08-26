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


/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/** Minimal Lisp tokenizer + parser + tiny evaluator (no libraries). */
public class Main {

    // ====== AST ======
    interface Node {}
    static final class NumberNode implements Node {
        final double value;
        NumberNode(double v) { this.value = v; }
        public String toString() { return Double.toString(value); }
    }
    static final class SymbolNode implements Node {
        final String name;
        SymbolNode(String n) { this.name = n; }
        public String toString() { return name; }
    }
    static final class ListNode implements Node {
        final List<Node> elems;
        ListNode(List<Node> e) { this.elems = e; }
        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            for (int i = 0; i < elems.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(elems.get(i));
            }
            return sb.append(')').toString();
        }
    }

    // ====== Tokenizer ======
    enum Kind { LPAREN, RPAREN, NUMBER, SYMBOL, EOF }
    static final class Token {
        final Kind kind;
        final String lexeme;
        Token(Kind k, String s) { this.kind = k; this.lexeme = s; }
        public String toString() { return kind + (lexeme == null ? "" : (":" + lexeme)); }
    }

    static final class Lexer {
        private final String s;
        private int i = 0;

        Lexer(String src) { this.s = src; }

        private boolean done() { return i >= s.length(); }
        private char peek()   { return s.charAt(i); }
        private char next()   { return s.charAt(i++); }

        Token nextToken() {
            // skip whitespace
            while (!done()) {
                char c = peek();
                if (Character.isWhitespace(c)) { i++; continue; }
                // ; comment to end of line
                if (c == ';') { while (!done() && peek() != '\n') i++; continue; }
                break;
            }
            if (done()) return new Token(Kind.EOF, null);

            char c = next();
            if (c == '(') return new Token(Kind.LPAREN, "(");
            if (c == ')') return new Token(Kind.RPAREN, ")");

            // number: [+-]? digits ( '.' digits )?
            if (isNumStart(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                boolean hasDot = (c == '.');
                while (!done()) {
                    char d = peek();
                    if (Character.isDigit(d)) { sb.append(next()); }
                    else if (d == '.' && !hasDot) { hasDot = true; sb.append(next()); }
                    else break;
                }
                // special case: lone '+' or '-' should be symbol, not number
                String lx = sb.toString();
                if (lx.equals("+") || lx.equals("-")) {
                    return new Token(Kind.SYMBOL, lx);
                }
                return new Token(Kind.NUMBER, lx);
            }

            // symbol: sequence until whitespace or paren
            if (isSymbolStart(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (!done()) {
                    char d = peek();
                    if (Character.isWhitespace(d) || d == '(' || d == ')') break;
                    sb.append(next());
                }
                return new Token(Kind.SYMBOL, sb.toString());
            }

            throw new RuntimeException("Unexpected character: '" + c + "'");
        }

        private boolean isNumStart(char c) {
            return Character.isDigit(c) || c == '.' || c == '+' || c == '-';
        }
        private boolean isSymbolStart(char c) {
            // allow typical Lisp symbol chars (very permissive)
            return !Character.isWhitespace(c) && c != '(' && c != ')';
        }
    }

    // ====== Parser: program := sexpr*
    static final class Parser {
        private final Lexer lx;
        private Token lookahead;

        Parser(String src) {
            this.lx = new Lexer(src);
            this.lookahead = lx.nextToken();
        }

        private void consume(Kind k) {
            if (lookahead.kind != k)
                throw new RuntimeException("Expected " + k + " but found " + lookahead);
            lookahead = lx.nextToken();
        }

        Node parseOne() {
            if (lookahead.kind == Kind.EOF) return null;
            return sexpr();
        }

        Node sexpr() {
            switch (lookahead.kind) {
                case NUMBER: {
                    double v = Double.parseDouble(lookahead.lexeme);
                    consume(Kind.NUMBER);
                    return new NumberNode(v);
                }
                case SYMBOL: {
                    String n = lookahead.lexeme;
                    consume(Kind.SYMBOL);
                    return new SymbolNode(n);
                }
                case LPAREN: {
                    consume(Kind.LPAREN);
                    List<Node> items = new ArrayList<>();
                    while (lookahead.kind != Kind.RPAREN) {
                        if (lookahead.kind == Kind.EOF)
                            throw new RuntimeException("Unclosed '('");
                        items.add(sexpr());
                    }
                    consume(Kind.RPAREN);
                    return new ListNode(items);
                }
                default:
                    throw new RuntimeException("Unexpected token: " + lookahead);
            }
        }
    }

    // ====== Minimal evaluator for demo (+ - * /) ======
    static final class Env {
        interface Fn { double call(List<Double> args); }
        final Map<String, Fn> fns = new HashMap<>();
        Env() {
            fns.put("+", args -> args.stream().mapToDouble(d -> d).sum());
            fns.put("-", args -> {
                if (args.isEmpty()) throw new RuntimeException("(-) needs args");
                if (args.size() == 1) return -args.get(0);
                double acc = args.get(0);
                for (int i = 1; i < args.size(); i++) acc -= args.get(i);
                return acc;
            });
            fns.put("*", args -> {
                double acc = 1.0;
                for (double d : args) acc *= d;
                return acc;
            });
            fns.put("/", args -> {
                if (args.isEmpty()) throw new RuntimeException("(/) needs args");
                if (args.size() == 1) return 1.0 / args.get(0);
                double acc = args.get(0);
                for (int i = 1; i < args.size(); i++) acc /= args.get(i);
                return acc;
            });
        }
    }

    static double eval(Node n, Env env) {
        if (n instanceof NumberNode) return ((NumberNode) n).value;
        if (n instanceof SymbolNode)
            throw new RuntimeException("Unbound symbol: " + ((SymbolNode) n).name);
        ListNode list = (ListNode) n;
        if (list.elems.isEmpty()) throw new RuntimeException("Empty list");
        Node head = list.elems.get(0);
        if (!(head instanceof SymbolNode))
            throw new RuntimeException("First element must be a symbol (function name)");
        String op = ((SymbolNode) head).name;
        Env.Fn fn = env.fns.get(op);
        if (fn == null) throw new RuntimeException("Unknown function: " + op);
        List<Double> args = new ArrayList<>();
        for (int i = 1; i < list.elems.size(); i++) args.add(eval(list.elems.get(i), env));
        return fn.call(args);
    }

    // ====== Demo REPL ======
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Env env = new Env();

        System.out.println("Mini Lisp (parser + arithmetic). Enter blank line to exit.");
        System.out.println("Try: (+ 6 (* 9 (/ 5 7)))");
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) break;

            try {
                Parser p = new Parser(line);
                Node n = p.parseOne();
                // Print AST then evaluation
                System.out.println("AST: " + n);
                double result = eval(n, env);
                System.out.println("=> " + result);
            } catch (RuntimeException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}

*/
