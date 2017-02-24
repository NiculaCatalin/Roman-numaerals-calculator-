package homework.classes.operators;

import homework.interfaces.operators.IOperator;

public abstract class Operator implements IOperator {
    protected String symbol;
    protected OperatorType type;
    private static String zeroPriority = "+-";
    private static String onePriority = "*/";
    private static String twoPriority = "^";
    private static String threePriority = "sqrt, log";
    private static final int LOWEST_PRIORITY = 0;
    private static final int LOW_PRIORITY = 1;
    private static final int MEDIUM_PRIORITY = 2;
    private static final int HIGH_PRIORITY = 3;

    protected static OperatorType getType(final String symbol) {
        OperatorType type = OperatorType.ADD;
        if (symbol.equals("+")) {
            type = OperatorType.ADD;
        } else if (symbol.equals("-")) {
            type = OperatorType.SUB;
        } else if (symbol.equals("*")) {
            type = OperatorType.MULT;
        } else if (symbol.equals("/")) {
            type = OperatorType.DIV;
        } else if (symbol.equals("^")) {
            type = OperatorType.POW;
        } else if (symbol.equals("sqrt")) {
            type = OperatorType.SQRT;
        } else if (symbol.equals("log")) {
            type = OperatorType.LOG;
        }
        return type;
    }

    @Override
    public final String getSymbol() {
        return symbol;
    }

    @Override
    public final void setSymbol(final String newSymbol) {
        this.symbol = newSymbol;
    }

    @Override
    public final int getPriority() {
        if (zeroPriority.contains(symbol)) {
            return LOWEST_PRIORITY;
        }
        if (onePriority.contains(symbol)) {
            return LOW_PRIORITY;
        }
        if (twoPriority.contains(symbol)) {
            return MEDIUM_PRIORITY;
        }
        if (threePriority.contains(symbol)) {

            return HIGH_PRIORITY;
        }
        return -1;
    }
}
