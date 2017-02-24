package homework.classes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import homework.classes.brackets.BracketsFactory;
import homework.classes.operands.OperandsFactory;
import homework.classes.operators.OperatorsFactory;
import homework.interfaces.IServer;
import homework.interfaces.IToken;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;

public final class Server implements IServer {
    private static Server instance;

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    private List<String> results;
    private Set<String> acceptableOps;
    private List<IToken> tokens;
    private Stack<IToken> tokenStack;

    private Server() {
        results = new LinkedList<>();
        acceptableOps = new HashSet<>();
        tokens = new LinkedList<>();
        tokenStack = new Stack<>();
    }

    @Override
    public boolean canPublish(final String[] newTokens) {
        for (String tok : newTokens) {
            if (!BracketsFactory.getInstance().isBracket(new Token(tok))) {
                if (OperandsFactory.getInstance().
                        convertToArabNumber(tok) == null) {
                    if (!acceptableOps.contains(tok)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void publish(final String command) {
        String[] commandTokens = command.split("\\ ");
        if (!canPublish(commandTokens)) {
            results.add("IMPOSSIBRU");
            return;
        }

        for (String tok : commandTokens) {
            if (OperandsFactory.getInstance().
                    convertToArabNumber(tok) != null) {
                tokens.add(new Token(tok));
            } else if (BracketsFactory.getInstance().
                    isBracket(new Token(tok))) {
                if (BracketsFactory.getInstance().isOpenedBracket(
                        BracketsFactory.getInstance().createBracket(tok))) {
                    tokenStack.add(new Token(tok));
                } else {

                    IToken stackToken;
                    stackToken = tokenStack.pop();
                    while (!BracketsFactory.getInstance()
                            .isOpenedBracket(BracketsFactory.getInstance().
                                    createBracket(stackToken.getSymbol()))) {
                        tokens.add(stackToken);
                        stackToken = tokenStack.pop();
                    }

                }
            } else {

                if (!tokenStack.isEmpty()) {
                    IToken stackOp = tokenStack.peek();
                    if (OperatorsFactory.getInstance().isOperator(stackOp)) {
                        while (OperatorsFactory.getInstance().isOperator(
                              stackOp) && OperatorsFactory.getInstance()
                             .createOperator(stackOp.getSymbol())
                             .getPriority() >= OperatorsFactory.getInstance()
                             .createOperator(tok).getPriority()) {

                            stackOp = tokenStack.pop();
                            tokens.add(stackOp);
                            if (!tokenStack.isEmpty()) {
                                stackOp = tokenStack.peek();
                            } else {
                                break;
                            }
                        }
                    }
                }
                tokenStack.push(new Token(tok));
            }

        }
        IToken stackToken;
        while (!tokenStack.isEmpty()) {
            stackToken = tokenStack.pop();
            tokens.add(stackToken);
        }
        calculate();
        tokens.clear();
    }

    public void calculate() {
        double operand;
        double rightOp;
        double leftOp;
        Stack<Double> numberStack = new Stack<Double>();

        for (IToken tok : tokens) {
            if (OperandsFactory.getInstance().
                    convertToArabNumber(tok.getSymbol()) != null) {
                numberStack.add(OperandsFactory.getInstance().
                        convertToArabNumber(tok.getSymbol()).getSymbolValue()
                        .doubleValue());
            } else {
                if (OperatorsFactory.getInstance()
                        .isBinaryOperator(OperatorsFactory.getInstance().
                                createOperator(tok.getSymbol()))) {
                    rightOp = numberStack.pop();
                    leftOp = numberStack.pop();
                    IBinaryOperator<Number> op = (IBinaryOperator<Number>)
                            OperatorsFactory.getInstance()
                            .createOperator(tok.getSymbol());
                    if (op.getSymbol().equals("/") && rightOp == 0) {
                        results.add("IMPOSSIBRU");
                        return;
                    }
                    numberStack.push(op.calculate(leftOp, rightOp).
                            getSymbolValue().doubleValue());
                } else {
                    operand = numberStack.pop();
                    IUnaryOperator<Number> op = (IUnaryOperator<Number>)
                            OperatorsFactory.getInstance()
                            .createOperator(tok.getSymbol());
                    numberStack.push(op.calculate(operand).
                            getSymbolValue().doubleValue());
                }
            }
        }
        if (numberStack.peek() % 1 < 0) {
            Double aux = numberStack.pop() - 1;
            numberStack.push(aux);
        }
        results.add(OperandsFactory.getInstance().convertToRomanNumber(
                numberStack.pop()).getSymbol());
    }

    @Override
    public void subscribe(final String operator) {
        acceptableOps.add(operator);
    }

    @Override
    public List<String> getResults() {
        return results;
    }

}
