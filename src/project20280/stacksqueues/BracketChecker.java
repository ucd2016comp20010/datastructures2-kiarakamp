package project20280.stacksqueues;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        // TODO
        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("error: missing left delimiter");
                    return;
                }

                char top = stack.pop();
                if ((top == '(' && ch != ')') || (top == '[' && ch != ']') ||
                        (top == '{' && ch != '}')) {
                    System.out.println("matching error");
                    return;
                }
            }
        }

        if(!stack.isEmpty()) {
            System.out.println("error: missing right delimiter");
        }

    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        String[] inputs2 = {
                "{[()]}",
                "{[(])}",
                "{{[[(())]]}}",
                "][]][][[]][]][][[[",
                "(((abc))((d)))))",
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }

        System.out.println("\n\n");

        for (String input : inputs2) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}